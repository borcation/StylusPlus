package com.ljlvink.Hook;

import com.ljlvink.utils.logutil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {
    private static final String PKG_ANDROID = "android";
    private static final String PKG_TOMATO = "com.dragon.read";

    private static String getprop(String str) {
        try {
            Class<?>[] clsArr = {String.class};
            Object[] objArr = {str};
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", clsArr).invoke(cls, objArr);
        } catch (Throwable th) {
            return "";
        }
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        ClassLoader classLoader = loadPackageParam.classLoader;
        if(loadPackageParam.packageName.equals(PKG_ANDROID)){
            String fingerprint=getprop("ro.build.fingerprint");
            if(fingerprint.contains("liuqin")||fingerprint.contains("pipa")){
                logutil.w("not support");
                return;
            }
            new StylusPlus().Payload(classLoader);
            return;
        }

        if(loadPackageParam.packageName.equals(PKG_TOMATO)){
            new StylusPlus().PayloadTomato(classLoader);
        }
    }
}
