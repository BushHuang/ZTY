package com.xh.xhcore.common.http;

import android.util.Base64;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.xh.xhcore.common.util.JsonUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;
import org.json.JSONObject;

public class XhJsonParse {
    private String D;
    private String I;
    private String M;
    private int R;
    private int Z;

    public static String gzipCompressString(String str) throws IOException {
        if (str.length() <= 0) {
            return "";
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            DeflaterInputStream deflaterInputStream = new DeflaterInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = deflaterInputStream.read(bArr);
                if (i == -1) {
                    byte[] bArrEncode = Base64.encode(byteArrayOutputStream.toByteArray(), 0);
                    byteArrayOutputStream.close();
                    deflaterInputStream.close();
                    byteArrayInputStream.close();
                    return new String(bArrEncode, "utf-8");
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Exception e) {
            throw new IllegalStateException("GZIP compression failed: " + e, e);
        }
    }

    public static String gzipUnCompressString(String str) throws IOException {
        if (str.length() <= 0) {
            return "";
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 0));
            InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inflaterInputStream.read(bArr);
                if (i == -1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    inflaterInputStream.close();
                    byteArrayInputStream.close();
                    return new String(byteArray, "utf-8");
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (Exception e) {
            throw new IllegalStateException("GZIP compression failed: " + e, e);
        }
    }

    public String getD() {
        return this.D;
    }

    public String getI() {
        return this.I;
    }

    public String getM() {
        return this.M;
    }

    public int getR() {
        return this.R;
    }

    public int getZ() {
        return this.Z;
    }

    @Deprecated
    public void parseData(String str) throws JsonSyntaxException, NullPointerException {
        JsonParser jsonParser = new JsonParser();
        JsonObject asJsonObject = jsonParser.parse(jsonParser.parse(str).getAsJsonObject().get("httpResponse").getAsString()).getAsJsonObject();
        if (!asJsonObject.has("D") || !asJsonObject.has("I") || !asJsonObject.has("M") || !asJsonObject.has("R") || !asJsonObject.has("Z")) {
            throw new NullPointerException("Response基础参数不全");
        }
        this.D = asJsonObject.get("D").toString();
        this.I = asJsonObject.get("I").getAsString();
        this.M = asJsonObject.get("M").getAsString();
        this.R = asJsonObject.get("R").getAsInt();
        if (asJsonObject.get("Z").getAsInt() == 1) {
            this.Z = asJsonObject.get("Z").getAsInt();
        } else {
            this.Z = asJsonObject.get("Z").getAsInt();
        }
    }

    public void parseDataDefault(String str) {
        JsonParser jsonParser = new JsonParser();
        JsonObject asJsonObject = jsonParser.parse(jsonParser.parse(str).getAsJsonObject().get("httpResponse").getAsString()).getAsJsonObject();
        if (asJsonObject.has("Z")) {
            this.Z = asJsonObject.get("Z").getAsInt();
        } else {
            this.Z = 0;
        }
        if (this.Z == 1) {
            if (asJsonObject.has("D")) {
                this.D = gzipUnCompressString(asJsonObject.get("D").toString());
            } else {
                this.D = "";
            }
        } else if (asJsonObject.has("D")) {
            this.D = asJsonObject.get("D").toString();
        } else {
            this.D = "";
        }
        if (asJsonObject.has("I")) {
            this.I = asJsonObject.get("I").getAsString();
        } else {
            this.I = "";
        }
        if (asJsonObject.has("M")) {
            this.M = asJsonObject.get("M").getAsString();
        } else {
            this.M = "";
        }
        if (asJsonObject.has("R")) {
            this.R = asJsonObject.get("R").getAsInt();
        } else {
            this.R = 0;
        }
    }

    public void parseDataWithKZ(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("httpResponse")) {
                jSONObject = (JSONObject) jSONObject.opt("httpResponse");
            }
            this.Z = jSONObject.optInt("Z");
            this.D = jSONObject.opt("D") != null ? jSONObject.opt("D").toString() : "";
            this.I = jSONObject.optString("I");
            this.M = jSONObject.optString("M");
            this.R = jSONObject.optInt("R");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void parseDataWithZTKT(String str) throws JsonSyntaxException, NullPointerException {
        JsonParser jsonParser = new JsonParser();
        JsonObject asJsonObject = jsonParser.parse(jsonParser.parse(str).getAsJsonObject().get("httpResponse").getAsString()).getAsJsonObject();
        if (!asJsonObject.has("D") || !asJsonObject.has("I") || !asJsonObject.has("M") || !asJsonObject.has("R") || !asJsonObject.has("Z")) {
            throw new NullPointerException("Response基础参数不全");
        }
        this.D = asJsonObject.get("D").getAsString();
        this.I = asJsonObject.get("I").getAsString();
        this.M = asJsonObject.get("M").getAsString();
        this.R = asJsonObject.get("R").getAsInt();
        if (asJsonObject.get("Z").getAsInt() == 1) {
            this.Z = asJsonObject.get("Z").getAsInt();
        } else {
            this.Z = asJsonObject.get("Z").getAsInt();
        }
    }

    public <T> T toObject(Class<T> cls) throws JsonSyntaxException {
        return (T) JsonUtil.parseObject(getD(), (Class) cls);
    }
}
