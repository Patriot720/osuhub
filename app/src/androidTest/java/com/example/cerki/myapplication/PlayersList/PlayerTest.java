package com.example.cerki.myapplication.PlayersList;

import android.content.ContentValues;
import android.support.test.runner.AndroidJUnit4;


import com.example.cerki.myapplication.Player.Columns;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.Player.DataEntry;

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
        assertEquals("1000",mPlayer.get("pp"));
    }

    @Test
    public void getAsStringWithEmptyValue(){
        String val = new Player(0).get("pp");
        assertEquals("",val);
    }
    @Test
    public void getAsString(){
        String pp = mPlayer.get("pp");
        assertEquals("1000",pp);
    }
    @Test
    public void set() throws Exception {
        mPlayer.set("pp",new DataEntry(24));
        assertEquals("24",mPlayer.get("pp"));
    }

    @Test
    public void generateContentValues() throws Exception {
        ContentValues values = mPlayer.generateContentValues();
        assertEquals(1000,  values.get("pp"));
        assertEquals(1000,  values.get("acc"));
        assertEquals(1000,  values.get("rank"));
        assertEquals("username",values.get("username"));
    }
    @Test
    public void generateContentValuesWithEmptyPlayer(){
        ContentValues values = new Player(0).generateContentValues();
        assertNull(values.get("username"));
        assertEquals(0,values.get(Columns.ID));
        assertNull(values.get("Keepo"));
        assertNull(values.get(Columns.PP));
    }

    @Test
    public void compare() throws Exception {
        Player fakePlayer = getFakePlayer();
        Player fakePlayer1 = getFakePlayer(2);
        HashMap<String, DataEntry> hashMap = fakePlayer1.data.compare(fakePlayer.data);
        Collection<DataEntry> values = hashMap.values();
        for(DataEntry val : values) {
            assertEquals(1000, val.getIntVal());
        }


    }
    @Test
    public void compareWithEmptyPlayer() {
        Player fakePlayer = getFakePlayer();
        Player fakePlayer1 = new Player(0);
        fakePlayer1.setId("25");
        HashMap<String, DataEntry> comparison = fakePlayer.data.compare(fakePlayer1.data);
        assertNull(comparison.get("pp"));
        assertNull(comparison.get("acc"));
    }
    @Test
    public void compareWithSameNumbers(){
        Player p1 = getFakePlayer();
        Player p2 = getFakePlayer();
        HashMap<String, DataEntry> comparison = p1.data.compare(p2.data);
        assertNull(comparison.get("pp"));
        assertNull(comparison.get("acc"));
    }

    @Test
    public void setConversionToDouble() throws Exception {
       mPlayer.set("pp",new DataEntry("14000pp"));
        String pp = mPlayer.get("pp");
        assertEquals("14000", pp);
    }

    @Before
    public void setUp() throws Exception {
        mPlayer = getFakePlayer();
    }
}