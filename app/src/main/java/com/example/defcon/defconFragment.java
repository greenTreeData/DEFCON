package com.example.defcon;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class defconFragment extends Fragment {

    //List view for the buttons and colors.
    private List<Button> dX;
    private List<Integer> colors;

    static final String TAG = "PRESENTATION-defconFragment";

    defconDomain statusD;

    public defconFragment() {
    }

    public static defconFragment newInstance() {
        defconFragment fragment = new defconFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusD = defconDomain.getDefcon(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_defcon, container, false);
    }

    @SuppressLint("LongLogTag")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button d1 = view.findViewById(R.id.button8);
        Button d2 = view.findViewById(R.id.button9);
        Button d3 = view.findViewById(R.id.button10);
        Button d4 = view.findViewById(R.id.button11);
        Button d5 = view.findViewById(R.id.button12);
        dX = new ArrayList<Button>(List.of(d1, d2, d3, d4, d5)); //Create array
        colors = new ArrayList<>(List.of(getResources().getColor(R.color.defcon1),
                getResources().getColor(R.color.defcon2),
                getResources().getColor(R.color.defcon3),
                getResources().getColor(R.color.defcon4),
                getResources().getColor(R.color.defcon5)));
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusD.setDefcon(1);
                changeButtonActivate();
            }
        });
        d2.setOnClickListener(v -> {
            statusD.setDefcon(2);
            changeButtonActivate();
        });
        d3.setOnClickListener(v -> {
            statusD.setDefcon(3);
            changeButtonActivate();
        });
        d4.setOnClickListener(v -> {
            statusD.setDefcon(4);
            changeButtonActivate();
        });
        d5.setOnClickListener(v -> {
            statusD.setDefcon(5);
            changeButtonActivate();
        });

        changeButtonActivate();
        Log.i(TAG, "onViewCreated: get DEFCON level from domain");
        super.onViewCreated(view, savedInstanceState);
    }

    private void changeButtonActivate(){
        desactivateAllButtons();
        int act = statusD.getDefconLVL() - 1; //index for the lists.
        Button dAct = dX.get(act);

        dAct.setTextColor(getResources().getColor(R.color.black));//Activate color
        dAct.setBackgroundColor(colors.get(act));
    }

    private void desactivateAllButtons(){
        int i = 0;
        for (Button b : dX){
            b.setBackgroundColor(getResources().getColor(R.color.emptyDefcon));
            b.setTextColor(colors.get(i));
            i++;
        }
    }
}