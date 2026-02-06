package com.obs.services.internal.consensus;

import java.util.concurrent.locks.ReentrantLock;

public class SegmentLock {
    private static final int SEGMENT_NUM = 16;
    private ReentrantLock[] locks = new ReentrantLock[16];

    private static class SegmentLockHolder {
        private static SegmentLock instance = new SegmentLock();

        private SegmentLockHolder() {
        }
    }

    public SegmentLock() {
        for (int i = 0; i < 16; i++) {
            this.locks[i] = new ReentrantLock();
        }
    }

    public static SegmentLock getInstance() {
        return SegmentLockHolder.instance;
    }

    public void clear() {
        this.locks = null;
    }

    public void lock(String str) {
        this.locks[Math.abs(str.hashCode()) % 16].lock();
    }

    public void unlock(String str) {
        this.locks[Math.abs(str.hashCode()) % 16].unlock();
    }
}
