package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cerki.myapplication.R;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by cerki on 08-Nov-17.
 */

class PlayersTopListAdapter extends ArrayAdapter<Player> {
    int mLayoutId;
    public PlayersTopListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mLayoutId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(mLayoutId,null);
        }
        Player p = getItem(position);
        if(p != null) {
            TextView pp = convertView.findViewById(R.id.item_pp);
            TextView acc = convertView.findViewById(R.id.item_acc);
            TextView rank = convertView.findViewById(R.id.item_rank);
            TextView username = convertView.findViewById(R.id.item_username);
            if (username != null){
                username.setText(p.getUsername());
            }
            if (pp != null) {
                pp.setText(p.getAsString(Player.COLUMN_PP));
            }
            if (acc != null){
                acc.setText(p.getAsString(Player.COLUMN_ACC));
            }
            if (rank != null){
                rank.setText(p.getAsString(Player.COLUMN_RANK));
            }
        }
        return convertView;
    }
}