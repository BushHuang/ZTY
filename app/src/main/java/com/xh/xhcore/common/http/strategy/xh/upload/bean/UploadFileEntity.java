package com.xh.xhcore.common.http.strategy.xh.upload.bean;

import com.xh.view.base.ext.ListExtensionKt;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/bean/UploadFileEntity;", "", "localPath", "", "(Ljava/lang/String;)V", "getLocalPath", "()Ljava/lang/String;", "targetFloderExtraPath", "", "getTargetFloderExtraPath", "()Ljava/util/List;", "setTargetFloderExtraPath", "(Ljava/util/List;)V", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "transformUploadFileExtraPath", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UploadFileEntity {
    private final String localPath;
    private List<String> targetFloderExtraPath;

    public UploadFileEntity(String str) {
        Intrinsics.checkNotNullParameter(str, "localPath");
        this.localPath = str;
    }

    public static UploadFileEntity copy$default(UploadFileEntity uploadFileEntity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uploadFileEntity.localPath;
        }
        return uploadFileEntity.copy(str);
    }

    public final String getLocalPath() {
        return this.localPath;
    }

    public final UploadFileEntity copy(String localPath) {
        Intrinsics.checkNotNullParameter(localPath, "localPath");
        return new UploadFileEntity(localPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UploadFileEntity) && Intrinsics.areEqual(this.localPath, ((UploadFileEntity) other).localPath);
    }

    public final String getLocalPath() {
        return this.localPath;
    }

    public final List<String> getTargetFloderExtraPath() {
        return this.targetFloderExtraPath;
    }

    public int hashCode() {
        return this.localPath.hashCode();
    }

    public final void setTargetFloderExtraPath(List<String> list) {
        this.targetFloderExtraPath = list;
    }

    public String toString() {
        return "UploadFileEntity(localPath=" + this.localPath + ')';
    }

    public final String transformUploadFileExtraPath() {
        List<String> list = this.targetFloderExtraPath;
        if (list == null || list.isEmpty()) {
            return null;
        }
        List listTransformList = ListExtensionKt.transformList(list, new Function1<String, String>() {
            @Override
            public final String invoke(String str) {
                Intrinsics.checkNotNullParameter(str, "it");
                return UploadFileEntityKt.trimAllBlank(str);
            }
        });
        StringBuilder sb = new StringBuilder();
        String str = File.separator;
        Intrinsics.checkNotNullExpressionValue(str, "separator");
        return ((StringBuilder) CollectionsKt.joinTo$default(listTransformList, sb, str, null, null, 0, null, null, 124, null)).toString();
    }
}
