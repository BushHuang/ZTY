package com.xh.xhcore.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
    public static <T> List<T> arrayToList(T[] tArr) {
        return Arrays.asList(tArr);
    }

    public static <T> void overflowCheck(List<T> list, int i, int i2) {
        if (list.size() > i) {
            list.removeAll(new ArrayList(list.subList(0, i2)));
        }
    }
}
