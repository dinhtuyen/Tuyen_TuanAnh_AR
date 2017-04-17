package com.qualcomm.ar.pl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.qualcomm.ar.pl.CameraPreview.CameraCacheInfo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SurfaceManager {
    private static final String MODULENAME = "SurfaceManager";
    Lock addSurfaceLock;
    View cameraSurfaceParentView;
    CameraCacheInfo cciForSurface;
    GLSurfaceView glSurfaceView;
    int glSurfaceViewChildPosition;
    boolean renderWhenDirtyEnabled;
    Lock viewLock;

    /* renamed from: com.qualcomm.ar.pl.SurfaceManager.1 */
    class C00581 implements Runnable {
        C00581() {
        }

        public void run() {
            SurfaceManager.this.addSurfaceLock.lock();
            SurfaceManager.this.retrieveGLSurfaceView();
            try {
                SurfaceManager.this.setupCameraSurface(SurfaceManager.this.cciForSurface);
                SurfaceManager.this.cameraSurfaceParentView.addView(SurfaceManager.this.cciForSurface.surface, SurfaceManager.this.glSurfaceViewChildPosition + 1, new LayoutParams(-1, -1));
                SurfaceManager.this.cciForSurface.surface.setVisibility(0);
            } catch (Exception e) {
            } finally {
                SurfaceManager.this.addSurfaceLock.unlock();
            }
        }
    }

    public SurfaceManager() {
        this.renderWhenDirtyEnabled = false;
        this.glSurfaceView = null;
        this.glSurfaceViewChildPosition = 0;
        this.cameraSurfaceParentView = null;
        this.viewLock = new ReentrantLock();
        this.addSurfaceLock = new ReentrantLock();
    }

    private GLSurfaceView searchForGLSurfaceView(View rootView) {
        GLSurfaceView result = null;
        this.glSurfaceViewChildPosition = 0;
        try {
            ViewGroup rootViewGroup = (ViewGroup) rootView;
            int numChildren = rootViewGroup.getChildCount();
            for (int i = 0; i < numChildren; i++) {
                View childView = rootViewGroup.getChildAt(i);
                if (childView instanceof GLSurfaceView) {
                    result = (GLSurfaceView) childView;
                    this.glSurfaceViewChildPosition = i;
                    break;
                }
                if (childView instanceof ViewGroup) {
                    result = searchForGLSurfaceView(childView);
                    if (result != null) {
                        break;
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean applyRenderWhenDirty() {
        int i = 0;
        if (this.glSurfaceView == null) {
            return false;
        }
        GLSurfaceView gLSurfaceView = this.glSurfaceView;
        if (!this.renderWhenDirtyEnabled) {
            i = 1;
        }
        gLSurfaceView.setRenderMode(i);
        return true;
    }

    private void setupCameraSurface(CameraCacheInfo cci) {
        if (cci.surface == null) {
            cci.surface = new CameraSurface(SystemTools.getActivityFromNative());
        } else if (cci.surface.getParent() != null && ViewGroup.class.isInstance(cci.surface.getParent())) {
            ((ViewGroup) cci.surface.getParent()).removeView(cci.surface);
        }
        cci.surface.setCamera(cci.camera);
    }

    public boolean retrieveGLSurfaceView() {
        try {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity == null) {
                return false;
            }
            boolean z;
            View decorView = activity.getWindow().getDecorView();
            this.glSurfaceView = searchForGLSurfaceView(decorView);
            if (this.glSurfaceView == null) {
                this.cameraSurfaceParentView = decorView;
            } else {
                this.cameraSurfaceParentView = (View) this.glSurfaceView.getParent();
                applyRenderWhenDirty();
            }
            if (this.glSurfaceView != null) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setEnableRenderWhenDirty(boolean enabled) {
        this.renderWhenDirtyEnabled = enabled;
        return applyRenderWhenDirty();
    }

    public void requestRender() {
        if (this.glSurfaceView == null) {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity != null) {
                this.glSurfaceView = searchForGLSurfaceView(activity.getWindow().getDecorView());
            }
        }
        if (this.glSurfaceView != null) {
            this.glSurfaceView.requestRender();
        }
    }

    public boolean addCameraSurface(CameraCacheInfo cci) {
        Activity activity = SystemTools.getActivityFromNative();
        if (activity == null) {
            return false;
        }
        this.cciForSurface = cci;
        boolean didExceptionHappen = false;
        this.viewLock.lock();
        try {
            activity.runOnUiThread(new C00581());
        } catch (Exception e) {
            didExceptionHappen = true;
        } finally {
            this.viewLock.unlock();
        }
        if (didExceptionHappen) {
            return false;
        }
        return true;
    }
}
