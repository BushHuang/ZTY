package com.analysys.visual;

import com.analysys.ui.WindowUIHelper;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ExceptionUtil;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ag {

    private static final Class<?>[] f94a = new Class[0];
    private ae b;

    private af a(Class<?> cls, JSONObject jSONObject) throws JSONException, ClassNotFoundException {
        al alVar;
        String string = jSONObject.getString("name");
        if (jSONObject.has("get")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("get");
            alVar = new al(cls, jSONObject2.getString("selector"), f94a, Class.forName(jSONObject2.getJSONObject("result").getString("type")));
        } else {
            alVar = null;
        }
        return new af(string, cls, alVar);
    }

    private void a(OutputStream outputStream, boolean z) throws Throwable {
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.b == null) {
            ANSLog.e("analysys.visual", "error: snapshot is null");
            return;
        }
        ANSLog.i("analysys.visual", "Send ws command: snapshot_response");
        List<WindowUIHelper.PageRootInfo> listA = this.b.a();
        Object[] objArr = new Object[2];
        objArr[0] = "analysys.visual";
        StringBuilder sb = new StringBuilder();
        sb.append("snapshot root view ");
        if (listA == null) {
            str = "is null";
        } else {
            str = "size: " + listA.size();
        }
        sb.append(str);
        objArr[1] = sb.toString();
        ANSLog.i(objArr);
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(outputStream);
                if (z) {
                    outputStreamWriter2.write("{");
                    outputStreamWriter2.write("\"type\": \"snapshot_response\",");
                    outputStreamWriter2.write("\"payload\": {\"new_feature\":1,");
                    outputStreamWriter2.write("\"activities\":");
                    outputStreamWriter2.flush();
                    this.b.a(outputStream, listA);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    outputStreamWriter2.write(",\"snapshot_time_millis\": ");
                    outputStreamWriter2.write(Long.toString(jCurrentTimeMillis2));
                    outputStreamWriter2.write("}");
                    outputStreamWriter2.write("}");
                    ANSLog.i("analysys.visual", "snapshot send new page");
                    outputStreamWriter2.flush();
                    try {
                        outputStreamWriter2.close();
                    } catch (Exception e) {
                        ANSLog.e("analysys.visual", "close writer fail", e);
                    }
                } else {
                    if (listA != null) {
                        try {
                            if (listA.isEmpty() || !this.b.a(listA)) {
                            }
                            outputStreamWriter2.flush();
                            outputStreamWriter2.close();
                        } catch (Exception e2) {
                            e = e2;
                            outputStreamWriter = outputStreamWriter2;
                            ANSLog.e("analysys.visual", "send snapshot server fail", e);
                            if (outputStreamWriter != null) {
                                try {
                                    outputStreamWriter.close();
                                } catch (Exception e3) {
                                    ANSLog.e("analysys.visual", "close writer fail", e3);
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            outputStreamWriter = outputStreamWriter2;
                            if (outputStreamWriter != null) {
                                try {
                                    outputStreamWriter.close();
                                } catch (Exception e4) {
                                    ANSLog.e("analysys.visual", "close writer fail", e4);
                                }
                            }
                            throw th;
                        }
                    }
                    outputStreamWriter2.write("egMsgCode");
                    ANSLog.i("analysys.visual", "snapshot page no change");
                    outputStreamWriter2.flush();
                    outputStreamWriter2.close();
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void a(List<af> list, af afVar) {
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                i = -1;
                break;
            }
            af afVar2 = list.get(i);
            if (!afVar2.equals(afVar)) {
                i++;
            } else if (!afVar.b.isAssignableFrom(afVar2.b)) {
                return;
            }
        }
        if (i == -1) {
            list.add(afVar);
        } else {
            list.set(i, afVar);
        }
    }

    public ae a(JSONObject jSONObject) throws Exception {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONObject("config").getJSONArray("classes");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                Class<?> cls = Class.forName(jSONObject2.getString("name"));
                JSONArray jSONArray2 = jSONObject2.getJSONArray("properties");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    a(arrayList, a(cls, jSONArray2.getJSONObject(i2)));
                }
            }
            return new ae(arrayList);
        } catch (ClassNotFoundException e) {
            throw new Exception("Can't resolve types for snapshot configuration", e);
        } catch (JSONException e2) {
            throw new Exception("Can't read snapshot configuration", e2);
        }
    }

    public void a() {
        this.b = null;
    }

    public void a(String str) {
        if (this.b != null) {
            return;
        }
        try {
            this.b = a(new JSONObject(str));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public byte[] a(boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            a(byteArrayOutputStream, z);
            byteArray = byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            try {
                ExceptionUtil.exceptionThrow(th);
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return byteArray;
            } catch (Throwable th3) {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th3;
            }
        }
        byteArrayOutputStream.close();
        return byteArray;
    }
}
