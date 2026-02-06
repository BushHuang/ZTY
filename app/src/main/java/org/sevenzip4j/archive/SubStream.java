package org.sevenzip4j.archive;

public class SubStream implements Comparable<SubStream> {
    private SevenZipEntry sevenZipEntry;
    private long unPackSize;
    private int unpackCRC;

    @Override
    public int compareTo(SubStream subStream) {
        if (subStream == null) {
            throw new IllegalArgumentException("Unable to compare SubStream with null object!");
        }
        if (this.unPackSize > subStream.unPackSize) {
            return -1;
        }
        return (this.sevenZipEntry.isDirectory() || !subStream.sevenZipEntry.isDirectory()) ? 1 : -1;
    }

    public SevenZipEntry getSevenZipEntry() {
        return this.sevenZipEntry;
    }

    public long getUnPackSize() {
        return this.unPackSize;
    }

    public int getUnpackCRC() {
        return this.unpackCRC;
    }

    public void setSevenZipEntry(SevenZipEntry sevenZipEntry) {
        this.sevenZipEntry = sevenZipEntry;
    }

    public void setUnPackSize(long j) {
        this.unPackSize = j;
    }

    public void setUnpackCRC(int i) {
        this.unpackCRC = i;
    }
}
