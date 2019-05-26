package com.zhenghaikj.shop.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UploadImgResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.PersonalInformationContract;
import com.zhenghaikj.shop.mvp.model.PersonalInformationModel;
import com.zhenghaikj.shop.mvp.presenter.PersonalInformationPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.utils.imageutil.CompressHelper;
import com.zhenghaikj.shop.utils.imageutil.Glide4Engine;
import com.zhenghaikj.shop.widget.CircleImageView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInformationActivity extends BaseActivity<PersonalInformationPresenter, PersonalInformationModel> implements View.OnClickListener, PersonalInformationContract.View {
    private static final String TAG = "PersonalInformationActivity";
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
    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.ll_avatar)
    LinearLayout mLlAvatar;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.ll_username)
    LinearLayout mLlUsername;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.ll_nickname)
    LinearLayout mLlNickname;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.ll_gender)
    LinearLayout mLlGender;
    @BindView(R.id.tv_gender)
    TextView mTvGender;
    private String userKey;
    private SPUtils spUtils;
    private View popupWindow_view;
    private String FilePath;
    private PopupWindow mPopupWindow;
    private ArrayList<String> permissions;
    private int size;
    private List<Uri> mSelected;
    private Uri uri;
    private String userName;
    private UserInfo.UserInfoDean userInfo;
    private AlertDialog dialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("个人信息");
        mTvTitle.setVisibility(View.VISIBLE);

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        mPresenter.PersonalInformation(userKey);
        mPresenter.GetUserInfoList(userName,"1");
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
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlAvatar.setOnClickListener(this);
        mLlNickname.setOnClickListener(this);
        mLlGender.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_avatar:
                if (requestPermissions()){
                    showPopupWindow(101, 102);
                }else{
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.ll_nickname:
                startActivity(new Intent(mActivity,ChageUserNameActivity.class));
                break;
            case R.id.ll_gender:
                gender();
                break;
        }
    }

    public void gender(){
        View view= LayoutInflater.from(mActivity).inflate(R.layout.dialog_gender,null);
        TextView male=view.findViewById(R.id.tv_male);
        TextView female=view.findViewById(R.id.tv_female);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.UpdateSex(userName,"男");
                dialog.dismiss();
                mTvGender.setText("男");
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.UpdateSex(userName,"女");
                dialog.dismiss();
                mTvGender.setText("女");
            }
        });
        dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.show();
        Window window= dialog.getWindow();
        WindowManager.LayoutParams lp=window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());

    }

    @Override
    public void PersonalInformation(PersonalInformation result) {
        if (result.isSuccess()) {
            mTvUsername.setText(result.getUserName());

            /*设置头像*/
            if (result.getPhoto()==null||"".equals(result.getPhoto())){//显示默认头像
                return;
            }else {
//                byte[] decode;
//                decode = Base64.decode(result.getPhoto(), Base64.DEFAULT);
                Glide.with(mActivity).asBitmap().load(result.getPhoto()).into(mIvAvatar);
            }
        }
    }

    @Override
    public void UploadPic(UploadImgResult result) {
        if (result.isSuccess()) {
            EventBus.getDefault().post("PersonalInformation");
//            ToastUtils.showShort("设置头像成功，图片路径为"+result.getSrc());
//            ToastUtils.showShort("上传成功，图片路径为"+result.getSrc());
        }else{
            ToastUtils.showShort(result.getMsg());
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                mTvNickname.setText(userInfo.getNickName());
                if (userInfo.getSex()==null){
                    mTvGender.setVisibility(View.GONE);
                }else {
                    mTvGender.setVisibility(View.VISIBLE);
                    mTvGender.setText(userInfo.getSex());
                }
                break;
        }
    }

    @Override
    public void UpdateSex(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort("修改成功");
            break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"RegiaterActivity".equals(name)) {
            return;
        }
        mPresenter.PersonalInformation(userKey);
    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2) {
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
                    Matisse.from(PersonalInformationActivity.this)
                            .choose(MimeType.ofImage())
                            .countable(true)
                            .maxSelectable(1)
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
            if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                size++;
            }
        }
        switch (requestCode) {
            case 10001:
                if (size ==grantResults.length) {//允许
                    showPopupWindow(101, 102);
                } else {//拒绝
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10002:
                if (size ==grantResults.length) {//允许
                    showPopupWindow(201, 202);
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
                    Glide.with(mActivity).load(FilePath).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);
                    file = new File(FilePath);
                    startCrop(Uri.fromFile(file));
                }

                break;
            //从相册中获取
            case 102:
                if (data != null) {
                    mSelected = Matisse.obtainResult(data);
                    if (mSelected.size()==1){
                        uri = mSelected.get(0);
                    }


                    Glide.with(mActivity).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);
                    file = new File(MyUtils.getRealPathFromUri(mActivity, uri));
                    startCrop(Uri.fromFile(file));
                }

                break;
            //裁剪后的效果
            case UCrop.REQUEST_CROP:
                if (resultCode==RESULT_OK){
                    Uri resultUri=UCrop.getOutput(data);

                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                        // headimageView.setImageBitmap(bitmap);
                        Glide.with(mActivity).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvAvatar);

                        File file1=uritoFile(resultUri);

                        if (file1!=null){
                            File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                            uploadImg(newFile);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                //错误裁剪的结果
            case UCrop.RESULT_ERROR:
                if(resultCode==RESULT_OK){
                    final Throwable cropError = UCrop.getError(data);
                    handleCropError(cropError);
                }
                break;


            default:
                break;
        }

    }

    //处理剪切失败的返回值
    private void handleCropError(Throwable cropError) {
        deleteTempPhotoFile();
        if (cropError != null) {
            Toast.makeText(this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "output_iamge.jpg");
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void uploadImg(File f) {
        mPresenter.UploadPic(MyUtils.fileToBase64(f),userKey);
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("img", f.getName(), RequestBody.create(MediaType.parse("img/png"), f));
//        builder.addFormDataPart("UserID", userID);
//        //builder.addFormDataPart("Sort", (code+1)+"");
//        MultipartBody requestBody = builder.build();
//        mPresenter.UploadAvator(requestBody);
    }

    //图片裁剪的方法
    private void startCrop(Uri uri){
        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(new File(getExternalCacheDir(), "uCrop.jpg"));
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1,1);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL,UCropActivity.NONE,UCropActivity.ALL);
        options.setToolbarTitle("头像裁剪");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格
        options.setOvalDimmedLayer(true);//设置是否为圆形裁剪框
        options.setShowCropFrame(false); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(this);
    }


    private File uritoFile(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event() {
//        if ("GetUserInfoList".equals(message)) {
            mPresenter.GetUserInfoList(userName, "1");
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
