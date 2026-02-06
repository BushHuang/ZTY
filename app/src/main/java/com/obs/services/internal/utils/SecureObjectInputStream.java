package com.obs.services.internal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SecureObjectInputStream extends ObjectInputStream {
    public static final List<String> ALLOWED_ClASS_NAMES = Collections.unmodifiableList(Arrays.asList("java.util.ArrayList", "com.obs.services.model.PartEtag", "java.lang.Integer", "java.lang.Number", "java.util.Date", "com.obs.services.internal.ResumableClient$TmpFileStatus", "com.obs.services.internal.ResumableClient$UploadCheckPoint", "com.obs.services.internal.ResumableClient$FileStatus", "com.obs.services.internal.ResumableClient$UploadPart", "com.obs.services.internal.ResumableClient$DownloadCheckPoint", "com.obs.services.internal.ResumableClient$DownloadPart", "com.obs.services.internal.ResumableClient$ObjectStatus"));

    public SecureObjectInputStream() throws IOException, SecurityException {
    }

    public SecureObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws ClassNotFoundException, IOException {
        String name = objectStreamClass.getName();
        if (ALLOWED_ClASS_NAMES.contains(name)) {
            return super.resolveClass(objectStreamClass);
        }
        throw new ClassNotFoundException(name + "not find");
    }
}
