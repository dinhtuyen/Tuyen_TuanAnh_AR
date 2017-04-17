package com.qualcomm.vuforia;

public class TrackerManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected TrackerManager(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(TrackerManager obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_TrackerManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof TrackerManager) {
            return ((TrackerManager) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static TrackerManager getInstance() {
        if (Vuforia.wasInitializedJava()) {
            return new TrackerManager(VuforiaJNI.TrackerManager_getInstance(), false);
        }
        throw new RuntimeException("Use of the Java Vuforia APIs requires initalization via the com.qualcomm.vuforia.Vuforia class");
    }

    public Tracker initTracker(Type type) {
        long cPtr = VuforiaJNI.TrackerManager_initTracker(this.swigCPtr, this, Type.getCPtr(type), type);
        if (cPtr == 0) {
            return null;
        }
        Tracker tmp = new Tracker(cPtr, false);
        if (tmp.isOfType(ImageTracker.getClassType())) {
            return new ImageTracker(cPtr, false);
        }
        if (tmp.isOfType(TextTracker.getClassType())) {
            return new TextTracker(cPtr, false);
        }
        return tmp.isOfType(MarkerTracker.getClassType()) ? new MarkerTracker(cPtr, false) : null;
    }

    public Tracker getTracker(Type type) {
        long cPtr = VuforiaJNI.TrackerManager_getTracker(this.swigCPtr, this, Type.getCPtr(type), type);
        if (cPtr == 0) {
            return null;
        }
        Tracker tmp = new Tracker(cPtr, false);
        if (tmp.isOfType(ImageTracker.getClassType())) {
            return new ImageTracker(cPtr, false);
        }
        if (tmp.isOfType(TextTracker.getClassType())) {
            return new TextTracker(cPtr, false);
        }
        return tmp.isOfType(MarkerTracker.getClassType()) ? new MarkerTracker(cPtr, false) : null;
    }

    public boolean deinitTracker(Type type) {
        return VuforiaJNI.TrackerManager_deinitTracker(this.swigCPtr, this, Type.getCPtr(type), type);
    }
}
