package com.qualcomm.vuforia;

public class DataSet {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class STORAGE_TYPE {
        public static final int STORAGE_ABSOLUTE = 2;
        public static final int STORAGE_APP = 0;
        public static final int STORAGE_APPRESOURCE = 1;

        private STORAGE_TYPE() {
        }
    }

    protected DataSet(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(DataSet obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_DataSet(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof DataSet) {
            return ((DataSet) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static boolean exists(String path, int storageType) {
        return VuforiaJNI.DataSet_exists(path, storageType);
    }

    public boolean load(String path, int storageType) {
        return VuforiaJNI.DataSet_load(this.swigCPtr, this, path, storageType);
    }

    public int getNumTrackables() {
        return VuforiaJNI.DataSet_getNumTrackables(this.swigCPtr, this);
    }

    public Trackable getTrackable(int idx) {
        long cPtr = VuforiaJNI.DataSet_getTrackable(this.swigCPtr, this, idx);
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

    public Trackable createTrackable(TrackableSource source) {
        long cPtr = VuforiaJNI.DataSet_createTrackable(this.swigCPtr, this, TrackableSource.getCPtr(source), source);
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
        return tmp.isOfType(Marker.getClassType()) ? new Marker(cPtr, false) : null;
    }

    public MultiTarget createMultiTarget(String name) {
        long cPtr = VuforiaJNI.DataSet_createMultiTarget(this.swigCPtr, this, name);
        return cPtr == 0 ? null : new MultiTarget(cPtr, false);
    }

    public boolean destroy(Trackable trackable) {
        return VuforiaJNI.DataSet_destroy(this.swigCPtr, this, Trackable.getCPtr(trackable), trackable);
    }

    public boolean hasReachedTrackableLimit() {
        return VuforiaJNI.DataSet_hasReachedTrackableLimit(this.swigCPtr, this);
    }

    public boolean isActive() {
        return VuforiaJNI.DataSet_isActive(this.swigCPtr, this);
    }
}
