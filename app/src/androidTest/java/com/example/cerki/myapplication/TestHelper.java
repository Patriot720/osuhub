package com.example.cerki.myapplication;

import com.example.cerki.myapplication.players_list.Player;

/**
 * Created by cerki on 15-Nov-17.
 */

public class TestHelper {
    public static Player getFakePlayer(int dataMultiplier){
        Player player = new Player();
        player.setUsername("username");
        player.setId("1");
        player.setCountry("country");
        player.set("pp", (double) 1000 * dataMultiplier);
        player.set("pc", (double) 1000 * dataMultiplier);
        player.set("acc", (double) 1000 * dataMultiplier);
        player.set("rank", (double) 1000 * dataMultiplier);
        return player;
    }
    public static Player getFakePlayer(){
        return getFakePlayer(1);
    }
}
