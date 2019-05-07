package com.zhenghaikj.shop.activity;
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

public class PhotosViewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView tvNum;
    private ImageView img_return;
    private ArrayList<String> urlList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);
        initView();

    }

    private void initView() {
        viewPager = findViewById(R.id.photo_viewpager);
        tvNum = findViewById(R.id.tv_num);
        img_return=findViewById(R.id.img_return);

        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotosViewActivity.this.finish();
            }
        });



        List<String> photo_list = (List<String>) getIntent().getSerializableExtra("photo_list");
        int photo_position = getIntent().getIntExtra("photo_position", 1);

        //需要加载的网络图片
        urlList = new ArrayList<>();
        urlList.addAll(photo_list);


        PhotoPagerAdapter viewPagerAdapter = new PhotoPagerAdapter(getSupportFragmentManager(), urlList);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setCurrentItem(photo_position);
        tvNum.setText("("+String.valueOf(photo_position+1) + "/" + urlList.size()+")");

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNum.setText("("+String.valueOf(position + 1) + "/" + urlList.size()+")");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




}
