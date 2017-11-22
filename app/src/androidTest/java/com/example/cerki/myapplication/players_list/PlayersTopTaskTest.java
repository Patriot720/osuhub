package com.example.cerki.myapplication.players_list;

import android.support.v4.widget.SwipeRefreshLayout;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Created by cerki on 22-Nov-17.
 */
public class PlayersTopTaskTest {

    private PlayersTopListAdapter mAdapter;
    private PlayersTopTask mTask;
    private ArrayList<Player> mPlayer;
    private SwipeRefreshLayout mRefresh;

    @Before
    public void setUp() throws Exception {
        mAdapter = new PlayersTopListAdapter(getTargetContext());
        mTask = new PlayersTopTask(mAdapter);
        mPlayer = new ArrayList<>();
        mRefresh = new SwipeRefreshLayout(getTargetContext());
        mPlayer.add(new Player(0));
    }

    @Test
    public void onPostExecute() throws Exception {
        mTask.onPostExecute(mPlayer);
        assertEquals(1,mAdapter.getCount());
        mTask.onPostExecute(mPlayer);
        assertEquals(2,mAdapter.getCount());
    }
    @Test
    public void onPostExecuteWithRefresh() throws Exception{
        mTask = new PlayersTopTask(mAdapter,mRefresh);
        mTask.onPostExecute(mPlayer);
        mTask.onPostExecute(mPlayer);
        assertEquals(1,mAdapter.getCount());
    }
}