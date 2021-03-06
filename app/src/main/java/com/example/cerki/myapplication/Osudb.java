package com.example.cerki.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cerki.myapplication.Player.Columns;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.Player.DataEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Osudb extends SQLiteOpenHelper{
    public static final String PLAYERS_TABLE_NAME = "players";
    public static Osudb osudb;




    public static final String PLAYERS_TABLE_CREATE = "CREATE TABLE " + PLAYERS_TABLE_NAME + "("
            + Columns.ID + " NUMBER PRIMARY KEY,"
            + Columns.USERNAME + " TEXT,"
            + Columns.COUNTRY + " TEXT,"
            + Columns.ACTIVITY + " TEXT,"
            + Columns.PP + " NUMBER,"
            + Columns.PC + " NUMBER,"
            + Columns.ACC +  " FLOAT,"
            + Columns.RANK + " NUMBER)";
    static final String DATABASE_NAME = "Osudb";
    public static final int DATABASE_VERSION = 1;

    protected Osudb(Context targetContext) {
            super(targetContext,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public static Osudb getInstance(Context context){
       if(osudb != null)
           return osudb;
       else{
           osudb = new Osudb(context);
           return osudb;
       }
    }
    public static Osudb getInstance() throws InstantiationException {
        if(osudb == null)
            throw new InstantiationException("Not yet instantiated");
        return osudb;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PLAYERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
    }
    public void insertOrUpdate(Player player){
        ContentValues values = player.generateContentValues();
        getWritableDatabase().replaceOrThrow(PLAYERS_TABLE_NAME,null,values);
    }
    public Player getPlayer(Player player){
        String selection = Columns.ID + "=" + player.getId();
        Cursor query = getReadableDatabase().query(PLAYERS_TABLE_NAME, null, selection, null, null, null, null);
        if(!query.moveToFirst()) {
            query.close();
            return null;
        }
        return new Player(query);
    }
    public HashMap<String,DataEntry> compare(Player player) {
        Player p = getPlayer(player);
        if(p == null)
            return new HashMap<>();
        return player.data.compare(p.data);
    }

    public List<Player> getAll() {
        Cursor query = getWritableDatabase().query(
                PLAYERS_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Columns.RANK + " ASC",
                "50"
        );
        ArrayList<Player> list = new ArrayList<>();
        if(query.moveToFirst())
            do {
            list.add(new Player(query));
            }while(query.moveToNext());
        query.close();
        return list;
    }
}
