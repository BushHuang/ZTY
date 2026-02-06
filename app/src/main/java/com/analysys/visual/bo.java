package com.analysys.visual;

import com.analysys.visual.bl;

public class bo extends bg {
    public bo() {
        super(bl.a.TEXT);
    }

    @Override
    public void a() throws ax {
        super.a();
        if (!cc.b(g())) {
            throw new ax(1007, "Received text is no valid utf8 string!");
        }
    }
}
