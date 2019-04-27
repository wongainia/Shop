package com.zhenghaikj.shop.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CartAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.StoreBean;
import com.zhenghaikj.shop.mvp.contract.CartContract;
import com.zhenghaikj.shop.mvp.model.CartModel;
import com.zhenghaikj.shop.mvp.presenter.CartPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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


    private CartAdapter cartAdapter;
    private List<StoreBean> shopBeanslist = new ArrayList<>();
    private SPUtils spUtils=SPUtils.getInstance("token");
    private String Userkey;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cart;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initView() {




    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void setListener() {
        mTvManagement.setOnClickListener(this);
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
        }
    }

    /*获取购物车*/
    @Override
    public void GetCartProduct(Cart Result) {
    if (Result.getSuccess().equals("true")){
       // Result.getShop().size();

      for (int i = 0; i < Result.getShop().size(); i++) {
            StoreBean storeBean =new StoreBean();
            storeBean.setShopName(Result.getShop().get(i).get(0).getShopName());
            storeBean.setShopLogo(Result.getShop().get(i).get(0).getShopLogo());

          List<CommodityBean> list=new ArrayList<>();
             for (int j = 0; j <Result.getShop().get(i).size(); j++) {
                 CommodityBean commodityBean = new CommodityBean();
                 commodityBean.setSkuId(Result.getShop().get(i).get(j).getSkuId());
                 commodityBean.setId(Result.getShop().get(i).get(j).getId());
                 commodityBean.setImgUrl(Result.getShop().get(i).get(j).getImgUrl());
                 commodityBean.setName(Result.getShop().get(i).get(j).getName());
                 commodityBean.setPrice(Result.getShop().get(i).get(j).getPrice());
                 commodityBean.setCount(Result.getShop().get(i).get(j).getCount());
                 commodityBean.setSize(Result.getShop().get(i).get(j).getSize());
                // commodityBean.setVersion(Result.getShop().get(i).get(j).getVersion());
                 commodityBean.setAddTime(Result.getShop().get(i).get(j).getAddTime());//添加时间
               list.add(commodityBean);
                 storeBean.setList(list);
             }
          shopBeanslist.add(storeBean);
        }



        mRvCart.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvCart.setHasFixedSize(true);
        cartAdapter = new CartAdapter(shopBeanslist,mActivity);
        mRvCart.setAdapter(cartAdapter);


        //全选CheckBox监听
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
                    }
                }
                //更新
                UpdateRecyclerView();
            }
        });



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
                        }
                    }
                } else {
                    // 解决点击取消选择商品时，
                    // 店铺全选按钮取消选择状态，不会不变成全不选
                    if (allChildSelect(position) == shopBeanslist.get(position).getList().size()) {
                        for (int i = 0; i < shopBeanslist.get(position).getList().size(); i++) {
                            if (shopBeanslist.get(position).getList().get(i).isIscheck()) {
                                shopBeanslist.get(position).getList().get(i).setIscheck(false);
                            }
                        }
                    }
                }
                //更新
                UpdateRecyclerView();
            }

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
                UpdateRecyclerView();
            }
        });





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
}
