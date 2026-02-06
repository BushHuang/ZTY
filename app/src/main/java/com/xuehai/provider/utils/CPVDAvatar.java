package com.xuehai.provider.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.xuehai.provider.R;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;

public class CPVDAvatar {
    public static final int[] COLOR_ARRAY = {Color.parseColor("#C68178"), Color.parseColor("#5C8C9E"), Color.parseColor("#B18D6D"), Color.parseColor("#708F6E")};

    public static String getAvatarMd5(Context context) {
        CPVDUser user = CPVDUserData.getUser(context);
        return (user == null || user.getAvatarMd5() == null) ? "" : user.getAvatarMd5();
    }

    public static String getAvatarPath(Context context) {
        CPVDUser user = CPVDUserData.getUser(context);
        return (user == null || user.getAvatarPath() == null) ? "" : user.getAvatarPath();
    }

    public static String getAvatarText(Context context) {
        String username;
        CPVDUser user = CPVDUserData.getUser(context);
        return (user == null || (username = user.getUsername()) == null) ? "" : subLast2(username.trim());
    }

    public static int getColorById(long j) {
        return COLOR_ARRAY[(int) (j % 4)];
    }

    public static int getDefaultAvatar() {
        return R.drawable.zhongxing;
    }

    public static int getErrorAvatar() {
        return R.drawable.touxiang_zwf_icon;
    }

    public static int getPlaceholderAvatar() {
        return R.drawable.touxiang_zwf_icon;
    }

    public static Drawable getTextAvatarDrawable(Context context) {
        CPVDUser user = CPVDUserData.getUser(context);
        if (user == null || user.getUsername() == null) {
            return null;
        }
        return new TextAvatarDrawable(user.getUsername(), user.getUserId());
    }

    public static String subLast2(String str) {
        return str == null ? "" : str.length() <= 2 ? str : str.substring(str.length() - 2);
    }
}
