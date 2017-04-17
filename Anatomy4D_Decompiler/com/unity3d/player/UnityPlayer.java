package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.provider.Settings.System;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import com.unity3d.player.C0079a.C0075a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.fmod.FMODAudioDevice;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UnityPlayer extends FrameLayout implements C0075a {
    public static Activity currentActivity;
    private static boolean f71s;
    private String f72A;
    private NetworkInfo f73B;
    private Bundle f74C;
    private List f75D;
    private C0103q f76E;
    private ProgressBar f77F;
    private Runnable f78G;
    private Runnable f79H;
    C0074a f80a;
    C0098m f81b;
    private boolean f82c;
    private boolean f83d;
    private boolean f84e;
    private final C0088g f85f;
    private final C0099n f86g;
    private boolean f87h;
    private C0101p f88i;
    private final ConcurrentLinkedQueue f89j;
    private BroadcastReceiver f90k;
    private boolean f91l;
    private ContextWrapper f92m;
    private SurfaceView f93n;
    private WakeLock f94o;
    private SensorManager f95p;
    private WindowManager f96q;
    private FMODAudioDevice f97r;
    private boolean f98t;
    private boolean f99u;
    private int f100v;
    private int f101w;
    private int f102x;
    private int f103y;
    private final C0094l f104z;

    /* renamed from: com.unity3d.player.UnityPlayer.b */
    private abstract class C0063b implements Runnable {
        final /* synthetic */ UnityPlayer f9f;

        private C0063b(UnityPlayer unityPlayer) {
            this.f9f = unityPlayer;
        }

        public abstract void m8a();

        public final void run() {
            if (!this.f9f.isFinishing()) {
                m8a();
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.10 */
    class AnonymousClass10 extends C0063b {
        final /* synthetic */ KeyEvent f10a;
        final /* synthetic */ UnityPlayer f11b;

        AnonymousClass10(UnityPlayer unityPlayer, KeyEvent keyEvent) {
            this.f11b = unityPlayer;
            this.f10a = keyEvent;
            super((byte) 0);
        }

        public final void m9a() {
            this.f11b.nativeInjectEvent(this.f10a);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.11 */
    class AnonymousClass11 extends C0063b {
        final /* synthetic */ MotionEvent f12a;
        final /* synthetic */ UnityPlayer f13b;

        AnonymousClass11(UnityPlayer unityPlayer, MotionEvent motionEvent) {
            this.f13b = unityPlayer;
            this.f12a = motionEvent;
            super((byte) 0);
        }

        public final void m10a() {
            this.f13b.nativeInjectEvent(this.f12a);
            this.f12a.recycle();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.13 */
    class AnonymousClass13 extends C0063b {
        final /* synthetic */ KeyEvent f19a;
        final /* synthetic */ UnityPlayer f20b;

        AnonymousClass13(UnityPlayer unityPlayer, KeyEvent keyEvent) {
            this.f20b = unityPlayer;
            this.f19a = keyEvent;
            super((byte) 0);
        }

        public final void m12a() {
            this.f20b.nativeKeysPressed(this.f19a.getCharacters());
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.14 */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ Semaphore f21a;
        final /* synthetic */ UnityPlayer f22b;

        AnonymousClass14(UnityPlayer unityPlayer, Semaphore semaphore) {
            this.f22b = unityPlayer;
            this.f21a = semaphore;
        }

        public final void run() {
            this.f22b.m54f();
            this.f21a.release();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.15 */
    class AnonymousClass15 implements Runnable {
        final /* synthetic */ Semaphore f23a;
        final /* synthetic */ UnityPlayer f24b;

        AnonymousClass15(UnityPlayer unityPlayer, Semaphore semaphore) {
            this.f24b = unityPlayer;
            this.f23a = semaphore;
        }

        public final void run() {
            if (this.f24b.nativePause()) {
                this.f24b.m54f();
                this.f23a.release(2);
                return;
            }
            this.f23a.release();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.17 */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ boolean f26a;
        final /* synthetic */ UnityPlayer f27b;

        AnonymousClass17(UnityPlayer unityPlayer, boolean z) {
            this.f27b = unityPlayer;
            this.f26a = z;
        }

        public final void run() {
            this.f27b.nativeFocusChanged(this.f26a);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.1 */
    class C00651 implements OnClickListener {
        final /* synthetic */ UnityPlayer f30a;

        C00651(UnityPlayer unityPlayer) {
            this.f30a = unityPlayer;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.f30a.m40b();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.2 */
    class C00662 implements Runnable {
        final /* synthetic */ boolean f31a;
        final /* synthetic */ boolean f32b;
        final /* synthetic */ SurfaceView f33c;
        final /* synthetic */ int f34d;
        final /* synthetic */ int f35e;
        final /* synthetic */ boolean f36f;
        final /* synthetic */ UnityPlayer f37g;

        C00662(UnityPlayer unityPlayer, boolean z, boolean z2, SurfaceView surfaceView, int i, int i2, boolean z3) {
            this.f37g = unityPlayer;
            this.f31a = z;
            this.f32b = z2;
            this.f33c = surfaceView;
            this.f34d = i;
            this.f35e = i2;
            this.f36f = z3;
        }

        public final void run() {
            if (!this.f31a) {
                if (this.f32b) {
                    this.f33c.getHolder().setSizeFromLayout();
                } else {
                    this.f33c.getHolder().setFixedSize(this.f34d, this.f35e);
                }
                this.f33c.invalidate();
            }
            if (C0093k.f143a) {
                C0093k.f145c.m95a(this.f37g, this.f36f);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.3 */
    class C00673 implements Runnable {
        final /* synthetic */ UnityPlayer f38a;
        final /* synthetic */ String f39b;
        final /* synthetic */ int f40c;
        final /* synthetic */ boolean f41d;
        final /* synthetic */ boolean f42e;
        final /* synthetic */ boolean f43f;
        final /* synthetic */ boolean f44g;
        final /* synthetic */ String f45h;
        final /* synthetic */ UnityPlayer f46i;

        C00673(UnityPlayer unityPlayer, UnityPlayer unityPlayer2, String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
            this.f46i = unityPlayer;
            this.f38a = unityPlayer2;
            this.f39b = str;
            this.f40c = i;
            this.f41d = z;
            this.f42e = z2;
            this.f43f = z3;
            this.f44g = z4;
            this.f45h = str2;
        }

        public final void run() {
            if (this.f46i.f83d) {
                ((InputMethodManager) this.f38a.f92m.getSystemService("input_method")).toggleSoftInput(0, 1);
                this.f46i.f84e = true;
                return;
            }
            UnityPlayer unityPlayer = this.f46i;
            Context o = this.f46i.f92m;
            UnityPlayer unityPlayer2 = this.f38a;
            String str = this.f39b;
            int i = this.f40c;
            boolean z = this.f41d;
            boolean z2 = this.f42e;
            boolean z3 = this.f43f;
            boolean z4 = this.f44g;
            unityPlayer.f81b = new C0098m(o, unityPlayer2, str, i, z, z2, z3, this.f45h);
            this.f46i.f81b.show();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.4 */
    class C00684 implements Runnable {
        final /* synthetic */ UnityPlayer f47a;

        C00684(UnityPlayer unityPlayer) {
            this.f47a = unityPlayer;
        }

        public final void run() {
            if (this.f47a.f84e) {
                ((InputMethodManager) this.f47a.f92m.getSystemService("input_method")).toggleSoftInput(1, 0);
                this.f47a.f84e = false;
            } else if (this.f47a.f81b != null) {
                this.f47a.f81b.dismiss();
                this.f47a.f81b = null;
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.5 */
    class C00695 implements Runnable {
        final /* synthetic */ String f48a;
        final /* synthetic */ UnityPlayer f49b;

        C00695(UnityPlayer unityPlayer, String str) {
            this.f49b = unityPlayer;
            this.f48a = str;
        }

        public final void run() {
            if (this.f49b.f81b != null && this.f48a != null) {
                this.f49b.f81b.m134a(this.f48a);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.6 */
    class C00706 extends C0063b {
        final /* synthetic */ boolean f50a;
        final /* synthetic */ String f51b;
        final /* synthetic */ int f52c;
        final /* synthetic */ UnityPlayer f53d;

        C00706(UnityPlayer unityPlayer, boolean z, String str, int i) {
            this.f53d = unityPlayer;
            this.f50a = z;
            this.f51b = str;
            this.f52c = i;
            super((byte) 0);
        }

        public final void m13a() {
            if (this.f50a) {
                this.f53d.nativeSetInputCanceled(true);
            } else if (this.f51b != null) {
                this.f53d.nativeSetInputString(this.f51b);
            }
            if (this.f52c == 1) {
                this.f53d.nativeSoftInputClosed();
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.7 */
    class C00717 extends C0063b {
        final /* synthetic */ int f54a;
        final /* synthetic */ byte[] f55b;
        final /* synthetic */ Size f56c;
        final /* synthetic */ C0079a f57d;
        final /* synthetic */ UnityPlayer f58e;

        C00717(UnityPlayer unityPlayer, int i, byte[] bArr, Size size, C0079a c0079a) {
            this.f58e = unityPlayer;
            this.f54a = i;
            this.f55b = bArr;
            this.f56c = size;
            this.f57d = c0079a;
            super((byte) 0);
        }

        public final void m14a() {
            this.f58e.nativeVideoFrameCallback(this.f54a, this.f55b, this.f56c.width, this.f56c.height);
            this.f57d.m89a(this.f55b);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.8 */
    class C00728 implements Runnable {
        final /* synthetic */ String f59a;
        final /* synthetic */ int f60b;
        final /* synthetic */ int f61c;
        final /* synthetic */ int f62d;
        final /* synthetic */ boolean f63e;
        final /* synthetic */ int f64f;
        final /* synthetic */ int f65g;
        final /* synthetic */ UnityPlayer f66h;

        C00728(UnityPlayer unityPlayer, String str, int i, int i2, int i3, boolean z, int i4, int i5) {
            this.f66h = unityPlayer;
            this.f59a = str;
            this.f60b = i;
            this.f61c = i2;
            this.f62d = i3;
            this.f63e = z;
            this.f64f = i4;
            this.f65g = i5;
        }

        public final void run() {
            if (this.f66h.f76E == null) {
                this.f66h.m33a(true);
                this.f66h.pause();
                this.f66h.f76E = new C0103q(this.f66h, this.f66h.f92m, this.f59a, this.f60b, this.f61c, this.f62d, this.f63e, (long) this.f64f, (long) this.f65g);
                this.f66h.addView(this.f66h.f76E);
                this.f66h.f76E.requestFocus();
                this.f66h.f86g.m141d(this.f66h.f93n);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.9 */
    class C00739 implements Runnable {
        final /* synthetic */ UnityPlayer f67a;

        C00739(UnityPlayer unityPlayer) {
            this.f67a = unityPlayer;
        }

        public final void run() {
            if (this.f67a.f76E != null) {
                this.f67a.f86g.m140c(this.f67a.f93n);
                this.f67a.removeView(this.f67a.f76E);
                this.f67a.f76E = null;
                this.f67a.resume();
                this.f67a.m33a(false);
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer.a */
    private class C0074a extends Thread {
        volatile boolean f68a;
        volatile boolean f69b;
        final /* synthetic */ UnityPlayer f70c;

        private C0074a(UnityPlayer unityPlayer) {
            this.f70c = unityPlayer;
        }

        public final void m15a() {
            this.f69b = true;
            synchronized (this) {
                notify();
            }
        }

        public final void m16b() {
            this.f68a = false;
            synchronized (this) {
                notify();
            }
        }

        public final void m17c() {
            this.f68a = true;
            synchronized (this) {
                notify();
            }
        }

        public final void run() {
            setName("UnityMain");
            while (!this.f69b) {
                try {
                    synchronized (this) {
                        wait();
                    }
                    while (!this.f69b && !this.f68a) {
                        this.f70c.executeGLThreadJobs();
                        if (!(this.f70c.isFinishing() || this.f70c.nativeRender())) {
                            this.f70c.m40b();
                        }
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static {
        currentActivity = null;
        new C0100o().m142a();
        f71s = true;
        System.loadLibrary("main");
    }

    public UnityPlayer(ContextWrapper contextWrapper) {
        super(contextWrapper);
        this.f82c = false;
        this.f83d = false;
        this.f84e = false;
        this.f87h = false;
        this.f88i = new C0101p();
        this.f89j = new ConcurrentLinkedQueue();
        this.f90k = null;
        this.f91l = false;
        this.f80a = new C0074a();
        this.f99u = true;
        this.f102x = 0;
        this.f103y = 0;
        this.f72A = null;
        this.f73B = null;
        this.f74C = new Bundle();
        this.f75D = new ArrayList();
        this.f81b = null;
        this.f77F = null;
        this.f78G = new Runnable() {
            final /* synthetic */ UnityPlayer f28a;

            {
                this.f28a = r1;
            }

            public final void run() {
                int m = this.f28a.nativeActivityIndicatorStyle();
                if (m >= 0) {
                    if (this.f28a.f77F == null) {
                        this.f28a.f77F = new ProgressBar(this.f28a.f92m, null, new int[]{16842874, 16843401, 16842873, 16843400}[m]);
                        this.f28a.f77F.setIndeterminate(true);
                        this.f28a.f77F.setLayoutParams(new LayoutParams(-2, -2, 51));
                        this.f28a.addView(this.f28a.f77F);
                    }
                    this.f28a.f77F.setVisibility(0);
                    this.f28a.bringChildToFront(this.f28a.f77F);
                }
            }
        };
        this.f79H = new Runnable() {
            final /* synthetic */ UnityPlayer f29a;

            {
                this.f29a = r1;
            }

            public final void run() {
                if (this.f29a.f77F != null) {
                    this.f29a.f77F.setVisibility(8);
                    this.f29a.f77F = null;
                }
            }
        };
        if (contextWrapper instanceof Activity) {
            currentActivity = (Activity) contextWrapper;
        }
        this.f86g = new C0099n(this);
        this.f92m = contextWrapper;
        this.f85f = contextWrapper instanceof Activity ? new C0092j(contextWrapper) : null;
        this.f104z = new C0094l(contextWrapper, this);
        m22a();
        m23a(this.f92m.getApplicationInfo());
        if (C0101p.m145c()) {
            nativeFile(this.f92m.getPackageCodePath());
            m63k();
            this.f93n = new SurfaceView(contextWrapper);
            this.f93n.getHolder().addCallback(new Callback() {
                final /* synthetic */ UnityPlayer f18a;

                /* renamed from: com.unity3d.player.UnityPlayer.12.1 */
                class C00641 extends C0063b {
                    final /* synthetic */ int f14a;
                    final /* synthetic */ int f15b;
                    final /* synthetic */ int f16c;
                    final /* synthetic */ AnonymousClass12 f17d;

                    C00641(AnonymousClass12 anonymousClass12, int i, int i2, int i3) {
                        this.f17d = anonymousClass12;
                        this.f14a = i;
                        this.f15b = i2;
                        this.f16c = i3;
                        super((byte) 0);
                    }

                    public final void m11a() {
                        int i = this.f15b;
                        int i2 = this.f16c;
                        if (!((this.f17d.f18a.f102x == 0 && this.f17d.f18a.f103y == 0) || (this.f17d.f18a.f102x == i && this.f17d.f18a.f103y == i2))) {
                            this.f17d.f18a.setScreenSize(this.f17d.f18a.f102x, this.f17d.f18a.f103y, C0093k.f143a ? C0093k.f145c.m96a() : false);
                            i = this.f17d.f18a.f102x;
                            i2 = this.f17d.f18a.f103y;
                        }
                        this.f17d.f18a.f100v = i;
                        this.f17d.f18a.f101w = i2;
                        this.f17d.f18a.nativeResize(this.f17d.f18a.f100v, this.f17d.f18a.f101w, this.f17d.f18a.f93n.getWidth(), this.f17d.f18a.f93n.getHeight());
                        this.f17d.f18a.m58h();
                    }
                }

                {
                    this.f18a = r1;
                }

                public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    this.f18a.m24a(new C00641(this, i, i2, i3));
                }

                public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                    Surface surface = surfaceHolder.getSurface();
                    this.f18a.f88i;
                    if (C0101p.m145c()) {
                        this.f18a.nativeRecreateGfxState(surface);
                    }
                }

                public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                }
            });
            this.f93n.setFocusable(true);
            this.f93n.setFocusableInTouchMode(true);
            this.f86g.m140c(this.f93n);
            this.f98t = false;
            this.f94o = ((PowerManager) this.f92m.getSystemService("power")).newWakeLock(26, "Unity-VideoPlayerWakeLock");
            m46c();
            initJni(contextWrapper);
            nativeInitWWW(WWW.class);
            if (C0093k.f143a) {
                C0093k.f145c.m94a((View) this);
            }
            this.f95p = (SensorManager) this.f92m.getSystemService("sensor");
            this.f96q = (WindowManager) this.f92m.getSystemService("window");
            nativeSetDefaultDisplay(this.f96q.getDefaultDisplay().getDisplayId());
            this.f80a.start();
            return;
        }
        AlertDialog create = new Builder(this.f92m).setTitle("Failure to initialize!").setPositiveButton("OK", new C00651(this)).setMessage("Your hardware does not support this application, sorry!").create();
        create.setCancelable(false);
        create.show();
    }

    public static native void UnitySendMessage(String str, String str2, String str3);

    private static String m21a(String str) {
        byte[] digest;
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(str);
            long length = new File(str).length();
            fileInputStream.skip(length - Math.min(length, 65558));
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
            for (int i2 = 0; i2 != -1; i2 = fileInputStream.read(bArr)) {
                instance.update(bArr, 0, i2);
            }
            digest = instance.digest();
        } catch (FileNotFoundException e) {
            digest = null;
        } catch (IOException e2) {
            digest = null;
        } catch (NoSuchAlgorithmException e3) {
            digest = null;
        }
        if (digest == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i < digest.length) {
            stringBuffer.append(Integer.toString((digest[i] & MotionEventCompat.ACTION_MASK) + AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY, 16).substring(1));
            i++;
        }
        return stringBuffer.toString();
    }

    private void m22a() {
        try {
            File file = new File(this.f92m.getPackageCodePath(), "assets/bin/Data/settings.xml");
            InputStream fileInputStream = file.exists() ? new FileInputStream(file) : this.f92m.getAssets().open("bin/Data/settings.xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(fileInputStream, null);
            String str = null;
            String str2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    str2 = newPullParser.getName();
                    String str3 = str;
                    for (int i = 0; i < newPullParser.getAttributeCount(); i++) {
                        if (newPullParser.getAttributeName(i).equalsIgnoreCase("name")) {
                            str3 = newPullParser.getAttributeValue(i);
                        }
                    }
                    str = str3;
                } else if (eventType == 3) {
                    str2 = null;
                } else if (eventType == 4 && str != null) {
                    if (str2.equalsIgnoreCase("integer")) {
                        this.f74C.putInt(str, Integer.parseInt(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("string")) {
                        this.f74C.putString(str, newPullParser.getText());
                    } else if (str2.equalsIgnoreCase("bool")) {
                        this.f74C.putBoolean(str, Boolean.parseBoolean(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("float")) {
                        this.f74C.putFloat(str, Float.parseFloat(newPullParser.getText()));
                    }
                    str = null;
                }
            }
        } catch (Exception e) {
            C0089h.Log(6, "Unable to locate player settings. " + e.getLocalizedMessage());
            m40b();
        }
    }

    private static void m23a(ApplicationInfo applicationInfo) {
        if (NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            C0101p.m143a();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to load libraries from libmain.so");
    }

    private void m24a(C0063b c0063b) {
        if (!isFinishing()) {
            m48c((Runnable) c0063b);
        }
    }

    static void m31a(Runnable runnable) {
        new Thread(runnable).start();
    }

    private static void m32a(String str, WakeLock wakeLock, boolean z) {
        try {
            if (z != wakeLock.isHeld()) {
                if (z) {
                    wakeLock.acquire();
                    if (!wakeLock.isHeld()) {
                        C0089h.Log(5, String.format("Unable to acquire %s wake-lock. Make sure 'android.permission.WAKE_LOCK' has been set in the manifest.", new Object[]{str}));
                    }
                } else if (!z) {
                    wakeLock.release();
                }
            }
        } catch (Exception e) {
            C0089h.Log(5, String.format("Unable to acquire/release %s wake-lock. Make sure 'android.permission.WAKE_LOCK' has been set in the manifest.", new Object[]{str}));
        }
    }

    private void m33a(boolean z) {
        m32a("video", this.f94o, z);
    }

    private boolean m34a(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 25 || keyCode == 24) {
            return false;
        }
        m24a(new AnonymousClass10(this, new KeyEvent(keyEvent)));
        return true;
    }

    private boolean m35a(MotionEvent motionEvent) {
        if (this.f91l) {
            return false;
        }
        m24a(new AnonymousClass11(this, MotionEvent.obtain(motionEvent)));
        return true;
    }

    private static String[] m38a(Context context) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        try {
            int i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + packageName);
                if (file.exists()) {
                    if (i > 0) {
                        String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                        if (new File(str).isFile()) {
                            vector.add(str);
                        }
                    }
                    if (i > 0) {
                        packageName = file + File.separator + "patch." + i + "." + packageName + ".obb";
                        if (new File(packageName).isFile()) {
                            vector.add(packageName);
                        }
                    }
                }
            }
            String[] strArr = new String[vector.size()];
            vector.toArray(strArr);
            return strArr;
        } catch (NameNotFoundException e) {
            return new String[0];
        }
    }

    private void m40b() {
        if ((this.f92m instanceof Activity) && !((Activity) this.f92m).isFinishing()) {
            ((Activity) this.f92m).finish();
        }
    }

    private void m43b(Runnable runnable) {
        if (this.f92m instanceof Activity) {
            ((Activity) this.f92m).runOnUiThread(runnable);
        } else {
            C0089h.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    private void m46c() {
        boolean a = new C0090i((Activity) this.f92m).m109a();
        this.f91l = a;
        nativeForwardEventsToDalvik(a);
    }

    private void m48c(Runnable runnable) {
        C0101p c0101p = this.f88i;
        if (!C0101p.m145c()) {
            return;
        }
        if (Thread.currentThread() == this.f80a) {
            runnable.run();
        } else {
            this.f89j.add(runnable);
        }
    }

    private void m50d() {
        for (C0079a c : this.f75D) {
            c.m91c();
        }
    }

    private void m52e() {
        for (C0079a c0079a : this.f75D) {
            try {
                c0079a.m88a((C0075a) this);
            } catch (Exception e) {
                C0089h.Log(6, "Unable to initialize camera: " + e.getMessage());
                c0079a.m91c();
            }
        }
    }

    private void m54f() {
        if (this.f97r != null) {
            this.f97r.close();
        }
        nativeDone();
    }

    private void m56g() {
        if (!this.f88i.m150e()) {
            return;
        }
        if (this.f76E != null) {
            m33a(true);
            this.f76E.onResume();
            return;
        }
        this.f88i.m148c(true);
        m52e();
        this.f80a.m16b();
        this.f104z.m126e();
        this.f72A = null;
        this.f73B = null;
        C0101p c0101p = this.f88i;
        if (C0101p.m145c()) {
            m63k();
        }
        m48c(new Runnable() {
            final /* synthetic */ UnityPlayer f25a;

            {
                this.f25a = r1;
            }

            public final void run() {
                this.f25a.nativeResume();
            }
        });
        if (f71s && this.f97r == null) {
            this.f97r = new FMODAudioDevice();
        }
        if (this.f97r != null && !this.f97r.isRunning()) {
            this.f97r.start();
        }
    }

    private void m58h() {
        if (this.f92m instanceof Activity) {
            float f = 0.0f;
            if (!this.f74C.getBoolean("hide_status_bar", true)) {
                Activity activity = (Activity) this.f92m;
                Rect rect = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                f = (float) rect.top;
            }
            nativeSetTouchDeltaY(f);
        }
    }

    private static void m59i() {
        if (!C0101p.m145c()) {
            return;
        }
        if (NativeLoader.unload()) {
            C0101p.m144b();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
    }

    private final native void initJni(Context context);

    private boolean m62j() {
        return this.f92m.getPackageManager().hasSystemFeature("android.hardware.camera") || this.f92m.getPackageManager().hasSystemFeature("android.hardware.camera.front");
    }

    private void m63k() {
        if (this.f74C.getBoolean("useObb")) {
            for (String str : m38a(this.f92m)) {
                String a = m21a(str);
                if (this.f74C.getBoolean(a)) {
                    nativeFile(str);
                }
                this.f74C.remove(a);
            }
        }
    }

    private final native int nativeActivityIndicatorStyle();

    private final native void nativeDone();

    private final native void nativeFile(String str);

    private final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWWW(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    private final native void nativeKeysPressed(String str);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(Surface surface);

    private final native boolean nativeRender();

    private final native boolean nativeRequested32bitDisplayBuffer();

    private final native int nativeRequestedAA();

    private final native void nativeResize(int i, int i2, int i3, int i4);

    private final native void nativeResume();

    private final native void nativeSetDefaultDisplay(int i);

    private final native void nativeSetExtras(Bundle bundle);

    private final native void nativeSetInputCanceled(boolean z);

    private final native void nativeSetInputString(String str);

    private final native void nativeSetTouchDeltaY(float f);

    private final native void nativeSoftInputClosed();

    private final native void nativeVideoFrameCallback(int i, byte[] bArr, int i2, int i3);

    protected boolean Location_IsServiceEnabledByUser() {
        return this.f104z.m121a();
    }

    protected void Location_SetDesiredAccuracy(float f) {
        this.f104z.m123b(f);
    }

    protected void Location_SetDistanceFilter(float f) {
        this.f104z.m120a(f);
    }

    protected void Location_StartUpdatingLocation() {
        this.f104z.m122b();
    }

    protected void Location_StopUpdatingLocation() {
        this.f104z.m124c();
    }

    protected void closeCamera(int i) {
        for (C0079a c0079a : this.f75D) {
            if (c0079a.m87a() == i) {
                c0079a.m91c();
                this.f75D.remove(c0079a);
                return;
            }
        }
    }

    public void configurationChanged(Configuration configuration) {
        if (this.f93n instanceof SurfaceView) {
            this.f93n.getHolder().setSizeFromLayout();
        }
        if (this.f84e && configuration.hardKeyboardHidden == 2) {
            ((InputMethodManager) this.f92m.getSystemService("input_method")).toggleSoftInput(0, 1);
        }
        if (this.f76E != null) {
            this.f76E.updateVideoLayout();
        }
    }

    protected void disableLogger() {
        C0089h.f137a = true;
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.f89j.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    protected void forwardMotionEventToDalvik(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        this.f85f.m107a(j, j2, i, i2, iArr, fArr, i3, f, f2, i4, i5, i6, i7, i8, jArr, fArr2);
    }

    protected int getCameraOrientation(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.orientation;
    }

    protected int getInternetReachability() {
        try {
            if (this.f73B == null) {
                this.f73B = ((ConnectivityManager) this.f92m.getSystemService("connectivity")).getActiveNetworkInfo();
            }
            NetworkInfo networkInfo = this.f73B;
            return networkInfo == null ? 0 : !networkInfo.isConnected() ? 0 : networkInfo.getType() + 1;
        } catch (Exception e) {
            C0089h.Log(5, "android.permission.ACCESS_NETWORK_STATE not available?");
            return 0;
        }
    }

    protected int getNumCameras() {
        return !m62j() ? 0 : Camera.getNumberOfCameras();
    }

    protected int getScreenTimeout() {
        return System.getInt(this.f92m.getContentResolver(), "screen_off_timeout", 15000) / 1000;
    }

    public Bundle getSettings() {
        return this.f74C;
    }

    protected int getSplashMode() {
        return this.f74C.getInt("splash_mode");
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        m43b(new C00684(this));
    }

    protected void hideVideoPlayer() {
        m43b(new C00739(this));
    }

    public void init(int i, boolean z) {
    }

    protected int[] initCamera(int i, int i2, int i3, int i4) {
        C0079a c0079a = new C0079a(i, i2, i3, i4);
        try {
            c0079a.m88a((C0075a) this);
            this.f75D.add(c0079a);
            Size b = c0079a.m90b();
            return new int[]{b.width, b.height};
        } catch (Exception e) {
            C0089h.Log(6, "Unable to initialize camera: " + e.getMessage());
            c0079a.m91c();
            return null;
        }
    }

    protected boolean isCameraFrontFacing(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.facing == 1;
    }

    protected boolean isFinishing() {
        if (!this.f98t) {
            boolean z = (this.f92m instanceof Activity) && ((Activity) this.f92m).isFinishing();
            this.f98t = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            C0089h.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            C0089h.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    final native void nativeDeviceOrientation(int i);

    final native void nativeForwardEventsToDalvik(boolean z);

    protected native void nativeSetLocation(float f, float f2, float f3, float f4, double d, float f5);

    protected native void nativeSetLocationStatus(int i);

    public void onCameraFrame(C0079a c0079a, byte[] bArr) {
        m24a(new C00717(this, c0079a.m87a(), bArr, c0079a.m90b(), c0079a));
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return m35a(MotionEvent.obtain(motionEvent));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return m34a(new KeyEvent(keyEvent));
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        C0101p c0101p = this.f88i;
        if (C0101p.m145c()) {
            if (i != 0) {
                for (int i3 = 0; i3 < i2; i3++) {
                    m34a(keyEvent);
                }
            } else {
                m24a(new AnonymousClass13(this, keyEvent));
            }
        }
        return true;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return (this.f84e && i == 4) ? m34a(keyEvent) : false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return m34a(new KeyEvent(keyEvent));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return m35a(MotionEvent.obtain(motionEvent));
    }

    public void pause() {
        if (this.f76E != null) {
            this.f76E.onPause();
            m33a(false);
            return;
        }
        reportSoftInputStr(null, 1, true);
        C0101p c0101p = this.f88i;
        if (C0101p.m145c()) {
            Semaphore semaphore = new Semaphore(0);
            if (isFinishing()) {
                m48c(new AnonymousClass14(this, semaphore));
            } else {
                m48c(new AnonymousClass15(this, semaphore));
            }
            try {
                semaphore.tryAcquire(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
            }
            if (semaphore.drainPermits() > 0) {
                quit();
            }
        }
        if (this.f88i.m151f()) {
            this.f88i.m148c(false);
            this.f88i.m147b(true);
            m50d();
            if (this.f97r != null) {
                this.f97r.stop();
            }
            this.f80a.m17c();
            this.f104z.m125d();
        }
    }

    public void quit() {
        this.f98t = true;
        if (!this.f88i.m149d()) {
            pause();
        }
        this.f80a.m15a();
        try {
            this.f80a.join(10000);
        } catch (InterruptedException e) {
        }
        if (this.f90k != null) {
            this.f92m.unregisterReceiver(this.f90k);
        }
        this.f90k = null;
        if (C0101p.m145c()) {
            removeAllViews();
        }
        m59i();
        kill();
    }

    protected void reportSoftInputStr(String str, int i, boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        m24a(new C00706(this, z, str, i));
    }

    public void resume() {
        if (C0093k.f143a) {
            C0093k.f145c.m98b(this);
        }
        this.f88i.m147b(false);
        m56g();
    }

    protected void setHideInputField(boolean z) {
        this.f83d = z;
    }

    protected void setScreenSize(int i, int i2, boolean z) {
        boolean z2 = true;
        SurfaceView surfaceView = this.f93n;
        surfaceView.getHolder().getSurfaceFrame();
        boolean z3 = (surfaceView.getWidth() == i && surfaceView.getHeight() == i2) || (i == 0 && i2 == 0);
        if (!(i == -1 && i2 == -1)) {
            z2 = false;
        }
        if (z2) {
            if (this.f102x == 0) {
                int i3 = this.f103y;
            }
        } else if (z3) {
            this.f102x = 0;
            this.f103y = 0;
        } else {
            this.f102x = i;
            this.f103y = i2;
        }
        m43b(new C00662(this, z2, z3, surfaceView, i, i2, z));
    }

    protected void setSoftInputStr(String str) {
        m43b(new C00695(this, str));
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        m43b(new C00673(this, this, str, i, z, z2, z3, z4, str2));
    }

    protected void showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        m43b(new C00728(this, str, i, i2, i3, z, i4, i5));
    }

    protected void startActivityIndicator() {
        m43b(this.f78G);
    }

    protected void stopActivityIndicator() {
        m43b(this.f79H);
    }

    protected void triggerResizeCall() {
        nativeResize(this.f100v, this.f101w, this.f100v, this.f101w);
    }

    public void windowFocusChanged(boolean z) {
        this.f88i.m146a(z);
        if (z && this.f81b != null) {
            reportSoftInputStr(null, 1, false);
        }
        m48c(new AnonymousClass17(this, z));
        m56g();
    }
}
