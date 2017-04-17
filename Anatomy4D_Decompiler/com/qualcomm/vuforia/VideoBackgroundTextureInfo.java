package com.qualcomm.vuforia;

public class VideoBackgroundTextureInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected VideoBackgroundTextureInfo(long cPtr, boolean cMemoryOwn) {
        this.swigCMemOwn = cMemoryOwn;
        this.swigCPtr = cPtr;
    }

    protected static long getCPtr(VideoBackgroundTextureInfo obj) {
        return obj == null ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    protected synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                VuforiaJNI.delete_VideoBackgroundTextureInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof VideoBackgroundTextureInfo) {
            return ((VideoBackgroundTextureInfo) obj).swigCPtr == this.swigCPtr;
        } else {
            return false;
        }
    }

    public void setTextureSize(Vec2I value) {
        VuforiaJNI.VideoBackgroundTextureInfo_TextureSize_set(this.swigCPtr, this, Vec2I.getCPtr(value), value);
    }

    public Vec2I getTextureSize() {
        long cPtr = VuforiaJNI.VideoBackgroundTextureInfo_TextureSize_get(this.swigCPtr, this);
        return cPtr == 0 ? null : new Vec2I(cPtr, false);
    }

    public void setImageSize(Vec2I value) {
        VuforiaJNI.VideoBackgroundTextureInfo_ImageSize_set(this.swigCPtr, this, Vec2I.getCPtr(value), value);
    }

    public Vec2I getImageSize() {
        long cPtr = VuforiaJNI.VideoBackgroundTextureInfo_ImageSize_get(this.swigCPtr, this);
        return cPtr == 0 ? null : new Vec2I(cPtr, false);
    }

    public void setPixelFormat(int value) {
        VuforiaJNI.VideoBackgroundTextureInfo_PixelFormat_set(this.swigCPtr, this, value);
    }

    public int getPixelFormat() {
        return VuforiaJNI.VideoBackgroundTextureInfo_PixelFormat_get(this.swigCPtr, this);
    }

    public VideoBackgroundTextureInfo() {
        this(VuforiaJNI.new_VideoBackgroundTextureInfo(), true);
    }
}
