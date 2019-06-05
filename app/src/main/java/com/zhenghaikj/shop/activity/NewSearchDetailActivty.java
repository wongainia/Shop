package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.NewSearchDetailAdapetr;
import com.zhenghaikj.shop.adapter.SearchDetailAdapetr;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;
import com.zhenghaikj.shop.mvp.model.SearchModel;
import com.zhenghaikj.shop.mvp.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewSearchDetailActivty extends BaseActivity<SearchPresenter, SearchModel> implements View.OnClickListener, SearchContract.View {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
  /*  @BindView(R.id.et_search)
    EditText mEtSearch;*/
    @BindView(R.id.tv_serach)
    TextView mTvSerach;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.ll_serach)
    LinearLayout mLlSerach;


    @BindView(R.id.ll_synthesis) //综合
    LinearLayout mLlSynthesis;
    @BindView(R.id.ll_price) //价格
    LinearLayout mLlPrice;
    @BindView(R.id.ll_salesnum)//销量
    LinearLayout mLlSalesnum;
    @BindView(R.id.ll_sifting) //筛选
    LinearLayout mLlSifting;

    @BindView(R.id.rv_search_detail)
    RecyclerView mRvSearchDetail;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.img_price_up_down)
    ImageView mImg_price_up_down;

    @BindView(R.id.ll_serach_txt)
    LinearLayout mLlserach_txt;

    @BindView(R.id.tv_search_txt)
    TextView Tvsearch_txt;

    @BindView(R.id.img_cancle)
    ImageView mImgcancle;

    @BindView(R.id.ll_bg_serach)
    LinearLayout mLlbg_serach;



    private SerachType serachtype;
    private String orderType="1";//排序方式 1.升序 2.降序
    private int pagaNo = 1;

    private String serach_content; //输入框中的内容
   // private SearchDetailAdapetr searchDetailAdapetr;
    private NewSearchDetailAdapetr newSearchDetailAdapetr;


    private boolean bool_price_up_down=true; //true为上  false为下

    private List<SearchResult.ProductBean> productBeanList=new ArrayList<>();

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

     enum SerachType{
        SYNTHESIS,PRICE_UP,PRICE_DOWN,SALESNUM,SIFTING
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_newserachdetail;
    }

    @Override
    protected void initData() {
        mLlSynthesis.setSelected(true);//默认选择综合
        serachtype=SerachType.SYNTHESIS;
         serach_content=getIntent().getStringExtra("search");
         if (!"".equals(serach_content)&&serach_content!=null){
            mPresenter.GetSearchProducts(serach_content, "", null, null, "1", orderType, Integer.toString(pagaNo), "10");
          //  mEtSearch.setText(getIntent().getStringExtra("search"));
             Tvsearch_txt.setText(getIntent().getStringExtra("search"));

        }

        newSearchDetailAdapetr = new NewSearchDetailAdapetr(R.layout.item_newsearch_detail, productBeanList);
        newSearchDetailAdapetr.setEmptyView(getEmptyView());
        mRvSearchDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSearchDetail.setAdapter(newSearchDetailAdapetr);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlSynthesis.setOnClickListener(this);
        mLlPrice.setOnClickListener(this);
        mLlSalesnum.setOnClickListener(this);
        mLlSifting.setOnClickListener(this);
        mTvSerach.setOnClickListener(this);

        mLlbg_serach.setOnClickListener(this);
        mLlserach_txt.setOnClickListener(this);


        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
            switch (serachtype){
                case SYNTHESIS://综合
                 pagaNo++;
                mPresenter.GetSearchProducts(serach_content, "", null, null, "1", orderType, Integer.toString(pagaNo), "10");
                break;
                case PRICE_UP://价格升序
                 pagaNo++;
                mPresenter.GetSearchProducts(serach_content, "", null, null, "3", orderType, Integer.toString(pagaNo), "10");
                break;
                case PRICE_DOWN://价格降序
                 pagaNo++;
                    mPresenter.GetSearchProducts(serach_content, "", null, null, "3", "2", Integer.toString(pagaNo), "10");
                break;
                case SALESNUM: //销量
                pagaNo++;
                mPresenter.GetSearchProducts(serach_content, "", null, null, "2", "2", Integer.toString(pagaNo), "10");
                break;
                case SIFTING://筛选
                break;
            }
                mRefreshLayout.finishLoadMore();
            }
        });

    /*    mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){//获得焦点时退出该界面
                    Log.d("======<et>","获得焦点");
                    Intent intent=new Intent();
                    intent.putExtra("searchresult",mEtSearch.getText().toString());
                    setResult(Config.SEARCH_RESULT,intent);
                    NewSearchDetailActivty.this.finish();
                }
            }
        });*/







        newSearchDetailAdapetr.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.rl_shop:
                        Intent intent=new Intent(mActivity,GoodsDetailActivity.class);
                        intent.putExtra("id",((SearchResult.ProductBean)adapter.getItem(position)).getProductId());
                        startActivity(intent);
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.ll_synthesis:
               StateChangeForType(mLlSynthesis);
               serachtype=SerachType.SYNTHESIS;
               pagaNo=1;
               mPresenter.GetSearchProducts(serach_content, "", null, null, "1", orderType, Integer.toString(pagaNo), "10");
               break;
           case R.id.ll_price://分为升序降序  默认升序
               StateChangeForType(mLlPrice);
               pagaNo=1;


               break;

           case R.id.ll_salesnum:  //销量为降序
               StateChangeForType(mLlSalesnum);
               serachtype=SerachType.SALESNUM;
               pagaNo=1;
               mPresenter.GetSearchProducts(serach_content, "", null, null, "2", "2", Integer.toString(pagaNo), "10");
               break;
           case R.id.ll_sifting:
               StateChangeForType(mLlSifting);
               serachtype=SerachType.SIFTING;
               pagaNo=1;
               Toast.makeText(mActivity,"暂未开发",Toast.LENGTH_SHORT).show();
               break;

           case R.id.tv_serach: //重新搜索
               //String serach_content = mEtSearch.getText().toString();
               if ("".equals(serach_content)){
                Toast.makeText(mActivity,"请输入内容",Toast.LENGTH_SHORT).show();
               }else {
                   StateChangeForType(mLlSynthesis);
                   serachtype=SerachType.SYNTHESIS;
                   pagaNo=1;
                   mPresenter.GetSearchProducts(serach_content,"", null, null, "1", orderType, Integer.toString(pagaNo), "10");
               }
               break;

           case R.id.ll_serach_txt://点击商品删除全部内容返回
               Intent intent=new Intent();
               intent.putExtra("searchresult","");
               setResult(Config.SEARCH_RESULT,intent);
               NewSearchDetailActivty.this.finish();
               break;

           case R.id.ll_bg_serach:  //点击背景返回但不删除
               Intent intent2=new Intent();
               intent2.putExtra("searchresult",serach_content);
               setResult(Config.SEARCH_RESULT,intent2);
               NewSearchDetailActivty.this.finish();
               break;







       }
    }

    @Override
    public void GetSearchProducts(SearchResult Result) {
       if (Result.getSuccess()){

           if (Result.getTotal()==0){
               //找不到产品
           }else {

               if(pagaNo==1){
                   productBeanList.clear();
                   productBeanList.addAll(Result.getProduct());
                   newSearchDetailAdapetr.notifyDataSetChanged();
               }else {
                   productBeanList.addAll(Result.getProduct());
                   newSearchDetailAdapetr.setNewData(productBeanList);
                   newSearchDetailAdapetr.notifyDataSetChanged();
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

    public void StateChangeForType(LinearLayout linearLayout){
        switch (linearLayout.getId()){
            case R.id.ll_synthesis:
                mLlSynthesis.setSelected(true);
                mLlPrice.setSelected(false);
                mLlSalesnum.setSelected(false);
                mLlSifting.setSelected(false);
                mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;
            case R.id.ll_price:  //默认第一次点击升序 第二次为降序
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(true);
                mLlSalesnum.setSelected(false);
                mLlSifting.setSelected(false);
                if (bool_price_up_down){
                    serachtype=SerachType.PRICE_UP;
                    mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up));
                    bool_price_up_down=false;
                    mPresenter.GetSearchProducts(serach_content, "", null, null, "3", "1", Integer.toString(pagaNo), "10");

                }else {
                    serachtype=SerachType.PRICE_DOWN;
                    mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_down));
                    bool_price_up_down=true;
                    mPresenter.GetSearchProducts(serach_content, "", null, null, "3", "2", Integer.toString(pagaNo), "10");
                }

                break;
            case R.id.ll_salesnum:
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(false);
                mLlSalesnum.setSelected(true);
                mLlSifting.setSelected(false);
                mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));

                bool_price_up_down=true;//价格回到上升排序
                break;
            case R.id.ll_sifting:
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(false);
                mLlSalesnum.setSelected(false);
                mLlSifting.setSelected(true);
                mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;

        }
       Log.d("======id", String.valueOf(linearLayout.getId()));



    }

}
