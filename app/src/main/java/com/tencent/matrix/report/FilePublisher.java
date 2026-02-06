package com.tencent.matrix.report;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.matrix.report.IssuePublisher;
import com.tencent.matrix.util.MatrixUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class FilePublisher extends IssuePublisher {
    private static final String TAG = "Matrix.FilePublisher";
    protected final Context context;
    private final SharedPreferences.Editor editor;
    private final long expiredTime;
    private final HashMap<String, Long> mPublishedMap;
    private final SharedPreferences sharedPreferences;

    public FilePublisher(Context context, long j, String str, IssuePublisher.OnIssueDetectListener onIssueDetectListener) {
        super(onIssueDetectListener);
        this.context = context;
        this.expiredTime = j;
        this.sharedPreferences = context.getSharedPreferences(str + MatrixUtil.getProcessName(context), 0);
        this.mPublishedMap = new HashMap<>();
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.editor = this.sharedPreferences.edit();
        Iterator it = new HashSet(this.sharedPreferences.getAll().keySet()).iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            long j2 = this.sharedPreferences.getLong(str2, 0L);
            long j3 = jCurrentTimeMillis - j2;
            if (j2 <= 0 || j3 > this.expiredTime) {
                this.editor.remove(str2);
            } else {
                this.mPublishedMap.put(str2, Long.valueOf(j2));
            }
        }
        this.editor.apply();
    }

    @Override
    public boolean isPublished(String str) {
        if (!this.mPublishedMap.containsKey(str)) {
            return false;
        }
        long jLongValue = this.mPublishedMap.get(str).longValue();
        if (jLongValue > 0 && System.currentTimeMillis() - jLongValue <= this.expiredTime) {
            return true;
        }
        this.editor.remove(str).apply();
        this.mPublishedMap.remove(str);
        return false;
    }

    @Override
    public void markPublished(String str) {
        if (str == null || this.mPublishedMap.containsKey(str)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mPublishedMap.put(str, Long.valueOf(jCurrentTimeMillis));
        this.editor.putLong(str, jCurrentTimeMillis).apply();
    }

    @Override
    public void unMarkPublished(String str) {
        if (str != null && this.mPublishedMap.containsKey(str)) {
            this.mPublishedMap.remove(str);
            this.editor.remove(str).apply();
        }
    }
}
