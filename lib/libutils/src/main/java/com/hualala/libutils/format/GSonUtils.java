package com.hualala.libutils.format;

import android.text.TextUtils;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GSonUtils {

    private static Gson gson = new Gson();

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static <T> T fromJson(String json, Type clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createJson(Map<String, ?> map) {
        return toJson(map);
    }

    public static String createArrayJson(ArrayList<?> array) {
        return toJson(array);
    }

    public static String createArrayJson(String key, ArrayList<?> array) {
        HashMap<String, ArrayList<?>> map = new HashMap<String, ArrayList<?>>();
        map.put(key, array);
        return gson.toJson(map);
    }

}
