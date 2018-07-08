package com.bigwhite.crab.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.bigwhite.crab.R;
import com.bigwhite.crab.base.HttpCallBack;
import com.bigwhite.crab.bean.CodeBean;
import com.bigwhite.crab.bean.CodeList;
import com.bigwhite.crab.http.DataLogic;
import com.bigwhite.crab.preference.AppPreference;
import com.bigwhite.crab.ui.dummy.login.UserInfo;
import com.bigwhite.crab.ui.dummy.order.Goods;
import com.bigwhite.crab.ui.dummy.order.KuaidiRequest;
import com.bigwhite.crab.utils.GsonUtil;
import com.bigwhite.crab.utils.ToastUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ShowChargCodeActivity extends Activity implements OnRefreshListener,OnLoadMoreListener,OnNetWorkErrorListener {

    private LRecyclerView mLRecyclerView;
    private ArrayList<CodeBean> mData;
    public static final String LIST_EXTRA = "list";
    public static final String BUNDLE = "bundle";
    public static final String LIST_INDEX= "index";
    private Goods mGoods;
    private TextView mPrice;
    private TextView mKucun;
    private TextView mJifen;
    private TextView mMerchant;
    private ImageView mBackImg;
    private TextView mTitle;
    private Button mBtMore;
    private ArrayList<CodeBean> mCodeBeans;
    private CommonAdapter<CodeBean> mCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_charg_code);
        mGoods = (Goods) getIntent().getSerializableExtra("goods");
        mCodeBeans = new ArrayList<>();
        mLRecyclerView = (LRecyclerView) findViewById(R.id.listview_charge_code);
        mLRecyclerView.setPullRefreshEnabled(true);
        mLRecyclerView.setOnRefreshListener(this);
        mLRecyclerView.setOnLoadMoreListener(this);
        mLRecyclerView.setOnNetWorkErrorListener(this);

        mPrice = (TextView) this.findViewById(R.id.text_price);
        mMerchant = (TextView) this.findViewById(R.id.text_merchant);
        mJifen = (TextView) this.findViewById(R.id.text_jifen);
        mKucun = (TextView) this.findViewById(R.id.text_kucun);
        mBackImg = (ImageView) this.findViewById(R.id.bar_back);
        mTitle = (TextView) this.findViewById(R.id.bar_title);
        mBtMore = (Button) this.findViewById(R.id.bt_more);
        mBtMore.setVisibility(View.GONE);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowChargCodeActivity.this.finish();
            }
        });
        mHandler.sendEmptyMessage(1001);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPrice.setText(mGoods.getPrice()+"元");
            mMerchant.setText(mGoods.getInfo());
            mJifen.setText(mGoods.getIntegral()+"积分");
            mKucun.setText(mGoods.getRemainNum()+"");
            mTitle.setText("商品详情");
            getChargeCode(false);
        }
    };

    private void getChargeCode(final boolean loadMore) {
        KuaidiRequest kuaidiRequest = new KuaidiRequest();
        kuaidiRequest.setId(mGoods.getId());
        kuaidiRequest.setToken(new AppPreference(this).getLoginToken());
        DataLogic.getInstance().getChargCode(kuaidiRequest, new HttpCallBack<Object>() {
            @Override
            public void onCompleted(int type, Object codeBean) {

                ArrayList<CodeBean> data = (ArrayList<CodeBean>) codeBean;
                if (loadMore) {
                    for (CodeBean bean1: data) {
                        mCodeBeans.add(bean1);
                    }
                } else {
                    mCodeBeans.clear();
                    mCodeBeans = (ArrayList<CodeBean>) codeBean;
                }
                mLRecyclerView.refreshComplete(mCodeBeans.size());
                initAdapter(mCodeBeans);

            }

            @Override
            public void onError(int type, String fail) {
                ToastUtils.showToast(getApplicationContext(),"获取错误");
            }
        });
    }

    private void initAdapter(final ArrayList<CodeBean> mdata) {
            mCommonAdapter = new CommonAdapter<CodeBean>(ShowChargCodeActivity.this,R.layout.list_item_charge_code,mdata){

                @Override
                protected void convert(ViewHolder holder, CodeBean codeBean, int position) {
                    holder.setText(R.id.text_code,codeBean.getCode());
                    int status = codeBean.getStatus();
                    switch (status) {
                        case 0:
                            holder.setText(R.id.text_code_status,"未兑换");
                            break;
                        case 1:
                            holder.setText(R.id.text_code_status,"已经兑换");
                            break;
                    }
                }
            };
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        LRecyclerViewAdapter LRecyclerViewAdapter = new LRecyclerViewAdapter(mCommonAdapter);
        mLRecyclerView.setAdapter(LRecyclerViewAdapter);
    }

    @Override
    public void onLoadMore() {
        getChargeCode(true);
    }

    @Override
    public void onRefresh() {
        getChargeCode(false);
    }

    @Override
    public void reload() {
        getChargeCode(false);
    }
}
