package com.zhenghaikj.shop.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.EvaluateAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CommentEntity;
import com.zhenghaikj.shop.entity.CommentsInfo;
import com.zhenghaikj.shop.entity.EvaluatePhotoEntity;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.entity.PostPostAddComment;
import com.zhenghaikj.shop.mvp.contract.EvaluateContract;
import com.zhenghaikj.shop.mvp.model.EvaluateModel;
import com.zhenghaikj.shop.mvp.presenter.EvaluatePresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.utils.imageutil.CompressHelper;
import com.zhenghaikj.shop.utils.imageutil.Glide4Engine;
import com.zhenghaikj.shop.widget.StarBarView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluateActivity extends BaseActivity<EvaluatePresenter, EvaluateModel> implements EvaluateContract.View, EvaluateAdapter.OnStatusListener, View.OnClickListener {
    @BindView(R.id.rv_rvaluate)
    RecyclerView mRvrvaluate;
    @BindView(R.id.tv_submit)
    TextView mTv_submit;

    List<CommentsInfo> commentslist = new ArrayList<>();

    List<EvaluateResult.ProductBean> list = new ArrayList<>();
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
    private EvaluateAdapter evaluateAdapter;
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private View footerview;
    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");
    private String FilePath;
    private ArrayList<String> permissions;
    private int size;
    private List<Uri> mSelected;
    private Uri uri;
    private CommentEntity mCommentEntity = new CommentEntity();//提交评价的实体类
    private Map<Integer, CommentsInfo> map = new HashMap<>();//用于存放评分 留言 图片


    private int position; //当前点击添加照片的位置
    private ZLoadingDialog dialog;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initData() {
        Userkey = spUtils.getString("UserKey");
        String orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetComment(orderID, Userkey);
        /*初始化提交的实体类*/
        mCommentEntity.setOrderId(orderID);
        mCommentEntity.setServiceMark("5");
        mCommentEntity.setDeliveryMark("5");
        mCommentEntity.setPackMark("5");
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
    protected void initView() {
        dialog = new ZLoadingDialog(mActivity);
        footerview = LayoutInflater.from(mActivity).inflate(R.layout.item_evaluate_store, null);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单评价");
    }

    @Override
    protected void setListener() {
        mTv_submit.setOnClickListener(this);
        mIconBack.setOnClickListener(this);
    }

    /*获取 “评论” 详情*/
    @Override
    public void GetComment(EvaluateResult Result) {
        if (Result.isSuccess()) {
            for (int i = 0; i < Result.getOrderItemIds().size(); i++) {
                CommentsInfo mCommentsInfo = new CommentsInfo();
                mCommentsInfo.setOrderItemId(Result.getOrderItemIds().get(i).toString());
                mCommentsInfo.setMark("5"); //默认5分
                mCommentsInfo.setCommentContent("用户未填写评论！");//
                map.put(i, mCommentsInfo);  //存入orderItemId
            }
            for (int i = 0; i < Result.getProduct().size(); i++) {
                CommentsInfo commentsInfo = new CommentsInfo();
                List<String> commentImgs = new ArrayList<>();
                List<String> src = new ArrayList<>();
                commentImgs.add("");
                src.add("");
                commentsInfo.setCommentImgs(commentImgs);
                commentsInfo.setSrc(src);
                commentslist.add(commentsInfo);
            }
            list.addAll(Result.getProduct());
            mRvrvaluate.setLayoutManager(new LinearLayoutManager(mActivity));
            evaluateAdapter = new EvaluateAdapter(R.layout.item_evaluate, list, commentslist, mActivity);
            evaluateAdapter.setOnStatusListener(this);
            evaluateAdapter.addFooterView(footerview);
            ShowStarState(evaluateAdapter);
            mRvrvaluate.setAdapter(evaluateAdapter);


        }

    }

    private void ShowStarState(EvaluateAdapter evaluateAdapter) {
        StarBarView baozhuang_star = evaluateAdapter.getFooterLayout().findViewById(R.id.baozhuang_star);
        StarBarView sudu_star = evaluateAdapter.getFooterLayout().findViewById(R.id.sudu_star);
        StarBarView peisong_star = evaluateAdapter.getFooterLayout().findViewById(R.id.peisong_star);


        TextView tv_baozhuang = evaluateAdapter.getFooterLayout().findViewById(R.id.tv_baozhuang);
        TextView tv_sudu = evaluateAdapter.getFooterLayout().findViewById(R.id.tv_sudu);
        TextView tv_peisong = evaluateAdapter.getFooterLayout().findViewById(R.id.tv_peisong);


        baozhuang_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = baozhuang_star.getStarRating();
                setStarName(tv_baozhuang, starRating);
                /*包装*/
                mCommentEntity.setPackMark(MyUtils.floatToString(starRating));
            }
        });

        sudu_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = sudu_star.getStarRating();
                setStarName(tv_sudu, starRating);
                /*速度*/
                mCommentEntity.setDeliveryMark(MyUtils.floatToString(starRating));
            }
        });

        peisong_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = peisong_star.getStarRating();
                setStarName(tv_peisong, starRating);
                /*配送*/
                mCommentEntity.setServiceMark(MyUtils.floatToString(starRating));

            }
        });


    }


    /*上传base64的图片获取 图片在服务器中的地址*/

    /*
     *
     *
     *
     * */

    @Override
    public void UploadPicEvaluate(EvaluatePhotoEntity Result) {
        if (Result.isSuccess()) {
            if (commentslist.get(position).getCommentImgs().size() < 3) {
                CommentsInfo commentsInfo = commentslist.get(position);
                List<String> commentImgs = commentsInfo.getCommentImgs();
                commentImgs.add(0, Result.getRomoteImage());

                List<String> imgSrc = commentsInfo.getSrc();
                imgSrc.add(0, Result.getSrc());
                commentsInfo.setCommentImgs(commentImgs);
                commentsInfo.setSrc(imgSrc);
                commentslist.set(position, commentsInfo);
                evaluateAdapter.notifyItemChanged(position);

            } else if (commentslist.get(position).getCommentImgs().size() == 3) {
                CommentsInfo commentsInfo = commentslist.get(position);
                List<String> commentImgs = commentsInfo.getCommentImgs();
                List<String> imgSrc = commentsInfo.getSrc();

                commentImgs.set(commentImgs.size() - 1, Result.getRomoteImage());
                imgSrc.set(imgSrc.size() - 1, Result.getSrc());


                commentsInfo.setCommentImgs(commentImgs);
                commentsInfo.setSrc(imgSrc);
                commentslist.set(position, commentsInfo);
                evaluateAdapter.notifyItemChanged(position);
            }


        }


    }

    @Override
    public void PostAddComment(PostPostAddComment Result) {

        if (Result.getSuccess().equals("true")) {
            Toast.makeText(mActivity, "评价成功", Toast.LENGTH_SHORT).show();
            setResult(401);
            EventBus.getDefault().post("待评价");
            EvaluateActivity.this.finish();

        } else {
            Toast.makeText(mActivity, "评价失败", Toast.LENGTH_SHORT).show();

        }

    }


    /**
     * 设置星星文字
     */
    private void setStarName(TextView textView, float star_num) {
        if (star_num == 5.0f) {
            textView.setText("非常好");
        } else if (star_num == 4.0f) {
            textView.setText("很好");
        } else if (star_num == 3.0f) {
            textView.setText("一般");
        } else if (star_num == 2.0f) {
            textView.setText("很差");
        } else if (star_num == 1.0f) {
            textView.setText("非常差");
        }
    }

    /*新增图片*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSetStatusListener(int pos) {
        position = pos;
        if (requestPermissions()) {
            showPopupWindow(101, 201, pos);
        } else {
            requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
        }


    }

    /*删除图片*/
    @Override
    public void onDeleteListener(int pos, int tagPos) {
        CommentsInfo commentsInfo = commentslist.get(pos);
        List<String> commentImgs = commentsInfo.getCommentImgs();
        List<String> src = commentsInfo.getSrc();


        if (!commentImgs.get(commentImgs.size() - 1).equals("")) {
            commentImgs.add("");
            src.add("");
        }
        commentImgs.remove(tagPos);
        src.remove(tagPos);

        commentsInfo.setCommentImgs(commentImgs);
        commentsInfo.setSrc(src);

        commentslist.set(pos, commentsInfo);
        evaluateAdapter.notifyItemChanged(pos);


    }


    /*星级评分*/
    @Override
    public void onStarBarListner(int position, float Star) {
        Log.d("=====" + position, "星级评分" + Star);
        CommentsInfo commentsInfo = map.get(position);
        commentsInfo.setMark(MyUtils.floatToString(Star));
        map.put(position, commentsInfo);

    }

    /*留言回调*/
    @Override
    public void onTextChangeLinstener(int position, String message) {
        CommentsInfo commentsInfo = map.get(position);
        commentsInfo.setCommentContent(message);
        map.put(position, commentsInfo);

    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2, int pos) {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.camera_layout, null);
        Button camera_btn = popupWindow_view.findViewById(R.id.camera_btn);
        Button photo_btn = popupWindow_view.findViewById(R.id.photo_btn);
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
                    Intent intent = new Intent();
                    // 指定开启系统相机的Action
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    String f = System.currentTimeMillis() + ".jpg";
                    String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy";
                    FilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/xgy/" + f;
                    File dirfile = new File(fileDir);
                    if (!dirfile.exists()) {
                        dirfile.mkdirs();
                    }
                    File file = new File(FilePath);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= 24) {
                        fileUri = FileProvider.getUriForFile(mActivity, "com.zhenghaikj.shop.fileProvider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }


                    /*启动照相机*/
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, code1);

                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                mPopupWindow.dismiss();
            }
        });
        photo_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (requestPermissions()) {
                    Matisse.from(EvaluateActivity.this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(getResiduePhoto(commentslist, position))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new Glide4Engine())

                            .forResult(code2);
                    mPopupWindow.dismiss();
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }

            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
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
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //申请相关权限:返回监听
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        size = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                size++;
            }
        }
        switch (requestCode) {
            case 10001:
                if (size == grantResults.length) {//允许
                    showPopupWindow(101, 201, position);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;

            default:
                break;

        }
    }

    //返回图片处理
    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        switch (requestCode) {
            //拍照获取图片
            case 101:

                if (resultCode == -1) {
                    file = new File(FilePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                    mPresenter.UploadPicEvaluate(MyUtils.fileToBase64(newFile));
                }

                break;
            //从相册中获取
            case 201:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size() != 0) {
                        for (int i = 0; i < mSelected.size(); i++) {
                            uri = mSelected.get(i);
                            file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                            File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file);
                            mPresenter.UploadPicEvaluate(MyUtils.fileToBase64(newFile));
                        }
                    }

                }

                break;


            default:
                break;
        }

    }

    /*判断当前position下评价还能传几张图片*/
    public int getResiduePhoto(List<CommentsInfo> list, int position) {

        if ("".equals(list.get(position).getCommentImgs().get(0))) {
            return 3;
        }

        if ("".equals(list.get(position).getCommentImgs().get(1))) {

            return 2;
        }

        return 1;

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_submit:

                /*添加图片*/
                for (int i = 0; i < commentslist.size(); i++) {
                    if (commentslist.get(i).getCommentImgs() != null && commentslist.get(i).getCommentImgs().size() > 0) {
                        CommentsInfo commentsInfo = map.get(i);
                        commentsInfo.setSrc(commentslist.get(i).getSrc());
                        map.put(i, commentsInfo);
                    }
                }

                List<CommentEntity.ProductCommentsBean> list = new ArrayList<>();
                for (int i = 0; i < map.size(); i++) {
                    CommentEntity.ProductCommentsBean bean = new CommentEntity.ProductCommentsBean();

                    for (int j = 0; j < map.get(i).getSrc().size(); j++) {

                        if (map.get(i).getSrc().get(j).equals("")) {
                            map.get(i).getSrc().remove(j);
                        }
                    }

                    bean.setImages(map.get(i).getSrc());
                    bean.setContent(map.get(i).getCommentContent());
                    bean.setMark(map.get(i).getMark());
                    bean.setOrderItemId(map.get(i).getOrderItemId());
                    list.add(bean);

                }
                mCommentEntity.setProductComments(list);

                Gson gson = new Gson();
                String json = gson.toJson(mCommentEntity);
                mPresenter.PostAddComment(Userkey, json);
                break;
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("图片上传中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }
    public void cancleLoading() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }
}
