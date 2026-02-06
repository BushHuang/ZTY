package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.Serializable;

public class DelegateFileFilter extends AbstractFileFilter implements Serializable {
    private final FileFilter fileFilter;
    private final FilenameFilter filenameFilter;

    public DelegateFileFilter(FileFilter fileFilter) {
        if (fileFilter == null) {
            throw new IllegalArgumentException("The FileFilter must not be null");
        }
        this.fileFilter = fileFilter;
        this.filenameFilter = null;
    }

    public DelegateFileFilter(FilenameFilter filenameFilter) {
        if (filenameFilter == null) {
            throw new IllegalArgumentException("The FilenameFilter must not be null");
        }
        this.filenameFilter = filenameFilter;
        this.fileFilter = null;
    }

    @Override
    public boolean accept(File file) {
        FileFilter fileFilter = this.fileFilter;
        return fileFilter != null ? fileFilter.accept(file) : super.accept(file);
    }

    @Override
    public boolean accept(File file, String str) {
        FilenameFilter filenameFilter = this.filenameFilter;
        return filenameFilter != null ? filenameFilter.accept(file, str) : super.accept(file, str);
    }

    @Override
    public String toString() {
        Object obj = this.fileFilter;
        if (obj == null) {
            obj = this.filenameFilter;
        }
        return super.toString() + "(" + obj.toString() + ")";
    }
}
