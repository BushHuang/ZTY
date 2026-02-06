package com.tinkerpatch.sdk.loader;

import android.app.Application;
import android.content.Intent;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.entry.DefaultApplicationLike;

public class TinkerPatchApplicationLike extends DefaultApplicationLike {
    private static ApplicationLike tinkerPatchApplicationLike;

    public TinkerPatchApplicationLike(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
        setTinkerPatchApplicationLike(this);
    }

    public static ApplicationLike getTinkerPatchApplicationLike() {
        return tinkerPatchApplicationLike;
    }

    private static void setTinkerPatchApplicationLike(ApplicationLike applicationLike) {
        tinkerPatchApplicationLike = applicationLike;
    }
}
