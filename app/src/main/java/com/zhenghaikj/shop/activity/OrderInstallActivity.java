package com.zhenghaikj.shop.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.AreaAdapter;
import com.zhenghaikj.shop.adapter.CityAdapter;
import com.zhenghaikj.shop.adapter.DistrictAdapter;
import com.zhenghaikj.shop.adapter.OrderInstallAdapter;
import com.zhenghaikj.shop.adapter.ProvinceAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.AddressCodeResult;
import com.zhenghaikj.shop.entity.Area;
import com.zhenghaikj.shop.entity.City;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.District;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.Province;
import com.zhenghaikj.shop.mvp.contract.AddInstallOrderContract;
import com.zhenghaikj.shop.mvp.model.AddInstallOrderModel;
import com.zhenghaikj.shop.mvp.presenter.AddInstallOrderPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/*保内上门安装*/
public class OrderInstallActivity extends BaseActivity<AddInstallOrderPresenter, AddInstallOrderModel> implements AddInstallOrderContract.View, View.OnClickListener {
    private static final String TAG ="OrderInstallActivity" ;
    private String orderID;
    private OrderDetail.OrderItemBean bean=new OrderDetail.OrderItemBean();
    private OrderDetail.OrderBean order=new OrderDetail.OrderBean();

    private ZLoadingDialog dialog;

    //@BindView(R.id.img_shop)
    //ImageView mImgshop;
    //@BindView(R.id.tv_shop)
    //TextView mTvshop;
  // @BindView(R.id.tv_save)
  // TextView mTvsave;
    @BindView(R.id.view)
    View mView;
 //   @BindView(R.id.tv_count)
 //   TextView mTvcount;
    @BindView(R.id.tv_address)
    TextView mTvaddress;
     @BindView(R.id.img_arrow)
     ImageView mImgarrow;
     @BindView(R.id.icon_back)
     ImageView mIconback;
     @BindView(R.id.tv_to_Address)
     TextView mTvtoAddress;
     @BindView(R.id.et_name)
     EditText mEtname;
     @BindView(R.id.et_phone)
     EditText mEtphone;
     //@BindView(R.id.adderview)
     //AdderView adderView;
     @BindView(R.id.et_address)
     EditText mEtaddress;

     @BindView(R.id.rv)
     RecyclerView rv;

     @BindView(R.id.tv_ordersend)
     TextView mTvordersend;

    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_area;
    private TextView tv_district;
    private TextView tv_choose;
    private ImageView iv_close;
    private RecyclerView rv_address_choose;
    private PopupWindow popupWindow;
    private List<Province> provinceList;
    private List<City> cityList;
    private List<Area> areaList;
    private List<District> districtList;

    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private AreaAdapter areaAdapter;
    private DistrictAdapter districtAdapter;

    private String ProvinceName;
    private String CityName;
    private String AreaName;
    private String DistrictName;

    private String ProvinceCode;//省code
    private String CityCode;//市code
    private String AreaCode;//区code
    private String DistrictCode;//街道code
    private OrderInstallAdapter orderInstallAdapter;

    private int installnum=0; //存储发单成功的数量


    private Map<Integer,OrderDetail.OrderItemBean> installmap =new HashMap<>();//用于储存安装的信息
    private Express expressResult=new Express();

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
    @Override
    protected int setLayoutId() {
        return R.layout.activity_orderinstall;
    }

    @Override
    protected void initData() {
        orderID = getIntent().getStringExtra("OrderId");
//        Log.d(TAG,"OrderId"+orderID);
        mPresenter.GetOrderDetail(orderID,userKey);
        mPresenter.GetExpress(orderID, userKey);

    }

    @Override
    protected void initView() {
        dialog = new ZLoadingDialog(mActivity);
    }

    @Override
    protected void setListener() {
       // mTvsave.setOnClickListener(this);
        mTvaddress.setOnClickListener(this);
        mImgarrow.setOnClickListener(this);
        mIconback.setOnClickListener(this);
        mTvordersend.setOnClickListener(this);
     /*   adderView.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                if (value>Integer.parseInt(bean.getCount())){
                    Toast.makeText(mActivity,"数量超过购买数！",Toast.LENGTH_SHORT).show();
                    adderView.setValue(Integer.parseInt(bean.getCount()));
                }
            }
        });*/
    }
    @Override
    public void GetOrderDetail(OrderDetail result) {
        if (result.isSuccess()){
           // mTvsave.setVisibility(View.VISIBLE);
           // mTvsave.setText("发单");
            order=result.getOrder();


            for (int i = 0; i <result.getOrderItem().size() ; i++) {
                if (!result.getOrderItem().get(i).isInstall()){
                    continue;
                }
                installmap.put(i,result.getOrderItem().get(i));
            }




            orderInstallAdapter=new OrderInstallAdapter(R.layout.item_orderinstall,result.getOrderItem());
            rv.setLayoutManager(new LinearLayoutManager(mActivity));
            rv.setAdapter(orderInstallAdapter);

            orderInstallAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.img_check:
                            if (adapter.getViewByPosition(rv,position,R.id.img_check).isSelected()){
                                adapter.getViewByPosition(rv,position,R.id.img_check).setSelected(false);
                                installmap.remove(position);
                            }else {
                                adapter.getViewByPosition(rv,position,R.id.img_check).setSelected(true);
                                installmap.put(position,(OrderDetail.OrderItemBean)adapter.getItem(position));
                            }

                            break;

                    }
                }
            });



            mPresenter.GetRegion(order.getRegionId());
            mTvtoAddress.setText(order.getAddress());
            mEtname.setText(order.getShipTo());
            mEtphone.setText(order.getPhone());
          //  adderView.setValue(Integer.parseInt(result.getOrderItem().get(0).getCount()));
            bean=result.getOrderItem().get(0);
        }

    }

    @Override
    public void GetRegion(AddressCodeResult result) {
        ProvinceCode = result.getProvince();
        CityCode = result.getCity();
        AreaCode = result.getCounty();
        DistrictCode = result.getTown();
    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                break;
        }
    }

    @Override
    public void GetExpress(Express Result) {
        if (Result.isSuccess()) {
//            mPresenter.GetExpressInfo(Result.getExpressNum());
            expressResult = Result;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                OrderInstallActivity.this.finish();
                break;

    //        case R.id.tv_save://提交
            case R.id.tv_ordersend:
//                ToastUtils.showShort(expressResult.getExpressNum());
             String name=mEtname.getText().toString();
             String phone=mEtphone.getText().toString();
             String address=mTvaddress.getText().toString();
             String housenumber=mEtaddress.getText().toString();
             /*故障描述默认格式安装 : 某某东西 */
             String Memo=""+bean.getBrandName()+" "+bean.getCategoryName();

             Log.d("=====>", String.valueOf(installmap.size()));

             if (installmap.size()==0){
             Toast.makeText(mActivity,"请选择产品发单",Toast.LENGTH_SHORT).show();
             }else {

                 for (Map.Entry<Integer,OrderDetail.OrderItemBean> entry :installmap.entrySet()){

                     mPresenter.AddOrder(
                             "2",
                             "安装",
                             order.getBisId(),//UserID传商家的bisid
                             entry.getValue().getBrandId(),
                             entry.getValue().getBrandName(),
                             entry.getValue().getParentCategoryId(),
                             entry.getValue().getParentCategoryName(),
                             entry.getValue().getCategoryId(),
                             entry.getValue().getCategoryName(),
                             ProvinceCode,
                             CityCode,
                             AreaCode,
                             DistrictCode,
                             order.getAddress(),
                             order.getShipTo(),
                             UserID,
                             Memo,
                             "100",
                             "48", //回收时间先为48小时
                             "Y",  //保内
                             "N",
                             "N",       //加急时间
                             "0",
                             "0", //加急费
                             entry.getValue().getCount(),
                             "N",//客户是否收到货
                             expressResult.getExpressNum(),
                             orderID,
                             "Mall");
                            showLoading(entry.getKey(),installmap);
                 }

             }



                /**
                 * 发布工单
                 * TypeID;//分类ID 1维修 2安装 3其他服务
                 * TypeName;//
                 * UserID;//用户id
                 * FBrandID;//品牌id
                 * FBrandName;//品牌名
                 * FCategoryID;//分类id
                 * FCategoryName;//分类名
                 * FProductTypeID;//型号id
                 * FProductType;//型号名
                 * ProvinceCode;//省code
                 * CityCode;//市code
                 * AreaCode;//区code
                 * Address;//详细地址
                 * UserName;//客户姓名
                 * Phone;//客户手机
                 * Memo;//故障描述
                 * RecycleOrderHour;//回收时间
                 * Guarantee;//保内Y保外N
                 * AccessorySendState;//是否已发配件 Y是N否
                 * Extra;//是否加急Y是N否
                 * ExtraTime;//加急时间
                 * ExtraFee;//加急费用
                 */

              Log.d("=====>order","安装"+bean.getBrandName()
                      +" "+bean.getCategoryName()+" "+
                      bean.getParentCategoryName()+" "+
                      address+housenumber+" "+
                      name+" "+
                      Memo);
                break;
            case R.id.tv_address://地址选择
            case R.id.img_arrow:
            case R.id.tv_province:
              mPresenter.GetProvince();
                break;
            case R.id.tv_city:
                mPresenter.GetCity(ProvinceCode);
                break;
            case R.id.tv_area:
                mPresenter.GetArea(CityCode);
                break;

        }
    }



    public void showPopWindowGetAddress(final TextView tv) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.address_pop, null);
        tv_province = contentView.findViewById(R.id.tv_province);
        tv_city = contentView.findViewById(R.id.tv_city);
        tv_area = contentView.findViewById(R.id.tv_area);
        tv_district = contentView.findViewById(R.id.tv_district);
        tv_city.setOnClickListener(this);
        tv_province.setOnClickListener(this);
        tv_area.setOnClickListener(this);
        tv_choose = contentView.findViewById(R.id.tv_choose);
        tv_choose.setText("选择省份/地区");
        rv_address_choose = contentView.findViewById(R.id.rv_address_choose);
        iv_close = contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        rv_address_choose.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_address_choose.setAdapter(provinceAdapter);
        provinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProvinceName = provinceList.get(position).getName();
                ProvinceCode = provinceList.get(position).getCode();
                mPresenter.GetCity(ProvinceCode);
                tv_province.setText(ProvinceName);
                tv_province.setVisibility(View.VISIBLE);
                tv_city.setVisibility(View.VISIBLE);

            }
        });
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight() - 700);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }



    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                provinceList = baseResult.getData();
                provinceAdapter = new ProvinceAdapter(R.layout.category_item, provinceList);
//                showPopWindow(mTvProvince, provinceAdapter2, provinceList);
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        rv_address_choose.setAdapter(provinceAdapter);
                        provinceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ProvinceName = provinceList.get(position).getName();
                                ProvinceCode = provinceList.get(position).getCode();
                                mPresenter.GetCity(ProvinceCode);
                                tv_province.setText(ProvinceName);
                                tv_province.setVisibility(View.VISIBLE);
                                tv_city.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        showPopWindowGetAddress(mTvaddress);
                    }
                } else {
                    showPopWindowGetAddress(mTvaddress);
                }

                break;
            case 401:
                break;
        }
    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<City>> data = baseResult.getData();
                if (data.isItem1()) {
                    cityList = data.getItem2();
                    cityAdapter = new CityAdapter(R.layout.category_item, cityList);
                    rv_address_choose.setAdapter(cityAdapter);
                    tv_choose.setText("选择城市");
                    cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            CityName = cityList.get(position).getName();
                            CityCode = cityList.get(position).getCode();
                            mPresenter.GetArea(CityCode);
                            tv_city.setText(CityName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    MyUtils.showToast(mActivity, "获取市失败！");
                }

                break;
            case 401:
                break;
        }
    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<Area>> data = baseResult.getData();
                if (data.isItem1()) {
                    areaList = data.getItem2();
                    areaAdapter = new AreaAdapter(R.layout.category_item, areaList);
                    rv_address_choose.setAdapter(areaAdapter);
                    tv_choose.setText("选择区/县");
                    areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            AreaName = areaList.get(position).getName();
                            AreaCode = areaList.get(position).getCode();
                            mPresenter.GetDistrict(AreaCode);
                            tv_area.setText(AreaName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    MyUtils.showToast(mActivity, "获取区失败！");
                }

                break;
            case 401:
                break;
        }
    }

    @Override
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<List<District>> data = baseResult.getData();
                if (data.isItem1()) {
                    districtList = data.getItem2();
                    districtAdapter = new DistrictAdapter(R.layout.category_item, districtList);
                    rv_address_choose.setAdapter(districtAdapter);
                    tv_choose.setText("选择街道/乡/镇");
                    districtAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            DistrictName = districtList.get(position).getName();
                            DistrictCode = districtList.get(position).getCode();
                            tv_district.setText(DistrictName);
                            tv_province.setVisibility(View.VISIBLE);
                            tv_city.setVisibility(View.VISIBLE);
                            tv_area.setVisibility(View.VISIBLE);
                            tv_district.setVisibility(View.VISIBLE);
                            popupWindow.dismiss();
                            mTvaddress.setText(ProvinceName + CityName + AreaName + DistrictName);
                        }
                    });
                } else {
                    MyUtils.showToast(mActivity, "获取街道/乡/镇失败");
                }
                break;
            case 401:
                break;
        }
    }


    @Override
    public void AddOrder(BaseResult<Data<String>> baseResult) {
    switch (baseResult.getStatusCode()){
    case 200:
    if (baseResult.getData().isItem1()){
        Toast.makeText(mActivity,baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
        installnum++;
       if (installnum==installmap.size()){
           setResult(Config.RECEIPT_RESULT);
           OrderInstallActivity.this.finish();
           cancleLoading();
       }

    }else {
        Toast.makeText(mActivity,baseResult.getData().getItem2(),Toast.LENGTH_SHORT).show();
    }

     break;
     default:
         cancleLoading();
         break;
     }
    }


    public void showLoading(int position ,Map<Integer,OrderDetail.OrderItemBean> map) {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("发单中(共"+map.size()+"单)请稍后...")
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
