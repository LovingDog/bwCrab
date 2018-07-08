package com.bigwhite.crab.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigwhite.crab.R;
import com.bigwhite.crab.ui.dummy.order.GoodsInfo;
import com.bigwhite.crab.utils.ToastUtils;

import java.io.Serializable;

public class OrderDetail extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1000;
    public static final String EXTRA_ORDER = "order";
    private EditText mKuaidi;
    private String mTemp;
    private GoodsInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("订单详情");
        Serializable serializable = getIntent().getExtras().getSerializable(EXTRA_ORDER);
        GoodsInfo info;
        if (serializable instanceof GoodsInfo) {
            info = (GoodsInfo) serializable;
        } else {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }
        setContentView(R.layout.activity_order_detail);
        TextView customerName = (TextView) findViewById(R.id.customer_name);
        customerName.setText(info.getUsername());
        TextView customerAddress = (TextView) findViewById(R.id.customer_address);
        customerAddress.setText(info.getAddress());
        TextView customerPhone = (TextView) findViewById(R.id.customer_phone);
        customerPhone.setText(info.getPhone());
        mKuaidi = (EditText) findViewById(R.id.kuaidi_num);
        mTemp = info.getKuaidiNum();
        // 默认为空字串
        if (TextUtils.isEmpty(mTemp)) {
            mTemp = "";
        }
        mKuaidi.setText(mTemp);
        findViewById(R.id.edit_order).setOnClickListener(this);
        findViewById(R.id.edit_done).setOnClickListener(this);

        mInfo = info;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.edit_order:
                // 修改编辑状态
                boolean enable = mKuaidi.isEnabled();
                mKuaidi.setEnabled(!enable);
                break;
            case R.id.edit_done:
                // 返回之前的界面
                String kuaidi = mKuaidi.getText().toString();
                if (kuaidi.equals("")) {
                    ToastUtils.showToast(this,"请输入订单号");
                    return;
                }
                if (mTemp.equals(kuaidi)) {
                    setResult(RESULT_CANCELED);
                } else {
                    mInfo.setKuaidiNum(kuaidi);
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_ORDER, mInfo);
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;
        }
    }
}