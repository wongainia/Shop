package com.zhenghaikj.shop.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yalantis.ucrop.UCrop;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.photoadapter.CommentAdapter;
import com.zhenghaikj.shop.adapter.EvaluateAdapter;
import com.zhenghaikj.shop.entity.CommentsInfo;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.mvp.contract.EvaluateContract;
import com.zhenghaikj.shop.mvp.model.EvaluateModel;
import com.zhenghaikj.shop.mvp.presenter.EvaluatePresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.utils.imageutil.CompressHelper;
import com.zhenghaikj.shop.utils.imageutil.Glide4Engine;
import com.zhenghaikj.shop.widget.StarBarView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class EvaluateActivity extends BaseActivity<EvaluatePresenter, EvaluateModel> implements EvaluateContract.View, CommentAdapter.OnStatusListener {
    @BindView(R.id.rv_rvaluate)
    RecyclerView mRvrvaluate;

    ArrayList<CommentsInfo> commentslist=new ArrayList<>();

    List<EvaluateResult.ProductBean> list=new ArrayList<>();
     private CommentAdapter commentAdapter;
    private StarBarView baozhuang_star,sudu_star,peisong_star;
    private TextView tv_baozhuang,tv_sudu,tv_peisong;
    private EvaluateAdapter evaluateAdapter;

    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private View view;
    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private String FilePath;
    private ArrayList<String> permissions;
    private int size;
    private List<Uri> mSelected;
    private Uri uri;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initData() {
        Userkey = spUtils.getString("UserKey");
        String orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetComment(orderID,Userkey);

    }

    @Override
    protected void initView() {
        view= LayoutInflater.from(mActivity).inflate(R.layout.item_evaluate_store,null);

    }

    @Override
    protected void setListener() {

    }

    /*获取 “评论” 详情*/
    @Override
    public void GetComment(EvaluateResult Result) {
        if (Result.isSuccess()){


            for(int i=0;i<Result.getProduct().size();i++){
                CommentsInfo commentsInfo = new CommentsInfo();
                List<String> commentImgs = new ArrayList<>();
                commentImgs.add("");
                commentsInfo.setCommentImgs(commentImgs);
                commentslist.add(commentsInfo);
            }

            list.addAll(Result.getProduct());
            mRvrvaluate.setLayoutManager(new LinearLayoutManager(mActivity));
            commentAdapter = new CommentAdapter(list,mActivity,commentslist);
            commentAdapter.setOnStatusListener(this);
            mRvrvaluate.setAdapter(commentAdapter);




        }

    }

    @Override
    public void UploadPicEvaluate(String Result) {

    }


    /**
     * 设置星星文字
     * */
    private void setStarName(TextView textView, float star_num) {
        if (star_num==5.0f){
            textView.setText("非常好");
        }else if (star_num==4.0f){
            textView.setText("很好");
        }else if (star_num==3.0f){
            textView.setText("一般");
        }else if (star_num==2.0f){
            textView.setText("很差");
        }else if (star_num==1.0f){
            textView.setText("非常差");
        }

    }

    @Override
    public void onSetStatusListener(int pos) {
      if(commentslist.get(pos).getCommentImgs().size()<5){
            CommentsInfo commentsInfo = commentslist.get(pos);
            List<String> commentImgs = commentsInfo.getCommentImgs();
            commentImgs.add(0,"http://img.xsmore.com/cjyp/Product/2ef24a07-f3b0-4497-8549-2acc0f7ca4b0.jpg");
            commentsInfo.setCommentImgs(commentImgs);
            commentslist.set(pos,commentsInfo);
            commentAdapter.notifyItemChanged(pos);
        }else if(commentslist.get(pos).getCommentImgs().size()==5){
            CommentsInfo commentsInfo = commentslist.get(pos);
            List<String> commentImgs = commentsInfo.getCommentImgs();
            commentImgs.set(commentImgs.size()-1,"http://img.xsmore.com/cjyp/Product/2ef24a07-f3b0-4497-8549-2acc0f7ca4b0.jpg");
            commentsInfo.setCommentImgs(commentImgs);
            commentslist.set(pos,commentsInfo);
            commentAdapter.notifyItemChanged(pos);
        }



    }

    @Override
    public void onDeleteListener(int pos, int tagPos) {
        CommentsInfo commentsInfo = commentslist.get(pos);
        List<String> commentImgs = commentsInfo.getCommentImgs();
        if(!commentImgs.get(commentImgs.size()-1).equals("")){
            commentImgs.add("");
        }
        commentImgs.remove(tagPos);
        commentsInfo.setCommentImgs(commentImgs);
        commentslist.set(pos,commentsInfo);
        commentAdapter.notifyItemChanged(pos);
    }






}
