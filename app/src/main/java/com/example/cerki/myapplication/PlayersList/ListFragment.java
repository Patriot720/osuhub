package com.example.cerki.myapplication.PlayersList;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.cerki.myapplication.Osudb;
import com.example.cerki.myapplication.Player.Columns;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.PlayerActivity;
import com.example.cerki.myapplication.R;

import java.net.URL;
import java.util.List;


public class ListFragment extends Fragment {
    public static final String REQUEST_URL = "https://osu.ppy.sh/rankings/osu/performance";
    private ListAdapter mAdapter;

    public ListFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.players_top_layout, container, false);
        ListView list = view.findViewById(R.id.players_top);
        mAdapter = new ListAdapter(getActivity().getApplicationContext());
        list.setAdapter(mAdapter);
        View inflate = View.inflate(getActivity().getApplicationContext(),R.layout.top_list_header,null);
        list.addHeaderView(inflate);
        list.setOnScrollListener(new EndlessScrollListener(list,mAdapter));
        setOnRefreshListener(view);
        setListOnClickListener(list);
        setListOnLongClickListener(list);
        ProgressBar bar = view.findViewById(R.id.listProgressBar);
        new DatabaseTask(bar,list).execute();
        return view;
    }
     class DatabaseTask extends AsyncTask<Void, Void, List<Player>> {
        ProgressBar mBar;
        ListView mList;
        DatabaseTask(ProgressBar bar,ListView list){
           mBar = bar;
           mList = list;
        }
        @Override
        protected List<Player> doInBackground(Void... voids) {
            try {
                return Osudb.getInstance().getAll();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Player> players) {
            mBar.setVisibility(View.GONE);
            mList.setVisibility(View.VISIBLE);
            mAdapter.addAll(players);
            new ListTask(mAdapter,new SwipeRefreshLayout(getContext())).execute(REQUEST_URL);
        }
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
                Player p = (Player) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra(Columns.ID,p.getId());
                getActivity().startActivity(intent);

            }
        });
    }

    private void setOnRefreshListener(View view) {
        final SwipeRefreshLayout refresh = view.findViewById(R.id.players_top_refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ListTask playersTopTask = new ListTask(mAdapter, refresh);
                playersTopTask.execute(REQUEST_URL);
            }
        });
    }

}
