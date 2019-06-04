package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.mvp.contract.GiftDetailContract;
import com.zhenghaikj.shop.mvp.model.GiftDetailModel;
import com.zhenghaikj.shop.mvp.presenter.GiftDetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;


public class GiftsDetailActivity extends BaseActivity<GiftDetailPresenter, GiftDetailModel> implements View.OnClickListener, GiftDetailContract.View {


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
    @BindView(R.id.banner_goods)
    Banner mBannerGoods;
    @BindView(R.id.tv_vip)
    TextView mTvVip;
    @BindView(R.id.tv_good_money)
    TextView mTvGoodMoney;
    @BindView(R.id.tv_good_money_max)
    TextView mTvGoodMoneyMax;
    @BindView(R.id.tv_good_name)
    TextView mTvGoodName;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.tv_express_delivery)
    TextView mTvExpressDelivery;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
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
    private SPUtils spUtils;
    private String userKey;

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
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
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
            case R.id.ll_collect:

                break;

            case R.id.tv_addcart:


                break;
            case R.id.iv_cart:
                break;
            case R.id.tv_all_goods:

                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Throwable e) {
        mStateLayout.changeState(StateFrameLayout.NET_ERROR);
    }

    @Override
    public void GetGifts(GiftDetailResult Result) {
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
        mTvGoodMoney.setText(Result.getNeedIntegral()+"西瓜币");
        mTvGoodMoneyMax.setText(Result.getGiftValue()+"");
        mTvVip.setText(Result.getNeedGradeName()+"专享");
        mTvGoodName.setText(Result.getGiftName());
        mTvExpressDelivery.setText("有效期至："+Result.getEndDate());
        mTvSalesVolume.setText("已兑："+Result.getSumSales());

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
            mTvBuy.setText("我要兑换");
        }else{
            mTvBuy.setBackgroundColor(Color.parseColor("#808080"));
            mTvBuy.setText(Result.getCanNotBuyDes());
        }
        mStateLayout.changeState(StateFrameLayout.SUCCESS);
    }
}
