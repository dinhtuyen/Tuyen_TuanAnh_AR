package com.qualcomm.vuforia;

public class MultiTarget extends Trackable {
    private long swigCPtr;

    protected MultiTarget(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.MultiTarget_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(MultiTarget obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_MultiTarget(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MultiTarget) {
            return ((MultiTarget) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.MultiTarget_getClassType(), true);
    }

    public int getNumParts() {
        return VuforiaJNI.MultiTarget_getNumParts(this.swigCPtr, this);
    }

    public Trackable getPart(int idx) {
        long cPtr = VuforiaJNI.MultiTarget_getPart__SWIG_0(this.swigCPtr, this, idx);
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
        if (tmp.isOfType(getClassType())) {
            return new MultiTarget(cPtr, false);
        }
        if (tmp.isOfType(Marker.getClassType())) {
            return new Marker(cPtr, false);
        }
        return null;
    }

    public Trackable getPart(String name) {
        long cPtr = VuforiaJNI.MultiTarget_getPart__SWIG_1(this.swigCPtr, this, name);
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
        if (tmp.isOfType(getClassType())) {
            return new MultiTarget(cPtr, false);
        }
        if (tmp.isOfType(Marker.getClassType())) {
            return new Marker(cPtr, false);
        }
        return null;
    }

    public int addPart(Trackable trackable) {
        return VuforiaJNI.MultiTarget_addPart(this.swigCPtr, this, Trackable.getCPtr(trackable), trackable);
    }

    public boolean removePart(int idx) {
        return VuforiaJNI.MultiTarget_removePart(this.swigCPtr, this, idx);
    }

    public boolean setPartOffset(int idx, Matrix34F offset) {
        return VuforiaJNI.MultiTarget_setPartOffset(this.swigCPtr, this, idx, Matrix34F.getCPtr(offset), offset);
    }

    public boolean getPartOffset(int idx, Matrix34F offset) {
        return VuforiaJNI.MultiTarget_getPartOffset(this.swigCPtr, this, idx, Matrix34F.getCPtr(offset), offset);
    }
}
