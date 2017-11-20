package com.example.cerki.myapplication.players_list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
@RunWith(JUnit4.class)
public class PlayerDataEntryTest {
   @Test
    public void testCreation(){
       PlayerDataEntry entry = new PlayerDataEntry("24.32");
       assertEquals(24.32,entry.getDoubleVal(),0);
       assertEquals("24.32",entry.getAsString());
       assertEquals(0,entry.getIntVal());
       PlayerDataEntry entry1 = new PlayerDataEntry("24");
       assertEquals(24,entry1.getIntVal());
       assertEquals("24",entry1.getAsString());
       assertEquals(0,entry1.getDoubleVal(),0);
   }
}