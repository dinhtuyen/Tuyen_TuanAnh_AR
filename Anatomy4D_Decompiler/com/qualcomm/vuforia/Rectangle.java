package com.qualcomm.vuforia;

public class Rectangle extends Area {
    private long swigCPtr;

    protected Rectangle(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.Rectangle_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Rectangle obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Rectangle(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            return ((Rectangle) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public Rectangle() {
        this(VuforiaJNI.new_Rectangle__SWIG_0(), true);
    }

    public Rectangle(Rectangle other) {
        this(VuforiaJNI.new_Rectangle__SWIG_1(getCPtr(other), other), true);
    }

    public Rectangle(float leftTopX, float leftTopY, float rightBottomX, float rightBottomY) {
        this(VuforiaJNI.new_Rectangle__SWIG_2(leftTopX, leftTopY, rightBottomX, rightBottomY), true);
    }

    public float getLeftTopX() {
        return VuforiaJNI.Rectangle_getLeftTopX(this.swigCPtr, this);
    }

    public float getLeftTopY() {
        return VuforiaJNI.Rectangle_getLeftTopY(this.swigCPtr, this);
    }

    public float getRightBottomX() {
        return VuforiaJNI.Rectangle_getRightBottomX(this.swigCPtr, this);
    }

    public float getRightBottomY() {
        return VuforiaJNI.Rectangle_getRightBottomY(this.swigCPtr, this);
    }

    public float getWidth() {
        return VuforiaJNI.Rectangle_getWidth(this.swigCPtr, this);
    }

    public float getHeight() {
        return VuforiaJNI.Rectangle_getHeight(this.swigCPtr, this);
    }

    public float getAreaSize() {
        return VuforiaJNI.Rectangle_getAreaSize(this.swigCPtr, this);
    }

    public int getType() {
        return VuforiaJNI.Rectangle_getType(this.swigCPtr, this);
    }
}
