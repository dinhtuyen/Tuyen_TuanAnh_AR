package com.qualcomm.vuforia;

public class TrackableResult {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    public static final class STATUS {
        public static final int DETECTED = 2;
        public static final int EXTENDED_TRACKED = 4;
        public static final int TRACKED = 3;
        public static final int UNDEFINED = 1;
        public static final int UNKNOWN = 0;

        private STATUS() {
        }
    }

    protected TrackableResult(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(TrackableResult obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_TrackableResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof TrackableResult) {
            return ((TrackableResult) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.TrackableResult_getClassType(), true);
    }

    public Type getType() {
        return new Type(VuforiaJNI.TrackableResult_getType(this.swigCPtr, this), true);
    }

    public boolean isOfType(Type type) {
        return VuforiaJNI.TrackableResult_isOfType(this.swigCPtr, this, Type.getCPtr(type), type);
    }

    public int getStatus() {
        return VuforiaJNI.TrackableResult_getStatus(this.swigCPtr, this);
    }

    public Trackable getTrackable() {
        long cPtr = VuforiaJNI.TrackableResult_getTrackable(this.swigCPtr, this);
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

    public Matrix34F getPose() {
        return new Matrix34F(VuforiaJNI.TrackableResult_getPose(this.swigCPtr, this), false);
    }
}
