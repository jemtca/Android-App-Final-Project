package com.bignerdranch.android.finalapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.models.Details;
import com.bignerdranch.android.finalapp.models.DetailsArray;

import java.util.List;

public class DetailsListFragment extends Fragment {

    private RecyclerView mDetailsRecyclerView;
    private DetailsAdapter mDetailsAdapter;

    //method to create and configure the fragment's view
    //method to inflate fragment's view and return the inflate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_details_list, container, false);

        //wiring up the RecyclerView widget
        mDetailsRecyclerView = (RecyclerView) view.findViewById(R.id.details_recycler_view); //getting the reference
        mDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //RecyclerView needs LayoutManager to work

        updateUI();

        return view;

    }

    //method to set up DetailsListFragment's UI
    private void updateUI(){

        DetailsArray detailsArray = DetailsArray.get(getActivity()); //getting singleton object
        List<Details> details = detailsArray.getDetails(); //getting all the details objects (List)

        //set the adapter to the RecyclerView
        mDetailsAdapter = new DetailsAdapter(details);
        mDetailsRecyclerView.setAdapter(mDetailsAdapter);

    }

    //ViewHolder (inner class). It will inflate and own the layout
    private class DetailsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Details mDetails;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        public DetailsHolder(LayoutInflater inflater, ViewGroup parent){

            super(inflater.inflate(R.layout.list_item_details, parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.details_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.details_date);

        }

        //method to set the information from the object (bind)
        //when given a details, DetailsHolder will update the title and the date to reflect the state of the Details
        public void bind(Details details){

            mDetails = details;
            mTitleTextView.setText(mDetails.getPurpose());
            mDateTextView.setText(mDetails.getDate().toString());

        }

        //the DetailsHolder itself is implementing the OnClickListener interface.
        //on the itemView, which is the View for the entire row, the DetailsHolder is set as the receiver of click events.
        @Override
        public void onClick(View v) {

            Toast.makeText(getActivity(), mDetails.getPurpose() + " clicked!", Toast.LENGTH_LONG).show();

        }
    }

    //CrimeAdapter (inner class)
    //Necessary to display a ViewHolder or connect an object to an existing ViewHolder
    private class DetailsAdapter extends RecyclerView.Adapter<DetailsHolder>{

        private List<Details> mDetails;

        public DetailsAdapter(List<Details> details){

            mDetails = details;

        }

        //method called by the RecyclerView when it needs a ViewHolder
        @Override
        public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new DetailsHolder(layoutInflater, parent);

        }

        //bind (details) will be called each time RecyclerView requests that a DetailsHolder
        //be bound to a particular details
        @Override
        public void onBindViewHolder(DetailsHolder holder, int position) {

            Details details = mDetails.get(position);
            holder.bind(details);

        }

        @Override
        public int getItemCount() {

            return mDetails.size();

        }
    }

}
