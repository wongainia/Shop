package com.zhenghaikj.shop.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_receiver)
    EditText mEtReceiver;
    @BindView(R.id.et_cellphone_number)
    EditText mEtCellphoneNumber;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.switcher_accept_the_repair_work_order)
    Switch mSwitcherAcceptTheRepairWorkOrder;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.iv_add:
//                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 0);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if(data!=null) {
                Uri uri = data.getData();
                if (uri != null) {
                    Cursor cursor = getContentResolver()
                            .query(uri,
                                    new String[]{
                                            ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                                    null, null, null);
                    while (cursor.moveToNext()) {
                        String number = cursor.getString(0);
                        String name = cursor.getString(1);
                        mEtCellphoneNumber.setText(number);
                        if (mEtReceiver.getText().toString().isEmpty()){
                            mEtReceiver.setText(name);
                        }

                    }
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
}
