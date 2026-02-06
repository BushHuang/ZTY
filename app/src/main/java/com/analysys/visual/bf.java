package com.analysys.visual;

import com.analysys.visual.bl;

public abstract class bf extends bh {
    public bf(bl.a aVar) {
        super(aVar);
    }

    @Override
    public void a() throws ay {
        if (!e()) {
            throw new ay("Control frame cant have fin==false set");
        }
        if (b()) {
            throw new ay("Control frame cant have rsv1==true set");
        }
        if (c()) {
            throw new ay("Control frame cant have rsv2==true set");
        }
        if (d()) {
            throw new ay("Control frame cant have rsv3==true set");
        }
    }
}
