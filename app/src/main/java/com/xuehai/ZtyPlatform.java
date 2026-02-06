package com.xuehai;

import android.app.Application;
import android.content.Context;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import com.xuehai.zty.ZtyUser;

public class ZtyPlatform {
    public static Application sApp;
    public static String sAppKey;
    public static String sAppSecret;

    public static ZtyUser getUser(Context context) {
        CPVDUser user = CPVDUserData.getUser(context);
        ZtyUser ztyUser = new ZtyUser();
        ztyUser.setOpenId(user.getOpenId());
        ztyUser.setMobile(user.getParentMobile());
        ztyUser.setActivateDate(user.getActivateDate());
        ztyUser.setGrade(user.getGrade());
        ztyUser.setSex(user.getSex());
        ztyUser.setUserName(user.getUsername());
        return ztyUser;
    }

    public static void init(Application application, String str, String str2) {
        sApp = application;
        sAppKey = str;
        sAppSecret = str2;
    }
}
