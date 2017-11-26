package com.example.cerki.myapplication.PlayersList;

import android.widget.AbsListView;

import com.example.cerki.myapplication.PlayersList.Tasks.ListTask;
import com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners.ListTaskListener;
import com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners.WorkDoneListener;


public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int visibleThreshold = 5;
    private int previousTotal = 2;
    private boolean loading = true;
    private WorkDoneListener workDoneListener;

    public EndlessScrollListener(ListTaskListener task){
            workDoneListener = task;
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
            new ListTask(workDoneListener).loadPlayersFromPage(currentPage);
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}
