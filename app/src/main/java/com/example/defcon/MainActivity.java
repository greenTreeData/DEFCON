package com.example.defcon;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * TODO: implement https://stackoverflow.com/questions/36902667/how-to-schedule-notification-in-android
 */
@RequiresApi(api = Build.VERSION_CODES.R)
public class MainActivity extends AppCompatActivity {

    FloatingActionButton buttonConfig;

    //Fragment managment:
    FragmentManager fragmentManager = getSupportFragmentManager();
    defconFragment fragmentDefcon = (defconFragment) fragmentManager.findFragmentById(R.id.defconFragment);
    configFragment fragmentConfig = (configFragment) fragmentManager.findFragmentById(R.id.configFrame);

    private int actualF = 0;
    static final int fConfig = 1;
    static final int fDefcon = 0;


    public MainActivity() {
        super(R.layout.main_layout);
    }

    //Life cicle:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //First stage called when the activity is launched.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, defconFragment.class, null)
                .commit();

        buttonConfig = findViewById(R.id.actionButtonConfig);
        buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (actualF){
                    case fDefcon:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .add(R.id.fragment_container_view, configFragment.class, null)
                                .commit();
                        actualF = fConfig;
                        break;
                    case fConfig:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .add(R.id.fragment_container_view, defconFragment.class, null)
                                .commit();
                        actualF = fDefcon;
                        break;
                }

            }
        });
    }



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        //changeButtonActivate();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}