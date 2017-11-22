package com.example.cerki.myapplication.players_list;

import android.content.ContentValues;
import android.database.Cursor;


import java.util.HashMap;
import java.util.Set;


public class Player {
    private int id;
    public final PlayerData data;
    public HashMap<String,PlayerDataEntry> difference;
    public final HashMap<String,String> personalInfo;

    public ContentValues generateContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.ID,id);
        Set<String> personalInfoKeys = personalInfo.keySet();
        for(String key : personalInfoKeys){
           contentValues.put(key,personalInfo.get(key));
        }
        contentValues.putAll(data.generateContentValues());
        return contentValues;
    }
    public Player(Cursor query) {
        this(query.getInt(0));
        int columnCount = query.getColumnCount();
        set("username",query.getString(1));
        set("country",query.getString(2));
        for(int i = 3; i < columnCount;i++){
            String columnName = query.getColumnName(i);
            double value = query.getDouble(i);
            this.set(columnName, new PlayerDataEntry(value));
        }
        query.close();
    }
    public Player(int id){
        this();
        this.id = id;
    }
    private Player() {
        data = new PlayerData();
        personalInfo = new HashMap<>();
    }

    public Player(String user_id) {
         this(Integer.parseInt(user_id.replaceAll("[^0-9]","")));
    }


    public void set(String key,PlayerDataEntry entry){
        data.put(key,entry);
    }
    public void set(String key,String value){
        if(key == null || value == null)return;
        personalInfo.put(key,value);
    }
    public String get(String key){
        if(personalInfo.containsKey(key)) return personalInfo.get(key);
        if(data.containsKey(key)) return data.get(key).getAsString();
        return "";
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId(String id){
        this.id = new PlayerDataEntry(id).getIntVal();
    }


    public PlayerDataEntry getDataEntry(String entry){
        return data.get(entry);
    }
    public int getId() {
        return id;
    }

    public boolean hasPerformanceChanged() {
        return difference != null && !difference.isEmpty() && difference.get(Columns.PP) != null;
    }
}
