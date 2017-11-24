package com.example.cerki.myapplication.PlayersList;

import com.example.cerki.myapplication.Player.DataEntry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
@RunWith(JUnit4.class)
public class DataEntryTest {
   @Test
    public void testCreation(){
       DataEntry entry = new DataEntry("24.32");
       assertEquals(24.32,entry.getDoubleVal(),0);
       assertEquals("24.32",entry.getAsString());
       assertEquals(0,entry.getIntVal());
       DataEntry entry1 = new DataEntry("24");
       assertEquals(24,entry1.getIntVal());
       assertEquals("24",entry1.getAsString());
       assertEquals(0,entry1.getDoubleVal(),0);
   }

   @Test
   public void getAsString() throws Exception {
      DataEntry entry = new DataEntry(23.4342342132);
      assertEquals("23.43",entry.getAsString());
   }
}