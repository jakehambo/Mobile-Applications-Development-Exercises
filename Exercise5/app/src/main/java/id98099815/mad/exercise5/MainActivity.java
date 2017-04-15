
//Packages
package id98099815.mad.exercise5;

//Imports
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import java.util.*;
import android.widget.*;

/**
 * Copyright (C) 2016 Jake Hambessis
 * The MainActivityClass that contains the recycler view and async task classes
 */
public class MainActivity extends AppCompatActivity {

    //Private fields for MainActivity class
    private ArrayList<TrainData> mTrainList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TrainAdapter mTrainAdapter;
    private ProgressBar mProgressGreenSquareBar;
    private TextView mArrivalTimeETA;
    private Random mRandom = new Random();

    /**
     * @param savedInstanceState On create method containing recycler view data, list data, progress bar and text view.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the progress bar for the green linear layout
        mProgressGreenSquareBar = (ProgressBar) findViewById(R.id.main_activity_adapter_item_progressbar);

        //Set the arrival time eta text view
        mArrivalTimeETA = (TextView) findViewById(R.id.main_activity_adapter_item_arrival_time_eta_text_view);

        //Set the layout view for main activity
        setContentView(R.layout.main_activity);

        //Set the toolbar
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);

        //Set the toolbar to act as action bar in main activity window
        setSupportActionBar(toolBar);

        //Set the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.main_activity_train_recycler_view);

        //Set the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Call trainDataList method containing array list items in recycler view
        trainDataList();

        //Create new instance of TrainAdapter with array list and context parameters
        mTrainAdapter = new TrainAdapter(this.mTrainList, this);

        //Set the recycler view adapter
        mRecyclerView.setAdapter(mTrainAdapter);

        //Set the animations to take place on items as changes are made to the adapter.
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Array list items in recycler view
     */
    private void trainDataList() {

        //Albion list
        mTrainList.add(new TrainData(
                getResources().getInteger(R.integer.albion_arrival_eta_time),
                getResources().getString(R.string.albion_platform),
                getResources().getString(R.string.albion_arrival_time),
                getResources().getString(R.string.albion_status),
                getResources().getString(R.string.albion_destination),
                getResources().getString(R.string.albion_destination_time)
        ));

        //Arncliffe list
        mTrainList.add(new TrainData(
                getResources().getInteger(R.integer.arncliffe_arrival_eta_time),
                getResources().getString(R.string.arncliffe_platform),
                getResources().getString(R.string.arncliffe_arrival_time),
                getResources().getString(R.string.arncliffe_status),
                getResources().getString(R.string.arncliffe_destination),
                getResources().getString(R.string.arncliffe_destination_time)
        ));

        //Artarmion list
        mTrainList.add(new TrainData(
                getResources().getInteger(R.integer.artarmion_arrival_eta_time),
                getResources().getString(R.string.artarmion_platform),
                getResources().getString(R.string.artarmion_arrival_time),
                getResources().getString(R.string.artarmion_status),
                getResources().getString(R.string.artarmion_destination),
                getResources().getString(R.string.artarmion_destination_time)
        ));

        //Berowra list
        mTrainList.add(new TrainData(
                getResources().getInteger(R.integer.berowra_arrival_eta_time),
                getResources().getString(R.string.berowra_platform),
                getResources().getString(R.string.berowra_arrival_time),
                getResources().getString(R.string.berowra_status),
                getResources().getString(R.string.berowra_destination),
                getResources().getString(R.string.berowra_destination_time)
        ));
    }

    /**
     * @param menu menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_one, menu);
        return true;
    }

    /**
     * @param item Method to handle item funtionality in toolbar
     * @return super
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Get the id
        int id = item.getItemId();

        //If item is add
        if (id == R.id.action_add_main_activity) {

            //Berowra list
            mTrainList.add(new TrainData(
                    getResources().getInteger(R.integer.berowra_arrival_eta_time),
                    getResources().getString(R.string.berowra_platform),
                    getResources().getString(R.string.berowra_arrival_time),
                    getResources().getString(R.string.berowra_status),
                    getResources().getString(R.string.berowra_destination),
                    getResources().getString(R.string.berowra_destination_time)
            ));


            //Let train adapter know that data has been changed
            mTrainAdapter.notifyDataSetChanged();
        }

        //If item is delete
        else if (id == R.id.action_delete_main_activity) {

            //Clear the array list
            mTrainList.clear();

            //Let train adapter know that data has been changed
            mTrainAdapter.notifyDataSetChanged();

        }

        //If item is quit
        else if (id == R.id.action_quit_main_activity) {

            //Close the activity
            finish();
        }

        //If item is refresh
        else if (id == R.id.action_refresh_main_activity) {

            //Call async task to refresh recycler
            new RefreshRecyclerAsyncTask().execute();

            //Let train adapter know that data has been changed
            mTrainAdapter.notifyDataSetChanged();
        }

        //Return super
        return super.onOptionsItemSelected(item);
    }


    /**
     * The AsyncTask to handle arrival time eta data change for all recycler view elements
     */
    private class RefreshRecyclerAsyncTask extends AsyncTask<Void, Void, Integer> {

        //RefreshRecyclerAsyncTask private fields;
        private ProgressBar mProgressBarMain;

        /**
         * Method to handle actions before execution
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Set the progress bar
            mProgressBarMain = (ProgressBar) findViewById(R.id.main_activity_progressbar);

            //Hide the recycler view
            mRecyclerView.setVisibility(View.GONE);

            //Make progress bar visible
            mProgressBarMain.setVisibility(View.VISIBLE);
        }

        /**
         * @param params Method to handle actions performed in background
         * @return null
         */
        @Override
        protected Integer doInBackground(Void... params) {

            //Try set time to 3 seconds
            try {

                //Set time to 3 seconds
                Thread.sleep(Constants.THREE_SECONDS);
            }

            //If an InterruptedException occurs, catch the exception
            catch (InterruptedException e) {

                //Show exception
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @param integer Method to handle actions after execution
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            //Hide progress bar
            mProgressBarMain.setVisibility(View.GONE);

            //Show the recycler view
            mRecyclerView.setVisibility(View.VISIBLE);

            //For each element in the array list
            for (TrainData trainDatas : mTrainList) {

                //Set the arrival time eta to be random
                trainDatas.setArrivalTimeETA(mRandom.nextInt(20) + 1);

                //Let train adapter know that data has been changed
                mTrainAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * AsyncTask to handle arrival time eta data change for a specific recycler view element
     */
    private class RefreshGreenSquareAsyncTask extends AsyncTask<TrainData, Void, Void> {

        //Private fields for RefreshGreenSquareAsyncTask
        private int mRandomOneToTwenty;

        /**
         * The constructor
         *
         * @param progressGreenSquareBar Progress bar for green linear layout
         * @param arrivalTimeETA         Arrival time eta text for green linear layout
         */
        public RefreshGreenSquareAsyncTask(ProgressBar progressGreenSquareBar, TextView arrivalTimeETA) {

            //Set adapter item progress bar
            mProgressGreenSquareBar = progressGreenSquareBar;

            //Set arrival time eta text view
            mArrivalTimeETA = arrivalTimeETA;
        }

        /**
         * Method to handle actions before execution
         */
        @Override
        protected void onPreExecute() {

            //Super onPreExecute
            super.onPreExecute();

            //Hide arrival time ta text view
            mArrivalTimeETA.setVisibility(View.GONE);

            //Show the adapter item progress bar
            mProgressGreenSquareBar.setVisibility(View.VISIBLE);
        }

        /**
         * @param trainDatas Method to handle actions in background
         * @return null
         */
        @Override
        protected Void doInBackground(TrainData... trainDatas) {

            //Initialise random number formula
            mRandomOneToTwenty = mRandom.nextInt(20) + 1;

            //Set the arrival time eta text view to be a random number
            trainDatas[0].setArrivalTimeETA(mRandomOneToTwenty);

            //Try set time to 2 seconds
            try {

                //Set time to 2 seconds
                Thread.sleep(Constants.TWO_SECONDS);
            }

            //If an InterruptedException occurs, catch the exception
            catch (InterruptedException e) {

                //Show exception
                e.printStackTrace();
            }
            return null;
        }


        /**
         * @param aVoid Method to handle actions after execution
         */
        @Override
        protected void onPostExecute(Void aVoid) {

            //Show arrival time eta text
            mArrivalTimeETA.setVisibility(View.VISIBLE);

            //Hide adapter item progress bar
            mProgressGreenSquareBar.setVisibility(View.GONE);
        }
    }

    /**
     * The TrainData class containing data for the recycler view
     */
    public class TrainData {

        //TrainData class private fields
        private String mArrivalTime;
        private String mPlatform;
        private int mArrivalTimeETA;
        private String mStatus;
        private String mDestination;
        private String mDestinationTime;

        /**
         * The TrainData constructor
         *
         * @param arrivalTimeETA  arrival time eta
         * @param platform        platform
         * @param arrivalTime     arrival time
         * @param status          status
         * @param destination     destination
         * @param destinationTime destination time
         */
        public TrainData(int arrivalTimeETA, String platform, String arrivalTime, String status, String destination, String destinationTime) {

            //Set field to parameter
            this.mArrivalTimeETA = arrivalTimeETA;

            //Set field to parameter
            this.mPlatform = platform;

            //Set field to parameter
            this.mArrivalTime = arrivalTime;

            //Set field to parameter
            this.mStatus = status;

            //Set field to parameter
            this.mDestination = destination;

            //Set field to parameter
            this.mDestinationTime = destinationTime;
        }

        /**
         * Get the arrival time eta
         *
         * @return arrival time eta
         */
        public int getArrivalTimeETA() {
            return mArrivalTimeETA;
        }

        /**
         * Set the arrival time eta
         *
         * @param arrivalTimeETA arrival time eta
         */
        public void setArrivalTimeETA(int arrivalTimeETA) {
            this.mArrivalTimeETA = arrivalTimeETA;
        }

        /**
         * Get the platform
         *
         * @return platform
         */
        public String getPlatform() {
            return mPlatform;
        }

        /**
         * Get the arrival time
         *
         * @return arrival time
         */
        public String getArrivalTime() {
            return mArrivalTime;
        }

        /**
         * Get the status
         *
         * @return status
         */
        public String getStatus() {
            return mStatus;
        }

        /**
         * Get the destination
         *
         * @return destination
         */
        public String getDestination() {
            return mDestination;
        }

        /**
         * Get the destination time
         *
         * @return destination time
         */
        public String getDestinationTime() {
            return mDestinationTime;
        }
    }

    /**
     * The TrainAdapter class that extends recycler view containing all functionality for recycler view
     */
    public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

        //TrainAdapter private fields
        private ArrayList<TrainData> mTrainList = new ArrayList<>();
        private TrainData mTrainDatas;
        private View mItemLayoutView;
        private Context mContext;

        /**
         * The TrainAdapter constructor
         *
         * @param mTrainList train list
         * @param context    main activity context
         */
        public TrainAdapter(ArrayList<TrainData> mTrainList, Context context) {

            //Set field to parameter
            this.mTrainList = mTrainList;

            //Set field to parameter
            this.mContext = context;
        }

        /**
         * onCreateViewHolder to display the data in the recycler view from the view holder
         *
         * @param parent   ViewGroup
         * @param viewType type of view
         * @return instance of view holder class
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //Set the view of for the recycler view
            mItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);

            //Return instance of the ViewHolder class
            return new ViewHolder(mItemLayoutView, mTrainList);
        }

        /**
         * onBindViewHolder to bind the data in the recycler view
         *
         * @param holder   ViewHolder class
         * @param position the list position
         */
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //Get the position in the train list
            mTrainDatas = mTrainList.get(position);

            //Set the divider line to be visible
            holder.mViewLine.setVisibility(View.VISIBLE);

            //Set the text for arrival time eta text view
            holder.mArrivalTimeETATextView.setText(String.format(mTrainDatas.getArrivalTimeETA()
                    + Constants.STRING_PLACEHOLDER, getResources().getString(R.string.eta_min)));

            //Set the text for the platform text view
            holder.mPlatformTextView.setText(mTrainDatas.getPlatform());

            //Set the text for the arrival time text view
            holder.mArrivalTimeTextView.setText(mTrainDatas.getArrivalTime());

            //Set the text for the status text view
            holder.mStatusTextView.setText(mTrainDatas.getStatus());

            //Set the text for the destination time text view
            holder.mDestinationTimeTextView.setText(mTrainDatas.getDestinationTime());

            //Set the text for the destination text view
            holder.mDestinationTextView.setText(mTrainDatas.getDestination());

            //Context equals this activity
            mContext = MainActivity.this;

            //If string is "Late"
            if (mTrainDatas.getStatus().equals(getResources().getString(R.string.status_late))) {

                //Change text color to red
                holder.mStatusTextView.setTextColor(ContextCompat.getColor(mContext, R.color.status_red));
            }

            //Else if "On Time" or any other text
            else {

                //Change color to green
                holder.mStatusTextView.setTextColor(ContextCompat.getColor(mContext, R.color.status_green));
            }
        }

        /**
         * Return the size of the list (int)
         *
         * @return size of list
         */
        @Override
        public int getItemCount() {

            //Return size of list
            return mTrainList.size();
        }

        /**
         * The view holder class extending recycler view and implementing on click listener
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            //Private fields for ViewHolder class
            private TextView mArrivalTimeETATextView;
            private TextView mPlatformTextView;
            private TextView mArrivalTimeTextView;
            private TextView mStatusTextView;
            private TextView mDestinationTimeTextView;
            private TextView mDestinationTextView;
            private ProgressBar mProgressGreenSquareBar;
            private View mViewLine;
            private int mPosition;
            private TrainData mTrainDatas;
            private View mArrivalTimeETALinearLayout;
            private ArrayList<TrainData> mTrainList = new ArrayList<>();

            /**
             * The ViewHolder constructor
             *
             * @param itemView   the item view
             * @param mTrainList the train list
             */
            public ViewHolder(View itemView, ArrayList<TrainData> mTrainList) {

                //Super for view
                super(itemView);

                //Field = parameter
                this.mTrainList = mTrainList;

                //Call view holder items method
                viewHolderItems();

                //Set the onClick listener on the Linear Layout
                mArrivalTimeETALinearLayout.setOnClickListener(this);
            }

            /**
             * The items for the view holder to displayed in the recycler view
             */
            private void viewHolderItems() {

                //Set Linear Layout (Green Square)
                mArrivalTimeETALinearLayout = itemView.findViewById(R.id.main_activity_adapter_item_eta_linear_layout);

                //Set the line divider
                mViewLine = itemView.findViewById(R.id.main_activity_adapter_item_line_view);

                //Set the arrival time eta text view
                mArrivalTimeETATextView = (TextView) itemView.findViewById(R.id.main_activity_adapter_item_arrival_time_eta_text_view);

                //Set the platform text view
                mPlatformTextView = (TextView) itemView.findViewById(R.id.main_activity_adapter_item_platform_text_view);

                //Set the arrival time text view
                mArrivalTimeTextView = (TextView) itemView.findViewById(R.id.main_activity_adapter_item_arrival_time_text_view);

                //Set the status text view
                mStatusTextView = (TextView) itemView.findViewById(R.id.main_activity_adapter_item_status_text_view);

                //Set the destination time text view
                mDestinationTimeTextView = (TextView) itemView.findViewById(R.id.main_activity_adapter_item_destination_time_text_view);

                //Set the destination text view
                mDestinationTextView = (TextView) itemView.findViewById(R.id.main_activity_adapter_item_destination_text_view);

                //Set the adapter item progress bar
                mProgressGreenSquareBar = (ProgressBar) itemView.findViewById(R.id.main_activity_adapter_item_progressbar);
            }

            /**
             * On click listener to handle green linear layout actions
             *
             * @param v view
             */
            @Override
            public void onClick(View v) {

                //Get the position of the train adapter
                mPosition = getAdapterPosition();

                //Get position of train list
                this.mTrainDatas = mTrainList.get(mPosition);

                //Toast that displays "Refreshing" and the selected destination once the user has interacted with green square linear layout
                Toast.makeText(MainActivity.this, getResources().getString(R.string.refreshing_message)
                        + this.mTrainDatas.getDestination(), Toast.LENGTH_SHORT).show();

                //Instance of RefreshGreenSquareAsyncTask
                new RefreshGreenSquareAsyncTask(mProgressGreenSquareBar, mArrivalTimeETATextView).execute(this.mTrainDatas);

                //Let train adapter know that data has been changed
                mTrainAdapter.notifyDataSetChanged();
            }
        }
    }
}