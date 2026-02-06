package com.analysys.visual;

import com.analysys.visual.utils.VisualIpc;
import java.io.OutputStream;

public class aa implements ad {
    @Override
    public void a(Object obj, OutputStream outputStream) {
        VisualIpc.getInstance().onVisualEditEvent(obj.toString());
    }
}
