package com.example.cerki.myapplication.PlayersList;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.cerki.myapplication.Player.Columns;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.PlayerActivity;
import com.example.cerki.myapplication.PlayersList.Tasks.ListTask;
import com.example.cerki.myapplication.PlayersList.Tasks.PlayersTask;
import com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners.ListTaskListener;
import com.example.cerki.myapplication.R;


public class ListFragment extends Fragment {
    private ListAdapter mAdapter;
    private ListView mList;
    private LayoutInflater mInflater;


    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.players_top_layout, container, false);
        mList = view.findViewById(R.id.players_top);
        mAdapter = new ListAdapter(getActivity().getApplicationContext());
        mList.setAdapter(mAdapter);
        addHeaderView();
        addFooterProgressBar();
        setOnRefreshListener(view);
        setListOnClickListener();
        setListOnLongClickListener();
        setOnListEndListener();
        new ListTask(new ListTaskListener(mAdapter)).loadPlayers();
        return view;
    }

    private void setOnListEndListener() {
        mList.setOnScrollListener(new EndlessScrollListener(new ListTaskListener(mAdapter)));
    }

    private void addHeaderView(){
        View header = mInflater.inflate(R.layout.top_list_header, null, false);
        mList.addHeaderView(header);
    }
    private void addFooterProgressBar() {
        View bar = mInflater.inflate(R.layout.progress_bar, null, false);
        mList.addFooterView(bar);
    }


    private void setListOnLongClickListener() {
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return true;
            }
        });
    }

    private void setListOnClickListener() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Player p = (Player) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra(Columns.ID, p.getId());
                getActivity().startActivity(intent);
            }
        });
    }

    private void setOnRefreshListener(View view) {
        final SwipeRefreshLayout refresh = view.findViewById(R.id.players_top_refresh);
        final ListTaskListener listTaskListener = new ListTaskListener(refresh, mAdapter);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ListTask(listTaskListener).loadPlayers();
            }
        });
    }

}
