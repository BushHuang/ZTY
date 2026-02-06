package androidx.core.net;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\u0086\b¨\u0006\u0005"}, d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class UriKt {
    public static final File toFile(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "$this$toFile");
        if (!Intrinsics.areEqual(uri.getScheme(), "file")) {
            throw new IllegalArgumentException(("Uri lacks 'file' scheme: " + uri).toString());
        }
        String path = uri.getPath();
        if (path != null) {
            return new File(path);
        }
        throw new IllegalArgumentException(("Uri path is null: " + uri).toString());
    }

    public static final Uri toUri(File file) {
        Intrinsics.checkParameterIsNotNull(file, "$this$toUri");
        Uri uriFromFile = Uri.fromFile(file);
        Intrinsics.checkExpressionValueIsNotNull(uriFromFile, "Uri.fromFile(this)");
        return uriFromFile;
    }

    public static final Uri toUri(String str) {
        Intrinsics.checkParameterIsNotNull(str, "$this$toUri");
        Uri uri = Uri.parse(str);
        Intrinsics.checkExpressionValueIsNotNull(uri, "Uri.parse(this)");
        return uri;
    }
}
