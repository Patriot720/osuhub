package com.example.cerki.myapplication;

import com.example.cerki.myapplication.players_list.Player;
import com.example.cerki.myapplication.players_list.PlayerDataEntry;

import static org.junit.Assert.*;

public class TestHelper {
    public static Player getFakePlayer(int multiplier){
        Player player = new Player(1);
        player.set("username","username");
        player.set("country","country");
        player.set("pp",new PlayerDataEntry(1000 * multiplier));
        player.set("pc",new PlayerDataEntry(1000 * multiplier));
        player.set("acc",new PlayerDataEntry(1000 * multiplier));
        player.set("rank",new PlayerDataEntry(1000 * multiplier));
        return player;
    }
    public static Player getFakePlayer(){
        return getFakePlayer(1);
    }
    public static void assertPlayer(Player p){
        assertPlayer(p,1);
    }
    public static void assertPlayer(Player p,int multiplier){
        assertEquals(1000 * multiplier,p.getDataEntry("pp").getIntVal());
        assertEquals(1000 * multiplier,p.getDataEntry("acc").getIntVal());
        assertEquals(1000 * multiplier,p.getDataEntry("rank").getIntVal());
        assertEquals("username",p.get("username"));
        assertEquals("country",p.get("country"));
    }

}
