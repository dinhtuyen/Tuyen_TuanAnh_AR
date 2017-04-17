package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: com.unity3d.player.q */
public final class C0103q extends FrameLayout implements SensorEventListener, OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {
    private static final boolean f171A;
    private static boolean f172a;
    private final UnityPlayer f173b;
    private final Context f174c;
    private final SurfaceView f175d;
    private final SurfaceHolder f176e;
    private final String f177f;
    private final int f178g;
    private final int f179h;
    private final boolean f180i;
    private final long f181j;
    private final long f182k;
    private final FrameLayout f183l;
    private final SensorManager f184m;
    private final Display f185n;
    private int f186o;
    private int f187p;
    private int f188q;
    private int f189r;
    private MediaPlayer f190s;
    private MediaController f191t;
    private boolean f192u;
    private boolean f193v;
    private int f194w;
    private boolean f195x;
    private int f196y;
    private boolean f197z;

    /* renamed from: com.unity3d.player.q.1 */
    class C01021 implements Runnable {
        final /* synthetic */ C0103q f170a;

        C01021(C0103q c0103q) {
            this.f170a = c0103q;
        }

        public final void run() {
            this.f170a.f173b.hideVideoPlayer();
        }
    }

    static {
        boolean z = false;
        f172a = false;
        if (Build.MANUFACTURER.equalsIgnoreCase("Amazon") && (Build.MODEL.equalsIgnoreCase("KFTT") || Build.MODEL.equalsIgnoreCase("KFJWI") || Build.MODEL.equalsIgnoreCase("KFJWA") || Build.MODEL.equalsIgnoreCase("KFSOWI") || Build.MODEL.equalsIgnoreCase("KFTHWA") || Build.MODEL.equalsIgnoreCase("KFTHWI") || Build.MODEL.equalsIgnoreCase("KFAPWA") || Build.MODEL.equalsIgnoreCase("KFAPWI"))) {
            z = true;
        }
        f171A = z;
    }

    protected C0103q(UnityPlayer unityPlayer, Context context, String str, int i, int i2, int i3, boolean z, long j, long j2) {
        super(context);
        this.f192u = false;
        this.f193v = false;
        this.f194w = 0;
        this.f195x = false;
        this.f196y = 0;
        this.f173b = unityPlayer;
        this.f174c = context;
        this.f183l = this;
        this.f175d = new SurfaceView(context);
        this.f176e = this.f175d.getHolder();
        this.f176e.addCallback(this);
        this.f176e.setType(3);
        this.f183l.setBackgroundColor(i);
        this.f183l.addView(this.f175d);
        this.f184m = (SensorManager) this.f174c.getSystemService("sensor");
        this.f185n = ((WindowManager) this.f174c.getSystemService("window")).getDefaultDisplay();
        this.f177f = str;
        this.f178g = i2;
        this.f179h = i3;
        this.f180i = z;
        this.f181j = j;
        this.f182k = j2;
        if (f172a) {
            C0103q.m154a("fileName: " + this.f177f);
        }
        if (f172a) {
            C0103q.m154a("backgroundColor: " + i);
        }
        if (f172a) {
            C0103q.m154a("controlMode: " + this.f178g);
        }
        if (f172a) {
            C0103q.m154a("scalingMode: " + this.f179h);
        }
        if (f172a) {
            C0103q.m154a("isURL: " + this.f180i);
        }
        if (f172a) {
            C0103q.m154a("videoOffset: " + this.f181j);
        }
        if (f172a) {
            C0103q.m154a("videoLength: " + this.f182k);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.f184m.registerListener(this, this.f184m.getDefaultSensor(1), 1);
        this.f197z = true;
    }

    private void m153a() {
        doCleanUp();
        try {
            this.f190s = new MediaPlayer();
            if (this.f180i) {
                this.f190s.setDataSource(this.f174c, Uri.parse(this.f177f));
            } else if (this.f182k != 0) {
                FileInputStream fileInputStream = new FileInputStream(this.f177f);
                this.f190s.setDataSource(fileInputStream.getFD(), this.f181j, this.f182k);
                fileInputStream.close();
            } else {
                try {
                    AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f177f);
                    this.f190s.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                } catch (IOException e) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.f177f);
                    this.f190s.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                }
            }
            this.f190s.setDisplay(this.f176e);
            this.f190s.setOnBufferingUpdateListener(this);
            this.f190s.setOnCompletionListener(this);
            this.f190s.setOnPreparedListener(this);
            this.f190s.setOnVideoSizeChangedListener(this);
            this.f190s.setAudioStreamType(3);
            this.f190s.prepare();
            if (this.f178g == 0 || this.f178g == 1) {
                this.f191t = new MediaController(this.f174c);
                this.f191t.setMediaPlayer(this);
                this.f191t.setAnchorView(this.f175d);
                this.f191t.setEnabled(true);
                this.f191t.show();
            }
        } catch (Exception e2) {
            if (f172a) {
                C0103q.m154a("error: " + e2.getMessage() + e2);
            }
            onDestroy();
        }
    }

    private static void m154a(String str) {
        Log.v("Video", str);
    }

    private void m155b() {
        if (!isPlaying()) {
            if (f172a) {
                C0103q.m154a("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f195x) {
                start();
            }
        }
    }

    public static int calculateDeviceOrientation(SensorEvent sensorEvent, Display display) {
        int i;
        float f;
        int i2;
        float f2 = sensorEvent.values[0];
        float f3 = sensorEvent.values[1];
        float f4 = sensorEvent.values[2];
        float sqrt = 1.0f / ((float) Math.sqrt((double) (((f2 * f2) + (f3 * f3)) + (f4 * f4))));
        f2 *= sqrt;
        f3 *= sqrt;
        f4 *= sqrt;
        if ((((display.getOrientation() & 1) == 0 ? 1 : 0) ^ (display.getHeight() > display.getWidth() ? 1 : 0)) != 0) {
            f2 = -f2;
        } else {
            float f5 = f3;
            f3 = f2;
            f2 = f5;
        }
        sqrt = f171A ? -f3 : f3;
        if (-1.0f < f2) {
            i = 1;
            f = f2;
        } else {
            i = 0;
            f = -1.0f;
        }
        if (f < (-f2)) {
            f3 = -f2;
            i2 = 2;
        } else {
            i2 = i;
            f3 = f;
        }
        if (f3 < sqrt) {
            i2 = 3;
            f3 = sqrt;
        }
        if (f3 < (-sqrt)) {
            f3 = -sqrt;
            i2 = 4;
        }
        if (f3 < f4) {
            i2 = 5;
            f3 = f4;
        }
        if (f3 < (-f4)) {
            f3 = -f4;
            i2 = 6;
        }
        return ((double) f3) < 0.5d * Math.sqrt(3.0d) ? 0 : i2;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    protected final void doCleanUp() {
        if (this.f190s != null) {
            this.f190s.release();
            this.f190s = null;
        }
        this.f188q = 0;
        this.f189r = 0;
        this.f193v = false;
        this.f192u = false;
    }

    public final int getBufferPercentage() {
        return this.f180i ? this.f194w : 100;
    }

    public final int getCurrentPosition() {
        return this.f190s == null ? 0 : this.f190s.getCurrentPosition();
    }

    public final int getDuration() {
        return this.f190s == null ? 0 : this.f190s.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f193v && this.f192u;
        return this.f190s == null ? !z : this.f190s.isPlaying() || !z;
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f172a) {
            C0103q.m154a("onBufferingUpdate percent:" + i);
        }
        this.f194w = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f172a) {
            C0103q.m154a("onCompletion called");
        }
        onDestroy();
    }

    public final void onControllerHide() {
    }

    protected final void onDestroy() {
        onPause();
        doCleanUp();
        UnityPlayer unityPlayer = this.f173b;
        UnityPlayer.m31a(new C01021(this));
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.f178g != 2 || i == 0 || keyEvent.isSystem())) {
            return this.f191t != null ? this.f191t.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        } else {
            onDestroy();
            return true;
        }
    }

    protected final void onPause() {
        if (f172a) {
            C0103q.m154a("onPause called");
        }
        this.f184m.unregisterListener(this);
        if (!this.f195x) {
            pause();
            this.f195x = false;
        }
        if (this.f190s != null) {
            this.f196y = this.f190s.getCurrentPosition();
        }
        this.f197z = false;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f172a) {
            C0103q.m154a("onPrepared called");
        }
        this.f193v = true;
        if (this.f193v && this.f192u) {
            m155b();
        }
    }

    protected final void onResume() {
        if (f172a) {
            C0103q.m154a("onResume called");
        }
        if (!this.f197z) {
            this.f184m.registerListener(this, this.f184m.getDefaultSensor(1), 1);
            if (!this.f195x) {
                start();
            }
        }
        this.f197z = true;
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            this.f173b.nativeDeviceOrientation(C0103q.calculateDeviceOrientation(sensorEvent, this.f185n));
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & MotionEventCompat.ACTION_MASK;
        if (this.f178g != 2 || action != 0) {
            return this.f191t != null ? this.f191t.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        } else {
            onDestroy();
            return true;
        }
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f172a) {
            C0103q.m154a("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f192u = true;
            this.f188q = i;
            this.f189r = i2;
            if (this.f193v && this.f192u) {
                m155b();
            }
        } else if (f172a) {
            C0103q.m154a("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        if (this.f190s != null) {
            this.f190s.pause();
            this.f195x = true;
        }
    }

    public final void seekTo(int i) {
        if (this.f190s != null) {
            this.f190s.seekTo(i);
        }
    }

    public final void start() {
        if (this.f190s != null) {
            this.f190s.start();
            this.f195x = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f172a) {
            C0103q.m154a("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        this.f186o = i2;
        this.f187p = i3;
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f172a) {
            C0103q.m154a("surfaceCreated called");
        }
        m153a();
        seekTo(this.f196y);
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f172a) {
            C0103q.m154a("surfaceDestroyed called");
        }
        doCleanUp();
    }

    protected final void updateVideoLayout() {
        if (f172a) {
            C0103q.m154a("updateVideoLayout");
        }
        WindowManager windowManager = (WindowManager) this.f174c.getSystemService("window");
        this.f186o = windowManager.getDefaultDisplay().getWidth();
        this.f187p = windowManager.getDefaultDisplay().getHeight();
        int i = this.f186o;
        int i2 = this.f187p;
        if (this.f179h == 1 || this.f179h == 2) {
            float f = ((float) this.f188q) / ((float) this.f189r);
            if (((float) this.f186o) / ((float) this.f187p) <= f) {
                i2 = (int) (((float) this.f186o) / f);
            } else {
                i = (int) (((float) this.f187p) * f);
            }
        } else if (this.f179h == 0) {
            i = this.f188q;
            i2 = this.f189r;
        }
        if (f172a) {
            C0103q.m154a("frameWidth = " + i + "; frameHeight = " + i2);
        }
        this.f183l.updateViewLayout(this.f175d, new LayoutParams(i, i2, 17));
    }
}
