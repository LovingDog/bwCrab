package com.bigwhite.crab.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bigwhite.crab.R;
import com.bigwhite.crab.adapter.ShowPhotosAdapter;
import com.bigwhite.crab.ui.dummy.order.Goods;

import java.util.ArrayList;


public class ShowPhotosActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LIST_EXTRA = "list";
    public static final String BUNDLE = "bundle";
    public static final String LIST_INDEX= "index";
    private ViewPager mViewPager;
    private ImageView mBackImg;
    private TextView mTitle;

    private ArrayList<Goods> mPaths;
    private ShowPhotosAdapter mAdapter;
    private int mCurrentIndex;
    private Button mBtMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);
        Bundle bundle = getIntent().getBundleExtra(BUNDLE);
        mPaths = (ArrayList<Goods>) bundle.getSerializable(LIST_EXTRA);
        mCurrentIndex = bundle.getInt(LIST_INDEX,0);
        initView();
        initEvent();
        initAdapter();
        initData();
    }

    private void initEvent() {
        mBtMore.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) this.findViewById(R.id.container);
        mBackImg = (ImageView) this.findViewById(R.id.bar_back);
        mTitle = (TextView) this.findViewById(R.id.bar_title);
        mBtMore = (Button) this.findViewById(R.id.bt_more);

    }

    private void initData() {
        mTitle.setText(getResources().getString(R.string.current_page, mViewPager.getCurrentItem() + 1, mPaths.size()));
    }

    public void initAdapter() {
        mAdapter = new ShowPhotosAdapter(mPaths, this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrentIndex);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
