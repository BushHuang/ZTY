package com.xh.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.xh.view.util.AnimationLoader;
import com.xh.view.util.DisplayUtil;

class TipDialog extends Dialog implements View.OnClickListener {
    private AnimationSet mAnimIn;
    private AnimationSet mAnimOut;
    private int mBackgroundColor;
    private View mBkgView;
    private Bitmap mContentBitmap;
    private ImageView mContentIv;
    private CharSequence mContentText;
    private int mContentTextColor;
    private TextView mContentTv;
    private View mDialogView;
    private Drawable mDrawable;
    private boolean mIsShowAnim;
    private OnNegativeListener mNegativeListener;
    private CharSequence mNegativeText;
    private OnPositiveListener mPositiveListener;
    private CharSequence mPositiveText;
    private int mResId;
    private CharSequence mSubContentText;
    private int mTipResId;
    private CharSequence mTitleText;
    private int mTitleTextColor;
    private TextView mTitleTv;

    public interface OnNegativeListener {
        void onClick(TipDialog tipDialog);
    }

    public interface OnPositiveListener {
        void onClick(TipDialog tipDialog);
    }

    public TipDialog(Context context) {
        this(context, 0);
    }

    public TipDialog(Context context, int i) {
        super(context, R.style.color_dialog);
        init();
    }

    private void callDismiss() {
        super.dismiss();
    }

    private void dismissWithAnimation(boolean z) {
        if (z) {
            this.mDialogView.startAnimation(this.mAnimOut);
        } else {
            super.dismiss();
        }
    }

    private void init() {
        this.mAnimIn = AnimationLoader.getInAnimation(getContext());
        this.mAnimOut = AnimationLoader.getOutAnimation(getContext());
        initAnimListener();
    }

    private void initAnimListener() {
        this.mAnimOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                TipDialog.this.mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        TipDialog.this.callDismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    private void setBackgroundColor() {
        if (this.mBackgroundColor == 0) {
            return;
        }
        float fDp2px = DisplayUtil.dp2px(getContext(), 6.0f);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{fDp2px, fDp2px, fDp2px, fDp2px, 0.0f, 0.0f, 0.0f, 0.0f}, null, null));
        shapeDrawable.getPaint().setColor(this.mBackgroundColor);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        this.mBkgView.setBackgroundDrawable(shapeDrawable);
    }

    private void setContentMode() {
        boolean z = (this.mDrawable != null) | (this.mContentBitmap != null) | (this.mResId != 0);
        boolean zIsEmpty = true ^ TextUtils.isEmpty(this.mContentText);
        if (z && zIsEmpty) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mContentTv.getLayoutParams();
            layoutParams.gravity = 80;
            this.mContentTv.setLayoutParams(layoutParams);
            this.mContentTv.setBackgroundColor(-16777216);
            this.mContentTv.getBackground().setAlpha(40);
            this.mContentTv.setVisibility(0);
            this.mContentIv.setVisibility(0);
            return;
        }
        if (!zIsEmpty) {
            if (z) {
                this.mContentTv.setVisibility(8);
                this.mContentIv.setVisibility(0);
                return;
            }
            return;
        }
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mContentTv.getLayoutParams();
        layoutParams2.gravity = 0;
        this.mContentTv.setLayoutParams(layoutParams2);
        this.mContentIv.setVisibility(8);
        this.mContentTv.setVisibility(0);
    }

    private void setTextColor() {
        int i = this.mTitleTextColor;
        if (i != 0) {
            this.mTitleTv.setTextColor(i);
        }
        int i2 = this.mContentTextColor;
        if (i2 != 0) {
            this.mContentTv.setTextColor(i2);
        }
    }

    private void startWithAnimation(boolean z) {
        if (z) {
            this.mDialogView.startAnimation(this.mAnimIn);
        }
    }

    @Override
    public void dismiss() {
        dismissWithAnimation(this.mIsShowAnim);
    }

    public CharSequence getContentText() {
        return this.mContentText;
    }

    public CharSequence getNegativeText() {
        return this.mNegativeText;
    }

    public CharSequence getPositiveText() {
        return this.mPositiveText;
    }

    public CharSequence getTitleText() {
        return this.mTitleText;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btnPositive == id) {
            this.mPositiveListener.onClick(this);
        } else if (R.id.btnNegative == id) {
            this.mNegativeListener.onClick(this);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View viewInflate = View.inflate(getContext(), R.layout.layout_colordialog, null);
        this.mDialogView = viewInflate;
        setContentView(viewInflate);
        this.mBkgView = this.mDialogView.findViewById(R.id.llBkg);
        this.mTitleTv = (TextView) this.mDialogView.findViewById(R.id.tvTitle);
        this.mContentTv = (TextView) this.mDialogView.findViewById(R.id.tvContent);
        this.mContentIv = (ImageView) this.mDialogView.findViewById(R.id.ivContent);
        ImageView imageView = (ImageView) this.mDialogView.findViewById(R.id.tipIv);
        TextView textView = (TextView) this.mDialogView.findViewById(R.id.tvSubContent);
        TextView textView2 = (TextView) this.mDialogView.findViewById(R.id.btnPositive);
        TextView textView3 = (TextView) this.mDialogView.findViewById(R.id.btnNegative);
        View viewFindViewById = this.mDialogView.findViewById(R.id.divider);
        View viewFindViewById2 = this.mDialogView.findViewById(R.id.llBtnGroup);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        if (TextUtils.isEmpty(this.mTitleText)) {
            this.mTitleTv.setVisibility(8);
        } else {
            this.mTitleTv.setText(this.mTitleText);
            this.mTitleTv.setVisibility(0);
        }
        this.mContentTv.setText(this.mContentText);
        textView2.setText(this.mPositiveText);
        textView3.setText(this.mNegativeText);
        if (this.mPositiveListener == null && this.mNegativeListener == null) {
            viewFindViewById2.setVisibility(8);
        } else if (this.mPositiveListener == null) {
            textView2.setVisibility(8);
            viewFindViewById.setVisibility(8);
            textView3.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.sel_def_gray));
        } else if (this.mNegativeListener == null) {
            textView3.setVisibility(8);
            viewFindViewById.setVisibility(8);
            textView2.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.sel_def_gray));
        }
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            this.mContentIv.setBackgroundDrawable(drawable);
        }
        Bitmap bitmap = this.mContentBitmap;
        if (bitmap != null) {
            this.mContentIv.setImageBitmap(bitmap);
        }
        int i = this.mResId;
        if (i != 0) {
            this.mContentIv.setBackgroundResource(i);
        }
        int i2 = this.mTipResId;
        if (i2 != 0) {
            imageView.setBackgroundResource(i2);
        } else {
            imageView.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.mSubContentText)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(this.mSubContentText);
        }
        setTextColor();
        setBackgroundColor();
        setContentMode();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(this.mIsShowAnim);
    }

    public TipDialog setAnimationEnable(boolean z) {
        this.mIsShowAnim = z;
        return this;
    }

    public TipDialog setAnimationIn(AnimationSet animationSet) {
        this.mAnimIn = animationSet;
        return this;
    }

    public TipDialog setAnimationOut(AnimationSet animationSet) {
        this.mAnimOut = animationSet;
        initAnimListener();
        return this;
    }

    public TipDialog setColor(int i) {
        this.mBackgroundColor = i;
        return this;
    }

    public TipDialog setColor(String str) {
        try {
            setColor(Color.parseColor(str));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TipDialog setContentImage(int i) {
        this.mResId = i;
        return this;
    }

    public TipDialog setContentImage(Bitmap bitmap) {
        this.mContentBitmap = bitmap;
        return this;
    }

    public TipDialog setContentImage(Drawable drawable) {
        this.mDrawable = drawable;
        return this;
    }

    public TipDialog setContentText(int i) {
        return setContentText(getContext().getText(i));
    }

    public TipDialog setContentText(CharSequence charSequence) {
        this.mContentText = charSequence;
        return this;
    }

    public TipDialog setContentTextColor(int i) {
        this.mContentTextColor = i;
        return this;
    }

    public TipDialog setContentTextColor(String str) {
        try {
            setContentTextColor(Color.parseColor(str));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TipDialog setNegativeListener(int i, OnNegativeListener onNegativeListener) {
        return setNegativeListener(getContext().getText(i), onNegativeListener);
    }

    public TipDialog setNegativeListener(CharSequence charSequence, OnNegativeListener onNegativeListener) {
        this.mNegativeText = charSequence;
        this.mNegativeListener = onNegativeListener;
        return this;
    }

    public TipDialog setPositiveListener(int i, OnPositiveListener onPositiveListener) {
        return setPositiveListener(getContext().getText(i), onPositiveListener);
    }

    public TipDialog setPositiveListener(CharSequence charSequence, OnPositiveListener onPositiveListener) {
        this.mPositiveText = charSequence;
        this.mPositiveListener = onPositiveListener;
        return this;
    }

    public TipDialog setSubContentText(int i) {
        return setSubContentText(getContext().getText(i));
    }

    public TipDialog setSubContentText(CharSequence charSequence) {
        this.mSubContentText = charSequence;
        return this;
    }

    public TipDialog setTipImage(int i) {
        this.mTipResId = i;
        return this;
    }

    @Override
    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    @Override
    public void setTitle(CharSequence charSequence) {
        this.mTitleText = charSequence;
    }

    public TipDialog setTitleTextColor(int i) {
        this.mTitleTextColor = i;
        return this;
    }

    public TipDialog setTitleTextColor(String str) {
        try {
            setTitleTextColor(Color.parseColor(str));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }
}
