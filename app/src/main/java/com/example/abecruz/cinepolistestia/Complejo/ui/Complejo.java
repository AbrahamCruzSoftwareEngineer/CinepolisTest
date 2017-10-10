package com.example.abecruz.cinepolistestia.Complejo.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abecruz.cinepolistestia.Complejo.model.ComApiGetCall;
import com.example.abecruz.cinepolistestia.Complejo.tools.ComApiUtils;
import com.example.abecruz.cinepolistestia.Complejo.ComAPIService;
import com.example.abecruz.cinepolistestia.Perfil.ui.Perfil;
import com.example.abecruz.cinepolistestia.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Complejo extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "ComplejoActivityTAG_";
    private TextView mTextMessage;
    private GoogleMap mMap;
    private TextView mNombre;
    private TextView mDireccion;
    private TextView mTelefono;
    private Button   mComoLlegar;
    private ComAPIService mAPIService;
    private String lat;
    private String lon;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_perfil:
                    mTextMessage.setText(R.string.title_perfil);
                    Intent perfil = new Intent(getApplicationContext(), Perfil.class);
                    startActivity(perfil);
                    return true;
                case R.id.navigation_cartelera:
                    mTextMessage.setText(R.string.title_cartelera);
                    return true;
                case R.id.navigation_complejo:
                    mTextMessage.setText(R.string.title_complejo);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complejo);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNombre = (TextView) findViewById(R.id.complejo_tv_nombre);
        mDireccion = (TextView) findViewById(R.id.complejo_tv_direccion);
        mTelefono = (TextView) findViewById(R.id.complejo_tv_telefono);
        mComoLlegar = (Button) findViewById(R.id.complejo_boton_como_llegar);
        mAPIService = ComApiUtils.getAPIService();
        getLocationFromCall();

        mComoLlegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDirections();
            }
        });

    }

    private void getDirections() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
        Uri.parse("google.navigation:q="+mDireccion.getText().toString()));
        startActivity(intent);
    }

    private void getLocationFromCall() {
        Resources res = getResources();
        String apiKey = String.format(res.getString(R.string.api_key));
        mAPIService.getComplex(apiKey).enqueue(new Callback<ComApiGetCall>() {
            @Override
            public void onResponse(Call<ComApiGetCall> call, Response<ComApiGetCall> response) {

                if (response.isSuccessful()) {
                    mNombre.setText(response.body().getName().toString());
                    mDireccion.setText(response.body().getAddress().toString());
                    mTelefono.setText(response.body().getPhone().toString());
                    lat = response.body().getLat().trim().toString();
                    lon = response.body().getLng().trim().toString();
                    Log.i(TAG, "GET call to API." + response.body().toString());
                    Toast.makeText(Complejo.this, "Exito!, Bienvenido.", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body()!=null) {
                        Log.i(TAG, "post submitted to API." + response.body().toString());
                        Toast.makeText(Complejo.this, "Error response: " + response.body().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ComApiGetCall> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API. Reason: " + t);
                Toast.makeText(Complejo.this, "Error: " + t, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latitude, longitude;
        latitude = 19.687;
        longitude = -101.151;
        // Add a marker in Sydney and move the camera
        LatLng cinema = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(cinema).title(String.valueOf(mNombre)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cinema));
        //Store these lat lng values somewhere. These should be constant.
        LatLng coordinate = new LatLng(latitude, longitude);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, 15);
        mMap.animateCamera(location);
    }
}
