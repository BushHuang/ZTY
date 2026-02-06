package com.tencent.matrix.trace.items;

public class MethodItem {
    public int count = 1;
    public int depth;
    public int durTime;
    public int methodId;

    public MethodItem(int i, int i2, int i3) {
        this.methodId = i;
        this.durTime = i2;
        this.depth = i3;
    }

    public void mergeMore(long j) {
        this.count++;
        this.durTime = (int) (this.durTime + j);
    }

    public String print() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.depth; i++) {
            stringBuffer.append('.');
        }
        return stringBuffer.toString() + this.methodId + " " + this.count + " " + this.durTime;
    }

    public String toString() {
        return this.depth + "," + this.methodId + "," + this.count + "," + this.durTime;
    }
}
