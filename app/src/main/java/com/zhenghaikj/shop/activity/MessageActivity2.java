package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.m7.imkfsdk.KfStartHelper;
import com.m7.imkfsdk.MainActivity;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.utils.MoorUtils;
import com.qiyukf.unicorn.api.pop.Session;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SessionAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.AnnouncementDetail;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.entity.MessageReadResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.ArticleContract;
import com.zhenghaikj.shop.mvp.contract.MessageContract;
import com.zhenghaikj.shop.mvp.model.ArticleModel;
import com.zhenghaikj.shop.mvp.model.MessageModel;
import com.zhenghaikj.shop.mvp.presenter.ArticlePresenter;
import com.zhenghaikj.shop.mvp.presenter.MessagePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;

public class MessageActivity2 extends BaseActivity<ArticlePresenter, ArticleModel> implements View.OnClickListener , ArticleContract.View {

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
    @BindView(R.id.tv_chat_message)
    TextView mTvChatMessage;
    @BindView(R.id.ll_chat_message)
    LinearLayout mLlChatMessage;
    @BindView(R.id.ll_chat)
    LinearLayout mLlChat;
    @BindView(R.id.tv_work_order_message)
    TextView mTvWorkOrderMessage;
    @BindView(R.id.ll_workmessage)
    LinearLayout mLlWorkmessage;
    @BindView(R.id.ll_work_order_message)
    LinearLayout mLlWorkOrderMessage;
    @BindView(R.id.tv_trading_information)
    TextView mTvTradingInformation;
    @BindView(R.id.ll_transactionmessage)
    LinearLayout mLlTransactionmessage;
    @BindView(R.id.ll_transaction_news)
    LinearLayout mLlTransactionNews;
    @BindView(R.id.tv_system_information)
    TextView mTvSystemInformation;
    @BindView(R.id.ll_announcement)
    LinearLayout mLlAnnouncement;

    private SessionAdapter adapter;

    private List<Session> sessionList;
    private UserInfo.UserInfoDean userInfo;
    private QBadgeView workqBadgeView;
    private QBadgeView transactionqBadgeView;
    private QBadgeView chatBadgeView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_message2;
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
        mLlChat.setOnClickListener(this);
        if (isLogin) {
            mPresenter.GetUserInfoList(UserID, "1");
        }

        chatBadgeView = new QBadgeView(mActivity);
        chatBadgeView.bindTarget(mLlChatMessage);
        chatBadgeView.setBadgeGravity(Gravity.CENTER|Gravity.END);

        workqBadgeView = new QBadgeView(mActivity);
        workqBadgeView.bindTarget(mLlWorkmessage);
        workqBadgeView.setBadgeGravity(Gravity.CENTER|Gravity.END);


        transactionqBadgeView = new QBadgeView(mActivity);
        transactionqBadgeView.bindTarget(mLlTransactionmessage);
        transactionqBadgeView.setBadgeGravity(Gravity.CENTER|Gravity.END);

        mPresenter.GetOrderMessageList(UserID,"0","99","1");
        mPresenter.GetTransactionMessageList(UserID,"0","99","1");
        final KfStartHelper helper = new KfStartHelper(MessageActivity2.this);
//        helper.initSdkChat("87326950-b5a5-11e9-be6e-a515be030f55", "name", "i12345678");//腾讯云正式
        if (MoorUtils.isInitForUnread(mActivity)) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
//                    Toast.makeText(mActivity, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                    if (acount==0){
                        chatBadgeView.setVisibility(View.INVISIBLE);
                        return;
                    }else if (acount>=99){
                        chatBadgeView.setVisibility(View.VISIBLE);
                        chatBadgeView.setBadgeNumber(99);
                    }else {
                        chatBadgeView.setVisibility(View.VISIBLE);
                        chatBadgeView.setBadgeNumber(acount);
                    }

                }
            });
        } else {
            //未初始化，消息当然为 ：0
            Toast.makeText(mActivity, "还没初始化", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlWorkOrderMessage.setOnClickListener(this);
        mLlTransactionNews.setOnClickListener(this);
        mLlAnnouncement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_chat:
                Intent intent1 = new Intent(mActivity, MainActivity.class);
                if (isLogin) {
                    intent1.putExtra("userName", userInfo.getNickName());
                    intent1.putExtra("userId", userInfo.getUserID());

                    intent1.putExtra("userPic", userInfo.getAvator());
                } else {
                    intent1.putExtra("userName", "游客");
                    intent1.putExtra("userId", "123456789");
                    intent1.putExtra("userPic", R.drawable.default_avator);
                }

                startActivity(intent1);
                break;
            case R.id.ll_work_order_message://工单消息

                startActivity(new Intent(mActivity, OrderMessageActivity.class));
                break;
            case R.id.ll_transaction_news://交易信息
                startActivity(new Intent(mActivity, TransactionMessageActivity.class));
                break;
            case R.id.ll_announcement:
                Intent intent = new Intent(mActivity, MessageActivity.class);
                intent.putExtra("CategoryID","7");
                startActivity(intent);
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
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                userInfo = Result.getData().getData().get(0);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (isLogin) {
            if ("message".equals(name)) {
                if (MoorUtils.isInitForUnread(mActivity)) {
                    IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                        @Override
                        public void getUnRead(int acount) {
//                    Toast.makeText(mActivity, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                            if (acount==0){
                                chatBadgeView.setVisibility(View.INVISIBLE);
                                return;
                            }else if (acount>=99){
                                chatBadgeView.setVisibility(View.VISIBLE);
                                chatBadgeView.setBadgeNumber(99);
                            }else {
                                chatBadgeView.setVisibility(View.VISIBLE);
                                chatBadgeView.setBadgeNumber(acount);
                            }

                        }
                    });
                } else {
                    //未初始化，消息当然为 ：0
                    Toast.makeText(mActivity, "还没初始化", Toast.LENGTH_SHORT).show();
                }
            }

            if ("transaction_num".equals(name)){
                mPresenter.GetTransactionMessageList(UserID,"0","99","1");
            }else if ("order_num".equals(name)){
                mPresenter.GetOrderMessageList(UserID,"0","99","1");
            }
        }
    }

    @Override
    public void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getCount()==0){
                    workqBadgeView.setVisibility(View.INVISIBLE);
                    return;
                }else if (baseResult.getData().getCount()>=99){
                    workqBadgeView.setVisibility(View.VISIBLE);
                    workqBadgeView.setBadgeNumber(99);
                }else {
                    workqBadgeView.setVisibility(View.VISIBLE);
                    workqBadgeView.setBadgeNumber(baseResult.getData().getCount());
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getCount()==0){
                    transactionqBadgeView.setVisibility(View.INVISIBLE);
                    return;
                }else if (baseResult.getData().getCount()>=99){
                    transactionqBadgeView.setVisibility(View.VISIBLE);
                    transactionqBadgeView.setBadgeNumber(99);
                }else {
                    transactionqBadgeView.setVisibility(View.VISIBLE);
                    transactionqBadgeView.setBadgeNumber(baseResult.getData().getCount());
                }
                break;
            default:
                break;
        }
    }
}
