package com.qualcomm.vuforia;

public class Vec2I {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Vec2I(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Vec2I obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Vec2I(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vec2I) {
            return ((Vec2I) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public Vec2I() {
        this(VuforiaJNI.new_Vec2I__SWIG_0(), true);
    }

    public Vec2I(int[] v) {
        this(VuforiaJNI.new_Vec2I__SWIG_1(v), true);
    }

    public Vec2I(int v0, int v1) {
        this(VuforiaJNI.new_Vec2I__SWIG_2(v0, v1), true);
    }

    public void setData(int[] value) {
        VuforiaJNI.Vec2I_data_set(this.swigCPtr, this, value);
    }

    public int[] getData() {
        return VuforiaJNI.Vec2I_data_get(this.swigCPtr, this);
    }

    public Vec2I(Vec2I other) {
        this(VuforiaJNI.new_Vec2I__SWIG_3(getCPtr(other), other), true);
    }
}
