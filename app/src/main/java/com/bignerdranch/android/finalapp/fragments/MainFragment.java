package com.bignerdranch.android.finalapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.activities.SecondActivity;
import com.bignerdranch.android.finalapp.models.ProvincesTerritoriesArray;

public class MainFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinner;
    private Button mTestButton;

    private ProvincesTerritoriesArray mProvincesTerritoriesArray;

    //method to configure the fragment instance
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //initialization of the territories/provinces in Canada
        mProvincesTerritoriesArray = new ProvincesTerritoriesArray();


    }

    //method to create and configure the fragment's view
    //method to inflate fragment's view and return the inflate view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);


        mSpinner = (Spinner) v.findViewById(R.id.spinner);
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        //method to go to the second activity once a item was selected
        mSpinner.setOnItemSelectedListener(this);



        //button to test before using the spinner
        mTestButton = (Button) v.findViewById(R.id.test_button); //getting the reference
        mTestButton.setOnClickListener(new View.OnClickListener() { //setting the listener
            @Override
            public void onClick(View v) {

                //int index = getProvinceTerritoryIndex("Alberta");

                //SecondFragment sf = SecondFragment.newInstance(index);

                //FragmentManager fm = getFragmentManager();
                //FragmentTransaction ft = fm.beginTransaction();
                //ft.replace(R.id.fragment_container, sf);
                //ft.addToBackStack(null);
                //ft.commit();


                // start second activity
                //int index = getProvinceTerritoryIndex("Alberta");
                //Intent intent = SecondActivity.newIntent(getActivity(), index);
                //startActivity(intent);

            }
        });

        return v;

    }

    //method from AdapterView.OnItemSelectedListener interface
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

        /*
        // On selecting a spinner item
        String item = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item + "(" +id + " " + pos + ")", Toast.LENGTH_LONG).show();
        */


        int index;
        Intent intent;

        switch(pos){
            case 0:

                break;

            case 1:

                index = getProvinceTerritoryIndex("Alberta");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 2:

                index = getProvinceTerritoryIndex("British Columbia");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 3:

                index = getProvinceTerritoryIndex("Manitoba");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 4:

                index = getProvinceTerritoryIndex("New Brunswick");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 5:

                index = getProvinceTerritoryIndex("Newfoundland and Labrador");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 6:

                index = getProvinceTerritoryIndex("Nova Scotia");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 7:

                index = getProvinceTerritoryIndex("Ontario");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 8:

                index = getProvinceTerritoryIndex("Prince Edward Island");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 9:

                index = getProvinceTerritoryIndex("Quebec");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 10:

                index = getProvinceTerritoryIndex("Saskatchewan");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 11:

                index = getProvinceTerritoryIndex("Northwest Territories");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 12:

                index = getProvinceTerritoryIndex("Nunavut");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

            case 13:

                index = getProvinceTerritoryIndex("Yukon");
                intent = SecondActivity.newIntent(getActivity(), index);
                startActivity(intent);

                break;

        }


    }

    //method from AdapterView.OnItemSelectedListener interface
    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }

    //method to find the index of a specific province (name) inside the array of provinces
    private int getProvinceTerritoryIndex(String name){

        int index = -1;
        boolean found = false;

        for(int i = 0; i < mProvincesTerritoriesArray.getProvincesTerritories().length && !found; i++){

            if(name.equalsIgnoreCase(mProvincesTerritoriesArray.getProvincesTerritories()[i].getName())){

                index = i;
                found = true;

            }

        }

        return index;

    }

}
