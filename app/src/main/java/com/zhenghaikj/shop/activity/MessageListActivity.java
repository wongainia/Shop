package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MessageAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.AnnouncementDetail;
import com.zhenghaikj.shop.entity.MessageReadResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.MessageContract;
import com.zhenghaikj.shop.mvp.model.MessageModel;
import com.zhenghaikj.shop.mvp.presenter.MessagePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageListActivity extends BaseActivity<MessagePresenter, MessageModel> implements View.OnClickListener, MessageContract.View {


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
    @BindView(R.id.rv_type)
    RecyclerView mRvType;
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;
    private List<Announcement.RowsBean> list = new ArrayList<>();
    private MessageAdapter adapter;

//    private List<Session> list = new ArrayList<>();
//    private SessionAdapter adapter;

    private String categoryId;
    private String title;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message_list;
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
        title = getIntent().getStringExtra("title");
        mTvTitle.setText(title);
        categoryId = getIntent().getStringExtra("categoryId");
        mPresenter.GetList(categoryId, "10", "1", userKey);
//        for (int i = 0; i <10 ; i++) {
//            list.add(new Product());
//        }

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    @Override
    public void AddArticlRead(MessageReadResult result) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {

    }

    @Override
    public void GetList(Announcement result) {
        list.addAll(result.getRows());
        adapter = new MessageAdapter(R.layout.item_message, list);
        mRvMessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvMessage.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_message:
                        Intent intent = new Intent(mActivity, MessageDetailActivity.class);
                        intent.putExtra("messageid", String.valueOf(result.getRows().get(position).getId()));
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void GetDetail(AnnouncementDetail id) {

    }
}
