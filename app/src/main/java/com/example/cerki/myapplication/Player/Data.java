package com.example.cerki.myapplication.Player;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.Set;


public class Data extends HashMap<String,DataEntry> {
    ContentValues generateContentValues(){
        ContentValues contentValues = new ContentValues();
        Set<String> dataKeys = keySet();
        for(String key : dataKeys){
            DataEntry entry = get(key);
            if(entry.isFloating())
                contentValues.put(key,entry.getDoubleVal());
            else
                contentValues.put(key,entry.getIntVal());
        }
        return contentValues;
    }

    public HashMap<String, DataEntry> compare(Data comparable){
        HashMap<String,DataEntry> difference = new HashMap<>();
        for (String key : keySet()) {
            if (get(key).equals(comparable.get(key)))
                continue;
            if(comparable.get(key) == null)
                continue;
            DataEntry diff = get(key).subtract(comparable.get(key));
            difference.put(key, diff);
        }
        return difference;
    }
}
