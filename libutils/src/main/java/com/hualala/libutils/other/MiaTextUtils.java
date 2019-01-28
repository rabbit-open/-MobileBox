//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hualala.libutils.other;

import android.content.Context;
import android.text.TextUtils;
import com.hualala.libutils.MBContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiaTextUtils {
    public MiaTextUtils() {
    }

    public static String getString(int resId, Object... formatArgs) {
        Context context = MBContext.getContext();
        return context == null ? null : context.getString(resId, formatArgs);
    }

    public static boolean isNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[0-9]\\d*$");
            Matcher matcher = pattern.matcher(str.trim());
            return matcher.matches();
        }
    }
}
