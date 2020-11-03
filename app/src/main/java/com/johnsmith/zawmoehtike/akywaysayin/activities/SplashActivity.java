package com.johnsmith.zawmoehtike.akywaysayin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.johnsmith.zawmoehtike.akywaysayin.R;

public class SplashActivity extends AppCompatActivity {
    private TextView tvA, tvKyway, tvSa, tvYin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvA = findViewById(R.id.one);
        tvKyway = findViewById(R.id.two);
        tvSa = findViewById(R.id.three);
        tvYin = findViewById(R.id.four);
        final int[] colorOne = {R.color.colorYellowButton, R.color.colorGreenButton, R.color.colorPrimary, R.color.colorRedButton};

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < colorOne.length; i++) {
                    if (i == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tvA.setTextColor(getResources().getColor(colorOne[i]));
                    }
                    if (i == 1)
                        tvKyway.setTextColor(getResources().getColor(colorOne[i]));
                    if (i == 2)
                        tvSa.setTextColor(getResources().getColor(colorOne[i]));
                    if (i == 3)
                        tvYin.setTextColor(getResources().getColor(colorOne[i]));

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == colorOne.length - 1) {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        finish();
                    }
                }
            }
        }).start();
    }
}
