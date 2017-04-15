package a98099815.mad.exercise6;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Jake Hambessis on 30/04/2016.
 */
public class Subject {

    private String mName;
    private String mNumber;
    private int mIsCore;
    private long mStartDate;
    private Context mContext;
    /**
     * Empty subject constructor
     */
    public Subject(){

    }

    /**
     * @param name set the subject name
     * @param number set the subject number
     * @param isCore set the isCore
     * @param startDate set the date
     */
    public Subject(Context ctx, String name, String number, int isCore, long startDate){
        this.setName(name);
        this.setNumber(number);
        this.setIsCore(isCore);
        this.setStartDate(startDate);
        this.mContext = ctx;

    }

    /**
     *
     * @param mName
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     *
     * @param mNumber
     */
    public void setNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    /**
     * @param mIsCore check if is core subject
     */
    public void setIsCore(int mIsCore) {
        this.mIsCore = mIsCore;
    }

    /**
     * @param mStartDate start date of subject
     */
    public void setStartDate(long mStartDate) {
        this.mStartDate = mStartDate;
    }

    /**
     * @return name of subject
     */
    public String getName() {
        return mName;
    }

    /**
     * @return the number of the subject
     */
    public String getNumber() {
        return mNumber;
    }

    /**
     * get isCore from radio group
     * @return iscore string
     */
    public String getIsCore() {
        String isCoreString = Constants.EMPTY_STRING;
        if(mIsCore == Constants.ELECTIVE_INT){
            isCoreString = mContext.getString(R.string.subject_core);
        }else if(mIsCore == Constants.CORE_INT){
            isCoreString = mContext.getString(R.string.subject_elective);
        }
        return isCoreString;
    }

    /**
     * get the date in the date picker
     * @return date format
     */
    public String getStartDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(mStartDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.UK);
        return dateFormat.format(calendar.getTime());
    }
}
