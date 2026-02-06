package com.huawei.agconnect.core.a;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.core.Service;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class c {

    private final Map<Class<?>, Service> f178a = new HashMap();
    private final Map<Class<?>, Object> b = new HashMap();

    c(List<Service> list) {
        a(list);
    }

    private Object a(AGConnectInstance aGConnectInstance, Service service) throws SecurityException {
        StringBuilder sb;
        String localizedMessage;
        if (service.getInstance() != null) {
            return service.getInstance();
        }
        Class<?> type = service.getType();
        if (type == null) {
            return null;
        }
        try {
            Constructor constructorA = a(type, Context.class, AGConnectInstance.class);
            if (constructorA != null) {
                return constructorA.newInstance(aGConnectInstance.getContext(), aGConnectInstance);
            }
            Constructor constructorA2 = a(type, Context.class);
            return constructorA2 != null ? constructorA2.newInstance(aGConnectInstance.getContext()) : type.newInstance();
        } catch (IllegalAccessException e) {
            sb = new StringBuilder();
            sb.append("Instantiate service exception ");
            localizedMessage = e.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("ServiceRepository", sb.toString());
            return null;
        } catch (InstantiationException e2) {
            sb = new StringBuilder();
            sb.append("Instantiate service exception ");
            localizedMessage = e2.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("ServiceRepository", sb.toString());
            return null;
        } catch (InvocationTargetException e3) {
            sb = new StringBuilder();
            sb.append("Instantiate service exception ");
            localizedMessage = e3.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e("ServiceRepository", sb.toString());
            return null;
        }
    }

    private static Constructor a(Class cls, Class... clsArr) throws SecurityException {
        boolean z = false;
        for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == clsArr.length) {
                for (int i = 0; i < clsArr.length; i++) {
                    z = parameterTypes[i] == clsArr[i];
                }
                if (z) {
                    return constructor;
                }
            }
        }
        return null;
    }

    public <T> T a(AGConnectInstance aGConnectInstance, Class<?> cls) {
        T t;
        Service service = this.f178a.get(cls);
        if (service == null) {
            return null;
        }
        if (service.isSingleton() && (t = (T) this.b.get(cls)) != null) {
            return t;
        }
        T t2 = (T) a(aGConnectInstance, service);
        if (t2 != null && service.isSingleton()) {
            this.b.put(cls, t2);
        }
        return t2;
    }

    public void a(List<Service> list) {
        if (list == null) {
            return;
        }
        for (Service service : list) {
            this.f178a.put(service.getInterface(), service);
        }
    }
}
