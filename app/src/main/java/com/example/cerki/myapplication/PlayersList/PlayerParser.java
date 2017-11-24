package com.example.cerki.myapplication.PlayersList;

import com.example.cerki.myapplication.Player.Columns;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.Player.DataEntry;

import org.jsoup.nodes.Element;


class PlayerParser {
    static Player parsePlayer(Element tr){
            if(tr == null)
                return null;
            int isActive =  tr.classNames().toString().indexOf("inactive");
            String activity;
            if(isActive == -1)
                activity = Player.ACTIVE;
            else
                activity = Player.INACTIVE;
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
           p.set(Columns.ACC,new DataEntry(acc));
           p.set(Columns.PP,new DataEntry(pp));
           p.set(Columns.PC,new DataEntry(pc));
           p.set(Columns.RANK,new DataEntry('-' + rank.substring(1)));
           p.set(Columns.USERNAME,username);
           p.set(Columns.COUNTRY,country);
           p.set(Columns.ACTIVITY,activity);
           return p;
    }
}
