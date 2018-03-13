package com.bignerdranch.android.finalapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.models.Details;
import com.bignerdranch.android.finalapp.models.DetailsArray;

import java.util.UUID;

public class DetailsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //no other class will access this extra
    private static final String ARG_DETAILS_ID = "details_id";

    private Details mDetails;

    private EditText mPurposeEditText;
    private EditText mPriceEditText;
    private EditText mDateEditView;
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

            }

            @Override
            public void afterTextChanged(Editable s) {

                //no need to implement anything

            }
        });

        //getting the reference and setting the text (date)
        mDateEditView = (EditText) v.findViewById(R.id.date);
        mDateEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //no need to implement anything

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //implement it later

            }

            @Override
            public void afterTextChanged(Editable s) {

                //no need to implement anything

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

        //method to set tag or ticket once a item was selected
        mTagOrTicketSpinner.setOnItemSelectedListener(this);

        //getting the reference and setting the button
        mSnapshotButton = (Button) v.findViewById(R.id.button_pic);

        //getting the reference and setting the button
        mDeleteButton = (Button) v.findViewById(R.id.button_delete);

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

}
