package com.xuehai.launcher.other;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.xuehai.launcher.common.adapter.BaseRecyclerAdapter;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0013B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0012\u001a\u00020\rH\u0016¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/other/OtherAdapter;", "Lcom/xuehai/launcher/common/adapter/BaseRecyclerAdapter;", "Lcom/xuehai/launcher/other/OtherBean;", "Lcom/xuehai/launcher/other/OtherAdapter$DebugHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/Collection;)V", "createViewHolder", "convertView", "Landroid/view/View;", "getViewLayoutId", "", "onBindView", "", "holder", "value", "position", "DebugHolder", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OtherAdapter extends BaseRecyclerAdapter<OtherBean, DebugHolder> {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/other/OtherAdapter$DebugHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "otherItemBackIv", "Landroid/widget/ImageView;", "getOtherItemBackIv", "()Landroid/widget/ImageView;", "otherItemContentTv", "Landroid/widget/TextView;", "getOtherItemContentTv", "()Landroid/widget/TextView;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DebugHolder extends RecyclerView.ViewHolder {
        private final ImageView otherItemBackIv;
        private final TextView otherItemContentTv;

        public DebugHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            View viewFindViewById = view.findViewById(2131296529);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.otherItemContentTv)");
            this.otherItemContentTv = (TextView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(2131296528);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.otherItemBackIv)");
            this.otherItemBackIv = (ImageView) viewFindViewById2;
        }

        public final ImageView getOtherItemBackIv() {
            return this.otherItemBackIv;
        }

        public final TextView getOtherItemContentTv() {
            return this.otherItemContentTv;
        }
    }

    public OtherAdapter(Context context, Collection<OtherBean> collection) {
        super(context, collection);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public DebugHolder createViewHolder(View convertView) {
        Intrinsics.checkNotNullParameter(convertView, "convertView");
        return new DebugHolder(convertView);
    }

    @Override
    public int getViewLayoutId() {
        return 2131492949;
    }

    @Override
    public void onBindView(DebugHolder holder, OtherBean value, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (value != null) {
            holder.getOtherItemContentTv().setText(value.getTitle());
            holder.itemView.setOnClickListener(value.getClick());
        }
    }
}
