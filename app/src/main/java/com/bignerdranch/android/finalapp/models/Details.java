package com.bignerdranch.android.finalapp.models;

import java.util.Date;
import java.util.UUID;

public class Details {

    private UUID mId; //unique id value
    private String mPurpose; //description
    private float mPrice; //price
    private Date mDate; //date
    private boolean mTagOrTicket; //tag = 0, ticket = 1

    public Details(){

        mId = UUID.randomUUID(); //generate an unique id
        mDate = new Date(); //this constructor sets the current date

    }

    public UUID getId() {
        return mId;
    }

    public String getPurpose() {
        return mPurpose;
    }

    public void setPurpose(String purpose) {
        mPurpose = purpose;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isTagOrTicket() {
        return mTagOrTicket;
    }

    public void setTagOrTicket(boolean tagOrTicket) {
        mTagOrTicket = tagOrTicket;
    }
}
