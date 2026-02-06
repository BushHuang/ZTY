package com.xh.xhcore.common.http.strategy.xh.upload.domain;

import com.google.gson.Gson;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.DomainConfigList;
import com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.DomainObjectStorageConfig;
import com.xh.xhcore.common.oss.OssConfig;
import com.xuehai.provider.core.db.CPVDRemoteConfig;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J5\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00060\tH\u0007J-\u0010\u000e\u001a\u00020\u00062#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00060\tH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/OsDomainHelper;", "", "()V", "TAG", "", "findDomainConfig", "", "domain", "successAction", "Lkotlin/Function1;", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainObjectStorageConfig;", "Lkotlin/ParameterName;", "name", "config", "requestDomainConfig", "Lcom/xh/xhcore/common/http/strategy/xh/upload/domain/bean/DomainConfigList;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OsDomainHelper {
    public static final OsDomainHelper INSTANCE = new OsDomainHelper();
    private static final String TAG = "OsDomainHelper";

    private OsDomainHelper() {
    }

    @JvmStatic
    public static final void findDomainConfig(final String domain, final Function1<? super DomainObjectStorageConfig, Unit> successAction) {
        Object objM228constructorimpl;
        Object next;
        Object objFromJson;
        Intrinsics.checkNotNullParameter(domain, "domain");
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        try {
            Result.Companion companion = Result.INSTANCE;
            objFromJson = new Gson().fromJson(new JSONObject(CPVDRemoteConfig.getConfig("ObjectStorageConfig")).getString("ThirdPartyObjectStorageConfig"), (Class<Object>) DomainConfigList.class);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
        }
        if (objFromJson == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.xh.xhcore.common.http.strategy.xh.upload.domain.bean.DomainConfigList");
        }
        objM228constructorimpl = Result.m228constructorimpl((DomainConfigList) objFromJson);
        if (Result.m235isSuccessimpl(objM228constructorimpl)) {
            Iterator<T> it = ((DomainConfigList) objM228constructorimpl).getDomainObjectStorageConfigList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (Intrinsics.areEqual(((DomainObjectStorageConfig) next).getDomain(), domain)) {
                        break;
                    }
                }
            }
            DomainObjectStorageConfig domainObjectStorageConfig = (DomainObjectStorageConfig) next;
            if (domainObjectStorageConfig != null) {
                successAction.invoke(domainObjectStorageConfig);
            } else {
                requestDomainConfig(new Function1<DomainConfigList, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(DomainConfigList domainConfigList) {
                        invoke2(domainConfigList);
                        return Unit.INSTANCE;
                    }

                    public final void invoke2(DomainConfigList domainConfigList) {
                        List<DomainObjectStorageConfig> domainObjectStorageConfigList;
                        DomainObjectStorageConfig domainObjectStorageConfig2 = null;
                        if (domainConfigList != null && (domainObjectStorageConfigList = domainConfigList.getDomainObjectStorageConfigList()) != null) {
                            String str = domain;
                            Iterator<T> it2 = domainObjectStorageConfigList.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                Object next2 = it2.next();
                                if (Intrinsics.areEqual(((DomainObjectStorageConfig) next2).getDomain(), str)) {
                                    domainObjectStorageConfig2 = next2;
                                    break;
                                }
                            }
                            domainObjectStorageConfig2 = domainObjectStorageConfig2;
                        }
                        successAction.invoke(domainObjectStorageConfig2);
                    }
                });
            }
        }
        if (Result.m231exceptionOrNullimpl(objM228constructorimpl) == null) {
            return;
        }
        requestDomainConfig(new Function1<DomainConfigList, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(DomainConfigList domainConfigList) {
                invoke2(domainConfigList);
                return Unit.INSTANCE;
            }

            public final void invoke2(DomainConfigList domainConfigList) {
                List<DomainObjectStorageConfig> domainObjectStorageConfigList;
                DomainObjectStorageConfig domainObjectStorageConfig2 = null;
                if (domainConfigList != null && (domainObjectStorageConfigList = domainConfigList.getDomainObjectStorageConfigList()) != null) {
                    String str = domain;
                    Iterator<T> it2 = domainObjectStorageConfigList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        Object next2 = it2.next();
                        if (Intrinsics.areEqual(((DomainObjectStorageConfig) next2).getDomain(), str)) {
                            domainObjectStorageConfig2 = next2;
                            break;
                        }
                    }
                    domainObjectStorageConfig2 = domainObjectStorageConfig2;
                }
                successAction.invoke(domainObjectStorageConfig2);
            }
        });
    }

    @JvmStatic
    private static final void requestDomainConfig(final Function1<? super DomainConfigList, Unit> successAction) {
        ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestOkHttpProxy.createOkHttp().setUrl(Intrinsics.stringPlus(OssConfig.ALI_AK_SK_HOST, "/XHFileServer/config/domain-obj-storage"))).setExchangeToMainThread(false)).setMethod("GET")).addQueryParam("schoolIds", XhBaseApplication.getSchoolId()).setCallback(new XHRequestCallBack.HttpCallBack<String>() {
            @Override
            public void failed(int code, String msg) {
                LogUtils.Companion.e$default(LogUtils.INSTANCE, "OsDomainHelper", "code = " + code + "，msg = " + ((Object) msg), null, 4, null);
                Function1<DomainConfigList, Unit> function1 = successAction;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    function1.invoke(null);
                    Result.m228constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m228constructorimpl(ResultKt.createFailure(th));
                }
            }

            @Override
            public void success(String t) {
                Object objM228constructorimpl;
                if (t != null) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM228constructorimpl = Result.m228constructorimpl(DomainConfigList.INSTANCE.toDomainConfigList(t));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM228constructorimpl = Result.m228constructorimpl(ResultKt.createFailure(th));
                    }
                    Function1<DomainConfigList, Unit> function1 = successAction;
                    if (Result.m235isSuccessimpl(objM228constructorimpl)) {
                        function1.invoke((DomainConfigList) objM228constructorimpl);
                    }
                    Function1<DomainConfigList, Unit> function12 = successAction;
                    Throwable thM231exceptionOrNullimpl = Result.m231exceptionOrNullimpl(objM228constructorimpl);
                    if (thM231exceptionOrNullimpl == null) {
                        return;
                    }
                    function12.invoke(null);
                    LogUtils.Companion.e$default(LogUtils.INSTANCE, "OsDomainHelper", Intrinsics.stringPlus("error = ", thM231exceptionOrNullimpl.getMessage()), null, 4, null);
                }
            }
        })).request();
    }
}
