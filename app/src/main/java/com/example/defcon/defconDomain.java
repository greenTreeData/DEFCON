package com.example.defcon;

import android.content.Context;
import android.util.Log;

import com.example.defcon.Persistance.defconPersistence;

/**
 * Basic DEFCON domain class.
 *
 */
public class defconDomain {

    private static defconDomain myDefcon;
    private static Context appContext;
    private static defconPersistence persistance;
    private int defconLVL;
    private static String domTAG = "DOMAIN";

    public static defconDomain getDefcon(Context c) {
        if(myDefcon ==null){
            appContext = c;
            myDefcon = new defconDomain();
        }
        Log.i(domTAG, "getDefcon: init controller.");
        return myDefcon;
    }

    private defconDomain(){
        persistance = defconPersistence.getInstance(appContext);
        defconLVL = persistance.loadDEFCON();
    }

    public int getDefconLVL() {
        return defconLVL;
    }

    public void setDefcon(int newLVL) {
        this.defconLVL = newLVL;
        persistance.saveDEFCON(newLVL);
    }
}
