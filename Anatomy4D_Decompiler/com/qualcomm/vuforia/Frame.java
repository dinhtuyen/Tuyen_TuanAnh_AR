package com.qualcomm.vuforia;

public class Frame {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Frame(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Frame obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Frame(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Frame) {
            return ((Frame) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public Frame() {
        this(VuforiaJNI.new_Frame__SWIG_0(), true);
    }

    public Frame(Frame other) {
        this(VuforiaJNI.new_Frame__SWIG_1(getCPtr(other), other), true);
    }

    public double getTimeStamp() {
        return VuforiaJNI.Frame_getTimeStamp(this.swigCPtr, this);
    }

    public int getIndex() {
        return VuforiaJNI.Frame_getIndex(this.swigCPtr, this);
    }

    public int getNumImages() {
        return VuforiaJNI.Frame_getNumImages(this.swigCPtr, this);
    }

    public Image getImage(int idx) {
        long cPtr = VuforiaJNI.Frame_getImage(this.swigCPtr, this, idx);
        return cPtr == 0 ? null : new Image(cPtr, false);
    }
}
