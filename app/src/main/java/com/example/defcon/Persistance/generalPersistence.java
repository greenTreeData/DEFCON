package com.example.defcon.Persistance;

import android.content.Context;

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

public abstract class generalPersistence {

    protected JSONObject myJSON;  //JSON object that contains all info.
    private File f;             //File to save the JSON object.

    /**
     * Construct.
     * @param parent
     * @param fileName
     */
    public generalPersistence(File parent, String fileName) {
        f = new File(parent, fileName);
        if(!f.exists()) initPersistance();
        else{
            String s = null;
            try {
                s = getStringFromFile();
                JSONTokener jsonTokener = new JSONTokener(s);
                myJSON = new JSONObject(jsonTokener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Callback to fill with inital values the JSONObject myJSON.
     */
    abstract void initPersistance();

    public Integer queryInt(String key){
        int lvl = 0;
        try {
            lvl = myJSON.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    /**
     * Change a value, with key "key".
     * @param key
     * @param newValue
     */
    public void changeKey(String key, Object newValue){
        try {
            myJSON.put(key, newValue);
            writeToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Read the file that contains the JSON.
     * @return
     * @throws FileNotFoundException
     */
    private String getStringFromFile() throws FileNotFoundException {
        String contents;
        FileInputStream fis = new FileInputStream(f);
        StringBuilder stringBuilder = new StringBuilder();
        try  {
            InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            contents = stringBuilder.toString();
        }
        return contents;
    }

    /**
     * Saves the JSON object.
     */
    private void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            String toWrite = myJSON.toString(4);
            byte[] b = toWrite.getBytes();
            fos.write(b);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
