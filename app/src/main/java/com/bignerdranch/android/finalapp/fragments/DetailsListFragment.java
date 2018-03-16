package com.bignerdranch.android.finalapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.finalapp.R;
import com.bignerdranch.android.finalapp.activities.DetailsActivity;
import com.bignerdranch.android.finalapp.activities.DetailsPagerActivity;
import com.bignerdranch.android.finalapp.models.Details;
import com.bignerdranch.android.finalapp.models.DetailsArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailsListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_STATE = "subtitle";

    private RecyclerView mDetailsRecyclerView;
    private DetailsAdapter mDetailsAdapter;
    private boolean mSubtitleVisible;

    private TextView mTextView;
    private ImageButton mAddButton;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        //letting the FragmentManager know that my fragment should receive a call to onCreateOptionsMenu()
        //setHasOptionsMenu(true);

    }

    //method to create and configure the fragment's view
    //method to inflate fragment's view and return the inflate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_details_list, container, false);

        //wiring up the RecyclerView widget
        //mDetailsRecyclerView = (RecyclerView) view.findViewById(R.id.details_recycler_view); //getting the reference
        //mDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //RecyclerView needs LayoutManager to work


        View view = inflater.inflate(R.layout.fragment_details_list_button, container, false);

        //wiring up the RecyclerView widget
        mDetailsRecyclerView = (RecyclerView) view.findViewById(R.id.details_recycler_view_button); //getting the reference
        mDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //RecyclerView needs LayoutManager to work

        //updating mSubtitleVisible if it exists already
        if(savedInstanceState != null){

            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_STATE);

        }

        mTextView = view.findViewById(R.id.temporary_message);
        mAddButton = (ImageButton) view.findViewById(R.id.temporary_button);

        updateUI();

        return view;

    }

    //method to update changes in the list of details (fragment´s view)
    @Override
    public void onResume() {

        super.onResume();
        updateUI();

    }
    //save the mSubtitleVisible instance variable across rotation with the saved instance state mechanism
    @Override
    public void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_STATE, mSubtitleVisible);

    }

    //method to inflate the menu defined in fragment_details_list.xml
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_details_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_entries);

        if(mSubtitleVisible){

            subtitleItem.setTitle(R.string.hide_subtitle);

        }
        else{

            subtitleItem.setTitle(R.string.show_subtitle);

        }

    }

    //method to respond the user presses (action item)
    //to determine which action item (checking the ID of the MenuItem)
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.new_entry:

                Details details = new Details();

                DetailsArray.get(getActivity()).addDetails(details);

                Intent intent = DetailsPagerActivity.newIntent(getActivity(), details.getId());

                startActivity(intent);

                return true;

            case R.id.show_entries:

                mSubtitleVisible = !mSubtitleVisible;

                //this makes onCreateOptionsMenu to be called again
                getActivity().invalidateOptionsMenu();

                updateSubtitle();

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }

    }

    //method to show how many entries the user has
    private void updateSubtitle(){

        String subtitle;

        DetailsArray detailsArray = DetailsArray.get(getActivity());

        int detailsCount = detailsArray.getDetails().size();

        //String subtitle = getString(R.string.subtitle_format, detailsCount);
        subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, detailsCount, detailsCount);


        if(!mSubtitleVisible || DetailsArray.get(getActivity()).getDetails().isEmpty()){

            subtitle = null;

        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.getSupportActionBar().setSubtitle(subtitle);

    }

    //method to set up DetailsListFragment's UI
    private void updateUI() {

        DetailsArray detailsArray = DetailsArray.get(getActivity()); //getting singleton object
        List<Details> details = detailsArray.getDetails(); //getting all the details objects (List)

        if (mDetailsAdapter == null) {

            //set the adapter to the RecyclerView
            mDetailsAdapter = new DetailsAdapter(details);
            mDetailsRecyclerView.setAdapter(mDetailsAdapter);

        } else {

            mDetailsAdapter.notifyDataSetChanged();

        }

        if (details.size() > 0) {

            //letting the FragmentManager know that my fragment should receive a call to onCreateOptionsMenu()
            setHasOptionsMenu(true);
            mTextView.setVisibility(View.GONE);
            mAddButton.setVisibility(View.GONE);

        } else {

            //letting the FragmentManager know that my fragment should not receive a call to onCreateOptionsMenu()
            setHasOptionsMenu(false);
            mTextView.setVisibility(View.VISIBLE);
            mAddButton.setVisibility(View.VISIBLE);
            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Details details = new Details();

                    DetailsArray.get(getActivity()).addDetails(details);

                    Intent intent = DetailsPagerActivity.newIntent(getActivity(), details.getId());

                    startActivity(intent);

                }
            });

        }

        updateSubtitle();

    }

    //ViewHolder (inner class). It will inflate and own the layout
    private class DetailsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Details mDetails;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mImageView;

        public DetailsHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_details, parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.details_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.details_date);
            mImageView = (ImageView) itemView.findViewById(R.id.logo);

        }

        //method to set the information from the object (bind)
        //when given a details, DetailsHolder will update the title and the date to reflect the state of the Details
        public void bind(Details details) {

            mDetails = details;

            mTitleTextView.setText(mDetails.getPurpose());

            Date date = mDetails.getDate();

            SimpleDateFormat dayOfTheWeekFormat = new SimpleDateFormat("EEEE");
            String dayOfTheWeek = dayOfTheWeekFormat.format(date);

            SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
            String month = monthFormat.format(date);

            SimpleDateFormat dayFormat = new SimpleDateFormat("d");
            String day = dayFormat.format(date);

            SimpleDateFormat yearFormat = new SimpleDateFormat("y");
            String year = yearFormat.format(date);

            mDateTextView.setText(dayOfTheWeek + ", " + month + " " + day + ", " + year + ".");

            //0 or false: it is a tag
            if(!mDetails.isTagOrTicket()){

                mImageView.setImageResource(R.drawable.ic_tag);

            }
            //1 or true: it is a ticket
            else{

                mImageView.setImageResource(R.drawable.ic_ticket);

            }

        }

        //the DetailsHolder itself is implementing the OnClickListener interface.
        //on the itemView, which is the View for the entire row, the DetailsHolder is set as the receiver of click events.
        @Override
        public void onClick(View v) {

            //Toast.makeText(getActivity(), mDetails.getPurpose() + " clicked!", Toast.LENGTH_LONG).show();

            //start DetailsActivity
            //Intent intent = DetailsActivity.newIntent(getActivity(), mDetails.getId());
            Intent intent = DetailsPagerActivity.newIntent(getActivity(), mDetails.getId());
            startActivity(intent);

        }
    }

    //CrimeAdapter (inner class)
    //Necessary to display a ViewHolder or connect an object to an existing ViewHolder
    private class DetailsAdapter extends RecyclerView.Adapter<DetailsHolder> {

        private List<Details> mDetails;

        public DetailsAdapter(List<Details> details) {

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
            //holder.itemView.setBackgroundColor();
            holder.bind(details);

        }

        @Override
        public int getItemCount() {

            return mDetails.size();

        }
    }

}
