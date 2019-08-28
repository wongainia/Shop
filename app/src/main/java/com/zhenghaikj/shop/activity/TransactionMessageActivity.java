package com.zhenghaikj.shop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.MessageAdapter2;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.mvp.contract.MyMessageContract;
import com.zhenghaikj.shop.mvp.model.MyMessageModel;
import com.zhenghaikj.shop.mvp.presenter.MyMessagePresenter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*交易消息页面*/
public class TransactionMessageActivity extends BaseActivity<MyMessagePresenter, MyMessageModel> implements MyMessageContract.View, View.OnClickListener {

    @BindView(R.id.rv_transactionmessage)
    RecyclerView mRvTransactionmessage;//未读

    @BindView(R.id.rv_transactionmessage_historical)
    RecyclerView mRv_transactionmessage_historical;//已读

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.tv_message_number)
    TextView mTv_message_number;
    @BindView(R.id.textView1)
    TextView mTextView1;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.rl_new_message)
    RelativeLayout mRlNewMessage;
    @BindView(R.id.tv_old_number)
    TextView mTvOldNumber;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.rl_old_message)
    RelativeLayout mRlOldMessage;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private String userId;
    private MessageAdapter2 messageAdapter;
    private MessageAdapter2 messagereadAdapter;
    private List<Message> list = new ArrayList<>();
    private List<Message> readlist = new ArrayList<>();//已读

    private int pageIndex = 1;
    private ZLoadingDialog dialog;

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
    protected int setLayoutId() {
        return R.layout.activity_transactionmessage;
    }

    @Override
    protected void initData() {
        dialog = new ZLoadingDialog(mActivity);
        mRvTransactionmessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvTransactionmessage.setHasFixedSize(true);
        mRvTransactionmessage.setNestedScrollingEnabled(false);
        messageAdapter = new MessageAdapter2(R.layout.item_message2, list);
        mRvTransactionmessage.setAdapter(messageAdapter);


        mRv_transactionmessage_historical.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv_transactionmessage_historical.setHasFixedSize(true);
        mRv_transactionmessage_historical.setNestedScrollingEnabled(false);
        messagereadAdapter = new MessageAdapter2(R.layout.item_message2, readlist);
        mRv_transactionmessage_historical.setAdapter(messagereadAdapter);


        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        showLoading();
        mPresenter.GetMessageList(userId, "1", "0", "999","1");
        mPresenter.GetReadMessageList(userId, "1", "0", "999", "1");


    }

    @Override
    protected void initView() {
        mTvTitle.setText("交易信息");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex = 1;
                //list.clear();
                mPresenter.GetMessageList(userId, "1", "0", "999", Integer.toString(pageIndex));
//                mPresenter.GetReadMessageList(userId, "1", "0", "10", String.valueOf(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上拉加载更多
//        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                pageIndex++; //页数加1
////                  mPresenter.WorkerGetOrderList(userID,"1",Integer.toString(pageIndex),"5");
//                mPresenter.GetMessageList(userId, "1", "0", "10", Integer.toString(pageIndex));
//                mPresenter.GetReadMessageList(userId, "1", "0", "10", String.valueOf(pageIndex));
//                messageAdapter.notifyDataSetChanged();
//                refreshLayout.finishLoadMore();
//            }
//        });
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    case R.id.ll_order_message:
                        mPresenter.AddOrUpdatemessage(((Message) adapter.getData().get(position)).getMessageID(), "2");


                        break;

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {

                    mTv_message_number.setText("暂无新消息");
                    mRlNewMessage.setVisibility(View.GONE);
                    return;

                } else {
                    list.clear();
                    list.addAll(baseResult.getData().getData());

                    if (baseResult.getData().getData().size() > 99) {
                        mTv_message_number.setText("您有99+条新消息");
                    } else if (baseResult.getData().getData().size() == 0) {
                        mTv_message_number.setText("暂无新消息");
                        mRlNewMessage.setVisibility(View.GONE);
                    } else {
                        mTv_message_number.setText("您有" + baseResult.getData().getData().size() + "条新消息");
                    }

                    messageAdapter.notifyDataSetChanged();
                }
                if (baseResult.getData().getCount() == 0) {

                    EventBus.getDefault().post("transactionempty");

                }

                cancleLoading();

                break;
            default:
                break;
        }
    }

    @Override
    public void GetReadMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {
                    return;

                } else {


                    messagereadAdapter.setNewData(baseResult.getData().getData());
                    messagereadAdapter.notifyDataSetChanged();
                }
                cancleLoading();
                break;
            default:
                break;
        }
    }

    @Override
    public void AddOrUpdatemessage(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    EventBus.getDefault().post("transaction_num");
                    mPresenter.GetMessageList(userId, "1", "0", "999", "1");
                    mPresenter.GetReadMessageList(userId, "1", "0", "999", "1");
                }
                break;
            default:
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
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("加载中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }
    public void cancleLoading() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }

}
