package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.example.cerki.myapplication.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;


/**
 * Created by cerki on 08-Nov-17.
 */
@RunWith(AndroidJUnit4.class)
public class PlayersTopListAdapterTest {
    private static final Context mContext = getTargetContext();
    public static final String DEFAULT_PP = mContext.getString(R.string.default_pp);
    public static final String DEFAULT_ACC = mContext.getString(R.string.default_acc);
    public static final String DEFAULT_RANK = mContext.getString(R.string.default_rank);
    public static final String DEFAULT_USERNAME = mContext.getString(R.string.default_username);
    PlayersTopListAdapter mAdapter;

    @Before
    public void setUp() throws Exception {
        mAdapter = new PlayersTopListAdapter(getTargetContext(), R.layout.players_top_item);
    }

    @Test
    public void get_view_test(){
        addFakePlayersToAdapter();
        View view = mAdapter.getView(0, null, null);
        TextView pp = view.findViewById(R.id.item_pp);
        TextView acc = view.findViewById(R.id.item_acc);
        TextView rank = view.findViewById(R.id.item_rank);
        TextView username = view.findViewById(R.id.item_username);
        assertNotEquals(DEFAULT_PP,pp.getText());
        assertNotEquals(DEFAULT_ACC,acc.getText());
        assertNotEquals(DEFAULT_RANK,rank.getText());
        assertNotEquals(DEFAULT_USERNAME,username.getText());
    }

    private void addFakePlayersToAdapter() {
        for (int i = 0; i < 100; i++) {
            Player p = new Player();
            mAdapter.add(p);
        }
    }

}