package com.intelin.vn.demomvvm.retrofit_client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intelin.vn.demomvvm.exception.CustomException;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 2:23 PM
 */
public class JSON {

    private static Gson parseGson = new GsonBuilder().disableHtmlEscaping().create();

    public static String encode(Object obj) throws CustomException.EncodingException {
        return parseGson.toJson(obj);
    }

    public static <T> T decode(String json, Class<T> tClass) throws Exception {
        try {
            return parseGson.fromJson(json, tClass);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
