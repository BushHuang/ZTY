package com.xuehai.provider.core.db;

import android.content.Context;
import android.content.Intent;
import com.xuehai.provider.constants.BroadcastAction;
import com.xuehai.provider.core.CPVDProcess;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.core.dto.CPVDMessage;
import com.xuehai.provider.log.ContentProviderLog;
import com.xuehai.provider.utils.JsonUtil;

public class CPVDMessageData {
    private static final String TAG = "MessageData";

    public static String getKey(long j, String str) {
        return "message_" + str + "_" + j;
    }

    public static CPVDMessage getMessage(Context context, long j, String str) {
        return (CPVDMessage) JsonUtil.parseJson(CPVDCacheData.getCacheValue(context, getKey(j, str)), CPVDMessage.class);
    }

    public static boolean isMessageKey(String str) {
        return str.startsWith("message_");
    }

    public static void readAllMessage(Context context, long j, String str) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(j, str), null));
    }

    public static void receiverMessage(Context context, CPVDMessage cPVDMessage, String str) {
        updateMessage(context, cPVDMessage);
        String strMessageTrans = BroadcastAction.messageTrans(cPVDMessage.getPackageName());
        Intent intent = new Intent(strMessageTrans);
        intent.setPackage(cPVDMessage.getPackageName());
        intent.putExtra(strMessageTrans, str);
        CPVDProcess.sendBroadcast(context, intent);
    }

    @Deprecated
    public static void updateMessage(Context context, long j, String str) {
        ContentProviderLog.i("MessageData", "updateMessage : " + str);
        CPVDMessage message = getMessage(context, j, str);
        if (message == null) {
            message = new CPVDMessage(j, str, 1);
        } else {
            message.setUnReadCount(message.getUnReadCount() + 1);
        }
        updateMessage(context, message);
    }

    public static void updateMessage(Context context, long j, String str, int i) {
        ContentProviderLog.i("MessageData", "updateMessage : " + str);
        CPVDMessage message = getMessage(context, j, str);
        if (message == null) {
            message = new CPVDMessage(j, str, i);
        } else {
            message.setUnReadCount(i);
        }
        updateMessage(context, message);
    }

    private static void updateMessage(Context context, CPVDMessage cPVDMessage) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(cPVDMessage.getUserId(), cPVDMessage.getPackageName()), JsonUtil.objToJson(cPVDMessage)));
    }
}
