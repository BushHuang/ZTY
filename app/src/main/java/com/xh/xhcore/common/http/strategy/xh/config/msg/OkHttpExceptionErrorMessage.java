package com.xh.xhcore.common.http.strategy.xh.config.msg;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0007H\u0016R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/config/msg/OkHttpExceptionErrorMessage;", "Lorg/json/JSONObject;", "Lcom/xh/xhcore/common/http/strategy/xh/config/msg/INetworkErrorMessage;", "()V", "exceptionHappenTimesMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "lastKey", "getLastKey", "()Ljava/lang/String;", "setLastKey", "(Ljava/lang/String;)V", "putOpt", "name", "value", "", "toString", "indentSpaces", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OkHttpExceptionErrorMessage extends JSONObject implements INetworkErrorMessage {
    private HashMap<String, Integer> exceptionHappenTimesMap = new HashMap<>();
    private String lastKey;

    public final String getLastKey() {
        return this.lastKey;
    }

    @Override
    public JSONObject putOpt(String name, Object value) throws JSONException {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.lastKey = name;
        Integer num = this.exceptionHappenTimesMap.get(name);
        int iIntValue = num != null ? Integer.valueOf(num.intValue() + 1).intValue() : 1;
        this.exceptionHappenTimesMap.put(name, Integer.valueOf(iIntValue));
        JSONObject jSONObjectPutOpt = super.putOpt(name, "happen times: " + iIntValue + '\n' + value);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPutOpt, "super.putOpt(name, \"happ…: ${newTimes}\\n${value}\")");
        return jSONObjectPutOpt;
    }

    public final void setLastKey(String str) {
        this.lastKey = str;
    }

    @Override
    public String toString(int indentSpaces) throws JSONException {
        String str = this.lastKey;
        if (!(str == null || str.length() == 0)) {
            remove(this.lastKey);
        }
        if (length() == 0) {
            return "";
        }
        String string = super.toString(indentSpaces);
        Intrinsics.checkNotNullExpressionValue(string, "super.toString(indentSpaces)");
        return string;
    }
}
