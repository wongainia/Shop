package com.zhenghaikj.shop.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.PhotoPagerAdapter;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static com.zhenghaikj.shop.utils.ImgUtils.donwloadImg;

public class PhotosViewActivity extends AppCompatActivity {
    private static final String TAG = "EvaluationDetailsActivity";
    private ViewPager viewPager;
    private TextView tvNum;
    private ImageView img_return;
    private ArrayList<String> urlList;
    private TextView tv_save;
    private List<String> photo_list;
    private int photo_position;
    private Bitmap bitmap;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);
        initView();

    }

    private void initView() {
        viewPager = findViewById(R.id.photo_viewpager);
        tvNum = findViewById(R.id.tv_num);
        img_return = findViewById(R.id.img_return);
        tv_save = findViewById(R.id.tv_save);

        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotosViewActivity.this.finish();
            }
        });


        photo_list = (List<String>) getIntent().getSerializableExtra("photo_list");
        photo_position = getIntent().getIntExtra("photo_position", 1);

        //需要加载的网络图片
        urlList = new ArrayList<>();
        urlList.addAll(photo_list);


        PhotoPagerAdapter viewPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager(), urlList);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(photo_position);
        tvNum.setText("(" + String.valueOf(photo_position + 1) + "/" + urlList.size() + ")");

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNum.setText("(" + String.valueOf(position + 1) + "/" + urlList.size() + ")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

            tv_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                donwloadImg(PhotosViewActivity.this, urlList.get(photo_position));
                }
            });
        }

}
