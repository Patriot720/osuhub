package com.example.cerki.myapplication.PlayersList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cerki.myapplication.Player.Columns;
import com.example.cerki.myapplication.Player.Player;
import com.example.cerki.myapplication.Player.DataEntry;
import com.example.cerki.myapplication.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


class ListAdapter extends ArrayAdapter<Player> {
    private final int mLayoutId;
    ListAdapter(@NonNull Context context) {
        super(context, R.layout.players_top_item);
        mLayoutId = R.layout.players_top_item;
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
            ImageView country = convertView.findViewById(R.id.item_country_image);
            if(country != null){
                setAsset(country,p.get(Columns.COUNTRY));
            }
            if (username != null){
                username.setText(p.get(Columns.USERNAME));
            }
            if (pp != null) {
                pp.setText(p.get(Columns.PP));
            }
            if(pc != null){
                pc.setText(p.get(Columns.PC));
            }
            if (acc != null){
                acc.setText(p.get(Columns.ACC));
            }
            if (rank != null){
                rank.setText("#" + p.get(Columns.RANK));
                int val = p.getDataEntry(Columns.RANK).getIntVal();
                setSpecialColorsForTop10Players(rank,val);
            }
            if(p.get(Columns.ACTIVITY).equals(Player.INACTIVE))
                convertView.setAlpha((float) 0.5);
            else
                convertView.setAlpha(1);
           if(p.difference != null) {
               DataEntry pp_diff = p.difference.get("pp");
               DataEntry acc_diff = p.difference.get("acc");
               DataEntry rank_diff = p.difference.get("rank");
               DataEntry pc_diff = p.difference.get("pc");
              setDiff(pp_diff_view,pp_arrow,pp_diff);
              setDiff(acc_diff_view,acc_arrow,acc_diff);
              setDiff(rank_diff_view,rank_arrow,rank_diff);
              setDiff(pc_diff_view,pc_arrow,pc_diff);
           }
        }
        return convertView;
    }



    private void setSpecialColorsForTop10Players(TextView rankView, int rank) {
                Resources resources = getContext().getResources();
                if(rank == 1)
                    rankView.setTextColor(resources.getColor(R.color.top1));
                else if (rank <= 10)
                    rankView.setTextColor(resources.getColor(R.color.top10));
                else
                    rankView.setTextColor(resources.getColor(R.color.white));
    }

    private void setAsset(ImageView destination, String source) {
        try {
            InputStream open = getContext().getAssets().open(source);
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            destination.setImageBitmap(bitmap);
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException))
                e.printStackTrace();
        }
    }

    void setDiff(TextView m, ImageView arrow, DataEntry i) {
        if(i == null) {
            if(m !=null)
            m.setVisibility(View.INVISIBLE);
            if(arrow !=null)
            arrow.setVisibility(View.INVISIBLE);
            return;
        }
        if(arrow != null) {
            arrow.setVisibility(View.VISIBLE);
            if (i.isPositive())
                arrow.setImageResource(R.drawable.arrow_up);
            else
                arrow.setImageResource(R.drawable.arrow_down);
        }
        if(m != null) {
            m.setText(i.getAbsString());
            m.setVisibility(View.VISIBLE);
        }

    }
}