package com.bignerdranch.android.finalapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.finalapp.database.ItemDbSchema.ItemsTable;

public class ItemBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "itemBase.db";

    public ItemBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + ItemsTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ItemsTable.Columns.UUID + ", " +
                ItemsTable.Columns.PURPOSE + ", " +
                ItemsTable.Columns.PRICE + ", " +
                ItemsTable.Columns.DATE + ", " +
                ItemsTable.Columns.TAG_OR_TICKET +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
