package org.deadlock.oim.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "oims.db";
    private static final int DATABASE_VERSION = 1;

    public dataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table accounts(" +
                "email txt null, " +
                "nama text null, " +
                "foto text null);";
        Log.d("Data", "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
