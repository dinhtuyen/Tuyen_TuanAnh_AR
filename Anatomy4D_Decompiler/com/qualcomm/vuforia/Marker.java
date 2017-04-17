package com.qualcomm.vuforia;

public class Marker extends Trackable {
    private long swigCPtr;

    public static final class MARKER_TYPE {
        public static final int ID_FRAME = 1;
        public static final int INVALID = 0;

        private MARKER_TYPE() {
        }
    }

    protected Marker(long cPtr, boolean cMemoryOwn) {
        super(VuforiaJNI.Marker_SWIGUpcast(cPtr), cMemoryOwn);
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(Marker obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_Marker(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Marker) {
            return ((Marker) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public static Type getClassType() {
        return new Type(VuforiaJNI.Marker_getClassType(), true);
    }

    public Vec2F getSize() {
        return new Vec2F(VuforiaJNI.Marker_getSize(this.swigCPtr, this), true);
    }

    public boolean setSize(Vec2F size) {
        return VuforiaJNI.Marker_setSize(this.swigCPtr, this, Vec2F.getCPtr(size), size);
    }

    public int getMarkerId() {
        return VuforiaJNI.Marker_getMarkerId(this.swigCPtr, this);
    }

    public int getMarkerType() {
        return VuforiaJNI.Marker_getMarkerType(this.swigCPtr, this);
    }
}
