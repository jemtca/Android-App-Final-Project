package com.bignerdranch.android.finalapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.finalapp.database.ItemBaseHelper;
import com.bignerdranch.android.finalapp.database.ItemCursorWrapper;
import com.bignerdranch.android.finalapp.database.ItemDbSchema;
import com.bignerdranch.android.finalapp.database.ItemDbSchema.ItemsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//singleton class (private constructor then get() method)
public class DetailsArray {

    private static DetailsArray mDetailsArray;

    //private List<Details> mDetails;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private DetailsArray(Context context){

        //mDetails = new ArrayList<>(); //empty list of details

        mContext = context.getApplicationContext();
        mDatabase = new ItemBaseHelper(mContext).getWritableDatabase();

        /*for(int i = 0; i < 5; i++){

            Details details = new Details();

            details.setPurpose("Detail #" + i);

            if(i % 2 == 0) { //odds positions

                details.setTagOrTicket(false); //tag

            }
            else{ //even positions

                details.setTagOrTicket(true); //ticket

            }

            mDetails.add(details);

        }*/

    }

    //if the instance already exists, get() will return the instance
    //if the instance does not exist, get() will call the constructor to create it
    public static DetailsArray get(Context context){

        if(mDetailsArray == null){

            mDetailsArray = new DetailsArray(context);

        }

        return mDetailsArray;

    }

    public void addDetails(Details d){

        //mDetails.add(d);

        ContentValues values = getContentValues(d);

        mDatabase.insert(ItemsTable.NAME, null, values);

    }

    public void deleteDetails(Details d){

        //mDetails.remove(d);
        mDatabase.delete(ItemsTable.NAME, ItemsTable.Columns.UUID + " = ? ", new String[] {d.getId().toString()});

    }

    //method that returns the list of details
    public List<Details> getDetails(){

        //return mDetails;
        //return new ArrayList<>();
        List<Details> details = new ArrayList<>();

        ItemCursorWrapper cursor = queryDetails(null, null);
        try {

            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                details.add(cursor.getDetails());
                cursor.moveToNext();

            }
        }
        finally {

            cursor.close();

        }
        return details;

    }

    //method that returns a crime with the given ID
    public Details getDetails(UUID id){

        /*for(Details details : mDetails){

            if(details.getId().equals(id)){

                return details;

            }

        }*/

        //return null;
        ItemCursorWrapper cursor = queryDetails(
                ItemsTable.Columns.UUID +
                " = ?", new String[] { id.toString() }
        );

        try {

            if (cursor.getCount() == 0) {

                return null;

            }

            cursor.moveToFirst();

            return cursor.getDetails();

        }
        finally {

            cursor.close();

        }

    }

    public void updateDetails(Details details) {

        String uuidString = details.getId().toString();

        ContentValues values = getContentValues(details);

        mDatabase.update(ItemsTable.NAME, values,ItemsTable.Columns.UUID + " = ?", new String[] { uuidString });

    }

    private ItemCursorWrapper queryDetails(String whereClause, String[] whereArgs) {

        Cursor cursor = mDatabase.query(
                ItemsTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new ItemCursorWrapper(cursor);

    }

    private static ContentValues getContentValues(Details details) {

        ContentValues values = new ContentValues();

        values.put(ItemsTable.Columns.UUID, details.getId().toString());
        values.put(ItemsTable.Columns.PURPOSE, details.getPurpose());
        values.put(ItemsTable.Columns.PRICE, details.getPrice());
        values.put(ItemsTable.Columns.DATE, details.getDate().getTime());
        values.put(ItemsTable.Columns.TAG_OR_TICKET, details.isTagOrTicket() ? 1 : 0);

        return values;

    }

}
