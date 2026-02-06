package com.obs.services.model;

public class AppendObjectRequest extends PutObjectRequest {
    protected long position = -1;

    public long getPosition() {
        return this.position;
    }

    public void setPosition(long j) {
        this.position = j;
    }
}
