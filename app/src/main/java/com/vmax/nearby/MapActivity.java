package com.vmax.nearby;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

public class MapActivity extends AppCompatActivity implements FetchFromServerUser {
    private static final String KEY = "AIzaSyDKv97jTLfbhTRIFjpOtojS4cKWEHG95nY";
    public static String KEY_POSITION = "key_pos";
    private Button CLOSE;
    private Button DETAILS;
    private ImageView FAVOURITE;
    private ImageView iconPlace;
    TextView address;
    String address_;
    Context context = this;
    Cursor crsr;
    RelativeLayout detailsLinearLay;
    TextView distance1;
    String distance_;
//    Fragment errorFragment;
    Fragment fragAbout;
    Fragment fragGallery;
    Fragment fragReview;
    private MapFragment fragment;
    private GoogleMap googleMap;
    int posPlace;
    boolean isBootonLinear = false;
    boolean isFavourite = false;
    TextView isOpen;
    String isOpen_;
    String kind;
//    RecyclerView listOfPlaces;
    List<PlaceBean> listing;
    GetLocation loc;
    Marker f1599m;
//    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    String mapiconname;
    private double markerLat;
    private double markerLong;
    private MarkerOptions markerPlace;
    TextView name;
    String name_;
    private TypedArray pinIcons;
    private HashMap<String, Float> placerating = new HashMap();
    String placerefernce_, placeIcon_;
    private HashMap<String, String> placesAddress = new HashMap();
    private HashMap<String, String> placesDistance = new HashMap();
    private HashMap<String, String> placesTime = new HashMap();
    private HashMap<String, String> placesname = new HashMap();
    private HashMap<String, String> plavereference = new HashMap();
    private HashMap<String, String> placeIconMap = new HashMap();
    private int position = 0;
    ProgressDialog progressDialog;
    TextView rating;
    Float rating12;
    String refeceren_place;
    TextView time;
    String time_;
    String url;
    private double userLat;
    private double userLong;

    class C11801 implements OnMapReadyCallback {
        C11801() {
        }

        public void onMapReady(GoogleMap Maps) {
            googleMap = Maps;
            googleMap.setMapType(1);
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setCompassEnabled(true);
                googleMap.getUiSettings().setRotateGesturesEnabled(true);
                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                googleMap.isTrafficEnabled();
                googleMap.setTrafficEnabled(false);
            }
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getMarkerLat() {
        return markerLat;
    }

    public void setMarkerLat(double markerLat) {
        this.markerLat = markerLat;
    }

    public double getMarkerLong() {
        return markerLong;
    }

    public void setMarkerLong(double markerLong) {
        this.markerLong = markerLong;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initilizeMap();
        detailsLinearLay = (RelativeLayout) findViewById(R.id.detailsLinear);
        loc = new GetLocation(this);
        aboutinit();
        kind = getIntent().getStringExtra("Place_id");
//        ((TextView) findViewById(R.id.namePlaceHolder)).setText(kind.replace("_", " "));
        setPosition(getIntent().getIntExtra("posi", 1));
        url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + loc.latitude + "," + loc.longitude + "&rankby=distance&types=" + kind + "&key=" + "AIzaSyDKv97jTLfbhTRIFjpOtojS4cKWEHG95nY";
        Log.e("PlaceResult", url);
        new FetchFromServerTask(this, 0, String.valueOf(loc.latitude), String.valueOf(loc.longitude), kind).execute();

    }

    private void aboutinit() {
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        CLOSE = (Button) findViewById(R.id.close);
        DETAILS = (Button) findViewById(R.id.detials);
        iconPlace = findViewById(R.id.iconPlace);
    }

    private void initilizeMap() {
        if (googleMap == null) {
            fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapPlaces);
            fragment.getMapAsync(new C11801());
            return;
        }
        Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_LONG).show();
    }

    public void onPreFetch() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Results");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(0);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void onFetchCompletion(String string, int id) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        /*if (errorFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(errorFragment).commit();
        }
        if (string == null || string.equals("")) {

            Bundle msg = new Bundle();
            msg.putString("msg", "No or poor internet connection.");
            errorFragment.setArguments(msg);
            getSupportFragmentManager().beginTransaction().replace(R.id.message, errorFragment).commit();
            return;
        }*/

        try {
            List<PlaceBean> list = new JSONParser(string, kind).getPlaceBeanList();
            if (list != null && list.size() > 0) {
                posPlace = 0;
                while (posPlace < list.size()) {
                    setMarkerLat(((PlaceBean) list.get(posPlace)).getLatitude());
                    setMarkerLong(((PlaceBean) list.get(posPlace)).getLongitude());
                    markerPlace = new MarkerOptions().position(new LatLng(getMarkerLat(), getMarkerLong())).title(((PlaceBean) list.get(posPlace)).getName());
                    mapiconname = ((PlaceBean) list.get(posPlace)).getName();
                    name_ = ((PlaceBean) list.get(posPlace)).getName();
                    address_ = ((PlaceBean) list.get(posPlace)).getVicinity();
                    placerefernce_ = ((PlaceBean) list.get(posPlace)).getPlaceref();
                    placeIcon_ = ((PlaceBean) list.get(posPlace)).getIcon();
                            int pos = getPosition();
                    URL url = new URL(list.get(posPlace).getIcon());
//                    Log.e("Icon Url", list.get(posPlace).getIcon());
                    Bitmap bitmap = DownloadImage(list.get(posPlace).getIcon());
//                    markerPlace.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                    f1599m = googleMap.addMarker(markerPlace);
                    placesname.put(f1599m.getId(), name_);
                    placesAddress.put(f1599m.getId(), address_);
                    plavereference.put(f1599m.getId(), placerefernce_);
                    placeIconMap.put(f1599m.getId(), placeIcon_);
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(new LatLng(getMarkerLat(), getMarkerLong())).zoom(14.0f).build()));
                    int i = posPlace;
                    posPlace++;

                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {

                            String refer_name =  placesname.get(marker.getId());
                            String refer_address = placesAddress.get(marker.getId());
                            kind = getIntent().getStringExtra("Place_id");
                            refeceren_place =  plavereference.get(marker.getId());
                            name.setText(refer_name);
                            address.setText(refer_address);
//                            iconPlace.setImageBitmap(DownloadImage(placeIconMap.get(marker.getId())));
                            Picasso.with(MapActivity.this).load(placeIconMap.get(marker.getId()))
                                    .error(R.mipmap.ic_launcher).into(iconPlace);

                            if (isBootonLinear) {
                                detailsLinearLay.setVisibility(View.VISIBLE);
                                isBootonLinear = false;
                            } else {
                                detailsLinearLay.setVisibility(View.VISIBLE);
                                isBootonLinear = true;
                                CLOSE.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        detailsLinearLay.setVisibility(View.GONE);
                                    }
                                });
                                DETAILS.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       /* Intent detailActivity = new Intent(MapActivity.this.context, PlaceDetail.class);
                                        detailActivity.putExtra("placeId", MapActivity.this.refeceren_place);
                                        detailActivity.putExtra("kind", MapActivity.this.kind);
                                        startActivity(detailActivity);
                                        refeceren_place = "";
                                        detailsLinearLay.setVisibility(8);*/
                                    }
                                });
                            }
                            String reference =  placesname.get(marker.getId());
                            return true;
                        }
                    });

                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error 404", Toast.LENGTH_LONG).show();
        }
    }

    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String urlString)
            throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");
        }
        return in;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double dist = (60.0d * rad2deg(Math.acos((Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))) + ((Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))) * Math.cos(deg2rad(lon1 - lon2)))))) * 1.1515d;
        if (unit == "K") {
            return dist * 1.609344d;
        }
        if (unit == "N") {
            return dist * 0.8684d;
        }
        return dist;
    }

    private static double deg2rad(double deg) {
        return (3.141592653589793d * deg) / 180.0d;
    }

    private static double rad2deg(double rad) {
        return (180.0d * rad) / 3.141592653589793d;
    }
}
