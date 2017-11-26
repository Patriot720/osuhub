package com.example.cerki.myapplication.PlayersList.Tasks;

import android.os.AsyncTask;

import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners.WorkDoneListener;

import java.util.List;

/**
 * Created by cerki on 26-Nov-17.
 */

public abstract class PlayersTask extends AsyncTask<String,Void,List<Player>> {
    public static final String REQUEST_URL = "https://osu.ppy.sh/rankings/osu/performance";
    private WorkDoneListener mWorkDoneListener;
    public PlayersTask(WorkDoneListener workDoneListener){
       mWorkDoneListener = workDoneListener;
    }
    @Override
    public void onPostExecute(List<Player> players) {
        mWorkDoneListener.workDone(players);
    }
    public void loadPlayersFromPage(int number){
        execute(REQUEST_URL + "?page=" + number);
    }
    public void loadPlayers(){
        execute(REQUEST_URL);
    }
}
