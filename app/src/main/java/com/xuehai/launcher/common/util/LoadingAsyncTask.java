package com.xuehai.launcher.common.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class LoadingAsyncTask<Params, Result> extends AsyncTask<Params, Integer, Result> {
    private TaskCallBack<Params, Result> callBack;
    private ProgressDialog progressDialog;
    private String tip;

    public interface TaskCallBack<Params, Result> {
        Result execute(int i, Params... paramsArr);

        void finish(int i, Result result);
    }

    public LoadingAsyncTask(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        this.progressDialog = progressDialog;
        progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("loading ......");
        this.progressDialog.setCancelable(false);
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.progressDialog.setIndeterminate(false);
    }

    private void showProgress(String str) {
        if (this.progressDialog.isShowing()) {
            return;
        }
        if (str != null) {
            this.progressDialog.setMessage(str);
        }
        this.progressDialog.show();
    }

    private void updateProgress(int i) {
        if (this.progressDialog.isShowing()) {
            this.progressDialog.setProgress(i);
        }
    }

    public void dismissProgress() {
        if (this.progressDialog.isShowing()) {
            this.progressDialog.setMessage("操作结束......");
            this.progressDialog.dismiss();
        }
    }

    @Override
    @SafeVarargs
    protected final Result doInBackground(Params... paramsArr) {
        TaskCallBack<Params, Result> taskCallBack = this.callBack;
        if (taskCallBack != null) {
            return taskCallBack.execute(0, paramsArr);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        TaskCallBack<Params, Result> taskCallBack = this.callBack;
        if (taskCallBack != null) {
            taskCallBack.finish(0, result);
        }
        dismissProgress();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgress(this.tip);
    }

    @Override
    protected void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate((Object[]) numArr);
        updateProgress(numArr[0].intValue());
    }

    public LoadingAsyncTask setCallBack(TaskCallBack<Params, Result> taskCallBack) {
        this.callBack = taskCallBack;
        return this;
    }

    public LoadingAsyncTask setTip(String str) {
        this.tip = str;
        return this;
    }
}
