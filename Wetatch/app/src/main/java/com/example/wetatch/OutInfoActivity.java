package com.example.wetatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class OutInfoActivity extends AppCompatActivity {

    private static final String TAG = "!!!!!!OutInfoActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то Activity надо закрыть
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Если Activity запускается первый раз
            // то добавим фрагмент в Activity
            OutInfoFragment details = new OutInfoFragment();

            //добавим фрагмент в активити                       //?? как работает ??
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, details).commit();
        }
    }
}
