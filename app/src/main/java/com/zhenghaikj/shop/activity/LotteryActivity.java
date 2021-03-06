package com.zhenghaikj.shop.activity;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cretin.www.wheelsruflibrary.listener.RotateListener;
import com.cretin.www.wheelsruflibrary.view.WheelSurfView;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LotteryActivity extends BaseActivity implements View.OnClickListener
{


    @BindView(R.id.ws)
    WheelSurfView mWs;
    @BindView(R.id.tv_back)
    TextView mTvback;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_lottery;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        /**
         * 新增使用代码设置属性的方式
         *
         * 请注意：
         *  使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
         *  使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
         *  使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
         *
         *  重要的事情说三遍
         *
         *  例如
         *  <com.cretin.www.wheelsruflibrary.view.WheelSurfView
         *      android:id="@+id/wheelSurfView2"
         *      android:layout_width="match_parent"
         *      android:layout_height="match_parent"
         *      wheelSurfView:typenum="-1"
         *      android:layout_margin="20dp">
         *
         *  然后调用setConfig()方法来设置你的属性
         *
         * 请注意：
         *  你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
         *  你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
         *  你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
         *
         *  重要的事情说三遍
         *
         * 请注意：
         *  .setmColors(colors)
         *  .setmDeses(des)
         *  .setmIcons(mListBitmap)
         *  这三个方法中的参数长度必须一致 否则会报运行时异常
         */


        //获取第一个视图


        //颜色
        Integer[] colors = new Integer[]{
                Color.parseColor("#ffdecc"),
                Color.parseColor("#fbc6a9"),
                Color.parseColor("#ffdecc"),
                Color.parseColor("#fbc6a9"),
                Color.parseColor("#ffdecc"),
                Color.parseColor("#fbc6a9"),
                Color.parseColor("#ffdecc"),
                Color.parseColor("#fbc6a9"),
                Color.parseColor("#ffdecc"),
                Color.parseColor("#fbc6a9"),};
        //文字
        String[] des = new String[]{"1 西 瓜 币",
                                    "5 西 瓜 币",
                                    "免 单 机 会",
                                    "谢 谢 参 与",
                                    "1 0 西 瓜 币",
                                    "2 0 西 瓜 币",
                                    "5 0 西 瓜 币",
                                    "1 0 0 西 瓜 币",
                                    "2 0 0 西 瓜 币",
                                    "苹 果 手 机"};
        //图标
        List<Bitmap> mListBitmap = new ArrayList<>();
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_miandang));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_xiexie));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_money));
        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.iphone));
        //主动旋转一下图片
        mListBitmap = WheelSurfView.rotateBitmaps(mListBitmap);
        WheelSurfView wheelSurfView = (WheelSurfView) findViewById(R.id.ws);
        WheelSurfView.Builder build = new WheelSurfView.Builder()
        .setmColors(colors)
        .setmTextColor(R.color.bank_01)
        .setmDeses(des)
        .setmIcons(mListBitmap)
        .setmType(1)
        .setmTypeNum(10)
        .build();
        wheelSurfView.setConfig(build);


        //添加滚动监听
        wheelSurfView.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                Toast.makeText(LotteryActivity.this,  + position + "恭喜您抽中" + des, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {

                final CommonDialog_Home dialog_home = new CommonDialog_Home(mActivity);
                dialog_home.setMessage("今天还剩余3次机会抽免单 ")
                        .setTitle("抽奖")
                        .setSingle(false).
                        setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        //模拟位置
                        int position = new Random().nextInt(10) + 1;
                        wheelSurfView.startRotate(position);
                        dialog_home.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog_home.dismiss();
                    }
                }).show();

            }
        });


    }

    @Override
    protected void setListener() {
        mTvback.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                LotteryActivity.this.finish();
                break;
        }
    }
}
