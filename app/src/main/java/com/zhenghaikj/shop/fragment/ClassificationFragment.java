package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lwkandroid.stateframelayout.StateFrameLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.Util.MyOkHttp;
import com.zhenghaikj.shop.Util.MyUtils;
import com.zhenghaikj.shop.adapter.CategoryAdapter;
import com.zhenghaikj.shop.adapter.CategoryContentAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Global;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.Products;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class ClassificationFragment extends BaseLazyFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.lv_content)
    ListView mLvContent;
    @BindView(R.id.stateLayout)
    StateFrameLayout mStateLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.iv_scan)
    ImageView mIvScan;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_search)
    EditText mTvSearch;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;

    private CategoryAdapter adapter;
    private CategoryContentAdapter categoryContentAdapter;
//    private List<Category> categoryList;
    private List<Product> productList;
    private List<Products> productsList;
    //记录滑动的ListView 滑动的位置   
    private int scrollPosition = -1;
    private boolean flags = true;
    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        super.initImmersionBar();
//        mImmersionBar.titleBar(R.id.toolbar);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//        mImmersionBar.statusBarColor(R.color.white);
//        mImmersionBar.fitsSystemWindows(false);
//        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_classification;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void initData() {
        mStateLayout.setOnNetErrorRetryListener(new StateFrameLayout.OnNetErrorRetryListener() {
            @Override
            public void onNetErrorRetry() {
                reqCategory();
            }
        });
        reqCategory();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableHeaderTranslationContent(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                flags = false;
                reqCategory();
            }
        });
//        adapter = new CategoryAdapter(getActivity(), Global.Productslist);
//        categoryContentAdapter = new CategoryContentAdapter(getActivity(), Global.Productslist);
//        listview.setAdapter(adapter);
//        lvContent.setAdapter(categoryContentAdapter);

    }

    @Override
    protected void setListener() {

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                mLvContent.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setSelectItem(position);
                        mLvContent.setSelection(position);
//                        lvContent.setSelectionFromTop(position,0);
//                        lv_content.smoothScrollToPositionFromTop(position,0);

                    }
                });
            }
        });
        mLvContent.setOnScrollListener(listener);
    }

    AbsListView.OnScrollListener listener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (scrollPosition != firstVisibleItem) {
                if (adapter != null) {
                    adapter.setSelectItem(firstVisibleItem);
                    mListview.setSelectionFromTop(firstVisibleItem, 40);
                    scrollPosition = firstVisibleItem;
                }
            }
        }
    };

    public void reqCategory() {
        if (!checkNetwork()) {
            mStateLayout.changeState(StateFrameLayout.NET_ERROR);
            return;
        }
        if (flags) {
            mStateLayout.changeState(StateFrameLayout.LOADING);
        }

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("provinceid", Global.provinceid);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("product.getCategoryList", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                MyUtils.e("------获取分类结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void reqProduct(String categoryid) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("keyword", "");
        formBody.add("categoryid", categoryid);
        formBody.add("producttype", "1");
        formBody.add("isnew", "1");
        formBody.add("ishot", "1");
        formBody.add("pageindex", "1");
        formBody.add("pagesize", "40");
        formBody.add("provinceid", Global.provinceid);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("product.getProductList", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyUtils.e("------获取产品结果------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            switch (msg.what) {
                case 0:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            mStateLayout.changeState(StateFrameLayout.SUCCESS);
                            JSONArray dataArray = new JSONArray(data);
                            productsList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                productsList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Products.class));
                            }
                            adapter = new CategoryAdapter(mActivity, productsList);
                            mListview.setAdapter(adapter);
                            categoryContentAdapter = new CategoryContentAdapter(mActivity, productsList);
                            mLvContent.setAdapter(categoryContentAdapter);
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONArray dataArray = new JSONArray(data);
                            for (int i = 0; i < dataArray.length(); i++) {
                                productList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Product.class));
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };


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
}
