package com.example.defcon;

import org.json.JSONObject;
import org.junit.Test;

import java.io.File;

public class PersistanceTest {

    @Test
    public void test_general_JSON(){
        File f = new File("",  "myJSON.json");
        JSONObject myJSON = new JSONObject();
        try {
            myJSON.put("level", new Integer(5));
            myJSON.put("j", false);
            System.out.println(myJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
