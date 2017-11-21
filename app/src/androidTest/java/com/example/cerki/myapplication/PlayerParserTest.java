package com.example.cerki.myapplication;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;

import static org.junit.Assert.*;
import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Created by cerki on 21-Nov-17.
 */
@RunWith(AndroidJUnit4.class)
public class PlayerParserTest {
    @Test
    public void test_assets() throws Exception {
        AssetManager assets = getTargetContext().getAssets();
        InputStream open = assets.open("flags/kr.png");
        assertNotNull(open);
        Bitmap bitmap = BitmapFactory.decodeStream(open);
        assertNotNull(bitmap);
        assertEquals(20,bitmap.getHeight());
    }
}
