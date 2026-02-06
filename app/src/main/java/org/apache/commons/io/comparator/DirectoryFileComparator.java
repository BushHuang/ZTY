package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class DirectoryFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> DIRECTORY_COMPARATOR;
    public static final Comparator<File> DIRECTORY_REVERSE;

    static {
        DirectoryFileComparator directoryFileComparator = new DirectoryFileComparator();
        DIRECTORY_COMPARATOR = directoryFileComparator;
        DIRECTORY_REVERSE = new ReverseComparator(directoryFileComparator);
    }

    private int getType(File file) {
        return file.isDirectory() ? 1 : 2;
    }

    @Override
    public int compare(File file, File file2) {
        return getType(file) - getType(file2);
    }

    @Override
    public List sort(List list) {
        return super.sort((List<File>) list);
    }

    @Override
    public File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
