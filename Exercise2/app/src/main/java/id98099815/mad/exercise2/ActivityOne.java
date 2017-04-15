package id98099815.mad.exercise2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener {

    /**
     * Called when the activity is first created. This is where you should do all of your normal static set up: create views,
     * bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
     * if there was one.
     * Always followed by onStart().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // method to handle spinner functionality
        spinnerAction();

        clearFields();// method to handle spinner functionality
        submitButton();// method to handle submit button functionality
        exitApplication();// method to handle exit button functionality
    }

    /**
     * method for exit button
     */
    private void exitApplication() {
        Button exit = (Button) findViewById(R.id.activity_one_exit_button); // exit button declaration
        exit.setOnClickListener(new View.OnClickListener() { //set listener for exit button
            @Override
            public void onClick(View v) {
                finish();//close the activity

            }
        });
    }

    //method for submit button
    private void submitButton() {
        Button submitButton = (Button) findViewById(R.id.activity_one_submit_button); // submit button declaration
        submitButton.setOnClickListener(new View.OnClickListener() //set listener for submit button
        {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.activity_one_name_editText); //name EditText declaration
                EditText email = (EditText) findViewById(R.id.activity_one_email_editText);//phone EditText declaration
                EditText phone = (EditText) findViewById(R.id.activity_one_number_editText);// email EditText declaration
                Spinner spinner = (Spinner) findViewById(R.id.activity_one_phonetype_spinner);//phone type Spinner declaration

                String namevalue = name.getText().toString();//get the name EditText value
                String emailvalue = email.getText().toString();//get the email EditText value
                String phonevalue = phone.getText().toString();//get the phone number EditText value
                String spinnerselection = spinner.getSelectedItem().toString(); //get the phone type Spinner selection

                Intent intent = new Intent(ActivityOne.this, ActivityTwo.class); //go to activity two
                intent.putExtra(Constants.NAME, namevalue); //store intent from name EditText
                intent.putExtra(Constants.EMAIL, emailvalue);//store intent from email EditText
                intent.putExtra(Constants.PHONE, phonevalue);//store intent from phone EditText
                intent.putExtra(Constants.PHONE_SPINNER, spinnerselection);//store intent from phone type Spinner
                startActivityForResult(intent, Constants.DATA_ENTERED); //start activity two with textviews replaced with intent from activity one

            }
        });
    }

    //method for clear button to clear EditText and set Spinner value to first element of array
    private void clearFields() {
        Button clearButton = (Button) findViewById(R.id.activity_one_clear_button); //clear button declaration
        clearButton.setOnClickListener(new View.OnClickListener() //set listener for clear button
        {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.activity_one_name_editText); //name EditText declaration
                EditText email = (EditText) findViewById(R.id.activity_one_email_editText); //phone EditText declaration
                EditText number = (EditText) findViewById(R.id.activity_one_number_editText); //email EditText declaration
                Spinner phonespinner = (Spinner) findViewById(R.id.activity_one_phonetype_spinner); //phone type EditText declaration
                phonespinner.setSelection(Constants.HOME); //set spinner to first selection i.e home
                name.setText(Constants.CLEAR); //clear the name EditText
                email.setText(Constants.CLEAR); // clear the email EditText
                number.setText(Constants.CLEAR); //clear the number EditText
            }
        });
    }

    //method for phone type spinner
    private void spinnerAction() {
        Spinner spinnerphonetype = (Spinner) findViewById(R.id.activity_one_phonetype_spinner);//phonetype spinner declaration
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_spinner, android.R.layout.simple_spinner_item); //create the spinner array
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //set the drop down function
        spinnerphonetype.setAdapter(adapter); //set the spinner array
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with, the resultCode
     * it returned, and any additional data from it. The resultCode will be RESULT_CANCELED if the activity explicitly
     * returned that, didn't return any result, or crashed during its operation.
     * <p/>
     * You will receive this call immediately before onResume() when your activity is re-starting.
     * <p/>
     * This method is never invoked if your activity sets noHistory to true.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.DATA_ENTERED) { //check if data entered
            if (resultCode == RESULT_OK) { //if the result code is ok
                boolean checked = data.getBooleanExtra(Constants.SELECT, false); //checked or not checked boolean

                //if checkbox is checked
                if (checked) {
                    Toast.makeText(ActivityOne.this, Constants.AGREE, Toast.LENGTH_LONG).show(); //display toast on activity one
                } else {
                    Toast.makeText(this, Constants.DISAGREE, Toast.LENGTH_LONG).show(); //display toast on activity one
                }
            }
        }
    }

    /**
     * Initialize the contents of the Activity's standard options menu. You should place your menu items in to menu.
     * <p/>
     * This is only called once, the first time the options menu is displayed. To update the menu every time it is displayed, see onPrepareOptionsMenu(Menu).
     * <p/>
     * The default implementation populates the menu with standard system menu items. These are placed in the
     * CATEGORY_SYSTEM group so that they will be correctly ordered with application-defined menu items. Deriving classes
     * should always call through to the base implementation.
     * <p/>
     * You can safely hold on to menu (and any items created from it), making modifications to it as desired, until the
     * next time onCreateOptionsMenu() is called.
     * <p/>
     * When you add items to the menu, you can implement the Activity's onOptionsItemSelected(MenuItem) method to handle them there.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_one, menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal processing happen
     * (calling the item's Runnable or sending a message to its Handler as appropriate). You
     * can use this method for any items for which you would like to do processing without
     * those other facilities.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
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
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constants.ERROR_TAG_ACTIVITY_ONE, Constants.ON_START);
    }

    /**
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart()
     */
    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(Constants.ERROR_TAG_ACTIVITY_ONE, Constants.ON_RESTART);
    }

    /**
     * Called when the activity will start interacting with the user. At this point
     * your activity is at the top of the activity stack, with user input going to it.
     * Always followed by onPause().
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constants.ERROR_TAG_ACTIVITY_ONE, Constants.ON_RESUME);
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
        Log.d(Constants.ERROR_TAG_ACTIVITY_ONE, Constants.ON_PAUSE);
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
        Log.d(Constants.ERROR_TAG_ACTIVITY_ONE, Constants.ON_STOP);
    }

    /**
     * The final call you receive before your activity is destroyed. This can happen either because the activity is
     * finishing (someone called finish() on it, or because the system is temporarily destroying this instance of
     * the activity to save space. You can distinguish between these two scenarios with the isFinishing() method.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.ERROR_TAG_ACTIVITY_ONE, Constants.ON_DESTROY);
    }


}
