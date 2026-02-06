package com.xuehai.launcher.device;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.xuehai.launcher.common.adapter.BaseRecyclerAdapter;
import com.xuehai.launcher.common.crash.CrashLevelManager;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0013B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0012\u001a\u00020\rH\u0016¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/device/DeviceResetAdapter;", "Lcom/xuehai/launcher/common/adapter/BaseRecyclerAdapter;", "Lcom/xuehai/launcher/device/DeviceResetBean;", "Lcom/xuehai/launcher/device/DeviceResetAdapter$DebugHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/Collection;)V", "createViewHolder", "convertView", "Landroid/view/View;", "getViewLayoutId", "", "onBindView", "", "holder", "value", "position", "DebugHolder", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceResetAdapter extends BaseRecyclerAdapter<DeviceResetBean, DebugHolder> {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/device/DeviceResetAdapter$DebugHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "deviceResetItemContent", "Landroid/widget/TextView;", "getDeviceResetItemContent", "()Landroid/widget/TextView;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class DebugHolder extends RecyclerView.ViewHolder {
        private final TextView deviceResetItemContent;

        public DebugHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            View viewFindViewById = view.findViewById(2131296396);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.deviceResetItemContent)");
            this.deviceResetItemContent = (TextView) viewFindViewById;
        }

        public final TextView getDeviceResetItemContent() {
            return this.deviceResetItemContent;
        }
    }

    public DeviceResetAdapter(Context context, Collection<DeviceResetBean> collection) {
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
        return 2131492915;
    }

    @Override
    public void onBindView(DebugHolder holder, DeviceResetBean value, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (value != null) {
            holder.getDeviceResetItemContent().setText(value.getName());
            holder.itemView.setOnClickListener(value.getClick());
            CrashLevelManager.postClear$default(CrashLevelManager.INSTANCE, 0L, 1, null);
        }
    }
}
