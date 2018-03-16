package com.bignerdranch.android.finalapp.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//singleton class (private constructor then get() method)
public class DetailsArray {

    private static DetailsArray mDetailsArray;

    private List<Details> mDetails;

    private DetailsArray(Context context){

        mDetails = new ArrayList<>(); //empty list of details

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

        mDetails.add(d);

    }

    public void deleleDetails(int index){

        mDetails.remove(index);

    }

    //method that returns the list of details
    public List<Details> getDetails(){

        return mDetails;

    }

    //method that returns a crime with the given ID
    public Details getDetails(UUID id){

        for(Details details : mDetails){

            if(details.getId().equals(id)){

                return details;

            }

        }

        return null;

    }

}
