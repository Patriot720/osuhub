package com.example.cerki.myapplication;

import com.example.cerki.myapplication.players_list.Player;
import com.example.cerki.myapplication.players_list.PlayerDataEntry;

/**
 * Created by cerki on 15-Nov-17.
 */

public class TestHelper {
    public static Player getFakePlayer(int dataMultiplier){
        Player player = new Player(1);
        player.set("username","username");
        player.set("country","country");
        player.set("pp",new PlayerDataEntry((int)1000 * dataMultiplier));
        player.set("pc",new PlayerDataEntry((int)1000 * dataMultiplier));
        player.set("acc",new PlayerDataEntry((int)1000 * dataMultiplier));
        player.set("rank",new PlayerDataEntry((int)1000 * dataMultiplier));
        return player;
    }
    public static Player getFakePlayer(){
        return getFakePlayer(1);
    }
}
