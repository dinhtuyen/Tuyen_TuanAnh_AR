package com.unity3d.player;

import android.os.Build.VERSION;

/* renamed from: com.unity3d.player.k */
public final class C0093k {
    static final boolean f143a;
    static final boolean f144b;
    static final C0086f f145c;
    static final C0082e f146d;

    static {
        C0082e c0082e = null;
        boolean z = true;
        f143a = VERSION.SDK_INT >= 11;
        if (VERSION.SDK_INT < 12) {
            z = false;
        }
        f144b = z;
        f145c = f143a ? new C0087d() : null;
        if (f144b) {
            c0082e = new C0083c();
        }
        f146d = c0082e;
    }
}
