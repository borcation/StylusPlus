package com.ljlvink.utils;

import de.robv.android.xposed.XposedBridge;

public class logutil {
    private static final String TAG = "[StylusPlus_xposed]";
    private static final String DEBUG_PROP = "persist.sys.stylusplus.debug";

    private static volatile boolean DEBUG = readDebugProp();

    private static boolean readDebugProp() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object value = cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, DEBUG_PROP, "1");
            return "1".equals(value) || "true".equalsIgnoreCase(String.valueOf(value));
        } catch (Throwable ignored) {
            return true;
        }
    }

    public static void refreshDebugFlag() {
        DEBUG = readDebugProp();
    }

    public static void setDebug(boolean enabled) {
        DEBUG = enabled;
    }

    public static void log(String log) {
        i(log);
    }

    public static void d(String log) {
        if (DEBUG) {
            XposedBridge.log(TAG + "[D] " + log);
        }
    }

    public static void i(String log) {
        XposedBridge.log(TAG + "[I] " + log);
    }

    public static void w(String log) {
        XposedBridge.log(TAG + "[W] " + log);
    }

    public static void e(String log) {
        XposedBridge.log(TAG + "[E] " + log);
    }
}
