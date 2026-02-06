package com.zaze.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    public static <E> void sortList(List<E> list, final String str, final String str2) {
        Collections.sort(list, new Comparator<E>() {
            @Override
            public int compare(Object obj, Object obj2) {
                try {
                    String string = obj.getClass().getMethod(str, new Class[0]).invoke(obj, new Object[0]).toString();
                    String string2 = obj2.getClass().getMethod(str, new Class[0]).invoke(obj2, new Object[0]).toString();
                    return "desc".equalsIgnoreCase(str2) ? string2.compareTo(string) : string.compareTo(string2);
                } catch (Throwable th) {
                    th.printStackTrace();
                    return 0;
                }
            }
        });
    }
}
