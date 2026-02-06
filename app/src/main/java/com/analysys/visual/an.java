package com.analysys.visual;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public abstract class an extends ao {

    private boolean f107a;
    private boolean b;
    private Timer c;
    private TimerTask d;
    private int e = 60;
    private boolean f = false;

    private void f() {
        g();
        this.c = new Timer("WebSocketTimer");
        TimerTask timerTask = new TimerTask() {
            private ArrayList<ap> b = new ArrayList<>();

            @Override
            public void run() {
                this.b.clear();
                try {
                    this.b.addAll(an.this.c());
                    long jCurrentTimeMillis = System.currentTimeMillis() - (an.this.e * 1500);
                    Iterator<ap> it = this.b.iterator();
                    while (it.hasNext()) {
                        ap next = it.next();
                        if (next instanceof aq) {
                            aq aqVar = (aq) next;
                            if (aqVar.h() < jCurrentTimeMillis) {
                                if (aq.b) {
                                    System.out.println("Closing connection due to no pong received: " + next.toString());
                                }
                                aqVar.b(1006, "The connection was closed because the other endpoint did not respond with a pong in time. For more information check: https://github.com/TooTallNate/Java-WebSocket/wiki/Lost-connection-detection");
                            } else if (aqVar.c()) {
                                aqVar.b();
                            } else if (aq.b) {
                                System.out.println("Trying to ping a non open connection: " + next.toString());
                            }
                        }
                    }
                } catch (Exception e) {
                    if (aq.b) {
                        System.out.println("Exception during connection lost ping: " + e.getMessage());
                    }
                }
                this.b.clear();
            }
        };
        this.d = timerTask;
        Timer timer = this.c;
        int i = this.e;
        timer.scheduleAtFixedRate(timerTask, i * 1000, i * 1000);
    }

    private void g() {
        Timer timer = this.c;
        if (timer != null) {
            timer.cancel();
            this.c = null;
        }
        TimerTask timerTask = this.d;
        if (timerTask != null) {
            timerTask.cancel();
            this.d = null;
        }
    }

    protected void a() {
        if (this.c == null && this.d == null) {
            return;
        }
        this.f = false;
        if (aq.b) {
            System.out.println("Connection lost timer stopped");
        }
        g();
    }

    public void a(boolean z) {
        this.f107a = z;
    }

    protected void b() {
        if (this.e <= 0) {
            if (aq.b) {
                System.out.println("Connection lost timer deactivated");
            }
        } else {
            if (aq.b) {
                System.out.println("Connection lost timer started");
            }
            this.f = true;
            f();
        }
    }

    public void b(boolean z) {
        this.b = z;
    }

    protected abstract Collection<ap> c();

    public boolean d() {
        return this.f107a;
    }

    public boolean e() {
        return this.b;
    }
}
