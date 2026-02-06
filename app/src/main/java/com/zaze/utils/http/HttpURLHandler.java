package com.zaze.utils.http;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class HttpURLHandler extends URLStreamHandler {
    public static final String PROTOCOL = "http";
    private URLStreamHandler handler;
    private Class httpHandlerClass;

    public HttpURLHandler() {
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                this.httpHandlerClass = Class.forName("com.android.okhttp.HttpHandler");
            } else {
                this.httpHandlerClass = Class.forName("libcore.net.http.HttpHandler");
            }
            this.handler = (URLStreamHandler) this.httpHandlerClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    private URL dealURL(URL url) {
        Log.i("zaze", "URLConnection url : " + url.toString());
        Log.i("zaze", "URLConnection thread : " + Thread.currentThread().getName());
        return url;
    }

    @Override
    protected URLConnection openConnection(URL url) throws NoSuchMethodException, SecurityException, IOException {
        try {
            Method declaredMethod = this.handler.getClass().getDeclaredMethod("openConnection", URL.class);
            declaredMethod.setAccessible(true);
            return (URLConnection) declaredMethod.invoke(this.handler, dealURL(url));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected URLConnection openConnection(URL url, Proxy proxy) throws NoSuchMethodException, SecurityException, IOException {
        try {
            Method declaredMethod = this.handler.getClass().getDeclaredMethod("openConnection", URL.class, Proxy.class);
            declaredMethod.setAccessible(true);
            return (URLConnection) declaredMethod.invoke(this.handler, dealURL(url), proxy);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
