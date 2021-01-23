package com.example.defcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link configFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class configFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    private static final int VALUE_NONE = 0;
    private static final int VALUE_DAY = 1;
    private static final int VALUE_WEEK = 2;
    private static final String REMINDER_TIME = "ReminderTime";
    private static final String REMINDER_HOURS = "ReminderHours";
    private static final String REMINDER_MINS = "ReminderMins";

    SharedPreferences appP;
    SharedPreferences.Editor appPEdit;

    static final String TAG = "PRESENTATION-config";
    static final Pattern clockREGEX = Pattern.compile("[0-2]?\\d:[0-5]\\d");

    public static configFragment newInstance() {
        configFragment configFragment = new configFragment();
        return configFragment;
    }

    public configFragment() {
        // Required empty public constructor
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appP = getActivity().getPreferences(Context.MODE_PRIVATE);
        appPEdit = appP.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.rbDay).setOnClickListener(this);//set onClick listener
        view.findViewById(R.id.rbWeek).setOnClickListener(this);
        view.findViewById(R.id.rbNone).setOnClickListener(this);
        TextView txtVw = view.findViewById(R.id.timeEditText);
        txtVw.setOnFocusChangeListener(this);

        //Get inital status of the options:
        final int actual = appP.getInt(REMINDER_TIME, VALUE_NONE);
        RadioButton rb = view.findViewById(R.id.rbNone);
        switch (actual){
            case VALUE_DAY:
                rb = view.findViewById(R.id.rbDay);
                break;
            case VALUE_WEEK:
                rb = view.findViewById(R.id.rbWeek);
                break;
            case VALUE_NONE:
                rb = view.findViewById(R.id.rbNone);
                break;
        }
        rb.setChecked(true);

        final int H = appP.getInt(REMINDER_HOURS, 12);
        final int m = appP.getInt(REMINDER_MINS, 0);
        String Hs, ms;
        if(H < 10) Hs = "0" + String.valueOf(H);
        else Hs = String.valueOf(H);
        if(m < 10) ms = "0" + String.valueOf(m);
        else ms = String.valueOf(m);
        txtVw.setText(new StringBuilder().append(Hs).append(":").append(ms).toString());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
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
        appPEdit.putInt(REMINDER_TIME, newVal);
        appPEdit.commit();
        Log.i(TAG, "change config: newVal" + newVal);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId() != R.id.timeEditText) return;
        if (! hasFocus){
            String inText = ((EditText) v).getText().toString();
            if (! clockREGEX.matcher(inText).matches()){
                Toast.makeText(getContext(), "The input text is incorrect, must be HH:MM", Toast.LENGTH_LONG).show();
                ((EditText) v).setText("12:00");
            }
            else{
                String[] readyToParse = inText.split(":");
                int H = Integer.parseInt(readyToParse[0]);
                int m = Integer.parseInt(readyToParse[1]);
                appPEdit.putInt(REMINDER_HOURS, H);
                appPEdit.putInt(REMINDER_MINS, m);
                appPEdit.commit();
                Log.i(TAG, "new Hour: " + inText);
            }
        }
    }
}