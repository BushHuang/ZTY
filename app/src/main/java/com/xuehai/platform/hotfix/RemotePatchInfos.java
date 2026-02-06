package com.xuehai.platform.hotfix;

import java.util.List;

public class RemotePatchInfos {
    private long changeVersion;
    private List<PatchInfo> content;

    public long getChangeVersion() {
        return this.changeVersion;
    }

    public List<PatchInfo> getContent() {
        return this.content;
    }

    public void setChangeVersion(long j) {
        this.changeVersion = j;
    }

    public void setContent(List<PatchInfo> list) {
        this.content = list;
    }

    public String toString() {
        return "RemotePatchInfos{changeVersion=" + this.changeVersion + ", content=" + this.content + '}';
    }
}
