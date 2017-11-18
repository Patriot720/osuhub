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
public class PlayersTopListAdapterTest {
    private static final Context mContext = getTargetContext();
    public static final String DEFAULT_PP = mContext.getString(R.string.default_pp);
    public static final String DEFAULT_ACC = mContext.getString(R.string.default_acc);
    public static final String DEFAULT_RANK = mContext.getString(R.string.default_rank);
    public static final String DEFAULT_USERNAME = mContext.getString(R.string.default_username);
    PlayersTopListAdapter mAdapter;

}