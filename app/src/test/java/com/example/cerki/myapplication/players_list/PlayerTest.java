package com.example.cerki.myapplication.players_list;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by cerki on 08-Nov-17.
 */
@RunWith(JUnit4.class)
public class PlayerTest {
    public static final String FAKE_RANK = "#44";
    public static final String FAKE_PP = "14000pp";
    public static final String FAKE_COUNTRY = "FR";
    public static final String FAKE_USERNAME = "C";
    public static final int FAKE_PP_INT = 14000;
    public static final int FAKE_RANK_INT = 44;
    public static final String FAKE_ACC = "44.33%";
    private static final float FAKE_ACC_FLOAT = (float)44.33;
    Player mPlayer;
    @Before
    public void setUp() throws Exception {
        mPlayer = new Player();
    }

    @Test
    public void string_insertion_conversion_test(){
        insertFakeData();
    assertEquals(FAKE_PP_INT,mPlayer.getPp());
    assertEquals(FAKE_COUNTRY,mPlayer.getCountry());
    assertEquals(FAKE_RANK_INT,mPlayer.getRank());
    assertEquals(FAKE_ACC_FLOAT,mPlayer.getAcc(),0);
}

    private void insertFakeData() {
        mPlayer.setRank(FAKE_RANK);
        mPlayer.setPp(FAKE_PP);
        mPlayer.setCountry(FAKE_COUNTRY);
        mPlayer.setAcc(FAKE_ACC);
        mPlayer.setUsername(FAKE_USERNAME);
    }

    @Test
    public void getters_should_return_strings(){
        insertFakeData();
        String pp = mPlayer.getPpString();
        String rank = mPlayer.getRankString();
        String acc = mPlayer.getAccString();
        assertEquals(FAKE_RANK,rank);
        assertEquals(FAKE_PP,pp);
        assertEquals(FAKE_ACC,acc);
    }
//    @Test
//    public void test_getting_differences(){
//        float acc_diff = mPlayer.getAccDiff();
//        int pp_diff = mPlayer.getPpDiff();
//        assertEquals(0.4,acc_diff);
//        assertEquals(0.3,acc_diff);
//
//    }
}