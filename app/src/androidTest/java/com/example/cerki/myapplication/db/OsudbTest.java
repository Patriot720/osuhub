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
    public void test_player_update() throws Exception{
        Player fakePlayer = TestHelper.getFakePlayer();
        osuDb.insertPlayer(fakePlayer);
        Player player = TestHelper.getFakePlayer(2);
        osuDb.insertPlayer(player);
        Player osuDbPlayer = osuDb.getPlayer(player);
        assertEquals(osuDbPlayer.getUsername(),"username");
        assertEquals(osuDbPlayer.get("pp"),2000,0);

    }
    @Test
    public void test_player_update_with_random_ids(){
        Player fakePlayer = TestHelper.getFakePlayer();
        fakePlayer.setId("2558286");
        osuDb.insertPlayer(fakePlayer);
        Player fakePlayer1 = TestHelper.getFakePlayer(2);
        fakePlayer1.setId("4650315");
        osuDb.insertPlayer(fakePlayer1);
        Player p = osuDb.getPlayer(fakePlayer);
        Player p2 = osuDb.getPlayer(fakePlayer1);
        assertEquals(2000,p2.get("pp"),0);
        assertEquals(2000,p2.get("acc"),0);
        assertEquals(1000,p.get("pp"),0);
        assertEquals(1000,p.get("rank"),0);
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
    @Test
    public void test_get_method_with_empty_player(){
        Player player = osuDb.getPlayer(new Player());
        assertEquals(null,player);
    }
    @Test
    public  void test_compare_with_empty_player(){
        HashMap<String,Double> hashMap = osuDb.compare(new Player());
        assertTrue(hashMap.isEmpty());
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