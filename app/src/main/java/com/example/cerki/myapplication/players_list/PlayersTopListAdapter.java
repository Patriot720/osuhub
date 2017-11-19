package com.example.cerki.myapplication.players_list;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cerki.myapplication.R;

import org.w3c.dom.Text;

import java.util.Locale;


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
            TextView pc = convertView.findViewById(R.id.item_pc);
            TextView pp_diff_view = convertView.findViewById(R.id.item_pp_difference);
            ImageView pp_arrow = convertView.findViewById(R.id.item_pp_arrow);
            TextView rank_diff_view = convertView.findViewById(R.id.item_rank_difference);
            ImageView rank_arrow = convertView.findViewById(R.id.item_rank_arrow);
            TextView acc_diff_view = convertView.findViewById(R.id.item_acc_difference);
            ImageView acc_arrow = convertView.findViewById(R.id.item_acc_arrow);
            TextView pc_diff_view = convertView.findViewById(R.id.item_pc_difference);
            ImageView pc_arrow = convertView.findViewById(R.id.item_pc_arrow);

            if (username != null){
                username.setText(p.getUsername());
            }
            if (pp != null) {
                pp.setText(p.getAsString(Player.COLUMN_PP));
            }
            if(pc != null){
                pc.setText(p.getAsString(Player.COLUMN_PC));
            }
            if (acc != null){
                acc.setText(p.getAsString(Player.COLUMN_ACC));
            }
            if (rank != null){
                rank.setText(p.getAsString(Player.COLUMN_RANK).substring(1));
            }
           if(p.getPlayerDifference() != null) {
               Double pp_diff = p.getPlayerDifference("pp");
               Double acc_diff = p.getPlayerDifference("acc");
               Double rank_diff = p.getPlayerDifference("rank");
               Double pc_diff = p.getPlayerDifference("pc");
              setDiff(pp_diff_view,pp_arrow,pp_diff);
              setDiff(acc_diff_view,acc_arrow,acc_diff);
              setDiff(rank_diff_view,rank_arrow,rank_diff);
              setDiff(pc_diff_view,pc_arrow,pc_diff);
           }
        }
        return convertView;
    }

    public void setDiff(TextView m, ImageView arrow, Double i) {
        if(i == null) {
            if(m !=null)
            m.setVisibility(View.GONE);
            if(arrow !=null)
            arrow.setVisibility(View.GONE);
            return;
        }
        if(arrow != null) {
            arrow.setVisibility(View.VISIBLE);
            if (i > 0)
                arrow.setImageResource(R.drawable.arrow_up);
            else
                arrow.setImageResource(R.drawable.arrow_down);
        }
        if(m != null) {
            i = Math.abs(i);
            if(i%1==0)
                m.setText(String.valueOf(i.intValue()));
            else
                m.setText(String.format(Locale.getDefault(),"%.2f",i));
            m.setVisibility(View.VISIBLE);
        }

    }
}