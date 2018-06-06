package com.imaginationunlimited.sniper.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imaginationunlimited.sniper.R;

/**
 * Created by MSC on 2018/6/6.
 * BJ AlphaMobile
 */
public class ChatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_chat, container, false);
        return view;
    }
}
