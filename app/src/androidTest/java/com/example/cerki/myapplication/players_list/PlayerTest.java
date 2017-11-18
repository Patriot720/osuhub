package com.example.cerki.myapplication.players_list;

import android.content.ContentValues;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.HashMap;

import static com.example.cerki.myapplication.TestHelper.getFakePlayer;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PlayerTest {
    private Player mPlayer;
    @Test
    public void get() throws Exception {
        assertEquals(1000,mPlayer.getComparable("pp"),0);
    }

    @Test
    public void set() throws Exception {
        mPlayer.set("pp", (double) 24);
        assertEquals(24,mPlayer.getComparable("pp"),0);
    }

    @Test
    public void generateContentValues() throws Exception {
        ContentValues values = mPlayer.generateContentValues();
        assertEquals(7,values.size());
        assertEquals(1000, (Double) values.get("pp"),0);
        assertEquals(1000, (Double) values.get("acc"),0);
        assertEquals(1000, (Double) values.get("rank"),0);
        assertEquals("username",values.get("username"));
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
        Double pp = mPlayer.getComparable("pp");
        assertEquals(14000, pp,0);
    }

    @Before
    public void setUp() throws Exception {
        mPlayer = getFakePlayer();
    }
}