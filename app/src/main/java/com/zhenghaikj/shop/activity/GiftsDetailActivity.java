package com.zhenghaikj.shop.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.fragment.MineFragment;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.mvp.contract.GiftDetailContract;
import com.zhenghaikj.shop.mvp.model.GiftDetailModel;
import com.zhenghaikj.shop.mvp.presenter.GiftDetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.AdderView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


public class GiftsDetailActivity extends BaseActivity<GiftDetailPresenter, GiftDetailModel> implements View.OnClickListener, GiftDetailContract.View {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;

    @BindView(R.id.tv_back)
    TextView mTvback;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner_goods)
    Banner mBannerGoods;

    @BindView(R.id.tv_haschange)
    TextView mTvhaschange;

    @BindView(R.id.tv_max_change)
    TextView mTvmax_change;

    @BindView(R.id.tv_inventory)
    TextView mTvinventory;
    @BindView(R.id.icon_share)
    ImageView mIconshare;

    @BindView(R.id.tv_good_money)
    TextView mTvGoodMoney;
    @BindView(R.id.tv_good_money_max)
    TextView mTvGoodMoneyMax;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;

    @BindView(R.id.one)
    LinearLayout mOne;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.three)
    LinearLayout mThree;
    @BindView(R.id.sv)
    ScrollView mSv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.stateLayout)
    StateFrameLayout mStateLayout;
    private String giftId;
    private Intent intent;
    private GiftDetailResult result;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private AdderView adderView;
    private ShareAction mShareAction;
    private MineFragment.CustomShareListener mShareListener;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift_detail;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("礼品详情");
        giftId =getIntent().getStringExtra("giftId");
        mPresenter.GetGifts(giftId,userKey);
        mStateLayout.changeState(StateFrameLayout.LOADING);
        //是否在展示内容布局的时候开启动画（200ms的Alpha动画）
        mStateLayout.enableContentAnim(false);

        //设置网络错误重试监听【不传netRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_net_error_retry"】
        mStateLayout.setOnNetErrorRetryListener(new StateFrameLayout.OnNetErrorRetryListener() {
            @Override
            public void onNetErrorRetry() {
                //TODO 在这里相应重试操作
                mPresenter.GetGifts(giftId,userKey);
            }
        });
        //设置空数据重试监听【不传emptyRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_empty_retry"】
        mStateLayout.setOnEmptyRetryListener(new StateFrameLayout.OnEmptyRetryListener() {
            @Override
            public void onEmptyRetry() {
                //TODO 在这里相应重试操作
                mPresenter.GetGifts(giftId,userKey);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.GetGifts(giftId,userKey);
                mRefreshLayout.finishRefresh(1000);
            }
        });

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mTvback.setOnClickListener(this);
        mIconshare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_buy:
                if (isLogin){
                    showPopupWindow();
                }else{
                    startActivity(new Intent(mActivity,LoginActivity.class));
                }
                break;
            case R.id.icon_share:
                mShareAction.open();
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Throwable e) {
        mStateLayout.changeState(StateFrameLayout.NET_ERROR);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String msg) {
        if ("GiftDetail".equals(msg)||"更新登录信息".equals(msg)){
            getLoginMsg();
            mPresenter.GetGifts(giftId,userKey);
        }
    }

    @Override
    public void GetGifts(GiftDetailResult Result) {
        result = Result;
        /*ImagePath顶部图片轮播*/
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < Result.getImages().size(); i++) {
            images.add(Result.getImages().get(i));
        }
        mBannerGoods.setImageLoader(new GlideImageLoader());
        mBannerGoods.setImages(images);
        mBannerGoods.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBannerGoods.setIndicatorGravity(BannerConfig.CENTER);
        mBannerGoods.setDelayTime(4000);

        mBannerGoods.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(mActivity, PhotosViewActivity.class);
                intent.putExtra("photo_list", (Serializable) images);
                intent.putExtra("photo_position", position);
                startActivity(intent);


            }
        });
        mBannerGoods.start();
        mTvGoodMoney.setText(Result.getNeedIntegral()+"");
        mTvGoodMoneyMax.setText(Result.getGiftValue()+"");

        String money =Result.getGiftValue()+"";
        SpannableString sp = new SpannableString(money);
        sp.setSpan(new StrikethroughSpan(), 0, money.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvGoodMoneyMax.setText(sp);

        mTvGoodName.setText(Result.getGiftName());

        mTvhaschange.setText("已兑换: "+Result.getSumSales()+"件");
        mTvmax_change.setText("每人限兑: "+Result.getShowLimtQuantity()+"件");
        mTvinventory.setText("库存:"+Result.getStockQuantity());


        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "\n" +
                "\t<title>detail</title>\n" +
                "\n" +
                "\t<style>body{border:0;padding:0;margin:0;}img{max-width:100%;border:0;display:block;vertical-align: middle;padding:0;margin:0;}p{border:0;padding:0;margin:0;}div{border:0;padding:0;margin:0;}</style>\n" +
                "</head>"
                + "<body>"
                + Result.getDescription() + "</body>" + "</html>";

        mWebview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(new WebChromeClient());

        if (Result.isCanBuy()){
            mTvBuy.setBackgroundColor(Color.parseColor("#E82C00"));
            mTvBuy.setText("立即兑换");
            mTvBuy.setEnabled(true);
        }else{
            mTvBuy.setBackgroundColor(Color.parseColor("#808080"));
            mTvBuy.setText(Result.getCanNotBuyDes());
            mTvBuy.setEnabled(false);
        }


        mStateLayout.changeState(StateFrameLayout.SUCCESS);



        mShareAction = new ShareAction((Activity) mActivity).setDisplayList(
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
                            RxPermissions rxPermissions = new RxPermissions((Activity) mActivity);
                            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .subscribe(new Consumer<Boolean>() {
                                        @Override
                                        public void accept(Boolean aBoolean) throws Exception {
                                            if (aBoolean) {
                                                // 获取全部权限成功

                                                UMWeb web = new UMWeb("http://mall.xigyu.com/product/detail/"+result.getId());
                                                web.setTitle(result.getGiftName());
                                                web.setThumb(new UMImage(mActivity, result.getImages().get(0)));
                                                new ShareAction((Activity) mActivity).withMedia(web)
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



    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_choose_num, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adderView = popupWindow_view.findViewById(R.id.adderview);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                if (!"不限".equals(result.getShowLimtQuantity())){
                    if (value>Integer.parseInt(result.getShowLimtQuantity())){
                        ToastUtils.showShort("每人限兑"+result.getShowLimtQuantity()+"件");
                        adderView.setValue(Integer.parseInt(result.getShowLimtQuantity()));
                    }
                }

                if (value>result.getStockQuantity()){
                    ToastUtils.showShort("不能大于库存");
                    adderView.setValue(result.getStockQuantity());
                }
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });

        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
        popupWindow_view.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                intent = new Intent(mActivity, ConfirmGiftOrderActivity.class);
                intent.putExtra("id", Integer.toString(result.getId()));
                intent.putExtra("count", Integer.toString(adderView.getValue()));
                startActivity(intent);
            }
        });

    }
}
