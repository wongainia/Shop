package com.zhenghaikj.shop.fragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.FoundGoodsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

//发现好货
public class FoundGoodsFragment extends BaseLazyFragment {
    @BindView(R.id.rv_found_goods)
    RecyclerView mRvFoundGoods;

    private List<Product> foundGoodsList=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_found_goods;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
    }

    @Override
    protected void initData() {
        for (int i=0;i<20;i++){
            foundGoodsList.add(new Product());
        }

        FoundGoodsAdapter foundGoodsAdapter=new FoundGoodsAdapter(R.layout.item_found_goods,foundGoodsList);
        mRvFoundGoods.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvFoundGoods.setAdapter(foundGoodsAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
