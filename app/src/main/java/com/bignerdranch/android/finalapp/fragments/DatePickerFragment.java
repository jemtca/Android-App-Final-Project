package com.bignerdranch.android.finalapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.bignerdranch.android.finalapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "sending_date_back";

    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;

    //DetailsFragment will call this method instead the constructor
    //This method creates the fragment instance and bundles up and sets its fragment
    public static DatePickerFragment newInstance(Date date){

        Bundle args = new Bundle();
        //inserts a key and a serializable object to the bundle
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        //attaching the arguments to the fragment
        datePickerFragment.setArguments(args);

        return datePickerFragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        //getting the Date from the arguments
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        //using Calendar object to get the year, month and day (int)
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //inflating the view (dialog_date.xml)
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker); //getting the reference
        mDatePicker.init(year, month, day, null); //initializing the DatePicker (requires integers)

        //return an AlertDialog' instance using the Builder class, with its view, title and button set, then create it
        //return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, null).create();
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();

                Date date = new GregorianCalendar(year,month,day).getTime();

                sendResult(Activity.RESULT_OK, date);

            }
        }).create();

    }

    //method to retrieve the extra, set the date on the Crime, and refresh the text of the date button
    private void sendResult(int resultCode, Date date){

        if(getTargetFragment() == null){

            return;

        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

    }

}
