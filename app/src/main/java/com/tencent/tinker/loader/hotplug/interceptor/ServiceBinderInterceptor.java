package com.tencent.tinker.loader.hotplug.interceptor;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import com.tencent.tinker.loader.hotplug.interceptor.Interceptor;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class ServiceBinderInterceptor extends Interceptor<IBinder> {
    private static final String TAG = "Tinker.SvcBndrIntrcptr";
    private static Method sGetServiceMethod;
    private static Field sSCacheField;
    private static Class<?> sServiceManagerClazz;
    private final Context mBaseContext;
    private final BinderInvocationHandler mBinderInvocationHandler;
    private final String mServiceName;

    public interface BinderInvocationHandler {
        Object invoke(Object obj, Method method, Object[] objArr) throws Throwable;
    }

    private static class FakeClientBinderHandler implements InvocationHandler {
        private final BinderInvocationHandler mBinderInvocationHandler;
        private final IBinder mOriginalClientBinder;

        FakeClientBinderHandler(IBinder iBinder, BinderInvocationHandler binderInvocationHandler) {
            this.mOriginalClientBinder = iBinder;
            this.mBinderInvocationHandler = binderInvocationHandler;
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String str;
            if (!"queryLocalInterface".equals(method.getName())) {
                return method.invoke(this.mOriginalClientBinder, objArr);
            }
            String interfaceDescriptor = this.mOriginalClientBinder.getInterfaceDescriptor();
            if (interfaceDescriptor.equals("android.app.IActivityManager")) {
                str = "android.app.ActivityManagerNative";
            } else {
                str = interfaceDescriptor + "$Stub";
            }
            IInterface iInterface = (IInterface) ShareReflectUtil.findMethod(Class.forName(str), "asInterface", (Class<?>[]) new Class[]{IBinder.class}).invoke(null, this.mOriginalClientBinder);
            return ServiceBinderInterceptor.createProxy(ServiceBinderInterceptor.getAllInterfacesThroughDeriveChain(iInterface.getClass()), new FakeInterfaceHandler(iInterface, (IBinder) obj, this.mBinderInvocationHandler));
        }
    }

    private static class FakeInterfaceHandler implements InvocationHandler {
        private final BinderInvocationHandler mBinderInvocationHandler;
        private final IBinder mOriginalClientBinder;
        private final IInterface mOriginalInterface;

        FakeInterfaceHandler(IInterface iInterface, IBinder iBinder, BinderInvocationHandler binderInvocationHandler) {
            this.mOriginalInterface = iInterface;
            this.mOriginalClientBinder = iBinder;
            this.mBinderInvocationHandler = binderInvocationHandler;
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            return "asBinder".equals(method.getName()) ? this.mOriginalClientBinder : this.mBinderInvocationHandler.invoke(this.mOriginalInterface, method, objArr);
        }
    }

    static {
        synchronized (ServiceBinderInterceptor.class) {
            if (sServiceManagerClazz == null) {
                try {
                    Class<?> cls = Class.forName("android.os.ServiceManager");
                    sServiceManagerClazz = cls;
                    sSCacheField = ShareReflectUtil.findField(cls, "sCache");
                    sGetServiceMethod = ShareReflectUtil.findMethod(sServiceManagerClazz, "getService", (Class<?>[]) new Class[]{String.class});
                } catch (Throwable th) {
                    Log.e("Tinker.SvcBndrIntrcptr", "unexpected exception.", th);
                }
            }
        }
    }

    public ServiceBinderInterceptor(Context context, String str, BinderInvocationHandler binderInvocationHandler) {
        while (context != null && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        this.mBaseContext = context;
        this.mServiceName = str;
        this.mBinderInvocationHandler = binderInvocationHandler;
    }

    private static <T> T createProxy(Class<?>[] clsArr, InvocationHandler invocationHandler) {
        int length = clsArr.length + 1;
        Class[] clsArr2 = new Class[length];
        System.arraycopy(clsArr, 0, clsArr2, 0, clsArr.length);
        clsArr2[clsArr.length] = Interceptor.ITinkerHotplugProxy.class;
        try {
            return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), clsArr2, invocationHandler);
        } catch (Throwable th) {
            final HashSet hashSet = new HashSet(4);
            for (int i = 0; i < length; i++) {
                hashSet.add(clsArr2[i].getClassLoader());
            }
            ClassLoader classLoader = hashSet.size() == 1 ? (ClassLoader) hashSet.iterator().next() : new ClassLoader() {
                @Override
                protected Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
                    Iterator it = hashSet.iterator();
                    Class<?> clsLoadClass = null;
                    while (it.hasNext()) {
                        try {
                            clsLoadClass = ((ClassLoader) it.next()).loadClass(str);
                        } catch (Throwable unused) {
                        }
                        if (clsLoadClass != null) {
                            return clsLoadClass;
                        }
                    }
                    throw new ClassNotFoundException("cannot find class: " + str);
                }
            };
            return (T) Proxy.newProxyInstance(classLoader, clsArr2, invocationHandler);
        }
    }

    private static void fixAMSBinderCache(IBinder iBinder) throws Throwable {
        Object obj;
        try {
            obj = ShareReflectUtil.findField(Class.forName("android.app.ActivityManagerNative"), "gDefault").get(null);
        } catch (Throwable unused) {
            obj = ShareReflectUtil.findField(Class.forName("android.app.ActivityManager"), "IActivityManagerSingleton").get(null);
        }
        Field fieldFindField = ShareReflectUtil.findField(obj, "mInstance");
        IInterface iInterface = (IInterface) fieldFindField.get(obj);
        if (iInterface == null || Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
            return;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
        if (iInterfaceQueryLocalInterface != null && Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterfaceQueryLocalInterface.getClass())) {
            fieldFindField.set(obj, iInterfaceQueryLocalInterface);
            return;
        }
        throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + iInterfaceQueryLocalInterface);
    }

    private static void fixPMSBinderCache(Context context, IBinder iBinder) throws Throwable {
        Field fieldFindField = ShareReflectUtil.findField(Class.forName("android.app.ActivityThread"), "sPackageManager");
        IInterface iInterface = (IInterface) fieldFindField.get(null);
        if (iInterface != null && !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterface.getClass())) {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
            if (iInterfaceQueryLocalInterface == null || !Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterfaceQueryLocalInterface.getClass())) {
                throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + iInterfaceQueryLocalInterface);
            }
            fieldFindField.set(null, iInterfaceQueryLocalInterface);
        }
        Field fieldFindField2 = ShareReflectUtil.findField(Class.forName("android.app.ApplicationPackageManager"), "mPM");
        PackageManager packageManager = context.getPackageManager();
        IInterface iInterface2 = (IInterface) fieldFindField2.get(packageManager);
        if (iInterface2 == null || Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterface2.getClass())) {
            return;
        }
        IInterface iInterfaceQueryLocalInterface2 = iBinder.queryLocalInterface(iBinder.getInterfaceDescriptor());
        if (iInterfaceQueryLocalInterface2 != null && Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iInterfaceQueryLocalInterface2.getClass())) {
            fieldFindField2.set(packageManager, iInterfaceQueryLocalInterface2);
            return;
        }
        throw new IllegalStateException("fakeBinder does not return fakeInterface, binder: " + iBinder + ", itf: " + iInterfaceQueryLocalInterface2);
    }

    private static Class<?>[] getAllInterfacesThroughDeriveChain(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        HashSet hashSet = new HashSet(10);
        while (!Object.class.equals(cls)) {
            hashSet.addAll(Arrays.asList(cls.getInterfaces()));
            cls = cls.getSuperclass();
        }
        return (Class[]) hashSet.toArray(new Class[hashSet.size()]);
    }

    @Override
    protected IBinder decorate(IBinder iBinder) throws Throwable {
        if (iBinder != null) {
            return Interceptor.ITinkerHotplugProxy.class.isAssignableFrom(iBinder.getClass()) ? iBinder : (IBinder) createProxy(getAllInterfacesThroughDeriveChain(iBinder.getClass()), new FakeClientBinderHandler(iBinder, this.mBinderInvocationHandler));
        }
        throw new IllegalStateException("target is null.");
    }

    @Override
    protected IBinder fetchTarget() throws Throwable {
        return (IBinder) sGetServiceMethod.invoke(null, this.mServiceName);
    }

    @Override
    protected void inject(IBinder iBinder) throws Throwable {
        ((Map) sSCacheField.get(null)).put(this.mServiceName, iBinder);
        if ("activity".equals(this.mServiceName)) {
            fixAMSBinderCache(iBinder);
        } else if ("package".equals(this.mServiceName)) {
            fixPMSBinderCache(this.mBaseContext, iBinder);
        }
    }
}
