package com.qualcomm.vuforia;

public class Matrix44F {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Matrix44F(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Matrix44F obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Matrix44F(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Matrix44F) {
            return ((Matrix44F) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public void setData(float[] value) {
        VuforiaJNI.Matrix44F_data_set(this.swigCPtr, this, value);
    }

    public float[] getData() {
        return VuforiaJNI.Matrix44F_data_get(this.swigCPtr, this);
    }

    public Matrix44F() {
        this(VuforiaJNI.new_Matrix44F__SWIG_0(), true);
    }

    public Matrix44F(Matrix44F other) {
        this(VuforiaJNI.new_Matrix44F__SWIG_1(getCPtr(other), other), true);
    }
}
