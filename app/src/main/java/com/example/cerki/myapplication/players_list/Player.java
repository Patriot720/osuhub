package com.example.cerki.myapplication.players_list;

import android.content.ContentValues;
import android.database.Cursor;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;



public class Player {
    private String username;
    private int id;
    private HashMap<String,Double> mPlayerData;
    private HashMap<String,Double> mPlayerDifference;
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PP = "pp";
    public static final String COLUMN_ACC = "acc";
    public static final String COLUMN_RANK = "rank";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PC = "pc";
    private String country;

    public Player(HashMap<String,Double> data){
        this.mPlayerData = data;
    }

    public Player(Cursor query) {
        this();
        int columnCount = query.getColumnCount();
        this.id = query.getInt(0);
        this.username = query.getString(1);
        this.country = query.getString(2);
        for(int i = 3; i < columnCount;i++){
            String columnName = query.getColumnName(i);
            double value = query.getDouble(i);
            this.set(columnName,value);
        }
    }

    public HashMap<String, Double> getPlayerDifference() {
        return mPlayerDifference;
    }

    public String getPlayerDifferenceAsString(String key){
        return get(mPlayerDifference,key);
    }
    public Double getPlayerDifference(String key) {
        return mPlayerDifference.get(key);
    }
    private String get(HashMap<String,Double> data, String key){
        Double val = data.get(key);
        if(val == null)
            return "";
        if(val % 1 == 0){
            return String.valueOf(val.intValue());
        }
        return String.valueOf(val);
    }
    public void setPlayerDifference(HashMap<String, Double> mPlayerDifference) {
        this.mPlayerDifference = mPlayerDifference;
    }

    public Double getComparable(String s){
        return mPlayerData.get(s);
    }
    public String getAsString(String s){
        return get(mPlayerData,s);
    }
    public void set(String key,Double val){
        mPlayerData.put(key,val);
    }
    public  Double get(String key){
        return mPlayerData.get(key);
    }
    public ContentValues generateContentValues(){
        Iterator<String> iterator = getKeysIterator();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID,id);
        if(username != null)
            cv.put(COLUMN_USERNAME,username);
        if(country != null)
            cv.put(COLUMN_COUNTRY,country);
        while(iterator.hasNext()){
            String s= iterator.next();
            cv.put(s, getComparable(s));
        }
        return cv;
    }

    private Iterator<String> getKeysIterator() {
        Set<String> strings = mPlayerData.keySet();
        return strings.iterator();
    }

    public HashMap<String, Double> compare(Player player){
        HashMap<String,Double> difference = new HashMap<>();
        int id = player.getId();
        if(id != this.id)
            return difference;
        Iterator<String> iterator = getKeysIterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            if(Objects.equals(this.getComparable(key), player.getComparable(key)))
                continue;
            Double diff = this.getComparable(key) - player.getComparable(key);
            difference.put(key,diff);
        }
        return difference;
    }
    public void set(String key,String val){
        Double value = Double.parseDouble(val.replaceAll("[^0-9.]",""));
        mPlayerData.put(key,value);
    }
    public Player() {
        mPlayerData = new HashMap<>();
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id.replaceAll("[^0-9]",""));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }
}
