package com.example.cerki.myapplication.PlayersList;

import android.support.v4.widget.SwipeRefreshLayout;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.*;

/**
 * Created by cerki on 25-Nov-17.
 */
public class ListFragmentTest {
    @Test
    public void setOnRefreshListener() throws Exception {
        SwipeRefreshLayout swipe = new SwipeRefreshLayout(getTargetContext());
    }
}