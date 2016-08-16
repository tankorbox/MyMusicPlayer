package com.example.tankorbox.myplayer.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tankorbox.myplayer.Adapter.OfflineListAdapter;
import com.example.tankorbox.myplayer.Classes.OfflineMusic;
import com.example.tankorbox.myplayer.Data.OfflineSong;
import com.example.tankorbox.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tankorbox on 8/13/2016.
 */
public class FragmentOffline extends Fragment {
    List<OfflineSong> list;
    ListView listView;
    OfflineListAdapter adapter;
    SongClickListener songClickListener;
    private boolean isMusicPlaying =false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            songClickListener = (SongClickListener) context;
        }
        catch (ClassCastException e) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentoffline,container,false);
        list = new ArrayList<>();
        OfflineMusic music = new OfflineMusic();
        list = music.getPlayList();
        listView = (ListView) v.findViewById(R.id.listview_offline);
        adapter = new OfflineListAdapter(list,getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isMusicPlaying==false) {
                    songClickListener.OnSongClickListener(i,isMusicPlaying);
                    isMusicPlaying = true;
                }
                else {
                    songClickListener.OnSongClickListener(i,isMusicPlaying);
                    isMusicPlaying = false;
                }
            }
        });
        return v;
    }

    public interface SongClickListener {
        void OnSongClickListener(int i,boolean isplay);
    }

}
