package com.xuehai.provider.constants;

import android.net.Uri;
import com.xuehai.provider.log.ContentProviderLog;

public class AuthorityUris {
    public static final String AUTHORITY_V1 = "com.xuehai.provider";
    public static final String AUTHORITY_V2 = "com.xh.zhitongyun.provider";
    private static final AuthorityUris authorityUris = new AuthorityUris("com.xh.zhitongyun.provider");
    private String authority;
    private Uri cacheUri;
    private Uri clientUri;

    @Deprecated
    private Uri messageUri;

    @Deprecated
    private Uri userUri;

    public AuthorityUris(String str) {
        init(str);
    }

    public static AuthorityUris getAuthorityUris() {
        return authorityUris;
    }

    private void init(String str) {
        this.authority = str;
        ContentProviderLog.d("AuthorityUris", "authority: " + str);
        this.cacheUri = Uri.parse("content://" + str + "/cache");
        this.clientUri = Uri.parse("content://" + str + "/client");
        this.userUri = Uri.parse("content://" + str + "/user");
        this.messageUri = Uri.parse("content://" + str + "/message");
    }

    public static void setAuthorityUris(String str) {
        authorityUris.init(str);
    }

    public String getAuthority() {
        return this.authority;
    }

    public Uri getCacheUri() {
        return this.cacheUri;
    }

    public Uri getClientUri() {
        return this.clientUri;
    }

    @Deprecated
    public Uri getMessageUri() {
        return this.messageUri;
    }

    @Deprecated
    public Uri getUserUri() {
        return this.userUri;
    }

    public void setAuthority(String str) {
        this.authority = str;
    }
}
