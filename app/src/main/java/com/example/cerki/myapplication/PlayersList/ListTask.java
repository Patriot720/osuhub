package com.example.cerki.myapplication.PlayersList;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;

import com.example.cerki.myapplication.Osudb;
import com.example.cerki.myapplication.Player.Player;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class ListTask extends AsyncTask<String,Void,List<Player>> {
    private SwipeRefreshLayout mRefresh;
    private ArrayAdapter<Player> mAdapter;
    ListTask(ArrayAdapter<Player> adapter, SwipeRefreshLayout refresh) {
       mAdapter = adapter;
       mRefresh = refresh;
    }
    ListTask(ArrayAdapter<Player> adapter){
        mAdapter = adapter;
    }
    @Override
    protected List<Player> doInBackground(String... urls) {
        try {
            Document page = Jsoup.connect(urls[0]).get();
            Elements tbody = page.select("tbody").first().children();
            Osudb db = Osudb.getInstance(); // TODO dependency injection
            ArrayList<Player> players = new ArrayList<>();
            for(Element tr : tbody){
                Player player = PlayerParser.parsePlayer(tr);
                player.difference = db.compare(player);
                db.insertOrUpdate(player);
                if(player.hasPerformanceChanged())
                    players.add(0,player);
                else
                    players.add(player);
            }
            return players;
        } catch (IOException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    protected void onPostExecute(List<Player> players) {
        if((mAdapter != null) && (mRefresh != null)) {
            mAdapter.clear();
            mAdapter.addAll(players);
            mRefresh.setRefreshing(false);
            return;
        }
        if(mAdapter != null){
            mAdapter.addAll(players);
        }
    }
}
