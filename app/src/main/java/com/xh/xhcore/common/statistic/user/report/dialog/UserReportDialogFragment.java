package com.xh.xhcore.common.statistic.user.report.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.xh.view.base.dialog.BaseCenterDialogFragment;
import com.xh.view.base.dialog.BaseDialogFragment;
import com.xh.xhcore.R;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestUtil;
import com.xh.xhcore.common.statistic.user.report.BaseUserReportUploader;
import com.xh.xhcore.common.statistic.user.report.UserReportAppErrorUploader;
import com.xh.xhcore.common.statistic.user.report.UserReportNetworkErrorUploader;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/dialog/UserReportDialogFragment;", "Lcom/xh/view/base/dialog/BaseCenterDialogFragment;", "()V", "baseUserReportUploader", "Lcom/xh/xhcore/common/statistic/user/report/BaseUserReportUploader;", "etErrorMessage", "Landroid/widget/EditText;", "tvCancel", "Landroid/widget/TextView;", "tvTextLengthHint", "tvUpload", "getViewLayoutId", "", "init", "", "refreshEditText", "currentLength", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserReportDialogFragment extends BaseCenterDialogFragment {

    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_UPLOADER = "uploader";
    private static final int USER_REPORT_EDIT_TEXT_MAX_LENGTB = 240;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private BaseUserReportUploader baseUserReportUploader;
    private EditText etErrorMessage;
    private TextView tvCancel;
    private TextView tvTextLengthHint;
    private TextView tvUpload;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0007J*\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/xh/xhcore/common/statistic/user/report/dialog/UserReportDialogFragment$Companion;", "", "()V", "KEY_UPLOADER", "", "USER_REPORT_EDIT_TEXT_MAX_LENGTB", "", "getUSER_REPORT_EDIT_TEXT_MAX_LENGTB", "()I", "newInstanceAppError", "Lcom/xh/xhcore/common/statistic/user/report/dialog/UserReportDialogFragment;", "code", "message", "appCustomErrorMessage", "appUserUploadFolder", "newInstanceNetworkError", "clientTraceId", "networkErrorMessage", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static UserReportDialogFragment newInstanceAppError$default(Companion companion, int i, String str, String str2, String str3, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                str2 = "";
            }
            if ((i2 & 8) != 0) {
                str3 = "";
            }
            return companion.newInstanceAppError(i, str, str2, str3);
        }

        public static UserReportDialogFragment newInstanceNetworkError$default(Companion companion, int i, String str, String str2, String str3, int i2, Object obj) {
            if ((i2 & 8) != 0) {
                str3 = "";
            }
            return companion.newInstanceNetworkError(i, str, str2, str3);
        }

        public final int getUSER_REPORT_EDIT_TEXT_MAX_LENGTB() {
            return UserReportDialogFragment.USER_REPORT_EDIT_TEXT_MAX_LENGTB;
        }

        @JvmStatic
        public final UserReportDialogFragment newInstanceAppError(int code, String message, String appCustomErrorMessage, String appUserUploadFolder) {
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(appCustomErrorMessage, "appCustomErrorMessage");
            Intrinsics.checkNotNullParameter(appUserUploadFolder, "appUserUploadFolder");
            UserReportDialogFragment userReportDialogFragment = new UserReportDialogFragment();
            String clientTraceIdValue = XHRequestUtil.getClientTraceIdValue();
            Intrinsics.checkNotNullExpressionValue(clientTraceIdValue, "clientTraceId");
            UserReportAppErrorUploader userReportAppErrorUploader = new UserReportAppErrorUploader(code, message, clientTraceIdValue, appCustomErrorMessage, appUserUploadFolder);
            Bundle bundle = new Bundle();
            bundle.putParcelable(UserReportDialogFragment.KEY_UPLOADER, userReportAppErrorUploader);
            userReportDialogFragment.setArguments(bundle);
            return userReportDialogFragment;
        }

        @JvmStatic
        public final UserReportDialogFragment newInstanceNetworkError(int code, String message, String clientTraceId, String networkErrorMessage) {
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(clientTraceId, "clientTraceId");
            Intrinsics.checkNotNullParameter(networkErrorMessage, "networkErrorMessage");
            UserReportDialogFragment userReportDialogFragment = new UserReportDialogFragment();
            UserReportNetworkErrorUploader userReportNetworkErrorUploader = new UserReportNetworkErrorUploader(code, message, clientTraceId, networkErrorMessage);
            Bundle bundle = new Bundle();
            bundle.putParcelable(UserReportDialogFragment.KEY_UPLOADER, userReportNetworkErrorUploader);
            userReportDialogFragment.setArguments(bundle);
            return userReportDialogFragment;
        }
    }

    public UserReportDialogFragment() {
        applyCancelable(false);
    }

    private static final void m37init$lambda1(UserReportDialogFragment userReportDialogFragment, View view) throws Throwable {
        Editable text;
        Intrinsics.checkNotNullParameter(userReportDialogFragment, "this$0");
        EditText editText = userReportDialogFragment.etErrorMessage;
        String string = (editText == null || (text = editText.getText()) == null) ? null : text.toString();
        BaseUserReportUploader baseUserReportUploader = userReportDialogFragment.baseUserReportUploader;
        if (baseUserReportUploader != null) {
            String str = string;
            if (str == null || str.length() == 0) {
                string = "用户未填写问题描述信息";
            }
            baseUserReportUploader.setUserFillFeedback(string);
            baseUserReportUploader.upload();
        }
        BaseDialogFragment.BaseDialogListener mDefaultListener = userReportDialogFragment.getMDefaultListener();
        if (mDefaultListener != null) {
            BaseDialogFragment.BaseDialogListener.DefaultImpls.onDialogPositiveClick$default(mDefaultListener, userReportDialogFragment, null, 2, null);
        }
        userReportDialogFragment.dismiss();
    }

    private static final void m38init$lambda2(UserReportDialogFragment userReportDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(userReportDialogFragment, "this$0");
        BaseDialogFragment.BaseDialogListener mDefaultListener = userReportDialogFragment.getMDefaultListener();
        if (mDefaultListener != null) {
            BaseDialogFragment.BaseDialogListener.DefaultImpls.onDialogNegativeClick$default(mDefaultListener, userReportDialogFragment, null, 2, null);
        }
        userReportDialogFragment.dismiss();
    }

    @JvmStatic
    public static final UserReportDialogFragment newInstanceAppError(int i, String str, String str2, String str3) {
        return INSTANCE.newInstanceAppError(i, str, str2, str3);
    }

    @JvmStatic
    public static final UserReportDialogFragment newInstanceNetworkError(int i, String str, String str2, String str3) {
        return INSTANCE.newInstanceNetworkError(i, str, str2, str3);
    }

    private final void refreshEditText(int currentLength) {
        TextView textView = this.tvTextLengthHint;
        if (textView == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentLength);
        sb.append('/');
        sb.append(USER_REPORT_EDIT_TEXT_MAX_LENGTB);
        textView.setText(sb.toString());
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.dialog_user_report;
    }

    @Override
    public void init() {
        Bundle arguments = getArguments();
        this.baseUserReportUploader = arguments == null ? null : (BaseUserReportUploader) arguments.getParcelable(KEY_UPLOADER);
        View view = getView();
        this.etErrorMessage = view == null ? null : (EditText) view.findViewById(R.id.etErrorMessage);
        View view2 = getView();
        this.tvUpload = view2 == null ? null : (TextView) view2.findViewById(R.id.tvUpload);
        View view3 = getView();
        this.tvCancel = view3 == null ? null : (TextView) view3.findViewById(R.id.tvCancel);
        View view4 = getView();
        this.tvTextLengthHint = view4 != null ? (TextView) view4.findViewById(R.id.tvTextLengthHint) : null;
        refreshEditText(0);
        EditText editText = this.etErrorMessage;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable edt) {
                    Intrinsics.checkNotNullParameter(edt, "edt");
                    UserReportDialogFragment.this.refreshEditText(edt.length());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
        }
        TextView textView = this.tvUpload;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view5) throws Throwable {
                    UserReportDialogFragment.m37init$lambda1(this.f$0, view5);
                }
            });
        }
        TextView textView2 = this.tvCancel;
        if (textView2 == null) {
            return;
        }
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view5) {
                UserReportDialogFragment.m38init$lambda2(this.f$0, view5);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
