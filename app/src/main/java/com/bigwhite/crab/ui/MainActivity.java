package com.bigwhite.crab.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MenuItem;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.base.MyApplication;
import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ContentFrameLayout mFragmentLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ActionBar actionBar = getSupportActionBar();
            switch (item.getItemId()) {
                case R.id.navigation_release:
//                    actionBar.setTitle(R.string.title_release);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReleaseFragment
                            .instantiate(MainActivity.this, ReleaseFragment.class.getName())).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_list:
//                    actionBar.setTitle(R.string.title_list);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReleaseFragment
                            .instantiate(MainActivity.this, ListItemFragment.class.getName()))
                            .commitAllowingStateLoss();
                    return true;
                case R.id.navigation_user:
//                    actionBar.setTitle(R.string.title_user);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReleaseFragment
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
        mTextMessage = (TextView) findViewById(R.id.message);
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
        transaction.add(R.id.fragment_container, ReleaseFragment.instantiate(this, ReleaseFragment.class.getName()));
        transaction.commitAllowingStateLoss();
    }
	
	//在fragement中onDestroy方式使用这个，activity默认 有该方法
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getApplicationWatcher(this);
        refWatcher.watch(this);
    }
}
