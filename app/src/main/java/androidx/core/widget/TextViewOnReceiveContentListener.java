package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentListener;

public final class TextViewOnReceiveContentListener implements OnReceiveContentListener {
    private static final String LOG_TAG = "ReceiveContent";

    private static final class Api16Impl {
        private Api16Impl() {
        }

        static CharSequence coerce(Context context, ClipData.Item item, int i) {
            if ((i & 1) == 0) {
                return item.coerceToStyledText(context);
            }
            CharSequence charSequenceCoerceToText = item.coerceToText(context);
            return charSequenceCoerceToText instanceof Spanned ? charSequenceCoerceToText.toString() : charSequenceCoerceToText;
        }
    }

    private static final class ApiImpl {
        private ApiImpl() {
        }

        static CharSequence coerce(Context context, ClipData.Item item, int i) {
            CharSequence charSequenceCoerceToText = item.coerceToText(context);
            return ((i & 1) == 0 || !(charSequenceCoerceToText instanceof Spanned)) ? charSequenceCoerceToText : charSequenceCoerceToText.toString();
        }
    }

    private static CharSequence coerceToText(ClipData clipData, Context context, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
            CharSequence charSequenceCoerceToText = coerceToText(context, clipData.getItemAt(i2), i);
            if (charSequenceCoerceToText != null) {
                spannableStringBuilder.append(charSequenceCoerceToText);
            }
        }
        return spannableStringBuilder;
    }

    private static CharSequence coerceToText(Context context, ClipData.Item item, int i) {
        return Build.VERSION.SDK_INT >= 16 ? Api16Impl.coerce(context, item, i) : ApiImpl.coerce(context, item, i);
    }

    private static void onReceiveForDragAndDrop(TextView textView, ContentInfoCompat contentInfoCompat) {
        replaceSelection((Editable) textView.getText(), coerceToText(contentInfoCompat.getClip(), textView.getContext(), contentInfoCompat.getFlags()));
    }

    private static void replaceSelection(Editable editable, CharSequence charSequence) {
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
        int iMax2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        Selection.setSelection(editable, iMax2);
        editable.replace(iMax, iMax2, charSequence);
    }

    @Override
    public ContentInfoCompat onReceiveContent(View view, ContentInfoCompat contentInfoCompat) {
        if (Log.isLoggable("ReceiveContent", 3)) {
            Log.d("ReceiveContent", "onReceive: " + contentInfoCompat);
        }
        int source = contentInfoCompat.getSource();
        if (source == 2) {
            return contentInfoCompat;
        }
        if (source == 3) {
            onReceiveForDragAndDrop((TextView) view, contentInfoCompat);
            return null;
        }
        ClipData clip = contentInfoCompat.getClip();
        int flags = contentInfoCompat.getFlags();
        TextView textView = (TextView) view;
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z = false;
        for (int i = 0; i < clip.getItemCount(); i++) {
            CharSequence charSequenceCoerceToText = coerceToText(context, clip.getItemAt(i), flags);
            if (charSequenceCoerceToText != null) {
                if (z) {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), charSequenceCoerceToText);
                } else {
                    replaceSelection(editable, charSequenceCoerceToText);
                    z = true;
                }
            }
        }
        return null;
    }
}
