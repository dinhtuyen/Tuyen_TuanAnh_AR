package com.qualcomm.vuforia;

public class TargetFinder {
    public static final int INIT_DEFAULT = 0;
    public static final int INIT_ERROR_NO_NETWORK_CONNECTION = -1;
    public static final int INIT_ERROR_SERVICE_NOT_AVAILABLE = -2;
    public static final int INIT_RUNNING = 1;
    public static final int INIT_SUCCESS = 2;
    public static final int UPDATE_ERROR_AUTHORIZATION_FAILED = -1;
    public static final int UPDATE_ERROR_BAD_FRAME_QUALITY = -5;
    public static final int UPDATE_ERROR_NO_NETWORK_CONNECTION = -3;
    public static final int UPDATE_ERROR_PROJECT_SUSPENDED = -2;
    public static final int UPDATE_ERROR_REQUEST_TIMEOUT = -8;
    public static final int UPDATE_ERROR_SERVICE_NOT_AVAILABLE = -4;
    public static final int UPDATE_ERROR_TIMESTAMP_OUT_OF_RANGE = -7;
    public static final int UPDATE_ERROR_UPDATE_SDK = -6;
    public static final int UPDATE_NO_MATCH = 0;
    public static final int UPDATE_NO_REQUEST = 1;
    public static final int UPDATE_RESULTS_AVAILABLE = 2;
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TargetFinder(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(TargetFinder obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_TargetFinder(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof TargetFinder) {
            return ((TargetFinder) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public boolean startInit(String userAuth, String secretAuth) {
        return VuforiaJNI.TargetFinder_startInit(this.swigCPtr, this, userAuth, secretAuth);
    }

    public int getInitState() {
        return VuforiaJNI.TargetFinder_getInitState(this.swigCPtr, this);
    }

    public void waitUntilInitFinished() {
        VuforiaJNI.TargetFinder_waitUntilInitFinished(this.swigCPtr, this);
    }

    public boolean deinit() {
        return VuforiaJNI.TargetFinder_deinit(this.swigCPtr, this);
    }

    public boolean startRecognition() {
        return VuforiaJNI.TargetFinder_startRecognition(this.swigCPtr, this);
    }

    public boolean stop() {
        return VuforiaJNI.TargetFinder_stop(this.swigCPtr, this);
    }

    public boolean isRequesting() {
        return VuforiaJNI.TargetFinder_isRequesting(this.swigCPtr, this);
    }

    public int updateSearchResults() {
        return VuforiaJNI.TargetFinder_updateSearchResults(this.swigCPtr, this);
    }

    public int getResultCount() {
        return VuforiaJNI.TargetFinder_getResultCount(this.swigCPtr, this);
    }

    public TargetSearchResult getResult(int idx) {
        long cPtr = VuforiaJNI.TargetFinder_getResult(this.swigCPtr, this, idx);
        return cPtr == 0 ? null : new TargetSearchResult(cPtr, false);
    }

    public ImageTarget enableTracking(TargetSearchResult result) {
        long cPtr = VuforiaJNI.TargetFinder_enableTracking(this.swigCPtr, this, TargetSearchResult.getCPtr(result), result);
        return cPtr == 0 ? null : new ImageTarget(cPtr, false);
    }

    public void clearTrackables() {
        VuforiaJNI.TargetFinder_clearTrackables(this.swigCPtr, this);
    }

    public int getNumImageTargets() {
        return VuforiaJNI.TargetFinder_getNumImageTargets(this.swigCPtr, this);
    }

    public ImageTarget getImageTarget(int idx) {
        long cPtr = VuforiaJNI.TargetFinder_getImageTarget(this.swigCPtr, this, idx);
        return cPtr == 0 ? null : new ImageTarget(cPtr, false);
    }

    public void setUIScanlineColor(float r, float g, float b) {
        VuforiaJNI.TargetFinder_setUIScanlineColor(this.swigCPtr, this, r, g, b);
    }

    public void setUIPointColor(float r, float g, float b) {
        VuforiaJNI.TargetFinder_setUIPointColor(this.swigCPtr, this, r, g, b);
    }
}
