package com.bigwhite.crab.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MenuItem;
import android.widget.TextView;

import com.bigwhite.crab.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ContentFrameLayout mFragmentLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_release:
                    mTextMessage.setText(R.string.title_release);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReleaseFragment.instantiate(MainActivity.this, getString(R.string.title_release))).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_list:
                    mTextMessage.setText(R.string.title_list);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReleaseFragment.instantiate(MainActivity.this, getString(R.string.title_list))).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_user:
                    mTextMessage.setText(R.string.title_user);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ReleaseFragment.instantiate(MainActivity.this, getString(R.string.title_user))).commitAllowingStateLoss();
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
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * Init the fragments.
     */
    private void initFragments() {
        ReleaseFragment.instantiate(this, getString(R.string.title_release));
        ListItemFragment.instantiate(this, getString(R.string.title_list));
        UserFragment.instantiate(this, getString(R.string.title_user));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, ReleaseFragment.instantiate(this, getString(R.string.title_release)));
        transaction.commitAllowingStateLoss();
    }
}
