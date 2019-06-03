package com.zhenghaikj.shop.widget.SqlHelp;

//2、创建数据库操作类SearchListDbOperation ,用于对数据进行，增删查等操作

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜素列表数据库操作类
 *
 * tableName 表名
 */
public class SearchListDbOperation {
    SearchListSQLiteOpenHelper searchListSQLiteOpenHelper;
    SQLiteDatabase recordsDb;
    String tableName;

    public SearchListDbOperation(Context context, String tableName){
        searchListSQLiteOpenHelper = new SearchListSQLiteOpenHelper(context);
        this.tableName = tableName;
    }


    //添加搜索记录
    public void addRecords(String record) {
        if (!isHasRecord(record)) {
            recordsDb = searchListSQLiteOpenHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", record);
            //添加
            recordsDb.insert(tableName, null, values);
            //关闭
            recordsDb.close();
        }
    }

    //判断是否含有该搜索记录
    public boolean isHasRecord(String record) {
        boolean isHasRecord = false;
        recordsDb = searchListSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = recordsDb.query(tableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                isHasRecord = true;
            }
        }
        //关闭数据库
        recordsDb.close();
        return isHasRecord;
    }

    //获取全部搜索记录
    public List<String> getRecordsList() {
        List<String> recordsList = new ArrayList<>();
        recordsDb = searchListSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = recordsDb.query(tableName, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            recordsList.add(name);
        }
        //关闭数据库
        recordsDb.close();
        return recordsList;
    }

    //清空搜索记录
    public void deleteAllRecords() {
        recordsDb = searchListSQLiteOpenHelper.getWritableDatabase();
        recordsDb.execSQL("delete from "+tableName);

        recordsDb.close();
    }



}
