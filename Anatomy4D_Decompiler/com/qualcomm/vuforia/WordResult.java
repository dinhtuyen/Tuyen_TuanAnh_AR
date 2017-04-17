package com.qualcomm.vuforia;

public class WordResult extends TrackableResult {
    private long swigCPtr;

    protected WordResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.WordResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(WordResult obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_WordResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WordResult) {
            return ((WordResult) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.WordResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new Word(VuforiaJNI.WordResult_getTrackable(this.swigCPtr, this), false);
    }

    public Obb2D getObb() {
        return new Obb2D(VuforiaJNI.WordResult_getObb(this.swigCPtr, this), false);
    }
}
