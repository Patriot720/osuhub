package com.example.cerki.myapplication.players_list;

import android.database.sqlite.SQLiteDatabase;

import com.example.cerki.myapplication.db.Osudb;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Created by cerki on 13-Nov-17.
 */
public class PlayerTest {
    private SQLiteDatabase db;
    @Before
    public void setUp() throws Exception {
        db = new Osudb(getTargetContext()).getWritableDatabase();
    }

    public Player getFakePlayer(String acc, String rank, String pp){
        Player player = new Player();
        player.setPp(pp);
        player.setCountry("FR");
        player.setRank(rank);
        player.setAcc(acc);
        player.setUsername("c");
        return player;
    }
    @Test
    public void get_diff_should_return_differnce_from_db_contentValues() throws Exception {
        Player p = getFakePlayer("10.3","24","14000");

    }
}