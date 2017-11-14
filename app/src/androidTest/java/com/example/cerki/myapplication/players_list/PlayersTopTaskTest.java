package com.example.cerki.myapplication.players_list;

import android.content.ContentValues;
import android.support.test.runner.AndroidJUnit4;

import com.example.cerki.myapplication.db.Osudb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Created by cerki on 10-Nov-17.
 */
@RunWith(AndroidJUnit4.class)
public class PlayersTopTaskTest {

    private PlayersTopTask mTask;

    private void assertPlayer(Player player) {
        assertTrue(player.getAcc() > 0);
        assertTrue(player.getPp() > 0);
        assertTrue(player.getRank() > 0);
        assertTrue(player.getId() > 0);
    }
    
    @Before
    public void setUp() throws Exception {
        mTask = new PlayersTopTask(getTargetContext());
    }

    @Test
    public void test_parsing() throws Exception {
        List<Player> players = mTask.execute(PlayersTopFragment.REQUEST_URL).get();
        Player player = players.get(0);
        assertPlayer(player);
    }


}