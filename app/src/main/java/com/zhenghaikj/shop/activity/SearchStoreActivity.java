package com.zhenghaikj.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SerachHistroyAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.widget.AutoLineFeedLayoutManager;
import com.zhenghaikj.shop.widget.SqlHelp.SearchListDbOperation;
import com.zhenghaikj.shop.base.NoImmBaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;



/*兑换商城搜索*/
public class SearchStoreActivity extends NoImmBaseActivity implements View.OnClickListener {

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
        return R.layout.activity_search_store;
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
//        hotsearch=new ArrayList<>();
//        hotsearch.add("冰箱");
//        hotsearch.add("空调");
//        hotsearch.add("佰益莱");
//        serachHistroyAdapterhot=new SerachHistroyAdapter(R.layout.item_hot,hotsearch);
//        mRvhot.setLayoutManager(new AutoLineFeedLayoutManager());
//        mRvhot.setAdapter(serachHistroyAdapterhot);

        if (getIntent().getStringExtra("searchresult")!=null){
            mEtSearch.setText(getIntent().getStringExtra("searchresult"));
            mEtSearch.setSelection(getIntent().getStringExtra("searchresult").length());
        }

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mTvSerach.setOnClickListener(this);
        mTvclean.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        serachHistroyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_item_history:
                        Intent intent=new Intent(mActivity,GetSerachListActivity.class);
                        intent.putExtra("skey",(String) adapter.getItem(position));
                        startActivityForResult(intent, Config.SEARCH_REQUEST);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;
                }

            }
        });

        /*暂时模拟*/
//        serachHistroyAdapterhot.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()){
//                    case R.id.ll_item_history:
//                        String record=(String) adapter.getItem(position);
//                        if (!searchListDbOperation.isHasRecord(record)) {
//                            tempList.add(record);
//                        }
//                        //将搜索记录保存至数据库中
//
//                        if (tempList.isEmpty()){
//                            mRlserach_history.setVisibility(View.GONE);
//                        }else {
//                            mRlserach_history.setVisibility(View.VISIBLE);
//                        }
//
//                        searchListDbOperation.addRecords(record);
//                        reversedList();
//                        serachHistroyAdapter.notifyDataSetChanged();
//                        Intent intent=new Intent(mActivity,NewSearchDetailActivty.class);
//                        intent.putExtra("search",(String) adapter.getItem(position));
//                        startActivityForResult(intent, Config.SEARCH_REQUEST);
//                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                        break;
//                }
//            }
//        });


        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                     //添加自己的方法

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
                        // Intent intent=new Intent(mActivity,SearchDetailActivity.class);
                        Intent intent=new Intent(mActivity,GetSerachListActivity.class);
                        intent.putExtra("skey",record);
                        startActivityForResult(intent, Config.SEARCH_REQUEST);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        if (!tempList.isEmpty()){
                            mRlserach_history.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mEtSearch.setText(String.valueOf(mEtSearch.getHint()));
                        mEtSearch.setSelection(String.valueOf(mEtSearch.getHint()).length());
                        Intent intent=new Intent(mActivity,GetSerachListActivity.class);
                        intent.putExtra("skey",String.valueOf(mEtSearch.getHint()));
                        startActivityForResult(intent, Config.SEARCH_REQUEST);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        //判断数据库中是否存在该记录
                        if (!searchListDbOperation.isHasRecord(String.valueOf(mEtSearch.getHint()))) {
                            tempList.add(String.valueOf(mEtSearch.getHint()));
                        }
                        //将搜索记录保存至数据库中
                        searchListDbOperation.addRecords(String.valueOf(mEtSearch.getHint()));
                        reversedList();
                        serachHistroyAdapter.notifyDataSetChanged();
                        if (!tempList.isEmpty()){
                            mRlserach_history.setVisibility(View.VISIBLE);
                        }

                    }




                    return true;
                }


                return false;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mEtSearch.setFocusable(true);
        mEtSearch.setFocusableInTouchMode(true);
        mEtSearch.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEtSearch, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 500);

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
                   // Intent intent=new Intent(mActivity,SearchDetailActivity.class);
                    Intent intent=new Intent(mActivity,GetSerachListActivity.class);
                    intent.putExtra("skey",record);

                    startActivityForResult(intent, Config.SEARCH_REQUEST);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    if (!tempList.isEmpty()){
                        mRlserach_history.setVisibility(View.VISIBLE);
                    }
                } else {
                    mEtSearch.setText(String.valueOf(mEtSearch.getHint()));
                    mEtSearch.setSelection(String.valueOf(mEtSearch.getHint()).length());
                    Intent intent=new Intent(mActivity,GetSerachListActivity.class);
                    intent.putExtra("skey",String.valueOf(mEtSearch.getHint()));
                    startActivityForResult(intent, Config.SEARCH_REQUEST);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    //判断数据库中是否存在该记录
                    if (!searchListDbOperation.isHasRecord(String.valueOf(mEtSearch.getHint()))) {
                        tempList.add(String.valueOf(mEtSearch.getHint()));
                    }
                    //将搜索记录保存至数据库中
                    searchListDbOperation.addRecords(String.valueOf(mEtSearch.getHint()));
                    reversedList();
                    serachHistroyAdapter.notifyDataSetChanged();
                    if (!tempList.isEmpty()){
                        mRlserach_history.setVisibility(View.VISIBLE);
                    }

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
            case R.id.iv_back:
                SearchStoreActivity.this.finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Config.SEARCH_REQUEST){
            if (resultCode==Config.SEARCH_RESULT){
                String content=data.getStringExtra("searchresult");
                mEtSearch.setText(content);
                mEtSearch.setSelection(content.length());
            }

        }
    }



}
