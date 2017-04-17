package com.qualcomm.vuforia;

public class CylinderTargetResult extends TrackableResult {
    private long swigCPtr;

    protected CylinderTargetResult(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.CylinderTargetResult_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(CylinderTargetResult obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_CylinderTargetResult(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CylinderTargetResult) {
            return ((CylinderTargetResult) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.CylinderTargetResult_getClassType(), true);
    }

    public Trackable getTrackable() {
        return new CylinderTarget(VuforiaJNI.CylinderTargetResult_getTrackable(this.swigCPtr, this), false);
    }
}
