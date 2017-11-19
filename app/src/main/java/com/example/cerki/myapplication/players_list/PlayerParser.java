package com.example.cerki.myapplication.players_list;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class PlayerParser {
    static public Player parsePlayer(Element tr){
            if(tr == null)
                return null;
            Player p = new Player();
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
           p.set(Player.COLUMN_RANK,-Double.parseDouble(rank.replaceAll("[^0-9]","")));
           p.setUsername(username);
           p.setId(user_id);
           p.setCountry(country);
           return p;
    }
}
