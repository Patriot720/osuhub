package com.example.cerki.myapplication.PlayersList.Tasks.TaskListeners;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.example.cerki.myapplication.Player.Player;

import java.util.List;

public class ListTaskListener implements WorkDoneListener{
     private SwipeRefreshLayout mRefresh;
     private ArrayAdapter<Player> mAdapter;
     private ProgressBar mBar;
     public ListTaskListener(SwipeRefreshLayout refresh,
                             ArrayAdapter<Player> adapter){
          mRefresh = refresh;
          mAdapter = adapter;
     }

     public ListTaskListener(ArrayAdapter<Player> mAdapter) {
          this.mAdapter = mAdapter;
     }

     public ListTaskListener(ArrayAdapter<Player> mAdapter, ProgressBar mBar) {
          this.mAdapter = mAdapter;
          this.mBar = mBar;
     }

     @Override
     public void workDone() {

     }

     @Override
     public void workDone(List<Player> results) {
          if(mAdapter == null)
               return;
         if(mBar != null)
              mBar.setVisibility(View.INVISIBLE);
          if(mRefresh !=null && mRefresh.isRefreshing()) {
               mRefresh.setRefreshing(false);
               mAdapter.clear();
          }
          if(results != null)
               mAdapter.addAll(results);
     }
}
