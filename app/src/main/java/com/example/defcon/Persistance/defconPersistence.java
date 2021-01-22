package com.example.defcon.Persistance;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class defconPersistence extends generalPersistence {

    private static defconPersistence myInstance;
    public static final String LEVEL = "level";
    private static String TAG = "PERSISTENCE";

    public static defconPersistence getInstance(Context c) {
        if (myInstance == null) {
            String fileName = "data.json";
            myInstance = new defconPersistence(c, fileName);
        }
        Log.i(TAG, "getInstance: init controler.");
        return myInstance;
    }

    private defconPersistence(Context c, String fileName) {
        super(c.getFilesDir(), fileName);
    }

    @Override
    void initPersistance() {
        try {
            myJSON.put(LEVEL, Integer.valueOf(5));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveDEFCON(int lvl) {
        changeKey(LEVEL, lvl);
        Log.i(TAG, "saveDEFCON: saving new DEFCON level " + lvl);
    }

    public int loadDEFCON() {
        int lvl = queryInt(LEVEL);
        Log.i(TAG, "loadDEFCON: loading DEFCON level " + lvl);
        return lvl;
    }
}
