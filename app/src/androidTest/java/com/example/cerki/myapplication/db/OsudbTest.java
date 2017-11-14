package com.example.cerki.myapplication.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cerki.myapplication.players_list.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Created by cerki on 12-Nov-17.
 */
public class OsudbTest {
    private SQLiteDatabase mDb;

    @Before
    public void setUp() throws Exception {
        Osudb db = new Osudb(getTargetContext());
        mDb = db.getWritableDatabase();
    }

    @After
    public void tearDown() throws Exception {
        getTargetContext().deleteDatabase(Osudb.DATABASE_NAME);
    }

    private void insertFakeData(){
        ContentValues cv = new ContentValues();
        cv.put(Osudb.COLUMN_ACC,(float) 25.48);
        cv.put(Osudb.COLUMN_COUNTRY,"FR");
        cv.put(Osudb.COLUMN_PP,14880);
        cv.put(Osudb.COLUMN_RANK,25);
        cv.put(Osudb.COLUMN_USERNAME,"Cookiezi");
        mDb.insertOrThrow(Osudb.PLAYERS_TABLE_NAME,null,cv);
        cv.put(Osudb.COLUMN_ACC,(float) 43.48);
        cv.put(Osudb.COLUMN_COUNTRY,"US");
        cv.put(Osudb.COLUMN_PP,23);
        cv.put(Osudb.COLUMN_RANK,2430);
        cv.put(Osudb.COLUMN_USERNAME,"Patriot720");
        mDb.insertOrThrow(Osudb.PLAYERS_TABLE_NAME,null,cv);
        cv.put(Osudb.COLUMN_ACC,(float) 44.34);
        cv.put(Osudb.COLUMN_COUNTRY,"dansGame");
        cv.put(Osudb.COLUMN_PP,3443342);
        cv.put(Osudb.COLUMN_RANK,3434);
        cv.put(Osudb.COLUMN_USERNAME,"djfkj");
        mDb.insertOrThrow(Osudb.PLAYERS_TABLE_NAME,null,cv);
        cv.put(Osudb.COLUMN_ACC,(float) 25.48);
        cv.put(Osudb.COLUMN_COUNTRY,"FR");
        cv.put(Osudb.COLUMN_PP,14880);
        cv.put(Osudb.COLUMN_RANK,25);
        cv.put(Osudb.COLUMN_USERNAME,"Cookiezi");
        mDb.insertOrThrow(Osudb.PLAYERS_TABLE_NAME,null,cv);

    }

    @Test
    public void test_db_insert_method() throws Exception {
        Osudb db = new Osudb(getTargetContext());
        Player player = new Player("C", "FR", 32, 44, 55,43254);
        db.insertPlayer(player);
        SQLiteDatabase readableDatabase = db.getReadableDatabase();
        Cursor query = readableDatabase.query(Osudb.PLAYERS_TABLE_NAME, null, null, null, null, null, null);
        assertEquals(1,query.getCount());
        query.moveToNext();
        query.getFloat(query.getColumnIndex(Osudb.COLUMN_ACC));
        int pp = query.getInt(query.getColumnIndex(Osudb.COLUMN_PP));
        int rank = query.getInt(query.getColumnIndex(Osudb.COLUMN_RANK));
        int id = query.getInt(query.getColumnIndex(Osudb.COLUMN_ID));
        assertEquals(44,pp);
        assertEquals(32,rank);
        assertEquals(43254,id);

        }


    @Test
    public void test_db_creation() throws Exception {
        insertFakeData();
        Cursor query = mDb.query(Osudb.PLAYERS_TABLE_NAME, null, null, null, null, null, null);
        assertEquals(query.getCount(),4);
    }    @Test
    
    public void should_get_differences_from_db() throws Exception {
        Osudb db = new Osudb(getTargetContext());
        db.insertPlayer(new Player("C","FR",25,44, (float) 33.48,23,2000));
        ContentValues diff = db.compare(new Player("C","FR",23,453,(float) 33.45,23,3000));
        assertEquals(2,diff.get("rank"));
        assertEquals(409,diff.get("pp"));
        assertEquals((float)-0.03,(float)diff.get("acc"),2);
        assertEquals(1000,diff.get("pc"));

    }
}