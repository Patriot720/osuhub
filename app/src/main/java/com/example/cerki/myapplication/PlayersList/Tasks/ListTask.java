package com.example.cerki.myapplication.PlayersList.Tasks;

import com.example.cerki.myapplication.Osudb;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.PlayersList.PlayerParser;
import com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners.WorkDoneListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ListTask extends PlayersTask {
    public ListTask(WorkDoneListener workDoneListener) {
        super(workDoneListener);
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
                players.add(player);
            }
            return players;
        } catch (IOException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
