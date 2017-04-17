package com.qualcomm.vuforia;

public class Vec4I {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Vec4I(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Vec4I obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Vec4I(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vec4I) {
            return ((Vec4I) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public Vec4I() {
        this(VuforiaJNI.new_Vec4I__SWIG_0(), true);
    }

    public Vec4I(int[] v) {
        this(VuforiaJNI.new_Vec4I__SWIG_1(v), true);
    }

    public void setData(int[] value) {
        VuforiaJNI.Vec4I_data_set(this.swigCPtr, this, value);
    }

    public int[] getData() {
        return VuforiaJNI.Vec4I_data_get(this.swigCPtr, this);
    }
}
