package com.zhenghaikj.shop.fragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.LimitedTimeAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.LimitBuyListResult;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/*限时抢购*/
public class PanicBuyingFragment extends BaseLazyFragment {
    @BindView(R.id.rv_panic_buying)
    RecyclerView mRvPanicBuying;


    private ArrayList<LimitBuyListResult.ListBean> limitedTimeList=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_panic_buying;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            limitedTimeList.add(new LimitBuyListResult.ListBean());
        }
        LimitedTimeAdapter limitedTimeAdapter=new LimitedTimeAdapter(R.layout.item_panic_buying,limitedTimeList);
        mRvPanicBuying.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPanicBuying.setAdapter(limitedTimeAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

}
