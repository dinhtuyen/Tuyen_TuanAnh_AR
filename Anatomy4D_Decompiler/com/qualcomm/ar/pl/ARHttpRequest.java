package com.qualcomm.ar.pl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;

public class ARHttpRequest {
    private static final String MODULENAME = "ARHttpRequest";
    public byte[] content;
    public String contentEncoding;
    public String contentPath;
    public String contentType;
    public String[] headers;
    boolean isQuery;
    public String method;
    public long nativeRequestPtr;
    public String url;

    public ARHttpRequest() {
        this.contentType = null;
        this.contentEncoding = null;
        this.contentPath = null;
        this.content = null;
    }

    public static HttpUriRequest createHttpRequest(ARHttpRequest arRequest) throws UnsupportedEncodingException {
        int i;
        if (arRequest.method.equals("POST")) {
            HttpPost httpPost = new HttpPost(arRequest.url);
            for (i = 0; i < arRequest.headers.length / 2; i++) {
                httpPost.addHeader(arRequest.headers[i * 2], arRequest.headers[(i * 2) + 1]);
            }
            if (arRequest.isQuery) {
                httpPost.setEntity(new StringEntity("", "UTF-8"));
                return httpPost;
            } else if (arRequest.contentPath != null) {
                httpPost.setEntity(new FileEntity(new File(arRequest.contentPath), arRequest.contentType));
                return httpPost;
            } else if (arRequest.content == null) {
                return httpPost;
            } else {
                ByteArrayEntity bae = new ByteArrayEntity(arRequest.content);
                bae.setContentType(arRequest.contentType);
                httpPost.setEntity(bae);
                return httpPost;
            }
        } else if (arRequest.method.equals("GET")) {
            HttpRequestBase httpGet = new HttpGet(arRequest.url);
            for (i = 0; i < arRequest.headers.length / 2; i++) {
                httpGet.addHeader(arRequest.headers[i * 2], arRequest.headers[(i * 2) + 1]);
            }
            return httpGet;
        } else {
            throw new UnsupportedOperationException("Attemped to use an unsupported HTTP operation");
        }
    }
}
