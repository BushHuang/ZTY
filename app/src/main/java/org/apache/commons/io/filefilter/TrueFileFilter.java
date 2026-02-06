package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class TrueFileFilter implements IOFileFilter, Serializable {
    public static final IOFileFilter INSTANCE;
    public static final IOFileFilter TRUE;

    static {
        TrueFileFilter trueFileFilter = new TrueFileFilter();
        TRUE = trueFileFilter;
        INSTANCE = trueFileFilter;
    }

    protected TrueFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return true;
    }

    @Override
    public boolean accept(File file, String str) {
        return true;
    }
}
