package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Data:2019/8/6
 * Time:15:00
 * author:ying
 **/
public class PhotoViewActivity2 extends BaseActivity {
    @BindView(R.id.photoview)
    PhotoView photoview;

    @BindView(R.id.view)
    View view;
    private String PhotoUrl;

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.black);
        mImmersionBar.statusBarView(view);
        mImmersionBar.fitsSystemWindows(false);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_view_photos2;
    }

    @Override
    protected void initData() {
        PhotoUrl=getIntent().getStringExtra("PhotoUrl");
        Log.d("=====>",PhotoUrl);
    }
    @Override
    protected void initView() {
        photoview.setScaleType(ImageView.ScaleType.CENTER);
        photoview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return true;
            }
        });
        photoview.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
            }
        });

        RequestOptions options=new RequestOptions().fitCenter().error(R.mipmap.ic_launcher);
        Glide.with(mActivity)
                .load(PhotoUrl)
                .apply(options)
                //   .placeholder(R.mipmap.ic_launcher)//加载过程中图片未显示时显示的本地图片
                // .error(R.mipmap.ic_launcher)//加载异常时显示的图片
                //  .centerCrop()//图片图填充ImageView设置的大小
                .into(photoview);
    }

    @Override
    protected void setListener() {

    }
}
