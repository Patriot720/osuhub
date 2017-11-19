package com.example.cerki.myapplication.players_list;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cerki on 16-Nov-17.
 */
@RunWith(JUnit4.class)
public class PlayerParserTest {

    @Test
    public void test_parse() throws Exception {
        String path = new File("").getAbsolutePath();
        File f = new File(path + "/app/src/test/resources/osu.html");
        Document doc = Jsoup.parse(f,"utf8");
        Elements tbody = doc.select("tbody").first().children();
        Element tr = tbody.get(0);
        Player player = PlayerParser.parsePlayer(tr);
        assertEquals("Cookiezi",player.getUsername());
    }
    @Test
    public void test_parse_empty() throws Exception{
        Element tr = null;
        Player player = PlayerParser.parsePlayer(tr);
        assertEquals(null,player);
    }
}