package com.qualcomm.ar.pl;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.qualcomm.vuforia.PIXEL_FORMAT;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraPreview implements PreviewCallback {
    private static final int AR_CAMERA_DIRECTION_BACK = 268443665;
    private static final int AR_CAMERA_DIRECTION_FRONT = 268443666;
    private static final int AR_CAMERA_DIRECTION_UNKNOWN = 268443664;
    private static final int AR_CAMERA_EXPOSUREMODE_AUTO = 805314560;
    private static final int AR_CAMERA_EXPOSUREMODE_CONTINUOUSAUTO = 805322752;
    private static final int AR_CAMERA_EXPOSUREMODE_NORMAL = 805310464;
    private static final int AR_CAMERA_FOCUSMODE_AUTO = 805306400;
    private static final int AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO = 805306432;
    private static final int AR_CAMERA_FOCUSMODE_FIXED = 805306880;
    private static final int AR_CAMERA_FOCUSMODE_INFINITY = 805306624;
    private static final int AR_CAMERA_FOCUSMODE_MACRO = 805306496;
    private static final int AR_CAMERA_FOCUSMODE_NORMAL = 805306384;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB32 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_ARGB8888 = 268439813;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR24 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGR888 = 268439822;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA32 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_BGRA8888 = 268439814;
    private static final int AR_CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV16 = 268439816;
    private static final int AR_CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB24 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGB888 = 268439811;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA32 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA4444 = 268439821;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA5551 = 268439820;
    private static final int AR_CAMERA_IMAGE_FORMAT_RGBA8888 = 268439812;
    private static final int AR_CAMERA_IMAGE_FORMAT_UNKNOWN = 268439808;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV12 = 268439818;
    private static final int AR_CAMERA_IMAGE_FORMAT_YV16 = 268439819;
    private static final int AR_CAMERA_PARAMTYPE_BASE = 536870912;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE = 536936448;
    private static final int AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE = 536903680;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTRANGE = 537133056;
    private static final int AR_CAMERA_PARAMTYPE_CONTRASTVALUE = 537001984;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURECOMPENSATIONRANGE = 536871424;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURECOMPENSATIONVALUE = 536871168;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREMODE = 536870944;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSURERANGE = 536871040;
    private static final int AR_CAMERA_PARAMTYPE_EXPOSUREVALUE = 536870976;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSMODE = 536870914;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSRANGE = 536870920;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSREGION = 536870928;
    private static final int AR_CAMERA_PARAMTYPE_FOCUSVALUE = 536870916;
    private static final int AR_CAMERA_PARAMTYPE_ISO = 537919488;
    private static final int AR_CAMERA_PARAMTYPE_RECORDING_HINT = 538968064;
    private static final int AR_CAMERA_PARAMTYPE_ROTATION = 537395200;
    private static final int AR_CAMERA_PARAMTYPE_TORCHMODE = 536870913;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE = 536871936;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE = 536875008;
    private static final int AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE = 536872960;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMRANGE = 536887296;
    private static final int AR_CAMERA_PARAMTYPE_ZOOMVALUE = 536879104;
    private static final int AR_CAMERA_PARAMVALUE_BASE = 805306368;
    private static final int AR_CAMERA_STATUS_CAPTURE_RUNNING = 268443651;
    private static final int AR_CAMERA_STATUS_OPENED = 268443650;
    private static final int AR_CAMERA_STATUS_UNINITIALIZED = 268443649;
    private static final int AR_CAMERA_STATUS_UNKNOWN = 268443648;
    private static final int AR_CAMERA_TORCHMODE_AUTO = 805306372;
    private static final int AR_CAMERA_TORCHMODE_CONTINUOUSAUTO = 805306376;
    private static final int AR_CAMERA_TORCHMODE_OFF = 805306369;
    private static final int AR_CAMERA_TORCHMODE_ON = 805306370;
    private static final int AR_CAMERA_TYPE_MONO = 268447761;
    private static final int AR_CAMERA_TYPE_STEREO = 268447762;
    private static final int AR_CAMERA_TYPE_UNKNOWN = 268447760;
    private static final int AR_CAMERA_WHITEBALANCEMODE_AUTO = 805437440;
    private static final int AR_CAMERA_WHITEBALANCEMODE_CONTINUOUSAUTO = 805568512;
    private static final int AR_CAMERA_WHITEBALANCEMODE_NORMAL = 805371904;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_FRAMERATES = 4;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGEFORMATS = 5;
    private static final int CAMERA_CAPSINFO_VALUE_NUM_SUPPORTED_IMAGESIZES = 3;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_PARAMVALUES = 2;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_QUERYABLE_PARAMS = 0;
    private static final int CAMERA_CAPSINFO_VALUE_SUPPORTED_SETTABLE_PARAMS = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_FORMAT = 2;
    private static final int CAMERA_CAPTUREINFO_VALUE_FRAMERATE = 3;
    private static final int CAMERA_CAPTUREINFO_VALUE_HEIGHT = 1;
    private static final int CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED = 4;
    private static final int CAMERA_CAPTUREINFO_VALUE_WIDTH = 0;
    private static final int[] CAMERA_IMAGE_FORMAT_CONVERSIONTABLE;
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = false;
    private static final String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";
    private static final String FOCUS_MODE_NORMAL = "normal";
    private static final String MODULENAME = "CameraPreview";
    private static final int NUM_CAPTURE_BUFFERS = 2;
    private static final int NUM_CAPTURE_BUFFERS_TO_ADD = 2;
    private static final int NUM_MAX_CAMERAOPEN_RETRY = 10;
    private static final int TIME_CAMERAOPEN_RETRY_DELAY_MS = 250;
    private static final int _NUM_CAMERA_CAPSINFO_VALUE_ = 6;
    private static final int _NUM_CAMERA_CAPTUREINFO_VALUE_ = 5;
    private static Object[] _addCallbackBufferArgs;
    private static Method _addCallbackBufferFunc;
    private static Method _setPreviewCallbackWithBufferFunc;
    private static Method _setPreviewTextureFunc;
    private static Constructor<?> _surfaceTextureConstructor;
    private static Method _updateTexImage;
    private Vector<CameraCacheInfo> cameraCacheInfo;
    private HashMap<Camera, Integer> cameraCacheInfoIndexCache;
    private SurfaceManager surfaceManager;

    /* renamed from: com.qualcomm.ar.pl.CameraPreview.1 */
    class C00561 implements AutoFocusCallback {
        C00561() {
        }

        public void onAutoFocus(boolean success, Camera camera) {
            Object intObj = CameraPreview.this.cameraCacheInfoIndexCache.get(camera);
            if (intObj != null) {
                CameraCacheInfo cci = CameraPreview.this.getCameraCacheInfo(((Integer) intObj).intValue());
                if (cci != null) {
                    cci.isAutoFocusing = false;
                }
            }
        }
    }

    public class CameraCacheInfo {
        byte[][] buffer;
        int bufferFormatPL;
        int bufferHeight;
        int bufferWidth;
        Camera camera;
        int[] caps;
        int deviceID;
        boolean isAutoFocusing;
        int overrideFormatAndroid;
        int overrideHeight;
        int overrideWidth;
        int requestFormatAndroid;
        int requestHeight;
        int requestWidth;
        int status;
        CameraSurface surface;
        Object surfaceTexture;
    }

    private native void newFrameAvailable(int i, int i2, int i3, int i4, byte[] bArr);

    static {
        CAMERA_IMAGE_FORMAT_CONVERSIONTABLE = new int[]{16, AR_CAMERA_IMAGE_FORMAT_NV16, 17, AR_CAMERA_IMAGE_FORMAT_NV21, CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED, AR_CAMERA_IMAGE_FORMAT_RGB565, 842094169, AR_CAMERA_IMAGE_FORMAT_YV12};
        CONVERT_FORMAT_TO_PL = true;
        CONVERT_FORMAT_TO_ANDROID = false;
    }

    public CameraPreview() {
        this.surfaceManager = null;
        this.cameraCacheInfo = null;
        this.cameraCacheInfoIndexCache = null;
        _addCallbackBufferFunc = null;
        _addCallbackBufferArgs = null;
        _setPreviewCallbackWithBufferFunc = null;
    }

    private boolean checkPermission() {
        try {
            Activity activity = SystemTools.getActivityFromNative();
            if (activity.getPackageManager().checkPermission("android.permission.CAMERA", activity.getPackageName()) == 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private int getCameraDeviceIndex(int camIndex, int type, int direction) {
        if (type != AR_CAMERA_TYPE_UNKNOWN) {
        }
        if (SystemTools.checkMinimumApiLevel(9)) {
            int camInfoDirection = -1;
            switch (direction) {
                case AR_CAMERA_DIRECTION_UNKNOWN /*268443664*/:
                    break;
                case AR_CAMERA_DIRECTION_BACK /*268443665*/:
                    camInfoDirection = CAMERA_CAPTUREINFO_VALUE_WIDTH;
                    break;
                case AR_CAMERA_DIRECTION_FRONT /*268443666*/:
                    camInfoDirection = CAMERA_CAPTUREINFO_VALUE_HEIGHT;
                    break;
                default:
                    SystemTools.setSystemErrorCode(NUM_CAPTURE_BUFFERS_TO_ADD);
                    return -1;
            }
            int num = Camera.getNumberOfCameras();
            int i = CAMERA_CAPTUREINFO_VALUE_WIDTH;
            while (i < num) {
                CameraInfo cameraInfo = new CameraInfo();
                try {
                    Camera.getCameraInfo(i, cameraInfo);
                    if ((camInfoDirection < 0 || camInfoDirection == cameraInfo.facing) && (camIndex < 0 || camIndex == i)) {
                        return i;
                    }
                } catch (Exception e) {
                }
                i += CAMERA_CAPTUREINFO_VALUE_HEIGHT;
            }
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return -1;
        } else if (direction == AR_CAMERA_DIRECTION_FRONT) {
            SystemTools.setSystemErrorCode(NUM_CAPTURE_BUFFERS_TO_ADD);
            return -1;
        } else if (camIndex < CAMERA_CAPTUREINFO_VALUE_HEIGHT) {
            return CAMERA_CAPTUREINFO_VALUE_WIDTH;
        } else {
            SystemTools.setSystemErrorCode(NUM_CAPTURE_BUFFERS_TO_ADD);
            return -1;
        }
    }

    private Parameters getCameraParameters(Camera camera) {
        Parameters params = null;
        try {
            params = camera.getParameters();
        } catch (Exception e) {
        }
        return params;
    }

    private CameraCacheInfo getCameraCacheInfo(int cameraCacheInfoIndex) {
        if (cameraCacheInfoIndex < 0 || cameraCacheInfoIndex >= this.cameraCacheInfo.size()) {
            return null;
        }
        return (CameraCacheInfo) this.cameraCacheInfo.get(cameraCacheInfoIndex);
    }

    private boolean setCustomCameraParams(Parameters cameraParams, String customData) {
        try {
            JSONObject jSONObject;
            JSONObject jsonObj = new JSONObject(customData);
            Iterator<?> elements = jsonObj.keys();
            while (elements.hasNext()) {
                String key = (String) elements.next();
                try {
                    Object value = jsonObj.get(key);
                    if (value.getClass() == String.class) {
                        cameraParams.set(key, (String) value);
                    } else if (value.getClass() == Integer.class) {
                        cameraParams.set(key, ((Integer) value).intValue());
                    } else {
                        jSONObject = jsonObj;
                        return false;
                    }
                } catch (JSONException e) {
                    jSONObject = jsonObj;
                    return false;
                }
            }
            jSONObject = jsonObj;
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    private boolean setCameraPreviewFps(int fps, Parameters params) {
        int min_fps = fps * 1000;
        int[] selected_range = null;
        for (int[] range : params.getSupportedPreviewFpsRange()) {
            if (range[CAMERA_CAPTUREINFO_VALUE_WIDTH] == min_fps && range[CAMERA_CAPTUREINFO_VALUE_HEIGHT] - range[CAMERA_CAPTUREINFO_VALUE_WIDTH] < Integer.MAX_VALUE) {
                selected_range = range;
            }
        }
        if (selected_range == null) {
            return false;
        }
        params.setPreviewFpsRange(selected_range[CAMERA_CAPTUREINFO_VALUE_WIDTH], selected_range[CAMERA_CAPTUREINFO_VALUE_HEIGHT]);
        return true;
    }

    private boolean setCameraCaptureParams(CameraCacheInfo camCacheInfo, Parameters camParams, int[] captureInfo, int[] overrideCaptureInfo) {
        if (!(captureInfo == null && overrideCaptureInfo == null)) {
            camCacheInfo.overrideWidth = overrideCaptureInfo != null ? overrideCaptureInfo[CAMERA_CAPTUREINFO_VALUE_WIDTH] : captureInfo[CAMERA_CAPTUREINFO_VALUE_WIDTH];
            camCacheInfo.overrideHeight = overrideCaptureInfo != null ? overrideCaptureInfo[CAMERA_CAPTUREINFO_VALUE_HEIGHT] : captureInfo[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
            camCacheInfo.overrideFormatAndroid = translateImageFormat(overrideCaptureInfo != null ? overrideCaptureInfo[NUM_CAPTURE_BUFFERS_TO_ADD] : captureInfo[NUM_CAPTURE_BUFFERS_TO_ADD], CONVERT_FORMAT_TO_ANDROID);
        }
        if (captureInfo == null) {
            return true;
        }
        camCacheInfo.requestWidth = captureInfo[CAMERA_CAPTUREINFO_VALUE_WIDTH];
        camCacheInfo.requestHeight = captureInfo[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
        camCacheInfo.requestFormatAndroid = translateImageFormat(captureInfo[NUM_CAPTURE_BUFFERS_TO_ADD], CONVERT_FORMAT_TO_ANDROID);
        int framerate = captureInfo[CAMERA_CAPTUREINFO_VALUE_FRAMERATE];
        try {
            boolean previewSurfaceEnabled;
            if (camCacheInfo.requestWidth > 0 && camCacheInfo.requestHeight > 0) {
                camParams.setPreviewSize(camCacheInfo.requestWidth, camCacheInfo.requestHeight);
            }
            if (framerate > 0) {
                if (!SystemTools.checkMinimumApiLevel(8)) {
                    camParams.setPreviewFrameRate(framerate);
                } else if (!setCameraPreviewFps(framerate, camParams)) {
                    camParams.setPreviewFrameRate(framerate);
                }
            }
            if (camCacheInfo.requestFormatAndroid != 0) {
                camParams.setPreviewFormat(camCacheInfo.requestFormatAndroid);
            }
            if (captureInfo[CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED] > 0) {
                previewSurfaceEnabled = true;
            } else {
                previewSurfaceEnabled = false;
            }
            if (previewSurfaceEnabled) {
                if (SystemTools.checkMinimumApiLevel(11)) {
                    Object[] argList = new Object[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
                    argList[CAMERA_CAPTUREINFO_VALUE_WIDTH] = new Integer(-1);
                    try {
                        camCacheInfo.surfaceTexture = _surfaceTextureConstructor.newInstance(argList);
                        try {
                            argList[CAMERA_CAPTUREINFO_VALUE_WIDTH] = camCacheInfo.surfaceTexture;
                            _setPreviewTextureFunc.invoke(camCacheInfo.camera, argList);
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
                        return false;
                    }
                } else if (this.surfaceManager == null) {
                    return false;
                } else {
                    if (!this.surfaceManager.addCameraSurface(camCacheInfo)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e3) {
            return false;
        }
    }

    private boolean setupPreviewBuffer(CameraCacheInfo cci) {
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            return false;
        }
        try {
            int bitsPerPixel;
            cci.bufferWidth = cci.requestWidth == cci.overrideWidth ? cameraParams.getPreviewSize().width : cci.overrideWidth;
            cci.bufferHeight = cci.requestHeight == cci.overrideHeight ? cameraParams.getPreviewSize().height : cci.overrideHeight;
            int bufferFormatAndroid = cci.requestFormatAndroid == cci.overrideFormatAndroid ? cameraParams.getPreviewFormat() : cci.overrideFormatAndroid;
            cci.bufferFormatPL = translateImageFormat(bufferFormatAndroid, CONVERT_FORMAT_TO_PL);
            try {
                PixelFormat pixelFormatInfo = new PixelFormat();
                PixelFormat.getPixelFormatInfo(bufferFormatAndroid, pixelFormatInfo);
                bitsPerPixel = pixelFormatInfo.bitsPerPixel;
            } catch (Exception e) {
                bitsPerPixel = getBitsPerPixel(bufferFormatAndroid);
                if (bitsPerPixel == 0) {
                    return false;
                }
            }
            int bufferSize = (((cci.bufferWidth * cci.bufferHeight) * bitsPerPixel) / 8) + AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD;
            cci.buffer = new byte[NUM_CAPTURE_BUFFERS_TO_ADD][];
            for (int i = CAMERA_CAPTUREINFO_VALUE_WIDTH; i < NUM_CAPTURE_BUFFERS_TO_ADD; i += CAMERA_CAPTUREINFO_VALUE_HEIGHT) {
                cci.buffer[i] = new byte[bufferSize];
                if (i < NUM_CAPTURE_BUFFERS_TO_ADD) {
                    _addCallbackBuffer(cci.camera, cci.buffer[i]);
                }
            }
            _setPreviewCallbackWithBuffer(cci.camera, this);
            System.gc();
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private void setCameraCapsBit(CameraCacheInfo cci, int capsIndex, int paramType, boolean value) {
        int baseValue;
        switch (capsIndex) {
            case CAMERA_CAPTUREINFO_VALUE_WIDTH /*0*/:
            case CAMERA_CAPTUREINFO_VALUE_HEIGHT /*1*/:
                baseValue = AR_CAMERA_PARAMTYPE_BASE;
                break;
            case NUM_CAPTURE_BUFFERS_TO_ADD /*2*/:
                baseValue = AR_CAMERA_PARAMVALUE_BASE;
                break;
            default:
                return;
        }
        int index = (int) (Math.log((double) ((baseValue ^ -1) & paramType)) / Math.log(2.0d));
        if (value) {
            int[] iArr = cci.caps;
            iArr[capsIndex] = iArr[capsIndex] | (CAMERA_CAPTUREINFO_VALUE_HEIGHT << index);
            return;
        }
        iArr = cci.caps;
        iArr[capsIndex] = iArr[capsIndex] & ((CAMERA_CAPTUREINFO_VALUE_HEIGHT << index) ^ -1);
    }

    private int translateImageFormat(int fromValue, boolean conversionMode) {
        int i = CAMERA_CAPTUREINFO_VALUE_WIDTH;
        while (i < CAMERA_IMAGE_FORMAT_CONVERSIONTABLE.length / NUM_CAPTURE_BUFFERS_TO_ADD) {
            if (fromValue != (conversionMode == CONVERT_FORMAT_TO_PL ? CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i * NUM_CAPTURE_BUFFERS_TO_ADD] : CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i * NUM_CAPTURE_BUFFERS_TO_ADD) + CAMERA_CAPTUREINFO_VALUE_HEIGHT])) {
                i += CAMERA_CAPTUREINFO_VALUE_HEIGHT;
            } else if (conversionMode == CONVERT_FORMAT_TO_PL) {
                return CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[(i * NUM_CAPTURE_BUFFERS_TO_ADD) + CAMERA_CAPTUREINFO_VALUE_HEIGHT];
            } else {
                return CAMERA_IMAGE_FORMAT_CONVERSIONTABLE[i * NUM_CAPTURE_BUFFERS_TO_ADD];
            }
        }
        return conversionMode == CONVERT_FORMAT_TO_PL ? AR_CAMERA_IMAGE_FORMAT_UNKNOWN : CAMERA_CAPTUREINFO_VALUE_WIDTH;
    }

    int getBitsPerPixel(int imgFormat) {
        switch (imgFormat) {
            case CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED /*4*/:
            case PIXEL_FORMAT.RGBA8888 /*16*/:
                return 16;
            case 17:
                return 12;
            case 842094169:
                return 12;
            default:
                return CAMERA_CAPTUREINFO_VALUE_WIDTH;
        }
    }

    public void onPreviewFrame(byte[] capturedBuffer, Camera camera) {
        Object intObj = this.cameraCacheInfoIndexCache.get(camera);
        if (intObj != null) {
            int cameraCacheInfoIndex = ((Integer) intObj).intValue();
            CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
            if (cci != null) {
                newFrameAvailable(cameraCacheInfoIndex, cci.bufferWidth, cci.bufferHeight, cci.bufferFormatPL, capturedBuffer);
                _addCallbackBuffer(camera, capturedBuffer);
            }
        }
    }

    private void _addCallbackBuffer(Camera cam, byte[] buffer) {
        _addCallbackBufferArgs[CAMERA_CAPTUREINFO_VALUE_WIDTH] = buffer;
        try {
            _addCallbackBufferFunc.invoke(cam, _addCallbackBufferArgs);
        } catch (Exception e) {
        }
    }

    private void _setPreviewCallbackWithBuffer(Camera cam, PreviewCallback previewCB) {
        Object[] args = new Object[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
        args[CAMERA_CAPTUREINFO_VALUE_WIDTH] = previewCB;
        try {
            _setPreviewCallbackWithBufferFunc.invoke(cam, args);
        } catch (Exception e) {
        }
    }

    public boolean init() {
        this.cameraCacheInfo = new Vector();
        this.cameraCacheInfoIndexCache = new HashMap();
        String CLASSNAME_CAMERA = "android.hardware.Camera";
        String CLASSNAME_SURFACETEXTURE = "android.graphics.SurfaceTexture";
        try {
            Class<?> cameraClass = Class.forName("android.hardware.Camera");
            Class<?>[] partypes = new Class[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
            partypes[CAMERA_CAPTUREINFO_VALUE_WIDTH] = new byte[CAMERA_CAPTUREINFO_VALUE_HEIGHT].getClass();
            _addCallbackBufferFunc = SystemTools.retrieveClassMethod(cameraClass, "addCallbackBuffer", partypes);
            if (_addCallbackBufferFunc == null) {
                return false;
            }
            _addCallbackBufferArgs = new Object[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
            Class[] clsArr = new Class[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
            clsArr[CAMERA_CAPTUREINFO_VALUE_WIDTH] = PreviewCallback.class;
            _setPreviewCallbackWithBufferFunc = SystemTools.retrieveClassMethod(cameraClass, "setPreviewCallbackWithBuffer", clsArr);
            if (_setPreviewCallbackWithBufferFunc == null) {
                return false;
            }
            if (SystemTools.checkMinimumApiLevel(11)) {
                Class<?> surfaceTextureClass = Class.forName("android.graphics.SurfaceTexture");
                clsArr = new Class[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
                clsArr[CAMERA_CAPTUREINFO_VALUE_WIDTH] = surfaceTextureClass;
                _setPreviewTextureFunc = SystemTools.retrieveClassMethod(cameraClass, "setPreviewTexture", clsArr);
                if (_setPreviewTextureFunc == null) {
                    return false;
                }
                Class[] clsArr2 = new Class[CAMERA_CAPTUREINFO_VALUE_HEIGHT];
                clsArr2[CAMERA_CAPTUREINFO_VALUE_WIDTH] = Integer.TYPE;
                _surfaceTextureConstructor = surfaceTextureClass.getConstructor(clsArr2);
                if (_surfaceTextureConstructor == null) {
                    return false;
                }
                _updateTexImage = SystemTools.retrieveClassMethod(surfaceTextureClass, "updateTexImage", new Class[CAMERA_CAPTUREINFO_VALUE_WIDTH]);
                if (_updateTexImage == null) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setSurfaceManager(SurfaceManager sm) {
        this.surfaceManager = sm;
    }

    public int getNumberOfCameras() {
        int i = -1;
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return i;
        } else if (SystemTools.checkMinimumApiLevel(9)) {
            try {
                return Camera.getNumberOfCameras();
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return i;
            }
        } else {
            try {
                int i2;
                if (SystemTools.getActivityFromNative().getPackageManager().hasSystemFeature("android.hardware.camera")) {
                    i2 = CAMERA_CAPTUREINFO_VALUE_HEIGHT;
                } else {
                    i2 = CAMERA_CAPTUREINFO_VALUE_WIDTH;
                }
                return i2;
            } catch (Exception e2) {
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return i;
            }
        }
    }

    public int getDirection(int cameraID) {
        if (!checkPermission()) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return -1;
        } else if (!SystemTools.checkMinimumApiLevel(9)) {
            return AR_CAMERA_DIRECTION_BACK;
        } else {
            CameraInfo cameraInfo = new CameraInfo();
            try {
                Camera.getCameraInfo(cameraID, cameraInfo);
                switch (cameraInfo.facing) {
                    case CAMERA_CAPTUREINFO_VALUE_WIDTH /*0*/:
                        return AR_CAMERA_DIRECTION_BACK;
                    case CAMERA_CAPTUREINFO_VALUE_HEIGHT /*1*/:
                        return AR_CAMERA_DIRECTION_FRONT;
                    default:
                        return AR_CAMERA_DIRECTION_UNKNOWN;
                }
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return -1;
            }
        }
    }

    public int getDeviceID(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci != null) {
            return cci.deviceID;
        }
        SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
        return -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int open(int r20, int r21, int r22, java.lang.String r23, int[] r24, int[] r25) {
        /*
        r19 = this;
        r16 = r19.checkPermission();
        if (r16 != 0) goto L_0x000d;
    L_0x0006:
        r16 = 6;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
    L_0x000c:
        return r3;
    L_0x000d:
        r5 = r19.getCameraDeviceIndex(r20, r21, r22);
        if (r5 >= 0) goto L_0x0015;
    L_0x0013:
        r3 = -1;
        goto L_0x000c;
    L_0x0015:
        r3 = -1;
        r9 = 0;
        r0 = r19;
        r0 = r0.cameraCacheInfo;
        r16 = r0;
        r4 = r16.size();
        r11 = 0;
    L_0x0022:
        if (r11 >= r4) goto L_0x003b;
    L_0x0024:
        r0 = r19;
        r0 = r0.cameraCacheInfo;
        r16 = r0;
        r0 = r16;
        r9 = r0.get(r11);
        r9 = (com.qualcomm.ar.pl.CameraPreview.CameraCacheInfo) r9;
        r0 = r9.deviceID;
        r16 = r0;
        r0 = r16;
        if (r0 != r5) goto L_0x00dc;
    L_0x003a:
        r3 = r11;
    L_0x003b:
        if (r3 >= 0) goto L_0x009a;
    L_0x003d:
        r9 = new com.qualcomm.ar.pl.CameraPreview$CameraCacheInfo;
        r0 = r19;
        r9.<init>();
        r9.deviceID = r5;
        r16 = 0;
        r0 = r16;
        r9.camera = r0;
        r16 = 0;
        r0 = r16;
        r9.surface = r0;
        r16 = 0;
        r16 = (byte[][]) r16;
        r0 = r16;
        r9.buffer = r0;
        r16 = 0;
        r0 = r16;
        r9.overrideWidth = r0;
        r0 = r16;
        r9.requestWidth = r0;
        r0 = r16;
        r9.bufferWidth = r0;
        r16 = 0;
        r0 = r16;
        r9.overrideHeight = r0;
        r0 = r16;
        r9.requestHeight = r0;
        r0 = r16;
        r9.bufferHeight = r0;
        r16 = 268439808; // 0x10001100 float:2.5256645E-29 double:1.32626887E-315;
        r0 = r16;
        r9.bufferFormatPL = r0;
        r16 = 0;
        r0 = r16;
        r9.overrideFormatAndroid = r0;
        r0 = r16;
        r9.requestFormatAndroid = r0;
        r16 = 0;
        r0 = r16;
        r9.caps = r0;
        r16 = 268443649; // 0x10002001 float:2.5268204E-29 double:1.32628785E-315;
        r0 = r16;
        r9.status = r0;
        r16 = 0;
        r0 = r16;
        r9.isAutoFocusing = r0;
    L_0x009a:
        r13 = 0;
        r6 = 10;
        r7 = r6;
    L_0x009e:
        r16 = 9;
        r16 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r16);	 Catch:{ Exception -> 0x00ef }
        if (r16 == 0) goto L_0x00e0;
    L_0x00a6:
        r0 = r9.deviceID;	 Catch:{ Exception -> 0x00ef }
        r16 = r0;
        r16 = android.hardware.Camera.open(r16);	 Catch:{ Exception -> 0x00ef }
        r0 = r16;
        r9.camera = r0;	 Catch:{ Exception -> 0x00ef }
    L_0x00b2:
        r0 = r9.camera;	 Catch:{ Exception -> 0x00ef }
        r16 = r0;
        if (r16 == 0) goto L_0x00f1;
    L_0x00b8:
        r13 = 1;
    L_0x00b9:
        if (r13 != 0) goto L_0x00c8;
    L_0x00bb:
        if (r7 <= 0) goto L_0x00c8;
    L_0x00bd:
        monitor-enter(r19);	 Catch:{ Exception -> 0x00f6 }
        r16 = 250; // 0xfa float:3.5E-43 double:1.235E-321;
        r0 = r19;
        r1 = r16;
        r0.wait(r1);	 Catch:{ all -> 0x00f3 }
        monitor-exit(r19);	 Catch:{ all -> 0x00f3 }
    L_0x00c8:
        if (r13 != 0) goto L_0x01bb;
    L_0x00ca:
        r6 = r7 + -1;
        if (r7 > 0) goto L_0x01b8;
    L_0x00ce:
        r0 = r9.camera;
        r16 = r0;
        if (r16 != 0) goto L_0x00f8;
    L_0x00d4:
        r16 = 6;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
        goto L_0x000c;
    L_0x00dc:
        r11 = r11 + 1;
        goto L_0x0022;
    L_0x00e0:
        r0 = r9.deviceID;	 Catch:{ Exception -> 0x00ef }
        r16 = r0;
        if (r16 != 0) goto L_0x00b2;
    L_0x00e6:
        r16 = android.hardware.Camera.open();	 Catch:{ Exception -> 0x00ef }
        r0 = r16;
        r9.camera = r0;	 Catch:{ Exception -> 0x00ef }
        goto L_0x00b2;
    L_0x00ef:
        r16 = move-exception;
        goto L_0x00b9;
    L_0x00f1:
        r13 = 0;
        goto L_0x00b9;
    L_0x00f3:
        r16 = move-exception;
        monitor-exit(r19);	 Catch:{ all -> 0x00f3 }
        throw r16;	 Catch:{ Exception -> 0x00f6 }
    L_0x00f6:
        r16 = move-exception;
        goto L_0x00c8;
    L_0x00f8:
        if (r24 == 0) goto L_0x0101;
    L_0x00fa:
        r0 = r24;
        r0 = r0.length;
        r16 = r0;
        if (r16 > 0) goto L_0x010a;
    L_0x0101:
        if (r25 == 0) goto L_0x012e;
    L_0x0103:
        r0 = r25;
        r0 = r0.length;
        r16 = r0;
        if (r16 <= 0) goto L_0x012e;
    L_0x010a:
        r14 = 1;
    L_0x010b:
        if (r23 == 0) goto L_0x0130;
    L_0x010d:
        r16 = r23.length();
        if (r16 <= 0) goto L_0x0130;
    L_0x0113:
        r15 = 1;
    L_0x0114:
        if (r14 != 0) goto L_0x0118;
    L_0x0116:
        if (r15 == 0) goto L_0x017c;
    L_0x0118:
        r0 = r9.camera;
        r16 = r0;
        r0 = r19;
        r1 = r16;
        r8 = r0.getCameraParameters(r1);
        if (r8 != 0) goto L_0x0132;
    L_0x0126:
        r16 = 6;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
        goto L_0x000c;
    L_0x012e:
        r14 = 0;
        goto L_0x010b;
    L_0x0130:
        r15 = 0;
        goto L_0x0114;
    L_0x0132:
        if (r14 == 0) goto L_0x015f;
    L_0x0134:
        if (r24 == 0) goto L_0x014b;
    L_0x0136:
        r0 = r24;
        r0 = r0.length;
        r16 = r0;
        r17 = 5;
        r0 = r16;
        r1 = r17;
        if (r0 == r1) goto L_0x014b;
    L_0x0143:
        r16 = 2;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
        goto L_0x000c;
    L_0x014b:
        r0 = r19;
        r1 = r24;
        r2 = r25;
        r12 = r0.setCameraCaptureParams(r9, r8, r1, r2);
        if (r12 != 0) goto L_0x015f;
    L_0x0157:
        r16 = 6;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
        goto L_0x000c;
    L_0x015f:
        if (r15 == 0) goto L_0x0173;
    L_0x0161:
        r0 = r19;
        r1 = r23;
        r12 = r0.setCustomCameraParams(r8, r1);
        if (r12 != 0) goto L_0x0173;
    L_0x016b:
        r16 = 2;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
        goto L_0x000c;
    L_0x0173:
        r0 = r9.camera;	 Catch:{ Exception -> 0x01af }
        r16 = r0;
        r0 = r16;
        r0.setParameters(r8);	 Catch:{ Exception -> 0x01af }
    L_0x017c:
        r16 = 268443650; // 0x10002002 float:2.5268207E-29 double:1.326287853E-315;
        r0 = r16;
        r9.status = r0;
        if (r3 >= 0) goto L_0x019c;
    L_0x0185:
        r0 = r19;
        r0 = r0.cameraCacheInfo;
        r16 = r0;
        r0 = r16;
        r0.add(r9);
        r0 = r19;
        r0 = r0.cameraCacheInfo;
        r16 = r0;
        r16 = r16.size();
        r3 = r16 + -1;
    L_0x019c:
        r0 = r19;
        r0 = r0.cameraCacheInfoIndexCache;
        r16 = r0;
        r0 = r9.camera;
        r17 = r0;
        r18 = java.lang.Integer.valueOf(r3);
        r16.put(r17, r18);
        goto L_0x000c;
    L_0x01af:
        r10 = move-exception;
        r16 = 6;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r16);
        r3 = -1;
        goto L_0x000c;
    L_0x01b8:
        r7 = r6;
        goto L_0x009e;
    L_0x01bb:
        r6 = r7;
        goto L_0x00ce;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qualcomm.ar.pl.CameraPreview.open(int, int, int, java.lang.String, int[], int[]):int");
    }

    public boolean close(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return false;
        }
        this.cameraCacheInfoIndexCache.remove(cci.camera);
        boolean result = false;
        try {
            cci.camera.release();
            result = true;
        } catch (Exception e) {
        }
        cci.camera = null;
        cci.buffer = (byte[][]) CAMERA_CAPTUREINFO_VALUE_WIDTH;
        cci.status = AR_CAMERA_STATUS_UNINITIALIZED;
        System.gc();
        return result;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int[] getCameraCapabilities(int r26) {
        /*
        r25 = this;
        r6 = r25.getCameraCacheInfo(r26);
        if (r6 != 0) goto L_0x000e;
    L_0x0006:
        r22 = 4;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r22);
        r22 = 0;
    L_0x000d:
        return r22;
    L_0x000e:
        r0 = r6.caps;
        r22 = r0;
        if (r22 == 0) goto L_0x0019;
    L_0x0014:
        r0 = r6.caps;
        r22 = r0;
        goto L_0x000d;
    L_0x0019:
        r0 = r6.camera;
        r22 = r0;
        r0 = r25;
        r1 = r22;
        r3 = r0.getCameraParameters(r1);
        if (r3 != 0) goto L_0x002f;
    L_0x0027:
        r22 = 6;
        com.qualcomm.ar.pl.SystemTools.setSystemErrorCode(r22);
        r22 = 0;
        goto L_0x000d;
    L_0x002f:
        r21 = r3.getSupportedPreviewSizes();
        r19 = r3.getSupportedPreviewFrameRates();
        r20 = r3.getSupportedPreviewFormats();
        r17 = r3.getSupportedFlashModes();
        r18 = r3.getSupportedFocusModes();
        if (r21 == 0) goto L_0x02a4;
    L_0x0045:
        r15 = r21.size();
    L_0x0049:
        if (r19 == 0) goto L_0x02a7;
    L_0x004b:
        r13 = r19.size();
    L_0x004f:
        if (r20 == 0) goto L_0x02aa;
    L_0x0051:
        r14 = r20.size();
    L_0x0055:
        r22 = r15 * 2;
        r22 = r22 + 6;
        r22 = r22 + r13;
        r4 = r22 + r14;
        r0 = new int[r4];
        r22 = r0;
        r0 = r22;
        r6.caps = r0;
        r5 = 0;
        r0 = r6.caps;
        r22 = r0;
        r23 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r22[r5] = r23;
        r23 = 536870913; // 0x20000001 float:1.0842023E-19 double:2.652494744E-315;
        if (r17 == 0) goto L_0x02b1;
    L_0x0073:
        r22 = "torch";
        r0 = r17;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 != 0) goto L_0x008b;
    L_0x007f:
        r22 = "on";
        r0 = r17;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 == 0) goto L_0x02ad;
    L_0x008b:
        r22 = 1;
    L_0x008d:
        r0 = r25;
        r1 = r23;
        r2 = r22;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 536870914; // 0x20000002 float:1.0842024E-19 double:2.65249475E-315;
        r23 = 1;
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 536870916; // 0x20000004 float:1.0842027E-19 double:2.65249476E-315;
        r23 = 8;
        r23 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r23);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 536871168; // 0x20000100 float:1.0842353E-19 double:2.652496004E-315;
        r23 = 8;
        r23 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r23);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 536871424; // 0x20000200 float:1.0842683E-19 double:2.65249727E-315;
        r23 = 8;
        r23 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r23);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r23 = 536879104; // 0x20002000 float:1.085261E-19 double:2.652535213E-315;
        r22 = 8;
        r22 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r22);
        if (r22 == 0) goto L_0x02b5;
    L_0x00e5:
        r22 = r3.isZoomSupported();
        if (r22 == 0) goto L_0x02b5;
    L_0x00eb:
        r22 = 1;
    L_0x00ed:
        r0 = r25;
        r1 = r23;
        r2 = r22;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r23 = 536887296; // 0x20004000 float:1.0863198E-19 double:2.652575686E-315;
        r22 = 8;
        r22 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r22);
        if (r22 == 0) goto L_0x02b9;
    L_0x0101:
        r22 = r3.isZoomSupported();
        if (r22 == 0) goto L_0x02b9;
    L_0x0107:
        r22 = 1;
    L_0x0109:
        r0 = r25;
        r1 = r23;
        r2 = r22;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r5 = 1;
        r0 = r6.caps;
        r22 = r0;
        r23 = 536870912; // 0x20000000 float:1.0842022E-19 double:2.652494739E-315;
        r22[r5] = r23;
        r23 = 536870913; // 0x20000001 float:1.0842023E-19 double:2.652494744E-315;
        if (r17 == 0) goto L_0x02c1;
    L_0x0120:
        r22 = "torch";
        r0 = r17;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 != 0) goto L_0x0138;
    L_0x012c:
        r22 = "on";
        r0 = r17;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 == 0) goto L_0x02bd;
    L_0x0138:
        r22 = 1;
    L_0x013a:
        r0 = r25;
        r1 = r23;
        r2 = r22;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 536870914; // 0x20000002 float:1.0842024E-19 double:2.65249475E-315;
        r23 = 1;
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 536871168; // 0x20000100 float:1.0842353E-19 double:2.652496004E-315;
        r23 = 8;
        r23 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r23);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r23 = 536879104; // 0x20002000 float:1.085261E-19 double:2.652535213E-315;
        r22 = 8;
        r22 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r22);
        if (r22 == 0) goto L_0x02c5;
    L_0x016e:
        r22 = r3.isZoomSupported();
        if (r22 == 0) goto L_0x02c5;
    L_0x0174:
        r22 = 1;
    L_0x0176:
        r0 = r25;
        r1 = r23;
        r2 = r22;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r5 = 2;
        r0 = r6.caps;
        r22 = r0;
        r23 = 805306368; // 0x30000000 float:4.656613E-10 double:3.97874211E-315;
        r22[r5] = r23;
        if (r17 == 0) goto L_0x01be;
    L_0x018a:
        r22 = "torch";
        r0 = r17;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 != 0) goto L_0x01a2;
    L_0x0196:
        r22 = "on";
        r0 = r17;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 == 0) goto L_0x01be;
    L_0x01a2:
        r22 = 805306369; // 0x30000001 float:4.6566134E-10 double:3.978742113E-315;
        r23 = 1;
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 805306370; // 0x30000002 float:4.656614E-10 double:3.97874212E-315;
        r23 = 1;
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
    L_0x01be:
        if (r18 == 0) goto L_0x025c;
    L_0x01c0:
        r22 = 805306384; // 0x30000010 float:4.656622E-10 double:3.978742187E-315;
        r23 = 1;
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 805306400; // 0x30000020 float:4.6566306E-10 double:3.978742266E-315;
        r23 = "auto";
        r0 = r18;
        r1 = r23;
        r23 = r0.contains(r1);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r23 = 805306432; // 0x30000040 float:4.6566484E-10 double:3.978742424E-315;
        r22 = 9;
        r22 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r22);
        if (r22 == 0) goto L_0x01fb;
    L_0x01ef:
        r22 = "continuous-video";
        r0 = r18;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 != 0) goto L_0x020f;
    L_0x01fb:
        r22 = 14;
        r22 = com.qualcomm.ar.pl.SystemTools.checkMinimumApiLevel(r22);
        if (r22 == 0) goto L_0x02c9;
    L_0x0203:
        r22 = "continuous-picture";
        r0 = r18;
        r1 = r22;
        r22 = r0.contains(r1);
        if (r22 == 0) goto L_0x02c9;
    L_0x020f:
        r22 = 1;
    L_0x0211:
        r0 = r25;
        r1 = r23;
        r2 = r22;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 805306496; // 0x30000080 float:4.656684E-10 double:3.97874274E-315;
        r23 = "macro";
        r0 = r18;
        r1 = r23;
        r23 = r0.contains(r1);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 805306624; // 0x30000100 float:4.656755E-10 double:3.978743373E-315;
        r23 = "infinity";
        r0 = r18;
        r1 = r23;
        r23 = r0.contains(r1);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
        r22 = 805306880; // 0x30000200 float:4.656897E-10 double:3.97874464E-315;
        r23 = "fixed";
        r0 = r18;
        r1 = r23;
        r23 = r0.contains(r1);
        r0 = r25;
        r1 = r22;
        r2 = r23;
        r0.setCameraCapsBit(r6, r5, r1, r2);
    L_0x025c:
        r0 = r6.caps;
        r22 = r0;
        r23 = 3;
        r22[r23] = r15;
        r0 = r6.caps;
        r22 = r0;
        r23 = 4;
        r22[r23] = r13;
        r0 = r6.caps;
        r22 = r0;
        r23 = 5;
        r22[r23] = r14;
        r9 = 6;
        if (r15 <= 0) goto L_0x02cd;
    L_0x0277:
        r12 = r21.listIterator();
    L_0x027b:
        r22 = r12.hasNext();
        if (r22 == 0) goto L_0x02cd;
    L_0x0281:
        r16 = r12.next();
        r16 = (android.hardware.Camera.Size) r16;
        r0 = r6.caps;
        r22 = r0;
        r0 = r16;
        r0 = r0.width;
        r23 = r0;
        r22[r9] = r23;
        r0 = r6.caps;
        r22 = r0;
        r23 = r9 + 1;
        r0 = r16;
        r0 = r0.height;
        r24 = r0;
        r22[r23] = r24;
        r9 = r9 + 2;
        goto L_0x027b;
    L_0x02a4:
        r15 = 0;
        goto L_0x0049;
    L_0x02a7:
        r13 = 0;
        goto L_0x004f;
    L_0x02aa:
        r14 = 0;
        goto L_0x0055;
    L_0x02ad:
        r22 = 0;
        goto L_0x008d;
    L_0x02b1:
        r22 = 0;
        goto L_0x008d;
    L_0x02b5:
        r22 = 0;
        goto L_0x00ed;
    L_0x02b9:
        r22 = 0;
        goto L_0x0109;
    L_0x02bd:
        r22 = 0;
        goto L_0x013a;
    L_0x02c1:
        r22 = 0;
        goto L_0x013a;
    L_0x02c5:
        r22 = 0;
        goto L_0x0176;
    L_0x02c9:
        r22 = 0;
        goto L_0x0211;
    L_0x02cd:
        if (r13 <= 0) goto L_0x02ec;
    L_0x02cf:
        r11 = r19.listIterator();
    L_0x02d3:
        r22 = r11.hasNext();
        if (r22 == 0) goto L_0x02ec;
    L_0x02d9:
        r22 = r11.next();
        r22 = (java.lang.Integer) r22;
        r8 = r22.intValue();
        r0 = r6.caps;
        r22 = r0;
        r22[r9] = r8;
        r9 = r9 + 1;
        goto L_0x02d3;
    L_0x02ec:
        if (r14 <= 0) goto L_0x0315;
    L_0x02ee:
        r10 = r20.listIterator();
    L_0x02f2:
        r22 = r10.hasNext();
        if (r22 == 0) goto L_0x0315;
    L_0x02f8:
        r22 = r10.next();
        r22 = (java.lang.Integer) r22;
        r7 = r22.intValue();
        r0 = r6.caps;
        r22 = r0;
        r23 = 1;
        r0 = r25;
        r1 = r23;
        r23 = r0.translateImageFormat(r7, r1);
        r22[r9] = r23;
        r9 = r9 + 1;
        goto L_0x02f2;
    L_0x0315:
        r0 = r6.caps;
        r22 = r0;
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qualcomm.ar.pl.CameraPreview.getCameraCapabilities(int):int[]");
    }

    public boolean setCaptureInfo(int cameraCacheInfoIndex, int[] captureInfo, int[] overrideCaptureInfo) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return false;
        } else if (captureInfo.length != _NUM_CAMERA_CAPTUREINFO_VALUE_) {
            SystemTools.setSystemErrorCode(NUM_CAPTURE_BUFFERS_TO_ADD);
            return false;
        } else {
            Parameters cameraParams = getCameraParameters(cci.camera);
            if (cameraParams == null) {
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            } else if (setCameraCaptureParams(cci, cameraParams, captureInfo, overrideCaptureInfo)) {
                try {
                    cci.camera.setParameters(cameraParams);
                    return true;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                    return false;
                }
            } else {
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            }
        }
    }

    public int[] getCaptureInfo(int cameraCacheInfoIndex) {
        int i = CAMERA_CAPTUREINFO_VALUE_WIDTH;
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return null;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return null;
        }
        try {
            int[] captureInfo = new int[_NUM_CAMERA_CAPTUREINFO_VALUE_];
            captureInfo[CAMERA_CAPTUREINFO_VALUE_WIDTH] = cameraParams.getPreviewSize().width;
            captureInfo[CAMERA_CAPTUREINFO_VALUE_HEIGHT] = cameraParams.getPreviewSize().height;
            captureInfo[NUM_CAPTURE_BUFFERS_TO_ADD] = translateImageFormat(cameraParams.getPreviewFormat(), CONVERT_FORMAT_TO_PL);
            captureInfo[CAMERA_CAPTUREINFO_VALUE_FRAMERATE] = cameraParams.getPreviewFrameRate();
            if (!(cci.surface == null && cci.surfaceTexture == null)) {
                i = CAMERA_CAPTUREINFO_VALUE_HEIGHT;
            }
            captureInfo[CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED] = i;
            return captureInfo;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return null;
        }
    }

    public boolean start(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return false;
        } else if (setupPreviewBuffer(cci)) {
            try {
                cci.camera.startPreview();
                cci.status = AR_CAMERA_STATUS_CAPTURE_RUNNING;
                return true;
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            }
        } else {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return false;
        }
    }

    public boolean stop(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return false;
        }
        try {
            cci.camera.stopPreview();
            cci.status = AR_CAMERA_STATUS_OPENED;
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return false;
        }
    }

    boolean setTypedCameraParameter(int cameraCacheInfoIndex, int type, Object value) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return false;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return false;
        }
        boolean doPostSetAction = false;
        switch (type) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                switch (((Number) value).intValue()) {
                    case AR_CAMERA_TORCHMODE_OFF /*805306369*/:
                        cameraParams.setFlashMode("off");
                        break;
                    case AR_CAMERA_TORCHMODE_ON /*805306370*/:
                        if (!cameraParams.getSupportedFlashModes().contains("torch")) {
                            cameraParams.setFlashMode("on");
                            break;
                        }
                        cameraParams.setFlashMode("torch");
                        break;
                    case AR_CAMERA_TORCHMODE_AUTO /*805306372*/:
                        SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_FRAMERATE);
                        return false;
                    default:
                        SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_FRAMERATE);
                        return false;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                cci.camera.cancelAutoFocus();
                switch (((Number) value).intValue()) {
                    case AR_CAMERA_FOCUSMODE_NORMAL /*805306384*/:
                        if (!cameraParams.getSupportedFocusModes().contains(FOCUS_MODE_NORMAL)) {
                            cameraParams.setFocusMode("auto");
                            doPostSetAction = true;
                            break;
                        }
                        cameraParams.setFocusMode(FOCUS_MODE_NORMAL);
                        break;
                    case AR_CAMERA_FOCUSMODE_AUTO /*805306400*/:
                        cameraParams.setFocusMode("auto");
                        doPostSetAction = true;
                        break;
                    case AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO /*805306432*/:
                        if (SystemTools.checkMinimumApiLevel(14) && cameraParams.getSupportedFocusModes().contains(FOCUS_MODE_CONTINUOUS_PICTURE)) {
                            cameraParams.setFocusMode(FOCUS_MODE_CONTINUOUS_PICTURE);
                            break;
                        } else if (SystemTools.checkMinimumApiLevel(9)) {
                            cameraParams.setFocusMode("continuous-video");
                            break;
                        } else {
                            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                            return false;
                        }
                    case AR_CAMERA_FOCUSMODE_MACRO /*805306496*/:
                        cameraParams.setFocusMode("macro");
                        break;
                    case AR_CAMERA_FOCUSMODE_INFINITY /*805306624*/:
                        cameraParams.setFocusMode("infinity");
                        break;
                    case AR_CAMERA_FOCUSMODE_FIXED /*805306880*/:
                        cameraParams.setFocusMode("fixed");
                        break;
                    default:
                        SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_FRAMERATE);
                        return false;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536870976*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_EXPOSURERANGE /*536871040*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_EXPOSURECOMPENSATIONVALUE /*536871168*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    cameraParams.setExposureCompensation(((Number) value).intValue());
                    break;
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_EXPOSURECOMPENSATIONRANGE /*536871424*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE /*536871936*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE /*536872960*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE /*536875008*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_ZOOMVALUE /*536879104*/:
                if (SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported()) {
                    cameraParams.setZoom(((Number) value).intValue());
                    break;
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536887296*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*536903680*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE /*536936448*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_CONTRASTVALUE /*537001984*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_CONTRASTRANGE /*537133056*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_ROTATION /*537395200*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return false;
            case AR_CAMERA_PARAMTYPE_ISO /*537919488*/:
                try {
                    cameraParams.set("iso", Integer.toString(((Number) value).intValue()));
                    break;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                    return false;
                }
            case AR_CAMERA_PARAMTYPE_RECORDING_HINT /*538968064*/:
                String str;
                String str2 = "recording-hint";
                if (((Number) value).intValue() != 0) {
                    str = "true";
                } else {
                    str = "false";
                }
                cameraParams.set(str2, str);
                break;
            default:
                return false;
        }
        try {
            cci.camera.setParameters(cameraParams);
            if (doPostSetAction) {
                switch (type) {
                    case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                        try {
                            cci.isAutoFocusing = true;
                            cci.camera.autoFocus(new C00561());
                            break;
                        } catch (Exception e2) {
                            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                            return false;
                        }
                }
            }
            return true;
        } catch (Exception e3) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return false;
        }
    }

    Object getTypedCameraParameter(int cameraCacheInfoIndex, int type) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci == null || cci.camera == null) {
            SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
            return null;
        }
        Parameters cameraParams = getCameraParameters(cci.camera);
        if (cameraParams == null) {
            SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
            return null;
        }
        switch (type) {
            case AR_CAMERA_PARAMTYPE_TORCHMODE /*536870913*/:
                try {
                    String flashMode = cameraParams.getFlashMode();
                    if (flashMode.equals("torch") || flashMode.equals("on")) {
                        return Integer.valueOf(AR_CAMERA_TORCHMODE_ON);
                    }
                    if (flashMode.equals("off")) {
                        return Integer.valueOf(AR_CAMERA_TORCHMODE_OFF);
                    }
                    SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                    return null;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                    return null;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSMODE /*536870914*/:
                String focusMode = cameraParams.getFocusMode();
                if (focusMode.equals("auto")) {
                    return Integer.valueOf(cci.isAutoFocusing ? AR_CAMERA_FOCUSMODE_AUTO : AR_CAMERA_FOCUSMODE_NORMAL);
                } else if ((SystemTools.checkMinimumApiLevel(9) && focusMode.equals("continuous-video")) || (SystemTools.checkMinimumApiLevel(14) && focusMode.equals(FOCUS_MODE_CONTINUOUS_PICTURE))) {
                    return Integer.valueOf(AR_CAMERA_FOCUSMODE_CONTINUOUSAUTO);
                } else {
                    if (focusMode.equals("infinity")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_INFINITY);
                    }
                    if (focusMode.equals("macro")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_MACRO);
                    }
                    if (focusMode.equals("fixed")) {
                        return Integer.valueOf(AR_CAMERA_FOCUSMODE_FIXED);
                    }
                    SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                    return null;
                }
            case AR_CAMERA_PARAMTYPE_FOCUSVALUE /*536870916*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return Float.valueOf(cameraParams.getFocalLength());
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSRANGE /*536870920*/:
                if (SystemTools.checkMinimumApiLevel(9)) {
                    float[] focusDistances = new float[CAMERA_CAPTUREINFO_VALUE_FRAMERATE];
                    cameraParams.getFocusDistances(focusDistances);
                    Object focusValueRange = new float[NUM_CAPTURE_BUFFERS_TO_ADD];
                    focusValueRange[CAMERA_CAPTUREINFO_VALUE_WIDTH] = focusDistances[CAMERA_CAPTUREINFO_VALUE_WIDTH];
                    focusValueRange[CAMERA_CAPTUREINFO_VALUE_HEIGHT] = focusDistances[NUM_CAPTURE_BUFFERS_TO_ADD];
                    return focusValueRange;
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_FOCUSREGION /*536870928*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREMODE /*536870944*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSUREVALUE /*536870976*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSURERANGE /*536871040*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSURECOMPENSATIONVALUE /*536871168*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    return Integer.valueOf(cameraParams.getExposureCompensation());
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_EXPOSURECOMPENSATIONRANGE /*536871424*/:
                if (SystemTools.checkMinimumApiLevel(8)) {
                    Object exposureCompRange = new int[NUM_CAPTURE_BUFFERS_TO_ADD];
                    exposureCompRange[CAMERA_CAPTUREINFO_VALUE_WIDTH] = cameraParams.getMinExposureCompensation();
                    exposureCompRange[CAMERA_CAPTUREINFO_VALUE_HEIGHT] = cameraParams.getMaxExposureCompensation();
                    return exposureCompRange;
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEMODE /*536871936*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCEVALUE /*536872960*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_WHITEBALANCERANGE /*536875008*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMVALUE /*536879104*/:
                if (SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported()) {
                    return Integer.valueOf(cameraParams.getZoom());
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_ZOOMRANGE /*536887296*/:
                if (SystemTools.checkMinimumApiLevel(8) && cameraParams.isZoomSupported()) {
                    Object zoomRange = new int[NUM_CAPTURE_BUFFERS_TO_ADD];
                    zoomRange[CAMERA_CAPTUREINFO_VALUE_WIDTH] = CAMERA_CAPTUREINFO_VALUE_WIDTH;
                    zoomRange[CAMERA_CAPTUREINFO_VALUE_HEIGHT] = cameraParams.getMaxZoom();
                    return zoomRange;
                }
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSVALUE /*536903680*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_BRIGHTNESSRANGE /*536936448*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_CONTRASTVALUE /*537001984*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_CONTRASTRANGE /*537133056*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            case AR_CAMERA_PARAMTYPE_ROTATION /*537395200*/:
                SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
                return null;
            default:
                return null;
        }
        SystemTools.setSystemErrorCode(_NUM_CAMERA_CAPSINFO_VALUE_);
        return null;
    }

    int getStatus(int cameraCacheInfoIndex) {
        CameraCacheInfo cci = getCameraCacheInfo(cameraCacheInfoIndex);
        if (cci != null) {
            return cci.status;
        }
        SystemTools.setSystemErrorCode(CAMERA_CAPTUREINFO_VALUE_PREVIEWSURFACEENABLED);
        return AR_CAMERA_STATUS_UNKNOWN;
    }
}
