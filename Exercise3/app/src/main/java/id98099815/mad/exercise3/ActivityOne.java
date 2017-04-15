package id98099815.mad.exercise3;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.*;
import android.view.*;
import android.widget.*;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    //Private fields for all the buttons, image views and edit text fields
    private Button mQuitButton, mRotateButton, mSwapImageButton, mClrEmailButton;
    private ImageView mThumbsUpImageView, mThinkImageView;
    private EditText mEmailEditText, mNameEditText, mNumberEditText;
    private CharSequence name;
    private CharSequence phone;
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //The button listener methods
        exitActivity();
        rotateButton();
        swapImageButton();
        clearEmailButton();
        emailFocus();
    }

    /**
     * Method to set an on-click listener onto the quit button
     * Once button is clicked, exit the activity using finish();
     */
    private void exitActivity() {
        //Reference for the quit button
        mQuitButton = (Button) findViewById(R.id.activity_one_quit_button);
        //Attach click listener to the quit button
        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Exit the activity
                finish();
            }
        });
    }

    /**
     * Method to set an on-click listener onto the rotate button
     * Detects when screen is portrait and landscape to handle the button listener
     */
    private void rotateButton() {
        //Reference for the rotate button
        mRotateButton = (Button) findViewById(R.id.activity_one_rotate_button);
        //Attach click listener to the rotate button
        mRotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the orientation
                //   if screen is portrait*/
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //Make the screen landscape using setRequestedOrientation to change the orientation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    /*get the orientation
                      if the screen is landscape*/
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //Make the screen portrait using setRequestedOrientation to change the orientation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
    }

    /**
     * Method to set an on-click listener onto the swap image button
     * each orientation mode requires a different image to be set
     */
    private void swapImageButton() {
        //Reference for swap image button
        mSwapImageButton = (Button) findViewById(R.id.activity_one_swap_image_button);
        //Attach click listener to swap image button
        mSwapImageButton.setOnClickListener(new View.OnClickListener() {
            //Declare variable being used to count
            int i = 0;

            @Override
            public void onClick(View view) {
                 /* get the orientation
                   if screen is portrait*/
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //Increment
                    i++;
                    //Reference thumbs up image view
                    mThumbsUpImageView = (ImageView) findViewById(R.id.activity_one_thumbs_up_image_view);
                    //If count is even change to thumbs up image view
                    if ((i % 2) == 0) {
                        mThumbsUpImageView.setImageResource(R.drawable.thumbsup);
                    }
                    //Else if count is odd change image to wink
                    else {
                        mThumbsUpImageView.setImageResource(R.drawable.wink);
                    }
                    /*get the orientation
                      if the screen is landscape*/
                } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //Increment
                    i++;
                    //Reference think image view
                    mThinkImageView = (ImageView) findViewById(R.id.activity_one_think_image_view);
                    //If count is even change to think image view
                    if ((i % 2) == 0) mThinkImageView.setImageResource(R.drawable.think);
                        //else if count is odd change image to wink
                    else mThinkImageView.setImageResource(R.drawable.wink);
                }
            }
        });
    }

    /**
     * Method for the clr email button to clear the email edit text field.
     */
    private void clearEmailButton() {
        //Reference email button
        mClrEmailButton = (Button) findViewById(R.id.activity_one_clear_email_button);
        //Attach click listener to the clr email button
        mClrEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reference your email edit text field
                mEmailEditText = (EditText) findViewById(R.id.activity_one_email_edit_text);
                //Clear the email edit text field
                mEmailEditText.setText(Constants.CLEAR);
            }
        });
    }

    /**
     * Method for the your email edit text field to detect focus on the
     * field and display toast's notifying the user if on or off focus.
     */
    private void emailFocus() {
        //Reference your email edit text field
        mEmailEditText = (EditText) findViewById(R.id.activity_one_email_edit_text);
        //Attach focus listener to the your email edit text field

        mEmailEditText.setOnFocusChangeListener(this);
        mEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }

    /**
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_one, menu);
        return true;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* Handle action bar item clicks here. The action bar will
           automatically handle clicks on the Home/Up button, so long
           as you specify a parent activity in AndroidManifest.xml. */
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * @param v onClick method to set an onClickListener onto the clear name button
     *          try and catch clause to test if EditText can be set to null
     *          clear the name EditText field
     */
    @Override
    public void onClick(View v) {
        //try setting your name edit text field to null
        try {
            //Reference your name edit text field
            mNameEditText = null;
            //Clear the your name edit text field
            mNameEditText.setText(Constants.CLEAR);
            //If exception occurs, catch the NullPointerException
        } catch (NullPointerException e) {
            //display error message in log cat window and information in regards to why the error occurred
            Log.e(Constants.TAG, Constants.NULL_ERROR_MESSAGE + e.toString());
        }
        //Reference your name edit text field
        mNameEditText = (EditText) findViewById(R.id.activity_one_name_edit_text);
        //Clear the your name edit text field
        mNameEditText.setText(Constants.CLEAR);
    }

    /**
     * @param savedInstanceState Called to retrieve per-instance state from an activity before being killed so that the state can be
     *                           restored in onCreate(Bundle) or onRestoreInstanceState(Bundle) (the Bundle populated by this method
     *                           will be passed to both).
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Message in log cat window to indicate onSaveInstanceState is being performed.
        Log.d(Constants.TAG, Constants.ACTIVITY_ONE_ON_SAVE);

        //Reference your name edit text field
        mNameEditText = (EditText) findViewById(R.id.activity_one_name_edit_text);

        //Reference your number edit text field
        mNumberEditText = (EditText) findViewById(R.id.activity_one_number_edit_text);
        orientation = getResources().getConfiguration().orientation;

        //Get name text
        name = mNameEditText.getText();

        //Get number text
        phone = mNumberEditText.getText();

        //Save the orientation
        savedInstanceState.putInt(Constants.ORIENTATION, orientation);

        //Save the name instance
        savedInstanceState.putCharSequence(Constants.NAME, name);

        //Save the phone instance
        savedInstanceState.putCharSequence(Constants.PHONE, phone);
    }

    /**
     * @param savedInstanceState This method is called after onStart() when the activity is being re-initialized from a
     *                           previously saved state, given here in savedInstanceState. Most implementations will simply
     *                           use onCreate(Bundle) to restore their state, but it is sometimes convenient to do it here after
     *                           all of the initialization has been done or to allow subclasses to decide whether to use your default
     *                           implementation. The default implementation of this method performs a restore of any view state that
     *                           had previously been frozen by onSaveInstanceState(Bundle).
     */

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        //Message in log cat window to indicate onRestoreInstanceState is being performed.
        Log.d(Constants.TAG, Constants.ACTIVITY_ONE_ON_RESTORE);
        //Reference your name edit text field
        mNameEditText = (EditText) findViewById(R.id.activity_one_name_edit_text);
        //Reference your number edit text field
        mNumberEditText = (EditText) findViewById(R.id.activity_one_number_edit_text);
        //Retrieve name text from savedInstanceState by getting the sequence of chars
        name = savedInstanceState.getCharSequence(Constants.NAME);
        //Retrieve phone text from savedInstanceState by getting the sequence of chars
        phone = savedInstanceState.getCharSequence(Constants.PHONE);
        orientation = savedInstanceState.getInt(Constants.ORIENTATION);
        //Restore name instance
        mNameEditText.setText(name);
        //Restore number instance
        mNumberEditText.setText(phone);
    }

    /**
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //If no focus display a toast saying "Email has LOST focus"
        if (!hasFocus) {
            Toast.makeText(ActivityOne.this, Constants.EMAIL_NO_FOCUS, Toast.LENGTH_SHORT);
        }
        //Else if focus display a toast saying "Email HAS focus"
        else {
            Toast.makeText(ActivityOne.this, Constants.EMAIL_HAS_FOCUS, Toast.LENGTH_SHORT);
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

