package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.ConfirmOrderActivity;
import com.zhenghaikj.shop.adapter.ChooseColorAdapter;
import com.zhenghaikj.shop.adapter.ChooseSizeAdapter;
import com.zhenghaikj.shop.adapter.ShopCoupAdapter;
import com.zhenghaikj.shop.entity.CartResult;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.adapter.CartAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.CartItem;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.mvp.contract.CartContract;
import com.zhenghaikj.shop.mvp.model.CartModel;
import com.zhenghaikj.shop.mvp.presenter.CartPresenter;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.widget.AdderView;
import com.zhenghaikj.shop.widget.AutoLineFeedLayoutManager;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CartFragment extends BaseLazyFragment<CartPresenter, CartModel> implements View.OnClickListener, CartContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_management)
    TextView mTvManagement;
    @BindView(R.id.tv_number_of_products)
    TextView mTvNumberOfProducts;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_settlement)
    TextView mTvSettlement;
    @BindView(R.id.rv_cart)
    RecyclerView mRvCart;
    Unbinder unbinder;
    @BindView(R.id.cb_circle_cart)
    CheckBox mCbCircleCart;
    @BindView(R.id.ll_calculation)
    LinearLayout mLlCalculation;
    @BindView(R.id.ll_clean_up)
    LinearLayout mLlCleanUp;
    @BindView(R.id.tv_move_to_favorites)
    TextView mTvMoveToFavorites;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.ll_finish)
    LinearLayout mLlFinish;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private CartAdapter cartAdapter;
    private List<StoreBean> shopBeanslist = new ArrayList<>();
    private CartItem cartItem;
    private List<CartItem.SkuIdsBean> list;
    CartItem.SkuIdsBean bean;


    private SPUtils spUtils = SPUtils.getInstance("token");
    private String Userkey;
    private String skuid_add; //添加删除的skuid
    private String count_add; //增加删除保存的count

    private View popupWindow_view;
    private PopupWindow mPopupWindow;

    private List<ShopCoupResult.CouponBean> couplist=new ArrayList<>();//用于存放优惠券列表
    private int commoditycount = 0;
    //String sku_delete="";
    private HashMap<String, String> sku_delete_map = new HashMap<>();
    private HashMap<String, String> sku_close_delte_map = new HashMap<>();//失效商品


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cart;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        switch (name) {
            case "cart":
                mPresenter.GetCartProduct(Userkey);
                break;
            default:
                break;
        }

    }

    @Override
    protected void initView() {
        popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.popwindow_shopcoups, null);
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void setListener() {
        mTvManagement.setOnClickListener(this);
        mTvDelete.setOnClickListener(this);
        mTvSettlement.setOnClickListener(this);
        mLlCleanUp.setOnClickListener(this);
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.GetCartProduct(Userkey);
                smartRefreshLayout.finishRefresh();


            }
        });

    }

    @Override
    protected void initData() {
        Userkey = spUtils.getString("UserKey");
        mPresenter.GetCartProduct(Userkey);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_management:
                if ("管理".equals(mTvManagement.getText())) {
                    mLlCalculation.setVisibility(View.GONE);
                    mLlFinish.setVisibility(View.VISIBLE);
                    mTvManagement.setText("完成");
                } else {
                    mLlCalculation.setVisibility(View.VISIBLE);
                    mLlFinish.setVisibility(View.GONE);
                    mTvManagement.setText("管理");
                }
                break;
            case R.id.tv_delete:
                if (sku_delete_map.isEmpty()) {
                    Toast.makeText(mActivity, "请选择删除商品", Toast.LENGTH_SHORT).show();
                } else {
                    String skuid = "";
                    Iterator<String> it = sku_delete_map.keySet().iterator();
                    while (it.hasNext()) {
                        String key = it.next();
                        String value = sku_delete_map.get(key);
                        skuid += "," + value;
                    }
                    String final_skuid = skuid.substring(1, skuid.length());//去除第一个逗号
                    mPresenter.PostDeleteCartProduct(final_skuid, Userkey);

                }
                break;

            /*清理失效商品*/
            case R.id.ll_clean_up:

                switch (Isfailure(shopBeanslist)) {
                    case 1: //不存在失效给出提示
                        Toast.makeText(mActivity, "所选商品中没有失效商品", Toast.LENGTH_SHORT).show();
                        break;
                    case -1: //存在失效 给出提示是否删除
                        final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                        dialog.setMessage("是否清除失效商品")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//清除
                                CleanfailureShop(shopBeanslist);
                                dialog.dismiss();
                            }
                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                        break;
                    case 0:
                        break;
                    default:
                        break;
                }

                break;

            /*结算*/
            case R.id.tv_settlement:
                switch (Isfailure(shopBeanslist)) {
                    case 1:
                        if (!shopBeanslist.isEmpty()){
                            Intent intent = new Intent(mActivity,ConfirmOrderActivity.class);
                            intent.putExtra("checkshop",(Serializable) (GetCheckShopList(shopBeanslist)));//传递集合
                            startActivity(intent);
                        }

                        break;
                    case -1:
                        Toast.makeText(mActivity, "提交失败存在失效商品", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }

                break;

        }
    }

    /*获取购物车*/
    @Override
    public void GetCartProduct(Cart Result) {
        if (Result.getSuccess().equals("true")) {


            sku_delete_map.clear();
            commoditycount = 0;
            shopBeanslist.clear();
            mCbCircleCart.setChecked(false);
            mTvMoney.setText("¥0");
            mTvSettlement.setText("结算(0)");


            for (int i = 0; i < Result.getShop().size(); i++) {

                StoreBean storeBean = new StoreBean();
                storeBean.setShopName(Result.getShop().get(i).get(0).getShopName());
                storeBean.setShopLogo(Result.getShop().get(i).get(0).getShopLogo());

                List<CommodityBean> list = new ArrayList<>();
                for (int j = 0; j < Result.getShop().get(i).size(); j++) {
                    CommodityBean commodityBean = new CommodityBean();
                    commodityBean.setCartItemId(Result.getShop().get(i).get(j).getCartItemId());
                    commodityBean.setSkuId(Result.getShop().get(i).get(j).getSkuId());
                    commodityBean.setId(Result.getShop().get(i).get(j).getId());
                    commodityBean.setImgUrl(Result.getShop().get(i).get(j).getImgUrl());
                    commodityBean.setName(Result.getShop().get(i).get(j).getName());
                    commodityBean.setPrice(Result.getShop().get(i).get(j).getPrice());
                    commodityBean.setCount(Result.getShop().get(i).get(j).getCount());
                    commodityBean.setSize(Result.getShop().get(i).get(j).getSize());
                    commodityBean.setColor(Result.getShop().get(i).get(j).getColor());
                    commodityBean.setStatus(Result.getShop().get(i).get(j).getStatus());
                    // commodityBean.setVersion(Result.getShop().get(i).get(j).getVersion());
                    commodityBean.setAddTime(Result.getShop().get(i).get(j).getAddTime());//添加时间
                    commodityBean.setShopName(Result.getShop().get(i).get(j).getShopName());
                    commodityBean.setShopLogo(Result.getShop().get(i).get(j).getShopLogo());
                    list.add(commodityBean);
                    storeBean.setList(list);
                    commoditycount++;
                }
                shopBeanslist.add(storeBean);
            }


            mTvNumberOfProducts.setText("共" + commoditycount + "件宝贝");
            mRvCart.setLayoutManager(new LinearLayoutManager(mActivity));
            mRvCart.setHasFixedSize(true);
            cartAdapter = new CartAdapter(shopBeanslist, mActivity);
            mRvCart.setAdapter(cartAdapter);
            getTotalMoneyAndCloseCount(shopBeanslist);


            //全选CheckBox监听  全选店铺
            mCbCircleCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        for (int i = 0; i < shopBeanslist.size(); i++) {
                            //选择店铺
                            if (!shopBeanslist.get(i).isIscheck()) {
                                shopBeanslist.get(i).setIscheck(true);
                            }
                            for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                                //选择店铺的商品
                                if (!shopBeanslist.get(i).getList().get(j).isIscheck()) {
                                    shopBeanslist.get(i).getList().get(j).setIscheck(true);
                                }
                            }
                        }

                        /*用于将skuid添加到map中*/
                        for (int i = 0; i < shopBeanslist.size(); i++) {
                            for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                                sku_delete_map.put(shopBeanslist.get(i).getList().get(j).getCartItemId(), shopBeanslist.get(i).getList().get(j).getSkuId());
                            }
                        }


                    } else {
                        //只有当点击全不选时才执行
                        // 解决点击取消选择店铺或商品时，
                        // 全选按钮取消选择状态，不会不变成全不选
                        if (allSelect() == shopBeanslist.size()) {
                            for (int i = 0; i < shopBeanslist.size(); i++) {
                                if (shopBeanslist.get(i).isIscheck()) {
                                    shopBeanslist.get(i).setIscheck(false);
                                }
                                for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                                    if (shopBeanslist.get(i).getList().get(j).isIscheck()) {
                                        shopBeanslist.get(i).getList().get(j).setIscheck(false);
                                    }
                                }
                            }
                            //清除保存的keyvalue
                            sku_delete_map.clear();

                        }
                    }
                    getTotalMoneyAndCloseCount(shopBeanslist);
                    //更新
                    UpdateRecyclerView();
                }
            });


            //店铺前的按钮
            cartAdapter.setCallBack(new CartAdapter.allCheck() {
                @Override
                public void OnCheckListener(boolean isSelected, int position) {
                    //保存店铺点击状态
                    shopBeanslist.get(position).setIscheck(isSelected);
                    //通知全选CheckBox的选择状态
                    if (allSelect() == shopBeanslist.size()) {
                        mCbCircleCart.setChecked(true);


                    } else {
                        mCbCircleCart.setChecked(false);


                    }
                    if (isSelected) {
                        for (int i = 0; i < shopBeanslist.get(position).getList().size(); i++) {
                            if (!shopBeanslist.get(position).getList().get(i).isIscheck()) {
                                shopBeanslist.get(position).getList().get(i).setIscheck(true);
                                sku_delete_map.put(shopBeanslist.get(position).getList().get(i).getCartItemId(), shopBeanslist.get(position).getList().get(i).getSkuId());
                            }
                        }


                    } else {
                        // 解决点击取消选择商品时，
                        // 店铺全选按钮取消选择状态，不会不变成全不选
                        if (allChildSelect(position) == shopBeanslist.get(position).getList().size()) {
                            for (int i = 0; i < shopBeanslist.get(position).getList().size(); i++) {
                                if (shopBeanslist.get(position).getList().get(i).isIscheck()) {
                                    shopBeanslist.get(position).getList().get(i).setIscheck(false);
                                    sku_delete_map.remove(shopBeanslist.get(position).getList().get(i).getCartItemId());
                                }
                            }
                        }
                    }
                    getTotalMoneyAndCloseCount(shopBeanslist);
                    //更新
                    UpdateRecyclerView();
                }


                /*点击店铺里面商品*/
                @Override
                public void OnItemCheckListener(boolean isSelected, int parentposition, int chaildposition) {
                    //保存商品点击状态
                    shopBeanslist.get(parentposition).getList().get(chaildposition).setIscheck(isSelected);

                    //通知店铺选择的状态
                    if (allChildSelect(parentposition) == shopBeanslist.get(parentposition).getList().size()) {
                        shopBeanslist.get(parentposition).setIscheck(true);


                    } else {
                        shopBeanslist.get(parentposition).setIscheck(false);
                    }

                    if (shopBeanslist.get(parentposition).getList().get(chaildposition).isIscheck()) {
                        sku_delete_map.put(shopBeanslist.get(parentposition).getList().get(chaildposition).getCartItemId(), shopBeanslist.get(parentposition).getList().get(chaildposition).getSkuId());


                    } else {
                        sku_delete_map.remove(shopBeanslist.get(parentposition).getList().get(chaildposition).getCartItemId());
                    }

                    getTotalMoneyAndCloseCount(shopBeanslist);


                    UpdateRecyclerView();
                }

                /*商品数量添加删减操作*/
                @Override
                public void OnItemAddReduceListener(int value, int parentposition, int chaildposition) {

                    skuid_add = shopBeanslist.get(parentposition).getList().get(chaildposition).getSkuId();
                    count_add = String.valueOf(value);

                    cartItem = new CartItem();
                    bean = new CartItem.SkuIdsBean();
                    list = new ArrayList<>();
                    bean.setSkuId(skuid_add);
                    bean.setCount(count_add);
                    list.add(bean);
                    cartItem.setUserkey(Userkey);
                    cartItem.setSkus(list);
                    Gson gson = new Gson();
                    String jsonstr = gson.toJson(cartItem);
                    mPresenter.PostUpdateCartItem(jsonstr, Userkey);


                }

                /*点击进入详情页*/
                @Override
                public void OnItemClickDetailListner(View view, int parentposition, int chaildposition) {
                    Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                    intent.putExtra("id", shopBeanslist.get(parentposition).getList().get(chaildposition).getId());
                    startActivity(intent);


                }

                /*领券*/
                @Override
                public void OnCheckCoupListner(int parentposition) {
                    String shopid = Result.getShop().get(parentposition).get(0).getShopId();
                    mPresenter.GetShopCouponList(shopid);


                }


            });


        }

    }

    /*从购物车删除商品*/
    @Override
    public void PostDeleteCartProduct(CartResult Result) {
        if (Result.getSuccess().equals("true")) {
            Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
//              UpdateRecyclerView();
            // smartRefreshLayout.autoRefresh();
            mPresenter.GetCartProduct(Userkey);
        }

    }


    /*更新购物车中商品的数量*/
    @Override
    public void PostUpdateCartItem(CartResult Result) {

        if (Result.getSuccess().equals("true")) {
            UpdataCount(shopBeanslist, skuid_add, count_add);

        }


    }

    @Override
    public void GetShopCouponList(ShopCoupResult Result) {
        if (Result.getSuccess().equals("true")) {
            couplist.clear();
            couplist.addAll(Result.getCoupon());
            showPopupWindow(Result.getCoupon().get(0).getShopName());
        } else {
            Toast.makeText(mActivity, Result.getErrorMsg(), Toast.LENGTH_SHORT).show();

        }

    }

    /*获取优惠券*/
    @Override
    public void PostAcceptCoupon(GetShopCoupResult Result) {

        if (Result.getSuccess().equals("true")){

            Toast.makeText(mActivity,"领取成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mActivity,Result.getErrorMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    //计算店铺的选择数量
    private int allSelect() {
        int sum = 0;
        for (int i = 0; i < shopBeanslist.size(); i++) {
            if (shopBeanslist.get(i).isIscheck()) {
                sum++;
            }
        }
        System.out.println(sum);
        return sum;
    }


    //计算每个店铺商品的选择数量
    private int allChildSelect(int position) {
        int sum = 0;
        for (int i = 0; i < shopBeanslist.get(position).getList().size(); i++) {
            if (shopBeanslist.get(position).getList().get(i).isIscheck()) {
                sum++;
                System.out.println(position + ":" + i + ":" + shopBeanslist.get(position).getList().get(i).isIscheck());
            }
        }
        return sum;
    }


    /*
     *解决Recycleyview刷新报错问题
     */
    private void UpdateRecyclerView() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                cartAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }


    /*计算总价 和 结算数目*/

    private void getTotalMoneyAndCloseCount(List<StoreBean> shopBeanslist) {
        double Money = 0;
        int CloseCount = 0;
        for (int i = 0; i < shopBeanslist.size(); i++) {
            for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                if (shopBeanslist.get(i).getList().get(j).isIscheck()) {
                    double count = Double.parseDouble(shopBeanslist.get(i).getList().get(j).getCount());
                    double price = Double.parseDouble(shopBeanslist.get(i).getList().get(j).getPrice());
                    CloseCount++;
                    Money += count * price;
                }

            }
        }

        mTvMoney.setText("¥" + String.format("%.2f", Money)); //保留两位小数
        mTvSettlement.setText("结算" + "(" + CloseCount + ")");


    }


    /*判断购物车中是否有失效的商品*/

    private int Isfailure(List<StoreBean> shopBeanslist) {

        for (int i = 0; i < shopBeanslist.size(); i++) {
            for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                if (shopBeanslist.get(i).getList().get(j).isIscheck()) {
                    if (shopBeanslist.get(i).getList().get(j).getStatus() == 0) {//存在失效商品
                        return -1;

                    } else {
                        return 1;
                    }

                }

            }
        }
        return 0;
    }

    /*清理失效商品*/
    private void CleanfailureShop(List<StoreBean> shopBeanslist) {

        for (int i = 0; i < shopBeanslist.size(); i++) {
            for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                if (shopBeanslist.get(i).getList().get(j).getStatus() == 0) {//存在失效商品并清除
                    sku_close_delte_map.put(shopBeanslist.get(i).getList().get(j).getCartItemId(), shopBeanslist.get(i).getList().get(j).getSkuId());
                }

            }
        }
        if (!sku_close_delte_map.isEmpty()) {//进行清除失效商品操作
            String skuid = "";
            Iterator<String> it = sku_close_delte_map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = sku_close_delte_map.get(key);
                skuid += "," + value;
            }
            String final_skuid = skuid.substring(1, skuid.length());//去除第一个逗号
            mPresenter.PostDeleteCartProduct(final_skuid, Userkey);
            sku_close_delte_map.clear();
        } else {
            return;
        }

    }

    /*进行商品数量加减后对内存中shopBeanslist里面的count进行改变*/
    public void UpdataCount(List<StoreBean> shopBeanslist, String skuid, String count) {
        for (int i = 0; i < shopBeanslist.size(); i++) {
            for (int j = 0; j < shopBeanslist.get(i).getList().size(); j++) {
                if (shopBeanslist.get(i).getList().get(j).getSkuId().equals(skuid)) {
                    shopBeanslist.get(i).getList().get(j).setCount(count);
                    getTotalMoneyAndCloseCount(shopBeanslist);
                }
            }
        }
    }

    /*获取选中的商品集合*/
    public List<StoreBean> GetCheckShopList(List<StoreBean> shoplist){
        Map<Integer,StoreBean> map=new HashMap();
        //list.addAll(shoplist);
        for (int i = 0; i <shoplist.size(); i++) {
            List<CommodityBean> listbean = new ArrayList<>();
            for (int j = 0; j < shoplist.get(i).getList().size(); j++) {
                if (shoplist.get(i).getList().get(j).isIscheck()==true){
                    StoreBean storeBean=new StoreBean();
                    storeBean.setShopName(shoplist.get(i).getList().get(j).getShopName());
                    storeBean.setShopLogo(shoplist.get(i).getList().get(j).getShopLogo());
                    if (shoplist.get(i).isIscheck()){
                        storeBean.setIscheck(true);
                    }

                        CommodityBean commodityBean = new CommodityBean();
                        commodityBean.setCartItemId(shoplist.get(i).getList().get(j).getCartItemId());
                        commodityBean.setSkuId(shoplist.get(i).getList().get(j).getSkuId());
                        commodityBean.setId(shoplist.get(i).getList().get(j).getId());
                        commodityBean.setImgUrl(shoplist.get(i).getList().get(j).getImgUrl());
                        commodityBean.setName(shoplist.get(i).getList().get(j).getName());
                        commodityBean.setPrice(shoplist.get(i).getList().get(j).getPrice());
                        commodityBean.setCount(shoplist.get(i).getList().get(j).getCount());
                        commodityBean.setSize(shoplist.get(i).getList().get(j).getSize());
                        commodityBean.setColor(shoplist.get(i).getList().get(j).getColor());
                        commodityBean.setStatus(shoplist.get(i).getList().get(j).getStatus());
                        commodityBean.setAddTime(shoplist.get(i).getList().get(j).getAddTime());//添加时间
                        commodityBean.setIscheck(true);
                        listbean.add(commodityBean);
                        storeBean.setList(listbean);
                        map.put(i,storeBean);
                }

            }

        }


        List<StoreBean> list=new ArrayList<>();
        Collection<StoreBean> collection = map.values();
        Iterator<StoreBean> iterator = collection.iterator();
        while (iterator.hasNext()) {
            StoreBean value =  iterator.next();
            list.add(value);
        }

        return list;
    }



    public void showPopupWindow(String shopname) {
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
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

          popupWindow_view.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mPopupWindow.dismiss();
              }
          });

          popupWindow_view.findViewById(R.id.img_cha).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mPopupWindow.dismiss();
              }
          });


        ((TextView)popupWindow_view.findViewById(R.id.tv_coup)).setText(shopname);
        RecyclerView rv=popupWindow_view.findViewById(R.id.rv_coup);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        ShopCoupAdapter shopCoupAdapter=new ShopCoupAdapter(R.layout.item_shopcoup,couplist);
        rv.setAdapter(shopCoupAdapter);

        shopCoupAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_getcoup:
                        mPresenter.PostAcceptCoupon(((ShopCoupResult.CouponBean)adapter.getData().get(position)).getVShopId(),((ShopCoupResult.CouponBean)adapter.getData().get(position)).getCouponId(),Userkey);
                        break;
                }

            }
        });




    }



}
