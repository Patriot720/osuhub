package com.example.cerki.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cerki.myapplication.players_list.Player;

import java.util.HashMap;

import static com.example.cerki.myapplication.players_list.Player.*;


public class Osudb extends SQLiteOpenHelper{
    public static final String PLAYERS_TABLE_NAME = "players";



    public static final String PLAYERS_TABLE_CREATE = "CREATE TABLE " + PLAYERS_TABLE_NAME + "("
            + COLUMN_ID + " NUMBER PRIMARY KEY,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_COUNTRY + " TEXT,"
            + COLUMN_PP + " NUMBER,"
            + COLUMN_PC + " NUMBER,"
            + COLUMN_ACC +  " FLOAT,"
            + COLUMN_RANK + " NUMBER)";
    static final String DATABASE_NAME = "Osudb";
    public static final int DATABASE_VERSION = 1;

    public Osudb(Context targetContext) {
            super(targetContext,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PLAYERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
    }
    public void insertPlayer(Player p){
        ContentValues cv = p.generateContentValues();
       getWritableDatabase().replaceOrThrow(Osudb.PLAYERS_TABLE_NAME,null,cv);

    }
    public Player getPlayer(Player player){
        String selection = COLUMN_ID + "=" + player.getId();
        Cursor query = getReadableDatabase().query(PLAYERS_TABLE_NAME, null, selection, null, null, null, null);
        if(!query.moveToFirst())
            return null;
        return new Player(query);
    }
    public HashMap<String,Double> compare(Player player) {
        Player p = getPlayer(player);
        if(p == null)
            return new HashMap<>();
        return player.compare(p);
    }
}
