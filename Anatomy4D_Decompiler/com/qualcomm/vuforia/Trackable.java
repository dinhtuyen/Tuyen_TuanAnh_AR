package com.qualcomm.vuforia;

public class Trackable {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Trackable(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Trackable obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Trackable(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public Object getUserData() {
        return Vuforia.retreiveFromUserDataMap(Integer.valueOf(getId()));
    }

    public boolean setUserData(Object userData) {
        return Vuforia.updateUserDataMap(Integer.valueOf(getId()), userData);
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.Trackable_getClassType(), true);
    }

    public Type getType() {
        return new Type(VuforiaJNI.Trackable_getType(this.swigCPtr, this), true);
    }

    public boolean isOfType(Type type) {
        return VuforiaJNI.Trackable_isOfType(this.swigCPtr, this, Type.getCPtr(type), type);
    }

    public int getId() {
        return VuforiaJNI.Trackable_getId(this.swigCPtr, this);
    }

    public String getName() {
        return VuforiaJNI.Trackable_getName(this.swigCPtr, this);
    }

    public boolean startExtendedTracking() {
        return VuforiaJNI.Trackable_startExtendedTracking(this.swigCPtr, this);
    }

    public boolean stopExtendedTracking() {
        return VuforiaJNI.Trackable_stopExtendedTracking(this.swigCPtr, this);
    }
}
