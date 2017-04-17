package com.qualcomm.vuforia;

public class ImageTargetResult extends TrackableResult {
    private long swigCPtr;

    protected ImageTargetResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.ImageTargetResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ImageTargetResult obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_ImageTargetResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageTargetResult) {
            return ((ImageTargetResult) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ImageTargetResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new ImageTarget(VuforiaJNI.ImageTargetResult_getTrackable(this.swigCPtr, this), false);
    }

    public int getNumVirtualButtons() {
        return VuforiaJNI.ImageTargetResult_getNumVirtualButtons(this.swigCPtr, this);
    }

    public VirtualButtonResult getVirtualButtonResult(int idx) {
        long cPtr = VuforiaJNI.ImageTargetResult_getVirtualButtonResult__SWIG_0(this.swigCPtr, this, idx);
        return cPtr == 0 ? null : new VirtualButtonResult(cPtr, false);
    }

    public VirtualButtonResult getVirtualButtonResult(String name) {
        long cPtr = VuforiaJNI.ImageTargetResult_getVirtualButtonResult__SWIG_1(this.swigCPtr, this, name);
        return cPtr == 0 ? null : new VirtualButtonResult(cPtr, false);
    }
}
