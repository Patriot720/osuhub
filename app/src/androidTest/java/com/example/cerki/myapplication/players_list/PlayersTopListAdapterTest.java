package com.example.cerki.myapplication.players_list;

import android.graphics.Bitmap;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class PlayersTopListAdapterTest {
    private PlayersTopListAdapter mAdapter;
    private TextView mTextView;
    private ImageView mArrow;
    @Before
    public void setUp() throws Exception {
        mArrow = new ImageView(getTargetContext());
        mTextView = new TextView(getTargetContext());
        mAdapter = new PlayersTopListAdapter(getTargetContext());
    }


    @Test
    public void setDifference() throws Exception {
        mArrow.setVisibility(View.INVISIBLE);
        mTextView.setVisibility(View.INVISIBLE);
        mAdapter.setDiff(mTextView,mArrow, new PlayerDataEntry(54));
        assertEquals(mTextView.getText(),"54");
        assertEquals(mTextView.getVisibility(),View.VISIBLE);
        assertEquals(mArrow.getVisibility(),View.VISIBLE);
    }
    @Test
    public void setDifferenceWithNegativeValueShouldReturnAbsoluteString(){
    mAdapter.setDiff(mTextView,null, new PlayerDataEntry(-23));
    assertEquals(mTextView.getText(),"23");
    }
    public void setDifferenceWithNullValuesShouldDoNothing(){
        mAdapter.setDiff(null,null,null);
    }
}