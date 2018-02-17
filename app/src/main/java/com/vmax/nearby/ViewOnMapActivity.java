package com.vmax.nearby;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ViewOnMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {


    private GoogleMap mMap;
    static Double latit=17.442809, longi=78.463639;
    String my_location="current location",status,desc;
    //String getLat="17.442809",getLng="78.463639", cat_type_id="2";
   // String getLat="17.442809",getLng="78.463639",dest_lat,dest_lng,location;
    String getLat,getLng,dest_lat,dest_lng,location;
    ArrayList<String> idArrayList = new ArrayList<String>();
    ArrayList<String> latArrayList = new ArrayList<String>();
    ArrayList<String> lngArrayList = new ArrayList<String>();
    ArrayList<String> locationArrayList = new ArrayList<String>();
    ArrayList<String> distanceArrayList = new ArrayList<String>();
    Marker marker;
    LatLng latLng;

    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    public String getlatitude,getlongitude;
    String currentLanguage;
    Configuration config;
    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_on_map);



        initMap();

    }


    private void initMap() {

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        Geocoder geocoder;
        List<Address> addresses_dest, address_current;
        geocoder = new Geocoder(this, Locale.getDefault());
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

          //  location = geocoder.getFromLocation(Double.valueOf(dest_lat), Double.valueOf(dest_lng), 1);
            addresses_dest = new ArrayList<>();
            if ("".equals(getLat) || "".equals(getLng)) {
                getLat = "0.0";
                getLng = "0.0";
            }

            try {
                my_location = getAddress(getLat, getLng);

                LatLng currentLatLng = new LatLng(Double.valueOf(getLat), Double.valueOf(getLng));
                LatLng destLatLng = new LatLng(Double.valueOf(dest_lat), Double.valueOf(dest_lng));

                marker = mMap.addMarker(new MarkerOptions()
                        .position(currentLatLng)
                        .title(my_location)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 8));
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                mMap.animateCamera(CameraUpdateFactory.zoomTo(8), 2000, null);


                marker.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));

                marker = mMap.addMarker(new MarkerOptions()
                        .position(destLatLng)
                        .title(getAddress(dest_lat, dest_lng))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                marker.showInfoWindow();
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(destLatLng)      // Sets the center of the map to location user
                        .zoom(8)                   // Sets the zoom
                        .bearing(50)                // Sets the orientation of the camera to east
                        .tilt(8)                   // Sets the tilt of the camera to 30 degrees
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMap.isBuildingsEnabled();
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
            catch (Exception e){
                e.printStackTrace();
            }
//


    }

    private String getAddress(String lat, String lng) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lng), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void drawMarker(LatLng latLng, String location, int index) {

        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(latLng)
                     .title(location)
                     .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        // Adding marker on the Google Map
        mMap.addMarker(markerOptions);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }


}