package com.example.cerki.myapplication.db

import android.database.Cursor

/**
 * Created by cerki on 15-Nov-17.
 */
package com.example.cerki.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.system.Os;

import com.example.cerki.myapplication.players_list.Player;

import java.util.HashMap;

import static com.example.cerki.myapplication.players_list.Player.*;

/**
 * Created by cerki on 12-Nov-17.
 */

public class Osudb extends SQLiteOpenHelper{
    public static final String PLAYERS_TABLE_NAME = "players";



    public static final String PLAYERS_TABLE_CREATE = "CREATE TABLE " + PLAYERS_TABLE_NAME + "("
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_ID + " NUMBER PRIMARY KEY,"
            + COLUMN_PP + " NUMBER,"
            + COLUMN_PC + " NUMBER,"
            + COLUMN_ACC +  " FLOAT, "
            + COLUMN_RANK + " NUMBER,"
            + COLUMN_COUNTRY + " TEXT)";
    public static final String DATABASE_NAME = "Osudb";
    public static final int DATABASE_VERSION = 1;

    public Osudb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
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
       getWritableDatabase().insertOrThrow(Osudb.PLAYERS_TABLE_NAME,null,cv);

    }
    public HashMap<String,Double> compare(Player player) {
        String selection = COLUMN_ID + "=" + player.get(COLUMN_ID);
        Cursor query = getReadableDatabase().query(PLAYERS_TABLE_NAME, null, selection, null, null, null, null);
        if(!query.moveToNext())
            return null;
        return player.compare(new Player(query));
    }
}