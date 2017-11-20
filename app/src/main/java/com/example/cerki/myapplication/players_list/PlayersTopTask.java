package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;

import com.example.cerki.myapplication.db.Osudb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



class PlayersTopTask extends AsyncTask<String,Void,List<Player>> {
    private SwipeRefreshLayout mRefresh;
    private ArrayAdapter mAdapter;
    private Osudb osuDb;
    public PlayersTopTask(Context context, ArrayAdapter adapter, SwipeRefreshLayout refresh) {
        osuDb = new Osudb(context);
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
        String url = strings[0];
        try {
            Document doc = Jsoup.connect(url).get();
            Elements tbody = doc.select("tbody").first().children();
            ArrayList<Player> players = new ArrayList<>();
            for(int i = 0; i < tbody.size();i++){
                Element tr = tbody.get(i);
                Player player = PlayerParser.parsePlayer(tr);
                HashMap<String, PlayerDataEntry> compare = osuDb.compare(player);
                player.difference = compare;
                //K
                osuDb.insertPlayer(player);
                if(!compare.isEmpty())
                    players.add(0,player);
                else
                    players.add(player);
            }
            return players;
        } catch (IOException e) {
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
        }
        super.onPostExecute(players);
    }
}
