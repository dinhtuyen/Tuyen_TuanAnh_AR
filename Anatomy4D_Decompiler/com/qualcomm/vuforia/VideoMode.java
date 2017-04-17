package com.qualcomm.vuforia;

public class VideoMode {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VideoMode(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VideoMode obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_VideoMode(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof VideoMode) {
            return ((VideoMode) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public VideoMode() {
        this(VuforiaJNI.new_VideoMode__SWIG_0(), true);
    }

    public void setWidth(int value) {
        VuforiaJNI.VideoMode_Width_set(this.swigCPtr, this, value);
    }

    public int getWidth() {
        return VuforiaJNI.VideoMode_Width_get(this.swigCPtr, this);
    }

    public void setHeight(int value) {
        VuforiaJNI.VideoMode_Height_set(this.swigCPtr, this, value);
    }

    public int getHeight() {
        return VuforiaJNI.VideoMode_Height_get(this.swigCPtr, this);
    }

    public void setFramerate(float value) {
        VuforiaJNI.VideoMode_Framerate_set(this.swigCPtr, this, value);
    }

    public float getFramerate() {
        return VuforiaJNI.VideoMode_Framerate_get(this.swigCPtr, this);
    }

    public VideoMode(VideoMode other) {
        this(VuforiaJNI.new_VideoMode__SWIG_1(getCPtr(other), other), true);
    }
}
