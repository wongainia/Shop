package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.StoreBean;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConfirmOrderAdapter extends BaseQuickAdapter<StoreBean, BaseViewHolder> {
    private List<StoreBean> list;
    private Context mContext;
    private String content;
    public ConfirmOrderAdapter(int layoutResId, @Nullable List<StoreBean> data,Context context,String content) {
        super(layoutResId, data);
        list=data;
        mContext=context;
        this.content=content;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreBean item) {

        helper.setText(R.id.tv_goods_number,"共"+item.getList().size()+"件商品");

        double Money = 0;
        for (int i = 0; i < item.getList().size(); i++) {
            double count = Double.parseDouble(item.getList().get(i).getCount());
            double price = Double.parseDouble(item.getList().get(i).getPrice());
            Money += count * price;
        }

        if (item.getOneCoupons()!=null){
        helper.setVisible(R.id.ll_coupons,true);
        helper.setText(R.id.tv_coupons,item.getOneCoupons().getBaseName()+" 优惠:"+item.getOneCoupons().getBasePrice()+"元");

         double discounts=item.getOneCoupons().getBasePrice();
         //算出优惠的价格
         helper.setText(R.id.tv_subtotal,"¥"+String.format("%.2f",  Money-discounts));

        }else {

            helper.setText(R.id.tv_subtotal,"¥"+String.format("%.2f", Money));
        }

        if ("true".equals(item.getProvideInvoice())){
            helper.setVisible(R.id.ll_billing,true);
            helper.setVisible(R.id.view,true);
        }else {
            helper.setGone(R.id.ll_billing,false);
            helper.setGone(R.id.view,false);
        }
        helper.addOnClickListener(R.id.ll_billing);
        helper.setText(R.id.et_billing,content);

        helper.setText(R.id.tv_store_name,item.getShopName());
        ConfirmOrderListAdapter confirmOrderListAdapter=new ConfirmOrderListAdapter(R.layout.item_confirm_order_list,item.getList());
        RecyclerView recyclerView = helper.getView(R.id.rv_confirm_order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(confirmOrderListAdapter);

        helper.getView(R.id.et_leave_a_message).setTag(helper.getLayoutPosition());
        ((EditText)helper.getView(R.id.et_leave_a_message)).addTextChangedListener(new TextSwitcher(helper));
    }




    public interface SaveEditTextStrListener{
        void SaveEdit(int position, String string);
    }

    class TextSwitcher implements TextWatcher{
        private BaseViewHolder holder;

        public TextSwitcher(BaseViewHolder holder) {
            this.holder=holder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            SaveEditTextStrListener listener=(SaveEditTextStrListener)mContext;
            if(s!=null){
                listener.SaveEdit(Integer.parseInt(holder.getView(R.id.et_leave_a_message).getTag().toString()),s.toString());
            }

        }
    }
}
