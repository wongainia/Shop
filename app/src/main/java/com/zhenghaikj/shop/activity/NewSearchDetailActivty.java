package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupPosition;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.FliterAdapter;
import com.zhenghaikj.shop.adapter.FliterBrandAdapter;
import com.zhenghaikj.shop.adapter.FliterClassifyAdapter;
import com.zhenghaikj.shop.adapter.NewSearchDetailAdapetr;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CategoryMall;
import com.zhenghaikj.shop.entity.FilterResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;
import com.zhenghaikj.shop.mvp.model.SearchModel;
import com.zhenghaikj.shop.mvp.presenter.SearchPresenter;
import com.zhenghaikj.shop.widget.CustomFilterDrawerPopupView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    private LinearLayout mLlfliter_brand;
    private LinearLayout mLlfliter_classify;




    private CustomFilterDrawerPopupView customFilterDrawerPopupView;
    private RecyclerView recyclerView_brand;
    private RecyclerView recyclerView_classify;
    private TextView mTvsubmit; //提交筛选结果
    private TextView mTvreset; //重置筛选
    private TextView mTvbrand_txt;
    private String choosebrandid=null; //筛选的品牌id
    private Map<Integer,String> mapchooseclassifyid=new HashMap<>();  //筛选的分类id
    private boolean isFliter=false;
    private BasePopupView xPopup;

    private List<FilterResult.CategoryBean.SubCategoryBeanX.SubCategoryBean> mClist=new ArrayList<>();


    private FliterBrandAdapter fliterBrandAdapter;
    private FliterClassifyAdapter fliterClassifyAdapter;


    private SerachType serachtype;
    private String orderType="1";//排序方式 1.升序 2.降序
    private int pagaNo = 1;
    private String serach_content; //输入框中的内容
    private String UserKey;
    private SPUtils spUtils;
   // private SearchDetailAdapetr searchDetailAdapetr;
    private NewSearchDetailAdapetr newSearchDetailAdapetr;
    private boolean bool_price_up_down=true; //true为上  false为下
    private List<SearchResult.ProductBean> productBeanList=new ArrayList<>();
    private String cid;
    private int searchbytype =1; //1为搜索  2为点击



    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

     enum SerachType{
        SYNTHESIS,PRICE_UP,PRICE_DOWN,SALESNUM
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_newserachdetail;
    }

    @Override
    protected void initData() {
        spUtils=SPUtils.getInstance("token");
        UserKey=spUtils.getString("UserKey");

        mLlSynthesis.setSelected(true);//默认选择综合
        serachtype=SerachType.SYNTHESIS;
         serach_content=getIntent().getStringExtra("search");
         if (!"".equals(serach_content)&&serach_content!=null){
             searchbytype=1;
            mPresenter.GetSearchProducts(serach_content, null, null, null,"1", "1", Integer.toString(pagaNo), "10","0");
             Tvsearch_txt.setText(getIntent().getStringExtra("search"));
        }

        if (getIntent().getSerializableExtra("tag")!=null){
            searchbytype=2;
            Tvsearch_txt.setText(((CategoryMall.CategoryBean) getIntent().getSerializableExtra("tag")).getName());
            cid=((CategoryMall.CategoryBean) getIntent().getSerializableExtra("tag")).getId();
            mPresenter.GetSearchProducts("",cid,null,null,"1","1",Integer.toString(pagaNo), "10","0");
        }


        /*获取筛选*/
        mPresenter.GetSearchFilter(Tvsearch_txt.getText().toString(),"0","","0",UserKey);
        newSearchDetailAdapetr = new NewSearchDetailAdapetr(R.layout.item_newsearch_detail, productBeanList);
        mRvSearchDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSearchDetail.setAdapter(newSearchDetailAdapetr);

    }

    @Override
    protected void initView() {
        customFilterDrawerPopupView=new CustomFilterDrawerPopupView(mActivity);
        mLlfliter_brand=customFilterDrawerPopupView.findViewById(R.id.ll_fliter_brand);
        mLlfliter_classify=customFilterDrawerPopupView.findViewById(R.id.ll_fliter_classify);
        mTvsubmit=customFilterDrawerPopupView.findViewById(R.id.tv_submit);
        recyclerView_brand=customFilterDrawerPopupView.findViewById(R.id.rv_brand);
        recyclerView_classify=customFilterDrawerPopupView.findViewById(R.id.rv_classify);
        mTvbrand_txt=customFilterDrawerPopupView.findViewById(R.id.tv_brand_txt);
        mTvreset=customFilterDrawerPopupView.findViewById(R.id.tv_reset);
        xPopup= new XPopup.Builder(mActivity)
                .popupPosition(PopupPosition.Right)//右边
                .hasStatusBarShadow(true) //启用状态栏阴影
                .asCustom(customFilterDrawerPopupView);
    }

    @Override
    protected void setListener() {
        mLlSynthesis.setOnClickListener(this);
        mLlPrice.setOnClickListener(this);
        mLlSalesnum.setOnClickListener(this);
        mLlSifting.setOnClickListener(this);
        mTvSerach.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mLlbg_serach.setOnClickListener(this);
        mLlserach_txt.setOnClickListener(this);
        mTvsubmit.setOnClickListener(this);
        mTvbrand_txt.setOnClickListener(this);
        mTvreset.setOnClickListener(this);


        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
            switch (serachtype){
                case SYNTHESIS://综合
                 pagaNo++;

                 if (isFliter==false){
                     if (searchbytype==1){
                         mPresenter.GetSearchProducts(serach_content,  null, null,null, "1", orderType, Integer.toString(pagaNo), "10","0");
                     }else {
                         mPresenter.GetSearchProducts("",  cid, null,null, "1", orderType, Integer.toString(pagaNo), "10","0");
                     }
                 }else {
                     if (searchbytype==1){
                         mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "1", orderType, Integer.toString(pagaNo), "10","0");
                     }else {
                         mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "1", orderType, Integer.toString(pagaNo), "10","0");
                     }
                 }


                break;
                case PRICE_UP://价格升序
                 pagaNo++;

                 if (isFliter==false){
                     if (searchbytype==1){
                         mPresenter.GetSearchProducts(serach_content,  null, null,null, "3", "1", Integer.toString(pagaNo), "10","0");
                     }else {
                         mPresenter.GetSearchProducts("",  cid, null,null, "3", "1", Integer.toString(pagaNo), "10","0");
                     }
                 }else {
                     if (searchbytype==1){
                         mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "1", Integer.toString(pagaNo), "10","0");
                     }else {
                         mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "1", Integer.toString(pagaNo), "10","0");
                     }
                 }

                break;
                case PRICE_DOWN://价格降序
                 pagaNo++;

                 if (isFliter==false){
                     if (searchbytype==1){
                         mPresenter.GetSearchProducts(serach_content,  null, null,null, "3", "2", Integer.toString(pagaNo), "10","0");
                     }else {
                         mPresenter.GetSearchProducts("",  cid, null,null, "3", "2", Integer.toString(pagaNo), "10","0");
                     }
                 }else {
                     if (searchbytype==1){
                         mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "2", Integer.toString(pagaNo), "10","0");
                     }else {
                         mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "2", Integer.toString(pagaNo), "10","0");
                     }
                 }

                break;
                case SALESNUM: //销量
                pagaNo++;

                    if (isFliter==false){
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  null, null,null, "2", "2", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  cid, null,null, "2", "2", Integer.toString(pagaNo), "10","0");
                        }
                    }else {
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,   getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "2", "2", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",   getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "2", "2", Integer.toString(pagaNo), "10","0");
                        }
                    }
                break;
            }
                mRefreshLayout.finishLoadMore();
            }
        });







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

               /*未筛选*/
               if (isFliter==false){
                   if (searchbytype==1){
                       mPresenter.GetSearchProducts(serach_content,  null, null,null, "1", "1", Integer.toString(pagaNo), "10","0");
                   }else {
                       mPresenter.GetSearchProducts("",  cid, null,null, "1", "1", Integer.toString(pagaNo), "10","0");
                   }
               }else {
                   /*筛选后*/
                   if (searchbytype==1){
                       mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "1", "1", Integer.toString(pagaNo), "10","0");
                   }else {
                       mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "1", "1", Integer.toString(pagaNo), "10","0");
                   }

               }

              // mPresenter.GetSearchProducts(serach_content,  null, null,null, "1", orderType, Integer.toString(pagaNo), "10","0");
               break;
           case R.id.ll_price://分为升序降序  默认升序
               pagaNo=1;
               StateChangeForType(mLlPrice);
               break;

           case R.id.ll_salesnum:  //销量为降序
               StateChangeForType(mLlSalesnum);
               serachtype=SerachType.SALESNUM;
               pagaNo=1;

               if (isFliter==false){
                   if (searchbytype==1){
                       mPresenter.GetSearchProducts(serach_content,  null, null,null, "2", "2", Integer.toString(pagaNo), "10","0");
                   }else {
                       mPresenter.GetSearchProducts("",  cid, null,null, "2", "2", Integer.toString(pagaNo), "10","0");
                   }

               }else {
                   if (searchbytype==1){
                       mPresenter.GetSearchProducts(serach_content, getChooseclassifyid(mapchooseclassifyid) , choosebrandid,null, "2", "2", Integer.toString(pagaNo), "10","0");
                   }else {
                       mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid),choosebrandid,null, "2", "2", Integer.toString(pagaNo), "10","0");
                   }
               }


              // mPresenter.GetSearchProducts(serach_content,  null, null,null ,"2", "2", Integer.toString(pagaNo), "10","0");
               break;
           case R.id.ll_sifting:
               StateChangeForType(mLlSifting);
               pagaNo=1;
               xPopup.show();
               break;

           case R.id.tv_submit: //筛选确定按钮
               //获取品牌和分类进行判断
               xPopup.dismiss();
               isFliter=true;
                switch (serachtype){
                    case SYNTHESIS:
                        pagaNo=1;
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "1", orderType, Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "1", orderType, Integer.toString(pagaNo), "10","0");
                        }
                        break;
                    case PRICE_UP:
                        pagaNo=1;
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "1", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "1", Integer.toString(pagaNo), "10","0");
                        }
                        break;
                    case PRICE_DOWN:
                        pagaNo=1;
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "2", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "2", Integer.toString(pagaNo), "10","0");
                        }
                        break;
                    case SALESNUM:
                        pagaNo=1;
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content, getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "2", "2", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",   getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "2", "2", Integer.toString(pagaNo), "10","0");
                        }
                        break;
                }

               break;
            case R.id.tv_reset:
                     isFliter=false;
                     mapchooseclassifyid.clear();
                     choosebrandid=null;
                     recyclerView_brand.setVisibility(View.VISIBLE);
                     mTvbrand_txt.setVisibility(View.GONE);
                   for (int i = 0; i < mClist.size(); i++) {
                    fliterClassifyAdapter.getViewByPosition(recyclerView_classify,i,R.id.item_cb).setSelected(false);
                   }
                break;
           case R.id.tv_serach: //重新搜索
               //String serach_content = mEtSearch.getText().toString();
               if ("".equals(serach_content)){
                Toast.makeText(mActivity,"请输入内容",Toast.LENGTH_SHORT).show();
               }else {
                   StateChangeForType(mLlSynthesis);
                   serachtype=SerachType.SYNTHESIS;
                   pagaNo=1;
                   if (searchbytype==1){
                       mPresenter.GetSearchProducts(serach_content,  null, null,null, "1", "1", Integer.toString(pagaNo), "10","0");
                   }else {
                       mPresenter.GetSearchProducts("",  cid, null,null, "1", "1", Integer.toString(pagaNo), "10","0");
                   }

               //    mPresenter.GetSearchProducts(serach_content, null, null, null,"1", orderType, Integer.toString(pagaNo), "10","0");
               }
               break;

           case R.id.ll_serach_txt://点击商品删除全部内容返回

               if (searchbytype==1){
                   Intent intent=new Intent();
                   intent.putExtra("searchresult","");
                   setResult(Config.SEARCH_RESULT,intent);
                   NewSearchDetailActivty.this.finish();
               }else {
                   Intent intent=new Intent(mActivity,SearchPreDetailActivity.class);
                   intent.putExtra("searchresult","");
                   startActivity(intent);
               }

               break;

           case R.id.ll_bg_serach:  //点击背景返回但不删除
               if (searchbytype==1){
               Intent intent2=new Intent();
               intent2.putExtra("searchresult",serach_content);
               setResult(Config.SEARCH_RESULT,intent2);
               NewSearchDetailActivty.this.finish();
               }else {
               Intent intent2=new Intent(mActivity,SearchPreDetailActivity.class);
               intent2.putExtra("searchresult","");
               startActivity(intent2);
               }

               break;
           case R.id.iv_back:
               NewSearchDetailActivty.this.finish();
               break;

           case R.id.tv_brand_txt://品牌去除
               mTvbrand_txt.setVisibility(View.GONE);
               recyclerView_brand.setVisibility(View.VISIBLE);
               choosebrandid=null;
               break;

       }
    }

    @Override
    public void GetSearchProducts(SearchResult Result) {
        if (Result.getSuccess()==null){
            return;
        }
       if (Result.getSuccess()){
           if (Result.getTotal()==0||Result.getProduct().isEmpty()){
               //找不到产品
                 productBeanList.clear();
                newSearchDetailAdapetr.setEmptyView(getEmptyView());
                newSearchDetailAdapetr.notifyDataSetChanged();
           }else {
               if(pagaNo==1){
                   productBeanList.clear();
                   productBeanList.addAll(Result.getProduct());
                   newSearchDetailAdapetr.notifyDataSetChanged();
               }else {
                   productBeanList.addAll(Result.getProduct());
                   newSearchDetailAdapetr.setNewData(productBeanList);
                   newSearchDetailAdapetr.notifyDataSetChanged();
                  /* if (Result.getProduct().isEmpty()){
                       View view= LayoutInflater.from(mActivity).inflate(R.layout.item_footer_nomore,null);
                       newSearchDetailAdapetr.setFooterView(view);
                   }*/


               }
           }

       }else {

       }




    }

    @Override
    public void GetSearchFilter(FilterResult Result) {
        if ("true".equals(Result.getSuccess())){
            if (!Result.getBrand().isEmpty()){
                mLlfliter_brand.setVisibility(View.VISIBLE);
                recyclerView_brand.setLayoutManager(new GridLayoutManager(mActivity,3));
                fliterBrandAdapter=new FliterBrandAdapter(R.layout.item_fliter_choose,Result.getBrand());
                recyclerView_brand.setAdapter(fliterBrandAdapter);
                fliterBrandAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.item_cb:
                                adapter.getViewByPosition(recyclerView_brand, position, R.id.item_cb).setSelected(true);
                                choosebrandid= ((FilterResult.BrandBean)adapter.getItem(position)).getId()+"";
                                recyclerView_brand.setVisibility(View.GONE);
                                mTvbrand_txt.setVisibility(View.VISIBLE);
                                mTvbrand_txt.setText(((FilterResult.BrandBean)adapter.getItem(position)).getName());
                                adapter.getViewByPosition(recyclerView_brand, position, R.id.item_cb).setSelected(false);
                                break;
                        }

                    }
                });


            }

            if (!Result.getCategory().isEmpty()){
                mLlfliter_classify.setVisibility(View.VISIBLE);
                recyclerView_classify.setLayoutManager(new GridLayoutManager(mActivity,3));
                mClist.clear();
                mClist.addAll(Result.getCategory().get(0).getSubCategory().get(0).getSubCategory());
                fliterClassifyAdapter=new FliterClassifyAdapter(R.layout.item_fliter_choose,Result.getCategory().get(0).getSubCategory().get(0).getSubCategory());
                recyclerView_classify.setAdapter(fliterClassifyAdapter);
                fliterClassifyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.item_cb:
                                if (adapter.getViewByPosition(recyclerView_classify, position, R.id.item_cb).isSelected()){
                                    adapter.getViewByPosition(recyclerView_classify, position, R.id.item_cb).setSelected(false);
                                    mapchooseclassifyid.remove(position);

                                }else {
                                    adapter.getViewByPosition(recyclerView_classify, position, R.id.item_cb).setSelected(true);
                                    mapchooseclassifyid.put(position, String.valueOf(((FilterResult.CategoryBean.SubCategoryBeanX.SubCategoryBean)adapter.getItem(position)).getId()));

                                }
                                break;
                        }


                    }
                });


            }

            if (Result.getBrand().isEmpty()&&Result.getCategory().isEmpty()){
             //   Toast.makeText(mActivity,"无分类",Toast.LENGTH_SHORT).show();
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
                mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;
            case R.id.ll_price:  //默认第一次点击升序 第二次为降序
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(true);
                mLlSalesnum.setSelected(false);
                if (bool_price_up_down){
                    serachtype=SerachType.PRICE_UP;
                    mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up));
                    bool_price_up_down=false;

                    if (isFliter==false){
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  null, null,null, "3", "1", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  cid, null,null, "3", "1", Integer.toString(pagaNo), "10","0");
                        }
                    }else {
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "1", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "1", Integer.toString(pagaNo), "10","0");
                        }

                    }



                }else {
                    serachtype=SerachType.PRICE_DOWN;
                    mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_down));
                    bool_price_up_down=true;

                    if (isFliter==false){
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  null, null,null, "3", "2", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  cid, null,null, "3", "2", Integer.toString(pagaNo), "10","0");
                        }
                    }else {
                        if (searchbytype==1){
                            mPresenter.GetSearchProducts(serach_content,  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "2", Integer.toString(pagaNo), "10","0");
                        }else {
                            mPresenter.GetSearchProducts("",  getChooseclassifyid(mapchooseclassifyid), choosebrandid,null, "3", "2", Integer.toString(pagaNo), "10","0");
                        }
                    }


                  //  mPresenter.GetSearchProducts(serach_content, null, null,null, "3", "2", Integer.toString(pagaNo), "10","0");
                }

                break;
            case R.id.ll_salesnum:
                mLlSynthesis.setSelected(false);
                mLlPrice.setSelected(false);
                mLlSalesnum.setSelected(true);
                mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;
            case R.id.ll_sifting:
                mLlSifting.setSelected(true);
                mImg_price_up_down.setImageDrawable(ContextCompat.getDrawable(mActivity,R.mipmap.icon_up_down));
                bool_price_up_down=true;//价格回到上升排序
                break;
        }

    }


    @Override
    public void finish() {
        super.finish();
    }


    public String getChooseclassifyid(Map<Integer,String> map){
      if (map.size()==0){
          return null;
      }else {
          String classifyid="";
           List<String> list=new ArrayList<>();

          Collection<String> collection = map.values();
          Iterator<String> iterator = collection.iterator();
          while (iterator.hasNext()) {
              String value = (String) iterator.next();
              list.add(value);
          }
          for (int i = 0; i < list.size(); i++) {

               if (i+1==list.size()){
                   classifyid+=list.get(i);
               }else {
                   classifyid+=list.get(i)+",";
               }
          }

          return classifyid;
      }

    }

}
