package com.tencent.tinker.android.utils;

public class SparseBoolArray implements Cloneable {
    private int[] mKeys;
    private int mSize;
    private boolean[] mValues;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final boolean[] EMPTY_BOOL_ARRAY = new boolean[0];

    public static class KeyNotFoundException extends Exception {
        public KeyNotFoundException() {
        }

        public KeyNotFoundException(String str) {
            super(str);
        }
    }

    public SparseBoolArray() {
        this(10);
    }

    public SparseBoolArray(int i) {
        if (i == 0) {
            this.mKeys = EMPTY_INT_ARRAY;
            this.mValues = EMPTY_BOOL_ARRAY;
        } else {
            this.mKeys = new int[i];
            this.mValues = new boolean[i];
        }
        this.mSize = 0;
    }

    private boolean[] appendElementIntoBoolArray(boolean[] zArr, int i, boolean z) {
        if (i <= zArr.length) {
            if (i + 1 > zArr.length) {
                boolean[] zArr2 = new boolean[growSize(i)];
                System.arraycopy(zArr, 0, zArr2, 0, i);
                zArr = zArr2;
            }
            zArr[i] = z;
            return zArr;
        }
        throw new IllegalArgumentException("Bad currentSize, originalSize: " + zArr.length + " currentSize: " + i);
    }

    private int[] appendElementIntoIntArray(int[] iArr, int i, int i2) {
        if (i <= iArr.length) {
            if (i + 1 > iArr.length) {
                int[] iArr2 = new int[growSize(i)];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                iArr = iArr2;
            }
            iArr[i] = i2;
            return iArr;
        }
        throw new IllegalArgumentException("Bad currentSize, originalSize: " + iArr.length + " currentSize: " + i);
    }

    private int binarySearch(int[] iArr, int i, int i2) {
        int i3 = i - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i4 = i5 + 1;
            } else {
                if (i6 <= i2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return i4 ^ (-1);
    }

    private static int growSize(int i) {
        if (i <= 4) {
            return 8;
        }
        return i + (i >> 1);
    }

    private boolean[] insertElementIntoBoolArray(boolean[] zArr, int i, int i2, boolean z) {
        if (i > zArr.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + zArr.length + " currentSize: " + i);
        }
        if (i + 1 <= zArr.length) {
            System.arraycopy(zArr, i2, zArr, i2 + 1, i - i2);
            zArr[i2] = z;
            return zArr;
        }
        boolean[] zArr2 = new boolean[growSize(i)];
        System.arraycopy(zArr, 0, zArr2, 0, i2);
        zArr2[i2] = z;
        System.arraycopy(zArr, i2, zArr2, i2 + 1, zArr.length - i2);
        return zArr2;
    }

    private int[] insertElementIntoIntArray(int[] iArr, int i, int i2, int i3) {
        if (i > iArr.length) {
            throw new IllegalArgumentException("Bad currentSize, originalSize: " + iArr.length + " currentSize: " + i);
        }
        if (i + 1 <= iArr.length) {
            System.arraycopy(iArr, i2, iArr, i2 + 1, i - i2);
            iArr[i2] = i3;
            return iArr;
        }
        int[] iArr2 = new int[growSize(i)];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        iArr2[i2] = i3;
        System.arraycopy(iArr, i2, iArr2, i2 + 1, iArr.length - i2);
        return iArr2;
    }

    public void append(int i, boolean z) {
        int i2 = this.mSize;
        if (i2 != 0 && i <= this.mKeys[i2 - 1]) {
            put(i, z);
            return;
        }
        this.mKeys = appendElementIntoIntArray(this.mKeys, this.mSize, i);
        this.mValues = appendElementIntoBoolArray(this.mValues, this.mSize, z);
        this.mSize++;
    }

    public void clear() {
        this.mSize = 0;
    }

    public SparseBoolArray m13clone() {
        SparseBoolArray sparseBoolArray;
        SparseBoolArray sparseBoolArray2 = null;
        try {
            sparseBoolArray = (SparseBoolArray) super.clone();
        } catch (CloneNotSupportedException unused) {
        }
        try {
            sparseBoolArray.mKeys = (int[]) this.mKeys.clone();
            sparseBoolArray.mValues = (boolean[]) this.mValues.clone();
            return sparseBoolArray;
        } catch (CloneNotSupportedException unused2) {
            sparseBoolArray2 = sparseBoolArray;
            return sparseBoolArray2;
        }
    }

    public boolean containsKey(int i) {
        return indexOfKey(i) >= 0;
    }

    public void delete(int i) {
        int iBinarySearch = binarySearch(this.mKeys, this.mSize, i);
        if (iBinarySearch >= 0) {
            removeAt(iBinarySearch);
        }
    }

    public boolean get(int i) throws KeyNotFoundException {
        int iBinarySearch = binarySearch(this.mKeys, this.mSize, i);
        if (iBinarySearch >= 0) {
            return this.mValues[iBinarySearch];
        }
        throw new KeyNotFoundException("" + i);
    }

    public int indexOfKey(int i) {
        return binarySearch(this.mKeys, this.mSize, i);
    }

    public int indexOfValue(boolean z) {
        for (int i = 0; i < this.mSize; i++) {
            if (this.mValues[i] == z) {
                return i;
            }
        }
        return -1;
    }

    public int keyAt(int i) {
        return this.mKeys[i];
    }

    public void put(int i, boolean z) {
        int iBinarySearch = binarySearch(this.mKeys, this.mSize, i);
        if (iBinarySearch >= 0) {
            this.mValues[iBinarySearch] = z;
            return;
        }
        int i2 = iBinarySearch ^ (-1);
        this.mKeys = insertElementIntoIntArray(this.mKeys, this.mSize, i2, i);
        this.mValues = insertElementIntoBoolArray(this.mValues, this.mSize, i2, z);
        this.mSize++;
    }

    public void removeAt(int i) {
        int[] iArr = this.mKeys;
        int i2 = i + 1;
        System.arraycopy(iArr, i2, iArr, i, this.mSize - i2);
        boolean[] zArr = this.mValues;
        System.arraycopy(zArr, i2, zArr, i, this.mSize - i2);
        this.mSize--;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.mSize * 28);
        sb.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i));
            sb.append('=');
            sb.append(valueAt(i));
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean valueAt(int i) {
        return this.mValues[i];
    }
}
