package com.xh.xhcore.common.matrix.issue;

import com.tencent.matrix.report.Issue;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/matrix/issue/IssueConverter;", "", "()V", "convert", "Lorg/json/JSONObject;", "issue", "Lcom/tencent/matrix/report/Issue;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IssueConverter {
    public static final IssueConverter INSTANCE = new IssueConverter();

    private IssueConverter() {
    }

    @JvmStatic
    public static final JSONObject convert(Issue issue) throws JSONException {
        Intrinsics.checkNotNullParameter(issue, "issue");
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObjectPutOpt = jSONObject.putOpt("APMType", Integer.valueOf(issue.getType())).putOpt("APMTag", issue.getTag()).putOpt("APMKey", issue.getKey()).putOpt("APMContent", issue.getContent());
            Intrinsics.checkNotNullExpressionValue(jSONObjectPutOpt, "{\n            result.put… issue.content)\n        }");
            return jSONObjectPutOpt;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jSONObjectPutOpt2 = jSONObject.putOpt("IssueConverterException", e.getMessage());
            Intrinsics.checkNotNullExpressionValue(jSONObjectPutOpt2, "{\n            e.printSta…on\", e.message)\n        }");
            return jSONObjectPutOpt2;
        }
    }
}
