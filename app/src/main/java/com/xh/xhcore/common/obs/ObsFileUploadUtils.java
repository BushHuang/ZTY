package com.xh.xhcore.common.obs;

import android.text.TextUtils;
import android.util.Log;
import com.xh.xhcore.common.http.strategy.xh.upload.XHUploadUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\b\u0010\n\u001a\u00020\u0004H\u0002J\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0002J\u0016\u0010\f\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0002¨\u0006\r"}, d2 = {"Lcom/xh/xhcore/common/obs/ObsFileUploadUtils;", "", "()V", "getUploadFileFullPath", "", "appKey", "fileName", "aliyunExtraPath", "", "fileExtraPath", "getUploadFileName", "getUploadFilePath", "transformAliyunExtraPathToHeaderString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ObsFileUploadUtils {
    public static final ObsFileUploadUtils INSTANCE = new ObsFileUploadUtils();

    private ObsFileUploadUtils() {
    }

    public static String getUploadFileFullPath$default(ObsFileUploadUtils obsFileUploadUtils, String str, String str2, List list, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            list = new ArrayList();
        }
        return obsFileUploadUtils.getUploadFileFullPath(str, str2, list, str3);
    }

    private final String getUploadFileName() {
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
        return StringsKt.replace$default(string, "-", "", false, 4, (Object) null);
    }

    private final String getUploadFilePath(String appKey, List<String> aliyunExtraPath) {
        String strTransformAliyunExtraPathToHeaderString = transformAliyunExtraPathToHeaderString(aliyunExtraPath);
        if (TextUtils.isEmpty(strTransformAliyunExtraPathToHeaderString)) {
            return Intrinsics.stringPlus(appKey, File.separator);
        }
        return appKey + ((Object) File.separator) + strTransformAliyunExtraPathToHeaderString + ((Object) File.separator);
    }

    private final String transformAliyunExtraPathToHeaderString(List<String> aliyunExtraPath) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = aliyunExtraPath.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(File.separator);
        }
        if (!(sb.length() > 0)) {
            return "";
        }
        String string = sb.deleteCharAt(sb.length() - 1).toString();
        Intrinsics.checkNotNullExpressionValue(string, "{\n            extraPathS…- 1).toString()\n        }");
        return string;
    }

    public final String getUploadFileFullPath(String appKey, String fileName, List<String> aliyunExtraPath, String fileExtraPath) {
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(aliyunExtraPath, "aliyunExtraPath");
        String uploadFilePath = getUploadFilePath(appKey, aliyunExtraPath);
        String str = fileExtraPath;
        if (str == null || str.length() == 0) {
            fileExtraPath = "";
        } else {
            String str2 = File.separator;
            Intrinsics.checkNotNullExpressionValue(str2, "separator");
            if (!StringsKt.endsWith$default(fileExtraPath, str2, false, 2, (Object) null)) {
                fileExtraPath = Intrinsics.stringPlus(fileExtraPath, File.separator);
            }
        }
        String str3 = uploadFilePath + fileExtraPath + getUploadFileName() + '.' + ((Object) XHUploadUtil.getFileSuffixFromFilePath(fileName));
        Log.e("ObsFileUploadUtils", Intrinsics.stringPlus("fullPath=", str3));
        return str3;
    }
}
