package com.zhenghaikj.shop.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gyf.barlibrary.ImmersionBar;
import com.m7.imkfsdk.KfStartHelper;
import com.m7.imkfsdk.MainActivity;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.utils.MoorUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.bugly.beta.Beta;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AddWorkOrderActivity;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.activity.LoginActivity;
import com.zhenghaikj.shop.activity.MessageActivity;
import com.zhenghaikj.shop.activity.MessageActivity2;
import com.zhenghaikj.shop.activity.SearchPreDetailActivity;
import com.zhenghaikj.shop.activity.StoreDetailActivity;
import com.zhenghaikj.shop.activity.TwoLevelActivity;
import com.zhenghaikj.shop.adapter.ExchageAdapter;
import com.zhenghaikj.shop.adapter.LimitedTimeAdapter;
import com.zhenghaikj.shop.adapter.NewHomeAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.HomeJsonResult;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.entity.LimitBuyListResult;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.HomeContract;
import com.zhenghaikj.shop.mvp.model.HomeModel;
import com.zhenghaikj.shop.mvp.presenter.HomePresenter;
import com.zhenghaikj.shop.utils.CommonUtil;
import com.zhenghaikj.shop.utils.GlideHomeBannerImageLoader;
import com.zhenghaikj.shop.utils.ZXingUtils;
import com.zhenghaikj.shop.widget.AnimationNestedScrollView;
import com.zhenghaikj.shop.widget.ArcGradualView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class HomeFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements View.OnClickListener, HomeContract.View {
    @BindView(R.id.img_icon_yu)
    ImageView mImgIconYu;
    @BindView(R.id.search_tv_title)
    TextView mSearchTvTitle; //标题
    @BindView(R.id.ll_member_code)
    LinearLayout mLlMemberCode;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.search_layout)
    RelativeLayout mSearchLayout;
    @BindView(R.id.search_tv_search)
    TextView mSearchTvSearch;
    @BindView(R.id.search_ll_search)
    LinearLayout mSearchLlSearch; //搜索框
    @BindView(R.id.search_rl_top)
    RelativeLayout mSearchRlTop;
    @BindView(R.id.banner_home)
    Banner mBannerHome;
    @BindView(R.id.iv_register)
    ImageView mIvRegister;
    @BindView(R.id.rv_panic)
    RecyclerView mRvPanic;
    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.search_sv_view)
    AnimationNestedScrollView mSearchSvView; //滑动内容
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_home)
    LinearLayout mLlhome;

    @BindView(R.id.view)
    View mView;


    @BindView(R.id.img_code)
    ImageView mImgcode;
    @BindView(R.id.img_message)
    ImageView mImgmessage;
    @BindView(R.id.img_search)
    ImageView mImgsearch;

    @BindView(R.id.header)
    TwoLevelHeader header;

    @BindView(R.id.search_rl_top_bg)
    RelativeLayout mSearchrl_top_bg;
    @BindView(R.id.tv_count_msg)
    TextView mTvCountMsg;
    @BindView(R.id.fl_message)
    FrameLayout mFlMessage;
    @BindView(R.id.header_view)
    ArcGradualView mHeaderView;


    private float LL_SEARCH_MIN_TOP_MARGIN, LL_SEARCH_MAX_TOP_MARGIN, LL_SEARCH_MAX_WIDTH, LL_SEARCH_MIN_WIDTH, TV_TITLE_MAX_TOP_MARGIN;
    private ViewGroup.MarginLayoutParams searchLayoutParams, titleLayoutParams;


    private List<ShopResult.GiftListNewBean> panicBuyList = new ArrayList<>();
    private List<Product> exchageList = new ArrayList<>();
    private List<LimitBuyListResult.ListBean> limitedTimeList = new ArrayList<>();
    private List<Product> categoryList = new ArrayList<>();

    private ArrayList<MenuItem> mMainMenus;
    private int fadingHeight = 600; // 当ScrollView滑动到什么位置时渐变消失（根据需要进行调整）
    private static final int START_ALPHA = 0;//scrollview滑动开始位置
    private static final int END_ALPHA = 255;//scrollview滑动结束位置
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LimitedTimeAdapter limitedTimeAdapter;
    private ExchageAdapter exchageAdapter;
    private List<HomeJsonResult.LModulesBean> modules;
    private List<HomeJsonResult.LModulesBean.ContentBean.DatasetBean> dataset = new ArrayList<>();
    private View under_review;
    private AlertDialog underReviewDialog;
    private Window window;
    private CustomShareListener mShareListener;
    private ShareAction mShareAction;
    private Intent intent;
    private List<String> ids;
    private String link;
    private String pageSize = "10";


    private NewHomeAdapter newHomeAdapter;
    private UserInfo.UserInfoDean userInfo;

    private float mSlope = 3.0f;//透明度斜率

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<HomeResult.ProductBean> mDatas = new ArrayList<>();
//    private Integer[] icons = new Integer[]{
//            R.mipmap.juxing, R.mipmap.juxing_one, R.mipmap.juxing_two, R.mipmap.juxing_three, R.mipmap.juxing_four, R.mipmap.juxing_five,
//            R.mipmap.juxing_six, R.mipmap.juxing_seven, R.mipmap.juxing_eight, R.mipmap.juxing_nine
//    };

    private Integer[] icons = new Integer[]{
            R.drawable.gift, R.drawable.qiandao,
            R.drawable.cmd, R.drawable.card
    };

//    private String[] names = new String[]{
//            "免费兑", "家电", "服装", "数码", "领券", "签到", "充值", "品牌", "发现", "分类"
//    };

    private String[] names = new String[]{
            "免费兑", "签到", "抽免单", "优惠券"
    };

//    private Integer[] picture = new Integer[]{
//            R.mipmap.duihuan, R.mipmap.bingxiang, R.mipmap.close, R.mipmap.shuma, R.mipmap.quan, R.mipmap.qiandao, R.mipmap.chongzhi, R.mipmap.pinpai,
//            R.mipmap.find, R.mipmap.leibie,
//    };

    private Integer[] picture = new Integer[]{
            R.mipmap.duihuan, R.mipmap.qiandao,
            R.drawable.cmd, R.mipmap.quan
    };
    private MenuAdapter mMainAdapter;
    //   private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private int pageNo = 1;

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home2;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {

        if (isLogin) {
            mPresenter.GetUserInfoList(UserID, "1");
        }
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(mActivity).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (snsPlatform.mShowWord.equals("复制文本")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();

                        } else {
                            RxPermissions rxPermissions = new RxPermissions(mActivity);
                            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                // 获取全部权限成功

                                                UMWeb web = new UMWeb("http://admin.xigyu.com/NewSign?phone=" + UserID + "&type=8");
                                                web.setTitle("西瓜鱼");
                                                web.setDescription("注册送西瓜币了！！！！！");
                                                web.setThumb(new UMImage(mActivity, R.drawable.shop));
                                                new ShareAction(mActivity).withMedia(web)
                                                        .setPlatform(share_media)
                                                        .setCallback(mShareListener)
                                                        .share();
                                            } else {
                                                // 获取全部权限失败
                                                ToastUtils.showShort("权限获取失败");
                                            }
                                        }
                                    });

                        }
                    }
                });


        newHomeAdapter = new NewHomeAdapter(R.layout.item_home_two, mDatas);
        mRvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvHome.setAdapter(newHomeAdapter);
        newHomeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_goods:
                        Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                        intent.putExtra("id", mDatas.get(position).getId());
                        startActivity(intent);
                        break;
                    case R.id.ll_gotoshop:
                        if (mDatas.get(position).getVshopId() == null) {
                            ToastUtils.showShort("该商家未申请微店");
                        } else {
                            Intent intent1 = new Intent(mActivity, StoreDetailActivity.class);
                            intent1.putExtra("VShopId", mDatas.get(position).getVshopId());
                            startActivity(intent1);
                        }

                        break;

                }
            }
        });


        mPresenter.GetList("4", pageSize, "1", userKey);
        mPresenter.Get();
        mPresenter.Get(Integer.toString(pageNo), pageSize);
        mPresenter.GetLismitBuyList(Integer.toString(pageNo), pageSize, "");


        limitedTimeAdapter = new LimitedTimeAdapter(R.layout.item_panic_buying, limitedTimeList);
        mRvPanic.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPanic.setAdapter(limitedTimeAdapter);
        limitedTimeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id", limitedTimeList.get(position).getProductId() + "");
                startActivity(intent);
            }
        });
        limitedTimeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(mActivity, PanicBuyingActivity.class);
////                intent.putExtra("id", limitedTimeList.get(position).getId());
//                startActivity(intent);

                Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id", limitedTimeList.get(position).getProductId() + "");
                startActivity(intent);
            }
        });


        mRefreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
           /*  0->255   0为透明  255为不透明
                      根据percent百分比显示隐藏头布局 0->1  1为完全透明*/

            /*header的拖拽*/
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                Log.d("======>Moving", String.valueOf(percent));

                Log.d("======>isDragging", String.valueOf(isDragging));
                Log.d("======>offset", String.valueOf(offset));
                Log.d("======>headerHeight", String.valueOf(headerHeight));
                Log.d("======>maxDragHeight", String.valueOf(maxDragHeight));

                if (percent < 1 / 3f) {
                    mImgIconYu.setImageAlpha((int) ((-percent * 765) + 255));
                    mImgcode.setImageAlpha((int) ((-percent * 765) + 255));
                    mImgmessage.setImageAlpha((int) ((-percent * 765) + 255));
                    mTvCountMsg.setTextColor(mTvCountMsg.getTextColors().withAlpha((int)((-percent*765)+255)));
                    mSearchTvTitle.setTextColor(mSearchTvTitle.getTextColors().withAlpha((int) ((-percent * 765) + 255)));
                    mSearchLlSearch.getBackground().setAlpha((int) ((-percent * 765) + 255));
                    mSearchTvSearch.setTextColor(mSearchTvSearch.getTextColors().withAlpha((int) ((-percent * 765) + 255)));
                    mImgsearch.setImageAlpha((int) ((-percent * 765) + 255));
                    mSearchRlTop.getBackground().setAlpha((int) ((-percent * 765) + 255));
                } else {
                    mImgIconYu.setImageAlpha(0);
                    mImgcode.setImageAlpha(0);
                    mImgmessage.setImageAlpha(0);
                    mTvCountMsg.setTextColor(mTvCountMsg.getTextColors().withAlpha(0));
                    mSearchTvTitle.setTextColor(mSearchTvTitle.getTextColors().withAlpha(0));
                    mSearchLlSearch.getBackground().setAlpha(0);
                    mSearchTvSearch.setTextColor(mSearchTvSearch.getTextColors().withAlpha(0));
                    mImgsearch.setImageAlpha(0);
                    mSearchRlTop.getBackground().setAlpha(0);
                }


            }

            /*刷新中*/
            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {
                Log.d("======>Released", "Released" + headerHeight + " " + maxDragHeight);
                mImgIconYu.setImageAlpha(0);
                mImgcode.setImageAlpha(0);
                mImgmessage.setImageAlpha(0);
                mSearchTvTitle.setTextColor(mSearchTvTitle.getTextColors().withAlpha(0));
                mSearchLlSearch.getBackground().setAlpha(0);
                mSearchTvSearch.setTextColor(mSearchTvSearch.getTextColors().withAlpha(0));
                mImgsearch.setImageAlpha(0);
                mSearchRlTop.getBackground().setAlpha(0);
                mTvCountMsg.getBackground().setAlpha(0);
                mTvCountMsg.setTextColor(mTvCountMsg.getTextColors().withAlpha(0));

            }

            @Override
            public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {
                Log.d("======>StartAnimator", "StartAnimator" + headerHeight + " " + maxDragHeight);
            }

            /*刷新完成*/
            @Override
            public void onHeaderFinish(RefreshHeader header, boolean success) {
                Log.d("======>Finish", "Finish" + success);


            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

            }

            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                mPresenter.Get(Integer.toString(pageNo), pageSize);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mDatas.clear();
                mPresenter.GetList("4", pageSize, "1", userKey);
                mPresenter.Get();
                mPresenter.Get(Integer.toString(pageNo), pageSize);
                mPresenter.GetLismitBuyList(Integer.toString(pageNo), pageSize, "");
                final KfStartHelper helper = new KfStartHelper(mActivity);
//        helper.initSdkChat("87326950-b5a5-11e9-be6e-a515be030f55", "name", "i12345678");//腾讯云正式
                if (MoorUtils.isInitForUnread(mActivity)) {
                    IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                        @Override
                        public void getUnRead(int acount) {
//                    Toast.makeText(mActivity, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                            if (acount==0){
                                mTvCountMsg.setVisibility(View.GONE);
                            }else {
                                mTvCountMsg.setText(acount+"");
                                mTvCountMsg.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                } else {
                    //未初始化，消息当然为 ：0
                    Toast.makeText(mActivity, "还没初始化", Toast.LENGTH_SHORT).show();
                }
                refreshLayout.setNoMoreData(false);
                refreshLayout.finishRefresh(1000);
            }

            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

                Log.d("=======>StateChanged", String.valueOf(refreshLayout.getState().isTwoLevel));
                Log.d("=======>isDragging", String.valueOf(refreshLayout.getState().isDragging));
                Log.d("=======>newState", String.valueOf(newState.isTwoLevel));
                Log.d("=======>oldState", String.valueOf(oldState.isTwoLevel));

                Log.d("1=======>isFinishing", String.valueOf(newState.isFinishing));
                Log.d("2=======>isFinishing", String.valueOf(refreshLayout.getState().isFinishing));
                Log.d("3=======>isFinishing", String.valueOf(oldState.isFinishing));

                Log.d("1=isReleaseToOpening", String.valueOf(newState.isReleaseToOpening));
                Log.d("2=isReleaseToOpening", String.valueOf(refreshLayout.getState().isReleaseToOpening));
                Log.d("3=isReleaseToOpening", String.valueOf(oldState.isReleaseToOpening));


                if (!refreshLayout.getState().isDragging) {
                    if (oldState.isTwoLevel) {
                        if (oldState.isReleaseToOpening) {
                            startActivity(new Intent(mActivity, TwoLevelActivity.class));
                            mActivity.overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
                        }
                        header.finishTwoLevel();

                    }
                }


            }
        });
    }


    @Override
    protected void initView() {

        searchLayoutParams = (ViewGroup.MarginLayoutParams) mSearchLlSearch.getLayoutParams();
        titleLayoutParams = (ViewGroup.MarginLayoutParams) mSearchTvTitle.getLayoutParams();

        LL_SEARCH_MIN_TOP_MARGIN = CommonUtil.dp2px(mActivity, 4.5f);//布局关闭时顶部距离
        LL_SEARCH_MAX_TOP_MARGIN = CommonUtil.dp2px(mActivity, 49f);//布局默认展开时顶部距离
        LL_SEARCH_MAX_WIDTH = CommonUtil.getScreenWidth(mActivity) - CommonUtil.dp2px(mActivity, 30f);//布局默认展开时的宽度
        LL_SEARCH_MIN_WIDTH = CommonUtil.getScreenWidth(mActivity) - CommonUtil.dp2px(mActivity, 95f);//布局关闭时的宽度
        TV_TITLE_MAX_TOP_MARGIN = CommonUtil.dp2px(mActivity, 11.5f);


        Beta.checkUpgrade(false, false);

        final KfStartHelper helper = new KfStartHelper(mActivity);
//        helper.initSdkChat("87326950-b5a5-11e9-be6e-a515be030f55", "name", "i12345678");//腾讯云正式
        if (MoorUtils.isInitForUnread(mActivity)) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
//                    Toast.makeText(mActivity, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                    if (acount==0){
                        mTvCountMsg.setVisibility(View.GONE);
                    }else {
                        mTvCountMsg.setText(acount+"");
                        mTvCountMsg.setVisibility(View.VISIBLE);
                    }

                }
            });
        } else {
            //未初始化，消息当然为 ：0
            Toast.makeText(mActivity, "还没初始化", Toast.LENGTH_SHORT).show();
        }
    }


    //初始化数据
    protected void init() {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("更新登录信息".equals(name)) {
            getLoginMsg();
            mPresenter.GetList("4", pageSize, "1", userKey);
        }
        if ("UpdateReadCount".equals(name)) {
            mPresenter.GetList("4", pageSize, "1", userKey);
        }
        if ("message".equals(name)) {
            final KfStartHelper helper = new KfStartHelper(mActivity);
//        helper.initSdkChat("87326950-b5a5-11e9-be6e-a515be030f55", "name", "i12345678");//腾讯云正式
            if (MoorUtils.isInitForUnread(mActivity)) {
                IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                    @Override
                    public void getUnRead(int acount) {
//                    Toast.makeText(mActivity, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                        if (acount==0){
                            mTvCountMsg.setVisibility(View.GONE);
                        }else {
                            mTvCountMsg.setText(acount+"");
                            mTvCountMsg.setVisibility(View.VISIBLE);
                        }

                    }
                });
            } else {
                //未初始化，消息当然为 ：0
                Toast.makeText(mActivity, "还没初始化", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void setListener() {

        mLlMessage.setOnClickListener(this);
        mLlMemberCode.setOnClickListener(this);
        mIvRegister.setOnClickListener(this);
        mSearchLlSearch.setOnClickListener(this);

        mSearchSvView.setOnAnimationScrollListener(new AnimationNestedScrollView.OnAnimationScrollChangeListener() {
            @Override
            public void onScrollChanged(float dy) {
                float searchLayoutNewTopMargin = LL_SEARCH_MAX_TOP_MARGIN - dy;
                float searchLayoutNewWidth = LL_SEARCH_MAX_WIDTH - dy * 2.0f;//此处 * 1.3f 可以设置搜索框宽度缩放的速率
                float titleNewTopMargin = (float) (TV_TITLE_MAX_TOP_MARGIN - dy * 0.5);
                //处理布局的边界问题

                searchLayoutNewWidth = searchLayoutNewWidth < LL_SEARCH_MIN_WIDTH ? LL_SEARCH_MIN_WIDTH : searchLayoutNewWidth;
                if (searchLayoutNewTopMargin < LL_SEARCH_MIN_TOP_MARGIN) {
                    searchLayoutNewTopMargin = LL_SEARCH_MIN_TOP_MARGIN;
                }

                if (searchLayoutNewWidth < LL_SEARCH_MIN_WIDTH) {
                    searchLayoutNewWidth = LL_SEARCH_MIN_WIDTH;
                }

                float titleAlpha = 255 * titleNewTopMargin / TV_TITLE_MAX_TOP_MARGIN;
                if (titleAlpha < 0) {
                    titleAlpha = 0;
                }

                //设置相关控件的LayoutParams  此处使用的是MarginLayoutParams，便于设置params的topMargin属性
                mSearchTvTitle.setTextColor(mSearchTvTitle.getTextColors().withAlpha((int) titleAlpha));
                titleLayoutParams.topMargin = (int) titleNewTopMargin;
                mSearchTvTitle.setLayoutParams(titleLayoutParams);

                searchLayoutParams.topMargin = (int) searchLayoutNewTopMargin;
                searchLayoutParams.width = (int) searchLayoutNewWidth;
                mSearchLlSearch.setLayoutParams(searchLayoutParams);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_ll_search:
                // startActivity(new Intent(mActivity, SearchDetailActivity.class));
                startActivity(new Intent(mActivity, SearchPreDetailActivity.class));

                break;
            case R.id.ll_member_code:
                under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_share, null);
                Button btn_share_one = under_review.findViewById(R.id.btn_share_one);
                ImageView iv_code_one = under_review.findViewById(R.id.iv_code_one);
                Button btn_go_to_the_mall = under_review.findViewById(R.id.btn_go_to_the_mall);
                Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/NewSign?phone=" + UserID + "&type=8", 600, 600, BitmapFactory.decodeResource(getResources(), R.drawable.shop));
                iv_code_one.setImageBitmap(bitmap);
                btn_share_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        underReviewDialog.dismiss();
                        mShareAction.open();
                    }
                });

                btn_go_to_the_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        openShopApp("com.zhenghaikj.shop");
                        underReviewDialog.dismiss();
                    }
                });

                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
                        .create();
                underReviewDialog.show();
                window = underReviewDialog.getWindow();
//                window.setContentView(under_review);
                WindowManager.LayoutParams lp = window.getAttributes();
                window.setAttributes(lp);
//                window.setDimAmount(0.1f);
                window.setBackgroundDrawable(new ColorDrawable());
                break;
            case R.id.iv_register:
                if (!isLogin) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                } else {
                    startActivity(new Intent(mActivity, AddWorkOrderActivity.class));
                }

                break;
            case R.id.ll_message:
//                if (!isLogin) {
//                    startActivity(new Intent(mActivity, LoginActivity.class));
//                } else {
//                    intent = new Intent(mActivity, MessageActivity2.class);
////                    intent.putExtra("categoryId","4");
////                    intent.putExtra("title","消息");
//                    startActivity(intent);
//                Intent intent1 = new Intent(mActivity, MainActivity.class);
////                intent1.putExtra("goodsName",result.getProduct().getProductName());
////                intent1.putExtra("goodsPricture",result.getProduct().getImagePath().get(0));
////                intent1.putExtra("goodsPrice","￥" + result.getProduct().getMinSalePrice());
////                intent1.putExtra("goodsURL","http://seller.xigyu.com/product/detail/"+result.getProduct().getProductId());
//                if (isLogin) {
//                    intent1.putExtra("userName", userInfo.getNickName());
//                    intent1.putExtra("userId", userInfo.getUserID());
//
//                    intent1.putExtra("userPic", userInfo.getAvator());
//                } else {
//                    intent1.putExtra("userName", "游客");
//                    intent1.putExtra("userId", "123456789");
//                    intent1.putExtra("userPic", R.drawable.default_avator);
//                }
//
//                startActivity(intent1);
//                }
                if (!isLogin) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                } else {
                    intent = new Intent(mActivity, MessageActivity2.class);
                    intent.putExtra("categoryId", "4");
                    intent.putExtra("title", "消息");
                    startActivity(intent);
                }

//                String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=2701274443&version=1";
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
//                } catch (Exception e) {
//                    // 未安装手Q或安装的版本不支持    showToast("未安装手Q或安装的版本不支持");
//                }
                break;


        }
    }

    @Override
    public void GetList(Announcement result) {
      if (result.getCount() > 0) {
            mTvCountMsg.setVisibility(View.VISIBLE);
            mTvCountMsg.setText(result.getCount() + "");
        } else {
            mTvCountMsg.setVisibility(View.GONE);
        }
    }

    @Override
    public void Get(HomeResult Result) {
        if (Result.getSuccess()) {

            if (Result.getProduct().size() == 0) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                if (mDatas.size() != 0) {
                    int lastposition = mDatas.size();
                    int itemCount = Result.getProduct().size();
                    mDatas.addAll(Result.getProduct());
                    newHomeAdapter.notifyItemRangeChanged(lastposition, itemCount);
                } else {
                    mDatas.addAll(Result.getProduct());
                    newHomeAdapter.notifyDataSetChanged();
                }
                mRefreshLayout.finishLoadMore();
            }
            /*List<String> images = new ArrayList<>();
            for (int i = 0; i < Result.getSlide().size(); i++) {
                images.add(Result.getSlide().get(i).getImageUrl());
            }
            mBannerHome.setImageLoader(new GlideImageLoader());
            mBannerHome.setImages(images);
            mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
            mBannerHome.start();*/
        }
    }

    @Override
    public void Get(HomeJsonResult Result) {
        mLlhome.removeAllViews();
        modules = Result.getLModules();
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getType() == 9) {
                dataset = modules.get(i).getContent().getDataset();
            }

            if (modules.get(i).getType() == 8) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_home_8, null);
                mLlhome.addView(view);
                int position = i;
                initViewBy_8(position, view, modules);
            }
            if (modules.get(i).getType() == 20) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_home_20, null);
                mLlhome.addView(view);
                int position = i;
                initViewBy_20(position, view, modules);
            }
            if (modules.get(i).getType() == 22) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_home_22, null);
                mLlhome.addView(view);
                int position = i;
                initViewBy_22(position, view, modules);

            }

            if (modules.get(i).getType() == 21) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_home_21, null);
                mLlhome.addView(view);

                int position = i;
                initViewBy_21(position, view, modules);

            }

            if (modules.get(i).getType() == 23) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_home_23, null);
                mLlhome.addView(view);
                int position = i;
                initViewBy_23(position, view, modules);
            }
            if (modules.get(i).getType() == 24) {
                View view = LayoutInflater.from(mActivity).inflate(R.layout.activity_home_24, null);
                mLlhome.addView(view);
                int position = i;
                initViewBy_24(position, view, modules);
            }


        }
        List<String> images = new ArrayList<>();
        ids = new ArrayList<>();
        for (int i = 0; i < dataset.size(); i++) {
//            String str=dataset.get(i).getPic();
//            str=str.replace("http","https");
            images.add(dataset.get(i).getPic());
            if (dataset.get(i).getLinkType() == 1) {
                link = dataset.get(i).getLink();
                ids.add(link.substring(link.lastIndexOf("/") + 1));
            }
        }
        mBannerHome.setImageLoader(new GlideHomeBannerImageLoader());
        mBannerHome.setImages(images);
        mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
        mBannerHome.setDelayTime(6000);
        mBannerHome.start();
        mBannerHome.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (dataset.get(position).getLinkType() == 1) {
                    Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                    intent.putExtra("id", ids.get(position));
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void GetLismitBuyList(LimitBuyListResult Result) {
        limitedTimeList = Result.getList();
        limitedTimeAdapter.setNewData(limitedTimeList);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                userInfo = Result.getData().getData().get(0);
                break;
        }
    }

    public class MenuItem {
        Integer icon;
        String name;
        Integer picture;

        public MenuItem(Integer icon, String name, Integer picture) {
            this.icon = icon;
            this.name = name;
            this.picture = picture;
        }

        public Integer getIcon() {
            return icon;
        }

        public void setIcon(Integer icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPicture() {
            return picture;
        }

        public void setPicture(Integer picture) {
            this.picture = picture;
        }
    }

    public class MenuAdapter extends BaseQuickAdapter<MenuItem, BaseViewHolder> {
        public MenuAdapter(int layoutResId, List<MenuItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MenuItem item) {
            // 加载网络图片
            Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_home));
            Glide.with(mContext).load(item.getPicture()).into((ImageView) helper.getView(R.id.iv_goods));
            helper.setText(R.id.tv_home, item.getName());
        }
    }

    public static class CustomShareListener implements UMShareListener {
        private Context mContext;

        public CustomShareListener(Context context) {
            mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(mContext, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
//                    Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
//                Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

//            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }


    private void initViewBy_8(int position, View view, List<HomeJsonResult.LModulesBean> modules) {
        ImageView img_home_8_0 = view.findViewById(R.id.img_home_8_0);
        ImageView img_home_8_1 = view.findViewById(R.id.img_home_8_1);
        ImageView img_home_8_2 = view.findViewById(R.id.img_home_8_2);
        ImageView img_home_8_3 = view.findViewById(R.id.img_home_8_3);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(0).getPic()).into(img_home_8_0);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(1).getPic()).into(img_home_8_1);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(2).getPic()).into(img_home_8_2);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_8_3);

        //position为点击的模块所在的位置
        //判断所点击位置的功能
        img_home_8_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(0).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(1).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_8_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(2).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(3).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void initViewBy_23(int position, View view, List<HomeJsonResult.LModulesBean> modules) {
        ImageView img_home_23_0 = view.findViewById(R.id.img_home_23_0);
        ImageView img_home_23_1 = view.findViewById(R.id.img_home_23_1);
        ImageView img_home_23_2 = view.findViewById(R.id.img_home_23_2);
        ImageView img_home_23_3 = view.findViewById(R.id.img_home_23_3);
        ImageView img_home_23_4 = view.findViewById(R.id.img_home_23_4);
        ImageView img_home_23_5 = view.findViewById(R.id.img_home_23_5);
        ImageView img_home_23_6 = view.findViewById(R.id.img_home_23_6);
        ImageView img_home_23_7 = view.findViewById(R.id.img_home_23_7);
        ImageView img_home_23_8 = view.findViewById(R.id.img_home_23_8);

        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(0).getPic()).into(img_home_23_0);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(1).getPic()).into(img_home_23_1);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(2).getPic()).into(img_home_23_2);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_23_3);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_23_4);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_23_5);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_23_6);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_23_7);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_23_8);


        img_home_23_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(0).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_23_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(1).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_23_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(2).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_23_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(3).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_23_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(4).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_23_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(5).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_23_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(6).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_23_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(7).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(7).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(7).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(7).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(7).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_23_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(8).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(8).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(8).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(8).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(8).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    private void initViewBy_20(int position, View view, List<HomeJsonResult.LModulesBean> modules) {
        ImageView img_home_20_0 = view.findViewById(R.id.img_home_20_0);
        ImageView img_home_20_1 = view.findViewById(R.id.img_home_20_1);
        ImageView img_home_20_2 = view.findViewById(R.id.img_home_20_2);
        ImageView img_home_20_3 = view.findViewById(R.id.img_home_20_3);
        ImageView img_home_20_4 = view.findViewById(R.id.img_home_20_4);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(0).getPic()).into(img_home_20_0);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(1).getPic()).into(img_home_20_1);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(2).getPic()).into(img_home_20_2);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_20_3);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(4).getPic()).into(img_home_20_4);

        img_home_20_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(0).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_20_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(1).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_20_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(2).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_20_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(3).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_20_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(4).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }

    private void initViewBy_22(int position, View view, List<HomeJsonResult.LModulesBean> modules) {
        ImageView img_home_22_0 = view.findViewById(R.id.img_home_22_0);
        ImageView img_home_22_1 = view.findViewById(R.id.img_home_22_1);
        ImageView img_home_22_2 = view.findViewById(R.id.img_home_22_2);
        ImageView img_home_22_3 = view.findViewById(R.id.img_home_22_3);
        ImageView img_home_22_4 = view.findViewById(R.id.img_home_22_4);
        ImageView img_home_22_5 = view.findViewById(R.id.img_home_22_5);
        ImageView img_home_22_6 = view.findViewById(R.id.img_home_22_6);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(0).getPic()).into(img_home_22_0);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(1).getPic()).into(img_home_22_1);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(2).getPic()).into(img_home_22_2);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_22_3);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(4).getPic()).into(img_home_22_4);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(5).getPic()).into(img_home_22_5);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(6).getPic()).into(img_home_22_6);
        img_home_22_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(0).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_22_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(1).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_22_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(2).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_22_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(3).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_22_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(4).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_22_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(5).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_22_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(6).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }


    private void initViewBy_21(int position, View view, List<HomeJsonResult.LModulesBean> modules) {
        ImageView img_home_21_0 = view.findViewById(R.id.img_home_21_0);
        ImageView img_home_21_1 = view.findViewById(R.id.img_home_21_1);
        ImageView img_home_21_2 = view.findViewById(R.id.img_home_21_2);
        ImageView img_home_21_3 = view.findViewById(R.id.img_home_21_3);
        ImageView img_home_21_4 = view.findViewById(R.id.img_home_21_4);
        ImageView img_home_21_5 = view.findViewById(R.id.img_home_21_5);
        ImageView img_home_21_6 = view.findViewById(R.id.img_home_21_6);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(0).getPic()).into(img_home_21_0);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(1).getPic()).into(img_home_21_1);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(2).getPic()).into(img_home_21_2);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(3).getPic()).into(img_home_21_3);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(4).getPic()).into(img_home_21_4);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(5).getPic()).into(img_home_21_5);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(6).getPic()).into(img_home_21_6);
        img_home_21_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(0).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_21_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(1).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_21_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(2).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        img_home_21_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(3).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


        img_home_21_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(4).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(4).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_21_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(5).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(5).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        img_home_21_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(6).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(6).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }

    private void initViewBy_24(int position, View view, List<HomeJsonResult.LModulesBean> modules) {
        ImageView img_home_24_0 = view.findViewById(R.id.img_home_24_0);
        Glide.with(mActivity).load(Config.URL_PIC + modules.get(position).getContent().getDataset().get(0).getPic()).into(img_home_24_0);
        img_home_24_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modules.get(position).getContent().getDataset().get(0).getLinkType()) {
                    case 1: //选择商品
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 3: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 27: //专题列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 6: //限时购列表
                        Toast.makeText(mActivity, "选中了" + "第" + position + "个" + modules.get(position).getContent().getDataset().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(mActivity, "暂未开发", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }


}
