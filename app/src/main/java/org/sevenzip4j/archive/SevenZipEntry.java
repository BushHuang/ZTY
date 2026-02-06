package org.sevenzip4j.archive;

public class SevenZipEntry {
    private static final int FILE_ATTRIBUTE_ARCHIVE = 32;
    private static final int FILE_ATTRIBUTE_DIRECTORY = 16;
    private static final int FILE_ATTRIBUTE_HIDDEN = 2;
    private static final int FILE_ATTRIBUTE_READONLY = 1;
    private static final int FILE_ATTRIBUTE_SYSTEM = 4;
    private boolean archive;
    private boolean directory;
    private boolean hidden;
    private Long lastWriteTime;
    private String name;
    private boolean readonly;
    private long size;
    private boolean system;

    public Integer getAttributes() {
        return Integer.valueOf((this.readonly ? 1 : 0) | 0 | (this.hidden ? 2 : 0) | (this.system ? 4 : 0) | (this.directory ? 16 : 0) | (this.archive ? 32 : 0));
    }

    public Long getLastWriteTime() {
        return this.lastWriteTime;
    }

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public boolean isArchive() {
        return this.archive;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public boolean isReadonly() {
        return this.readonly;
    }

    public boolean isSystem() {
        return this.system;
    }

    public void setArchive(boolean z) {
        this.archive = z;
    }

    public void setDirectory(boolean z) {
        this.directory = z;
    }

    public void setHidden(boolean z) {
        this.hidden = z;
    }

    public void setLastWriteTime(Long l) {
        this.lastWriteTime = l;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setReadonly(boolean z) {
        this.readonly = z;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setSystem(boolean z) {
        this.system = z;
    }
}
