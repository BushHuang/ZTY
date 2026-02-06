package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;

public class d extends AsyncTask<Context, Integer, Boolean> {

    private static final String f425a = d.class.getSimpleName();

    @Override
    protected Boolean doInBackground(Context... contextArr) throws IOException {
        InputStream bksFromTss;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            bksFromTss = BksUtil.getBksFromTss(contextArr[0]);
        } catch (Exception e) {
            g.b(f425a, "doInBackground: exception : " + e.getMessage());
            bksFromTss = null;
        }
        g.a(f425a, "doInBackground: get bks from hms tss cost : " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        if (bksFromTss == null) {
            return false;
        }
        f.a(bksFromTss);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            g.c(f425a, "onPostExecute: upate done");
        } else {
            g.b(f425a, "onPostExecute: upate failed");
        }
    }

    @Override
    protected void onProgressUpdate(Integer... numArr) {
        g.c(f425a, "onProgressUpdate");
    }

    @Override
    protected void onPreExecute() {
        g.a(f425a, "onPreExecute");
    }
}
