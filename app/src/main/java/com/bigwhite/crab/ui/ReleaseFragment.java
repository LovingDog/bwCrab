package com.bigwhite.crab.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigwhite.crab.R;
import com.bigwhite.crab.adapter.UploadPhotoAdapter;
import com.bigwhite.crab.ui.dummy.upload.UploadMerchantController;
import com.bigwhite.crab.utils.SpacesItemDecoration;

public class ReleaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int RELASE_IMAGE_COLUMNCOUNT = 6;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mReleaseImageRecyclerView;
    private UploadPhotoAdapter mUploadPhotoAdapter;
    private UploadMerchantController mUploadController;
    public ReleaseFragment() {
        // Required empty public constructor
    }

    public static com.bigwhite.crab.ui.fragment.ReleaseFragment newInstance(String param1, String param2) {
        com.bigwhite.crab.ui.fragment.ReleaseFragment fragment = new com.bigwhite.crab.ui.fragment.ReleaseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mUploadController = new UploadMerchantController(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("ren_kang", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_release, container, false);
        mReleaseImageRecyclerView = (RecyclerView) view.findViewById(R.id.release_recyclerview);
        mReleaseImageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), RELASE_IMAGE_COLUMNCOUNT));
        mReleaseImageRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        mUploadPhotoAdapter = new UploadPhotoAdapter(getActivity());
        mReleaseImageRecyclerView.setAdapter(mUploadPhotoAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUploadController.initView();
        mUploadController.initEvent();
    }
}

