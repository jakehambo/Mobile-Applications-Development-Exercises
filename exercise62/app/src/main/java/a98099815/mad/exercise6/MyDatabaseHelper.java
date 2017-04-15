package a98099815.mad.exercise6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jake Hambessis on 10/05/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private Subject mSubject;
    private ContentValues mContentValues;
    private SQLiteDatabase mDb;
    private Cursor mCursor;
    private String mName;
    private String mNumber;
    private int mIsScore;
    private long mDate;

    //Database table
    private static final String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + SubjectData.SubjectTableData.TABLE_NAME
            + " (" + SubjectData.SubjectTableData.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SubjectData.SubjectTableData.KEY_SUBJECT_NAME+ " TEXT, "
            + SubjectData.SubjectTableData.KEY_SUBJECT_NUMBER + " TEXT, "
            + SubjectData.SubjectTableData.KEY_IS_CORE + " INTEGER, "
            + SubjectData.SubjectTableData.KEY_START_DATE + " INTEGER);";

    /**
     * MyDatabaseHelper Constructor
     * @param ctx context of activity
     */
    public MyDatabaseHelper(Context ctx) {
        super(ctx, SubjectData.SubjectTableData.TABLE_NAME, null, SubjectData.SubjectTableData.DATABASE_VERSION);
    }

    /**
     * Create the table in the database
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    /**
     * needed if the db changes
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SubjectData.SubjectTableData.SQL_DROP_TABLE + SubjectData.SubjectTableData.TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * add data to the database
     * @param subjectName
     * @param subjectNumber
     * @param isCore
     * @param startDate
     */
    public void addSubjectInformation(String subjectName, String subjectNumber, int isCore, long startDate) {
        mContentValues = new ContentValues();
        mContentValues.put(SubjectData.SubjectTableData.KEY_SUBJECT_NAME, subjectName);
        mContentValues.put(SubjectData.SubjectTableData.KEY_SUBJECT_NUMBER, subjectNumber);
        mContentValues.put(SubjectData.SubjectTableData.KEY_IS_CORE, isCore);
        mContentValues.put(SubjectData.SubjectTableData.KEY_START_DATE, startDate);
        mDb = this.getWritableDatabase();
        mDb.insert(SubjectData.SubjectTableData.TABLE_NAME, null, mContentValues);
        mDb.close();
    }

    /**
     * Add data to list
     * @return subject array list
     */
    public ArrayList<Subject> getAllSubjects(Context ctx) {

        //Initialise context, array list and database
        this.mContext = ctx;
        ArrayList<Subject> subjectArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        //Initialise the columns in an array.
        String[] columns = new String[]{SubjectData.SubjectTableData.KEY_SUBJECT_NAME,
                SubjectData.SubjectTableData.KEY_SUBJECT_NUMBER,
                SubjectData.SubjectTableData.KEY_IS_CORE,
                SubjectData.SubjectTableData.KEY_START_DATE
        };

        //Query to display all columns and records in database
        mCursor = db.query(SubjectData.SubjectTableData.TABLE_NAME, columns, null, null, null, null, null);

        //Get the data from the database
        while(mCursor.moveToNext()){
            mName = mCursor.getString(mCursor.getColumnIndex(SubjectData.SubjectTableData.KEY_SUBJECT_NAME));
            mNumber = mCursor.getString(mCursor.getColumnIndex(SubjectData.SubjectTableData.KEY_SUBJECT_NUMBER));
            mIsScore = mCursor.getInt(mCursor.getColumnIndex(SubjectData.SubjectTableData.KEY_IS_CORE));
            mDate = mCursor.getLong(mCursor.getColumnIndex(SubjectData.SubjectTableData.KEY_START_DATE));
            mSubject = new Subject(mContext, mName, mNumber, mIsScore, mDate);
            subjectArrayList.add(mSubject);
        }
        mCursor.close();
        db.close();

        return subjectArrayList;
    }
}
