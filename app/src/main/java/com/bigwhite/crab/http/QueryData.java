package com.bigwhite.crab.http;

import android.os.Handler;
import android.os.Message;

import com.bigwhite.crab.R;
import com.bigwhite.crab.utils.SystemUtil;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class QueryData {
    private int id;
    private HttpTask.HttpTaskListener listener;
    private Handler mNetWorkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            SystemUtil.showToast(R.string.toast_no_network);
        }
    };

    public QueryData(int id, HttpTask.HttpTaskListener listener) {
        this.id = id;
        this.listener = listener;
    }

    private boolean checkNet() {
        if (SystemUtil.isNetWork()) {
            return true;
        }
        Message message = new Message();
        mNetWorkHandler.sendMessage(message);
        return false;
    }

    public void getData() {
        if (checkNet()) {
            new HttpTask(this.id, this.listener).execute();
        }
    }
}
