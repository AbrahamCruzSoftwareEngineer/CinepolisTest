package com.example.abecruz.cinepolistestia.Login.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abecruz.cinepolistestia.Login.APIService;
import com.example.abecruz.cinepolistestia.Login.model.ApiPostCall;
import com.example.abecruz.cinepolistestia.Login.tools.ApiUtils;
import com.example.abecruz.cinepolistestia.Perfil.ui.Perfil;
import com.example.abecruz.cinepolistestia.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesTest extends AppCompatActivity {


    // UI referencias.
    private TextView mUser;
    private TextView mPassword;
    private Button mSubmitLogin;
    private APIService mAPIService;


    private static final String TAG = "LoginActivityTAG_";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_TOKEN_TYPE = "tokenType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_test);
        Animation mainFade = AnimationUtils.loadAnimation(this, R.anim.mainfadein);
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.image_zoom_in);
        mUser = (TextView) findViewById(R.id.login_TextView_User);
        mPassword = (TextView) findViewById(R.id.login_TextView_Password);
        mSubmitLogin = (Button) findViewById(R.id.login_button);
        mUser.startAnimation(zoomIn);
        mPassword.startAnimation(zoomIn);
        mSubmitLogin.startAnimation(mainFade);

        mAPIService = ApiUtils.getAPIService();

        mSubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mUser.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
                    sendPost(user, password);
                }
            }
        });

    }

    public void sendPost(String user, String password) {
        Resources res = getResources();
        String countryCode = String.format(res.getString(R.string.country_code));
        String grantType = String.format(res.getString(R.string.grant_type));
        String clientId = String.format(res.getString(R.string.client_id));
        String clientSecret = String.format(res.getString(R.string.client_secret));

        mAPIService.savePost(countryCode ,user, password, grantType,clientId,clientSecret).enqueue(new Callback<ApiPostCall>() {
            @Override
            public void onResponse(Call<ApiPostCall> call, Response<ApiPostCall> response) {

                if(response.isSuccessful()) {
                    Intent profile = new Intent(getApplicationContext(), Perfil.class);
                    profile.putExtra(KEY_ACCESS_TOKEN,response.body().getAccessToken().toString());
                    profile.putExtra(KEY_TOKEN_TYPE,response.body().getTokenType().toString());
                    startActivity(profile);
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    Toast.makeText(MoviesTest.this, "Exito!, Bienvenido.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                    Toast.makeText(MoviesTest.this, "Error response: "+response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiPostCall> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API. Reason: "+t);
                Toast.makeText(MoviesTest.this, "Error: "+t, Toast.LENGTH_LONG).show();
            }
        });
    }


}
