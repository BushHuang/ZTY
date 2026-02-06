package com.dovar.dtoast.inner;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.WindowManager;
import com.dovar.dtoast.DUtil;
import java.util.Comparator;
import java.util.Iterator;

class DovaTN extends Handler {
    private static final int REMOVE = 2;
    private final DPriorityQueue<DovaToast> toastQueue;

    private static class SingletonHolder {
        private static final DovaTN mTn = new DovaTN();

        private SingletonHolder() {
        }
    }

    private DovaTN() {
        this.toastQueue = new DPriorityQueue<>(new Comparator<DovaToast>() {
            @Override
            public int compare(DovaToast dovaToast, DovaToast dovaToast2) {
                if (dovaToast2.isShowing()) {
                    return 1;
                }
                if (dovaToast.getTimestamp() == dovaToast2.getTimestamp()) {
                    return 0;
                }
                return dovaToast.getTimestamp() < dovaToast2.getTimestamp() ? -1 : 1;
            }
        });
    }

    private void displayToast(DovaToast dovaToast) {
        WindowManager wMManager = dovaToast.getWMManager();
        if (wMManager == null) {
            return;
        }
        View viewInternal = dovaToast.getViewInternal();
        if (viewInternal == null) {
            this.toastQueue.remove(dovaToast);
            showNextToast();
            return;
        }
        ViewParent parent = viewInternal.getParent();
        if (parent instanceof ViewManager) {
            ((ViewManager) parent).removeView(viewInternal);
        }
        try {
            DUtil.log("displayToast: addView");
            wMManager.addView(viewInternal, dovaToast.getWMParams());
            dovaToast.isShowing = true;
            sendRemoveMsgDelay(dovaToast);
        } catch (Exception e) {
            if ((e instanceof WindowManager.BadTokenException) && e.getMessage() != null && (e.getMessage().contains("token null is not valid") || e.getMessage().contains("is your activity running"))) {
                if (dovaToast instanceof ActivityToast) {
                    DovaToast.Count4BadTokenException = 0L;
                } else {
                    DovaToast.Count4BadTokenException++;
                    if (dovaToast.getContext() instanceof Activity) {
                        this.toastQueue.remove(dovaToast);
                        removeMessages(2);
                        dovaToast.isShowing = false;
                        try {
                            wMManager.removeViewImmediate(viewInternal);
                        } catch (Exception unused) {
                            DUtil.log("windowManager removeViewImmediate error.Do not care this!");
                        }
                        new ActivityToast(dovaToast.getContext()).setTimestamp(dovaToast.getTimestamp()).setView(viewInternal).setDuration(dovaToast.getDuration()).setGravity(dovaToast.getGravity(), dovaToast.getXOffset(), dovaToast.getYOffset()).show();
                        return;
                    }
                }
            }
            e.printStackTrace();
        }
    }

    static DovaTN instance() {
        return SingletonHolder.mTn;
    }

    private boolean isShowing() {
        return this.toastQueue.size() > 0;
    }

    private void notifyNewToastComeIn(DovaToast dovaToast) {
        boolean zIsShowing = isShowing();
        if (dovaToast.getTimestamp() <= 0) {
            dovaToast.setTimestamp(System.currentTimeMillis());
        }
        this.toastQueue.add(dovaToast);
        if (!zIsShowing) {
            showNextToast();
        } else if (this.toastQueue.size() == 2) {
            DovaToast dovaToastPeek = this.toastQueue.peek();
            if (dovaToast.getPriority() >= dovaToastPeek.getPriority()) {
                sendRemoveMsg(dovaToastPeek);
            }
        }
    }

    private void remove(DovaToast dovaToast) {
        this.toastQueue.remove(dovaToast);
        removeInternal(dovaToast);
    }

    private void removeInternal(DovaToast dovaToast) {
        if (dovaToast == null || !dovaToast.isShowing()) {
            return;
        }
        WindowManager wMManager = dovaToast.getWMManager();
        if (wMManager != null) {
            try {
                DUtil.log("removeInternal: removeView");
                wMManager.removeViewImmediate(dovaToast.getViewInternal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dovaToast.isShowing = false;
    }

    private void sendRemoveMsg(DovaToast dovaToast) {
        removeMessages(2);
        Message messageObtainMessage = obtainMessage(2);
        messageObtainMessage.obj = dovaToast;
        sendMessage(messageObtainMessage);
    }

    private void sendRemoveMsgDelay(DovaToast dovaToast) {
        removeMessages(2);
        Message messageObtainMessage = obtainMessage(2);
        messageObtainMessage.obj = dovaToast;
        sendMessageDelayed(messageObtainMessage, dovaToast.getDuration());
    }

    private void showNextToast() {
        if (this.toastQueue.isEmpty()) {
            return;
        }
        DovaToast dovaToastPeek = this.toastQueue.peek();
        if (dovaToastPeek == null) {
            this.toastQueue.poll();
            showNextToast();
        } else if (this.toastQueue.size() <= 1) {
            displayToast(dovaToastPeek);
        } else if (this.toastQueue.get(1).getPriority() < dovaToastPeek.getPriority()) {
            displayToast(dovaToastPeek);
        } else {
            this.toastQueue.remove(dovaToastPeek);
            showNextToast();
        }
    }

    public void add(DovaToast dovaToast) {
        DovaToast dovaToastClone;
        if (dovaToast == null || (dovaToastClone = dovaToast.m7clone()) == null) {
            return;
        }
        notifyNewToastComeIn(dovaToastClone);
    }

    void cancelActivityToast(Activity activity) {
        if (activity == null) {
            return;
        }
        Iterator<DovaToast> it = this.toastQueue.iterator();
        while (it.hasNext()) {
            DovaToast next = it.next();
            if ((next instanceof ActivityToast) && next.getContext() == activity) {
                remove(next);
            }
        }
    }

    void cancelAll() {
        removeMessages(2);
        if (!this.toastQueue.isEmpty()) {
            removeInternal(this.toastQueue.peek());
        }
        this.toastQueue.clear();
    }

    @Override
    public void handleMessage(Message message) {
        if (message != null && message.what == 2) {
            remove((DovaToast) message.obj);
            showNextToast();
        }
    }
}
