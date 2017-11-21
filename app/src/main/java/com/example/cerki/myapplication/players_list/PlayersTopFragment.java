package com.example.cerki.myapplication.players_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.cerki.myapplication.R;


public class PlayersTopFragment extends Fragment {
    public static final String REQUEST_URL = "https://osu.ppy.sh/rankings/osu/performance";
    private PlayersTopListAdapter mAdapter;

    public PlayersTopFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.players_top_layout, container, false);
        ListView list = view.findViewById(R.id.players_top);
        mAdapter = new PlayersTopListAdapter(getActivity().getApplicationContext());
        list.setAdapter(mAdapter);
        View inflate = View.inflate(getActivity().getApplicationContext(),R.layout.top_list_header,null);
        list.addHeaderView(inflate);
        setListOnRefreshListener(view);
        return view;
    }

    private void setListOnRefreshListener(View view) {
        final SwipeRefreshLayout refresh = view.findViewById(R.id.players_top_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PlayersTopTask playersTopTask = new PlayersTopTask(getActivity().getApplicationContext(), mAdapter, refresh);
                playersTopTask.execute(REQUEST_URL);
            }
        });
    }

}
