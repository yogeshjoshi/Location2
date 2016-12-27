package com.example.joshiyogesh.location2;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {

    TextView Latitude , Longitude;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Latitude = (TextView)findViewById(R.id.Latitude_textview);
        Longitude = (TextView)findViewById(R.id.Longitude_textView);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void onStart(){
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void onStop(){
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG_TAG","Unfortunnely Connection Has been Suspended");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG_TAG","Connection Has been Dropped");
    }

    @Override
    public void onLocationChanged(Location location) {
            Longitude.setText(Double.valueOf(location.getLongitude()).toString());
            Latitude.setText(Double.valueOf(location.getLatitude()).toString());
    }
}
