package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class DefaultFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> DEFAULT_COMPARATOR;
    public static final Comparator<File> DEFAULT_REVERSE;

    static {
        DefaultFileComparator defaultFileComparator = new DefaultFileComparator();
        DEFAULT_COMPARATOR = defaultFileComparator;
        DEFAULT_REVERSE = new ReverseComparator(defaultFileComparator);
    }

    @Override
    public int compare(File file, File file2) {
        return file.compareTo(file2);
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
