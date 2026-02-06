package org.sevenzip4j.archive;

public class Folder {
    private int unpackCRC;
    private CoderInfo[] coders = {new CoderInfo()};
    private boolean unpackCRCDefined = false;
    private long[] packStreamSizes = null;
    private long[] unpackSizes = null;

    public CoderInfo[] getCoders() {
        return this.coders;
    }

    public int getNumOutStreams() {
        int numOutStreams = 0;
        for (CoderInfo coderInfo : this.coders) {
            numOutStreams += coderInfo.getNumOutStreams();
        }
        return numOutStreams;
    }

    public long getPackStreamSize() {
        long j = 0;
        for (long j2 : this.packStreamSizes) {
            j += j2;
        }
        return j;
    }

    public long[] getPackStreamSizes() {
        return this.packStreamSizes;
    }

    public int getUnpackCRC() {
        return this.unpackCRC;
    }

    public long getUnpackSize() {
        long j = 0;
        for (long j2 : this.unpackSizes) {
            j += j2;
        }
        return j;
    }

    public long[] getUnpackSizes() {
        return this.unpackSizes;
    }

    public boolean isUnpackCRCDefined() {
        return this.unpackCRCDefined;
    }

    public void setCoders(CoderInfo[] coderInfoArr) {
        this.coders = coderInfoArr;
    }

    public void setPackStreamSizes(long[] jArr) {
        this.packStreamSizes = jArr;
    }

    public void setUnpackCRC(int i) {
        this.unpackCRC = i;
        this.unpackCRCDefined = true;
    }

    public void setUnpackSizes(long[] jArr) {
        this.unpackSizes = jArr;
    }
}
