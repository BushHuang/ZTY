package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class NotFileFilter extends AbstractFileFilter implements Serializable {
    private final IOFileFilter filter;

    public NotFileFilter(IOFileFilter iOFileFilter) {
        if (iOFileFilter == null) {
            throw new IllegalArgumentException("The filter must not be null");
        }
        this.filter = iOFileFilter;
    }

    @Override
    public boolean accept(File file) {
        return !this.filter.accept(file);
    }

    @Override
    public boolean accept(File file, String str) {
        return !this.filter.accept(file, str);
    }

    @Override
    public String toString() {
        return super.toString() + "(" + this.filter.toString() + ")";
    }
}
