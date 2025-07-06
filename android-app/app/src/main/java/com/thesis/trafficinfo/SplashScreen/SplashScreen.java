package com.thesis.trafficinfo.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.thesis.trafficinfo.LoginAndSignUp.LoginActivity;
import com.thesis.trafficinfo.R;
import com.thesis.trafficinfo.SessionManager.SessionManager;
import com.thesis.trafficinfo.TraficPolicePanel.PoliceDashboard;

public class SplashScreen extends AppCompatActivity {
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sessionManager = new SessionManager(this);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    if (sessionManager.isLoggin()) {
                        sleep(2000);
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));

                        finish();
                    } else {
                        sleep(2000);
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));

                        finish();
                    }

                } catch (Exception e) {

                }
            }
        };
        thread.start();
    }
}