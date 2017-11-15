package com.example.cerki.myapplication.players_list;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.runner.AndroidJUnit4;

import com.example.cerki.myapplication.db.Osudb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.HashMap;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static com.example.cerki.myapplication.TestHelper.getFakePlayer;
import static org.junit.Assert.*;

/**
 * Created by cerki on 13-Nov-17.
 */
@RunWith(AndroidJUnit4.class)
public class PlayerTest {
    Player mPlayer;
    @Test
    public void get() throws Exception {
    }

    @Test
    public void set() throws Exception {
    }

    @Test
    public void generateContentValues() throws Exception {
    }

    @Test
    public void compare() throws Exception {
        Player fakePlayer = getFakePlayer();
        Player fakePlayer1 = getFakePlayer(2);
        HashMap<String,Double> hashMap = fakePlayer1.compare(fakePlayer);
        Collection<Double> values = hashMap.values();
        for(Double val : values) {
            assertEquals((double)1000, val,0);
        }

    }

    @Test
    public void setConversionToDouble() throws Exception {
       mPlayer.set("pp","14000pp");
        Double pp = mPlayer.get("pp");
        assertEquals(14000, pp,0);
    }

    @Before
    public void setUp() throws Exception {
        mPlayer = new Player();
    }
}