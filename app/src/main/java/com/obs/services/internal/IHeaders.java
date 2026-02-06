package com.obs.services.internal;

public interface IHeaders {
    String aclHeader();

    String azRedundancyHeader();

    String bucketRegionHeader();

    String contentSha256Header();

    String copySourceHeader();

    String copySourceIfMatchHeader();

    String copySourceIfModifiedSinceHeader();

    String copySourceIfNoneMatchHeader();

    String copySourceIfUnmodifiedSinceHeader();

    String copySourceRangeHeader();

    String copySourceSseCHeader();

    String copySourceSseCKeyHeader();

    String copySourceSseCKeyMd5Header();

    String copySourceVersionIdHeader();

    String dateHeader();

    String defaultStorageClassHeader();

    String deleteMarkerHeader();

    String epidHeader();

    String expirationHeader();

    String expiresHeader();

    String fsFileInterfaceHeader();

    String fsModeHeader();

    String grantFullControlDeliveredHeader();

    String grantFullControlHeader();

    String grantReadAcpHeader();

    String grantReadDeliveredHeader();

    String grantReadHeader();

    String grantWriteAcpHeader();

    String grantWriteHeader();

    String headerMetaPrefix();

    String headerPrefix();

    String listTimeoutHeader();

    String locationHeader();

    String metadataDirectiveHeader();

    String nextPositionHeader();

    String objectTypeHeader();

    String requestId2Header();

    String requestIdHeader();

    String restoreHeader();

    String securityTokenHeader();

    String serverVersionHeader();

    String sseCHeader();

    String sseCKeyHeader();

    String sseCKeyMd5Header();

    String sseKmsHeader();

    String sseKmsKeyHeader();

    String sseKmsProjectIdHeader();

    String storageClassHeader();

    String successRedirectLocationHeader();

    String versionIdHeader();

    String websiteRedirectLocationHeader();
}
