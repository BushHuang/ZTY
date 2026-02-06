package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.util.ArrayUtil;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import java.io.File;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppBury {
    private static AppBury instance;
    private final CopyOnWriteArrayList<BuryEvent> buryEvents = new CopyOnWriteArrayList<>();

    private AppBury() {
    }

    private void dumpBuryEventsToFile(File file) throws Throwable {
        if (XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")) {
            StringBuilder sb = new StringBuilder();
            Iterator<BuryEvent> it = this.buryEvents.iterator();
            while (it.hasNext()) {
                sb.append(JsonUtil.toJsonString(it.next()));
                sb.append("\r\n");
            }
            XHFileUtil.writeFileFromString(file, sb.toString(), true);
        }
    }

    private int getBuryEventsSize() {
        return this.buryEvents.size();
    }

    public static AppBury getInstance() {
        if (instance == null) {
            synchronized (AppBury.class) {
                if (instance == null) {
                    instance = new AppBury();
                }
            }
        }
        return instance;
    }

    private static boolean renameBuryFile(File file) {
        File file2 = new File(file.getAbsolutePath() + ".1");
        if (file2.exists()) {
            file2.delete();
        }
        return file.renameTo(file2);
    }

    public void addEvent(BuryEvent buryEvent) {
        ArrayUtil.overflowCheck(this.buryEvents, 10000, 500);
        this.buryEvents.add(buryEvent);
    }

    public void appendBuryEventToFile(File file) {
        if (!XHFileUtil.isFileExistsAndCanRead(file) || file.length() <= ConstStatistics.INSTANCE.getMAX_BURY_FILE_SIZE()) {
            dumpBuryEventsToFile(file);
        } else {
            renameBuryFile(file);
        }
    }

    public void clearBuryEvents() {
        this.buryEvents.clear();
    }

    public String getBuryEventNames() {
        StringBuilder sb = new StringBuilder();
        Iterator<BuryEvent> it = this.buryEvents.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            sb.append(";");
        }
        return sb.toString();
    }

    public boolean isBuryEventsEmpty() {
        return this.buryEvents.isEmpty();
    }
}
