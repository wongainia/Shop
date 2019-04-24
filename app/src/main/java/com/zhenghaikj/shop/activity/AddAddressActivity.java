package com.zhenghaikj.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.ProvinceAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.RegionResult;
import com.zhenghaikj.shop.mvp.contract.AddressContract;
import com.zhenghaikj.shop.mvp.model.AddressModel;
import com.zhenghaikj.shop.mvp.presenter.AddressPresenter;
import com.zhenghaikj.shop.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAddressActivity extends BaseActivity<AddressPresenter, AddressModel> implements View.OnClickListener, AddressContract.View {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_receiver)
    EditText mEtReceiver;
    @BindView(R.id.et_cellphone_number)
    EditText mEtCellphoneNumber;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.switcher_accept_the_repair_work_order)
    Switch mSwitcherAcceptTheRepairWorkOrder;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_area;
    private TextView tv_district;
    private TextView tv_choose;
    private RecyclerView rv_address_choose;
    private ImageView iv_close;
    private PopupWindow popupWindow;
    private ProvinceAdapter provinceAdapter;
    private List<RegionResult> provinceList=new ArrayList<>();
    private List<RegionResult> cityList=new ArrayList<>();
    private List<RegionResult> areaList=new ArrayList<>();
    private List<RegionResult> districtList=new ArrayList<>();
    private RegionResult regionResult;
    private String mProvince;
    private String mCity;
    private String mArea;
    private String mDistrict;
    private String regionId;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initData() {
//        mPresenter.GetAllRegion();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mTvArea.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_area:
                mPresenter.GetAllRegion();
                break;
            case R.id.tv_province:
                provinceAdapter.setNewData(provinceList);
                break;
            case R.id.tv_city:
                provinceAdapter.setNewData(cityList);
                break;
            case R.id.tv_areas:
                provinceAdapter.setNewData(areaList);
                break;
            case R.id.tv_district:
                provinceAdapter.setNewData(districtList);
                break;
            case R.id.iv_add:
//                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 0);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if(data!=null) {
                Uri uri = data.getData();
                if (uri != null) {
                    Cursor cursor = getContentResolver()
                            .query(uri,
                                    new String[]{
                                            ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                                    null, null, null);
                    while (cursor.moveToNext()) {
                        String number = cursor.getString(0);
                        String name = cursor.getString(1);
                        mEtCellphoneNumber.setText(number);
                        if (mEtReceiver.getText().toString().isEmpty()){
                            mEtReceiver.setText(name);
                        }

                    }
                }
            }

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetAllRegion(List<RegionResult> Result) {
        provinceList = Result;
        provinceAdapter = new ProvinceAdapter(R.layout.region_item, provinceList);
        showPopWindowGetAddress(mTvArea);
        /*if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                rv_address_choose.setAdapter(provinceAdapter);
                provinceAdapter.setOnItemClickListener((adapter, view, position) -> {
                    regionResult = provinceList.get(position);
                    provinceAdapter.setNewData(regionResult.getSub());
                    tv_province.setText(regionResult.getName());
                    tv_province.setVisibility(View.VISIBLE);
                    tv_city.setVisibility(View.VISIBLE);
                });
            } else {
                showPopWindowGetAddress(mTvArea);
            }
        } else {
            showPopWindowGetAddress(mTvArea);
        }*/
    }

    @Override
    public void GetSubRegion(List<RegionResult> Result) {
        districtList=Result;
        provinceAdapter.setNewData(districtList);
    }

    public void showPopWindowGetAddress(final TextView tv) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.address_pop, null);
        tv_province = contentView.findViewById(R.id.tv_province);
        tv_city = contentView.findViewById(R.id.tv_city);
        tv_area = contentView.findViewById(R.id.tv_areas);
        tv_district = contentView.findViewById(R.id.tv_district);
        tv_city.setOnClickListener(this);
        tv_province.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_choose = contentView.findViewById(R.id.tv_choose);
        tv_choose.setText("选择省份/地区");
        rv_address_choose = contentView.findViewById(R.id.rv_address_choose);
        iv_close = contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(v -> popupWindow.dismiss());
        rv_address_choose.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_address_choose.setAdapter(provinceAdapter);
        provinceAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (provinceAdapter.getData().equals(provinceList)){
                regionResult=provinceList.get(position);
                cityList=regionResult.getSub();
                provinceAdapter.setNewData(cityList);
                mProvince=regionResult.getName();
                tv_province.setText(mProvince);
                tv_province.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);
                return;
            }
            if (provinceAdapter.getData().equals(cityList)){
                regionResult=cityList.get(position);
                areaList=regionResult.getSub();
                provinceAdapter.setNewData(areaList);
                mCity=regionResult.getName();
                tv_city.setText(mCity);
                tv_province.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);
                tv_area.setVisibility(View.VISIBLE);
                return;
            }
            if (provinceAdapter.getData().equals(areaList)){
                regionResult=areaList.get(position);

                mArea=regionResult.getName();
                mPresenter.GetSubRegion(regionResult.getId());
                tv_area.setText(mArea);
                tv_province.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);
                tv_area.setVisibility(View.VISIBLE);
                tv_district.setVisibility(View.VISIBLE);
                return;
            }
            if (provinceAdapter.getData().equals(districtList)){
                regionResult=districtList.get(position);
                mDistrict=regionResult.getName();
                regionId=regionResult.getId();
                mTvArea.setText(mProvince+mCity+mArea+mDistrict);
                popupWindow.dismiss();
                return;
            }
        });
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight() - 700);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(() -> {
//                MyUtils.backgroundAlpha(mActivity,1);
            MyUtils.setWindowAlpa(mActivity, false);
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
//            MyUtils.backgroundAlpha(mActivity,0.5f);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }
}
