package com.example.cerki.myapplication.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.cerki.myapplication.TestHelper;
import com.example.cerki.myapplication.players_list.Columns;
import com.example.cerki.myapplication.players_list.Player;
import com.example.cerki.myapplication.players_list.PlayerDataEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

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
        assertEquals(osuDbPlayer.get(Columns.USERNAME),"username");
        assertEquals(osuDbPlayer.getDataEntry(Columns.PP).getIntVal(),2000);

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
        assertEquals("2000",p2.get(Columns.PP));
        assertEquals("2000",p2.get(Columns.ACC));
        assertEquals("1000",p.get(Columns.PP));
        assertEquals("1000",p.get(Columns.ACC));

    }
    @Test
    public void test_player_insertion() throws Exception {
       Player fakePlayer = TestHelper.getFakePlayer();
       osuDb.insertPlayer(fakePlayer);
       Player p = osuDb.getPlayer(fakePlayer);
       assertEquals(1000,p.getDataEntry("pp").getIntVal());
       assertEquals(1000,p.getDataEntry("acc").getIntVal());
       assertEquals("username",p.get("username"));
       assertEquals(1,p.getId());
    }
    @Test
    public void test_get_method_with_empty_player(){
        Player player = osuDb.getPlayer(new Player(0));
        assertEquals(null,player);
    }
    @Test
    public  void test_compare_with_empty_player(){
        HashMap<String, PlayerDataEntry> hashMap = osuDb.compare(new Player(0));
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
        HashMap<String, PlayerDataEntry> comparison = osuDb.compare(fakePlayer2);
        assertEquals("1000",comparison.get("pp").getAsString());
        assertEquals("1000",comparison.get("acc").getAsString());
        assertEquals("1000",comparison.get("rank").getAsString());
    }


}