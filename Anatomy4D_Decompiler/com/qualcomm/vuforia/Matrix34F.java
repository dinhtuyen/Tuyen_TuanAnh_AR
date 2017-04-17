package com.qualcomm.vuforia;

public class Matrix34F {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Matrix34F(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Matrix34F obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Matrix34F(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Matrix34F) {
            return ((Matrix34F) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public void setData(float[] value) {
        VuforiaJNI.Matrix34F_data_set(this.swigCPtr, this, value);
    }

    public float[] getData() {
        return VuforiaJNI.Matrix34F_data_get(this.swigCPtr, this);
    }

    public Matrix34F() {
        this(VuforiaJNI.new_Matrix34F__SWIG_0(), true);
    }

    public Matrix34F(Matrix34F other) {
        this(VuforiaJNI.new_Matrix34F__SWIG_1(getCPtr(other), other), true);
    }
}
