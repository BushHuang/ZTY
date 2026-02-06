package com.xh.xhcore.common.http.strategy.xh.upload.domain;

import android.text.TextUtils;
import com.xh.networkclient.common.FileMD5;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.upload.bean.UploadFileEntity;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.aksk.OnOsAkSkCallback;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.aksk.OsDomainAkSkUtil;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsResponse;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsUploadConfig;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.OsUploadFilesBean;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.exception.OsException;
import com.xh.xhcore.common.obs.ObsUploadHelper;
import com.xh.xhcore.common.oss.OssUploadHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jq\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042K\u0010\n\u001aG\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\b0\u000b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\b0\u0012H\u0002J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0004J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u0018\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/OsTypeUploadHelper;", "", "()V", "TAG", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "getAkSk", "", "domain", "onSuccessBlock", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "ak", "sk", "token", "onErrorBlock", "Lkotlin/Function1;", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/exception/OsException;", "getFileMd5", "path", "uploadFile", "uploadConfig", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/OsUploadConfig;", "callback", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/OnOsUploadCallback;", "uploadFiles", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OsTypeUploadHelper {
    public static final OsTypeUploadHelper INSTANCE = new OsTypeUploadHelper();
    private static final String TAG = "OsTypeUploadHelper";
    private static final ExecutorService executorService;

    static {
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewCachedThreadPool, "newCachedThreadPool()");
        executorService = executorServiceNewCachedThreadPool;
    }

    private OsTypeUploadHelper() {
    }

    private final void getAkSk(final String domain, final Function3<? super String, ? super String, ? super String, Unit> onSuccessBlock, final Function1<? super OsException, Unit> onErrorBlock) {
        OsDomainAkSkUtil.getAkSk(domain, new OnOsAkSkCallback() {
            @Override
            public void failed(int code, String msg) {
                LogUtils.Companion.e$default(LogUtils.INSTANCE, "OsTypeUploadHelper", "failed: code = " + code + " msg = " + ((Object) msg), null, 4, null);
                OsException.Companion companion = OsException.INSTANCE;
                if (msg == null) {
                    msg = Intrinsics.stringPlus("AKSK获取失败, domain=", domain);
                }
                onErrorBlock.invoke(companion.fromOsException(code, msg));
            }

            @Override
            public void success(String ak, String sk, String token) {
                Intrinsics.checkNotNullParameter(ak, "ak");
                Intrinsics.checkNotNullParameter(sk, "sk");
                Intrinsics.checkNotNullParameter(token, "token");
                onSuccessBlock.invoke(ak, sk, token);
            }
        });
    }

    public final String getFileMd5(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return FileMD5.getMD5(new File(path));
    }

    public final void uploadFile(final OsUploadConfig uploadConfig, final OnOsUploadCallback callback) {
        Intrinsics.checkNotNullParameter(uploadConfig, "uploadConfig");
        String domain = uploadConfig.getDomain();
        final UploadFileEntity uploadFileEntity = (UploadFileEntity) CollectionsKt.first((List) uploadConfig.getUploadFileEntities());
        final String str = uploadConfig.getBuckets().get(uploadFileEntity.getLocalPath());
        if (str == null) {
            return;
        }
        getAkSk(domain, new Function3<String, String, String, Unit>() {
            {
                super(3);
            }

            @Override
            public Unit invoke(String str2, String str3, String str4) {
                invoke2(str2, str3, str4);
                return Unit.INSTANCE;
            }

            public final void invoke2(String str2, String str3, String str4) {
                Intrinsics.checkNotNullParameter(str2, "ak");
                Intrinsics.checkNotNullParameter(str3, "sk");
                Intrinsics.checkNotNullParameter(str4, "token");
                String osType = uploadConfig.getOsType();
                if (TextUtils.equals(osType, "1")) {
                    new ObsUploadHelper(OsTypeUploadHelper.executorService).uploadObsFile(uploadFileEntity.getLocalPath(), str2, str3, str4, uploadConfig.getEndpoint(), str, uploadConfig.getExtraPath(), uploadFileEntity.transformUploadFileExtraPath(), uploadConfig.getFileSecurityMap(), callback);
                    return;
                }
                if (TextUtils.equals(osType, "0")) {
                    new OssUploadHelper().uploadOssFile(uploadFileEntity.getLocalPath(), str2, str3, str4, uploadConfig.getEndpoint(), str, uploadConfig.getExtraPath(), uploadFileEntity.transformUploadFileExtraPath(), uploadConfig.getFileSecurityMap(), uploadConfig.getFileSecurityConfigMap(), callback);
                    return;
                }
                OsException osExceptionFromOsException = OsException.INSTANCE.fromOsException(107005001, "上传类型错误");
                OnOsUploadCallback onOsUploadCallback = callback;
                if (onOsUploadCallback == null) {
                    return;
                }
                onOsUploadCallback.onError(osExceptionFromOsException);
            }
        }, new Function1<OsException, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(OsException osException) {
                invoke2(osException);
                return Unit.INSTANCE;
            }

            public final void invoke2(OsException osException) {
                Intrinsics.checkNotNullParameter(osException, "it");
                OnOsUploadCallback onOsUploadCallback = callback;
                if (onOsUploadCallback == null) {
                    return;
                }
                onOsUploadCallback.onError(osException);
            }
        });
    }

    public final void uploadFiles(final OsUploadConfig uploadConfig, final OnOsUploadCallback callback) {
        Intrinsics.checkNotNullParameter(uploadConfig, "uploadConfig");
        String domain = uploadConfig.getDomain();
        final List<UploadFileEntity> uploadFileEntities = uploadConfig.getUploadFileEntities();
        final Map<String, String> buckets = uploadConfig.getBuckets();
        final Map<String, Boolean> fileSecurityMap = uploadConfig.getFileSecurityMap();
        getAkSk(domain, new Function3<String, String, String, Unit>() {
            {
                super(3);
            }

            @Override
            public Unit invoke(String str, String str2, String str3) {
                invoke2(str, str2, str3);
                return Unit.INSTANCE;
            }

            public final void invoke2(String str, String str2, String str3) {
                OnOsUploadCallback onOsUploadCallback;
                OsUploadConfig osUploadConfig;
                Map<String, Boolean> map;
                Map<String, String> map2;
                List<UploadFileEntity> list;
                Intrinsics.checkNotNullParameter(str, "ak");
                Intrinsics.checkNotNullParameter(str2, "sk");
                Intrinsics.checkNotNullParameter(str3, "token");
                final Ref.LongRef longRef = new Ref.LongRef();
                final Ref.LongRef longRef2 = new Ref.LongRef();
                final LinkedHashMap linkedHashMap = new LinkedHashMap();
                Iterator<T> it = uploadFileEntities.iterator();
                while (it.hasNext()) {
                    String localPath = ((UploadFileEntity) it.next()).getLocalPath();
                    longRef.element += new File(localPath).length();
                    linkedHashMap.put(localPath, null);
                }
                final Ref.IntRef intRef = new Ref.IntRef();
                final Ref.IntRef intRef2 = new Ref.IntRef();
                String osType = uploadConfig.getOsType();
                List<UploadFileEntity> list2 = uploadFileEntities;
                Map<String, String> map3 = buckets;
                Map<String, Boolean> map4 = fileSecurityMap;
                OsUploadConfig osUploadConfig2 = uploadConfig;
                OnOsUploadCallback onOsUploadCallback2 = callback;
                for (UploadFileEntity uploadFileEntity : list2) {
                    String localPath2 = uploadFileEntity.getLocalPath();
                    String str4 = map3.get(localPath2);
                    String str5 = str4;
                    if (str5 == null || str5.length() == 0) {
                        onOsUploadCallback = onOsUploadCallback2;
                        osUploadConfig = osUploadConfig2;
                        map = map4;
                        map2 = map3;
                        list = list2;
                    } else {
                        final List<UploadFileEntity> list3 = list2;
                        final OnOsUploadCallback onOsUploadCallback3 = onOsUploadCallback2;
                        onOsUploadCallback = onOsUploadCallback2;
                        osUploadConfig = osUploadConfig2;
                        OnOsUploadCallback onOsUploadCallback4 = new OnOsUploadCallback() {
                            @Override
                            public void onError(OsException e) {
                                Intrinsics.checkNotNullParameter(e, "e");
                                LinkedHashMap<String, OsUploadFilesBean> linkedHashMap2 = linkedHashMap;
                                Ref.IntRef intRef3 = intRef;
                                Ref.IntRef intRef4 = intRef2;
                                List<UploadFileEntity> list4 = list3;
                                OnOsUploadCallback onOsUploadCallback5 = onOsUploadCallback3;
                                synchronized (linkedHashMap2) {
                                    intRef3.element++;
                                    if (intRef3.element + intRef4.element == list4.size() && onOsUploadCallback5 != null) {
                                        onOsUploadCallback5.onError(e);
                                    }
                                    Unit unit = Unit.INSTANCE;
                                }
                            }

                            @Override
                            public void onProgress(double current, double total, String speed) {
                                LinkedHashMap<String, OsUploadFilesBean> linkedHashMap2 = linkedHashMap;
                                Ref.LongRef longRef3 = longRef2;
                                OnOsUploadCallback onOsUploadCallback5 = onOsUploadCallback3;
                                Ref.LongRef longRef4 = longRef;
                                synchronized (linkedHashMap2) {
                                    longRef3.element += (long) current;
                                    if (onOsUploadCallback5 != null) {
                                        onOsUploadCallback5.onProgress(longRef3.element, longRef4.element, speed);
                                        Unit unit = Unit.INSTANCE;
                                    }
                                }
                            }

                            @Override
                            public void onSuccess(OsResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                LinkedHashMap<String, OsUploadFilesBean> linkedHashMap2 = linkedHashMap;
                                Ref.IntRef intRef3 = intRef2;
                                List<UploadFileEntity> list4 = list3;
                                OnOsUploadCallback onOsUploadCallback5 = onOsUploadCallback3;
                                synchronized (linkedHashMap2) {
                                    intRef3.element++;
                                    String url = response.getUrl();
                                    LinkedHashMap<String, OsUploadFilesBean> linkedHashMap3 = linkedHashMap2;
                                    String path = response.getPath();
                                    OsUploadFilesBean.Companion companion = OsUploadFilesBean.INSTANCE;
                                    Boolean boolIsPrivate = response.isPrivate();
                                    String eTag = response.getETag();
                                    if (eTag == null) {
                                        eTag = "";
                                    }
                                    linkedHashMap3.put(path, companion.toOsUploadFilesBean(url, boolIsPrivate, eTag));
                                    LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                                    for (Map.Entry<String, OsUploadFilesBean> entry : linkedHashMap2.entrySet()) {
                                        if (entry.getValue() != null) {
                                            linkedHashMap4.put(entry.getKey(), entry.getValue());
                                        }
                                    }
                                    if (linkedHashMap4.size() == list4.size()) {
                                        ArrayList arrayList = new ArrayList();
                                        for (OsUploadFilesBean osUploadFilesBean : linkedHashMap4.values()) {
                                            if (osUploadFilesBean != null) {
                                                arrayList.add(osUploadFilesBean);
                                            }
                                        }
                                        response.setUploadFilesBean(arrayList);
                                        if (onOsUploadCallback5 != null) {
                                            onOsUploadCallback5.onSuccess(response);
                                        }
                                    }
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        };
                        if (map4.get(localPath2) == null) {
                            map4.put(localPath2, true);
                        }
                        String str6 = osType;
                        if (TextUtils.equals(str6, "1")) {
                            map = map4;
                            map2 = map3;
                            list = list2;
                            new ObsUploadHelper(OsTypeUploadHelper.executorService).uploadObsFile(localPath2, str, str2, str3, osUploadConfig.getEndpoint(), str4, osUploadConfig.getExtraPath(), uploadFileEntity.transformUploadFileExtraPath(), map, onOsUploadCallback4);
                        } else {
                            map = map4;
                            map2 = map3;
                            list = list2;
                            if (TextUtils.equals(str6, "0")) {
                                new OssUploadHelper().uploadOssFile(localPath2, str, str2, str3, osUploadConfig.getEndpoint(), str4, osUploadConfig.getExtraPath(), uploadFileEntity.transformUploadFileExtraPath(), map, osUploadConfig.getFileSecurityConfigMap(), onOsUploadCallback4);
                            } else {
                                OsException osExceptionFromOsException = OsException.INSTANCE.fromOsException(107005001, "上传类型错误");
                                if (onOsUploadCallback != null) {
                                    onOsUploadCallback.onError(osExceptionFromOsException);
                                }
                            }
                        }
                    }
                    onOsUploadCallback2 = onOsUploadCallback;
                    osUploadConfig2 = osUploadConfig;
                    map4 = map;
                    map3 = map2;
                    list2 = list;
                }
            }
        }, new Function1<OsException, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(OsException osException) {
                invoke2(osException);
                return Unit.INSTANCE;
            }

            public final void invoke2(OsException osException) {
                Intrinsics.checkNotNullParameter(osException, "it");
                OnOsUploadCallback onOsUploadCallback = callback;
                if (onOsUploadCallback == null) {
                    return;
                }
                onOsUploadCallback.onError(osException);
            }
        });
    }
}
