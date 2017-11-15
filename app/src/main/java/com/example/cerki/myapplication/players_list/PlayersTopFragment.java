package com.example.cerki.myapplication.players_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cerki.myapplication.R;

/**
 * Created by cerki on 08-Nov-17.
 */

public class PlayersTopFragment extends Fragment {
    public static final String REQUEST_URL = "https://osu.ppy.sh/rankings/osu/performance";
    private ArrayAdapter mAdapter;

    public PlayersTopFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.players_top_layout, container, false);
        ListView list = view.findViewById(R.id.players_top);
        mAdapter = new PlayersTopListAdapter(getActivity().getApplicationContext(),R.layout.players_top_item);
        list.setAdapter(mAdapter);
        setListOnRefreshListener(view);
        return view;
    }

    private void setListOnRefreshListener(View view) {
        final SwipeRefreshLayout refresh = view.findViewById(R.id.players_top_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new PlayersTopTask(getActivity().getApplicationContext(),mAdapter,refresh).execute(REQUEST_URL);
            }
        });
    }

}
