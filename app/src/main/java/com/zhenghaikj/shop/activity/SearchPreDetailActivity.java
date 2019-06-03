package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SerachHistroyAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.widget.AutoLineFeedLayoutManager;
import com.zhenghaikj.shop.widget.SqlHelp.SearchListDbOperation;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPreDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
   /* @BindView(R.id.iv_search)
    ImageView mIvSearch;*/
    @BindView(R.id.tv_clean)
    TextView mTvclean;
    @BindView(R.id.tv_serach)
    TextView mTvSerach;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.ll_serach)
    LinearLayout mLlSerach;
    @BindView(R.id.tv_history)
    TextView mTvHistory;
    @BindView(R.id.rv_history)
    RecyclerView mRvHistory;

    @BindView(R.id.rl_serach_history)
    RelativeLayout mRlserach_history;

    @BindView(R.id.rv_hot)
    RecyclerView mRvhot;


    private List<String> searchRecordsList;
    private List<String> tempList;//临时列表

    private SearchListDbOperation searchListDbOperation;
    private SerachHistroyAdapter serachHistroyAdapter;



    private List<String> hotsearch;
    private SerachHistroyAdapter serachHistroyAdapterhot;
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_serachpredetail;
    }

    @Override
    protected void initData() {
        searchListDbOperation = new SearchListDbOperation(this,"building");//传入表名，以对表进行操作
        searchRecordsList = new ArrayList<>();
        tempList = new ArrayList<>();
        tempList.addAll(searchListDbOperation.getRecordsList());
        reversedList();//倒序输出

        if (tempList.isEmpty()){
            mRlserach_history.setVisibility(View.GONE);
        }else {
            mRlserach_history.setVisibility(View.VISIBLE);
        }

        //设置适配器
        serachHistroyAdapter=new SerachHistroyAdapter(R.layout.item_histroy,searchRecordsList);
        mRvHistory.setLayoutManager(new AutoLineFeedLayoutManager());
        mRvHistory.setAdapter(serachHistroyAdapter);



        /*模拟热门搜索*/
        hotsearch=new ArrayList<>();
        hotsearch.add("冰箱");
        hotsearch.add("空调");
        hotsearch.add("佰益莱");
        serachHistroyAdapterhot=new SerachHistroyAdapter(R.layout.item_hot,hotsearch);
        mRvhot.setLayoutManager(new AutoLineFeedLayoutManager());
        mRvhot.setAdapter(serachHistroyAdapterhot);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mTvSerach.setOnClickListener(this);
        mTvclean.setOnClickListener(this);

        serachHistroyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_item_history:
                        Intent intent=new Intent(mActivity,SearchDetailActivity.class);
                        intent.putExtra("search",(String) adapter.getItem(position));
                        startActivity(intent);
                        break;
                }

            }
        });

        /*暂时模拟*/
        serachHistroyAdapterhot.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_item_history:
                        String record=(String) adapter.getItem(position);
                        if (!searchListDbOperation.isHasRecord(record)) {
                            tempList.add(record);
                        }
                        //将搜索记录保存至数据库中

                        if (tempList.isEmpty()){
                            mRlserach_history.setVisibility(View.GONE);
                        }else {
                            mRlserach_history.setVisibility(View.VISIBLE);
                        }

                        searchListDbOperation.addRecords(record);
                        reversedList();
                        serachHistroyAdapter.notifyDataSetChanged();

                        Intent intent=new Intent(mActivity,SearchDetailActivity.class);
                        intent.putExtra("search",(String) adapter.getItem(position));
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
            case R.id.tv_serach:
                if (mEtSearch.getText().toString().length() > 0) {
                    String record = mEtSearch.getText().toString();
                    //判断数据库中是否存在该记录
                    if (!searchListDbOperation.isHasRecord(record)) {
                        tempList.add(record);
                    }
                    //将搜索记录保存至数据库中
                    searchListDbOperation.addRecords(record);
                    reversedList();
                    serachHistroyAdapter.notifyDataSetChanged();
                    Intent intent=new Intent(mActivity,SearchDetailActivity.class);
                    intent.putExtra("search",record);
                    startActivity(intent);
                    if (!tempList.isEmpty()){
                        mRlserach_history.setVisibility(View.VISIBLE);
                    }


                } else {
                    Toast.makeText(mActivity,"搜索内容不能为空",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_clean:
                tempList.clear();
                reversedList();
                searchListDbOperation.deleteAllRecords();
                serachHistroyAdapter.notifyDataSetChanged();
                if (tempList.isEmpty()){
                    mRlserach_history.setVisibility(View.GONE);
                }
                break;


        }


    }


    //颠倒list顺序，用户输入的信息会从上依次往下显示
    private void reversedList(){
        searchRecordsList.clear();
        for(int i = tempList.size() - 1 ; i >= 0 ; i --){
            searchRecordsList.add(tempList.get(i));
        }
    }




}