package com.example.tankorbox.myplayer.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tankorbox.myplayer.R;
import com.firebase.client.Firebase;

/**
 * Created by tankorbox on 8/13/2016.
 */
public class FragmentOnline extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragmentonline,container,false);

        Firebase.setAndroidContext(getActivity());
        Firebase myfb = new Firebase("https://mmplayer-19dd3.firebaseio.com/");
        

        return v;
    }
}
