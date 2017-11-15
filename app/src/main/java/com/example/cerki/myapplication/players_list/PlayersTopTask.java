package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by cerki on 10-Nov-17.
 */

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
        List<Player> players = null;
        String url = strings[0];
        try {
            Document doc = Jsoup.connect(url).get();
            players  = parse(doc);
            return players;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<Player> parse(Document doc){
        ArrayList<Player> players = new ArrayList<Player>();
        Elements tbody = doc.select("tbody").first().children();
        for (int i = 0; i < tbody.size(); i++) {
            Player p = new Player();
            Element tr = tbody.get(i);
            String username = tr.select("span").text();
            String rank = tr.children().first().text();
            String acc = tr.children().get(2).text();
            String pc = tr.children().get(3).text();
            String pp = tr.children().get(4).text();
            String country_tmp = tr.children().get(1).select("span").attr("style");
            String country = country_tmp.substring(country_tmp.indexOf("'") + 1,country_tmp.lastIndexOf("'"));
            String user_id = tr.select(".ranking-page-table__user-link").attr("href");
           p.set(Player.COLUMN_ACC,acc);
           p.set(Player.COLUMN_PP,pp);
           p.set(Player.COLUMN_PC,pc);
           p.set(Player.COLUMN_RANK,rank);
           p.setUsername(username);
           p.setId(user_id);
           p.setCountry(country);
           players.add(p);
        }
        return players;
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
