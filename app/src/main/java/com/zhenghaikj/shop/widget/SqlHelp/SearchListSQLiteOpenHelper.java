package com.zhenghaikj.shop.widget.SqlHelp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//1.创建数据库，创建表，创建一个类SearchListSQLiteOpenHelper继承SQLiteOpenHelper
public class SearchListSQLiteOpenHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "search.db";
    private final static int DB_VERSION = 1;

    public SearchListSQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE IF NOT EXISTS building (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";//创建存储搜索楼盘的表
        db.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
