package com.bignerdranch.android.finalapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.finalapp.database.ItemDbSchema.ItemsTable;
import com.bignerdranch.android.finalapp.models.Details;

import java.util.Date;
import java.util.UUID;

public class ItemCursorWrapper extends CursorWrapper {

    public ItemCursorWrapper(Cursor cursor) {

        super(cursor);

    }

    public Details getDetails() {

        String uuidString = getString(getColumnIndex(ItemsTable.Columns.UUID));
        String purpose = getString(getColumnIndex(ItemsTable.Columns.PURPOSE));
        float price = getFloat(getColumnIndex(ItemsTable.Columns.PRICE)); //*
        long date = getLong(getColumnIndex(ItemsTable.Columns.DATE));
        int isSolved = getInt(getColumnIndex(ItemsTable.Columns.TAG_OR_TICKET));

        Details details = new Details(UUID.fromString(uuidString));
        details.setPurpose(purpose);
        details.setPrice(price);
        details.setDate(new Date(date));
        details.setTagOrTicket(isSolved != 0);

        return details;

    }

}
