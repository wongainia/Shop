package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zhenghaikj.shop.R;

import androidx.fragment.app.Fragment;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoFragment extends Fragment {

    private String url;
    private PhotoView mPhotoView;

    /**
     * 获取这个fragment需要展示图片的url
     * @param url
     * @return
     */
    public static PhotoFragment newInstance(String url) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_img, container, false);
        mPhotoView = view.findViewById(R.id.photoview);
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER);
        mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return true;
            }
        });
        mPhotoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
            }
        });

        RequestOptions options=new RequestOptions().fitCenter().error(R.mipmap.ic_launcher);
        Glide.with(getContext())
                .load(url)
                .apply(options)
             //   .placeholder(R.mipmap.ic_launcher)//加载过程中图片未显示时显示的本地图片
               // .error(R.mipmap.ic_launcher)//加载异常时显示的图片
              //  .centerCrop()//图片图填充ImageView设置的大小
                 .into(mPhotoView);
        return view;
    }


}
