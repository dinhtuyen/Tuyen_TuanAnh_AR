package com.qualcomm.vuforia;

public class Renderer {
    private static State sState;
    private static final Object sStateMutex;
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Renderer(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Renderer obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Renderer(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    static {
        sState = null;
        sStateMutex = new Object();
    }

    public static Renderer getInstance() {
        if (Vuforia.wasInitializedJava()) {
            return new Renderer(VuforiaJNI.Renderer_getInstance(), false);
        }
        throw new RuntimeException("Use of the Java Vuforia APIs requires initalization via the com.qualcomm.vuforia.Vuforia class");
    }

    public State begin() {
        synchronized (sStateMutex) {
            if (sState != null) {
                sState.delete();
            }
            sState = new State(VuforiaJNI.Renderer_begin__SWIG_0(this.swigCPtr, this), true);
        }
        return sState;
    }

    public void begin(State state) {
        VuforiaJNI.Renderer_begin__SWIG_1(this.swigCPtr, this, State.getCPtr(state), state);
    }

    public boolean drawVideoBackground() {
        return VuforiaJNI.Renderer_drawVideoBackground(this.swigCPtr, this);
    }

    public void end() {
        VuforiaJNI.Renderer_end(this.swigCPtr, this);
        synchronized (sStateMutex) {
            if (sState != null) {
                sState.delete();
                sState = null;
            }
        }
    }

    public boolean bindVideoBackground(int unit) {
        return VuforiaJNI.Renderer_bindVideoBackground(this.swigCPtr, this, unit);
    }

    public void setVideoBackgroundConfig(VideoBackgroundConfig cfg) {
        VuforiaJNI.Renderer_setVideoBackgroundConfig(this.swigCPtr, this, VideoBackgroundConfig.getCPtr(cfg), cfg);
    }

    public VideoBackgroundConfig getVideoBackgroundConfig() {
        return new VideoBackgroundConfig(VuforiaJNI.Renderer_getVideoBackgroundConfig(this.swigCPtr, this), false);
    }

    public VideoBackgroundTextureInfo getVideoBackgroundTextureInfo() {
        return new VideoBackgroundTextureInfo(VuforiaJNI.Renderer_getVideoBackgroundTextureInfo(this.swigCPtr, this), false);
    }

    public boolean setVideoBackgroundTextureID(int textureID) {
        return VuforiaJNI.Renderer_setVideoBackgroundTextureID(this.swigCPtr, this, textureID);
    }

    public void setARProjection(float nearPlane, float farPlane) {
        VuforiaJNI.Renderer_setARProjection(this.swigCPtr, this, nearPlane, farPlane);
    }
}
