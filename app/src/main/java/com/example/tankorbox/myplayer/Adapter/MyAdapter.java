package com.example.tankorbox.myplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tankorbox.myplayer.Data.SongData;
import com.example.tankorbox.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tankorbox on 8/13/2016.
 */
public class MyAdapter extends BaseAdapter {
    List<SongData> list = new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public MyAdapter(List<SongData> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        if (list.isEmpty()||list==null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null) {
            view = (View) inflater.inflate(R.layout.item_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.tvid = (TextView) view.findViewById(R.id.tvid);
            viewHolder.tvname = (TextView) view.findViewById(R.id.tvname);
            viewHolder.tvtime = (TextView) view.findViewById(R.id.tvtime);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        SongData songData = list.get(i);
        viewHolder.tvid.setText(songData.getId());
        viewHolder.tvname.setText(songData.getName());
        viewHolder.tvtime.setText(songData.getTime());
        return view;
    }

    class ViewHolder {
        TextView tvname;
        TextView tvtime;
        TextView tvid;
    }
}
