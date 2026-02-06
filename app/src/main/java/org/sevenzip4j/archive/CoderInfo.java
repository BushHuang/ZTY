package org.sevenzip4j.archive;

import org.sevenzip4j.archive.coder.Coder;

public class CoderInfo {
    private int numInStreams = 1;
    private int numOutStreams = 1;
    private Coder coder = null;

    public Coder getCoder() {
        return this.coder;
    }

    public int getNumInStreams() {
        return this.numInStreams;
    }

    public int getNumOutStreams() {
        return this.numOutStreams;
    }

    public void setCoder(Coder coder) {
        this.coder = coder;
    }

    public void setNumInStreams(int i) {
        this.numInStreams = i;
    }

    public void setNumOutStreams(int i) {
        this.numOutStreams = i;
    }
}
