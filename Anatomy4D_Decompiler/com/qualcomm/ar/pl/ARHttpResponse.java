package com.qualcomm.ar.pl;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class ARHttpResponse {
    public static int ERROR_CANCELED = 0;
    public static int ERROR_NONE = 0;
    public static int ERROR_OPERATION_FAILED = 0;
    public static int ERROR_TIMEOUT = 0;
    private static final String MODULENAME = "ARHttpResponse";
    public byte[] contentBytes;
    public String contentEncoding;
    public String contentType;
    public int networkStatus;
    public int statusCode;

    static {
        ERROR_NONE = 0;
        ERROR_CANCELED = 1;
        ERROR_OPERATION_FAILED = 2;
        ERROR_TIMEOUT = 3;
    }

    public static ARHttpResponse createARResponse(int errorReason) {
        if (errorReason <= 0 || errorReason >= 4) {
            return null;
        }
        ARHttpResponse arResponse = new ARHttpResponse();
        arResponse.statusCode = 0;
        arResponse.contentBytes = null;
        arResponse.contentEncoding = null;
        arResponse.contentType = null;
        arResponse.networkStatus = errorReason;
        return arResponse;
    }

    public static ARHttpResponse createARResponse(HttpResponse httpResponse) throws IOException {
        ARHttpResponse arResponse = new ARHttpResponse();
        arResponse.statusCode = httpResponse.getStatusLine().getStatusCode();
        arResponse.networkStatus = ERROR_NONE;
        Header header = httpResponse.getFirstHeader("Content-Type");
        if (header != null) {
            arResponse.contentType = header.getValue();
        }
        header = httpResponse.getFirstHeader("Content-Encoding");
        if (header != null) {
            arResponse.contentEncoding = header.getValue();
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && entity.getContentLength() > 0) {
            arResponse.contentBytes = new byte[((int) entity.getContentLength())];
            new DataInputStream(entity.getContent()).readFully(arResponse.contentBytes);
        } else if (entity != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataInputStream dis = new DataInputStream(entity.getContent());
            byte[] xfer = new byte[AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT];
            while (true) {
                int bytesRead = dis.read(xfer);
                if (bytesRead == -1) {
                    break;
                }
                baos.write(xfer, 0, bytesRead);
            }
            arResponse.contentBytes = baos.toByteArray();
        }
        return arResponse;
    }
}
