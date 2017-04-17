package com.unity3d.player;

import android.app.Activity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/* renamed from: com.unity3d.player.b */
abstract class C0077b implements Callback {
    private final Activity f113a;
    private final int f114b;
    private SurfaceView f115c;

    /* renamed from: com.unity3d.player.b.1 */
    class C00801 implements Runnable {
        final /* synthetic */ C0077b f129a;

        C00801(C0077b c0077b) {
            this.f129a = c0077b;
        }

        public final void run() {
            if (this.f129a.f115c == null) {
                this.f129a.f115c = new SurfaceView(C0099n.f160a.m137a());
                this.f129a.f115c.getHolder().setType(this.f129a.f114b);
                this.f129a.f115c.getHolder().addCallback(this.f129a);
                C0099n.f160a.m138a(this.f129a.f115c);
                this.f129a.f115c.setVisibility(0);
            }
        }
    }

    /* renamed from: com.unity3d.player.b.2 */
    class C00812 implements Runnable {
        final /* synthetic */ C0077b f130a;

        C00812(C0077b c0077b) {
            this.f130a = c0077b;
        }

        public final void run() {
            if (this.f130a.f115c != null) {
                C0099n.f160a.m139b(this.f130a.f115c);
            }
            this.f130a.f115c = null;
        }
    }

    C0077b(int i) {
        this.f113a = (Activity) C0099n.f160a.m137a();
        this.f114b = 3;
    }

    final void m78a() {
        this.f113a.runOnUiThread(new C00801(this));
    }

    final void m79b() {
        this.f113a.runOnUiThread(new C00812(this));
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
