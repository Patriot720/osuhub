package com.example.cerki.myapplication.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.cerki.myapplication.TestHelper;
import com.example.cerki.myapplication.players_list.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Created by cerki on 12-Nov-17.
 */
public class OsudbTest {
    private SQLiteDatabase mDb;
    private Osudb osuDb;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(Osudb.DATABASE_NAME);
        Osudb db = new Osudb(getTargetContext());
        osuDb = db;
        mDb = db.getWritableDatabase();
    }

    @Test
    public void test_player_insertion() throws Exception {
       Player fakePlayer = TestHelper.getFakePlayer();
       osuDb.insertPlayer(fakePlayer);
       Player p = osuDb.getPlayer(fakePlayer);
       assertEquals(1000,p.getComparable("pp"),0);
       assertEquals(1000,p.getComparable("acc"),0);
       assertEquals("username",p.getUsername());
       assertEquals(1,p.getId());
    }

    @After
    public void tearDown() throws Exception {
        getTargetContext().deleteDatabase(Osudb.DATABASE_NAME);

    }
    @Test
    public void test_player_comparison(){
        Player fakePlayer1 = TestHelper.getFakePlayer();
        Player fakePlayer2 = TestHelper.getFakePlayer(2);
        osuDb.insertPlayer(fakePlayer1);
        HashMap<String, Double> comparison = osuDb.compare(fakePlayer2);
        assertEquals(1000,comparison.get("pp"),0);
        assertEquals(1000,comparison.get("acc"),0);
        assertEquals(1000,comparison.get("rank"),0);


    }


}