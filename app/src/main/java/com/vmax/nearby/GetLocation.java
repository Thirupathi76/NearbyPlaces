package com.vmax.nearby;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class GetLocation implements LocationListener {
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_TO_UPDATE = 0;
    boolean canGetLocation = false;
    private Context context;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    public double latitude;
    private Location location;
    protected LocationManager locationManager;
    public double longitude;
//    Utility utility;

    public GetLocation(Context context) {
        this.context = context;
//        this.utility = new Utility(context);
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.isGPSEnabled = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        this.isNetworkEnabled = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (this.isGPSEnabled || this.isNetworkEnabled) {
            this.canGetLocation = true;
            if (this.isGPSEnabled) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
                this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10.0f, this);
                this.location = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (this.location != null) {
                    this.latitude = this.location.getLatitude();
                    this.longitude = this.location.getLongitude();
                }
            }
            if (this.isNetworkEnabled) {
                this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10.0f, this);
                this.location = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (this.location != null) {
                    this.latitude = this.location.getLatitude();
                    this.longitude = this.location.getLongitude();
                    return;
                }
                return;
            }
            return;
        }
//        this.utility.showSettingsDialog();
    }

    public void stopGPSUpdate() {
        if (this.locationManager != null) {
            this.locationManager.removeUpdates(this);
        }
    }

    public void onLocationChanged(Location location) {
        this.location = location;
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
