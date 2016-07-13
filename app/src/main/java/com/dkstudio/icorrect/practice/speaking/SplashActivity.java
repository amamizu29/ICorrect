package com.dkstudio.icorrect.practice.speaking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.dkstudio.icorrect.R;

/**
 * Created by Administrator on 30/06/2016.
 */
public class SplashActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(3000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }.start();
    }


}
