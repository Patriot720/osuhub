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


    @Test
    public void test_db_creation() throws Exception {
        Cursor query = mDb.query(Osudb.PLAYERS_TABLE_NAME, null, null, null, null, null, null);
        assertEquals(query.getCount(),4);
    }
}