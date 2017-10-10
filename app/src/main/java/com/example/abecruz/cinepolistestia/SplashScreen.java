package com.example.abecruz.cinepolistestia;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.abecruz.cinepolistestia.Login.ui.MoviesTest;


public class SplashScreen extends AppCompatActivity  {
    private Handler mUiLooperHandler;
    public static Intent createLaunchIntent(Context context) {
        Intent intent = new Intent(context, SplashScreen.class);
        return intent;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (savedInstanceState == null) {
            getUiLooperHandler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    //Create an intent that will start the main activity.
                    Intent mainIntent = new Intent(SplashScreen.this, MoviesTest.class);
                    SplashScreen.this.startActivity(mainIntent);

                    //Finish splash activity so user cant go back to it.
                    SplashScreen.this.finish();

                    //Apply splash exit (fade out) and main entry (fade in) animation transitions.
                    overridePendingTransition(R.anim.mainfadein, R.anim.splashfadeout);

                }


            },800);

        }
    }
    public Handler getUiLooperHandler() {
        if (mUiLooperHandler == null) {
            mUiLooperHandler = new Handler(Looper.getMainLooper());
        }
        return mUiLooperHandler;
    }


}
