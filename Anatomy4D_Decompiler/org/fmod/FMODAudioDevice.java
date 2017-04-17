package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {
    private static int f200i;
    private static int f201j;
    private static int f202k;
    private static int f203l;
    private volatile Thread f204a;
    private volatile boolean f205b;
    private volatile C0104a f206c;
    private AudioTrack f207d;
    private boolean f208e;
    private ByteBuffer f209f;
    private byte[] f210g;
    private volatile C0105a f211h;

    /* renamed from: org.fmod.FMODAudioDevice.a */
    private class C0104a extends Thread {
        final /* synthetic */ FMODAudioDevice f198a;
        private Object f199b;

        C0104a(FMODAudioDevice fMODAudioDevice) {
            this.f198a = fMODAudioDevice;
            super("FMODStreamBlocker");
            this.f199b = new Object();
        }

        private void m156a() {
            synchronized (this.f199b) {
                this.f199b.notifyAll();
            }
        }

        public final void run() {
            if (this.f198a.fmodBlockStreaming() != 0) {
                throw new RuntimeException("Unable to block fmod streaming thread");
            }
            synchronized (this.f199b) {
                try {
                    this.f199b.wait();
                } catch (InterruptedException e) {
                }
            }
            if (this.f198a.fmodUnblockStreaming() != 0) {
                throw new RuntimeException("Unable to unblock fmod streaming thread");
            }
        }
    }

    static {
        f200i = 0;
        f201j = 1;
        f202k = 2;
        f203l = 3;
    }

    public FMODAudioDevice() {
        this.f204a = null;
        this.f205b = false;
        this.f206c = null;
        this.f207d = null;
        this.f208e = false;
        this.f209f = null;
        this.f210g = null;
    }

    private synchronized void blockStreaming() {
        if (isInitialized() && this.f206c == null) {
            this.f206c = new C0104a(this);
            this.f206c.start();
        }
    }

    private native int fmodBlockStreaming();

    private native int fmodGetInfo(int i);

    private native int fmodInitJni();

    private native int fmodProcess(ByteBuffer byteBuffer);

    private native int fmodUnblockStreaming();

    private void releaseAudioTrack() {
        if (this.f207d != null) {
            if (this.f207d.getState() == 1) {
                this.f207d.stop();
            }
            this.f207d.release();
            this.f207d = null;
        }
        this.f209f = null;
        this.f210g = null;
        this.f208e = false;
    }

    private synchronized void unblockStreaming() {
        if (this.f206c != null) {
            do {
                try {
                    this.f206c.m156a();
                    this.f206c.join(10);
                } catch (InterruptedException e) {
                }
            } while (this.f206c.isAlive());
            this.f206c = null;
        }
    }

    public synchronized void close() {
        stop();
        unblockStreaming();
    }

    native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isInitialized() {
        return fmodGetInfo(f200i) > 0;
    }

    public boolean isRunning() {
        return this.f204a != null && this.f204a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.f205b) {
            if (isInitialized()) {
                int i2;
                if (this.f208e || i <= 0) {
                    i2 = i;
                } else {
                    releaseAudioTrack();
                    int fmodGetInfo = fmodGetInfo(f200i);
                    int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                    int fmodGetInfo2 = fmodGetInfo(f201j);
                    i2 = fmodGetInfo(f202k);
                    if ((fmodGetInfo2 * i2) * 4 > round) {
                        round = (i2 * fmodGetInfo2) * 4;
                    }
                    this.f207d = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                    this.f208e = this.f207d.getState() == 1;
                    if (this.f208e) {
                        this.f209f = ByteBuffer.allocateDirect((fmodGetInfo2 * 2) * 2);
                        this.f210g = new byte[this.f209f.capacity()];
                        this.f207d.play();
                        i2 = 3;
                    } else {
                        Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f207d.getState() + ")");
                        releaseAudioTrack();
                        i2 = i - 1;
                    }
                }
                if (!this.f208e) {
                    i = i2;
                } else if (fmodGetInfo(f203l) == 1) {
                    fmodProcess(this.f209f);
                    this.f209f.get(this.f210g, 0, this.f209f.capacity());
                    this.f207d.write(this.f210g, 0, this.f209f.capacity());
                    this.f209f.position(0);
                    i = i2;
                } else {
                    releaseAudioTrack();
                    i = i2;
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    this.f205b = false;
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f204a != null) {
            stop();
        }
        this.f204a = new Thread(this, "FMODAudioDevice");
        this.f204a.setPriority(10);
        this.f205b = true;
        fmodInitJni();
        unblockStreaming();
        this.f204a.start();
        if (this.f211h != null) {
            this.f211h.m160b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f211h == null) {
            this.f211h = new C0105a(this, i, i2);
            this.f211h.m160b();
        }
        return this.f211h.m159a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
        r1 = this;
        monitor-enter(r1);
    L_0x0001:
        r0 = r1.f204a;	 Catch:{ all -> 0x0021 }
        if (r0 == 0) goto L_0x0016;
    L_0x0005:
        r0 = 0;
        r1.f205b = r0;	 Catch:{ all -> 0x0021 }
        r0 = r1.f204a;	 Catch:{ InterruptedException -> 0x0014 }
        r0.join();	 Catch:{ InterruptedException -> 0x0014 }
        r0 = 0;
        r1.f204a = r0;	 Catch:{ InterruptedException -> 0x0014 }
        r1.blockStreaming();	 Catch:{ InterruptedException -> 0x0014 }
        goto L_0x0001;
    L_0x0014:
        r0 = move-exception;
        goto L_0x0001;
    L_0x0016:
        r0 = r1.f211h;	 Catch:{ all -> 0x0021 }
        if (r0 == 0) goto L_0x001f;
    L_0x001a:
        r0 = r1.f211h;	 Catch:{ all -> 0x0021 }
        r0.m161c();	 Catch:{ all -> 0x0021 }
    L_0x001f:
        monitor-exit(r1);
        return;
    L_0x0021:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FMODAudioDevice.stop():void");
    }

    public synchronized void stopAudioRecord() {
        if (this.f211h != null) {
            this.f211h.m161c();
            this.f211h = null;
        }
    }
}
