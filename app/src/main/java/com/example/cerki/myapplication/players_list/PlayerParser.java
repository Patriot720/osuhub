package com.example.cerki.myapplication.players_list;

import org.jsoup.nodes.Element;


public class PlayerParser {
    static public Player parsePlayer(Element tr){
            if(tr == null)
                return null;
            String user_id = tr.select(".ranking-page-table__user-link").attr("href");
            Player p = new Player(user_id);
            String username = tr.select("span").text();
            String rank = tr.children().first().text();
            String acc = tr.children().get(2).text();
            String pc = tr.children().get(3).text();
            String pp = tr.children().get(4).text();
            String country_tmp = tr.children().get(1).select("span").attr("style");
            String country = country_tmp.substring(country_tmp.indexOf("'") + 1,country_tmp.lastIndexOf("'"));
            country = country.substring(1);
            country = country.substring(country.indexOf("/"));
            country = country.toLowerCase();
            country = country.substring(1);
           p.set(Columns.ACC,new PlayerDataEntry(acc));
           p.set(Columns.PP,new PlayerDataEntry(pp));
           p.set(Columns.PC,new PlayerDataEntry(pc));
           p.set(Columns.RANK,new PlayerDataEntry('-' + rank.substring(1)));
           p.set(Columns.USERNAME,username);
           p.set(Columns.COUNTRY,country);
           return p;
    }
}
