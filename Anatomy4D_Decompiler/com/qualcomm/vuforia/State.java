package com.qualcomm.vuforia;

public class State implements Cloneable {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected State(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(State obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_State(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public State clone() {
        return new State(this);
    }

    public State() {
        this(VuforiaJNI.new_State__SWIG_0(), true);
    }

    public State(State other) {
        this(VuforiaJNI.new_State__SWIG_1(getCPtr(other), other), true);
    }

    public Frame getFrame() {
        return new Frame(VuforiaJNI.State_getFrame(this.swigCPtr, this), true);
    }

    public int getNumTrackables() {
        return VuforiaJNI.State_getNumTrackables(this.swigCPtr, this);
    }

    public Trackable getTrackable(int idx) {
        long cPtr = VuforiaJNI.State_getTrackable(this.swigCPtr, this, idx);
        if (cPtr == 0) {
            return null;
        }
        Trackable tmp = new Trackable(cPtr, false);
        if (tmp.isOfType(ImageTarget.getClassType())) {
            return new ImageTarget(cPtr, false);
        }
        if (tmp.isOfType(Word.getClassType())) {
            return new Word(cPtr, false);
        }
        if (tmp.isOfType(CylinderTarget.getClassType())) {
            return new CylinderTarget(cPtr, false);
        }
        if (tmp.isOfType(MultiTarget.getClassType())) {
            return new MultiTarget(cPtr, false);
        }
        if (tmp.isOfType(Marker.getClassType())) {
            return new Marker(cPtr, false);
        }
        return null;
    }

    public int getNumTrackableResults() {
        return VuforiaJNI.State_getNumTrackableResults(this.swigCPtr, this);
    }

    public TrackableResult getTrackableResult(int idx) {
        long cPtr = VuforiaJNI.State_getTrackableResult(this.swigCPtr, this, idx);
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
        if (tmp.isOfType(MultiTargetResult.getClassType())) {
            return new MultiTargetResult(cPtr, false);
        }
        if (tmp.isOfType(MarkerResult.getClassType())) {
            return new MarkerResult(cPtr, false);
        }
        return null;
    }
}
