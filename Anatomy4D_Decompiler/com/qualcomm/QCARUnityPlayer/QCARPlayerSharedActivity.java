package com.qualcomm.QCARUnityPlayer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import com.qualcomm.QCAR.QCAR;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QCARPlayerSharedActivity {
    private static final int APPSTATUS_INITED = 3;
    private static final int APPSTATUS_INIT_APP = 0;
    private static final int APPSTATUS_INIT_QCAR = 1;
    private static final int APPSTATUS_INIT_UNITY = 2;
    private static final int APPSTATUS_UNINITED = -1;
    private static final String NATIVE_LIB_QCAR = "Vuforia";
    private static final String NATIVE_LIB_QCARWRAPPER = "QCARWrapper";
    private static final String NATIVE_LIB_UNITYPLAYER = "QCARUnityPlayer";
    private Activity mActivity;
    private int mAppStatus;
    private InitQCARTask mInitQCARTask;
    private InitUnityTask mInitUnityTask;
    private int mQCARFlags;
    private Object mShutdownLock;
    private IUnityInitializer mUnityInitializer;

    public interface IUnityInitializer {
        void InitializeUnity();
    }

    private class InitQCARTask extends AsyncTask<Activity, Integer, Boolean> {
        private int mProgressValue;

        private InitQCARTask() {
            this.mProgressValue = QCARPlayerSharedActivity.APPSTATUS_UNINITED;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Boolean doInBackground(android.app.Activity... r7) {
            /*
            r6 = this;
            r0 = 1;
            r1 = 0;
            r2 = com.qualcomm.QCARUnityPlayer.QCARPlayerSharedActivity.this;
            r3 = r2.mShutdownLock;
            monitor-enter(r3);
            r2 = com.qualcomm.QCAR.QCAR.isInitialized();	 Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0017;
        L_0x000f:
            r2 = "InitQCARTask::doInBackground: forcing QCAR deinitialization";
            com.qualcomm.QCARUnityPlayer.DebugLog.LOGD(r2);	 Catch:{ all -> 0x0059 }
            com.qualcomm.QCAR.QCAR.deinit();	 Catch:{ all -> 0x0059 }
        L_0x0017:
            r2 = r7.length;	 Catch:{ all -> 0x0059 }
            if (r2 <= 0) goto L_0x0055;
        L_0x001a:
            r2 = 0;
            r2 = r7[r2];	 Catch:{ all -> 0x0059 }
        L_0x001d:
            r4 = com.qualcomm.QCARUnityPlayer.QCARPlayerSharedActivity.this;	 Catch:{ all -> 0x0059 }
            r4 = r4.mQCARFlags;	 Catch:{ all -> 0x0059 }
            com.qualcomm.QCAR.QCAR.setInitParameters(r2, r4);	 Catch:{ all -> 0x0059 }
        L_0x0026:
            r2 = com.qualcomm.QCAR.QCAR.init();	 Catch:{ all -> 0x0059 }
            r6.mProgressValue = r2;	 Catch:{ all -> 0x0059 }
            r2 = 1;
            r2 = new java.lang.Integer[r2];	 Catch:{ all -> 0x0059 }
            r4 = 0;
            r5 = r6.mProgressValue;	 Catch:{ all -> 0x0059 }
            r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0059 }
            r2[r4] = r5;	 Catch:{ all -> 0x0059 }
            r6.publishProgress(r2);	 Catch:{ all -> 0x0059 }
            r2 = r6.isCancelled();	 Catch:{ all -> 0x0059 }
            if (r2 != 0) goto L_0x004b;
        L_0x0041:
            r2 = r6.mProgressValue;	 Catch:{ all -> 0x0059 }
            if (r2 < 0) goto L_0x004b;
        L_0x0045:
            r2 = r6.mProgressValue;	 Catch:{ all -> 0x0059 }
            r4 = 100;
            if (r2 < r4) goto L_0x0026;
        L_0x004b:
            r2 = r6.mProgressValue;	 Catch:{ all -> 0x0059 }
            if (r2 <= 0) goto L_0x0057;
        L_0x004f:
            r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ all -> 0x0059 }
            monitor-exit(r3);	 Catch:{ all -> 0x0059 }
            return r0;
        L_0x0055:
            r2 = 0;
            goto L_0x001d;
        L_0x0057:
            r0 = r1;
            goto L_0x004f;
        L_0x0059:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0059 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qualcomm.QCARUnityPlayer.QCARPlayerSharedActivity.InitQCARTask.doInBackground(android.app.Activity[]):java.lang.Boolean");
        }

        protected void onProgressUpdate(Integer... values) {
        }

        protected void onPostExecute(Boolean result) {
            if (result.booleanValue()) {
                DebugLog.LOGI("QCAR initialization successful");
                QCARPlayerSharedActivity.this.updateApplicationStatus(QCARPlayerSharedActivity.APPSTATUS_INIT_UNITY);
                return;
            }
            DebugLog.LOGE("QCAR initialization failed");
            QCARPlayerSharedActivity.this.mUnityInitializer.InitializeUnity();
            QCARPlayerSharedActivity.this.setErrorCode(this.mProgressValue);
        }
    }

    private class InitUnityTask extends AsyncTask<Void, Integer, Boolean> {
        private InitUnityTask() {
        }

        protected Boolean doInBackground(Void... params) {
            return Boolean.valueOf(true);
        }

        protected void onPostExecute(Boolean result) {
            DebugLog.LOGI("Initializing UnityPlayer");
            QCARPlayerSharedActivity.this.mUnityInitializer.InitializeUnity();
            QCARPlayerSharedActivity.this.updateApplicationStatus(QCARPlayerSharedActivity.APPSTATUS_INITED);
        }
    }

    private native void initApplicationNative(int i, int i2, int i3);

    public native void setErrorCode(int i);

    public QCARPlayerSharedActivity() {
        this.mAppStatus = APPSTATUS_UNINITED;
        this.mShutdownLock = new Object();
        this.mQCARFlags = APPSTATUS_INIT_APP;
    }

    static {
        loadLibrary(NATIVE_LIB_QCAR);
        loadLibrary(NATIVE_LIB_QCARWRAPPER);
        loadLibrary(NATIVE_LIB_UNITYPLAYER);
    }

    public void onCreate(Activity activity, int gles_mode, IUnityInitializer unityInitializer) {
        int i = APPSTATUS_INIT_QCAR;
        this.mActivity = activity;
        this.mUnityInitializer = unityInitializer;
        if (gles_mode != APPSTATUS_INIT_QCAR) {
            i = APPSTATUS_INIT_UNITY;
        }
        this.mQCARFlags = i;
        if (QCAR.isInitialized()) {
            QCAR.setInitParameters(this.mActivity, this.mQCARFlags);
            updateApplicationStatus(APPSTATUS_INIT_UNITY);
            return;
        }
        updateApplicationStatus(APPSTATUS_INIT_APP);
    }

    public void onResume() {
        QCAR.onResume();
    }

    public void onPause() {
        QCAR.onPause();
    }

    public void onDestroy() {
        if (!(this.mInitQCARTask == null || this.mInitQCARTask.getStatus() == Status.FINISHED)) {
            this.mInitQCARTask.cancel(true);
            this.mInitQCARTask = null;
        }
        if (!(this.mInitUnityTask == null || this.mInitUnityTask.getStatus() == Status.FINISHED)) {
            this.mInitUnityTask.cancel(true);
            this.mInitUnityTask = null;
        }
        synchronized (this.mShutdownLock) {
            QCAR.deinit();
        }
    }

    private synchronized void updateApplicationStatus(int appStatus) {
        if (this.mAppStatus != appStatus) {
            this.mAppStatus = appStatus;
            switch (this.mAppStatus) {
                case APPSTATUS_INIT_APP /*0*/:
                    initApplication();
                    updateApplicationStatus(APPSTATUS_INIT_QCAR);
                    break;
                case APPSTATUS_INIT_QCAR /*1*/:
                    try {
                        this.mInitQCARTask = new InitQCARTask();
                        InitQCARTask initQCARTask = this.mInitQCARTask;
                        Activity[] activityArr = new Activity[APPSTATUS_INIT_QCAR];
                        activityArr[APPSTATUS_INIT_APP] = this.mActivity;
                        initQCARTask.execute(activityArr);
                        break;
                    } catch (Exception e) {
                        DebugLog.LOGE("Initializing QCAR SDK failed");
                        break;
                    }
                case APPSTATUS_INIT_UNITY /*2*/:
                    try {
                        this.mInitUnityTask = new InitUnityTask();
                        this.mInitUnityTask.execute(new Void[APPSTATUS_INIT_APP]);
                        break;
                    } catch (Exception e2) {
                        DebugLog.LOGE("Initializing Unity failed");
                        break;
                    }
                case APPSTATUS_INITED /*3*/:
                    System.gc();
                    break;
                default:
                    throw new RuntimeException("Invalid application state");
            }
        }
    }

    private void initApplication() {
        int major = APPSTATUS_INIT_APP;
        int minor = APPSTATUS_INIT_APP;
        int change = APPSTATUS_INIT_APP;
        try {
            String[] versionSplit = ReadUnityVersion().split("\\.");
            major = Integer.parseInt(versionSplit[APPSTATUS_INIT_APP]);
            minor = Integer.parseInt(versionSplit[APPSTATUS_INIT_QCAR]);
            change = Integer.parseInt(versionSplit[APPSTATUS_INIT_UNITY]);
        } catch (Exception e) {
            DebugLog.LOGW("Could not interpret unity version number: " + e.getMessage());
        }
        initApplicationNative(major, minor, change);
    }

    private String ReadUnityVersion() {
        String unityVersion = "0.0.0";
        try {
            unityVersion = ReadFileContents(new InputStreamReader(this.mActivity.getAssets().open("QCAR/unity.txt")));
            DebugLog.LOGD("Found unity version file in activity assets.");
        } catch (Exception e) {
            DebugLog.LOGD("Could not find unity version file in activity assets: " + e.getMessage());
        }
        try {
            unityVersion = ReadFileContents(new FileReader(this.mActivity.getFilesDir().getAbsolutePath() + "/unity.txt"));
            DebugLog.LOGD("Found unity version file in internal file dir.");
        } catch (Exception e2) {
            DebugLog.LOGD("Could not find unity version file in internal file dir: " + e2.getMessage());
        }
        try {
            unityVersion = ReadFileContents(new FileReader(this.mActivity.getExternalFilesDir("").getAbsolutePath() + "/unity.txt"));
            DebugLog.LOGD("Found unity version file in external file dir.");
        } catch (Exception e22) {
            DebugLog.LOGD("Could not find unity version file in external file dir: " + e22.getMessage());
        }
        if (unityVersion.equals("0.0.0")) {
            DebugLog.LOGW("No unity version file found this time. This should only happen on first application start in some rare cases. A version file will be generated for the next application start");
        }
        return unityVersion;
    }

    private static String ReadFileContents(InputStreamReader inputStreamReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String ret = bufferedReader.readLine();
        bufferedReader.close();
        return ret;
    }

    public static boolean loadLibrary(String nLibName) {
        try {
            System.loadLibrary(nLibName);
            return true;
        } catch (UnsatisfiedLinkError ulee) {
            DebugLog.LOGE("The library lib" + nLibName + ".so could not be loaded: " + ulee.toString());
            return false;
        } catch (SecurityException e) {
            DebugLog.LOGE("The library lib" + nLibName + ".so was not allowed to be loaded");
            return false;
        }
    }
}
