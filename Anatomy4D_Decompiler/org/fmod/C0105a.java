package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C0105a implements Runnable {
    private final FMODAudioDevice f212a;
    private final ByteBuffer f213b;
    private final int f214c;
    private final int f215d;
    private final int f216e;
    private volatile Thread f217f;
    private volatile boolean f218g;
    private AudioRecord f219h;
    private boolean f220i;

    C0105a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f212a = fMODAudioDevice;
        this.f214c = i;
        this.f215d = i2;
        this.f216e = 2;
        this.f213b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    private void m158d() {
        if (this.f219h != null) {
            if (this.f219h.getState() == 1) {
                this.f219h.stop();
            }
            this.f219h.release();
            this.f219h = null;
        }
        this.f213b.position(0);
        this.f220i = false;
    }

    public final int m159a() {
        return this.f213b.capacity();
    }

    public final void m160b() {
        if (this.f217f != null) {
            m161c();
        }
        this.f218g = true;
        this.f217f = new Thread(this);
        this.f217f.start();
    }

    public final void m161c() {
        while (this.f217f != null) {
            this.f218g = false;
            try {
                this.f217f.join();
                this.f217f = null;
            } catch (InterruptedException e) {
            }
        }
    }

    public final void run() {
        int i = 3;
        while (this.f218g) {
            if (this.f212a.isInitialized()) {
                int i2;
                if (!this.f220i && i > 0) {
                    m158d();
                    this.f219h = new AudioRecord(1, this.f214c, this.f215d, this.f216e, this.f213b.capacity());
                    this.f220i = this.f219h.getState() == 1;
                    if (this.f220i) {
                        this.f213b.position(0);
                        this.f219h.startRecording();
                        i2 = 3;
                        if (this.f220i || this.f219h.getRecordingState() != 3) {
                            i = i2;
                        } else {
                            this.f212a.fmodProcessMicData(this.f213b, this.f219h.read(this.f213b, this.f213b.capacity()));
                            this.f213b.position(0);
                            i = i2;
                        }
                    } else {
                        Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f219h.getState() + ")");
                        i--;
                        m158d();
                    }
                }
                i2 = i;
                if (this.f220i) {
                }
                i = i2;
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    this.f218g = false;
                }
            }
        }
        m158d();
    }
}
