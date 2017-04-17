package com.unity3d.player;

/* renamed from: com.unity3d.player.p */
final class C0101p {
    private static boolean f166a;
    private boolean f167b;
    private boolean f168c;
    private boolean f169d;

    static {
        f166a = false;
    }

    C0101p() {
        this.f167b = false;
        this.f168c = false;
        this.f169d = true;
    }

    static void m143a() {
        f166a = true;
    }

    static void m144b() {
        f166a = false;
    }

    static boolean m145c() {
        return f166a;
    }

    final void m146a(boolean z) {
        this.f167b = z;
    }

    final void m147b(boolean z) {
        this.f169d = z;
    }

    final void m148c(boolean z) {
        this.f168c = z;
    }

    final boolean m149d() {
        return this.f169d;
    }

    final boolean m150e() {
        return f166a && this.f167b && !this.f169d && !this.f168c;
    }

    final boolean m151f() {
        return this.f168c;
    }

    public final String toString() {
        return super.toString();
    }
}
