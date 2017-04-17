package com.qualcomm.vuforia;

class UpdateCallback {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected UpdateCallback(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(UpdateCallback obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_UpdateCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        VuforiaJNI.UpdateCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        VuforiaJNI.UpdateCallback_change_ownership(this, this.swigCPtr, true);
    }

    public boolean equals(Object obj) {
        if (obj instanceof UpdateCallback) {
            return ((UpdateCallback) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public void QCAR_onUpdate(State state) {
        VuforiaJNI.UpdateCallback_QCAR_onUpdate(this.swigCPtr, this, State.getCPtr(state), state);
    }

    public UpdateCallback() {
        this(VuforiaJNI.new_UpdateCallback(), true);
        VuforiaJNI.UpdateCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
