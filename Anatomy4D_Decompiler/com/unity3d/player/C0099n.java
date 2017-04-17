package com.unity3d.player;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.unity3d.player.n */
final class C0099n {
    public static C0099n f160a;
    private final FrameLayout f161b;
    private Set f162c;
    private View f163d;
    private View f164e;

    C0099n(FrameLayout frameLayout) {
        this.f162c = new HashSet();
        this.f161b = frameLayout;
        f160a = this;
    }

    private void m135e(View view) {
        this.f161b.addView(view, this.f161b.getChildCount());
    }

    private void m136f(View view) {
        this.f161b.removeView(view);
        this.f161b.requestLayout();
    }

    public final Context m137a() {
        return this.f161b.getContext();
    }

    public final void m138a(View view) {
        this.f162c.add(view);
        if (this.f163d != null) {
            m135e(view);
        }
    }

    public final void m139b(View view) {
        this.f162c.remove(view);
        if (this.f163d != null) {
            m136f(view);
        }
    }

    public final void m140c(View view) {
        if (this.f163d != view) {
            this.f163d = view;
            this.f161b.addView(view);
            for (View e : this.f162c) {
                m135e(e);
            }
            if (this.f164e != null) {
                this.f164e.setVisibility(4);
            }
        }
    }

    public final void m141d(View view) {
        if (this.f163d == view) {
            for (View f : this.f162c) {
                m136f(f);
            }
            this.f161b.removeView(view);
            this.f163d = null;
            if (this.f164e != null) {
                this.f164e.setVisibility(0);
            }
        }
    }
}
