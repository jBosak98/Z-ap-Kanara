package pl.ct8.monteth.zlapkanara.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.tasks.OnSuccessListener;
import pl.ct8.monteth.zlapkanara.R;
import pl.ct8.monteth.zlapkanara.services.StreetLocationService;
import pl.ct8.monteth.zlapkanara.services.TicketInsService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double latitudeStreet, longitudeStreet;
    Double latitudeUser, longitudeUser;
    Location userLocation;
    private String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSION_ALL = 101;
    private Marker ticketInsurance;
    public static final int RADIUS = 300;
    Circle dangerAreaCircle;
    CircleOptions optionsY, optionsR;
    boolean ifVibrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        int Permission_All = 1;
        String[] Permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,};
        if (!hasPermissions(this, Permissions)) {
            ActivityCompat.requestPermissions(this, Permissions, Permission_All);
        }


        latitudeStreet = StreetLocationService.INSTANCE.getLocation(this, TicketInsService.INSTANCE.getTodaysData().getStreet() + ", Wrocław").getLatitude();
        longitudeStreet = StreetLocationService.INSTANCE.getLocation(this, TicketInsService.INSTANCE.getTodaysData().getStreet() + ", Wrocław").getLongitude();
        optionsR = new CircleOptions()
                .center(new LatLng(latitudeStreet, longitudeStreet))
                .radius(RADIUS)
                .strokeColor(Color.parseColor("#66FF0000"))
                .fillColor(Color.parseColor("#38FF0000"));
        optionsY = new CircleOptions()
                .center(new LatLng(latitudeStreet, longitudeStreet))
                .radius(RADIUS)
                .strokeColor(Color.parseColor("#66EEEE00"))
                .fillColor(Color.parseColor("#38EEEE00"));
        ifVibrate = true;

//        latitudeUser = userLocation.getLatitude();
//        longitudeUser = userLocation.getLongitude();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a ticketInsurance near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng place = new LatLng(latitudeStreet, longitudeStreet);

        // Add a ticketInsurance in Sydney and move the camera
        ticketInsurance = mMap.addMarker(new MarkerOptions().position(place));

//        if(ifDistLessThanRadius(latitudeStreet,longitudeStreet,latitudeStreet,longitudeStreet)) {
//            Circle dangerAreaCircle = mMap.addCircle(new CircleOptions()
//                    .center(place)
//                    .radius(RADIUS)
//                    .strokeColor(Color.parseColor("#66FF0000"))
//                    .fillColor(Color.parseColor("#38FF0000")));
//        }
//        else{
//            Circle dangerAreaCircle = mMap.addCircle(new CircleOptions()
//                    .center(place)
//                    .radius(RADIUS)
//                    .strokeColor(Color.parseColor("#66EEEE00"))
//                    .fillColor(Color.parseColor("#38EEEE00")));
//        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(place)
                .zoom(15)
                .build();
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            askPermission();
//        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        askPermission();
        initLocationService();

    }

    public void askPermission() {
        for (String permission : PERMISSIONS) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PERMISSIONS, PERMISSION_ALL);
                    return;
                }
            }
        }
    }
    public static boolean hasPermissions(Context context, String... permissions){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context!=null &&permissions!=null){
            for (String permission: permissions){
                if (ActivityCompat.checkSelfPermission(context, permission)!=PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    public void initLocationService() {
        FusedLocationProviderClient locationProvider = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askPermission();
        }
        locationProvider.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Log.e("LOC:","Init succeed"+location.getLatitude() + ", " + location.getLongitude());
            }
        });
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = location;
                latitudeUser = userLocation.getLatitude();
                longitudeUser = userLocation.getLongitude();
                Log.e("LOC:",userLocation.getLatitude() + ", " + userLocation.getLongitude());
                if(ifDistLessThanRadius(latitudeStreet,longitudeStreet,latitudeUser,longitudeUser)){
                    if(dangerAreaCircle!=null) {
                        dangerAreaCircle.remove();
                    }
                    dangerAreaCircle = mMap.addCircle(optionsR);
                }
                else{
                    if(dangerAreaCircle!=null) {
                        dangerAreaCircle.remove();
                    }
                    dangerAreaCircle = mMap.addCircle(optionsY);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LocationManager locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }


    private boolean ifDistLessThanRadius(double length1, double width1,double length2,double width2){
        double dist = Math.sqrt(Math.pow(length2-length1,2)+Math.pow(Math.cos(((length1*Math.PI)/180))*(width2-width1),2))*(40075.704/360);
        Log.e("CHECK",""+dist*1000);
        return dist*1000<RADIUS;

    }
}
