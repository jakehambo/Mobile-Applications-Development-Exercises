package a98099815.mad.exercise6;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jake Hambessis on 10/05/2016.
 */
public class SubjectManager {

    private static SubjectManager sOurInstance = new SubjectManager();
    private Context mContext;
    private MyDatabaseHelper dbHelper;

    /**
     * @return instance of subject manager
     */
    public static SubjectManager getInstance() {
        return sOurInstance;
    }

    /**
     * Empty SubjectManager constructor
     */
    private SubjectManager() {}

    /**
     * set context and initiate a new database helper
     * @param ctx ctx of activity
     */
    protected void list(Context ctx){
        mContext = ctx;
        dbHelper = new MyDatabaseHelper(ctx);
    }

    /**
     * Add the data to the database and display toast to notify user that data is added.
     */
    protected void addSubject(View v, String name, String number, int isCore, long startDate){
        dbHelper.addSubjectInformation(name, number, isCore, startDate);
        Toast.makeText(mContext, mContext.getString(R.string.subject_added), Toast.LENGTH_SHORT).show();
    }

    /**
     * return list of subject data from database using database helper
     * @param ctx context of list activity
     * @return array list
     */
    protected ArrayList<Subject> listSubject(Context ctx){
        this.mContext = ctx;
        ArrayList<Subject> subjectArrayList = dbHelper.getAllSubjects(ctx);
        return subjectArrayList;
    }
}