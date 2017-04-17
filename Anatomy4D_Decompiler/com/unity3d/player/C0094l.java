package com.unity3d.player;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import java.util.List;

/* renamed from: com.unity3d.player.l */
final class C0094l implements LocationListener {
    private final Context f147a;
    private final UnityPlayer f148b;
    private Location f149c;
    private float f150d;
    private boolean f151e;
    private int f152f;
    private boolean f153g;
    private int f154h;

    protected C0094l(Context context, UnityPlayer unityPlayer) {
        this.f150d = 0.0f;
        this.f151e = false;
        this.f152f = 0;
        this.f153g = false;
        this.f154h = 0;
        this.f147a = context;
        this.f148b = unityPlayer;
    }

    private void m116a(int i) {
        this.f154h = i;
        this.f148b.nativeSetLocationStatus(i);
    }

    private void m117a(Location location) {
        if (location != null && C0094l.m118a(location, this.f149c)) {
            this.f149c = location;
            GeomagneticField geomagneticField = new GeomagneticField((float) this.f149c.getLatitude(), (float) this.f149c.getLongitude(), (float) this.f149c.getAltitude(), this.f149c.getTime());
            this.f148b.nativeSetLocation((float) location.getLatitude(), (float) location.getLongitude(), (float) location.getAltitude(), location.getAccuracy(), ((double) location.getTime()) / 1000.0d, geomagneticField.getDeclination());
        }
    }

    private static boolean m118a(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        return !(accuracy < 0) ? (!z3 || (accuracy > 0)) ? z3 && ((accuracy > 200 ? 1 : 0) | (location.getAccuracy() == 0.0f ? 1 : 0)) == 0 && C0094l.m119a(location.getProvider(), location2.getProvider()) : true : true;
    }

    private static boolean m119a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    public final void m120a(float f) {
        this.f150d = f;
    }

    public final boolean m121a() {
        return !((LocationManager) this.f147a.getSystemService("location")).getProviders(new Criteria(), true).isEmpty();
    }

    public final void m122b() {
        this.f153g = false;
        if (this.f151e) {
            C0089h.Log(5, "Location_StartUpdatingLocation already started!");
        } else if (m121a()) {
            LocationManager locationManager = (LocationManager) this.f147a.getSystemService("location");
            m116a(1);
            List<String> providers = locationManager.getProviders(true);
            if (providers.isEmpty()) {
                m116a(3);
                return;
            }
            LocationProvider locationProvider;
            if (this.f152f == 2) {
                for (String provider : providers) {
                    LocationProvider provider2 = locationManager.getProvider(provider);
                    if (provider2.getAccuracy() == 2) {
                        locationProvider = provider2;
                        break;
                    }
                }
            }
            locationProvider = null;
            for (String provider3 : providers) {
                if (locationProvider == null || locationManager.getProvider(provider3).getAccuracy() != 1) {
                    m117a(locationManager.getLastKnownLocation(provider3));
                    locationManager.requestLocationUpdates(provider3, 0, this.f150d, this, this.f147a.getMainLooper());
                    this.f151e = true;
                }
            }
        } else {
            m116a(3);
        }
    }

    public final void m123b(float f) {
        if (f < 100.0f) {
            this.f152f = 1;
        } else if (f < 500.0f) {
            this.f152f = 1;
        } else {
            this.f152f = 2;
        }
    }

    public final void m124c() {
        ((LocationManager) this.f147a.getSystemService("location")).removeUpdates(this);
        this.f151e = false;
        this.f149c = null;
        m116a(0);
    }

    public final void m125d() {
        if (this.f154h == 1 || this.f154h == 2) {
            this.f153g = true;
            m124c();
        }
    }

    public final void m126e() {
        if (this.f153g) {
            m122b();
        }
    }

    public final void onLocationChanged(Location location) {
        m116a(2);
        m117a(location);
    }

    public final void onProviderDisabled(String str) {
        this.f149c = null;
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
