package com.xh.xhcore.common.matrix.issue;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tencent.matrix.report.Issue;
import com.xh.xhcore.R;
import com.xh.xhcore.common.statistic.XHEnvironment;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IssuesListActivity extends AppCompatActivity {
    private static final String TAG = "IssuesListActivity";
    private static int currToastCount = 0;
    private static final File methodFilePath = new File(XHEnvironment.getExternalStorageDirectory(), "Debug.methodmap");
    private static final int toastCount = 3;
    private RecyclerView recyclerView;
    private Adapter rvAdapter;

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {
        WeakReference<Context> context;

        public Adapter(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        public int getItemCount() {
            return IssuesMap.getCount();
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int i) throws IOException {
            viewHolder.bindPosition(i);
            final Issue issue = IssuesMap.get(IssueFilter.getCurrentFilter()).get(i);
            viewHolder.bind(issue);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) throws IOException {
                    if (viewHolder.isShow) {
                        viewHolder.hideIssue();
                    } else {
                        viewHolder.showIssue(issue);
                    }
                }
            });
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(this.context.get()).inflate(R.layout.apm_item_issue_list, viewGroup, false));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private boolean isShow;
        private Context mContext;
        public int position;
        TextView tvContent;
        TextView tvIndex;
        TextView tvKey;
        TextView tvTag;
        TextView tvTime;
        TextView tvType;

        public ViewHolder(View view) {
            super(view);
            this.isShow = true;
            this.mContext = view.getContext();
            this.tvTime = (TextView) view.findViewById(R.id.apm_item_time);
            this.tvTag = (TextView) view.findViewById(R.id.apm_item_tag);
            this.tvKey = (TextView) view.findViewById(R.id.apm_item_key);
            this.tvType = (TextView) view.findViewById(R.id.apm_item_type);
            this.tvContent = (TextView) view.findViewById(R.id.apm_item_content);
            this.tvIndex = (TextView) view.findViewById(R.id.apm_item_index);
        }

        public void bind(Issue issue) throws IOException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SSS");
            Date date = new Date(Long.parseLong(issue.getContent().optString("time")));
            this.tvTime.setText("IssueTime -> " + simpleDateFormat.format(date));
            if (TextUtils.isEmpty(issue.getTag())) {
                this.tvTag.setVisibility(8);
            } else {
                this.tvTag.setText("TAG -> " + issue.getTag());
            }
            if (TextUtils.isEmpty(issue.getKey())) {
                this.tvKey.setVisibility(8);
            } else {
                this.tvKey.setText("KEY -> " + issue.getKey());
            }
            this.tvIndex.setText((this.position + 1) + "");
            this.tvIndex.setTextColor(getColor(this.position));
            if (this.isShow) {
                showIssue(issue);
            } else {
                hideIssue();
            }
        }

        public void bindPosition(int i) {
            this.position = i;
        }

        public int getColor(int i) {
            if (i == 0) {
                return -65536;
            }
            if (i != 1) {
                return i != 2 ? -7829368 : -16776961;
            }
            return -16711936;
        }

        public void hideIssue() {
            this.tvContent.setVisibility(8);
            this.isShow = false;
        }

        public void showIssue(Issue issue) throws IOException {
            try {
                this.tvContent.setText(ParseIssueUtil.parseIssue(issue, true, this.mContext.getAssets().open("methodMapping.txt")));
                this.tvContent.setVisibility(0);
                this.isShow = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static PendingIntent createPendingIntent(Context context) {
        Intent intent = new Intent(context, (Class<?>) IssuesListActivity.class);
        intent.setFlags(335544320);
        return PendingIntent.getActivity(context, 1, intent, 134217728);
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.apm_recycler_view);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter adapter = new Adapter(this);
        this.rvAdapter = adapter;
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("IssuesListActivity", "[onCreate] is enter");
        setContentView(R.layout.apm_activity_issue_list);
        setTitle(IssueFilter.getCurrentFilter());
        initRecyclerView();
        int i = currToastCount;
        if (i < 3) {
            currToastCount = i + 1;
            Toast.makeText(this, "click view to hide or show issue detail", 1).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (IssuesMap.getCount() <= 0) {
            initRecyclerView();
        }
        this.rvAdapter.notifyDataSetChanged();
    }
}
