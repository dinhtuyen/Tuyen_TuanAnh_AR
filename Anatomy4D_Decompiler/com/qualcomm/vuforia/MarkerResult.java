package com.qualcomm.vuforia;

public class MarkerResult extends TrackableResult {
    private long swigCPtr;

    protected MarkerResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.MarkerResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(MarkerResult obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_MarkerResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MarkerResult) {
            return ((MarkerResult) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.MarkerResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new Marker(VuforiaJNI.MarkerResult_getTrackable(this.swigCPtr, this), false);
    }
}
