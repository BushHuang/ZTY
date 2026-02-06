package androidx.activity.result.contract;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public final class ActivityResultContracts {

    public static class CaptureVideo extends ActivityResultContract<Uri, Boolean> {
        @Override
        public Intent createIntent(Context context, Uri uri) {
            return new Intent("android.media.action.VIDEO_CAPTURE").putExtra("output", uri);
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Boolean> getSynchronousResult(Context context, Uri uri) {
            return null;
        }

        @Override
        public final Boolean parseResult(int i, Intent intent) {
            return Boolean.valueOf(i == -1);
        }
    }

    public static class CreateDocument extends ActivityResultContract<String, Uri> {
        @Override
        public Intent createIntent(Context context, String str) {
            return new Intent("android.intent.action.CREATE_DOCUMENT").setType("*/*").putExtra("android.intent.extra.TITLE", str);
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Uri> getSynchronousResult(Context context, String str) {
            return null;
        }

        @Override
        public final Uri parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return intent.getData();
        }
    }

    public static class GetContent extends ActivityResultContract<String, Uri> {
        @Override
        public Intent createIntent(Context context, String str) {
            return new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(str);
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Uri> getSynchronousResult(Context context, String str) {
            return null;
        }

        @Override
        public final Uri parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return intent.getData();
        }
    }

    public static class GetMultipleContents extends ActivityResultContract<String, List<Uri>> {
        static List<Uri> getClipDataUris(Intent intent) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (intent.getData() != null) {
                linkedHashSet.add(intent.getData());
            }
            ClipData clipData = intent.getClipData();
            if (clipData == null && linkedHashSet.isEmpty()) {
                return Collections.emptyList();
            }
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri uri = clipData.getItemAt(i).getUri();
                    if (uri != null) {
                        linkedHashSet.add(uri);
                    }
                }
            }
            return new ArrayList(linkedHashSet);
        }

        @Override
        public Intent createIntent(Context context, String str) {
            return new Intent("android.intent.action.GET_CONTENT").addCategory("android.intent.category.OPENABLE").setType(str).putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }

        @Override
        public final ActivityResultContract.SynchronousResult<List<Uri>> getSynchronousResult(Context context, String str) {
            return null;
        }

        @Override
        public final List<Uri> parseResult(int i, Intent intent) {
            return (intent == null || i != -1) ? Collections.emptyList() : getClipDataUris(intent);
        }
    }

    public static class OpenDocument extends ActivityResultContract<String[], Uri> {
        @Override
        public Intent createIntent(Context context, String[] strArr) {
            return new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", strArr).setType("*/*");
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Uri> getSynchronousResult(Context context, String[] strArr) {
            return null;
        }

        @Override
        public final Uri parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return intent.getData();
        }
    }

    public static class OpenDocumentTree extends ActivityResultContract<Uri, Uri> {
        @Override
        public Intent createIntent(Context context, Uri uri) {
            Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            if (Build.VERSION.SDK_INT >= 26 && uri != null) {
                intent.putExtra("android.provider.extra.INITIAL_URI", uri);
            }
            return intent;
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Uri> getSynchronousResult(Context context, Uri uri) {
            return null;
        }

        @Override
        public final Uri parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return intent.getData();
        }
    }

    public static class OpenMultipleDocuments extends ActivityResultContract<String[], List<Uri>> {
        @Override
        public Intent createIntent(Context context, String[] strArr) {
            return new Intent("android.intent.action.OPEN_DOCUMENT").putExtra("android.intent.extra.MIME_TYPES", strArr).putExtra("android.intent.extra.ALLOW_MULTIPLE", true).setType("*/*");
        }

        @Override
        public final ActivityResultContract.SynchronousResult<List<Uri>> getSynchronousResult(Context context, String[] strArr) {
            return null;
        }

        @Override
        public final List<Uri> parseResult(int i, Intent intent) {
            return (i != -1 || intent == null) ? Collections.emptyList() : GetMultipleContents.getClipDataUris(intent);
        }
    }

    public static final class PickContact extends ActivityResultContract<Void, Uri> {
        @Override
        public Intent createIntent(Context context, Void r2) {
            return new Intent("android.intent.action.PICK").setType("vnd.android.cursor.dir/contact");
        }

        @Override
        public Uri parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return intent.getData();
        }
    }

    public static final class RequestMultiplePermissions extends ActivityResultContract<String[], Map<String, Boolean>> {
        public static final String ACTION_REQUEST_PERMISSIONS = "androidx.activity.result.contract.action.REQUEST_PERMISSIONS";
        public static final String EXTRA_PERMISSIONS = "androidx.activity.result.contract.extra.PERMISSIONS";
        public static final String EXTRA_PERMISSION_GRANT_RESULTS = "androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS";

        static Intent createIntent(String[] strArr) {
            return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr);
        }

        @Override
        public Intent createIntent(Context context, String[] strArr) {
            return createIntent(strArr);
        }

        @Override
        public ActivityResultContract.SynchronousResult<Map<String, Boolean>> getSynchronousResult(Context context, String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return new ActivityResultContract.SynchronousResult<>(Collections.emptyMap());
            }
            ArrayMap arrayMap = new ArrayMap();
            boolean z = true;
            for (String str : strArr) {
                boolean z2 = ContextCompat.checkSelfPermission(context, str) == 0;
                arrayMap.put(str, Boolean.valueOf(z2));
                if (!z2) {
                    z = false;
                }
            }
            if (z) {
                return new ActivityResultContract.SynchronousResult<>(arrayMap);
            }
            return null;
        }

        @Override
        public Map<String, Boolean> parseResult(int i, Intent intent) {
            if (i == -1 && intent != null) {
                String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                if (intArrayExtra == null || stringArrayExtra == null) {
                    return Collections.emptyMap();
                }
                HashMap map = new HashMap();
                int length = stringArrayExtra.length;
                for (int i2 = 0; i2 < length; i2++) {
                    map.put(stringArrayExtra[i2], Boolean.valueOf(intArrayExtra[i2] == 0));
                }
                return map;
            }
            return Collections.emptyMap();
        }
    }

    public static final class RequestPermission extends ActivityResultContract<String, Boolean> {
        @Override
        public Intent createIntent(Context context, String str) {
            return RequestMultiplePermissions.createIntent(new String[]{str});
        }

        @Override
        public ActivityResultContract.SynchronousResult<Boolean> getSynchronousResult(Context context, String str) {
            if (str == null) {
                return new ActivityResultContract.SynchronousResult<>(false);
            }
            if (ContextCompat.checkSelfPermission(context, str) == 0) {
                return new ActivityResultContract.SynchronousResult<>(true);
            }
            return null;
        }

        @Override
        public Boolean parseResult(int i, Intent intent) {
            int[] intArrayExtra;
            if (intent == null || i != -1 || (intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS")) == null || intArrayExtra.length == 0) {
                return false;
            }
            return Boolean.valueOf(intArrayExtra[0] == 0);
        }
    }

    public static final class StartActivityForResult extends ActivityResultContract<Intent, ActivityResult> {
        public static final String EXTRA_ACTIVITY_OPTIONS_BUNDLE = "androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE";

        @Override
        public Intent createIntent(Context context, Intent intent) {
            return intent;
        }

        @Override
        public ActivityResult parseResult(int i, Intent intent) {
            return new ActivityResult(i, intent);
        }
    }

    public static final class StartIntentSenderForResult extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        public static final String ACTION_INTENT_SENDER_REQUEST = "androidx.activity.result.contract.action.INTENT_SENDER_REQUEST";
        public static final String EXTRA_INTENT_SENDER_REQUEST = "androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST";
        public static final String EXTRA_SEND_INTENT_EXCEPTION = "androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION";

        @Override
        public Intent createIntent(Context context, IntentSenderRequest intentSenderRequest) {
            return new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
        }

        @Override
        public ActivityResult parseResult(int i, Intent intent) {
            return new ActivityResult(i, intent);
        }
    }

    public static class TakePicture extends ActivityResultContract<Uri, Boolean> {
        @Override
        public Intent createIntent(Context context, Uri uri) {
            return new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", uri);
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Boolean> getSynchronousResult(Context context, Uri uri) {
            return null;
        }

        @Override
        public final Boolean parseResult(int i, Intent intent) {
            return Boolean.valueOf(i == -1);
        }
    }

    public static class TakePicturePreview extends ActivityResultContract<Void, Bitmap> {
        @Override
        public Intent createIntent(Context context, Void r2) {
            return new Intent("android.media.action.IMAGE_CAPTURE");
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Bitmap> getSynchronousResult(Context context, Void r2) {
            return null;
        }

        @Override
        public final Bitmap parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return (Bitmap) intent.getParcelableExtra("data");
        }
    }

    @Deprecated
    public static class TakeVideo extends ActivityResultContract<Uri, Bitmap> {
        @Override
        public Intent createIntent(Context context, Uri uri) {
            return new Intent("android.media.action.VIDEO_CAPTURE").putExtra("output", uri);
        }

        @Override
        public final ActivityResultContract.SynchronousResult<Bitmap> getSynchronousResult(Context context, Uri uri) {
            return null;
        }

        @Override
        public final Bitmap parseResult(int i, Intent intent) {
            if (intent == null || i != -1) {
                return null;
            }
            return (Bitmap) intent.getParcelableExtra("data");
        }
    }

    private ActivityResultContracts() {
    }
}
