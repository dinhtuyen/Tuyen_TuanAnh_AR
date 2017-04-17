package com.qualcomm.vuforia;

public class ImageTracker extends Tracker {
    private long swigCPtr;

    protected ImageTracker(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.ImageTracker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(ImageTracker obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_ImageTracker(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageTracker) {
            return ((ImageTracker) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.ImageTracker_getClassType(), true);
    }

    public DataSet createDataSet() {
        long cPtr = VuforiaJNI.ImageTracker_createDataSet(this.swigCPtr, this);
        return cPtr == 0 ? null : new DataSet(cPtr, false);
    }

    public boolean destroyDataSet(DataSet dataset) {
        return VuforiaJNI.ImageTracker_destroyDataSet(this.swigCPtr, this, DataSet.getCPtr(dataset), dataset);
    }

    public boolean activateDataSet(DataSet dataset) {
        return VuforiaJNI.ImageTracker_activateDataSet(this.swigCPtr, this, DataSet.getCPtr(dataset), dataset);
    }

    public boolean deactivateDataSet(DataSet dataset) {
        return VuforiaJNI.ImageTracker_deactivateDataSet(this.swigCPtr, this, DataSet.getCPtr(dataset), dataset);
    }

    public DataSet getActiveDataSet(int idx) {
        long cPtr = VuforiaJNI.ImageTracker_getActiveDataSet__SWIG_0(this.swigCPtr, this, idx);
        return cPtr == 0 ? null : new DataSet(cPtr, false);
    }

    public DataSet getActiveDataSet() {
        long cPtr = VuforiaJNI.ImageTracker_getActiveDataSet__SWIG_1(this.swigCPtr, this);
        return cPtr == 0 ? null : new DataSet(cPtr, false);
    }

    public int getActiveDataSetCount() {
        return VuforiaJNI.ImageTracker_getActiveDataSetCount(this.swigCPtr, this);
    }

    public ImageTargetBuilder getImageTargetBuilder() {
        long cPtr = VuforiaJNI.ImageTracker_getImageTargetBuilder(this.swigCPtr, this);
        return cPtr == 0 ? null : new ImageTargetBuilder(cPtr, false);
    }

    public TargetFinder getTargetFinder() {
        long cPtr = VuforiaJNI.ImageTracker_getTargetFinder(this.swigCPtr, this);
        return cPtr == 0 ? null : new TargetFinder(cPtr, false);
    }

    public boolean persistExtendedTracking(boolean on) {
        return VuforiaJNI.ImageTracker_persistExtendedTracking(this.swigCPtr, this, on);
    }

    public boolean resetExtendedTracking() {
        return VuforiaJNI.ImageTracker_resetExtendedTracking(this.swigCPtr, this);
    }
}
