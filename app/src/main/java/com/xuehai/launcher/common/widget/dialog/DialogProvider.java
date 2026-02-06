package com.xuehai.launcher.common.widget.dialog;

import android.content.Context;
import android.view.View;
import com.xuehai.launcher.common.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xuehai/launcher/common/widget/dialog/DialogProvider;", "", "()V", "Builder", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DialogProvider {

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00002\b\b\u0002\u00108\u001a\u00020\u0004J\u0006\u00109\u001a\u00020:J\u000e\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>J\u0010\u0010\t\u001a\u00020\u00002\b\b\u0002\u00108\u001a\u00020\u0004J\u001a\u0010\f\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010?\u001a\u00020\u0013J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0018\u001a\u00020\u00192%\b\u0002\u0010\u001e\u001a\u001f\u0012\u0013\u0012\u00110 ¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$\u0018\u00010\u001fJ7\u0010)\u001a\u00020\u00002\b\b\u0002\u0010)\u001a\u00020\u00192%\b\u0002\u0010,\u001a\u001f\u0012\u0013\u0012\u00110 ¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$\u0018\u00010\u001fJ\u000e\u0010/\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u0019J\u0010\u00102\u001a\u00020\u00002\b\b\u0001\u00102\u001a\u00020\u0013J\u000e\u00105\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u0019R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR7\u0010\u001e\u001a\u001f\u0012\u0013\u0012\u00110 ¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010)\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR7\u0010,\u001a\u001f\u0012\u0013\u0012\u00110 ¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020$\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001c\u0010/\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001dR\u001e\u00102\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0015\"\u0004\b4\u0010\u0017R\u001c\u00105\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001b\"\u0004\b7\u0010\u001d¨\u0006@"}, d2 = {"Lcom/xuehai/launcher/common/widget/dialog/DialogProvider$Builder;", "", "()V", "applicationOverlay", "", "getApplicationOverlay", "()Z", "setApplicationOverlay", "(Z)V", "cancelable", "getCancelable", "setCancelable", "message", "", "getMessage", "()Ljava/lang/CharSequence;", "setMessage", "(Ljava/lang/CharSequence;)V", "messageGravity", "", "getMessageGravity", "()I", "setMessageGravity", "(I)V", "negative", "", "getNegative", "()Ljava/lang/String;", "setNegative", "(Ljava/lang/String;)V", "negativeListener", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "v", "", "getNegativeListener", "()Lkotlin/jvm/functions/Function1;", "setNegativeListener", "(Lkotlin/jvm/functions/Function1;)V", "positive", "getPositive", "setPositive", "positiveListener", "getPositiveListener", "setPositiveListener", "tag", "getTag", "setTag", "theme", "getTheme", "setTheme", "title", "getTitle", "setTitle", "boolean", "build", "Lcom/xuehai/launcher/common/widget/dialog/CustomDialogFragment;", "buildCustomDialog", "Lcom/xuehai/launcher/common/widget/dialog/CustomDialog;", "context", "Landroid/content/Context;", "gravity", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private boolean applicationOverlay;
        private boolean cancelable;
        private CharSequence message;
        private String negative;
        private Function1<? super View, Unit> negativeListener;
        private String positive;
        private Function1<? super View, Unit> positiveListener;
        private String tag;
        private String title;
        private int theme = R.style.TransparentDialog;
        private int messageGravity = 17;

        public static Builder applicationOverlay$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            return builder.applicationOverlay(z);
        }

        public static Builder cancelable$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            return builder.cancelable(z);
        }

        public static Builder message$default(Builder builder, CharSequence charSequence, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 17;
            }
            return builder.message(charSequence, i);
        }

        public static Builder negative$default(Builder builder, String str, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "取消";
            }
            if ((i & 2) != 0) {
                function1 = null;
            }
            return builder.negative(str, function1);
        }

        public static Builder positive$default(Builder builder, String str, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "确定";
            }
            if ((i & 2) != 0) {
                function1 = null;
            }
            return builder.positive(str, function1);
        }

        public final Builder applicationOverlay(boolean z) {
            this.applicationOverlay = z;
            return this;
        }

        public final CustomDialogFragment build() {
            return new CustomDialogFragment().init(this);
        }

        public final CustomDialog buildCustomDialog(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new CustomDialog(context, this);
        }

        public final Builder cancelable(boolean z) {
            this.cancelable = z;
            return this;
        }

        public final boolean getApplicationOverlay() {
            return this.applicationOverlay;
        }

        public final boolean getCancelable() {
            return this.cancelable;
        }

        public final CharSequence getMessage() {
            return this.message;
        }

        public final int getMessageGravity() {
            return this.messageGravity;
        }

        public final String getNegative() {
            return this.negative;
        }

        public final Function1<View, Unit> getNegativeListener() {
            return this.negativeListener;
        }

        public final String getPositive() {
            return this.positive;
        }

        public final Function1<View, Unit> getPositiveListener() {
            return this.positiveListener;
        }

        public final String getTag() {
            return this.tag;
        }

        public final int getTheme() {
            return this.theme;
        }

        public final String getTitle() {
            return this.title;
        }

        public final Builder message(CharSequence message, int gravity) {
            this.message = message;
            this.messageGravity = gravity;
            return this;
        }

        public final Builder negative(String negative, Function1<? super View, Unit> negativeListener) {
            Intrinsics.checkNotNullParameter(negative, "negative");
            this.negative = negative;
            this.negativeListener = negativeListener;
            return this;
        }

        public final Builder positive(String positive, Function1<? super View, Unit> positiveListener) {
            Intrinsics.checkNotNullParameter(positive, "positive");
            this.positive = positive;
            this.positiveListener = positiveListener;
            return this;
        }

        public final void setApplicationOverlay(boolean z) {
            this.applicationOverlay = z;
        }

        public final void setCancelable(boolean z) {
            this.cancelable = z;
        }

        public final void setMessage(CharSequence charSequence) {
            this.message = charSequence;
        }

        public final void setMessageGravity(int i) {
            this.messageGravity = i;
        }

        public final void setNegative(String str) {
            this.negative = str;
        }

        public final void setNegativeListener(Function1<? super View, Unit> function1) {
            this.negativeListener = function1;
        }

        public final void setPositive(String str) {
            this.positive = str;
        }

        public final void setPositiveListener(Function1<? super View, Unit> function1) {
            this.positiveListener = function1;
        }

        public final void setTag(String str) {
            this.tag = str;
        }

        public final void setTheme(int i) {
            this.theme = i;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final Builder tag(String tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.tag = tag;
            return this;
        }

        public final Builder theme(int theme) {
            this.theme = theme;
            return this;
        }

        public final Builder title(String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.title = title;
            return this;
        }
    }

    private DialogProvider() {
    }
}
