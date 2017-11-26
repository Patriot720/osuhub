package com.example.cerki.myapplication.PlayersList;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.PlayersList.Tasks.ListTask;
import com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners.ListTaskListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

public class PlayersTopTaskTest {

    private ListAdapter mAdapter;
    private ListTask mTask;
    private ArrayList<Player> mPlayer;
    private SwipeRefreshLayout mRefresh;

    @Before
    public void setUp() throws Exception {
        mAdapter = new ListAdapter(getTargetContext());
        mRefresh = new SwipeRefreshLayout(getTargetContext());
        ListTaskListener listener = new ListTaskListener(mRefresh, mAdapter);
        mTask = new ListTask(listener);
        mPlayer = new ArrayList<>();
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
        mRefresh.setRefreshing(true);
        mTask.onPostExecute(mPlayer);
        mRefresh.setRefreshing(true);
        mTask.onPostExecute(mPlayer);
        assertEquals(1,mAdapter.getCount());
    }
}