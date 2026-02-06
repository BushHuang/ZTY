package com.oef.services;

import com.obs.log.ILogger;
import com.obs.log.InterfaceLogBean;
import com.obs.log.LoggerBuilder;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.ServiceException;
import com.obs.services.internal.utils.AccessLoggerUtils;
import com.obs.services.internal.utils.JSONChange;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.HeaderResponse;
import com.oef.services.model.CreateAsyncFetchJobsRequest;
import com.oef.services.model.CreateAsynchFetchJobsResult;
import com.oef.services.model.PutExtensionPolicyRequest;
import com.oef.services.model.QueryAsynchFetchJobsResult;
import com.oef.services.model.QueryExtensionPolicyResult;
import java.util.Date;

public class OefClient extends ObsClient implements IOefClient {
    private static final ILogger ILOG = LoggerBuilder.getLogger((Class<?>) OefClient.class);

    private abstract class ActionCallbackWithResult<T> {
        private ActionCallbackWithResult() {
        }

        abstract T action() throws ServiceException;

        void authTypeNegotiate(String str) throws ServiceException {
            OefClient.this.getProviderCredentials().setThreadLocalAuthType(OefClient.this.getApiVersion(str));
        }
    }

    public OefClient(ObsConfiguration obsConfiguration) {
        super(obsConfiguration);
    }

    public OefClient(String str) {
        super(str);
    }

    public OefClient(String str, String str2, ObsConfiguration obsConfiguration) {
        super(str, str2, obsConfiguration);
    }

    public OefClient(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public OefClient(String str, String str2, String str3, ObsConfiguration obsConfiguration) {
        super(str, str2, str3, obsConfiguration);
    }

    public OefClient(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    private <T> T doActionWithResult(String str, String str2, ActionCallbackWithResult<T> actionCallbackWithResult) throws ObsException {
        if (!isCname()) {
            ServiceUtils.asserParameterNotNull(str2, "bucketName is null");
        }
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean(str, getEndpoint(), "");
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (isAuthTypeNegotiation()) {
                    actionCallbackWithResult.authTypeNegotiate(str2);
                }
                T tAction = actionCallbackWithResult.action();
                interfaceLogBean.setRespTime(new Date());
                interfaceLogBean.setResultCode("0");
                if (ILOG.isInfoEnabled()) {
                    ILOG.info(interfaceLogBean);
                }
                if (ILOG.isInfoEnabled()) {
                    ILOG.info((CharSequence) ("ObsClient [" + str + "] cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms"));
                }
                return tAction;
            } catch (ServiceException e) {
                ObsException obsExceptionChangeFromServiceException = ServiceUtils.changeFromServiceException(e);
                if (obsExceptionChangeFromServiceException.getResponseCode() < 400 || obsExceptionChangeFromServiceException.getResponseCode() >= 500) {
                    if (!ILOG.isErrorEnabled()) {
                        throw obsExceptionChangeFromServiceException;
                    }
                    interfaceLogBean.setRespTime(new Date());
                    interfaceLogBean.setResultCode(String.valueOf(obsExceptionChangeFromServiceException.getResponseCode()));
                    ILOG.error(interfaceLogBean);
                    throw obsExceptionChangeFromServiceException;
                }
                if (!ILOG.isWarnEnabled()) {
                    throw obsExceptionChangeFromServiceException;
                }
                interfaceLogBean.setRespTime(new Date());
                interfaceLogBean.setResultCode(String.valueOf(e.getResponseCode()));
                ILOG.warn(interfaceLogBean);
                throw obsExceptionChangeFromServiceException;
            }
        } finally {
            if (isAuthTypeNegotiation()) {
                getProviderCredentials().removeThreadLocalAuthType();
            }
            AccessLoggerUtils.printLog();
        }
    }

    @Override
    public CreateAsynchFetchJobsResult createFetchJob(final CreateAsyncFetchJobsRequest createAsyncFetchJobsRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(createAsyncFetchJobsRequest.getBucketName(), "bucket is null");
        ServiceUtils.asserParameterNotNull(createAsyncFetchJobsRequest, "policy is null");
        ServiceUtils.asserParameterNotNull(createAsyncFetchJobsRequest.getUrl(), "url is null");
        if (createAsyncFetchJobsRequest.getCallBackUrl() != null) {
            ServiceUtils.asserParameterNotNull(createAsyncFetchJobsRequest.getCallBackBody(), "callbackbody is null when callbackurl is not null");
        }
        return (CreateAsynchFetchJobsResult) doActionWithResult("CreateFetchJob", createAsyncFetchJobsRequest.getBucketName(), new ActionCallbackWithResult<CreateAsynchFetchJobsResult>() {
            {
                super();
            }

            @Override
            public CreateAsynchFetchJobsResult action() throws ServiceException {
                return OefClient.this.createFetchJobImpl(createAsyncFetchJobsRequest.getBucketName(), JSONChange.objToJson(createAsyncFetchJobsRequest));
            }
        });
    }

    @Override
    public HeaderResponse deleteExtensionPolicy(final String str) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucket is null");
        return (HeaderResponse) doActionWithResult("deleteExtensionPolicy", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return OefClient.this.deleteExtensionPolicyImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse putExtensionPolicy(final String str, final PutExtensionPolicyRequest putExtensionPolicyRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucket is null");
        ServiceUtils.asserParameterNotNull(putExtensionPolicyRequest, "policy is null");
        if (putExtensionPolicyRequest.getCompress() == null && putExtensionPolicyRequest.getFetch() == null && putExtensionPolicyRequest.getTranscode() == null) {
            throw new IllegalArgumentException("putExtensionPolicy failed: compress, fetch and transcode cannot be empty at the same time");
        }
        return (HeaderResponse) doActionWithResult("putExtensionPolicy", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return OefClient.this.setExtensionPolicyImpl(str, JSONChange.objToJson(putExtensionPolicyRequest));
            }
        });
    }

    @Override
    public QueryExtensionPolicyResult queryExtensionPolicy(final String str) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucket is null");
        return (QueryExtensionPolicyResult) doActionWithResult("queryExtensionPolicy", str, new ActionCallbackWithResult<QueryExtensionPolicyResult>() {
            {
                super();
            }

            @Override
            public QueryExtensionPolicyResult action() throws ServiceException {
                return OefClient.this.queryExtensionPolicyImpl(str);
            }
        });
    }

    @Override
    public QueryAsynchFetchJobsResult queryFetchJob(final String str, final String str2) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucket is null");
        ServiceUtils.asserParameterNotNull(str2, "jobId is null");
        return (QueryAsynchFetchJobsResult) doActionWithResult("queryFetchJob", str, new ActionCallbackWithResult<QueryAsynchFetchJobsResult>() {
            {
                super();
            }

            @Override
            public QueryAsynchFetchJobsResult action() throws ServiceException {
                return OefClient.this.queryFetchJobImpl(str, str2);
            }
        });
    }
}
