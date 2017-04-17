package com.qualcomm.ar.pl;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

public class ARHttpClient {
    private static final String MODULENAME = "ARHttpClient";
    ExecutorService executor;
    private Future<?> postDSNTimeoutTask;
    private Future<HttpResponse> requestTask;

    private class HttpResponseCallable implements Callable<HttpResponse> {
        private ARHttpParams arParams;
        private ARHttpRequest arRequest;

        public HttpResponseCallable(ARHttpRequest arRequest, ARHttpParams arParams) {
            this.arRequest = arRequest;
            this.arParams = arParams;
        }

        public HttpResponse call() throws Exception {
            if (this.arParams.delayedStart_ms != 0) {
                Thread.sleep((long) this.arParams.delayedStart_ms);
            }
            return new DefaultHttpClient(ARHttpParams.createHttpParams(this.arParams)).execute(ARHttpRequest.createHttpRequest(this.arRequest));
        }
    }

    private class HttpResponseWatcher implements Runnable {
        private ARHttpParams arParams;
        private ARHttpRequest arRequest;

        public HttpResponseWatcher(ARHttpRequest arRequest, ARHttpParams arParams) {
            this.arParams = arParams;
            this.arRequest = arRequest;
        }

        public void run() {
            ARHttpResponse arResponse = null;
            int networkStatus = ARHttpResponse.ERROR_NONE;
            try {
                arResponse = ARHttpResponse.createARResponse((HttpResponse) ARHttpClient.this.requestTask.get((long) (this.arParams.delayedStart_ms + this.arParams.requestTimeout_ms), TimeUnit.MILLISECONDS));
                if (arResponse.statusCode != 200) {
                }
                if (!(ARHttpClient.this.requestTask == null || ARHttpClient.this.requestTask.isCancelled())) {
                    ARHttpClient.this.requestTask.cancel(true);
                }
                if (networkStatus != ARHttpResponse.ERROR_NONE) {
                    arResponse = ARHttpResponse.createARResponse(networkStatus);
                }
            } catch (CancellationException e) {
                networkStatus = ARHttpResponse.ERROR_CANCELED;
                if (!(ARHttpClient.this.requestTask == null || ARHttpClient.this.requestTask.isCancelled())) {
                    ARHttpClient.this.requestTask.cancel(true);
                }
                if (networkStatus != ARHttpResponse.ERROR_NONE) {
                    arResponse = ARHttpResponse.createARResponse(networkStatus);
                }
            } catch (TimeoutException e2) {
                networkStatus = ARHttpResponse.ERROR_TIMEOUT;
                if (!(ARHttpClient.this.requestTask == null || ARHttpClient.this.requestTask.isCancelled())) {
                    ARHttpClient.this.requestTask.cancel(true);
                }
                if (networkStatus != ARHttpResponse.ERROR_NONE) {
                    arResponse = ARHttpResponse.createARResponse(networkStatus);
                }
            } catch (Exception e3) {
                networkStatus = ARHttpResponse.ERROR_OPERATION_FAILED;
                if (!(ARHttpClient.this.requestTask == null || ARHttpClient.this.requestTask.isCancelled())) {
                    ARHttpClient.this.requestTask.cancel(true);
                }
                if (networkStatus != ARHttpResponse.ERROR_NONE) {
                    arResponse = ARHttpResponse.createARResponse(networkStatus);
                }
            } catch (Throwable th) {
                if (!(ARHttpClient.this.requestTask == null || ARHttpClient.this.requestTask.isCancelled())) {
                    ARHttpClient.this.requestTask.cancel(true);
                }
                if (networkStatus != ARHttpResponse.ERROR_NONE) {
                    arResponse = ARHttpResponse.createARResponse(networkStatus);
                }
            }
            ARHttpClient.this.nativeCallback(arResponse, this.arRequest.nativeRequestPtr);
            ARHttpClient.this.executor.shutdownNow();
        }
    }

    public native void nativeCallback(ARHttpResponse aRHttpResponse, long j);

    public ARHttpClient() {
        this.requestTask = null;
        this.postDSNTimeoutTask = null;
    }

    public boolean execute(ARHttpRequest arRequest, ARHttpParams arParams) {
        if (arRequest == null) {
            return false;
        }
        try {
            this.executor = Executors.newFixedThreadPool(2);
            this.requestTask = this.executor.submit(new HttpResponseCallable(arRequest, arParams));
            this.postDSNTimeoutTask = this.executor.submit(new HttpResponseWatcher(arRequest, arParams));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancel() {
        if (this.requestTask == null) {
            return false;
        }
        this.requestTask.cancel(true);
        return this.requestTask.isDone();
    }
}
