package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.QuickEntry;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.pop.POPManager;
import com.qiyukf.unicorn.api.pop.Session;
import com.qiyukf.unicorn.api.pop.SessionListEntrance;
import com.qiyukf.unicorn.api.pop.ShopEntrance;
import com.qiyukf.unicorn.api.pop.ShopInfo;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SessionAdapter;
import com.zhenghaikj.shop.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity2 extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;

    private SessionAdapter adapter;

    private List<Session> sessionList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message;
    }

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("消息");
        /**
         * 获取最近联系商家列表
         *
         * @return 最近联系商家列表
         */
        sessionList = POPManager.getSessionList();
        adapter = new SessionAdapter(R.layout.item_message, sessionList);
        mRvMessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvMessage.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 * 根据商家ID获取商家信息，如名称，logo
                 *
                 * @param shopId 商家ID
                 * @return 如果用户联系过该商家，返回商家信息，否则返回 null
                 */
                ShopInfo shopInfo = POPManager.getShopInfo(sessionList.get(position).getContactId());

                String title = shopInfo.getName();
/**
 * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
 * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
 */
                ConsultSource source = new ConsultSource("", "消息页面", "custom information string");
/**
 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
 * 如果返回为false，该接口不会有任何动作
 *
 * @param context 上下文
 * @param title   聊天窗口的标题
 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
 */
                source.shopEntrance=new ShopEntrance.Builder().setLogo(shopInfo.getAvatar()).setName(shopInfo.getName()).build();
                source.sessionListEntrance=new SessionListEntrance.Builder().build();
                source.quickEntryList = new ArrayList<>();
                source.quickEntryList.add(new QuickEntry(0, "查订单", ""));
                source.quickEntryList.add(new QuickEntry(1, "查物流", ""));
//                source.productDetail=new ProductDetail.Builder()
//                        .setTitle(result.getProduct().getProductName())
//                        .setPicture(result.getProduct().getImagePath().get(0))
//                        .setNote("￥"+result.getProduct().getMinSalePrice())
//                        .setDesc(result.getProduct().getProductName())
//                        .setUrl(result.getProduct().getProductId()+"")
//                        .setShow(1)
//                        .setAlwaysSend(true)
//                        .build();
//                source.shopId=result.getShop().getVShopId()+"";
                Unicorn.openServiceActivity(mActivity, title, source);
            }
        });
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
