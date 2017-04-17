package com.unity3d.player;

import android.app.Activity;
import android.content.ContextWrapper;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import com.qualcomm.ar.pl.SystemTools;
import com.qualcomm.vuforia.TrackableResult.STATUS;
import com.qualcomm.vuforia.WordList.STORAGE_TYPE;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.unity3d.player.j */
public final class C0092j implements C0088g {
    private final Queue f140a;
    private final Activity f141b;
    private Runnable f142c;

    /* renamed from: com.unity3d.player.j.1 */
    class C00911 implements Runnable {
        final /* synthetic */ C0092j f139a;

        C00911(C0092j c0092j) {
            this.f139a = c0092j;
        }

        private static void m110a(View view, MotionEvent motionEvent) {
            if (C0093k.f144b) {
                C0093k.f146d.m92a(view, motionEvent);
            }
        }

        public final void run() {
            while (true) {
                MotionEvent motionEvent = (MotionEvent) this.f139a.f140a.poll();
                if (motionEvent != null) {
                    View decorView = this.f139a.f141b.getWindow().getDecorView();
                    int source = motionEvent.getSource();
                    if ((source & 2) != 0) {
                        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
                            case STORAGE_TYPE.STORAGE_APP /*0*/:
                            case STORAGE_TYPE.STORAGE_APPRESOURCE /*1*/:
                            case STORAGE_TYPE.STORAGE_ABSOLUTE /*2*/:
                            case STATUS.TRACKED /*3*/:
                            case STATUS.EXTENDED_TRACKED /*4*/:
                            case SystemTools.AR_ERROR_INVALID_OPERATION /*5*/:
                            case SystemTools.AR_ERROR_OPERATION_FAILED /*6*/:
                                decorView.dispatchTouchEvent(motionEvent);
                                break;
                            default:
                                C00911.m110a(decorView, motionEvent);
                                break;
                        }
                    } else if ((source & 4) != 0) {
                        decorView.dispatchTrackballEvent(motionEvent);
                    } else {
                        C00911.m110a(decorView, motionEvent);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public C0092j(ContextWrapper contextWrapper) {
        this.f140a = new ConcurrentLinkedQueue();
        this.f142c = new C00911(this);
        this.f141b = (Activity) contextWrapper;
    }

    private static int m111a(PointerCoords[] pointerCoordsArr, float[] fArr, int i) {
        for (int i2 = 0; i2 < pointerCoordsArr.length; i2++) {
            PointerCoords pointerCoords = new PointerCoords();
            pointerCoordsArr[i2] = pointerCoords;
            int i3 = i + 1;
            pointerCoords.orientation = fArr[i];
            int i4 = i3 + 1;
            pointerCoords.pressure = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.size = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.toolMajor = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.toolMinor = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.touchMajor = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.touchMinor = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.x = fArr[i3];
            i = i4 + 1;
            pointerCoords.y = fArr[i4];
        }
        return i;
    }

    private static PointerCoords[] m113a(int i, float[] fArr) {
        PointerCoords[] pointerCoordsArr = new PointerCoords[i];
        C0092j.m111a(pointerCoordsArr, fArr, 0);
        return pointerCoordsArr;
    }

    public final void m115a(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        if (this.f141b != null) {
            MotionEvent obtain = MotionEvent.obtain(j, j2, i, i2, iArr, C0092j.m113a(i2, fArr), i3, f, f2, i4, i5, i6, i7);
            int i9 = 0;
            for (int i10 = 0; i10 < i8; i10++) {
                PointerCoords[] pointerCoordsArr = new PointerCoords[i2];
                i9 = C0092j.m111a(pointerCoordsArr, fArr2, i9);
                obtain.addBatch(jArr[i10], pointerCoordsArr, i3);
            }
            this.f140a.add(obtain);
            this.f141b.runOnUiThread(this.f142c);
        }
    }
}
