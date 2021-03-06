package com.example.cerki.myapplication;

import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.Player.DataEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static com.example.cerki.myapplication.TestHelper.*;
import static org.junit.Assert.*;

public class OsudbTest {
    private Osudb osuDb;
    private Player mFakePlayer1;
    private Player mFakePlayer2;

    @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(Osudb.DATABASE_NAME);
        osuDb = new Osudb(getTargetContext());
        mFakePlayer1 = getFakePlayer();
        mFakePlayer2 = getFakePlayer(2);
    }

    @After
    public void tearDown() throws Exception {
        getTargetContext().deleteDatabase(Osudb.DATABASE_NAME);

    }
    @Test
    public void test_player_update() throws Exception{
        osuDb.insertOrUpdate(mFakePlayer1);
        osuDb.insertOrUpdate(mFakePlayer2);
        Player osuDbPlayer = osuDb.getPlayer(mFakePlayer2);
        assertPlayer(osuDbPlayer,2);

    }
    @Test
    public void test_player_update_with_random_ids(){
        mFakePlayer1.setId("2558286");
        osuDb.insertOrUpdate(mFakePlayer1);
        mFakePlayer2.setId("4650315");
        osuDb.insertOrUpdate(mFakePlayer2);
        Player p = osuDb.getPlayer(mFakePlayer1);
        Player p2 = osuDb.getPlayer(mFakePlayer2);
        assertPlayer(p2,2);
        assertPlayer(p);
    }
    @Test
    public void test_player_insertion() throws Exception {
       osuDb.insertOrUpdate(mFakePlayer1);
       Player p = osuDb.getPlayer(mFakePlayer1);
       assertPlayer(p);
    }
    @Test
    public void try_to_get_player_that_is_not_in_db(){
        Player player = osuDb.getPlayer(new Player(0));
        assertEquals(null,player);
    }
    @Test
    public  void test_compare_empty_player(){
        HashMap<String, DataEntry> hashMap = osuDb.compare(new Player(0));
        assertTrue(hashMap.isEmpty());
    }

    @Test
    public void compare_player1_from_db_to_new_player2(){
        osuDb.insertOrUpdate(mFakePlayer1);
        HashMap<String, DataEntry> comparison = osuDb.compare(mFakePlayer2);
        assertEquals("1000",comparison.get("pp").getAsString());
        assertEquals("1000",comparison.get("acc").getAsString());
        assertEquals("1000",comparison.get("rank").getAsString());
    }

    @Test
    public void getPlayersFromDbSortedByRank() throws Exception {
        Player fakePlayer2 = getFakePlayer();
        Player fakePlayer1 = getFakePlayer();
        Player fakePlayer = getFakePlayer();
        fakePlayer.setId(2);
        fakePlayer1.setId(3);
        fakePlayer2.setId(4);
        osuDb.insertOrUpdate(fakePlayer);
        osuDb.insertOrUpdate(fakePlayer1);
        osuDb.insertOrUpdate(fakePlayer2);
        List<Player> playerList = osuDb.getAll();
        assertEquals(3,playerList.size());
        for(Player p : playerList)
            assertPlayer(p);
    }
}