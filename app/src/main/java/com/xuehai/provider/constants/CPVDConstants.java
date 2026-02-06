package com.xuehai.provider.constants;

import android.net.Uri;

public interface CPVDConstants {

    @Deprecated
    public static final String EXIT = "com.xh.launcher.exit";
    public static final String KEY_PACKAGE_NAME = "key_PACKAGE_NAME";

    @Deprecated
    public static final String RESOURCE_UPDATE = "com.xuehai.cpvd.resource_update_";

    @Deprecated
    public static final String SESSION_RESET_SUCCESS = "com.xuehai.cpvd.session.reset.success";

    @Deprecated
    public static final String AUTHORITY = AuthorityUris.getAuthorityUris().getAuthority();

    @Deprecated
    public static final Uri URI_CACHE = AuthorityUris.getAuthorityUris().getCacheUri();

    @Deprecated
    public static final Uri URI_USER = AuthorityUris.getAuthorityUris().getUserUri();

    @Deprecated
    public static final Uri URI_MESSAGE = AuthorityUris.getAuthorityUris().getMessageUri();

    public interface Environment {
        public static final int DEVELOP = 3;
        public static final int PRE_RELEASE = 4;
        public static final int PRODUCTION = 1;
        public static final int TEST = 2;
    }

    public interface LoginStatus {
        public static final int LOGIN = 1;
        public static final int LOGOUT = 2;
        public static final int UN_KNOW = 0;
    }
}
