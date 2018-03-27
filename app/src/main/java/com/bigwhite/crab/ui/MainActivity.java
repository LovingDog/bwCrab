package com.bigwhite.crab.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MenuItem;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.base.MyApplication;
import com.bigwhite.crab.ui.fragment.ListItemFragment;
import com.bigwhite.crab.ui.fragment.UserFragment;
import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {

    private int mCurrentId = INDEX_RELEASE;
    private static final int INDEX_RELEASE = 0;
    private static final int INDEX_LIST = 1;
    private static final int INDEX_USER = 2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ActionBar actionBar = getSupportActionBar();
            switch (item.getItemId()) {
                case R.id.navigation_release:
                    if (mCurrentId == INDEX_RELEASE) {
                        return false;
                    }
                    mCurrentId = INDEX_RELEASE;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Fragment
                            .instantiate(MainActivity.this, ReleaseFragment.class.getName())).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_list:
                    if (mCurrentId == INDEX_LIST) {
                        return false;
                    }
                    mCurrentId = INDEX_LIST;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Fragment
                            .instantiate(MainActivity.this, ListItemFragment.class.getName()))
                            .commitAllowingStateLoss();
                    return true;
                case R.id.navigation_user:
                    if (mCurrentId == INDEX_USER) {
                        return false;
                    }
                    mCurrentId = INDEX_USER;
//                    actionBar.setTitle(R.string.title_user);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Fragment
                            .instantiate(MainActivity.this, UserFragment.class.getName())).commitAllowingStateLoss();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setupActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initFragments();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    /**
     * Init the fragments.
     */
    private void initFragments() {
        ReleaseFragment.instantiate(this, ReleaseFragment.class.getName());
        ListItemFragment.instantiate(this, ListItemFragment.class.getName());
        UserFragment.instantiate(this, UserFragment.class.getName());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, Fragment.instantiate(this, ReleaseFragment.class.getName()));
        transaction.commitAllowingStateLoss();
    }

    //在fragment中onDestroy方式使用这个，activity默认 有该方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getApplicationWatcher(this);
        refWatcher.watch(this);
    }
}
