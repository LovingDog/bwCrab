package com.bigwhite.crab.http;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class HttpTask extends AsyncTask<Object, Void, Object> {
    private int id;
    private HttpTaskListener listener;

    public HttpTask(int id, HttpTaskListener listener) {
        this.id = id;
        this.listener = listener;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return listener.getData(id);
    }

    @Override
    protected void onPostExecute(Object o) {
        listener.onSuccess(id, o);
    }

    public interface HttpTaskListener {
        Object getData(int id);

        void onSuccess(int id, Object object);
    }
}
