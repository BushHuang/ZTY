package com.xh.xhcore.common.upload;

public class XHTask {
    public static final int XH_TASK_ID_ERROR = -1;
    private XHTask keyTask;
    private String msg;
    private Object originalTask;
    private SdkType requestRealType;
    private boolean success;
    private int xhTaskId;

    public XHTask() {
    }

    public XHTask(int i) {
        this.xhTaskId = i;
    }

    public XHTask getKeyTask() {
        return this.keyTask;
    }

    public String getMsg() {
        return this.msg;
    }

    public Object getOriginalTask() {
        return this.originalTask;
    }

    public SdkType getRequestRealType() {
        return this.requestRealType;
    }

    public int getXhTaskId() {
        return this.xhTaskId;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setKeyTask(XHTask xHTask) {
        this.keyTask = xHTask;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setOriginalTask(Object obj) {
        this.originalTask = obj;
    }

    public void setRequestRealType(SdkType sdkType) {
        this.requestRealType = sdkType;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public XHTask setXhTaskId(int i) {
        this.xhTaskId = i;
        return this;
    }
}
