package com.qualcomm.ar.pl;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Vector;

public class SensorController extends HandlerThread implements SensorEventListener {
    private static final int AR_SENSOR_CONFIDENCE_HIGH = 4;
    private static final int AR_SENSOR_CONFIDENCE_LOW = 2;
    private static final int AR_SENSOR_CONFIDENCE_MEDIUM = 3;
    private static final int AR_SENSOR_CONFIDENCE_UNKNOWN = 0;
    private static final int AR_SENSOR_CONFIDENCE_UNRELIABLE = 1;
    private static int AR_SENSOR_INDEX_DONTCARE = 0;
    private static final int AR_SENSOR_PARAMTYPE_ACCURACY = 1879048200;
    private static final int AR_SENSOR_PARAMTYPE_BASE = 1879048192;
    private static final int AR_SENSOR_PARAMTYPE_DATARANGE_MAX = 1879048194;
    private static final int AR_SENSOR_PARAMTYPE_DATARANGE_MIN = 1879048193;
    private static final int AR_SENSOR_PARAMTYPE_RESOLUTION = 1879048196;
    private static final int AR_SENSOR_PARAMTYPE_SENSITIVITY = 1879048208;
    private static final int AR_SENSOR_PARAMTYPE_UPDATEINTERVAL = 1879048224;
    private static final int AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_ABSTRACT = 1879048256;
    private static final int AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_MIN = 1879048320;
    private static final int AR_SENSOR_PARAMTYPE_UPDATERATE_MIN = 1879048448;
    private static final int AR_SENSOR_STATUS_IDLE = 1342242818;
    private static final int AR_SENSOR_STATUS_RUNNING = 1342242819;
    private static final int AR_SENSOR_STATUS_UNINITIALIZED = 1342242817;
    private static final int AR_SENSOR_STATUS_UNKNOWN = 1342242816;
    private static final int AR_SENSOR_TYPE_ACCELEROMETER = 1342177282;
    private static final int AR_SENSOR_TYPE_AMBIENT_LIGHT = 1342177286;
    private static final int AR_SENSOR_TYPE_GYROSCOPE = 1342177281;
    private static final int AR_SENSOR_TYPE_MAGNETOMETER = 1342177283;
    private static final int AR_SENSOR_TYPE_PROXIMITY = 1342177285;
    private static final int AR_SENSOR_TYPE_UNKNOWN = 1342177280;
    private static final int AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE = 4;
    private static final int AR_SENSOR_UPDATEINTERVAL_HIGHRATE = 3;
    private static final int AR_SENSOR_UPDATEINTERVAL_LOWRATE = 1;
    private static final int AR_SENSOR_UPDATEINTERVAL_MEDIUMRATE = 2;
    private static final int AR_SENSOR_UPDATEINTERVAL_UNKNOWN = 0;
    private static boolean CONVERT_FORMAT_TO_ANDROID = false;
    private static boolean CONVERT_FORMAT_TO_PL = false;
    private static final String MODULENAME = "SensorController";
    private static final int SENSORINFO_VALUE_ANDROIDSENSORTYPE = 1;
    private static final int SENSORINFO_VALUE_ISDEFAULT = 2;
    private static final int SENSORINFO_VALUE_PLSENSORTYPE = 0;
    private static final int[] SENSOR_TYPE_CONVERSIONTABLE;
    private static final int _NUM_SENSORINFO_VALUE_ = 3;
    private Vector<SensorCacheInfo> sensorCacheInfo;
    private Handler sensorEventHandler;
    private HashMap<Sensor, Integer> sensorIndexMap;
    private SensorManager sensorManager;

    public class SensorCacheInfo {
        int cacheIndex;
        boolean isDefaultSensor;
        int plSensorType;
        int requestedAbstractUpdateRate;
        Sensor sensor;
        float[] valuesForForcedSensorEvent;
    }

    private native void newDataAvailable(int i, long j, int i2, float[] fArr);

    static {
        SENSOR_TYPE_CONVERSIONTABLE = new int[]{AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE, AR_SENSOR_TYPE_GYROSCOPE, SENSORINFO_VALUE_ANDROIDSENSORTYPE, AR_SENSOR_TYPE_ACCELEROMETER, SENSORINFO_VALUE_ISDEFAULT, AR_SENSOR_TYPE_MAGNETOMETER, 8, AR_SENSOR_TYPE_PROXIMITY, 5, AR_SENSOR_TYPE_AMBIENT_LIGHT};
        CONVERT_FORMAT_TO_PL = true;
        CONVERT_FORMAT_TO_ANDROID = false;
        AR_SENSOR_INDEX_DONTCARE = -1;
    }

    public SensorController() {
        super(MODULENAME);
        this.sensorCacheInfo = null;
        this.sensorIndexMap = null;
    }

    private SensorCacheInfo getSensorCacheInfo(int sensorCacheInfoIndex) {
        if (sensorCacheInfoIndex < 0 || sensorCacheInfoIndex >= this.sensorCacheInfo.size()) {
            return null;
        }
        return (SensorCacheInfo) this.sensorCacheInfo.get(sensorCacheInfoIndex);
    }

    private int translateSensorType(int fromValue, boolean conversionMode) {
        int i = SENSORINFO_VALUE_PLSENSORTYPE;
        while (i < SENSOR_TYPE_CONVERSIONTABLE.length / SENSORINFO_VALUE_ISDEFAULT) {
            if (fromValue != (conversionMode == CONVERT_FORMAT_TO_PL ? SENSOR_TYPE_CONVERSIONTABLE[i * SENSORINFO_VALUE_ISDEFAULT] : SENSOR_TYPE_CONVERSIONTABLE[(i * SENSORINFO_VALUE_ISDEFAULT) + SENSORINFO_VALUE_ANDROIDSENSORTYPE])) {
                i += SENSORINFO_VALUE_ANDROIDSENSORTYPE;
            } else if (conversionMode == CONVERT_FORMAT_TO_PL) {
                return SENSOR_TYPE_CONVERSIONTABLE[(i * SENSORINFO_VALUE_ISDEFAULT) + SENSORINFO_VALUE_ANDROIDSENSORTYPE];
            } else {
                return SENSOR_TYPE_CONVERSIONTABLE[i * SENSORINFO_VALUE_ISDEFAULT];
            }
        }
        return conversionMode == CONVERT_FORMAT_TO_PL ? AR_SENSOR_TYPE_UNKNOWN : SENSORINFO_VALUE_PLSENSORTYPE;
    }

    private int translateSensorUpdateIntervalToAndroid(int updateIntervalValue_PL) {
        switch (updateIntervalValue_PL) {
            case SENSORINFO_VALUE_ANDROIDSENSORTYPE /*1*/:
                return _NUM_SENSORINFO_VALUE_;
            case SENSORINFO_VALUE_ISDEFAULT /*2*/:
                return SENSORINFO_VALUE_ISDEFAULT;
            case _NUM_SENSORINFO_VALUE_ /*3*/:
                return SENSORINFO_VALUE_ANDROIDSENSORTYPE;
            case AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE /*4*/:
                return SENSORINFO_VALUE_PLSENSORTYPE;
            default:
                return -1;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        Object intObj = this.sensorIndexMap.get(event.sensor);
        if (intObj != null) {
            SensorCacheInfo sci = getSensorCacheInfo(((Integer) intObj).intValue());
            if (sci != null) {
                int plConfidenceValue = SENSORINFO_VALUE_PLSENSORTYPE;
                switch (event.accuracy) {
                    case SENSORINFO_VALUE_PLSENSORTYPE /*0*/:
                        plConfidenceValue = SENSORINFO_VALUE_ANDROIDSENSORTYPE;
                        break;
                    case SENSORINFO_VALUE_ANDROIDSENSORTYPE /*1*/:
                        plConfidenceValue = SENSORINFO_VALUE_ISDEFAULT;
                        break;
                    case SENSORINFO_VALUE_ISDEFAULT /*2*/:
                        plConfidenceValue = _NUM_SENSORINFO_VALUE_;
                        break;
                    case _NUM_SENSORINFO_VALUE_ /*3*/:
                        plConfidenceValue = AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE;
                        break;
                }
                newDataAvailable(sci.cacheIndex, event.timestamp, plConfidenceValue, event.values);
            }
        }
    }

    public boolean init() {
        this.sensorManager = null;
        this.sensorCacheInfo = new Vector();
        this.sensorIndexMap = new HashMap();
        return true;
    }

    public int getAllSupportedSensors() {
        this.sensorManager = (SensorManager) SystemTools.getActivityFromNative().getSystemService("sensor");
        if (this.sensorManager == null) {
            SystemTools.setSystemErrorCode(6);
            SystemTools.logSystemError("Failed to retrieve Context's Sensor Service");
            return -1;
        } else if (this.sensorCacheInfo.size() > 0) {
            return this.sensorCacheInfo.size();
        } else {
            for (Sensor sensor : this.sensorManager.getSensorList(-1)) {
                int sensorType = sensor.getType();
                boolean isDefaultSensor = sensor.equals(this.sensorManager.getDefaultSensor(sensorType));
                int plSensorType = translateSensorType(sensorType, CONVERT_FORMAT_TO_PL);
                if (plSensorType != AR_SENSOR_TYPE_UNKNOWN) {
                    SensorCacheInfo sci = new SensorCacheInfo();
                    sci.sensor = sensor;
                    sci.plSensorType = plSensorType;
                    sci.isDefaultSensor = isDefaultSensor;
                    sci.cacheIndex = this.sensorCacheInfo.size();
                    sci.requestedAbstractUpdateRate = SENSORINFO_VALUE_PLSENSORTYPE;
                    this.sensorCacheInfo.add(sci);
                    this.sensorIndexMap.put(sci.sensor, Integer.valueOf(sci.cacheIndex));
                }
            }
            return this.sensorCacheInfo.size();
        }
    }

    boolean open(int sensorType, int index) {
        SensorCacheInfo sci = null;
        if (index == AR_SENSOR_INDEX_DONTCARE) {
            for (int i = SENSORINFO_VALUE_PLSENSORTYPE; i < this.sensorCacheInfo.size(); i += SENSORINFO_VALUE_ANDROIDSENSORTYPE) {
                SensorCacheInfo tmpSCI = (SensorCacheInfo) this.sensorCacheInfo.get(i);
                if (tmpSCI.plSensorType == sensorType && tmpSCI.isDefaultSensor) {
                    sci = tmpSCI;
                    break;
                }
            }
        } else {
            sci = (SensorCacheInfo) this.sensorCacheInfo.get(index);
        }
        if (sci == null) {
            SystemTools.setSystemErrorCode(SENSORINFO_VALUE_ISDEFAULT);
            SystemTools.logSystemError("No sensor matching the requested sensor device info has been found");
            return false;
        }
        if (this.sensorEventHandler == null) {
            try {
                start();
                this.sensorEventHandler = new Handler(getLooper());
            } catch (Exception e) {
                SystemTools.setSystemErrorCode(6);
                SystemTools.logSystemError("Failed to " + (isAlive() ? "retrieve a handler for the sensor event handler thread" : "start Java handler thread for sensor events") + ": " + e.toString());
                return false;
            }
        }
        return true;
    }

    boolean close(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        boolean result = false;
        try {
            this.sensorManager.unregisterListener(this, sci.sensor);
            result = true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            SystemTools.logSystemError("Failed to unregister sensor event listerer");
        }
        System.gc();
        return result;
    }

    int[] getSensorInfoValues(int sensorCacheInfoIndex) {
        int i = SENSORINFO_VALUE_ANDROIDSENSORTYPE;
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            return null;
        }
        int[] sensorInfoValues = new int[_NUM_SENSORINFO_VALUE_];
        sensorInfoValues[SENSORINFO_VALUE_PLSENSORTYPE] = sci.plSensorType;
        sensorInfoValues[SENSORINFO_VALUE_ANDROIDSENSORTYPE] = sci.sensor.getType();
        if (!sci.isDefaultSensor) {
            i = SENSORINFO_VALUE_PLSENSORTYPE;
        }
        sensorInfoValues[SENSORINFO_VALUE_ISDEFAULT] = i;
        return sensorInfoValues;
    }

    String getSensorName(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            return null;
        }
        return sci.sensor.getName();
    }

    Object getTypedSensorParameter(int sensorCacheInfoIndex, int type) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE);
            SystemTools.logSystemError("Sensor handle is invalid");
            return null;
        }
        switch (type) {
            case AR_SENSOR_PARAMTYPE_DATARANGE_MIN /*1879048193*/:
            case AR_SENSOR_PARAMTYPE_ACCURACY /*1879048200*/:
            case AR_SENSOR_PARAMTYPE_SENSITIVITY /*1879048208*/:
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL /*1879048224*/:
                SystemTools.setSystemErrorCode(_NUM_SENSORINFO_VALUE_);
                SystemTools.logSystemError("Querying sensor parameter " + type + " is not supported for sensor type " + sci.plSensorType + (type == AR_SENSOR_PARAMTYPE_UPDATEINTERVAL ? " when using the Java-based sensor API" : ""));
                return null;
            case AR_SENSOR_PARAMTYPE_DATARANGE_MAX /*1879048194*/:
                return Float.valueOf(sci.sensor.getMaximumRange());
            case AR_SENSOR_PARAMTYPE_RESOLUTION /*1879048196*/:
                return Float.valueOf(sci.sensor.getResolution());
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_ABSTRACT /*1879048256*/:
                return Integer.valueOf(sci.requestedAbstractUpdateRate);
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_MIN /*1879048320*/:
                if (SystemTools.checkMinimumApiLevel(9)) {
                    return Integer.valueOf(sci.sensor.getMinDelay());
                }
                SystemTools.setSystemErrorCode(_NUM_SENSORINFO_VALUE_);
                SystemTools.logSystemError("Unknown sensor parameter");
                return null;
            default:
                try {
                    SystemTools.setSystemErrorCode(_NUM_SENSORINFO_VALUE_);
                    SystemTools.logSystemError("Unknown sensor parameter");
                    return null;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
                    return null;
                }
        }
        SystemTools.setSystemErrorCode(6);
        SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
        return null;
    }

    boolean setTypedSensorParameter(int sensorCacheInfoIndex, int type, Object value) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        switch (type) {
            case AR_SENSOR_PARAMTYPE_DATARANGE_MIN /*1879048193*/:
            case AR_SENSOR_PARAMTYPE_DATARANGE_MAX /*1879048194*/:
            case AR_SENSOR_PARAMTYPE_RESOLUTION /*1879048196*/:
            case AR_SENSOR_PARAMTYPE_ACCURACY /*1879048200*/:
            case AR_SENSOR_PARAMTYPE_SENSITIVITY /*1879048208*/:
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL /*1879048224*/:
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_MIN /*1879048320*/:
                SystemTools.setSystemErrorCode(_NUM_SENSORINFO_VALUE_);
                SystemTools.logSystemError("Sensor parameter " + type + " cannot be set for sensor type " + sci.plSensorType + (type == AR_SENSOR_PARAMTYPE_UPDATEINTERVAL ? " when using the Java-based sensor API" : ""));
                return false;
            case AR_SENSOR_PARAMTYPE_UPDATEINTERVAL_ABSTRACT /*1879048256*/:
                int updateIntervalValue = ((Number) value).intValue();
                if (updateIntervalValue < SENSORINFO_VALUE_ANDROIDSENSORTYPE || updateIntervalValue > AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE) {
                    SystemTools.setSystemErrorCode(SENSORINFO_VALUE_ISDEFAULT);
                    SystemTools.logSystemError("Invalid abstract sensor update interval (" + updateIntervalValue + ")");
                    return false;
                }
                sci.requestedAbstractUpdateRate = updateIntervalValue;
                return true;
            default:
                try {
                    SystemTools.setSystemErrorCode(_NUM_SENSORINFO_VALUE_);
                    SystemTools.logSystemError("Unknown sensor parameter");
                    return false;
                } catch (Exception e) {
                    SystemTools.setSystemErrorCode(6);
                    SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
                    return false;
                }
        }
        SystemTools.setSystemErrorCode(6);
        SystemTools.logSystemError("Failed to get sensor parameter: " + e.toString());
        return false;
    }

    boolean start(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        int requestedUpdateRateAndroid;
        int updateRateAndroid = translateSensorUpdateIntervalToAndroid(sci.requestedAbstractUpdateRate);
        if (updateRateAndroid < 0) {
            requestedUpdateRateAndroid = SENSORINFO_VALUE_ANDROIDSENSORTYPE;
        } else {
            requestedUpdateRateAndroid = updateRateAndroid;
        }
        boolean result = false;
        try {
            result = this.sensorManager.registerListener(this, sci.sensor, requestedUpdateRateAndroid, this.sensorEventHandler);
        } catch (Exception e) {
        }
        if (result) {
            return result;
        }
        SystemTools.setSystemErrorCode(6);
        SystemTools.logSystemError("Failed to start sensor, could not register sensor event listerer");
        return result;
    }

    boolean stop(int sensorCacheInfoIndex) {
        SensorCacheInfo sci = getSensorCacheInfo(sensorCacheInfoIndex);
        if (sci == null) {
            SystemTools.setSystemErrorCode(AR_SENSOR_UPDATEINTERVAL_HIGHESTRATE);
            SystemTools.logSystemError("Sensor handle is invalid");
            return false;
        }
        try {
            this.sensorManager.unregisterListener(this, sci.sensor);
            return true;
        } catch (Exception e) {
            SystemTools.setSystemErrorCode(6);
            SystemTools.logSystemError("Failed to stop sensor, could not unregister sensor event listerer");
            return false;
        }
    }
}
