package com.tencent.tinker.loader;

import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public final class TinkerClassLoader extends BaseDexClassLoader {
    private final ClassLoader mOriginAppClassLoader;

    class CompoundEnumeration<E> implements Enumeration<E> {
        private Enumeration<E>[] enums;
        private int index = 0;

        public CompoundEnumeration(Enumeration<E>[] enumerationArr) {
            this.enums = enumerationArr;
        }

        @Override
        public boolean hasMoreElements() {
            while (true) {
                int i = this.index;
                Enumeration<E>[] enumerationArr = this.enums;
                if (i >= enumerationArr.length) {
                    return false;
                }
                if (enumerationArr[i] != null && enumerationArr[i].hasMoreElements()) {
                    return true;
                }
                this.index++;
            }
        }

        @Override
        public E nextElement() {
            if (hasMoreElements()) {
                return this.enums[this.index].nextElement();
            }
            throw new NoSuchElementException();
        }
    }

    TinkerClassLoader(String str, File file, String str2, ClassLoader classLoader) {
        super(str, file, str2, ClassLoader.getSystemClassLoader());
        this.mOriginAppClassLoader = classLoader;
    }

    @Override
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        Class<?> clsFindClass;
        try {
            clsFindClass = super.findClass(str);
        } catch (ClassNotFoundException unused) {
            clsFindClass = null;
        }
        return clsFindClass != null ? clsFindClass : this.mOriginAppClassLoader.loadClass(str);
    }

    @Override
    public URL getResource(String str) {
        URL resource = Object.class.getClassLoader().getResource(str);
        if (resource != null) {
            return resource;
        }
        URL urlFindResource = findResource(str);
        return urlFindResource != null ? urlFindResource : this.mOriginAppClassLoader.getResource(str);
    }

    @Override
    public Enumeration<URL> getResources(String str) throws IOException {
        return new CompoundEnumeration(new Enumeration[]{Object.class.getClassLoader().getResources(str), findResources(str), this.mOriginAppClassLoader.getResources(str)});
    }
}
