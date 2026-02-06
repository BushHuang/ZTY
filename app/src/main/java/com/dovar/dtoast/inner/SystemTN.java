package com.dovar.dtoast.inner;

import android.os.Handler;
import android.os.Message;
import java.util.LinkedList;

class SystemTN extends Handler {
    static final int REMOVE = 2;
    private final LinkedList<SystemToast> toastQueue;

    private static class SingletonHolder {
        private static final SystemTN mTn = new SystemTN();

        private SingletonHolder() {
        }
    }

    private SystemTN() {
        this.toastQueue = new LinkedList<>();
    }

    private void displayToast(SystemToast systemToast) {
        systemToast.showInternal();
        sendRemoveMsgDelay(systemToast);
    }

    static SystemTN instance() {
        return SingletonHolder.mTn;
    }

    private boolean isShowing() {
        return this.toastQueue.size() > 0;
    }

    private void notifyNewToastComeIn(SystemToast systemToast) {
        boolean zIsShowing = isShowing();
        this.toastQueue.add(systemToast);
        if (!zIsShowing) {
            showNextToast();
        } else if (this.toastQueue.size() == 2) {
            SystemToast systemToastPeek = this.toastQueue.peek();
            if (systemToast.getPriority() >= systemToastPeek.getPriority()) {
                sendRemoveMsg(systemToastPeek);
            }
        }
    }

    private void remove(SystemToast systemToast) {
        this.toastQueue.remove(systemToast);
        systemToast.cancelInternal();
        showNextToast();
    }

    private void sendRemoveMsg(SystemToast systemToast) {
        removeMessages(2);
        Message messageObtainMessage = obtainMessage(2);
        messageObtainMessage.obj = systemToast;
        sendMessage(messageObtainMessage);
    }

    private void sendRemoveMsgDelay(SystemToast systemToast) {
        removeMessages(2);
        Message messageObtainMessage = obtainMessage(2);
        messageObtainMessage.obj = systemToast;
        sendMessageDelayed(messageObtainMessage, systemToast.getDuration());
    }

    private void showNextToast() {
        if (this.toastQueue.isEmpty()) {
            return;
        }
        SystemToast systemToastPeek = this.toastQueue.peek();
        if (systemToastPeek == null) {
            this.toastQueue.poll();
            showNextToast();
        } else if (this.toastQueue.size() <= 1) {
            displayToast(systemToastPeek);
        } else if (this.toastQueue.get(1).getPriority() < systemToastPeek.getPriority()) {
            displayToast(systemToastPeek);
        } else {
            this.toastQueue.remove(systemToastPeek);
            showNextToast();
        }
    }

    void add(SystemToast systemToast) {
        SystemToast systemToastClone;
        if (systemToast == null || (systemToastClone = systemToast.m8clone()) == null) {
            return;
        }
        notifyNewToastComeIn(systemToastClone);
    }

    void cancelAll() {
        removeMessages(2);
        if (!this.toastQueue.isEmpty()) {
            this.toastQueue.peek().cancelInternal();
        }
        this.toastQueue.clear();
    }

    @Override
    public void handleMessage(Message message) {
        if (message != null && message.what == 2) {
            remove((SystemToast) message.obj);
        }
    }
}
