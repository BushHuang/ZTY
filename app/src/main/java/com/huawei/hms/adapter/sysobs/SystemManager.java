package com.huawei.hms.adapter.sysobs;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SystemManager {

    public static SystemManager f209a = new SystemManager();
    public static final Object b = new Object();
    public static SystemNotifier c = new a();

    public static class a implements SystemNotifier {

        public final List<SystemObserver> f210a = new ArrayList();

        @Override
        public void notifyNoticeObservers(int i) {
            synchronized (SystemManager.b) {
                Iterator<SystemObserver> it = this.f210a.iterator();
                while (it.hasNext()) {
                    if (it.next().onNoticeResult(i)) {
                        it.remove();
                    }
                }
            }
        }

        @Override
        public void notifyObservers(int i) {
            synchronized (SystemManager.b) {
                Iterator<SystemObserver> it = this.f210a.iterator();
                while (it.hasNext()) {
                    if (it.next().onUpdateResult(i)) {
                        it.remove();
                    }
                }
            }
        }

        @Override
        public void notifyObservers(Intent intent, String str) {
            synchronized (SystemManager.b) {
                Iterator<SystemObserver> it = this.f210a.iterator();
                while (it.hasNext()) {
                    if (it.next().onSolutionResult(intent, str)) {
                        it.remove();
                    }
                }
            }
        }

        @Override
        public void registerObserver(SystemObserver systemObserver) {
            if (systemObserver == null || this.f210a.contains(systemObserver)) {
                return;
            }
            synchronized (SystemManager.b) {
                this.f210a.add(systemObserver);
            }
        }

        @Override
        public void unRegisterObserver(SystemObserver systemObserver) {
            synchronized (SystemManager.b) {
                this.f210a.remove(systemObserver);
            }
        }
    }

    public static SystemManager getInstance() {
        return f209a;
    }

    public static SystemNotifier getSystemNotifier() {
        return c;
    }

    public void notifyNoticeResult(int i) {
        c.notifyNoticeObservers(i);
    }

    public void notifyResolutionResult(Intent intent, String str) {
        c.notifyObservers(intent, str);
    }

    public void notifyUpdateResult(int i) {
        c.notifyObservers(i);
    }
}
