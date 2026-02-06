package com.obs.services.internal.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.obs.services.internal.ServiceException;
import java.io.IOException;

public class JSONChange {
    public static Object jsonToObj(Object obj, String str) throws ServiceException {
        try {
            return ObjectMapperUtil.getInstance().readValue(str, obj.getClass());
        } catch (JsonParseException e) {
            throw new ServiceException("conversion JSON failed", e);
        } catch (JsonMappingException e2) {
            throw new ServiceException("conversion JSON failed", e2);
        } catch (IOException e3) {
            throw new ServiceException("conversion JSON failed", e3);
        }
    }

    public static String objToJson(Object obj) throws ServiceException {
        try {
            return ObjectMapperUtil.getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ServiceException("conversion JSON failed", e);
        }
    }
}
