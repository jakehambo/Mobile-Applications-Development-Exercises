package id98099815.mad.exercise2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends ActionBarActivity implements View.OnClickListener {


    /**
     *
     * Called when the activity is first created. This is where you should do all of your normal static set up: create views,
     * bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
     * if there was one.
     Always followed by onStart().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayActivityOneData(); //get intent/set text method
        submitButtonCheckbox(); //submit button/checkbox method
    }

    /**
     * method to pass intent from activity one to activity one
     * 4 TextViews to display user input from activity one
     * */
    private void displayActivityOneData()
    {
        TextView name = (TextView)findViewById(R.id.activity_two_name_textview); //name textview declaration
        String namevalue = getIntent().getExtras().getString(Constants.NAME); //get the intent from name TextView in activity one
        name.setText(namevalue); //set the intent as text from name TextView gathered from activity one

        TextView phone = (TextView)findViewById(R.id.activity_two_phone_textview); //phone number textview declaration
        String phonevalue = getIntent().getExtras().getString(Constants.PHONE); //get the intent from phone TextView in activity one
        phone.setText(phonevalue); //set the intent as text from name TextView gathered from activity one

        TextView spinnertext = (TextView)findViewById(R.id.activity_two_home_textview); //textview declaration for spinner value from activity one
        String homevalue = getIntent().getExtras().getString(Constants.PHONE_SPINNER); //get the intent from phone type Spinner in activity one
        spinnertext.setText(homevalue); //set the intent as text from phone type Spinner gathered from activity one

        TextView email = (TextView)findViewById(R.id.activity_two_email_textview);//email textview declaration
        String emailvalue = getIntent().getExtras().getString(Constants.EMAIL); //get the intent from email TextView in activity one
        email.setText(emailvalue); //set the intent as text from email TextView gathered from activity one
    }

    //method for submit button in activity two
    private void submitButtonCheckbox()
    {
        Button activity_two_submit = (Button) findViewById(R.id.activity_two_submit_button); //submit button declaration
        activity_two_submit.setOnClickListener(new View.OnClickListener() { //make a listener for submit button
            @Override
            public void onClick(View view) {
                CheckBox agreecheckbox = (CheckBox) findViewById(R.id.activity_two_agree_checkbox); //checkbox declaration
                Intent intent = new Intent(); //new intent object
                boolean check = agreecheckbox.isChecked(); //check if the checkbox has been checked
                intent.putExtra(Constants.SELECT, check); //check the checkbox intent
                setResult(RESULT_OK, intent); //set the result code for checkbox so the result code can be checked in activity one
                finish(); //close activity two
            }
        });
    }

    //method generated for View.OnClickListener
    @Override
    public void onClick(View v) {

    }

    /**
     * ACITIVITY LIFE CYCLE METHODS WITH LOG.D MESSAGES
     */


    /**
     * Called when the activity is becoming visible to the user.
     Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
     */
    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(Constants.ERROR_TAG_ACTIVITY_TWO, Constants.ON_START);
    }
    /**
     * Called after your activity has been stopped, prior to it being started again.
     Always followed by onStart()

     */
    @Override
    public void onRestart()
    {
        super.onRestart();
        Log.d(Constants.ERROR_TAG_ACTIVITY_TWO, Constants.ON_RESTART);
    }

    /**
     * Called when the activity will start interacting with the user. At this point
     * your activity is at the top of the activity stack, with user input going to it.
     Always followed by onPause().
     */
    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(Constants.ERROR_TAG_ACTIVITY_TWO, Constants.ON_RESUME);
    }

    /**
     * Called when the system is about to start resuming a previous activity.
     * This is typically used to commit unsaved changes to persistent data, stop
     * animations and other things that may be consuming CPU, etc. Implementations
     * of this method must be very quick because the next activity will not be resumed
     * until this method returns.
     Followed by either onResume() if the activity returns back to the front,
     or onStop() if it becomes invisible to the user.
     */
    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(Constants.ERROR_TAG_ACTIVITY_TWO, Constants.ON_PAUSE);
    }

    /**
     * Called when the activity is no longer visible to the user, because another activity has
     * been resumed and is covering this one. This may happen either because a new activity is
     * being started, an existing one is being brought in front of this one, or this one is being destroyed.
     Followed by either onRestart() if this activity is coming back to interact with the user, or
     onDestroy() if this activity is going away.
     */
    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(Constants.ERROR_TAG_ACTIVITY_TWO, Constants.ON_STOP);
    }

    /**
     * The final call you receive before your activity is destroyed. This can happen either because the activity is
     * finishing (someone called finish() on it, or because the system is temporarily destroying this instance of
     * the activity to save space. You can distinguish between these two scenarios with the isFinishing() method.
     */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(Constants.ERROR_TAG_ACTIVITY_TWO, Constants.ON_DESTROY);
    }
}
