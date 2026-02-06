package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;

public class e extends AsyncTask<Context, Integer, Boolean> {

    private static final String f426a = e.class.getSimpleName();

    @Override
    protected Boolean doInBackground(Context... contextArr) throws IOException {
        InputStream bksFromTss;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            bksFromTss = BksUtil.getBksFromTss(contextArr[0]);
        } catch (Exception e) {
            g.b(f426a, "doInBackground: exception : " + e.getMessage());
            bksFromTss = null;
        }
        g.a(f426a, "doInBackground: get bks from hms tss cost : " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        if (bksFromTss == null) {
            return false;
        }
        f.a(bksFromTss);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        if (bool.booleanValue()) {
            g.c(f426a, "onPostExecute: upate done");
        } else {
            g.b(f426a, "onPostExecute: upate failed");
        }
    }

    @Override
    protected void onProgressUpdate(Integer... numArr) {
        g.c(f426a, "onProgressUpdate");
    }

    @Override
    protected void onPreExecute() {
        g.a(f426a, "onPreExecute");
    }
}
