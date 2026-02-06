package com.xh.xhcore.common.matrix.issue;

import com.tencent.matrix.report.Issue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseIssueUtil {
    private static HashMap<String, String> methodMap;

    public static String parseIssue(Issue issue, boolean z, InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        if (!z) {
            sb.append("tag");
            sb.append(" : ");
            sb.append(issue.getTag());
            sb.append("\n");
            sb.append("type");
            sb.append(" : ");
            sb.append(issue.getType());
            sb.append("\n");
            sb.append("key");
            sb.append(" : ");
            sb.append(issue.getKey());
            sb.append("\n");
        }
        sb.append("content :");
        sb.append("\n");
        return readMappingFile(inputStream) ? pauseJsonObj(sb, issue.getContent()).toString() : "readMappingFile failed";
    }

    public static String parseStack(String str, String str2) {
        try {
            return parseIssue(new Issue(new JSONObject().put("stack", new JSONArray(str))), true, new FileInputStream(str2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "parseStack error";
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "parseStack error";
        }
    }

    private static StringBuilder pauseJsonObj(StringBuilder sb, JSONObject jSONObject) throws JSONException, NumberFormatException {
        String string;
        String str;
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if ("stackKey".equals(next)) {
                String[] strArrSplit = jSONObject.optString(next).split("\\|");
                StringBuilder sb2 = new StringBuilder();
                for (String str2 : strArrSplit) {
                    sb2.append(str2);
                    sb2.append(": ");
                    sb2.append(methodMap.get(str2));
                    sb2.append("\n");
                }
                string = sb2.toString();
            } else if ("stack".equals(next)) {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(next);
                StringBuilder sb3 = new StringBuilder("[depth, method, count, durTime] \n");
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    String strOptString = jSONArrayOptJSONArray.optString(i);
                    if (strOptString.contains(",")) {
                        String[] strArrSplit2 = strOptString.split(",");
                        try {
                            str = strArrSplit2[1];
                        } catch (Exception unused) {
                        }
                        if (methodMap.containsKey(str)) {
                            strArrSplit2[1] = str + ":(" + methodMap.get(str) + ")";
                            String strTrim = strArrSplit2[0].trim();
                            int i2 = Integer.parseInt(strTrim);
                            for (int i3 = 0; i3 < i2; i3++) {
                                sb3.append(".");
                            }
                            sb3.append(strTrim);
                            sb3.append(",  ");
                            sb3.append(strArrSplit2[1]);
                            sb3.append(",  ");
                            sb3.append(strArrSplit2[2]);
                            sb3.append(",  ");
                            sb3.append(strArrSplit2[3]);
                            sb3.append("\n");
                        } else {
                            System.out.print("error!!!");
                        }
                    }
                }
                string = sb3.toString();
            } else if ("threadStack".equals(next)) {
                try {
                    string = jSONObject.optJSONArray(next).toString(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                    string = "";
                }
            } else {
                string = jSONObject.optString(next);
            }
            sb.append("\t");
            sb.append(next);
            sb.append(" : ");
            sb.append(string);
            sb.append("\n");
        }
        return sb;
    }

    private static boolean readMappingFile(InputStream inputStream) throws Throwable {
        HashMap<String, String> map = methodMap;
        if (map != null && !map.isEmpty()) {
            return true;
        }
        methodMap = new HashMap<>();
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2 = null;
        bufferedReader = null;
        bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        try {
                            String line = bufferedReader3.readLine();
                            if (line == null) {
                                break;
                            }
                            String[] strArrSplit = line.split(",");
                            ?? r0 = methodMap;
                            r0.put(strArrSplit[0], strArrSplit[2].replace('\n', ' '));
                            bufferedReader2 = r0;
                        } catch (IOException e) {
                            e = e;
                            bufferedReader = bufferedReader3;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            HashMap<String, String> map2 = methodMap;
                            if (map2 == null) {
                            }
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader3;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader3.close();
                    bufferedReader3.close();
                    bufferedReader = bufferedReader2;
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            HashMap<String, String> map22 = methodMap;
            return map22 == null && !map22.isEmpty();
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
