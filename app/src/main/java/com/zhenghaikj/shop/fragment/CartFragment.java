package com.zhenghaikj.shop.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.CartResult;
import com.zhenghaikj.shop.adapter.CartAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.mvp.contract.CartContract;
import com.zhenghaikj.shop.mvp.model.CartModel;
import com.zhenghaikj.shop.mvp.presenter.CartPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    private SPUtils spUtils=SPUtils.getInstance("token");
    private String Userkey;

    private int commoditycount=0;
    //String sku_delete="";
    private HashMap<String,String> sku_delete_map=new HashMap<>();
    private HashMap<String,String> sku_close_delte_map=new HashMap<>();//失效商品
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cart;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
      switch (name){
          case "cart":
              mPresenter.GetCartProduct(Userkey);
              break;
           default:
               break;
      }

    }

    @Override
    protected void initView() {

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
        Userkey=spUtils.getString("UserKey");
        mPresenter.GetCartProduct(Userkey);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_management:
                if ("管理".equals(mTvManagement.getText())){
                    mLlCalculation.setVisibility(View.GONE);
                    mLlFinish.setVisibility(View.VISIBLE);
                    mTvManagement.setText("完成");
                }else {
                    mLlCalculation.setVisibility(View.VISIBLE);
                    mLlFinish.setVisibility(View.GONE);
                    mTvManagement.setText("管理");
                }
                break;
            case R.id.tv_delete:
                if (sku_delete_map.isEmpty()){
                    Toast.makeText(mActivity,"请选择删除商品",Toast.LENGTH_SHORT).show();
                }else {
                    String skuid="";
                    Iterator<String> it = sku_delete_map.keySet().iterator();
                    while (it.hasNext()) {
                        String key = it.next();
                        String value = sku_delete_map.get(key);
                        skuid +=","+value;
                    }
                    String final_skuid = skuid.substring(1, skuid.length());//去除第一个逗号
                    mPresenter.PostDeleteCartProduct(final_skuid,Userkey);

                }
                break;

                /*清理失效商品*/
            case R.id.ll_clean_up:

                switch (Isfailure(shopBeanslist)){
                    case 1: //不存在失效给出提示
                        Toast.makeText(mActivity,"所选商品中没有失效商品",Toast.LENGTH_SHORT).show();
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
                  switch (Isfailure(shopBeanslist)){
                      case 1:
                          Toast.makeText(mActivity,"提交成功待开发",Toast.LENGTH_SHORT).show();
                          break;
                      case -1:
                          Toast.makeText(mActivity,"提交失败存在失效商品",Toast.LENGTH_SHORT).show();
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
    if (Result.getSuccess().equals("true")){
        sku_delete_map.clear();
        commoditycount=0;
        shopBeanslist.clear();
        mCbCircleCart.setSelected(false);

      for (int i = 0; i < Result.getShop().size(); i++) {

            StoreBean storeBean =new StoreBean();
            storeBean.setShopName(Result.getShop().get(i).get(0).getShopName());
            storeBean.setShopLogo(Result.getShop().get(i).get(0).getShopLogo());

          List<CommodityBean> list=new ArrayList<>();
             for (int j = 0; j <Result.getShop().get(i).size(); j++) {
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
                 list.add(commodityBean);
                 storeBean.setList(list);
                 commoditycount++;
             }
               shopBeanslist.add(storeBean);
        }


        mTvNumberOfProducts.setText("共"+commoditycount+"件宝贝");
        mRvCart.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvCart.setHasFixedSize(true);
        cartAdapter = new CartAdapter(shopBeanslist,mActivity);
        mRvCart.setAdapter(cartAdapter);


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
                    for (int i = 0; i <shopBeanslist.size() ; i++) {
                        for (int j = 0; j <shopBeanslist.get(i).getList().size() ; j++) {
                          sku_delete_map.put(shopBeanslist.get(i).getList().get(j).getCartItemId(),shopBeanslist.get(i).getList().get(j).getSkuId());
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
                            sku_delete_map.put(shopBeanslist.get(position).getList().get(i).getCartItemId(),shopBeanslist.get(position).getList().get(i).getSkuId());
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

                if (shopBeanslist.get(parentposition).getList().get(chaildposition).isIscheck()){
                    Log.d("==========>",shopBeanslist.get(parentposition).getList().get(chaildposition).getSkuId());
                      sku_delete_map.put(shopBeanslist.get(parentposition).getList().get(chaildposition).getCartItemId(),shopBeanslist.get(parentposition).getList().get(chaildposition).getSkuId());


                }else {
                    sku_delete_map.remove(shopBeanslist.get(parentposition).getList().get(chaildposition).getCartItemId());
                }
                getTotalMoneyAndCloseCount(shopBeanslist);

                 Log.d("==========>", String.valueOf(sku_delete_map.size()));

              UpdateRecyclerView();
            }
        });





    }

    }

    /*从购物车删除商品*/
    @Override
    public void PostDeleteCartProduct(CartResult Result) {
          if (Result.getSuccess().equals("true")){
              Toast.makeText(mActivity,"删除成功",Toast.LENGTH_SHORT).show();
//              UpdateRecyclerView();
             // smartRefreshLayout.autoRefresh();
              mPresenter.GetCartProduct(Userkey);
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

    private void getTotalMoneyAndCloseCount(List<StoreBean> shopBeanslist){
          double Money = 0;
          int CloseCount=0;
        for (int i = 0; i <shopBeanslist.size() ; i++) {
            for (int j = 0; j <shopBeanslist.get(i).getList().size() ; j++) {
                if (shopBeanslist.get(i).getList().get(j).isIscheck()){
                    double count= Double.parseDouble(shopBeanslist.get(i).getList().get(j).getCount());
                    double price= Double.parseDouble(shopBeanslist.get(i).getList().get(j).getPrice());
                    CloseCount++;
                    Money+=count*price;
                }

            }
        }

        mTvMoney.setText("¥"+Money);
        mTvSettlement.setText("结算"+"("+CloseCount+")");


    }


    /*判断购物车中是否有失效的商品*/

    private int Isfailure(List<StoreBean> shopBeanslist){

        for (int i = 0; i <shopBeanslist.size() ; i++) {
            for (int j = 0; j <shopBeanslist.get(i).getList().size() ; j++) {
                if (shopBeanslist.get(i).getList().get(j).isIscheck()){
                   if (shopBeanslist.get(i).getList().get(j).getStatus()==0){//存在失效商品
                       return -1;

                   }else {
                       return 1;
                   }

                }

            }
        }
        return 0;
    }

    /*清理失效商品*/
    private void CleanfailureShop(List<StoreBean> shopBeanslist){

        for (int i = 0; i <shopBeanslist.size() ; i++) {
            for (int j = 0; j <shopBeanslist.get(i).getList().size() ; j++) {
                    if (shopBeanslist.get(i).getList().get(j).getStatus()==0){//存在失效商品并清除
                        sku_close_delte_map.put(shopBeanslist.get(i).getList().get(j).getCartItemId(),shopBeanslist.get(i).getList().get(j).getSkuId());
                    }

            }
        }
        if (!sku_close_delte_map.isEmpty()){//进行清除失效商品操作
            String skuid="";
            Iterator<String> it = sku_close_delte_map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = sku_close_delte_map.get(key);
                skuid +=","+value;
            }
            String final_skuid = skuid.substring(1, skuid.length());//去除第一个逗号
            mPresenter.PostDeleteCartProduct(final_skuid,Userkey);
            sku_close_delte_map.clear();
        }else {
            return;
        }

    }


}
