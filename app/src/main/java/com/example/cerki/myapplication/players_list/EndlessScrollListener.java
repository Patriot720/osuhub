package com.example.cerki.myapplication.players_list;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import static android.content.ContentValues.TAG;


public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 5;
    private int currentPage = 2;
    private int previousTotal = 2;
    private boolean loading = true;
    private ArrayAdapter<Player> mAdapter;

    public EndlessScrollListener(ArrayAdapter<Player> adapter) {
        mAdapter = adapter;
    }
    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if(!loading && totalItemCount <= 51){
            currentPage = 2;
            previousTotal = 2;
        }
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
            new PlayersTopTask(mAdapter).execute(PlayersTopFragment.REQUEST_URL + "?page="+ currentPage);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}
