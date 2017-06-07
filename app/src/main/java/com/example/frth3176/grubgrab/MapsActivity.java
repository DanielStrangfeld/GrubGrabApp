package com.example.frth3176.grubgrab;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener, LocationListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private Location mLastLocation;
    public Button button1;

    public int test;

    public void init ()     //go back to calendar
    {
        button1= (Button)findViewById(R.id.nutinButaButton);     //Id of the button to go to cal view
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent switchActivities = new Intent(MapsActivity.this,MainActivity.class);
                startActivity(switchActivities);
            }
        });

        Intent i = getIntent();
        Double thisLC1 = i.getDoubleExtra("theLC1",0.0);
        Double thisLC2 = i.getDoubleExtra("theLC2",0.0);
        String thisLocation = i.getStringExtra("theLocation");
        String thisTime = i.getStringExtra("theTime");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        init();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //IDK what the "getDoubleExtra" or "getStringExtra" is

        Intent i = getIntent();
        Double thisLC1 = i.getDoubleExtra("theLC1",0.0);    //These refer to the coordinates and are used to place markers
        Double thisLC2 = i.getDoubleExtra("theLC2",0.0);    //Pull in the coordinates on the blue numbers.
        String thisLocation = i.getStringExtra("theLocation");
        String thisTime = i.getStringExtra("theTime");


        //Place a marker
        LatLng foodEvent = new LatLng(thisLC1, thisLC2);
        mMap.addMarker(new MarkerOptions().position(foodEvent));  //.title("Free Food Event").snippet(thisLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(foodEvent, 12));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(this);            //Could this be the reason that it crashes???
    }

    private void setUpMap ()
    {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        mMap.setMyLocationEnabled(true);

/*
        LocationAvailability locationAvailability =
                LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
        if (null != locationAvailability && locationAvailability.isLocationAvailable())
        {

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (mLastLocation != null)
            {
                LatLng currentLocation = new LatLng(mLastLocation.getLatitude(), mLastLocation
                        .getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
            }
        }
        */
    }



//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        //set map type
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
//        //check for permission
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            //enables location layer
//            mMap.setMyLocationEnabled(true);
//        }
//        /*
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        */
//    }



    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        setUpMap();
    }

    @Override
    public void onConnectionSuspended(int i) { }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

    @Override
    public void onLocationChanged(Location location) { }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        Intent i = getIntent();
        Double thisLC1 = i.getDoubleExtra("theLC1",0.0);    //These refer to the coordinates and are used to place markers
        Double thisLC2 = i.getDoubleExtra("theLC2",0.0);    //Pull in the coordinates on the blue numbers.
        String thisLocation = i.getStringExtra("theLocation");
        String thisTime = i.getStringExtra("theTime");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle(thisLC2.toString())
                .setMessage(thisLC1.toString())       //Data here
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        return false;
    }
}

