package com.qualcomm.vuforia;

public class MultiTargetResult extends TrackableResult {
    private long swigCPtr;

    protected MultiTargetResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.MultiTargetResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(MultiTargetResult obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_MultiTargetResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MultiTargetResult) {
            return ((MultiTargetResult) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.MultiTargetResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new MultiTarget(VuforiaJNI.MultiTargetResult_getTrackable(this.swigCPtr, this), false);
    }

    public int getNumPartResults() {
        return VuforiaJNI.MultiTargetResult_getNumPartResults(this.swigCPtr, this);
    }

    public TrackableResult getPartResult(int idx) {
        long cPtr = VuforiaJNI.MultiTargetResult_getPartResult__SWIG_0(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        TrackableResult tmp = new TrackableResult(cPtr, false);
        if (tmp.isOfType(ImageTargetResult.getClassType())) {
            return new ImageTargetResult(cPtr, false);
        }
        if (tmp.isOfType(WordResult.getClassType())) {
            return new WordResult(cPtr, false);
        }
        if (tmp.isOfType(CylinderTargetResult.getClassType())) {
            return new CylinderTargetResult(cPtr, false);
        }
        if (tmp.isOfType(getClassType())) {
            return new MultiTargetResult(cPtr, false);
        }
        if (tmp.isOfType(MarkerResult.getClassType())) {
            return new MarkerResult(cPtr, false);
        }
        return null;
    }

    public TrackableResult getPartResult(String name) {
        long cPtr = VuforiaJNI.MultiTargetResult_getPartResult__SWIG_1(this.swigCPtr, this, name);
        if (cPtr == 0) {
            return null;
        }
        TrackableResult tmp = new TrackableResult(cPtr, false);
        if (tmp.isOfType(ImageTargetResult.getClassType())) {
            return new ImageTargetResult(cPtr, false);
        }
        if (tmp.isOfType(WordResult.getClassType())) {
            return new WordResult(cPtr, false);
        }
        if (tmp.isOfType(CylinderTargetResult.getClassType())) {
            return new CylinderTargetResult(cPtr, false);
        }
        if (tmp.isOfType(getClassType())) {
            return new MultiTargetResult(cPtr, false);
        }
        if (tmp.isOfType(MarkerResult.getClassType())) {
            return new MarkerResult(cPtr, false);
        }
        return null;
    }
}
