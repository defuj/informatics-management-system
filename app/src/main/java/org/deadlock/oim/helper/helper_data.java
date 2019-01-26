package org.deadlock.oim.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class helper_data extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "oims.db";
    private static final int DATABASE_VERSION = 1;

    public helper_data(Context context) {
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

        sql = "create table orgs(" +
                "id integer primary key, " +
                "organization text null, " +
                "organization_categories text null, " +
                "descriptions text null);";
        Log.d("Data", "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
