package id98099815.mad.exercise4;

import android.app.ProgressDialog;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

/**
 * blah
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    //Private fields for MainActivity
    private int mProgress = 0;
    private Spinner mSpinnerImageSelection;
    private String mSpinnerSelection;
    private String mPhotoNames[];
    private String mSelectedName;
    private int mNumPhotos;
    private ImageView mArtistImageView;
    private Button mDownloadButton;
    private Button mDownloadAllButton;
    private ArrayAdapter mAdapter;
    private int mSelectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialise the spinner
        mSpinnerImageSelection = (Spinner) findViewById(R.id.main_activity_image_selection_spinner);

        //Set the spinner array
        spinnerAction();

        //Set the onItemSelectedListener to the spinner
        mSpinnerImageSelection.setOnItemSelectedListener(this);

        //Initialise the image view
        mArtistImageView = (ImageView) findViewById(R.id.main_activity_artist_ImageView);

        //Initialise the download button
        mDownloadButton = (Button) findViewById(R.id.main_activity_download_button);

        //Set the onClickListener to the download button
        mDownloadButton.setOnClickListener(this);

        //Initialise the download all button
        mDownloadAllButton = (Button) findViewById(R.id.main_activity_download_all_button);

        //Set the onClickListener to the download all button
        mDownloadAllButton.setOnClickListener(this);
    }

    /**
     * spinnerAction() method attach the array adapter to the spinner
     */
    private void spinnerAction() {

        //Initialise the array adapter
        mAdapter = ArrayAdapter.createFromResource(this, R.array.main_activity_download_file_names, android.R.layout.simple_spinner_item);

        //Set the drop down view
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set the adapter
        mSpinnerImageSelection.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @param parent
     * @param view
     * @param position
     * @param id       Actions to occur if a list item in array is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Initialise the spinner
        mSpinnerImageSelection = (Spinner) findViewById(R.id.main_activity_image_selection_spinner);

        //Get the selected item of the spinner
        mSpinnerSelection = mSpinnerImageSelection.getSelectedItem().toString();

        //Display toast in accordance to spinner selection
        Toast.makeText(this, Constants.SELECTED + mSpinnerSelection, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param parent Actions to occur when nothing is selected
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //Do not show any toast is nothing is selected
        Toast.makeText(MainActivity.this, Constants.NO_STRING, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param view OnClick listener to handle actions for the download button and download all button
     */
    @Override
    public void onClick(View view) {

        //Get the position using selected item (int)
        mSelectedPosition = mSpinnerImageSelection.getSelectedItemPosition();

        //Get the string using selected item (String)
        mSelectedName = mSpinnerImageSelection.getSelectedItem().toString();

        //Switch-case statement to get the id of the button
        switch (view.getId()) {

            //If the button id is download button
            case R.id.main_activity_download_button:

                //Create new instance of DownloadPhotoAsyncTask class
                new DownloadPhotoAsyncTask(mSelectedPosition, mSelectedName).execute();
                break;

            //If the button if is download all button
            case R.id.main_activity_download_all_button:

                //Get the array
                mPhotoNames = getResources().getStringArray(R.array.main_activity_download_file_names);

                //Get the length off array
                mNumPhotos = mPhotoNames.length;

                //Create new instance of DownloadAllAsyncTask class
                new DownloadAllAsyncTask(mNumPhotos).execute(mPhotoNames);
                break;
        }
    }

    /**
     * AsyncTask for the download button
     */
    private class DownloadPhotoAsyncTask extends AsyncTask<Void, Void, Integer> {

        //DownloadPhotoAsyncTask fields
        private ProgressDialog mDialog;
        private ImageView mImageArtist;
        private int mSelectedPosition;
        private String mSelectedName;

        //DownloadPhotoAsyncTask constructor
        public DownloadPhotoAsyncTask(int itemPosition, String itemName) {

            //Create a new dialog
            mDialog = new ProgressDialog(MainActivity.this);

            //Store the itemPosition parameter
            mSelectedPosition = itemPosition;

            //Store the itemName parameter
            mSelectedName = itemName;
        }

        /**
         * @param params
         * @return Actions that are performed in the background of the async task
         */
        @Override
        protected Integer doInBackground(Void... params) {

            //Initialise and set the position to 0
            int itemPosition = 0;

            //Try set the position and the sleep time
            try {

                //Store the positions of array 0..2 so they can be returned in this method
                switch (mSelectedPosition) {

                    //If position is 0
                    case 0:

                        //Store first position
                        itemPosition = 0;
                        break;

                    //If position is 1
                    case 1:

                        //Store second position
                        itemPosition = 1;
                        break;

                    //If position is 2
                    case 2:

                        //Store third position
                        itemPosition = 2;
                        break;
                }

                //Set three seconds for the process dialog
                Thread.sleep(3000);
            }

            //If thread is waiting or sleeping and another thread interrupts catch the exception
            catch (InterruptedException e) {

                //Print exception in log cat window
                e.printStackTrace();
            }

            //Return the position of the item
            return itemPosition;
        }

        @Override
        protected void onPreExecute() {

            //Set message by getting the selected item and displaying the message
            mDialog.setMessage(Constants.DOWNLOADING + mSelectedName + Constants.ELLIPSIS);

            //Set cancelable to false so user cannot cancel progress because no cancel handling is required in activity
            mDialog.setCancelable(false);

            //Show process dialog
            mDialog.show();
        }

        /**
         * @param mPosition Actions to occur once AsyncTask is finished
         */
        @Override
        protected void onPostExecute(Integer mPosition) {

            //Dismiss the dialog
            mDialog.dismiss();

            //Initialise the image view
            mImageArtist = (ImageView) findViewById(R.id.main_activity_artist_ImageView);

            switch (mPosition) {

                //If position is 0
                case 0:

                    //Set apple rub image on image view
                    mImageArtist.setImageResource(R.drawable.main_activity_apple_rub);
                    break;

                //If position is 1
                case 1:

                    //Set eat apple image on image view
                    mImageArtist.setImageResource(R.drawable.main_activity_eat_apple);
                    break;

                //If position is 2
                case 2:

                    //Set android bin image
                    mImageArtist.setImageResource(R.drawable.main_activity_android_bin);
                    break;
            }
        }
    }

    /**
     * AsyncTask for the download all button
     */
    private class DownloadAllAsyncTask extends AsyncTask<String, String, Void> {

        //DownloadAllAsyncTask private fields
        private ProgressDialog mDialog;
        private int mNumPhotos;

        /**
         * @param numPhotos DownloadAllAsyncTask constructor
         */
        public DownloadAllAsyncTask(int numPhotos) {

            //Create a new dialog
            mDialog = new ProgressDialog(MainActivity.this);

            //Set the mNumPhotos field to the constructor parameter
            this.mNumPhotos = numPhotos;
        }

        /**
         * @param params
         * @return Actions that are performed in the background of the async task
         */
        @Override
        protected Void doInBackground(String... params) {

            //Increment until less than number of photos (3)
            for (int i = 0; i < mNumPhotos; i++) {

                //Publish the progress otherwise download progress will not appear
                publishProgress(params[i]);

                //Try to set 3 seconds per download of image
                try {
                    Thread.sleep(3000);
                }

                //If thread is waiting or sleeping and another thread interrupts catch the exception
                catch (InterruptedException e) {

                    //Print exception in log cat window
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * @param mPhotoValue Runs on the UI thread after publishProgress(Progress...)
         *                    is invoked. The specified values are the values passed
         *                    to publishProgress(Progress...).
         */
        @Override
        protected void onProgressUpdate(String... mPhotoValue) {

            //Set maximum number of files
            mDialog.setMax(mNumPhotos);

            //Set type of percentage increase depending on number of photos
            int progress = 3 / mNumPhotos;

            //Increment actual progress by 33
            mProgress += progress;

            //Set the dialog message, this will
            mDialog.setMessage(Constants.DOWNLOADING + mPhotoValue[0] + Constants.ELLIPSIS);

            //Set the progress to actual progress (i.e, mProgress)
            mDialog.setProgress(mProgress);

            //Switch-case to perform actions depending on the dialog message that appears.
            switch (mPhotoValue[0]) {

                //If dialog message is adele
                case Constants.ADELE:

                    //Set the image view to apple_rub image
                    mArtistImageView.setImageResource(R.drawable.main_activity_apple_rub);
                    break;

                //If dialog message is megan
                case Constants.MEGAN:

                    //Set the image view to eat apple
                    mArtistImageView.setImageResource(R.drawable.main_activity_eat_apple);
                    break;

                //Else dj_kevin
                default:

                    //Set image view to android_bin
                    mArtistImageView.setImageResource(R.drawable.main_activity_android_bin);
                    break;
            }
        }

        /**
         * Actions to occur before the execution starts.
         */
        @Override
        protected void onPreExecute() {

            //setMessage stub with no string. Strings will appear when photo downloads start
            mDialog.setMessage(Constants.NO_STRING);

            //Set the progress bar (no spinning wheel)
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

            //Do not make Indeterminate. i.e, show the progress bar and percentage.
            mDialog.setIndeterminate(false);

            //Don't allow user to cancel the progress as this activity does not require AsyncTask cancel handling.
            mDialog.setCancelable(false);

            //Show the progress bar
            mDialog.show();
        }

        /**
         * @param v Actions to occur after the AsyncTask is finished.
         */
        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            //Set the progress to 0 otherwise if the user were to press the button again, the progress will be at 100% instantly.
            mProgress = 0;

            //Dismiss the dialog otherwise it will keep showing
            mDialog.dismiss();
        }
    }


    /**
     * ACTIVITY LIFE CYCLE METHODS WITH LOG.D MESSAGES
     */

    /**
     * Called when the activity is becoming visible to the user.
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constants.TAG, Constants.ON_START);
    }

    /**
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart()
     */
    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, Constants.ON_RESTART);
    }

    /**
     * Called when the activity will start interacting with the user. At this point
     * your activity is at the top of the activity stack, with user input going to it.
     * Always followed by onPause().
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constants.TAG, Constants.ON_RESUME);
    }

    /**
     * Called when the system is about to start resuming a previous activity.
     * This is typically used to commit unsaved changes to persistent data, stop
     * animations and other things that may be consuming CPU, etc. Implementations
     * of this method must be very quick because the next activity will not be resumed
     * until this method returns.
     * Followed by either onResume() if the activity returns back to the front,
     * or onStop() if it becomes invisible to the user.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constants.TAG, Constants.ON_PAUSE);
    }

    /**
     * Called when the activity is no longer visible to the user, because another activity has
     * been resumed and is covering this one. This may happen either because a new activity is
     * being started, an existing one is being brought in front of this one, or this one is being destroyed.
     * Followed by either onRestart() if this activity is coming back to interact with the user, or
     * onDestroy() if this activity is going away.
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constants.TAG, Constants.ON_STOP);
    }

    /**
     * The final call you receive before your activity is destroyed. This can happen either because the activity is
     * finishing (someone called finish() on it, or because the system is temporarily destroying this instance of
     * the activity to save space. You can distinguish between these two scenarios with the isFinishing() method.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, Constants.ON_DESTROY);
    }
}

