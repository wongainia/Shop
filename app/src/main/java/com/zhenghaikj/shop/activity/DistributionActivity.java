package com.zhenghaikj.shop.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.DistributionAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Distribution;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.fragment.MineFragment;
import com.zhenghaikj.shop.mvp.contract.DistributionContract;
import com.zhenghaikj.shop.mvp.model.DistributionModel;
import com.zhenghaikj.shop.mvp.presenter.DistributionPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class DistributionActivity extends BaseActivity<DistributionPresenter, DistributionModel> implements View.OnClickListener, DistributionContract.View {
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
    @BindView(R.id.rv_distribution)
    RecyclerView mRvDistribution;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartrefresh;
    private DistributionAdapter adapter;
    private ShareAction mShareAction;
    private CustomShareListener mShareListener;
    private PersonalInformation userInfo;

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

    private List<Distribution> list = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_distribution;
    }

    @Override
    protected void initData() {
//        for (int i = 0; i < 5; i++) {
//            list.add(new Distribution());
//        }

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(mActivity).setShareConfig(config);
        mShareListener = new CustomShareListener(mActivity);

        adapter = new DistributionAdapter(R.layout.item_distribution, list);
        mRvDistribution.setLayoutManager(new GridLayoutManager(mActivity,2));
        mRvDistribution.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_look_similar:
                        /*增加自定义按钮的分享面板*/
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

                                                                UMWeb web = new UMWeb("http://mall.xigyu.com/product/detail/" + list.get(position).getProductId()+"?partnerid="+userInfo.getUserId());
                                                                web.setTitle(list.get(position).getProductName());
                                                                web.setDescription(list.get(position).getProductName());
                                                                web.setThumb(new UMImage(mActivity, list.get(position).getShareImageUrl()));
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
                        mShareAction.open();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的分销");
        mTvSave.setText("找商品");
        mTvSave.setVisibility(View.VISIBLE);

        mPresenter.PersonalInformation(userKey);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_save:
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra("Url", "http://seller.xigyu.com/m-wap/DistributionMarket/");
                intent.putExtra("Title", "分销商品");
                startActivity(intent);
                break;

        }
    }

    @Override
    public void ProductList(List<Distribution> Result) {
        list.addAll(Result);
        adapter.setNewData(list);
    }

    @Override
    public void PersonalInformation(PersonalInformation result) {
        if (result.isSuccess()){
            userInfo = result;
            mPresenter.ProductList(result.getUserId(),userKey);
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

}
