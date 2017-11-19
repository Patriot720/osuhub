package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cerki.myapplication.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PlayersTopListAdapterTest {
    PlayersTopListAdapter mAdapter;
    @Before
    public void setUp() throws Exception {

        mAdapter = new PlayersTopListAdapter(getTargetContext(),R.layout.players_top_item);
    }

    @Test
    public void test_set_difference() throws Exception {
        TextView m = new TextView(getTargetContext());
        ImageView arrow = new ImageView(getTargetContext());
        arrow.setVisibility(View.INVISIBLE);
        m.setVisibility(View.INVISIBLE);
        mAdapter.setDiff(m,arrow, (double) 54);
        assertEquals(m.getText(),"54");
        assertEquals(m.getVisibility(),View.VISIBLE);
        assertEquals(arrow.getVisibility(),View.VISIBLE);
    }
    @Test
    public void test_set_difference_negative(){
    TextView m = new TextView(getTargetContext());
    mAdapter.setDiff(m,null, (double) -23);
    assertEquals(m.getText(),"23");
    }
    public void test_set_difference_empty(){
        mAdapter.setDiff(null,null,null);
    }
}