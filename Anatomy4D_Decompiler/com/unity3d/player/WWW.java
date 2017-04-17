package com.unity3d.player;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class WWW extends Thread {
    private int f105a;
    private int f106b;
    private String f107c;
    private byte[] f108d;
    private Map f109e;

    WWW(int i, String str, byte[] bArr, Map map) {
        this.f106b = i;
        this.f107c = str;
        this.f108d = bArr;
        this.f109e = map;
        this.f105a = 0;
        start();
    }

    private static native void doneCallback(int i);

    private static native void errorCallback(int i, String str);

    private static native boolean headerCallback(int i, String str);

    private static native void progressCallback(int i, float f, float f2, double d, int i2);

    private static native boolean readCallback(int i, byte[] bArr, int i2);

    protected boolean headerCallback(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        stringBuilder.append("\n\r");
        return headerCallback(this.f106b, stringBuilder.toString());
    }

    protected boolean headerCallback(Map map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            for (String str : (List) entry.getValue()) {
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append(": ");
                stringBuilder.append(str);
                stringBuilder.append("\n\r");
            }
        }
        return headerCallback(this.f106b, stringBuilder.toString());
    }

    protected void progressCallback(int i, int i2, int i3, int i4, long j, long j2) {
        float f;
        float f2;
        double d;
        if (i4 > 0) {
            f = ((float) i3) / ((float) i4);
            double max = ((double) Math.max(i4 - i3, 0)) / ((1000.0d * ((double) i3)) / Math.max((double) (j - j2), 0.1d));
            if (Double.isInfinite(max) || Double.isNaN(max)) {
                max = 0.0d;
            }
            f2 = 1.0f;
            d = max;
        } else if (i2 > 0) {
            f = 0.0f;
            f2 = (float) (i / i2);
            d = 0.0d;
        } else {
            return;
        }
        progressCallback(this.f106b, f2, f, d, i4);
    }

    protected boolean readCallback(byte[] bArr, int i) {
        return readCallback(this.f106b, bArr, i);
    }

    public void run() {
        int i = this.f105a + 1;
        this.f105a = i;
        if (i > 5) {
            errorCallback(this.f106b, "Too many redirects");
            return;
        }
        try {
            URL url = new URL(this.f107c);
            URLConnection openConnection = url.openConnection();
            if (!url.getProtocol().equalsIgnoreCase("file") || url.getHost() == null || url.getHost().length() == 0) {
                int i2;
                if (this.f109e != null) {
                    for (Entry entry : this.f109e.entrySet()) {
                        openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.f108d != null) {
                    openConnection.setDoOutput(true);
                    try {
                        OutputStream outputStream = openConnection.getOutputStream();
                        i2 = 0;
                        while (i2 < this.f108d.length) {
                            i = Math.min(1428, this.f108d.length - i2);
                            outputStream.write(this.f108d, i2, i);
                            i2 += i;
                            progressCallback(i2, this.f108d.length, 0, 0, 0, 0);
                        }
                    } catch (Exception e) {
                        errorCallback(this.f106b, e.toString());
                        return;
                    }
                }
                if (openConnection instanceof HttpURLConnection) {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                    try {
                        i2 = httpURLConnection.getResponseCode();
                        Map headerFields = httpURLConnection.getHeaderFields();
                        if (headerFields != null && (i2 == 301 || i2 == 302)) {
                            List list = (List) headerFields.get("Location");
                            if (!(list == null || list.isEmpty())) {
                                httpURLConnection.disconnect();
                                this.f107c = (String) list.get(0);
                                run();
                                return;
                            }
                        }
                    } catch (IOException e2) {
                        errorCallback(this.f106b, e2.toString());
                        return;
                    }
                }
                Map headerFields2 = openConnection.getHeaderFields();
                boolean headerCallback = headerCallback(headerFields2);
                if ((headerFields2 == null || !headerFields2.containsKey("content-length")) && openConnection.getContentLength() != -1) {
                    headerCallback = headerCallback || headerCallback("content-length", String.valueOf(openConnection.getContentLength()));
                }
                if ((headerFields2 == null || !headerFields2.containsKey("content-type")) && openConnection.getContentType() != null) {
                    headerCallback = headerCallback || headerCallback("content-type", openConnection.getContentType());
                }
                if (headerCallback) {
                    errorCallback(this.f106b, this.f107c + " aborted");
                    return;
                }
                int contentLength = openConnection.getContentLength() > 0 ? openConnection.getContentLength() : 0;
                i = (url.getProtocol().equalsIgnoreCase("file") || url.getProtocol().equalsIgnoreCase("jar")) ? contentLength == 0 ? AccessibilityNodeInfoCompat.ACTION_PASTE : Math.min(contentLength, AccessibilityNodeInfoCompat.ACTION_PASTE) : 1428;
                int i3 = 0;
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    byte[] bArr = new byte[i];
                    InputStream inputStream = openConnection.getInputStream();
                    for (i = 0; i != -1; i = inputStream.read(bArr)) {
                        if (readCallback(bArr, i)) {
                            errorCallback(this.f106b, this.f107c + " aborted");
                            return;
                        }
                        i3 += i;
                        progressCallback(0, 0, i3, contentLength, System.currentTimeMillis(), currentTimeMillis);
                    }
                    progressCallback(0, 0, i3, i3, 0, 0);
                    doneCallback(this.f106b);
                    return;
                } catch (Exception e3) {
                    errorCallback(this.f106b, e3.toString());
                    return;
                }
            }
            errorCallback(this.f106b, url.getHost() + url.getFile() + " is not an absolute path!");
        } catch (MalformedURLException e4) {
            errorCallback(this.f106b, e4.toString());
        } catch (IOException e22) {
            errorCallback(this.f106b, e22.toString());
        }
    }
}
