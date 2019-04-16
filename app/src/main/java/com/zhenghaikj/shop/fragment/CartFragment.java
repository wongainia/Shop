package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CartAdapter;
import com.zhenghaikj.shop.adapter.RecyclerAdapter1;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Bean;
import com.zhenghaikj.shop.entity.Cbean;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CartFragment extends BaseLazyFragment implements View.OnClickListener {

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
    private List<Bean> cartList = new ArrayList<>();
    private List<Cbean> cbeanList, cbeanListcp;
    private List<Product> shopTypeList = new ArrayList<>();
    private CartAdapter cartAdapter;
    private RecyclerAdapter1 shopTypeAdapter;

    private RecyclerView.LayoutManager manager;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_cart;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initView() {
        cartList = new ArrayList<>();
        //第一个店铺的数据
        cbeanList = new ArrayList<>();
        Cbean c = new Cbean();
        c.setText("商品");
        c.setIscheck(false);
        Cbean c1 = new Cbean();
        c1.setText("商品1");
        c1.setIscheck(false);
        cbeanList.add(c);
        cbeanList.add(c1);
        Bean b = new Bean();
        b.setIscheck(false);
        b.setText("店名");
        b.setList(cbeanList);

        //第二个店铺的数据
        cbeanListcp = new ArrayList<>();
        Cbean c2 = new Cbean();
        c2.setText("商品2");
        c2.setIscheck(false);
        Cbean c3 = new Cbean();
        c3.setText("商品3");
        c3.setIscheck(false);
        cbeanListcp.add(c2);
        cbeanListcp.add(c3);
        Bean b1 = new Bean();
        b1.setIscheck(false);
        b1.setText("店名1");
        b1.setList(cbeanListcp);

        //不能添加有重复变量的数据
        cartList.add(b);
        cartList.add(b1);


        manager = new LinearLayoutManager(mActivity);
        mRvCart.setLayoutManager(manager);
        mRvCart.setHasFixedSize(true);
        cartAdapter = new CartAdapter(cartList);
        mRvCart.setAdapter(cartAdapter);

        //全选CheckBox监听
        mCbCircleCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < cartList.size(); i++) {
                        //选择店铺
                        if (!cartList.get(i).ischeck()) {
                            cartList.get(i).setIscheck(true);
                        }
                        for (int j = 0; j < cartList.get(i).getList().size(); j++) {
                            //选择店铺的商品
                            if (!cartList.get(i).getList().get(j).ischeck()) {
                                cartList.get(i).getList().get(j).setIscheck(true);
                            }
                        }
                    }
                } else {
                    //只有当点击全不选时才执行
                    // 解决点击取消选择店铺或商品时，
                    // 全选按钮取消选择状态，不会不变成全不选
                    if (allSelect() == cartList.size()) {
                        for (int i = 0; i < cartList.size(); i++) {
                            if (cartList.get(i).ischeck()) {
                                cartList.get(i).setIscheck(false);
                            }
                            for (int j = 0; j < cartList.get(i).getList().size(); j++) {
                                if (cartList.get(i).getList().get(j).ischeck()) {
                                    cartList.get(i).getList().get(j).setIscheck(false);
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
                cartList.get(position).setIscheck(isSelected);
                //通知全选CheckBox的选择状态
                if (allSelect() == cartList.size()) {
                    mCbCircleCart.setChecked(true);
                } else {
                    mCbCircleCart.setChecked(false);
                }
                if (isSelected) {
                    for (int i = 0; i < cartList.get(position).getList().size(); i++) {
                        if (!cartList.get(position).getList().get(i).ischeck()) {
                            cartList.get(position).getList().get(i).setIscheck(true);
                        }
                    }
                } else {
                    // 解决点击取消选择商品时，
                    // 店铺全选按钮取消选择状态，不会不变成全不选
                    if (allChildSelect(position) == cartList.get(position).getList().size()) {
                        for (int i = 0; i < cartList.get(position).getList().size(); i++) {
                            if (cartList.get(position).getList().get(i).ischeck()) {
                                cartList.get(position).getList().get(i).setIscheck(false);
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
                cartList.get(parentposition).getList().get(chaildposition).setIscheck(isSelected);
                //通知店铺选择的状态
                if (allChildSelect(parentposition) == cartList.get(parentposition).getList().size()) {
                    cartList.get(parentposition).setIscheck(true);
                } else {
                    cartList.get(parentposition).setIscheck(false);
                }
                UpdateRecyclerView();
            }
        });

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

    //计算店铺的选择数量
    private int allSelect() {
        int sum = 0;
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).ischeck()) {
                sum++;
            }
        }
        System.out.println(sum);
        return sum;
    }

    //计算每个店铺商品的选择数量
    private int allChildSelect(int position) {
        int sum = 0;
        for (int i = 0; i < cartList.get(position).getList().size(); i++) {
            if (cartList.get(position).getList().get(i).ischeck()) {
                sum++;
                System.out.println(position + ":" + i + ":" + cartList.get(position).getList().get(i).ischeck());
            }
        }
        return sum;
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
}
