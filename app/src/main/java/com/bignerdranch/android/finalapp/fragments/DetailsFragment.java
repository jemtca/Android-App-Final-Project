package com.bignerdranch.android.finalapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.activities.DetailsPagerActivity;
import com.bignerdranch.android.finalapp.models.Details;
import com.bignerdranch.android.finalapp.models.DetailsArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //no other class will access this extra
    private static final String ARG_DETAILS_ID = "details_id";
    private static final String DIALOG_DATE = "dialog_date";

    private static final int REQUEST_DATE = 0;

    private Details mDetails;

    private EditText mPurposeEditText;
    private EditText mPriceEditText;
    private Button mDateButton;
    private Spinner mTagOrTicketSpinner;
    private Button mSnapshotButton;
    //private
    private Button mDeleteButton;

    //DetailsActivity will call this method instead the constructor
    //This method creates the fragment instance and bundles up and sets its fragment
    public static DetailsFragment newInstance(UUID detailsId) {

        Bundle args = new Bundle();

        //inserts a key and a serializable object to the bundle
        args.putSerializable(ARG_DETAILS_ID, detailsId);

        DetailsFragment fragment = new DetailsFragment();
        //attaching the arguments to the fragment
        fragment.setArguments(args);

        return fragment;

    }


    //method to configure the fragment instance
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //retrieve the extra from the intent
        UUID detailsId = (UUID) getArguments().getSerializable(ARG_DETAILS_ID);

        mDetails = DetailsArray.get(getActivity()).getDetails(detailsId);

    }

    //method to create and configure the fragment's view
    //method to inflate fragment's view and return the inflate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);

        //getting the reference and setting text (purpose)
        mPurposeEditText = (EditText) v.findViewById(R.id.purpose);
        mPurposeEditText.setText(mDetails.getPurpose());

        //mPurposeEditText.setEnabled(false);
        //mPurposeEditText.setInputType(InputType.TYPE_NULL);

        mPurposeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //no need to implement anything

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mDetails.setPurpose(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

                //no need to implement anything

            }
        });

        //getting the reference and setting text (price)
        mPriceEditText = (EditText) v.findViewById(R.id.price);

        if(mDetails.getPrice() == 0.0){

            mPriceEditText.setText("");

        }
        else{

            mPriceEditText.setText(String.valueOf(mDetails.getPrice()));


        }

        //mPriceEditText.setEnabled(false);

        mPriceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //no need to implement anything

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().isEmpty()) {

                    mDetails.setPrice(Float.parseFloat(s.toString()));

                }
                else if(s.toString().isEmpty()){

                    mDetails.setPrice(0.0f);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                //no need to implement anything

            }
        });

        //getting the reference and setting the button (date)
        mDateButton = (Button) v.findViewById(R.id.date);

        updateDate();

        //mDateButton.setEnabled(false);

        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();

                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mDetails.getDate());
                //setting this fragment as a target
                dialog.setTargetFragment(DetailsFragment.this, REQUEST_DATE);
                //the string parameter uniquely identifies the DialogFragment in the FragmentManager’s list
                dialog.show(fragmentManager, DIALOG_DATE);

            }
        });



        //getting the reference and setting the spinner
        mTagOrTicketSpinner = (Spinner) v.findViewById(R.id.spinner2);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner2, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //Apply the adapter to the spinner
        mTagOrTicketSpinner.setAdapter(adapter);

        //set tag or ticket position
        int myInt = (mDetails.isTagOrTicket()) ? 1 : 0;
        mTagOrTicketSpinner.setSelection(myInt + 1);

        //mTagOrTicketSpinner.setEnabled(false);

        //method to set tag or ticket once a item was selected
        mTagOrTicketSpinner.setOnItemSelectedListener(this);

        //getting the reference and setting the button
        mSnapshotButton = (Button) v.findViewById(R.id.button_pic);

        //mSnapshotButton.setEnabled(false);

        //getting the reference and setting the button
        mDeleteButton = (Button) v.findViewById(R.id.button_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int index = DetailsArray.get(getActivity()).getDetails().indexOf(mDetails);

                DetailsArray.get(getActivity()).deleleDetails(index);

                getActivity().finish();

            }
        });

        return v;

    }

    //method from AdapterView.OnItemSelectedListener interface
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (pos) {

            case 0:

                break;

            case 1:

                mDetails.setTagOrTicket(false); //it is a tag

                break;

            case 2:

                mDetails.setTagOrTicket(true); //it is a ticket

                break;

        }

    }

    //method from AdapterView.OnItemSelectedListener interface
    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode != Activity.RESULT_OK){

            return;

        }

        if(requestCode == REQUEST_DATE){

            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mDetails.setDate(date);

            updateDate();

        }

    }

    //method to inflate the menu defined in fragment_details.xml
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_details, menu);

    }

    //method to respond the user presses (action item)
    //to determine which action item (checking the ID of the MenuItem)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.edit_details:
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }

    }

    //method to update date
    private void updateDate(){

        Date date = mDetails.getDate();

        SimpleDateFormat dayOfTheWeekFormat = new SimpleDateFormat("EEEE");
        String dayOfTheWeek = dayOfTheWeekFormat.format(date);

        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        String month = monthFormat.format(date);

        SimpleDateFormat dayFormat = new SimpleDateFormat("d");
        String day = dayFormat.format(date);

        SimpleDateFormat yearFormat = new SimpleDateFormat("y");
        String year = yearFormat.format(date);

        mDateButton.setText(dayOfTheWeek + ", " + month + " " + day + ", " + year + ".");

    }

}
