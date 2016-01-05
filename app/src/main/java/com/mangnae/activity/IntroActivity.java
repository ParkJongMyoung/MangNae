package com.mangnae.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.mangnae.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * git test!
 * Created by pjm on 2015-12-30.
 */
public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.intro_activity);

        timeToMain();
    }

    private void timeToMain(){

        Timer goTimer = new Timer();
        goTimer.schedule(new TimerTask() {
                             @Override
                             public void run() {

                                 Intent  i = new Intent(IntroActivity.this, MainActivity.class);
                                 startActivity(i);
                                 finish();
                             }
                         }, 2000
        );

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
