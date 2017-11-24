package com.example.cerki.myapplication.Player;

import android.content.ContentValues;
import android.database.Cursor;


import java.util.HashMap;
import java.util.Set;


public class Player {
    public static final String ACTIVE = "active";
    public static final String INACTIVE ="inactive" ;
    private int id;
    public final Data data;
    public HashMap<String,DataEntry> difference;
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
        this(query.getInt(query.getColumnIndex(Columns.ID)));
        int columnCount = query.getColumnCount();
        int comparable_border_id = query.getColumnIndex(Columns.PP);
        for(int i = 1; i < comparable_border_id;i++){
            String columnName = query.getColumnName(i);
            String value = query.getString(i);
            set(columnName,value);
        }
        for(int i = comparable_border_id;i < columnCount;i++){
            String columnName = query.getColumnName(i);
            double value = query.getDouble(i);
            this.set(columnName, new DataEntry(value));
        }
    }
    public Player(int id){
        this();
        this.id = id;
    }
    private Player() {
        data = new Data();
        personalInfo = new HashMap<>();
    }

    public Player(String user_id) {
         this(Integer.parseInt(user_id.replaceAll("[^0-9]","")));
    }


    public void set(String key,DataEntry entry){
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
        this.id = new DataEntry(id).getIntVal();
    }


    public DataEntry getDataEntry(String entry){
        return data.get(entry);
    }
    public int getId() {
        return id;
    }

    public boolean hasPerformanceChanged() {
        return difference != null && !difference.isEmpty() && difference.get(Columns.PP) != null;
    }
}
