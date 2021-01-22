package com.example.defcon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link configFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class configFragment extends Fragment implements View.OnClickListener{

    private static final String REMINDER_TIME = "ReminderTime";
    private static final int VALUE_NONE = 0;
    private static final int VALUE_DAY = 1;
    private static final int VALUE_WEEK = 2;

    SharedPreferences appP;


    static final String TAG = "PRESENTATION-configFragment";
    public static configFragment newInstance() {
        configFragment fragment = new configFragment();
        return fragment;
    }

    public configFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appP = getActivity().getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.rbDay).setOnClickListener(this);
        view.findViewById(R.id.rbWeek).setOnClickListener(this);
        view.findViewById(R.id.rbNone).setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        RadioButton rb1 = (RadioButton) v;
        Log.i(TAG, "onRadioButtonClicked: set " + rb1.getText().toString());
        int newVal = VALUE_NONE;
        switch (rb1.getText().toString()){
            case "Every day":
                newVal = VALUE_DAY;
                break;
            case "Every week":
                newVal = VALUE_WEEK;
                break;
            case "No reminder":
                newVal = VALUE_NONE;
                break;

        }
        appP.edit().putInt(REMINDER_TIME, newVal);
        Log.i(TAG, "change config: newVal" + newVal);

    }
}