package com.qualcomm.vuforia;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import com.qualcomm.QCAR.QCAR;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Vuforia extends QCAR implements VuforiaConstants {
    private static boolean initializedJava;
    private static UpdateCallback sUpdateCallback;
    private static UpdateCallbackInterface sUpdateCallbackInterface;
    protected static Map<Integer, Object> sUserDataMap;

    /* renamed from: com.qualcomm.vuforia.Vuforia.1 */
    static class C00601 extends UpdateCallback {
        C00601() {
        }

        public void QCAR_onUpdate(State s) {
            Vuforia.sUpdateCallbackInterface.QCAR_onUpdate(s);
        }
    }

    public interface UpdateCallbackInterface {
        void QCAR_onUpdate(State state);
    }

    static {
        initializedJava = false;
        sUserDataMap = new ConcurrentHashMap(16, 0.75f, 4);
        sUpdateCallback = null;
        sUpdateCallbackInterface = null;
    }

    protected static boolean wasInitializedJava() {
        return initializedJava;
    }

    protected static void setHint() {
        setHint(-858996736, 2796202);
    }

    public static void setInitParameters(Activity activity, int flags) {
        if (!initializedJava) {
            setHint();
            initializedJava = true;
        }
        QCAR.setInitParameters(activity, flags);
    }

    protected static short[] convertStringToShortArray(String str) {
        if (str == null) {
            return null;
        }
        short[] codes = new short[(str.codePointCount(0, str.length()) + 1)];
        int length = str.length();
        int offset = 0;
        int idx = 0;
        while (offset < length) {
            int i;
            int codePoint = str.codePointAt(offset);
            if (codePoint > SupportMenu.USER_MASK) {
                i = idx + 1;
                codes[idx] = (short) (codePoint >> 16);
            } else {
                i = idx;
            }
            idx = i + 1;
            codes[i] = (short) codePoint;
            offset += Character.charCount(codePoint);
        }
        codes[codes.length - 1] = (short) 0;
        return codes;
    }

    protected static boolean updateUserDataMap(Integer trackableId, Object userData) {
        if (trackableId == null) {
            return false;
        }
        if (userData == null) {
            sUserDataMap.remove(trackableId);
        } else {
            sUserDataMap.put(trackableId, userData);
        }
        return true;
    }

    protected static Object retreiveFromUserDataMap(Integer trackableId) {
        if (sUserDataMap.containsKey(trackableId)) {
            return sUserDataMap.get(trackableId);
        }
        return null;
    }

    protected static UpdateCallback registerLocalReference(UpdateCallbackInterface cbi) {
        if (cbi == null) {
            sUpdateCallback = null;
            sUpdateCallbackInterface = null;
            return null;
        }
        sUpdateCallbackInterface = cbi;
        sUpdateCallback = new C00601();
        return sUpdateCallback;
    }

    public static void deinit() {
        VuforiaJNI.deinit();
    }

    public static boolean setHint(long hint, int value) {
        return VuforiaJNI.setHint(hint, value);
    }

    public static void registerCallback(UpdateCallbackInterface object) {
        VuforiaJNI.registerCallback(UpdateCallback.getCPtr(registerLocalReference(object)), sUpdateCallback);
    }

    public static boolean setFrameFormat(int format, boolean enabled) {
        return VuforiaJNI.setFrameFormat(format, enabled);
    }

    public static int getBitsPerPixel(int format) {
        return VuforiaJNI.getBitsPerPixel(format);
    }

    public static boolean requiresAlpha() {
        return VuforiaJNI.requiresAlpha();
    }

    public static int getBufferSize(int width, int height, int format) {
        return VuforiaJNI.getBufferSize(width, height, format);
    }

    public static void onResume() {
        VuforiaJNI.onResume();
    }

    public static void onPause() {
        VuforiaJNI.onPause();
    }

    public static void onSurfaceCreated() {
        VuforiaJNI.onSurfaceCreated();
    }

    public static void onSurfaceChanged(int width, int height) {
        VuforiaJNI.onSurfaceChanged(width, height);
    }
}
