package com.xh.networkclient;

import com.xh.logutils.Log;
import com.xh.xhcore.common.util.XHLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class LinkSelector {
    private double pingTask(String str) throws IOException, NumberFormatException {
        Process processExec;
        XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " ip:" + str, new Object[0]);
        try {
            processExec = Runtime.getRuntime().exec("ping -c 1 " + str);
        } catch (IOException e) {
            e.printStackTrace();
            processExec = null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        double d = 0.0d;
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " line:" + line, new Object[0]);
                int iIndexOf = line.indexOf("time=");
                if (iIndexOf != -1) {
                    String strSubstring = line.substring(iIndexOf + 5);
                    d = Double.parseDouble(strSubstring.substring(0, strSubstring.indexOf(" ")));
                    if (!strSubstring.substring(strSubstring.indexOf(" ") + 1).startsWith("m")) {
                        d *= 1000.0d;
                    }
                    XHLog.v(Log.XH_NETWORK_CLIENT_TAG, " time:" + d, new Object[0]);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (processExec.waitFor() != 0) {
                System.err.println("exit value = " + processExec.exitValue());
                Log.e("netCheck", "\nproc.exitValue():" + processExec.exitValue());
            }
        } catch (InterruptedException e3) {
            System.err.println(e3);
        }
        return d;
    }

    public ArrayList<LinkResult> LinkSort(ArrayList<String> arrayList) {
        ArrayList<LinkResult> arrayList2 = new ArrayList<>();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList2.add(new LinkResult(next, pingTask(next)));
        }
        Collections.sort(arrayList2);
        return arrayList2;
    }
}
