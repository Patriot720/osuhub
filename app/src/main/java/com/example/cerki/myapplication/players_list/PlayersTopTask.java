package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import static com.example.cerki.myapplication.players_list.PlayerParser.parse;


class PlayersTopTask extends AsyncTask<String,Void,List<Player>> {
    private SwipeRefreshLayout mRefresh;
    private ArrayAdapter mAdapter;

    public PlayersTopTask(Context context, ArrayAdapter adapter, SwipeRefreshLayout refresh) {
       mAdapter = adapter;
       mRefresh = refresh;
    }
    public PlayersTopTask(Context context, ArrayAdapter adapter){
        mAdapter = adapter;
    }
    public PlayersTopTask(ArrayAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public PlayersTopTask(Context targetContext) {
    }

    @Override
    protected List<Player> doInBackground(String... strings) {
        List<Player> players;
        String url = strings[0];
        try {
            Document doc = Jsoup.connect(url).get();
            players  = parse(doc);
            return players;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(List<Player> players) {
        if((mAdapter != null) && (mRefresh != null)) {
            mAdapter.addAll(players);
            mRefresh.setRefreshing(false);
        }
        super.onPostExecute(players);
    }
}
