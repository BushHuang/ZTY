package com.obs.services.model;

import com.obs.services.internal.utils.ServiceUtils;

public enum EventTypeEnum {
    OBJECT_CREATED_ALL,
    OBJECT_CREATED_PUT,
    OBJECT_CREATED_POST,
    OBJECT_CREATED_COPY,
    OBJECT_CREATED_COMPLETE_MULTIPART_UPLOAD,
    OBJECT_REMOVED_ALL,
    OBJECT_REMOVED_DELETE,
    OBJECT_REMOVED_DELETE_MARKER_CREATED;

    public static EventTypeEnum getValueFromCode(String str) {
        if (!ServiceUtils.isValid(str)) {
            return null;
        }
        if (str.indexOf("ObjectCreated:*") >= 0) {
            return OBJECT_CREATED_ALL;
        }
        if (str.indexOf("ObjectCreated:Put") >= 0) {
            return OBJECT_CREATED_PUT;
        }
        if (str.indexOf("ObjectCreated:Post") >= 0) {
            return OBJECT_CREATED_POST;
        }
        if (str.indexOf("ObjectCreated:Copy") >= 0) {
            return OBJECT_CREATED_COPY;
        }
        if (str.indexOf("ObjectCreated:CompleteMultipartUpload") >= 0) {
            return OBJECT_CREATED_COMPLETE_MULTIPART_UPLOAD;
        }
        if (str.indexOf("ObjectRemoved:*") >= 0) {
            return OBJECT_REMOVED_ALL;
        }
        if (str.indexOf("ObjectRemoved:Delete") >= 0) {
            return OBJECT_REMOVED_DELETE;
        }
        if (str.indexOf("ObjectRemoved:DeleteMarkerCreated") >= 0) {
            return OBJECT_REMOVED_DELETE_MARKER_CREATED;
        }
        return null;
    }
}
