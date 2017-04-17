package com.unity3d.player;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.unity3d.player.a */
final class C0079a {
    Camera f118a;
    Parameters f119b;
    Size f120c;
    int f121d;
    int[] f122e;
    C0077b f123f;
    private final Object[] f124g;
    private final int f125h;
    private final int f126i;
    private final int f127j;
    private final int f128k;

    /* renamed from: com.unity3d.player.a.a */
    interface C0075a {
        void onCameraFrame(C0079a c0079a, byte[] bArr);
    }

    /* renamed from: com.unity3d.player.a.1 */
    class C00761 implements PreviewCallback {
        long f110a;
        final /* synthetic */ C0075a f111b;
        final /* synthetic */ C0079a f112c;

        C00761(C0079a c0079a, C0075a c0075a) {
            this.f112c = c0079a;
            this.f111b = c0075a;
            this.f110a = 0;
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            if (this.f112c.f118a == camera) {
                this.f111b.onCameraFrame(this.f112c, bArr);
            }
        }
    }

    /* renamed from: com.unity3d.player.a.2 */
    class C00782 extends C0077b {
        Camera f116a;
        final /* synthetic */ C0079a f117b;

        C00782(C0079a c0079a) {
            this.f117b = c0079a;
            super(3);
            this.f116a = this.f117b.f118a;
        }

        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            synchronized (this.f117b.f124g) {
                if (this.f117b.f118a != this.f116a) {
                    return;
                }
                try {
                    this.f117b.f118a.setPreviewDisplay(surfaceHolder);
                    this.f117b.f118a.startPreview();
                } catch (Exception e) {
                    C0089h.Log(6, "Unable to initialize webcam data stream: " + e.getMessage());
                }
            }
        }

        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            synchronized (this.f117b.f124g) {
                if (this.f117b.f118a != this.f116a) {
                    return;
                }
                this.f117b.f118a.stopPreview();
            }
        }
    }

    public C0079a(int i, int i2, int i3, int i4) {
        this.f124g = new Object[0];
        this.f125h = i;
        this.f126i = C0079a.m80a(i2, 640);
        this.f127j = C0079a.m80a(i3, 480);
        this.f128k = C0079a.m80a(i4, 24);
    }

    private static final int m80a(int i, int i2) {
        return i != 0 ? i : i2;
    }

    private static void m81a(Parameters parameters) {
        if (parameters.getSupportedColorEffects() != null) {
            parameters.setColorEffect("none");
        }
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
    }

    private void m83b(C0075a c0075a) {
        synchronized (this.f124g) {
            this.f118a = Camera.open(this.f125h);
            this.f119b = this.f118a.getParameters();
            this.f120c = m86f();
            this.f122e = m85e();
            this.f121d = m84d();
            C0079a.m81a(this.f119b);
            this.f119b.setPreviewSize(this.f120c.width, this.f120c.height);
            this.f119b.setPreviewFpsRange(this.f122e[0], this.f122e[1]);
            this.f118a.setParameters(this.f119b);
            PreviewCallback c00761 = new C00761(this, c0075a);
            int i = (((this.f120c.width * this.f120c.height) * this.f121d) / 8) + AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD;
            this.f118a.addCallbackBuffer(new byte[i]);
            this.f118a.addCallbackBuffer(new byte[i]);
            this.f118a.setPreviewCallbackWithBuffer(c00761);
        }
    }

    private final int m84d() {
        this.f119b.setPreviewFormat(17);
        return ImageFormat.getBitsPerPixel(17);
    }

    private final int[] m85e() {
        double d = (double) (this.f128k * 1000);
        List supportedPreviewFpsRange = this.f119b.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null) {
            supportedPreviewFpsRange = new ArrayList();
        }
        int[] iArr = new int[]{this.f128k * 1000, this.f128k * 1000};
        double d2 = Double.MAX_VALUE;
        for (int[] iArr2 : r0) {
            int[] iArr3;
            double d3;
            double abs = Math.abs(Math.log(d / ((double) iArr2[0]))) + Math.abs(Math.log(d / ((double) iArr2[1])));
            if (abs < d2) {
                iArr3 = iArr2;
                d3 = abs;
            } else {
                d3 = d2;
                iArr3 = iArr;
            }
            iArr = iArr3;
            d2 = d3;
        }
        return iArr;
    }

    private final Size m86f() {
        double d = (double) this.f126i;
        double d2 = (double) this.f127j;
        Size size = null;
        double d3 = Double.MAX_VALUE;
        for (Size size2 : this.f119b.getSupportedPreviewSizes()) {
            Size size3;
            double d4;
            double abs = Math.abs(Math.log(d / ((double) size2.width))) + Math.abs(Math.log(d2 / ((double) size2.height)));
            if (abs < d3) {
                size3 = size2;
                d4 = abs;
            } else {
                d4 = d3;
                size3 = size;
            }
            d3 = d4;
            size = size3;
        }
        return size;
    }

    public final int m87a() {
        return this.f125h;
    }

    public final void m88a(C0075a c0075a) {
        synchronized (this.f124g) {
            if (this.f118a == null) {
                m83b(c0075a);
            }
            if (C0093k.f143a && C0093k.f145c.m97a(this.f118a)) {
                this.f118a.startPreview();
                return;
            }
            if (this.f123f == null) {
                this.f123f = new C00782(this);
                this.f123f.m78a();
            }
        }
    }

    public final void m89a(byte[] bArr) {
        synchronized (this.f124g) {
            if (this.f118a != null) {
                this.f118a.addCallbackBuffer(bArr);
            }
        }
    }

    public final Size m90b() {
        return this.f120c;
    }

    public final void m91c() {
        synchronized (this.f124g) {
            if (this.f118a != null) {
                this.f118a.setPreviewCallbackWithBuffer(null);
                this.f118a.stopPreview();
                this.f118a.release();
                this.f118a = null;
            }
            if (this.f123f != null) {
                this.f123f.m79b();
                this.f123f = null;
            }
        }
    }
}
