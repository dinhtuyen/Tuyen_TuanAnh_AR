package com.qualcomm.vuforia;

public class Type {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Type(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Type obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Type(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            return ((Type) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public Type() {
        this(VuforiaJNI.new_Type__SWIG_0(), true);
    }

    public Type(short data) {
        this(VuforiaJNI.new_Type__SWIG_1(data), true);
    }

    public boolean isOfType(Type type) {
        return VuforiaJNI.Type_isOfType(this.swigCPtr, this, getCPtr(type), type);
    }
}
