package com.unity3d.player;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;

/* renamed from: com.unity3d.player.d */
public final class C0087d implements C0086f {
    private static final SurfaceTexture f135a;
    private volatile boolean f136b;

    /* renamed from: com.unity3d.player.d.1 */
    class C00841 implements OnSystemUiVisibilityChangeListener {
        final /* synthetic */ View f131a;
        final /* synthetic */ C0087d f132b;

        C00841(C0087d c0087d, View view) {
            this.f132b = c0087d;
            this.f131a = view;
        }

        public final void onSystemUiVisibilityChange(int i) {
            this.f132b.m99a(this.f131a, 1000);
        }
    }

    /* renamed from: com.unity3d.player.d.2 */
    class C00852 implements Runnable {
        final /* synthetic */ View f133a;
        final /* synthetic */ C0087d f134b;

        C00852(C0087d c0087d, View view) {
            this.f134b = c0087d;
            this.f133a = view;
        }

        public final void run() {
            this.f134b.m103a(this.f133a, this.f134b.f136b);
        }
    }

    static {
        f135a = new SurfaceTexture(-1);
    }

    private void m99a(View view, int i) {
        Handler handler = view.getHandler();
        if (handler == null) {
            m103a(view, this.f136b);
        } else {
            handler.postDelayed(new C00852(this, view), (long) i);
        }
    }

    public final void m102a(View view) {
        view.setOnSystemUiVisibilityChangeListener(new C00841(this, view));
    }

    public final void m103a(View view, boolean z) {
        this.f136b = z;
        view.setSystemUiVisibility(this.f136b ? view.getSystemUiVisibility() | 1 : view.getSystemUiVisibility() & -2);
    }

    public final boolean m104a() {
        return this.f136b;
    }

    public final boolean m105a(Camera camera) {
        try {
            camera.setPreviewTexture(f135a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public final void m106b(View view) {
        if (this.f136b) {
            m103a(view, false);
            this.f136b = true;
            m99a(view, 500);
        }
    }
}
