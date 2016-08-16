package com.example.tankorbox.myplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tankorbox.myplayer.Data.OfflineSong;
import com.example.tankorbox.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tankorbox on 8/14/2016.
 */
public class OfflineListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<OfflineSong> list = new ArrayList<>();
    Context context;

    public OfflineListAdapter(List<OfflineSong> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list.isEmpty()||list==null) {
            return 0;
        }
        else {
            return list.size();
        }
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
        if (view ==null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_offline_list,null);
            viewHolder.tvname = (TextView) view.findViewById(R.id.offlinesong);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        OfflineSong data = list.get(i);
        viewHolder.tvname.setText(data.getName());
        return view;
    }

    class ViewHolder {
        TextView tvname;
    }

}
