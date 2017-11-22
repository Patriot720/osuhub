package com.example.cerki.myapplication.players_list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

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
        list.setOnScrollListener(new EndlessScrollListener(mAdapter));
        setOnRefreshListener(view);
        setListOnClickListener(list);
        setListOnLongClickListener(list);
        return view;
    }


    private void setListOnLongClickListener(ListView list) {
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return true;
            }
        });
    }

    private void setListOnClickListener(ListView list) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animation animation = new AlphaAnimation(.3f,1.0f);
                animation.setDuration(300);
                view.startAnimation(animation);
            }
        });
    }

    private void setOnRefreshListener(View view) {
        final SwipeRefreshLayout refresh = view.findViewById(R.id.players_top_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PlayersTopTask playersTopTask = new PlayersTopTask(mAdapter, refresh);
                playersTopTask.execute(REQUEST_URL);
            }
        });
    }

}
