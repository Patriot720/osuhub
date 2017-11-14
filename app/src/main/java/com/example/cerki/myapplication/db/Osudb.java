package com.example.cerki.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.system.Os;

import com.example.cerki.myapplication.players_list.Player;

/**
 * Created by cerki on 12-Nov-17.
 */

public class Osudb extends SQLiteOpenHelper{
    public static final String PLAYERS_TABLE_NAME = "players";

    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PP = "pp";
    public static final String COLUMN_ACC = "acc";
    public static final String COLUMN_RANK = "rank";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PC = "pc";

    public static final String PLAYERS_TABLE_CREATE = "CREATE TABLE " + PLAYERS_TABLE_NAME + "("
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PP + " NUMBER,"
            + COLUMN_PC + " NUMBER,"
            + COLUMN_ACC +  " FLOAT, "
            + COLUMN_RANK + " NUMBER,"
            + COLUMN_ID + " NUMBER,"
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
    public ContentValues compare(Player player) {
        String selection = COLUMN_ID + "=" + player.getId();
        Cursor query = getReadableDatabase().query(PLAYERS_TABLE_NAME, null, selection, null, null, null, null);
        ContentValues cv = new ContentValues();
        if(!query.moveToNext())
            return cv;
        float acc_diff = player.getAcc() -  query.getFloat(query.getColumnIndex(COLUMN_ACC));
        int pp_diff = player.getPp() - query.getInt(query.getColumnIndex(COLUMN_PP));
        int rank_diff =query.getInt(query.getColumnIndex(COLUMN_RANK)) - player.getRank();
        int pc_diff = player.getPc() - query.getInt(query.getColumnIndex(COLUMN_PC));
        cv.put(COLUMN_PC,pc_diff);
        cv.put(COLUMN_ACC,acc_diff);
        cv.put(COLUMN_PP,pp_diff);
        cv.put(COLUMN_RANK,rank_diff);
        return cv;
    }
}
