package com.example.tankorbox.myplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tankorbox.myplayer.Data.MyNavi;
import com.example.tankorbox.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tankorbox on 8/13/2016.
 */
public class MyNaviAdapter extends BaseAdapter {
    List<MyNavi> list = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public MyNaviAdapter(List<MyNavi> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
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
        view = layoutInflater.inflate(R.layout.itemfornavi,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imNavi);
        TextView textView = (TextView) view.findViewById(R.id.tvNavi);
        MyNavi myNavi = list.get(i);
        imageView.setImageResource(myNavi.getImageView());
        textView.setText(myNavi.getName());
        return view;
    }
}
