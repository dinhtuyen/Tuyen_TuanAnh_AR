package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.qualcomm.ar.pl.SystemTools;
import com.qualcomm.vuforia.TrackableResult.STATUS;
import com.qualcomm.vuforia.WordList.STORAGE_TYPE;

class ConnectivityManagerCompatGingerbread {
    ConnectivityManagerCompatGingerbread() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return true;
        }
        switch (info.getType()) {
            case STORAGE_TYPE.STORAGE_APP /*0*/:
            case STORAGE_TYPE.STORAGE_ABSOLUTE /*2*/:
            case STATUS.TRACKED /*3*/:
            case STATUS.EXTENDED_TRACKED /*4*/:
            case SystemTools.AR_ERROR_INVALID_OPERATION /*5*/:
            case SystemTools.AR_ERROR_OPERATION_FAILED /*6*/:
                return true;
            case STORAGE_TYPE.STORAGE_APPRESOURCE /*1*/:
                return false;
            default:
                return true;
        }
    }
}
