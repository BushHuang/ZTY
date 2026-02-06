package com.xuehai.system.mdm;

import android.app.usage.UsageEvents;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import com.google.gson.reflect.TypeToken;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.constants.FilePath;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.ActivityUtil;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.TotpUtils;
import com.xuehai.launcher.natives.SecretJni;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.xuehai.mdm.config.DeviceSupport;
import com.xuehai.system.common.appusage.AppUsageHelper;
import com.xuehai.system.common.entities.PowerMode;
import com.xuehai.system.common.entities.RingerMode;
import com.xuehai.system.mdm.MdmAidlInterface;
import com.xuehai.system.mdm.WipeDataActivity;
import com.xuehai.system.mdm.device.DeviceManager;
import com.xuehai.system.mdm.device.DevicePasswordManager;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.xuehai.system.mdm.proxy.SettingPolicyProxy;
import com.zaze.utils.AppUtil;
import com.zaze.utils.FileUtil;
import com.zaze.utils.JsonUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.codec.binary.Base32;

@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u001d\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J8\u0010\u0015\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00132\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00132\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0018\u0010\u0019\u001a\u00020\u00112\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0018\u0010\u001b\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0018\u0010\u001c\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0018\u0010\u001d\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0012\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0016J\u0018\u0010 \u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0018\u0010!\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J \u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\u0010\u0010'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0018\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020$2\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0010\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0010\u0010.\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0010\u0010/\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0010\u00100\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0011H\u0016J\u0010\u00101\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0011H\u0016J\"\u00102\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\b\u00104\u001a\u00020\u0011H\u0016J\b\u00105\u001a\u00020\u0011H\u0016J\b\u00106\u001a\u00020\fH\u0016J\b\u00107\u001a\u00020\u0011H\u0016J\b\u00108\u001a\u00020\u0011H\u0016J\b\u00109\u001a\u00020\u0011H\u0016J\b\u0010:\u001a\u00020\fH\u0016J\u0010\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u00020\u0011H\u0016J\u0010\u0010=\u001a\u00020\f2\u0006\u0010>\u001a\u00020\u0011H\u0016J\u0010\u0010?\u001a\u00020\f2\u0006\u0010<\u001a\u00020\u0011H\u0016J\u0010\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020$H\u0016J\u0010\u0010B\u001a\u00020\f2\u0006\u0010C\u001a\u00020\u000fH\u0016J\u0010\u0010D\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\b\u0010E\u001a\u00020\fH\u0016J\u0018\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020HH\u0016J\u000e\u0010J\u001a\b\u0012\u0004\u0012\u00020\u000f0KH\u0016J\u000e\u0010L\u001a\b\u0012\u0004\u0012\u00020\u000f0KH\u0016J\u001e\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00132\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020HH\u0016J\u001e\u0010O\u001a\b\u0012\u0004\u0012\u00020P0K2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020HH\u0016J\u0010\u0010Q\u001a\n\u0012\u0004\u0012\u00020R\u0018\u00010\u0013H\u0016J\b\u0010S\u001a\u00020\u0011H\u0016J\b\u0010T\u001a\u00020\u000fH\u0016J\u000e\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000f0KH\u0016J\b\u0010V\u001a\u00020\u000fH\u0016J\u0018\u0010W\u001a\u00020$2\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000fH\u0016J\u0018\u0010Y\u001a\u00020H2\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000fH\u0016J\u000e\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u000f0KH\u0016J\b\u0010[\u001a\u00020\u000fH\u0016J\b\u0010\\\u001a\u00020\u000fH\u0016J\u0018\u0010]\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000fH\u0016J\b\u0010^\u001a\u00020\u000fH\u0016J\u0018\u0010_\u001a\u00020`2\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000fH\u0016J\u0012\u0010a\u001a\u00020\u00112\b\u0010b\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010c\u001a\u00020\u0011H\u0016J\b\u0010d\u001a\u00020\u0011H\u0016J\b\u0010e\u001a\u00020\u0011H\u0016J\b\u0010f\u001a\u00020\u0011H\u0016J\b\u0010g\u001a\u00020\u0011H\u0016J\b\u0010h\u001a\u00020\u0011H\u0016J\b\u0010i\u001a\u00020\u0011H\u0016J\b\u0010j\u001a\u00020\u0011H\u0016J\u0010\u0010k\u001a\u00020\u00112\u0006\u0010+\u001a\u00020$H\u0016J\b\u0010l\u001a\u00020\u0011H\u0016J\b\u0010m\u001a\u00020\u0011H\u0016J\b\u0010n\u001a\u00020\u0011H\u0016J\b\u0010o\u001a\u00020\u0011H\u0016J\b\u0010p\u001a\u00020\u0011H\u0016J\b\u0010q\u001a\u00020\u0011H\u0016J\b\u0010r\u001a\u00020\fH\u0016J\b\u0010s\u001a\u00020\u0011H\u0016J\b\u0010t\u001a\u00020\fH\u0016J \u0010u\u001a\u00020\u00112\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010v\u001a\u00020`H\u0016J \u0010w\u001a\u00020\u00112\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010v\u001a\u00020$H\u0016J \u0010x\u001a\u00020\u00112\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010v\u001a\u00020HH\u0016J \u0010y\u001a\u00020\u00112\u0006\u0010A\u001a\u00020$2\u0006\u0010X\u001a\u00020\u000f2\u0006\u0010v\u001a\u00020\u000fH\u0016J,\u0010z\u001a\u0004\u0018\u00010{2\u0006\u0010|\u001a\u00020$2\b\u0010}\u001a\u0004\u0018\u00010\u000f2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020HH\u0016J4\u0010~\u001a\u0004\u0018\u00010{2\u0006\u0010|\u001a\u00020$2\b\u0010}\u001a\u0004\u0018\u00010\u000f2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\u0006\u0010\u007f\u001a\u00020$H\u0016J\u0017\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u00012\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010\u0083\u0001\u001a\u00020\fH\u0016J\u0019\u0010\u0084\u0001\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0019\u0010\u0085\u0001\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u001e\u0010\u0086\u0001\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010\u0088\u0001\u001a\u00020\fH\u0016J\u0019\u0010\u0089\u0001\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\t\u0010\u008a\u0001\u001a\u00020\u0011H\u0016J\u0019\u0010\u008b\u0001\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0019\u0010\u008c\u0001\u001a\u00020\u00112\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0013H\u0016J\u0014\u0010\u008d\u0001\u001a\u00020\u00112\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010\u008f\u0001\u001a\u00020\u0011H\u0016J\t\u0010\u0090\u0001\u001a\u00020\u0011H\u0016J\u0014\u0010\u0091\u0001\u001a\u00020\u000f2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u001e\u0010\u0093\u0001\u001a\u00020\u00112\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u001b\u0010\u0097\u0001\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\u0006\u0010-\u001a\u00020\u0011H\u0016J\u001c\u0010\u0098\u0001\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u0011\u0010\u0099\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010\u009a\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010\u009b\u0001\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u0011H\u0016J3\u0010\u009c\u0001\u001a\u00020\f2\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u000f2\u0007\u0010 \u0001\u001a\u00020HH\u0016J\u0011\u0010¡\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010¢\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010£\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010¤\u0001\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u0011H\u0016J\u001a\u0010¥\u0001\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u000f2\u0007\u0010\u0087\u0001\u001a\u00020\u000fH\u0016J\u001e\u0010¦\u0001\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010§\u0001\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u0012\u0010¨\u0001\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u0011\u0010©\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010ª\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0014\u0010«\u0001\u001a\u00020\u00112\t\u0010¬\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u00ad\u0001\u001a\u00020\u00112\u0007\u0010®\u0001\u001a\u00020\u0011H\u0016J\u0011\u0010¯\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010°\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010±\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0012\u0010²\u0001\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u0012\u0010³\u0001\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u0011\u0010´\u0001\u001a\u00020\f2\u0006\u0010A\u001a\u00020$H\u0016J\u0011\u0010µ\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010¶\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010·\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010¸\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0012\u0010¹\u0001\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J\u001f\u0010º\u0001\u001a\u00020\f2\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u000f2\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010»\u0001\u001a\u00020\u00112\u0007\u0010\u0096\u0001\u001a\u00020\u0011H\u0016J'\u0010¼\u0001\u001a\u00020\f2\u001c\u0010½\u0001\u001a\u0017\u0012\u0007\u0012\u0005\u0018\u00010¿\u0001\u0012\u0007\u0012\u0005\u0018\u00010¿\u0001\u0018\u00010¾\u0001H\u0016J\"\u0010À\u0001\u001a\u00020\u00112\u0007\u0010Á\u0001\u001a\u00020$2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\u0011\u0010Â\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0012\u0010Ã\u0001\u001a\u00020\u00112\u0007\u0010Ä\u0001\u001a\u00020HH\u0016J\u0011\u0010Å\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0014\u0010Æ\u0001\u001a\u00020\u00112\t\u0010Ç\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0011\u0010È\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\u0011\u0010É\u0001\u001a\u00020\u00112\u0006\u0010-\u001a\u00020\u0011H\u0016J\t\u0010Ê\u0001\u001a\u00020\fH\u0016J\u0012\u0010Ë\u0001\u001a\u00020\u00112\u0007\u0010Ì\u0001\u001a\u00020\u0011H\u0016J\u001b\u0010Í\u0001\u001a\u00020\f2\u0007\u0010Î\u0001\u001a\u00020$2\u0007\u0010Ï\u0001\u001a\u00020$H\u0016J\u0014\u0010Ð\u0001\u001a\u00020\u00112\t\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0013\u0010Ò\u0001\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010Ó\u0001\u001a\u00020\u0011H\u0016J\u0013\u0010Ô\u0001\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010Õ\u0001\u001a\u00020\fH\u0016J'\u0010Ö\u0001\u001a\u00020\u00112\b\u0010X\u001a\u0004\u0018\u00010\u000f2\t\u0010×\u0001\u001a\u0004\u0018\u00010\u000f2\u0007\u0010Ø\u0001\u001a\u00020$H\u0016J\u001d\u0010Ù\u0001\u001a\u00020\u00112\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u000f2\u0007\u0010Ø\u0001\u001a\u00020$H\u0016J\u0013\u0010Ú\u0001\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010Û\u0001\u001a\u00020\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006Ü\u0001"}, d2 = {"Lcom/xuehai/system/mdm/MdmBinder;", "Lcom/xuehai/system/mdm/MdmAidlInterface$Stub;", "()V", "context", "Lcom/xuehai/launcher/App;", "deviceSupport", "Lcom/xuehai/mdm/config/DeviceSupport;", "getDeviceSupport", "()Lcom/xuehai/mdm/config/DeviceSupport;", "deviceSupport$delegate", "Lkotlin/Lazy;", "activateFreeLicense", "", "activatePayLicense", "license", "", "addAppAlwaysRunningList", "", "packageList", "", "addAppAutoRunningList", "addAppDomainsRules", "packageNames", "allowDomains", "denyDomains", "addDomainsRules", "domains", "addFirewallAllowApps", "addFirewallDenyApps", "addIgnoreFrequentRelaunchAppList", "addPackageToBatteryOptimizationWhiteList", "packageName", "addPackagesToNotificationWhiteList", "addRuntimePermissionFixAppList", "adjustStreamVolume", "streamType", "", "direction", "flags", "allowFactoryReset", "allow", "allowFirmwareRecovery", "allowHardwareKey", "keyCode", "allowLocationService", "enable", "allowMultiWindowMode", "allowMultipleUsers", "allowOTAUpgrade", "allowSafeMode", "applyRuntimePermissions", "runtimePermissions", "cleanAppDomainRules", "cleanFirewallApps", "clearBootingAnimation", "clearIgnoreFrequentRelaunchAppList", "clearPackagesFromNotificationList", "clearRuntimePermissionFixAppList", "clearShuttingDownAnimation", "controlAirplaneMode", "isOn", "controlChangeHome", "isEnable", "controlEyeMode", "controlRingerMode", "mode", "controlSettingsTopLevelMenu", "keyList", "controlShowDeprecatedTarget", "deactivateDevice", "dumpAppUsageEventsSnapshot", "beginTime", "", "endTime", "getAppAlwaysRunningList", "", "getAppAutoRunningList", "getAppUsage", "Lcom/xuehai/system/mdm/AppUsage;", "getAppUsageV2", "Lcom/xuehai/system/mdm/AppUsage2;", "getApplicationNetworkStats", "Lcom/xuehai/system/mdm/NetworkStatsBean;", "getAutomaticTime", "getDeviceMacAddress", "getIgnoreFrequentRelaunchAppList", "getImei", "getIntSettingsSystemValue", "key", "getLongSettingsSystemValue", "getRuntimePermissionFixAppList", "getSerialNumber", "getSimSerialNumber", "getStringSettingsSystemValue", "getTopAppPackageName", "gettFloatSettingsSystemValue", "", "installApplication", "apkFilePath", "isBluetoothEnabled", "isClipboardAllowed", "isDeviceAdminActivated", "isExternalStorageEnable", "isEyeComfortTurnedOn", "isFactoryResetAllowed", "isFirmwareRecoveryAllowed", "isFreeLicenseActivated", "isHardwareKeyAllow", "isMTPAvailable", "isMdmSupport", "isOsVersionRemoteValid", "isPayLicenseActivated", "isRandomMacDisabled", "isUsbDebuggingEnabled", "lockScreen", "multipleUsersAllowed", "openSoftUpdateView", "putFloatSettingsSystemValue", "value", "putIntSettingsSystemValue", "putLongSettingsSystemValue", "putStringSettingsSystemValue", "queryTrafficDetailBySummary", "Lcom/xuehai/system/mdm/TrafficInfo;", "networkType", "subscriberId", "queryTrafficDetailByUid", "uid", "readFileFromData", "Landroid/os/ParcelFileDescriptor;", "fileName", "reboot", "removeAppAlwaysRunningList", "removeAppAutoRunningList", "removeDefaultLauncher", "className", "removeDevicePassword", "removeIgnoreFrequentRelaunchAppList", "removeLockScreen", "removePackagesFromNotificationWhiteList", "removeRuntimePermissionFixAppList", "resetFactory", "token", "resetLocalActivateState", "resetNetworkSetting", "runBugReport", "filePath", "setApplicationComponentState", "cmpName", "Landroid/content/ComponentName;", "state", "setApplicationState", "setApplicationUninstalltionState", "setAutomaticTime", "setBixbyShortcutEnabled", "setBluetoothEnable", "setBootingAnimation", "animationFile", "loopFile", "soundFile", "delay", "setCameraShortcutEnabled", "setCameraState", "setClipboardEnabled", "setDateTimeChangeEnabled", "setDefaultHome", "setDefaultLauncher", "setDevelopHiddenState", "setDropMenuHiddenState", "setEmergencyCallOnly", "setFileShareDisabled", "setInputMethod", "inputMethodClassName", "setIptablesOption", "status", "setKanbanEnabled", "setLockScreenItems", "setMTPEnabled", "setMobileDataState", "setNetworkSpeedState", "setPowerSavingMode", "setRandomMacDisabled", "setScreenCapture", "setSdCardState", "setSettingEnabled", "setSettingsHiddenState", "setShuttingDownAnimation", "setSpenPointImageState", "setStatusBarButtonState", "map", "", "", "setStreamVolume", "volume", "setSwitchInputMethodEnabled", "setSystemTime", "timeMillis", "setTaskBarEnabled", "setTimeZone", "timeZoneId", "setUsbDebuggingEnabled", "setWiFiState", "shoutdown", "startGPS", "start", "startSoftUpdate", "hour", "minute", "startTcpdump", "outPath", "stopApp", "stopTcpdump", "uninstallApplication", "unlockScreen", "verifyDynamicCode", "code", "tokenType", "verifyDynamicToken", "wipeApplicationData", "wipeRecentTasks", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmBinder extends MdmAidlInterface.Stub {
    private final App context = App.INSTANCE.getInstance();

    private final Lazy deviceSupport = LazyKt.lazy(new Function0<DeviceSupport>() {
        {
            super(0);
        }

        @Override
        public final DeviceSupport invoke() {
            return new DeviceSupport(this.this$0.context);
        }
    });

    private static final void m217dumpAppUsageEventsSnapshot$lambda21(MdmBinder mdmBinder, long j, long j2) {
        Intrinsics.checkNotNullParameter(mdmBinder, "this$0");
        List<UsageEvents.Event> listQueryAppUsageEvents = AppUsageHelper.INSTANCE.queryAppUsageEvents(mdmBinder.context, j, j2);
        File file = new File(FilePath.getLogDir() + "/sys_usage_events_snapshot.json.1");
        String strObjToJson = JsonUtil.objToJson(listQueryAppUsageEvents);
        Intrinsics.checkNotNullExpressionValue(strObjToJson, "objToJson(appUsageEvents)");
        FileUtil.writeToFile$default(file, strObjToJson, false, 4, (Object) null);
    }

    private final DeviceSupport getDeviceSupport() {
        return (DeviceSupport) this.deviceSupport.getValue();
    }

    private static final void m218installApplication$lambda3() {
        SessionData.INSTANCE.setZtyUpdating(false);
    }

    private static final void m220readFileFromData$lambda0(String str, ParcelFileDescriptor[] parcelFileDescriptorArr) throws Throwable {
        WriteFileTask writeFileTask = WriteFileTask.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(parcelFileDescriptorArr, "pfd");
        writeFileTask.writeDataFileToPipe(str, parcelFileDescriptorArr);
    }

    @Override
    public void activateFreeLicense() {
        PolicyManager.getLicensePolicyProxy().activateFreeLicense();
    }

    @Override
    public void activatePayLicense(String license) {
        if (license != null) {
            PolicyManager.getLicensePolicyProxy().activatePayLicense(license);
        }
    }

    @Override
    public boolean addAppAlwaysRunningList(List<String> packageList) {
        if (packageList != null) {
            return PolicyManager.getApplicationPolicyProxy().addAppAlwaysRunningList(packageList);
        }
        return true;
    }

    @Override
    public boolean addAppAutoRunningList(List<String> packageList) {
        if (packageList != null) {
            return PolicyManager.getApplicationPolicyProxy().addAppAutoRunningList(packageList);
        }
        return true;
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        if (packageNames == null || allowDomains == null || denyDomains == null) {
            return false;
        }
        return PolicyManager.getFirewallPolicyProxy().addAppDomainsRules(packageNames, allowDomains, denyDomains);
    }

    @Override
    public boolean addDomainsRules(List<String> domains) {
        if (domains == null) {
            return false;
        }
        return PolicyManager.getFirewallPolicyProxy().addDomainsRules(domains);
    }

    @Override
    public boolean addFirewallAllowApps(List<String> packageNames) {
        if (packageNames != null) {
            return PolicyManager.getFirewallPolicyProxy().addAllowApps(packageNames);
        }
        return true;
    }

    @Override
    public boolean addFirewallDenyApps(List<String> packageNames) {
        if (packageNames != null) {
            return PolicyManager.getFirewallPolicyProxy().addDenyApps(packageNames);
        }
        return true;
    }

    @Override
    public boolean addIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        List<String> list = packageNames;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return PolicyManager.getApplicationPolicyProxy().addIgnoreFrequentRelaunchAppList(packageNames);
    }

    @Override
    public boolean addPackageToBatteryOptimizationWhiteList(String packageName) {
        if (packageName != null) {
            return PolicyManager.getApplicationPolicyProxy().addPackageToBatteryOptimizationWhiteList(packageName);
        }
        return false;
    }

    @Override
    public boolean addPackagesToNotificationWhiteList(List<String> packageList) {
        if (packageList != null) {
            return PolicyManager.getApplicationPolicyProxy().addPackagesToNotificationWhiteList(packageList);
        }
        return true;
    }

    @Override
    public boolean addRuntimePermissionFixAppList(List<String> packageNames) {
        List<String> list = packageNames;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return PolicyManager.getApplicationPolicyProxy().addRuntimePermissionFixAppList(packageNames);
    }

    @Override
    public boolean adjustStreamVolume(int streamType, int direction, int flags) {
        return PolicyManager.getSettingPolicyProxy().adjustStreamVolume(streamType, direction, flags);
    }

    @Override
    public boolean allowFactoryReset(boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowFactoryReset(allow);
    }

    @Override
    public boolean allowFirmwareRecovery(boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowFirmwareRecovery(allow);
    }

    @Override
    public boolean allowHardwareKey(int keyCode, boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowHardwareKey(keyCode, allow);
    }

    @Override
    public boolean allowLocationService(boolean enable) {
        return PolicyManager.getLocationPolicyProxy().allowLocationService(enable);
    }

    @Override
    public boolean allowMultiWindowMode(boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowMultiWindowMode(allow);
    }

    @Override
    public boolean allowMultipleUsers(boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowMultipleUsers(allow);
    }

    @Override
    public boolean allowOTAUpgrade(boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowOTAUpgrade(allow);
    }

    @Override
    public boolean allowSafeMode(boolean allow) {
        return PolicyManager.getRestrictionPolicyProxy().allowSafeMode(allow);
    }

    @Override
    public boolean applyRuntimePermissions(String packageName, List<String> runtimePermissions) {
        String str = packageName;
        boolean z = true;
        if (!(str == null || str.length() == 0)) {
            List<String> list = runtimePermissions;
            if (list != null && !list.isEmpty()) {
                z = false;
            }
            if (!z) {
                return PolicyManager.getApplicationPolicyProxy().applyRuntimePermissions(packageName, runtimePermissions);
            }
        }
        return false;
    }

    @Override
    public boolean cleanAppDomainRules() {
        return PolicyManager.getFirewallPolicyProxy().cleanDomainRules();
    }

    @Override
    public boolean cleanFirewallApps() {
        return PolicyManager.getFirewallPolicyProxy().cleanAppRules();
    }

    @Override
    public void clearBootingAnimation() {
        PolicyManager.getDevicePolicyProxy().clearBootingAnimation();
    }

    @Override
    public boolean clearIgnoreFrequentRelaunchAppList() {
        return PolicyManager.getApplicationPolicyProxy().clearIgnoreFrequentRelaunchAppList();
    }

    @Override
    public boolean clearPackagesFromNotificationList() {
        return PolicyManager.getApplicationPolicyProxy().clearPackagesFromNotificationList();
    }

    @Override
    public boolean clearRuntimePermissionFixAppList() {
        return PolicyManager.getApplicationPolicyProxy().clearRuntimePermissionFixAppList();
    }

    @Override
    public void clearShuttingDownAnimation() {
        PolicyManager.getDevicePolicyProxy().clearShuttingDownAnimation();
    }

    @Override
    public void controlAirplaneMode(boolean isOn) {
        PolicyManager.getSettingPolicyProxy().controlAirplaneMode(isOn);
    }

    @Override
    public void controlChangeHome(boolean isEnable) {
        PolicyManager.getApplicationPolicyProxy().controlChangeHome(isEnable);
    }

    @Override
    public void controlEyeMode(boolean isOn) {
        PolicyManager.getSettingPolicyProxy().controlEyeMode(isOn);
    }

    @Override
    public void controlRingerMode(int mode) {
        PolicyManager.getSettingPolicyProxy().controlRingerMode(mode != 0 ? mode != 1 ? RingerMode.Normal.INSTANCE : RingerMode.Normal.INSTANCE : RingerMode.Mute.INSTANCE);
    }

    @Override
    public void controlSettingsTopLevelMenu(String keyList) {
        Intrinsics.checkNotNullParameter(keyList, "keyList");
        PolicyManager.getSettingPolicyProxy().controlSettingsTopLevelMenu(keyList);
    }

    @Override
    public boolean controlShowDeprecatedTarget(boolean enable) {
        return PolicyManager.getDevicePolicyProxy().controlShowDeprecatedTarget(enable);
    }

    @Override
    public void deactivateDevice() {
        PolicyManager.INSTANCE.unLock();
    }

    @Override
    public void dumpAppUsageEventsSnapshot(final long beginTime, final long endTime) {
        if (!AppUsageHelper.INSTANCE.checkAppUsagePermission(this.context) || Build.VERSION.SDK_INT < 26) {
            return;
        }
        ThreadPlugins.runInIoThread(new Runnable() {
            @Override
            public final void run() {
                MdmBinder.m217dumpAppUsageEventsSnapshot$lambda21(this.f$0, beginTime, endTime);
            }
        });
    }

    @Override
    public List<String> getAppAlwaysRunningList() {
        return PolicyManager.getApplicationPolicyProxy().getAppAlwaysRunningList();
    }

    @Override
    public List<String> getAppAutoRunningList() {
        return PolicyManager.getApplicationPolicyProxy().getAppAutoRunningList();
    }

    @Override
    public List<AppUsage> getAppUsage(long beginTime, long endTime) {
        Map<String, Long> appUsage = PolicyManager.getApplicationPolicyProxy().getAppUsage(beginTime, endTime);
        if (appUsage == null) {
            appUsage = MapsKt.emptyMap();
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Long> entry : appUsage.entrySet()) {
            arrayList.add(new AppUsage(entry.getKey(), entry.getValue().longValue()));
        }
        return arrayList;
    }

    @Override
    public List<AppUsage2> getAppUsageV2(long beginTime, long endTime) {
        return PolicyManager.getApplicationPolicyProxy().getAppUsageV2(beginTime, endTime);
    }

    @Override
    public List<NetworkStatsBean> getApplicationNetworkStats() {
        return JsonUtil.parseJsonToList(JsonUtil.objToJson(PolicyManager.getWifiPolicyProxy().getApplicationNetworkStats()), new TypeToken<List<? extends NetworkStatsBean>>() {
        }.getType());
    }

    @Override
    public boolean getAutomaticTime() {
        return PolicyManager.getDateTimePolicyProxy().getAutomaticTime();
    }

    @Override
    public String getDeviceMacAddress() {
        return PolicyManager.getDevicePolicyProxy().getDeviceMacAddress();
    }

    @Override
    public List<String> getIgnoreFrequentRelaunchAppList() {
        return PolicyManager.getApplicationPolicyProxy().getIgnoreFrequentRelaunchAppList();
    }

    @Override
    public String getImei() {
        return PolicyManager.getDevicePolicyProxy().getImei();
    }

    @Override
    public int getIntSettingsSystemValue(int mode, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((Number) PolicyManager.getSettingPolicyProxy().getSettingsSystemValue(mode, key, 0)).intValue();
    }

    @Override
    public long getLongSettingsSystemValue(int mode, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((Number) PolicyManager.getSettingPolicyProxy().getSettingsSystemValue(mode, key, 0L)).longValue();
    }

    @Override
    public List<String> getRuntimePermissionFixAppList() {
        return PolicyManager.getApplicationPolicyProxy().getRuntimePermissionFixAppList();
    }

    @Override
    public String getSerialNumber() {
        return PolicyManager.getDevicePolicyProxy().getSerialNumber();
    }

    @Override
    public String getSimSerialNumber() {
        return PolicyManager.getDevicePolicyProxy().getSimSerialNumber();
    }

    @Override
    public String getStringSettingsSystemValue(int mode, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (String) PolicyManager.getSettingPolicyProxy().getSettingsSystemValue(mode, key, "");
    }

    @Override
    public String getTopAppPackageName() {
        String topAppPackageName = PolicyManager.getApplicationPolicyProxy().getTopAppPackageName();
        return topAppPackageName == null ? "" : topAppPackageName;
    }

    @Override
    public float gettFloatSettingsSystemValue(int mode, String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return ((Number) PolicyManager.getSettingPolicyProxy().getSettingsSystemValue(mode, key, Float.valueOf(0.0f))).floatValue();
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        PackageInfo packageArchiveInfo;
        String str = apkFilePath;
        if ((str == null || str.length() == 0) || (packageArchiveInfo = AppUtil.getPackageArchiveInfo(App.INSTANCE.getInstance(), apkFilePath)) == null) {
            return false;
        }
        if (ZtyClientUtil.INSTANCE.isZtyClient(packageArchiveInfo.applicationInfo.packageName)) {
            SessionData.INSTANCE.setZtyUpdating(true);
        }
        boolean zInstallApplication = PolicyManager.getApplicationPolicyProxy().installApplication(apkFilePath);
        if (zInstallApplication) {
            ThreadPlugins.runInWorkThread(new Runnable() {
                @Override
                public final void run() {
                    MdmBinder.m218installApplication$lambda3();
                }
            }, 60000L);
        } else {
            SessionData.INSTANCE.setZtyUpdating(false);
        }
        return zInstallApplication;
    }

    @Override
    public boolean isBluetoothEnabled() {
        return PolicyManager.getSettingPolicyProxy().isBluetoothEnabled();
    }

    @Override
    public boolean isClipboardAllowed() {
        return PolicyManager.getRestrictionPolicyProxy().isClipboardAllowed();
    }

    @Override
    public boolean isDeviceAdminActivated() {
        return DeviceActiveManager.INSTANCE.isDeviceActivated(this.context);
    }

    @Override
    public boolean isExternalStorageEnable() {
        return PolicyManager.getRestrictionPolicyProxy().isSdCardEnabled();
    }

    @Override
    public boolean isEyeComfortTurnedOn() {
        return PolicyManager.getSettingPolicyProxy().isEyeComfortTurnedOn();
    }

    @Override
    public boolean isFactoryResetAllowed() {
        return PolicyManager.getRestrictionPolicyProxy().isFactoryResetAllowed();
    }

    @Override
    public boolean isFirmwareRecoveryAllowed() {
        return PolicyManager.getRestrictionPolicyProxy().isFirmwareRecoveryAllowed();
    }

    @Override
    public boolean isFreeLicenseActivated() {
        return PolicyManager.getLicensePolicyProxy().isFreeLicenseActivated();
    }

    @Override
    public boolean isHardwareKeyAllow(int keyCode) {
        return PolicyManager.getRestrictionPolicyProxy().isHardwareKeyAllow(keyCode);
    }

    @Override
    public boolean isMTPAvailable() {
        return PolicyManager.getRestrictionPolicyProxy().isMTPAvailable();
    }

    @Override
    public boolean isMdmSupport() {
        return getDeviceSupport().isSDKSupport();
    }

    @Override
    public boolean isOsVersionRemoteValid() {
        return SessionData.INSTANCE.isOsVersionRemoteValid();
    }

    @Override
    public boolean isPayLicenseActivated() {
        return PolicyManager.getLicensePolicyProxy().isPayLicenseActivated();
    }

    @Override
    public boolean isRandomMacDisabled() {
        return PolicyManager.getWifiPolicyProxy().isRandomMacDisabled();
    }

    @Override
    public boolean isUsbDebuggingEnabled() {
        return PolicyManager.getRestrictionPolicyProxy().isUsbDebuggingEnabled();
    }

    @Override
    public void lockScreen() {
        DeviceManager.INSTANCE.lockNow(BaseApplication.INSTANCE.getInstance());
    }

    @Override
    public boolean multipleUsersAllowed() {
        return PolicyManager.getRestrictionPolicyProxy().multipleUsersAllowed();
    }

    @Override
    public void openSoftUpdateView() {
        PolicyManager.getSettingPolicyProxy().openSoftUpdateView();
    }

    @Override
    public boolean putFloatSettingsSystemValue(int mode, String key, float value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return PolicyManager.getSettingPolicyProxy().putSettingsSystemValue(mode, key, Float.valueOf(value));
    }

    @Override
    public boolean putIntSettingsSystemValue(int mode, String key, int value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return PolicyManager.getSettingPolicyProxy().putSettingsSystemValue(mode, key, Integer.valueOf(value));
    }

    @Override
    public boolean putLongSettingsSystemValue(int mode, String key, long value) {
        Intrinsics.checkNotNullParameter(key, "key");
        return PolicyManager.getSettingPolicyProxy().putSettingsSystemValue(mode, key, Long.valueOf(value));
    }

    @Override
    public boolean putStringSettingsSystemValue(int mode, String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return PolicyManager.getSettingPolicyProxy().putSettingsSystemValue(mode, key, value);
    }

    @Override
    public TrafficInfo queryTrafficDetailBySummary(int networkType, String subscriberId, long beginTime, long endTime) {
        return PolicyManager.getApplicationPolicyProxy().queryTrafficDetailBySummary(networkType, subscriberId, beginTime, endTime);
    }

    @Override
    public TrafficInfo queryTrafficDetailByUid(int networkType, String subscriberId, long beginTime, long endTime, int uid) {
        return PolicyManager.getApplicationPolicyProxy().queryTrafficDetailByUid(networkType, subscriberId, beginTime, endTime, uid);
    }

    @Override
    public ParcelFileDescriptor readFileFromData(final String fileName) throws IOException {
        final ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
        ThreadPlugins.runInIoThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                MdmBinder.m220readFileFromData$lambda0(fileName, parcelFileDescriptorArrCreatePipe);
            }
        });
        return parcelFileDescriptorArrCreatePipe[0];
    }

    @Override
    public void reboot() {
        PolicyManager.getDevicePolicyProxy().reboot();
    }

    @Override
    public boolean removeAppAlwaysRunningList(List<String> packageList) {
        if (packageList != null) {
            return PolicyManager.getApplicationPolicyProxy().removeAppAlwaysRunningList(packageList);
        }
        return true;
    }

    @Override
    public boolean removeAppAutoRunningList(List<String> packageList) {
        if (packageList != null) {
            return PolicyManager.getApplicationPolicyProxy().removeAppAutoRunningList(packageList);
        }
        return true;
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        if (packageName == null || className == null) {
            return true;
        }
        return PolicyManager.getApplicationPolicyProxy().removeDefaultLauncher(packageName, className);
    }

    @Override
    public void removeDevicePassword() {
        DevicePasswordManager devicePasswordManager = DevicePasswordManager.INSTANCE;
        App app = this.context;
        devicePasswordManager.resetPassword(app, DeviceManager.getAdminReceiver(app));
    }

    @Override
    public boolean removeIgnoreFrequentRelaunchAppList(List<String> packageNames) {
        List<String> list = packageNames;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return PolicyManager.getApplicationPolicyProxy().removeIgnoreFrequentRelaunchAppList(packageNames);
    }

    @Override
    public boolean removeLockScreen() {
        return PolicyManager.getSettingPolicyProxy().removeLockScreen();
    }

    @Override
    public boolean removePackagesFromNotificationWhiteList(List<String> packageList) {
        if (packageList != null) {
            return PolicyManager.getApplicationPolicyProxy().removePackagesFromNotificationWhiteList(packageList);
        }
        return true;
    }

    @Override
    public boolean removeRuntimePermissionFixAppList(List<String> packageNames) {
        List<String> list = packageNames;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return PolicyManager.getApplicationPolicyProxy().removeRuntimePermissionFixAppList(packageNames);
    }

    @Override
    public boolean resetFactory(String token) throws InterruptedException {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        WipeDataActivity.INSTANCE.setWipeDataListener(new WipeDataActivity.WipeDataListener() {
            @Override
            public void onResult(boolean success) {
                booleanRef.element = success;
                countDownLatch.countDown();
            }
        });
        ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, this.context, new Intent(this.context, (Class<?>) WipeDataActivity.class), 0, 4, null);
        countDownLatch.await(60000L, TimeUnit.MILLISECONDS);
        return booleanRef.element;
    }

    @Override
    public boolean resetLocalActivateState() {
        return PolicyManager.getLicensePolicyProxy().resetLocalActivateState();
    }

    @Override
    public boolean resetNetworkSetting() {
        PolicyManager.getLocationPolicyProxy().startGPS(true);
        return PolicyManager.getWifiPolicyProxy().resetNetworkSetting();
    }

    @Override
    public String runBugReport(String filePath) {
        String str = filePath;
        return str == null || str.length() == 0 ? "" : PolicyManager.getDevicePolicyProxy().runBugReport(filePath);
    }

    @Override
    public boolean setApplicationComponentState(ComponentName cmpName, boolean state) {
        if (cmpName != null) {
            return PolicyManager.getApplicationPolicyProxy().setApplicationComponentState(cmpName, state);
        }
        return false;
    }

    @Override
    public void setApplicationState(String packageName, boolean enable) {
        if (packageName != null) {
            if (enable) {
                PolicyManager.getApplicationPolicyProxy().setEnableApplication(packageName);
            } else {
                PolicyManager.getApplicationPolicyProxy().setDisableApplication(packageName);
            }
        }
    }

    @Override
    public void setApplicationUninstalltionState(String packageName, boolean state) {
        if (packageName != null) {
            if (state) {
                PolicyManager.getApplicationPolicyProxy().setApplicationUninstallationEnabled(packageName);
            } else {
                PolicyManager.getApplicationPolicyProxy().setApplicationUninstallationDisabled(packageName);
            }
        }
    }

    @Override
    public boolean setAutomaticTime(boolean enable) {
        return PolicyManager.getDateTimePolicyProxy().setAutomaticTime(enable);
    }

    @Override
    public boolean setBixbyShortcutEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setBixbyShortcutEnabled(enable);
    }

    @Override
    public void setBluetoothEnable(boolean enable) {
        PolicyManager.getSettingPolicyProxy().setBluetoothEnable(enable);
    }

    @Override
    public void setBootingAnimation(String animationFile, String loopFile, String soundFile, long delay) {
        String str = animationFile;
        if (str == null || str.length() == 0) {
            return;
        }
        String str2 = loopFile;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        String str3 = soundFile;
        if (str3 == null || str3.length() == 0) {
            return;
        }
        PolicyManager.getDevicePolicyProxy().setBootingAnimation(new File(animationFile), new File(loopFile), new File(soundFile), delay);
    }

    @Override
    public boolean setCameraShortcutEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setCameraShortcutEnabled(enable);
    }

    @Override
    public boolean setCameraState(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setCameraState(enable);
    }

    @Override
    public boolean setClipboardEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setClipboardEnabled(enable);
    }

    @Override
    public boolean setDateTimeChangeEnabled(boolean isEnable) {
        return PolicyManager.getDateTimePolicyProxy().setDateTimeChangeEnabled(isEnable);
    }

    @Override
    public void setDefaultHome(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        PolicyManager.getApplicationPolicyProxy().setDefaultHome(packageName, className);
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        if (packageName == null || className == null) {
            return false;
        }
        return PolicyManager.getApplicationPolicyProxy().setDefaultLauncher(packageName, className);
    }

    @Override
    public boolean setDevelopHiddenState(boolean state) {
        return PolicyManager.getSettingPolicyProxy().setDevelopHiddenState(state);
    }

    @Override
    public boolean setDropMenuHiddenState(boolean state) {
        return PolicyManager.getSettingPolicyProxy().setDropMenuHiddenState(state);
    }

    @Override
    public boolean setEmergencyCallOnly(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setEmergencyCallOnly(enable);
    }

    @Override
    public boolean setFileShareDisabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setFileShareDisabled(enable);
    }

    @Override
    public boolean setInputMethod(String inputMethodClassName) {
        if (inputMethodClassName != null) {
            return PolicyManager.getRestrictionPolicyProxy().setInputMethod(inputMethodClassName);
        }
        return false;
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        return PolicyManager.getFirewallPolicyProxy().setIptablesOption(status);
    }

    @Override
    public boolean setKanbanEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setKanbanEnabled(enable);
    }

    @Override
    public boolean setLockScreenItems(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setLockScreenItems(enable);
    }

    @Override
    public boolean setMTPEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setMTPEnabled(enable);
    }

    @Override
    public boolean setMobileDataState(boolean state) {
        return PolicyManager.getSettingPolicyProxy().setMobileDataState(state);
    }

    @Override
    public boolean setNetworkSpeedState(boolean state) {
        return PolicyManager.getWifiPolicyProxy().setNetworkSpeedState(state);
    }

    @Override
    public void setPowerSavingMode(int mode) {
        PowerMode.SavingOff savingOff = (mode == 0 || mode != 1) ? PowerMode.SavingOff.INSTANCE : PowerMode.SavingOn.INSTANCE;
        PolicyManager.getSettingPolicyProxy().setPowerSavingMode(savingOff);
    }

    @Override
    public boolean setRandomMacDisabled(boolean enable) {
        return PolicyManager.getWifiPolicyProxy().setRandomMacDisabled(enable);
    }

    @Override
    public boolean setScreenCapture(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setScreenCapture(enable);
    }

    @Override
    public boolean setSdCardState(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setSdCardState(enable);
    }

    @Override
    public boolean setSettingEnabled(boolean enable) {
        return enable ? PolicyManager.getSettingPolicyProxy().setSettingEnabled() : PolicyManager.getSettingPolicyProxy().setSettingDisabled();
    }

    @Override
    public boolean setSettingsHiddenState(boolean state) {
        if (getDeviceSupport().isLenovoSupport()) {
            PolicyManager.getDevicePolicyProxy().clipSystemWifiDetail(!state);
        }
        return PolicyManager.getSettingPolicyProxy().setSettingsHiddenState(state);
    }

    @Override
    public void setShuttingDownAnimation(String animationFile, String soundFile) {
        String str = animationFile;
        if ((str == null || str.length() == 0) || soundFile == null) {
            return;
        }
        PolicyManager.getDevicePolicyProxy().setShuttingDownAnimation(new File(animationFile), new File(soundFile));
    }

    @Override
    public boolean setSpenPointImageState(boolean state) {
        return PolicyManager.getDevicePolicyProxy().setSpenPointImageState(state);
    }

    @Override
    public void setStatusBarButtonState(Map<Object, Object> map) {
        try {
            SettingPolicyProxy settingPolicyProxy = PolicyManager.getSettingPolicyProxy();
            if (map == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.Boolean>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.Boolean> }");
            }
            settingPolicyProxy.setStatusBarButtonState((HashMap) map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override
    public boolean setStreamVolume(int volume, int streamType, int flags) {
        return PolicyManager.getSettingPolicyProxy().setStreamVolume(volume, streamType, flags);
    }

    @Override
    public boolean setSwitchInputMethodEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setSwitchInputMethodEnabled(enable);
    }

    @Override
    public boolean setSystemTime(long timeMillis) {
        return PolicyManager.getDateTimePolicyProxy().setSystemTime(new Date(timeMillis));
    }

    @Override
    public boolean setTaskBarEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setTaskBarEnabled(enable);
    }

    @Override
    public boolean setTimeZone(String timeZoneId) {
        if (timeZoneId != null) {
            return PolicyManager.getDateTimePolicyProxy().setTimeZone(timeZoneId);
        }
        return false;
    }

    @Override
    public boolean setUsbDebuggingEnabled(boolean enable) {
        return PolicyManager.getRestrictionPolicyProxy().setUsbDebuggingEnabled(enable);
    }

    @Override
    public boolean setWiFiState(boolean enable) {
        return PolicyManager.getWifiPolicyProxy().setWiFiState(enable);
    }

    @Override
    public void shoutdown() {
        PolicyManager.getDevicePolicyProxy().shoutdown();
    }

    @Override
    public boolean startGPS(boolean start) {
        return PolicyManager.getLocationPolicyProxy().startGPS(start);
    }

    @Override
    public void startSoftUpdate(int hour, int minute) {
        PolicyManager.getSettingPolicyProxy().startSoftUpdate(hour, minute);
    }

    @Override
    public boolean startTcpdump(String outPath) {
        if (outPath != null) {
            return PolicyManager.getDevicePolicyProxy().startTcpDump(outPath);
        }
        return false;
    }

    @Override
    public boolean stopApp(String packageName) {
        if (packageName != null) {
            return PolicyManager.getApplicationPolicyProxy().stopApp(packageName);
        }
        return false;
    }

    @Override
    public boolean stopTcpdump() {
        return PolicyManager.getDevicePolicyProxy().stopTcpDump();
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        String str = packageName;
        if (str == null || str.length() == 0) {
            return false;
        }
        if (AppUtil.isInstalled(this.context, packageName)) {
            return PolicyManager.getApplicationPolicyProxy().uninstallApplication(packageName);
        }
        return true;
    }

    @Override
    public void unlockScreen() {
        DeviceManager.INSTANCE.unlock(BaseApplication.INSTANCE.getInstance());
    }

    @Override
    public boolean verifyDynamicCode(String key, String code, int tokenType) {
        boolean zVerify;
        try {
            String str = key;
            boolean z = true;
            if (str == null || str.length() == 0) {
                return false;
            }
            String str2 = code;
            if (str2 != null && str2.length() != 0) {
                z = false;
            }
            if (z) {
                return false;
            }
            if (StringsKt.startsWith$default(code, "@", false, 2, (Object) null) && StringsKt.endsWith$default(code, "@", false, 2, (Object) null)) {
                zVerify = TotpUtils.verify(key, StringsKt.replace$default(code, "@", "", false, 4, (Object) null), 3600);
            } else {
                Base32 base32 = new Base32();
                byte[] bytes = key.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                byte[] bArrEncode = base32.encode(bytes);
                Intrinsics.checkNotNullExpressionValue(bArrEncode, "Base32().encode(key.toByteArray())");
                zVerify = TotpUtils.verify(new String(bArrEncode, Charsets.UTF_8), code, 900, 8);
            }
            return zVerify;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override
    public boolean verifyDynamicToken(String token, int tokenType) {
        if (tokenType == 0) {
            return SecretJni.getInstance().verifyDevelopToken(BaseApplication.INSTANCE.getInstance(), token, null);
        }
        if (tokenType != 1) {
            return false;
        }
        return SecretJni.getInstance().verifyQrToken(BaseApplication.INSTANCE.getInstance(), token);
    }

    @Override
    public boolean wipeApplicationData(String packageName) {
        if (packageName != null) {
            return PolicyManager.getApplicationPolicyProxy().wipeApplicationData(packageName);
        }
        return false;
    }

    @Override
    public boolean wipeRecentTasks() {
        return PolicyManager.getRestrictionPolicyProxy().wipeRecentTasks();
    }
}
