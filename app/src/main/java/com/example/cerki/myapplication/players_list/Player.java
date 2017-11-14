package com.example.cerki.myapplication.players_list;

import android.content.ContentValues;

import static com.example.cerki.myapplication.db.Osudb.COLUMN_ACC;
import static com.example.cerki.myapplication.db.Osudb.COLUMN_COUNTRY;
import static com.example.cerki.myapplication.db.Osudb.COLUMN_ID;
import static com.example.cerki.myapplication.db.Osudb.COLUMN_PC;
import static com.example.cerki.myapplication.db.Osudb.COLUMN_PP;
import static com.example.cerki.myapplication.db.Osudb.COLUMN_RANK;
import static com.example.cerki.myapplication.db.Osudb.COLUMN_USERNAME;

/**
 * Created by cerki on 08-Nov-17.
 */

public class Player {
    private int rank;
    private String country;
    private int pp;
    private float acc;
    private String username;
    private String ppString;
    private int id;
    private ContentValues difference;
    private int pc;

    public Player(String username, String country, int rank, int pp, float acc, int id) {
        this(username,country,rank,pp,acc);
        this.id = id;
    }

    public Player(String username,String country, int rank, int pp, float acc, int id, int pc){
        this(username,country,rank,pp,acc,id);
        this.pc = pc;
    }
    public Player(String username, String country, int rank, int pp, float acc) {
        this.username = username;
        this.rank = rank;
        this.acc = acc;
        this.pp = pp;
        this.country = country;
    }

    Player() {
    }
    public ContentValues generateContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME,username);
        cv.put(COLUMN_PP,pp);
        cv.put(COLUMN_RANK,rank);
        cv.put(COLUMN_ACC,acc);
        cv.put(COLUMN_COUNTRY,country);
        cv.put(COLUMN_ID,id);
        cv.put(COLUMN_PC,pc);
        return cv;
    }
    public ContentValues getDifference() {
        return difference;
    }

    public void setDifference(ContentValues difference) {
        this.difference = difference;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = getInt(id);
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPp() {
        return pp;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }
    public void setPp(String pp){
        this.pp = getInt(pp);
    }

    public float getAcc() {
        return acc;
    }

    public void setAcc(float acc) {
        this.acc = acc;
    }
    public void setAcc(String acc){
        this.acc = Float.parseFloat(acc.replaceAll("[^0-9.]",""));
    }

    public void setRank(String rank) {
        this.rank = getInt(rank);
    }

    public int getRank() {
        return rank;
    }

    public String getAccString() {
        return String.valueOf(this.acc);
    }

    public String getRankString() {
        return String.valueOf(rank);
    }

    public String getPpString() {
        return String.valueOf(pp);
    }
    private int getInt(String item){
       return  Integer.parseInt(item.replaceAll("[^0-9]",""));
    }
    public void setPc(String pc){
        this.pc = getInt(pc);
    }
    public int getPc() {
        return pc;
    }
}
