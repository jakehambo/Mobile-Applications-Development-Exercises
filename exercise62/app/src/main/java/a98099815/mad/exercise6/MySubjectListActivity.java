package a98099815.mad.exercise6;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jake Hambessis on 1/05/2016.
 */
public class MySubjectListActivity extends ListActivity {

    private SubjectManager mSubjectManager;
    private ArrayList<Subject> mSubjectArrayList;
    private SubjectAdapter mSubjectAdapter;

    /**
     * call setList
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setList();
    }

    /**
     * initialise instance of subject manager class,
     * Set the array list, list view and subject adapter
     */
    private void setList(){
        mSubjectManager = SubjectManager.getInstance();
        mSubjectArrayList = mSubjectManager.listSubject(this);
        ListView subjectListView = getListView();
        mSubjectAdapter = new SubjectAdapter(this, mSubjectArrayList);
        subjectListView.setAdapter(mSubjectAdapter);
    }
}
