package a98099815.mad.exercise6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Jake Hambessis 10/05/2016
 */
public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {

    private EditText mSubjectName;
    private EditText mSubjectNumber;
    private DatePicker mDatePicker;
    private int mIsCore;
    private SubjectManager mSubjectMan;
    private String mStartDate;
    private long startDateLong;

    /**
     * initialise edit text field and date pickers
     * initialise instance of subject manager class
     * add the objects from subject manager class
     * set date picker values
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSubjectName = (EditText) findViewById(R.id.subject_name_main_activity_edit_text);
        mSubjectNumber = (EditText) findViewById(R.id.main_activity_subject_number_edit_text);
        mDatePicker = (DatePicker) findViewById(R.id.main_activity_subject_date_picker);
        mSubjectMan = SubjectManager.getInstance();
        mSubjectMan.list(this);
        mDatePicker.init(Constants.YEAR, Constants.MONTH, Constants.DAY, this);
    }


    /**
     * isCore must be int when clicked
     *
     * @param v for onClick
     */
    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.main_activity_core_radio_button:
                if (checked) {
                    mIsCore = Constants.ELECTIVE_INT;
                    break;
                }
            case R.id.main_activity_elective_radio_button:
                if (checked) {
                    mIsCore = Constants.CORE_INT;
                    break;
                }
            default:
                mIsCore = Constants.NO_SELECTION;
                break;
        }
    }

    /**
     * call subject manager to add subject when add subject button is clicked
     *
     * @param v for onClick
     */
    public void addSubject(View v) {
        mSubjectMan.addSubject(v, mSubjectName.getText().toString(), mSubjectNumber.getText().toString(), mIsCore, startDateLong);
    }

    /**
     * start the list activity
     *
     * @param v for onClick
     */
    public void listSubjects(View v) {
        Intent startList = new Intent(this, MySubjectListActivity.class);
        startActivity(startList);
    }

    /**
     * get date in long values when the date is changed
     *
     * @param view        datepicker view
     * @param year        year in date picker
     * @param monthOfYear month of date picker
     * @param dayOfMonth  day of date picker
     */
    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, monthOfYear, dayOfMonth);
        startDateLong = calendar.getTimeInMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.UK);
        mStartDate = dateFormat.format(startDateLong);
    }
}
