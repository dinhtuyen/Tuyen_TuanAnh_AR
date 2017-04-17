package com.daqri.daqritools;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.unity3d.player.UnityPlayer;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Locale;
import org.apache.http.conn.util.InetAddressUtils;

public class DaqriTools {
    public static String getNetworkType() {
        String networkType = "Unknown";
        NetworkInfo nInfo = ((ConnectivityManager) UnityPlayer.currentActivity.getSystemService("connectivity")).getActiveNetworkInfo();
        if (nInfo == null || !nInfo.isConnectedOrConnecting()) {
            return networkType;
        }
        if (nInfo.getType() == 1) {
            return "Wi-Fi";
        }
        if (nInfo.getType() == 0) {
            return nInfo.getSubtypeName();
        }
        return networkType;
    }

    public static String getIPAddress(boolean useIpV4) {
        String address = "";
        try {
            boolean found = false;
            for (NetworkInterface nIter : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress iAdd : Collections.list(nIter.getInetAddresses())) {
                    address = iAdd.getHostAddress().toUpperCase();
                    boolean isIPV4 = InetAddressUtils.isIPv4Address(address);
                    if (useIpV4) {
                        if (isIPV4) {
                            found = true;
                            break;
                            continue;
                        }
                    } else if (!isIPV4) {
                        int delim = address.indexOf(37);
                        if (delim >= 0 && delim >= 0) {
                            address = address.substring(0, delim);
                        }
                        found = true;
                        continue;
                    }
                }
                if (found) {
                    return address;
                }
            }
            return address;
        } catch (Exception ex) {
            address = "";
            ex.printStackTrace();
            return address;
        }
    }

    public static String getCarrierName() {
        String operatorName = "";
        try {
            operatorName = ((TelephonyManager) UnityPlayer.currentActivity.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return operatorName;
    }

    public static String getCountryAndNetworkCode() {
        String countryAndNetworkCode = "";
        try {
            countryAndNetworkCode = ((TelephonyManager) UnityPlayer.currentActivity.getSystemService("phone")).getSimOperator();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryAndNetworkCode;
    }

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
