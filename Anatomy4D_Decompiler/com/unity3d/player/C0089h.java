package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.h */
final class C0089h {
    protected static boolean f137a;

    static {
        f137a = false;
    }

    protected static void Log(int i, String str) {
        if (!f137a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
