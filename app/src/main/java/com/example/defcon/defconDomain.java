package com.example.defcon;

/**
 * Basic DEFCON domain class.
 *
 */
public class defconDomain {

    private static defconDomain myDefcon;
    private int defconLVL=1;

    public static defconDomain getDefcon() {
        if(myDefcon ==null){
            myDefcon = new defconDomain();
        }
        return myDefcon;
    }

    private defconDomain(){

    }

    public int getDefconLVL() {
        return defconLVL;
    }

    public void setDefcon(int newLVL) {
        this.defconLVL = newLVL;
    }
}
