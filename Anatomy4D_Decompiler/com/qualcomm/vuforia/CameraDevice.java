package com.qualcomm.vuforia;

public class CameraDevice {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class CAMERA {
        public static final int CAMERA_BACK = 1;
        public static final int CAMERA_DEFAULT = 0;
        public static final int CAMERA_FRONT = 2;

        private CAMERA() {
        }
    }

    public static final class FOCUS_MODE {
        public static final int FOCUS_MODE_CONTINUOUSAUTO = 2;
        public static final int FOCUS_MODE_INFINITY = 3;
        public static final int FOCUS_MODE_MACRO = 4;
        public static final int FOCUS_MODE_NORMAL = 0;
        public static final int FOCUS_MODE_TRIGGERAUTO = 1;

        private FOCUS_MODE() {
        }
    }

    public static final class MODE {
        public static final int MODE_DEFAULT = -1;
        public static final int MODE_OPTIMIZE_QUALITY = -3;
        public static final int MODE_OPTIMIZE_SPEED = -2;

        private MODE() {
        }
    }

    protected CameraDevice(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(CameraDevice obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_CameraDevice(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof CameraDevice) {
            return ((CameraDevice) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static CameraDevice getInstance() {
        if (Vuforia.wasInitializedJava()) {
            return new CameraDevice(VuforiaJNI.CameraDevice_getInstance(), false);
        }
        throw new RuntimeException("Use of the Java Vuforia APIs requires initalization via the com.qualcomm.vuforia.Vuforia class");
    }

    public boolean init(int camera) {
        return VuforiaJNI.CameraDevice_init__SWIG_0(this.swigCPtr, this, camera);
    }

    public boolean init() {
        return VuforiaJNI.CameraDevice_init__SWIG_1(this.swigCPtr, this);
    }

    public boolean deinit() {
        return VuforiaJNI.CameraDevice_deinit(this.swigCPtr, this);
    }

    public boolean start() {
        return VuforiaJNI.CameraDevice_start(this.swigCPtr, this);
    }

    public boolean stop() {
        return VuforiaJNI.CameraDevice_stop(this.swigCPtr, this);
    }

    public int getNumVideoModes() {
        return VuforiaJNI.CameraDevice_getNumVideoModes(this.swigCPtr, this);
    }

    public VideoMode getVideoMode(int nIndex) {
        return new VideoMode(VuforiaJNI.CameraDevice_getVideoMode(this.swigCPtr, this, nIndex), true);
    }

    public boolean selectVideoMode(int index) {
        return VuforiaJNI.CameraDevice_selectVideoMode(this.swigCPtr, this, index);
    }

    public CameraCalibration getCameraCalibration() {
        return new CameraCalibration(VuforiaJNI.CameraDevice_getCameraCalibration(this.swigCPtr, this), false);
    }

    public boolean setFlashTorchMode(boolean on) {
        return VuforiaJNI.CameraDevice_setFlashTorchMode(this.swigCPtr, this, on);
    }

    public boolean setFocusMode(int focusMode) {
        return VuforiaJNI.CameraDevice_setFocusMode(this.swigCPtr, this, focusMode);
    }
}
