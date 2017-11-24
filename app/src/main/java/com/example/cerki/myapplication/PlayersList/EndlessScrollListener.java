package com.example.cerki.myapplication.PlayersList;

import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.cerki.myapplication.Player.Player;

import java.util.List;


public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 5;
    private int previousTotal = 2;
    private boolean loading = true;
    private ListView mListView;
    private ListAdapter mAdapter;

    public EndlessScrollListener(ListView listView,ListAdapter adapter) {
        mListView = listView;
        mAdapter = adapter;
    }
    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        int currentPage = totalItemCount / 50 + 1;
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
            new ListTask(mAdapter).execute(ListFragment.REQUEST_URL + "?page="+ currentPage);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}
