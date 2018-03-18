package com.bignerdranch.android.finalapp.database;

public class ItemDbSchema {

    public static  final class ItemsTable{

        public static final String NAME = "items";

        public static final class Columns{

            public static final String UUID = "uuid";
            public static final String PURPOSE = "purpose";
            public static final String PRICE = "price";
            public static final String DATE = "date";
            public static final String TAG_OR_TICKET = "tagOrTicket";

        }

    }

}
