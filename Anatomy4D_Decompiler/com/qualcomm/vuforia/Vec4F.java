package com.qualcomm.vuforia;

public class Vec4F {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Vec4F(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Vec4F obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Vec4F(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vec4F) {
            return ((Vec4F) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public Vec4F() {
        this(VuforiaJNI.new_Vec4F__SWIG_0(), true);
    }

    public Vec4F(float[] v) {
        this(VuforiaJNI.new_Vec4F__SWIG_1(v), true);
    }

    public Vec4F(float v0, float v1, float v2, float v3) {
        this(VuforiaJNI.new_Vec4F__SWIG_2(v0, v1, v2, v3), true);
    }

    public void setData(float[] value) {
        VuforiaJNI.Vec4F_data_set(this.swigCPtr, this, value);
    }

    public float[] getData() {
        return VuforiaJNI.Vec4F_data_get(this.swigCPtr, this);
    }

    public Vec4F(Vec4F other) {
        this(VuforiaJNI.new_Vec4F__SWIG_3(getCPtr(other), other), true);
    }
}
