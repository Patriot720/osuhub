package com.example.cerki.myapplication.players_list;

import org.intellij.lang.annotations.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class PlayerParserTest {

    @Test
    public void parse() throws Exception {
        String path = new File("").getAbsolutePath();
        File f = new File(path + "/app/src/test/resources/osu.html");
        Document doc = Jsoup.parse(f,"utf8");
        Elements tbody = doc.select("tbody").first().children();
        Element tr = tbody.get(0);
        Player player = PlayerParser.parsePlayer(tr);
        assertEquals("Cookiezi",player.get("username"));
        assertEquals("flags/kr.png",player.get("country"));
        assertTrue(player.getDataEntry("pp").getIntVal() > 0);
        assertTrue(player.getDataEntry("acc").getDoubleVal() > 0);
    }

    @Test
    public void convertCountryString() throws Exception {
        String s = "/images/flags/KR.png";
        assertEquals("/flags/kr.png",s);
    }

    @Test
    public void parseEmpty() throws Exception{
        Player player = PlayerParser.parsePlayer(null);
        assertEquals(null,player);
    }
}