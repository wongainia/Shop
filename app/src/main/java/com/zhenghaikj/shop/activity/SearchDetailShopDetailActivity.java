package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SearchShopDetailAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.mvp.contract.SearchDetailShopDetailContract;
import com.zhenghaikj.shop.mvp.model.SearchDetailShopDetailModel;
import com.zhenghaikj.shop.mvp.presenter.SearchDetailShopDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


/*店铺内的商品*/
public class SearchDetailShopDetailActivity extends BaseActivity<SearchDetailShopDetailPresenter, SearchDetailShopDetailModel>  implements View.OnClickListener , SearchDetailShopDetailContract.View {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_search_txt)
    TextView mTvSearchTxt;
    @BindView(R.id.ll_serach_txt)
    LinearLayout mLlSerachTxt;
    @BindView(R.id.ll_bg_serach)
    LinearLayout mLlBgSerach;
    @BindView(R.id.tv_sort)
    TextView mTvSort;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.ll_serach)
    LinearLayout mLlSerach;
    @BindView(R.id.ll_synthesis)
    LinearLayout mLlSynthesis;
    @BindView(R.id.img_price_up_down)
    ImageView mImgPriceUpDown;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.ll_salesnum)
    LinearLayout mLlSalesnum;
    @BindView(R.id.rv)
    RecyclerView mRv;
   @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartRefreshLayout;

    private SearchShopDetailAdapter searchShopDetailAdapter;
    private String shopCategoryId;
    private String shopid;
    private String sname; //商品分类名

    private String content;//搜索的商品名
    private int pagaNo = 1;
    private List<SearchResult.ProductBean> list=new ArrayList<>();
    private SerachType serachType;
    private boolean bool_price_up_down=true; //true为上  false为下

    private int search_by_type=1; //1为分类进入 2为内容搜索
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    enum SerachType{
        SYNTHESIS,PRICE_UP,PRICE_DOWN,SALESNUM
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_searchdetailshopdetail;
    }

    @Override
    protected void initData() {
        mLlSynthesis.setSelected(true);//默认选择综合
        serachType= SerachType.SYNTHESIS;

        shopCategoryId=getIntent().getStringExtra("shopCategoryId");
        shopid=getIntent().getStringExtra("shopid");
        sname=getIntent().getStringExtra("sname");
        content=getIntent().getStringExtra("content");

        /*通过搜索进入*/
        if ("".equals(content)||content==null){

        }else {
            search_by_type=2;
            mTvSearchTxt.setText(content);
            mPresenter.GetSearchProducts(content,null,null,null,"1","1",String.valueOf(pagaNo),"10",shopid);
            searchShopDetailAdapter=new SearchShopDetailAdapter(R.layout.item_search_shop_detail,list);
            mRv.setLayoutManager(new LinearLayoutManager(mActivity));
            mRv.setAdapter(searchShopDetailAdapter);
        }

        /*通过点击进入*/
        if ("".equals(sname)||sname==null){
        }else {
            search_by_type=1;
            mTvSearchTxt.setText(sname);
            //mPresenter.GetProductList("10",String.valueOf(pagaNo),shopCategoryId,shopid,"");
            mPresenter.GetSearchProducts(content,shopCategoryId,null,null,"1","1",String.valueOf(pagaNo),"10",shopid);
            searchShopDetailAdapter=new SearchShopDetailAdapter(R.layout.item_search_shop_detail,list);
            mRv.setLayoutManager(new LinearLayoutManager(mActivity));
            mRv.setAdapter(searchShopDetailAdapter);
        }


    }





    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlBgSerach.setOnClickListener(this);
        mLlSynthesis.setOnClickListener(this);
        mLlPrice.setOnClickListener(this);
        mLlSalesnum.setOnClickListener(this);

        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                switch (serachType){
                    case SYNTHESIS://综合
                        pagaNo++;

                        if (search_by_type==1){//分类进入
                            mPresenter.GetSearchProducts("", null, null,null, "1", "1", Integer.toString(pagaNo), "10",shopid);
                        }else {//搜索进入
                            mPresenter.GetSearchProducts(content, null, null,null, "1", "1", Integer.toString(pagaNo), "10",shopid);
                        }

                        break;
                    case PRICE_UP://价格升序
                        pagaNo++;
                        if (search_by_type==1){//分类进入
                            mPresenter.GetSearchProducts("", null, null,null, "3", "1", Integer.toString(pagaNo), "10",shopid);
                        }else {//搜索进入
                            mPresenter.GetSearchProducts(content, null, null,null, "3", "1", Integer.toString(pagaNo), "10",shopid);
                        }

                        break;
                    case PRICE_DOWN://价格降序
                        pagaNo++;

                        if (search_by_type==1){//分类进入
                            mPresenter.GetSearchProducts("", null, null,null, "3", "2", Integer.toString(pagaNo), "10",shopid);
                        }else {//搜索进入
                            mPresenter.GetSearchProducts(content, null, null,null, "3", "2", Integer.toString(pagaNo), "10",shopid);
                        }

                        break;
                    case SALESNUM: //销量
                        pagaNo++;

                        if (search_by_type==1){//分类进入
                            mPresenter.GetSearchProducts("", null, null,null, "2", "2", Integer.toString(pagaNo), "10",shopid);
                        }else {//搜索进入
                            mPresenter.GetSearchProducts(content, null, null,null, "2", "2", Integer.toString(pagaNo), "10",shopid);
                        }
                        break;
                }
                mSmartRefreshLayout.finishLoadMore();
            }
        });

        searchShopDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_bg_serach:
                Intent intent=new Intent(mActivity,SearchShopPreDetailActivity.class);
                intent.putExtra("shopid",shopid);
                startActivity(intent);
                SearchDetailShopDetailActivity.this.finish();
                break;
            case R.id.ll_synthesis:
                StateChangeForType(mLlSynthesis);
                serachType= SerachType.SYNTHESIS;
                pagaNo=1;
                if (search_by_type==1){//分类进入
                    mPresenter.GetSearchProducts("", null, null,null, "1", "1", Integer.toString(pagaNo), "10",shopid);
                }else {//搜索进入
                    mPresenter.GetSearchProducts(content, null, null,null, "1", "1", Integer.toString(pagaNo), "10",shopid);
                }
                break;
            case R.id.ll_price://分为升序降序  默认升序
                pagaNo=1;
                StateChangeForType(mLlPrice);
                break;
            case R.id.ll_salesnum:  //销量为降序
                StateChangeForType(mLlSalesnum);
                serachType= SerachType.SALESNUM;
                pagaNo=1;
                if (search_by_type==1){//分类进入
                    mPresenter.GetSearchProducts("", null, null,null, "2", "2", Integer.toString(pagaNo), "10",shopid);
                }else {//搜索进入
                    mPresenter.GetSearchProducts(content, null, null,null, "2", "2", Integer.toString(pagaNo), "10",shopid);
                }
                break;


        }
    }

    @Override
    public void GetProductList(StoreCommodityResult result) {

    }

    @Override
    public void GetSearchProducts(SearchResult Result) {
        if (Result.getSuccess()==null){
            return;
        }
        if (Result.getSuccess()){
            if (Result.getTotal()==0){
                //找不到产品
                searchShopDetailAdapter.setEmptyView(getEmptyView());
            }else {
                if(pagaNo==1){
                    list.clear();
                    list.addAll(Result.getProduct());
                    searchShopDetailAdapter.notifyDataSetChanged();
                }else {
                    list.addAll(Result.getProduct());
                    searchShopDetailAdapter.setNewData(list);
                    searchShopDetailAdapter.notifyDataSetChanged();
                }
            }

        }
    }



    public void StateChangeForType(LinearLayout linearLayout){
        switch (linearLayout.getId()){
            case R.id.ll_synthesis:
                mLlSynthesis.setSelected(true);
                mLlPrice.setSelected(false);
                mLlSalesnum.setSelected(false);
                mImgPriceUpDown.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;
            case R.id.ll_price:  //默认第一次点击升序 第二次为降序
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(true);
                mLlSalesnum.setSelected(false);
                if (bool_price_up_down){
                    serachType= SerachType.PRICE_UP;
                    mImgPriceUpDown.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up));
                    bool_price_up_down=false;
                  if (search_by_type==1){//分类进入
                      mPresenter.GetSearchProducts("", null, null,null, "3", "1", Integer.toString(pagaNo), "10",shopid);
                  }else {//搜索进入
                      mPresenter.GetSearchProducts(content, null, null,null, "3", "1", Integer.toString(pagaNo), "10",shopid);
                  }


                }else {
                    serachType= SerachType.PRICE_DOWN;
                    mImgPriceUpDown.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_down));
                    bool_price_up_down=true;

                    if (search_by_type==1){//分类进入
                        mPresenter.GetSearchProducts("", null, null,null, "3", "2", Integer.toString(pagaNo), "10",shopid);
                    }else {//搜索进入
                        mPresenter.GetSearchProducts(content, null, null,null, "3", "2", Integer.toString(pagaNo), "10",shopid);
                    }
                }

                break;
            case R.id.ll_salesnum:
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(false);
                mLlSalesnum.setSelected(true);
                mImgPriceUpDown.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;
        }

    }
}
