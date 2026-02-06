package com.xh.xhcore.common.statistic;

import android.os.FileObserver;
import java.io.File;

public class BuryFileListener extends FileObserver {
    String filePath;
    BurySaveListener saveListener;

    public interface BurySaveListener {
        void saveFinish();
    }

    public BuryFileListener(String str) {
        super(str);
        this.filePath = str;
    }

    @Override
    public void onEvent(int i, String str) {
        BurySaveListener burySaveListener;
        if ((i & 4095) == 8 && new File(this.filePath).exists() && (burySaveListener = this.saveListener) != null) {
            burySaveListener.saveFinish();
        }
    }

    public void setBurySaveListener(BurySaveListener burySaveListener) {
        this.saveListener = burySaveListener;
    }
}
