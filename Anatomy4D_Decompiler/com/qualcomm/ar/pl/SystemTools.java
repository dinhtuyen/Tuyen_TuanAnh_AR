package com.qualcomm.ar.pl;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

public class SystemTools {
    public static final int AR_DEVICE_ORIENTATION_0 = 268455954;
    public static final int AR_DEVICE_ORIENTATION_180 = 268455955;
    public static final int AR_DEVICE_ORIENTATION_270 = 268455957;
    public static final int AR_DEVICE_ORIENTATION_90 = 268455956;
    public static final int AR_DEVICE_ORIENTATION_UNKNOWN = 268455952;
    public static final int AR_ERROR_INVALID_ENUM = 3;
    public static final int AR_ERROR_INVALID_HANDLE = 4;
    public static final int AR_ERROR_INVALID_OPERATION = 5;
    public static final int AR_ERROR_INVALID_VALUE = 2;
    public static final int AR_ERROR_NONE = 0;
    public static final int AR_ERROR_OPERATION_CANCELED = 7;
    public static final int AR_ERROR_OPERATION_FAILED = 6;
    public static final int AR_ERROR_OPERATION_TIMEOUT = 8;
    public static final int AR_ERROR_UNKNOWN = 1;
    public static final int AR_RENDERING_TEXTURE_ROTATION_AUTO = 268455953;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_LEFT = 268455956;
    public static final int AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_RIGHT = 268455957;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT = 268455954;
    public static final int AR_RENDERING_TEXTURE_ROTATION_PORTRAIT_UPSIDEDOWN = 268455955;
    public static final int AR_VIDEOTEXTURE_ROTATION_UNKNOWN = 268455952;
    private static final String MODULENAME = "SystemTools";

    /* renamed from: com.qualcomm.ar.pl.SystemTools.1 */
    static class C00591 implements Runnable {
        final /* synthetic */ Activity val$activity;

        C00591(Activity activity) {
            this.val$activity = activity;
        }

        public void run() {
            try {
                Method method = this.val$activity.getClass().getDeclaredMethod("onPause", new Class[SystemTools.AR_ERROR_NONE]);
                method.setAccessible(true);
                method.invoke(this.val$activity, new Object[SystemTools.AR_ERROR_NONE]);
                method = this.val$activity.getClass().getDeclaredMethod("onDestroy", new Class[SystemTools.AR_ERROR_NONE]);
                method.setAccessible(true);
                method.invoke(this.val$activity, new Object[SystemTools.AR_ERROR_NONE]);
            } catch (Exception e) {
                SystemTools.logSystemError("Error attempting to call onPause and onDestroy, will proceed with exiting");
                SystemTools.logSystemError(e.getCause().toString());
                StackTraceElement[] arr$ = e.getStackTrace();
                int len$ = arr$.length;
                for (int i$ = SystemTools.AR_ERROR_NONE; i$ < len$; i$ += SystemTools.AR_ERROR_UNKNOWN) {
                    SystemTools.logSystemError(arr$[i$].toString());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
            }
            System.exit(SystemTools.AR_ERROR_NONE);
        }
    }

    public static native Activity getActivityFromNative();

    public static native void logSystemError(String str);

    public static native void setSystemErrorCode(int i);

    public static boolean checkMinimumApiLevel(int apiLevel) {
        return VERSION.SDK_INT >= apiLevel;
    }

    public static void sendKillSignal() {
        Activity activity = getActivityFromNative();
        activity.runOnUiThread(new C00591(activity));
    }

    public static Method retrieveClassMethod(Class<?> cls, String name, Class<?>... parameterTypes) {
        Method classMethod = null;
        try {
            classMethod = cls.getMethod(name, parameterTypes);
        } catch (Exception e) {
        }
        return classMethod != null ? classMethod : classMethod;
    }

    public static int getDeviceOrientation(Activity activity) {
        int i = AR_VIDEOTEXTURE_ROTATION_UNKNOWN;
        if (activity == null) {
            DebugLog.LOGE(MODULENAME, "Invalid activity");
        } else {
            int displayRotation;
            Configuration config = activity.getResources().getConfiguration();
            Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
            if (checkMinimumApiLevel(AR_ERROR_OPERATION_TIMEOUT)) {
                displayRotation = display.getRotation();
            } else {
                displayRotation = display.getOrientation();
            }
            if (displayRotation == 0) {
                i = AR_RENDERING_TEXTURE_ROTATION_PORTRAIT;
            } else if (displayRotation == AR_ERROR_UNKNOWN) {
                i = AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_LEFT;
            } else if (displayRotation == AR_ERROR_INVALID_VALUE) {
                i = AR_RENDERING_TEXTURE_ROTATION_PORTRAIT_UPSIDEDOWN;
            } else if (displayRotation == AR_ERROR_INVALID_ENUM) {
                i = AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_RIGHT;
            }
        }
        return i;
    }

    public static int getActivityOrientation(Activity activity) {
        if (activity == null) {
            return AR_VIDEOTEXTURE_ROTATION_UNKNOWN;
        }
        int displayRotation;
        Configuration config = activity.getResources().getConfiguration();
        Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (checkMinimumApiLevel(AR_ERROR_OPERATION_TIMEOUT)) {
            displayRotation = display.getRotation();
        } else {
            displayRotation = display.getOrientation();
        }
        int i;
        switch (config.orientation) {
            case AR_ERROR_UNKNOWN /*1*/:
            case AR_ERROR_INVALID_ENUM /*3*/:
                i = (displayRotation == 0 || displayRotation == AR_ERROR_INVALID_ENUM) ? AR_RENDERING_TEXTURE_ROTATION_PORTRAIT : AR_RENDERING_TEXTURE_ROTATION_PORTRAIT_UPSIDEDOWN;
                return i;
            case AR_ERROR_INVALID_VALUE /*2*/:
                i = (displayRotation == 0 || displayRotation == AR_ERROR_UNKNOWN) ? AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_LEFT : AR_RENDERING_TEXTURE_ROTATION_LANDSCAPE_RIGHT;
                return i;
            default:
                return AR_VIDEOTEXTURE_ROTATION_UNKNOWN;
        }
    }

    public static String getNativeLibraryPath(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            ApplicationInfo appInfo = activity.getApplicationInfo();
            if (appInfo == null) {
                return null;
            }
            if (!checkMinimumApiLevel(9)) {
                return "/data/data/" + activity.getPackageName() + "/lib/";
            }
            String path = appInfo.nativeLibraryDir;
            if (path == null || path.length() <= 0 || path.charAt(path.length() - 1) == '/') {
                return path;
            }
            return path + '/';
        } catch (Exception e) {
            return null;
        }
    }

    public static int[] getActivitySize(Activity activity) {
        if (activity == null) {
            return null;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        if (screenWidth <= 0 || screenHeight <= 0) {
            return null;
        }
        int[] screenSize = new int[AR_ERROR_INVALID_VALUE];
        screenSize[AR_ERROR_NONE] = screenWidth;
        screenSize[AR_ERROR_UNKNOWN] = screenHeight;
        return screenSize;
    }
}
