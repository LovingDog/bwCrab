package com.bigwhite.crab.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigwhite.crab.R;
import com.bigwhite.crab.ui.dummy.upload.UploadImgInfoController;
import com.bigwhite.crab.ui.dummy.upload.UploadMerchantController;
import com.bigwhite.crab.utils.ToastUtils;
import com.bigwhite.crab.utils.Utils;

import java.io.File;

public class ReleaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int RELASE_IMAGE_COLUMNCOUNT = 6;
    private static final int REQUEST_CAMERA = 101;
    private static final int REQUEST_PHOTOS = 102;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private UploadMerchantController mUploadController;
    private UploadImgInfoController mUploadImgInfoController;
    private File mFile;

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
        mUploadImgInfoController = new UploadImgInfoController(getActivity(), view);
        mUploadImgInfoController.initView();
        mUploadImgInfoController.initImgData();
        mUploadImgInfoController.setListener(new UploadImgInfoController.StartCameraCaptureListener() {
            @Override
            public void startCaptureListener(boolean camera) {
                if (camera) {
                    startCameraCapture();
                } else {
                    startPhotosChoose();
                }
            }
        });
        return view;
    }

    private void startCameraCapture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mFile = Utils.getSaveImgPath();
        mFile.getParentFile().mkdirs();
        Uri uri = FileProvider.getUriForFile(getActivity(), "com.bigwhite.crab.fileprovider", mFile);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void startPhotosChoose() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PHOTOS);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUploadController.initView();
        mUploadController.initEvent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (mFile.exists()) {
                mUploadImgInfoController.addImg(mFile.getAbsolutePath());
            }
            //can refresh
//            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            Uri contentUri = Uri.fromFile(file);
//            mediaScanIntent.setData(contentUri);
//            sendBroadcast(mediaScanIntent);
        } else if (requestCode == REQUEST_PHOTOS && data != null) {
            String path = Utils.getIntentPath(getActivity(), data);
            if (new File(path).exists()) {
                mUploadImgInfoController.addImg(path);
            }
        }
    }
}

