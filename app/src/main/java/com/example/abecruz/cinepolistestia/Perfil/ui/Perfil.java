package com.example.abecruz.cinepolistestia.Perfil.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abecruz.cinepolistestia.Complejo.ui.Complejo;
import com.example.abecruz.cinepolistestia.Perfil.APIService;
import com.example.abecruz.cinepolistestia.Perfil.adapters.PerfilListaAdapter;
import com.example.abecruz.cinepolistestia.Perfil.model.ApiGetCall;
import com.example.abecruz.cinepolistestia.Perfil.model.Loyalty;
import com.example.abecruz.cinepolistestia.Perfil.tools.ApiUtils;
import com.example.abecruz.cinepolistestia.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perfil extends AppCompatActivity {

    private static final String TAG = "PerfilActivityTAG_";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_TOKEN_TYPE = "tokenType";
    private TextView mTextMessage;
    private TextView mNombreUsuario;
    private TextView mCorreoUsuario;
    private TextView mTarjetaUsuario;
    private Button mObtenerTx;
    private ListView mListaDeTx;
    private List<String> data;
    private APIService mAPIService;
    public static String accessToken;
    public static String tokenType;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_perfil:
                    mTextMessage.setText(R.string.title_perfil);
                    return true;
                case R.id.navigation_cartelera:
                    mTextMessage.setText(R.string.title_cartelera);
                    return true;
                case R.id.navigation_complejo:
                    mTextMessage.setText(R.string.title_complejo);
                    Intent complejo = new Intent(getApplicationContext(), Complejo.class);
                    startActivity(complejo);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // getIntent() de la actividad pasada
        Intent myIntent = getIntent(); // devuelve los valores almacenados en el intent pasado
        //como no tenemos refresh solo quitamos el valor si cambia...
        if (accessToken==null)
            accessToken = myIntent.getStringExtra(KEY_ACCESS_TOKEN); // retornara "KEY_ACCESS_TOKEN"
        if (tokenType==null)
            tokenType = myIntent.getStringExtra(KEY_TOKEN_TYPE); // retornara "KEY_TOKEN_TYPE"


        Animation mainFade = AnimationUtils.loadAnimation(this, R.anim.mainfadein);
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.image_zoom_in);
        mTextMessage = (TextView) findViewById(R.id.message);
        mNombreUsuario = (TextView) findViewById(R.id.perfilNombreUsuario);
        mCorreoUsuario = (TextView) findViewById(R.id.perfilCorreoUsuario);
        mTarjetaUsuario = (TextView) findViewById(R.id.perfilNumeroTarjeta);
        mObtenerTx = (Button) findViewById(R.id.perfilBotonObtenerTx);
        mTextMessage.startAnimation(zoomIn);
        mNombreUsuario.startAnimation(zoomIn);
        mCorreoUsuario.startAnimation(zoomIn);
        mTarjetaUsuario.startAnimation(zoomIn);
        mObtenerTx.startAnimation(mainFade);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Primero hacemos el casting del listview con una referencia
        mListaDeTx = (ListView) findViewById(R.id.perfilListTx);
        mAPIService = ApiUtils.getAPIService();
        getInfoFromCall();

        mObtenerTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTransactionsFromAPI();
            }
        });


    }


    private void getInfoFromCall() {
        Resources res = getResources();
        String apiKey = String.format(res.getString(R.string.api_key));
        String auth = tokenType + " " + accessToken;
        mAPIService.getUser(apiKey, auth).enqueue(new Callback<ApiGetCall>() {
            @Override
            public void onResponse(Call<ApiGetCall> call, Response<ApiGetCall> response) {

                if (response.isSuccessful()) {
                    mNombreUsuario.setText(response.body().getName().toString());
                    mCorreoUsuario.setText(response.body().getEmail().toString());
                    mTarjetaUsuario.setText(response.body().getCardNumber().toString());
                    Log.i(TAG, "GET call to API." + response.body().toString());
                    Toast.makeText(Perfil.this, "Exito!, Bienvenido.", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body()!=null) {
                        Log.i(TAG, "post submitted to API." + response.body().toString());
                        Toast.makeText(Perfil.this, "Error response: " + response.body().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiGetCall> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API. Reason: " + t);
                Toast.makeText(Perfil.this, "Error: " + t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getTransactionsFromAPI() {
        Resources res = getResources();
        String countryCode = String.format(res.getString(R.string.country_code));
        mAPIService.saveTransaction(countryCode, mTarjetaUsuario.getText().toString(), "true").enqueue(new Callback<Loyalty>() {
            @Override
            public void onResponse(Call<Loyalty> call, Response<Loyalty> response) {

                if (response.isSuccessful()) {
                    Log.i(TAG, "POST call to API." + response.body().toString());
                    Toast.makeText(Perfil.this, "Exito!, Bienvenido.", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.code() == 404) {
                        Log.i(TAG, "POST error on API." + response.errorBody().toString());
                        Toast.makeText(Perfil.this, "Error: An error occurred, please try again later. ", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Loyalty> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API. Reason: " + t);
                Toast.makeText(Perfil.this, "Error: " + t, Toast.LENGTH_LONG).show();
            }
        });

        //ESTO ES DONDE SE ENLAZA Y USA EL ADAPTER
        data = new ArrayList<String>();
        if (!data.isEmpty()) {
            PerfilListaAdapter myAdapter = new PerfilListaAdapter(this, R.layout.perfil_list_item, data);
            mListaDeTx.setAdapter(myAdapter);
        }

    }

}
