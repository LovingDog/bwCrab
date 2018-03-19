package com.ws.crab.bwui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ws.crab.R;
import com.ws.crab.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/19.
 */

public class MineFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine,container,false);
        return view;
    }
}
