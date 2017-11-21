package com.example.cerki.myapplication.players_list;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.Set;


public class PlayerData extends HashMap<String,PlayerDataEntry> {
    public ContentValues generateContentValues(){
        ContentValues contentValues = new ContentValues();
        Set<String> dataKeys = keySet();
        for(String key : dataKeys){
            PlayerDataEntry entry = get(key);
            if(entry.isFloating())
                contentValues.put(key,entry.getDoubleVal());
            else
                contentValues.put(key,entry.getIntVal());
        }
        return contentValues;
    }

    public HashMap<String, PlayerDataEntry> compare(PlayerData comparable){
        HashMap<String,PlayerDataEntry> difference = new HashMap<>();
        for (String key : keySet()) {
            if (get(key).equals(comparable.get(key)))
                continue;
            if(comparable.get(key) == null)
                continue;
            PlayerDataEntry diff = get(key).subtract(comparable.get(key));
            difference.put(key, diff);
        }
        return difference;
    }
}
