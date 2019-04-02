package com.intelin.vn.demomvvm;

import android.text.TextUtils;
import android.util.Log;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 3:12 PM
 */
public class LogDog {
    private class TAG {
        public static final String DEBUG = Constants.APP + "-[DEBUG]-";
        public static final String ERROR = Constants.APP + "-[ERROR]-";
        public static final String INFO = Constants.APP + "-[INFO]-";
    }

    //LogHusky Debug
    public static int d(String _msg) {
        return d(_msg, 5);
    }

    private static int d(String _msg, int deepth) {
        if (TextUtils.isEmpty(_msg))
            return Log.e(LogHusky(deepth, TAG.DEBUG), "LogHusky message parameter is empty!!");

        return Log.d(LogHusky(deepth, TAG.DEBUG), _msg);
    }

    //LogHusky Info
    public static int i(String _msg) {
        return i(_msg, 5);
    }

    private static int i(String _msg, int deepth) {
        if (TextUtils.isEmpty(_msg))
            return Log.e(LogHusky(deepth, TAG.INFO), "LogHusky message parameter is empty!!");

        return Log.d(LogHusky(deepth, TAG.INFO), _msg);
    }

    //LogHusky Error
    public static void e(Exception e) {
        if (null != e) {
            e(e.getMessage(), 5);
            e.printStackTrace();
        }
    }

    public static void e(Throwable t) {
        if (null != t) {
            e(t.getMessage(), 5);
            t.printStackTrace();
        }
    }

    public static int e(String _msg) {
        return e(_msg, 5);
    }

    private static int e(String _msg, int deepth) {
        if (TextUtils.isEmpty(_msg))
            return Log.e(LogHusky(deepth, TAG.ERROR), "LogHusky message parameter is empty!!");

        return Log.d(LogHusky(deepth, TAG.ERROR), _msg);
    }


    private static String LogHusky(int depth, String TAG) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[depth];
        String str = element.getFileName();
        return TAG + "[" + element.getMethodName() + "]-[" + str + ":" + element.getLineNumber() + "]";
    }
}
