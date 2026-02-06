package com.oef.services;

import com.obs.services.exception.ObsException;
import com.obs.services.model.HeaderResponse;
import com.oef.services.model.CreateAsyncFetchJobsRequest;
import com.oef.services.model.CreateAsynchFetchJobsResult;
import com.oef.services.model.PutExtensionPolicyRequest;
import com.oef.services.model.QueryAsynchFetchJobsResult;
import com.oef.services.model.QueryExtensionPolicyResult;
import java.io.IOException;

public interface IOefClient {
    void close() throws IOException;

    CreateAsynchFetchJobsResult createFetchJob(CreateAsyncFetchJobsRequest createAsyncFetchJobsRequest) throws ObsException;

    HeaderResponse deleteExtensionPolicy(String str) throws ObsException;

    HeaderResponse putExtensionPolicy(String str, PutExtensionPolicyRequest putExtensionPolicyRequest) throws ObsException;

    QueryExtensionPolicyResult queryExtensionPolicy(String str) throws ObsException;

    QueryAsynchFetchJobsResult queryFetchJob(String str, String str2) throws ObsException;
}
