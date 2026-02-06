package com.obs.services.internal;

public class ObsHeaders implements IHeaders {
    private static ObsHeaders instance = new ObsHeaders();

    private ObsHeaders() {
    }

    public static IHeaders getInstance() {
        return instance;
    }

    @Override
    public String aclHeader() {
        return headerPrefix() + "acl";
    }

    @Override
    public String azRedundancyHeader() {
        return headerPrefix() + "az-redundancy";
    }

    @Override
    public String bucketRegionHeader() {
        return headerPrefix() + "bucket-location";
    }

    @Override
    public String contentSha256Header() {
        return null;
    }

    @Override
    public String copySourceHeader() {
        return headerPrefix() + "copy-source";
    }

    @Override
    public String copySourceIfMatchHeader() {
        return headerPrefix() + "copy-source-if-match";
    }

    @Override
    public String copySourceIfModifiedSinceHeader() {
        return headerPrefix() + "copy-source-if-modified-since";
    }

    @Override
    public String copySourceIfNoneMatchHeader() {
        return headerPrefix() + "copy-source-if-none-match";
    }

    @Override
    public String copySourceIfUnmodifiedSinceHeader() {
        return headerPrefix() + "copy-source-if-unmodified-since";
    }

    @Override
    public String copySourceRangeHeader() {
        return headerPrefix() + "copy-source-range";
    }

    @Override
    public String copySourceSseCHeader() {
        return headerPrefix() + "copy-source-server-side-encryption-customer-algorithm";
    }

    @Override
    public String copySourceSseCKeyHeader() {
        return headerPrefix() + "copy-source-server-side-encryption-customer-key";
    }

    @Override
    public String copySourceSseCKeyMd5Header() {
        return headerPrefix() + "copy-source-server-side-encryption-customer-key-MD5";
    }

    @Override
    public String copySourceVersionIdHeader() {
        return headerPrefix() + "copy-source-version-id";
    }

    @Override
    public String dateHeader() {
        return headerPrefix() + "date";
    }

    @Override
    public String defaultStorageClassHeader() {
        return headerPrefix() + "storage-class";
    }

    @Override
    public String deleteMarkerHeader() {
        return headerPrefix() + "delete-marker";
    }

    @Override
    public String epidHeader() {
        return headerPrefix() + "epid";
    }

    @Override
    public String expirationHeader() {
        return headerPrefix() + "expiration";
    }

    @Override
    public String expiresHeader() {
        return headerPrefix() + "expires";
    }

    @Override
    public String fsFileInterfaceHeader() {
        return headerPrefix() + "fs-file-interface";
    }

    @Override
    public String fsModeHeader() {
        return headerMetaPrefix() + "mode";
    }

    @Override
    public String grantFullControlDeliveredHeader() {
        return headerPrefix() + "grant-full-control-delivered";
    }

    @Override
    public String grantFullControlHeader() {
        return headerPrefix() + "grant-full-control";
    }

    @Override
    public String grantReadAcpHeader() {
        return headerPrefix() + "grant-read-acp";
    }

    @Override
    public String grantReadDeliveredHeader() {
        return headerPrefix() + "grant-read-delivered";
    }

    @Override
    public String grantReadHeader() {
        return headerPrefix() + "grant-read";
    }

    @Override
    public String grantWriteAcpHeader() {
        return headerPrefix() + "grant-write-acp";
    }

    @Override
    public String grantWriteHeader() {
        return headerPrefix() + "grant-write";
    }

    @Override
    public String headerMetaPrefix() {
        return "x-obs-meta-";
    }

    @Override
    public String headerPrefix() {
        return "x-obs-";
    }

    @Override
    public String listTimeoutHeader() {
        return headerPrefix() + "list-timeout";
    }

    @Override
    public String locationHeader() {
        return null;
    }

    @Override
    public String metadataDirectiveHeader() {
        return headerPrefix() + "metadata-directive";
    }

    @Override
    public String nextPositionHeader() {
        return headerPrefix() + "next-append-position";
    }

    @Override
    public String objectTypeHeader() {
        return headerPrefix() + "object-type";
    }

    @Override
    public String requestId2Header() {
        return headerPrefix() + "id-2";
    }

    @Override
    public String requestIdHeader() {
        return headerPrefix() + "request-id";
    }

    @Override
    public String restoreHeader() {
        return headerPrefix() + "restore";
    }

    @Override
    public String securityTokenHeader() {
        return headerPrefix() + "security-token";
    }

    @Override
    public String serverVersionHeader() {
        return headerPrefix() + "version";
    }

    @Override
    public String sseCHeader() {
        return headerPrefix() + "server-side-encryption-customer-algorithm";
    }

    @Override
    public String sseCKeyHeader() {
        return headerPrefix() + "server-side-encryption-customer-key";
    }

    @Override
    public String sseCKeyMd5Header() {
        return headerPrefix() + "server-side-encryption-customer-key-MD5";
    }

    @Override
    public String sseKmsHeader() {
        return headerPrefix() + "server-side-encryption";
    }

    @Override
    public String sseKmsKeyHeader() {
        return headerPrefix() + "server-side-encryption-kms-key-id";
    }

    @Override
    public String sseKmsProjectIdHeader() {
        return headerPrefix() + "sse-kms-key-project-id";
    }

    @Override
    public String storageClassHeader() {
        return headerPrefix() + "storage-class";
    }

    @Override
    public String successRedirectLocationHeader() {
        return "success-action-redirect";
    }

    @Override
    public String versionIdHeader() {
        return headerPrefix() + "version-id";
    }

    @Override
    public String websiteRedirectLocationHeader() {
        return headerPrefix() + "website-redirect-location";
    }
}
