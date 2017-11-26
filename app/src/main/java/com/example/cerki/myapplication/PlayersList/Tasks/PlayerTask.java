package com.example.cerki.myapplication.PlayersList.Tasks;

import android.os.AsyncTask;

import com.example.cerki.myapplication.Player.Player;

/**
 * Created by cerki on 26-Nov-17.
 */

public class PlayerTask extends AsyncTask<String,Void,Player> {
    final private String REQUEST_URL = "https://osu.ppy.sh/api/";
    final private String TEMPORARY_API_KEY = "b40b7a7a8207b1ebd870eaf1f74bd2995f1a2cb6";

    private String compileUrl(String userId){
        return REQUEST_URL + "?k=" + TEMPORARY_API_KEY + "&u=" + userId;
    }
    @Override
    protected Player doInBackground(String... userId) {

        return null;
    }
}
