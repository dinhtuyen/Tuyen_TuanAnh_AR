package com.qualcomm.QCARUnityPlayer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;

public class OrientationUtility {
    static final int SCREEN_ORIENTATION_LANDSCAPELEFT = 3;
    static final int SCREEN_ORIENTATION_LANDSCAPERIGHT = 4;
    static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    static final int SCREEN_ORIENTATION_PORTRAITUPSIDEDOWN = 2;
    static final int SCREEN_ORIENTATION_UNKNOWN = 0;

    public static int getSurfaceOrientation(Activity activity) {
        if (activity == null) {
            return -1;
        }
        int displayRotation;
        Configuration config = activity.getResources().getConfiguration();
        Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 8) {
            displayRotation = display.getRotation();
        } else {
            displayRotation = display.getOrientation();
        }
        int i;
        switch (config.orientation) {
            case SCREEN_ORIENTATION_PORTRAIT /*1*/:
            case SCREEN_ORIENTATION_LANDSCAPELEFT /*3*/:
                i = (displayRotation == 0 || displayRotation == SCREEN_ORIENTATION_LANDSCAPELEFT) ? SCREEN_ORIENTATION_PORTRAIT : SCREEN_ORIENTATION_PORTRAITUPSIDEDOWN;
                return i;
            case SCREEN_ORIENTATION_PORTRAITUPSIDEDOWN /*2*/:
                i = (displayRotation == 0 || displayRotation == SCREEN_ORIENTATION_PORTRAIT) ? SCREEN_ORIENTATION_LANDSCAPELEFT : SCREEN_ORIENTATION_LANDSCAPERIGHT;
                return i;
            default:
                return 0;
        }
    }
}
