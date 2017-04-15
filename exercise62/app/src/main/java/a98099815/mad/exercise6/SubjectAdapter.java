package a98099815.mad.exercise6;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * Created by Jake Hambessis on 10/05/2016.
 */
public class SubjectAdapter extends BaseAdapter {

    private ArrayList<Subject> mSubjectList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private SubjectViewHolder mSubjectViewHolder;
    private Subject mSubject;

    /**
     * @param context set context
     * @param subjectArrayList set arrayList
     */
    public SubjectAdapter(Context context, ArrayList<Subject> subjectArrayList){
        this.mContext = context;
        this.mSubjectList = subjectArrayList;
    }

    /**
     *
     * @return return size of list
     */
    @Override
    public int getCount() {
        return mSubjectList.size();
    }

    /**
     *
     * @param position get position in list
     * @return position
     */
    @Override
    public Subject getItem(int position) {
        return mSubjectList.get(position);
    }

    /**
     *
     * @param position position in list
     * @return position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * inflate the adapter
     * set the text, date in the list view from the view holder
     * @param position
     * @param row
     * @param parent
     * @return row
     */
    @Override
    public View getView(int position, View row, ViewGroup parent) {

        if(row == null){
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = mLayoutInflater.inflate(R.layout.subject_data_row, parent, false);
            mSubjectViewHolder = new SubjectViewHolder(row);
            row.setTag(mSubjectViewHolder);
        } else{
            mSubjectViewHolder = (SubjectViewHolder) row.getTag();
        }

        mSubject = getItem(position);
        mSubjectViewHolder.mSubjectName.setText(mSubject.getName());
        mSubjectViewHolder.mSubjectNumber.setText(mSubject.getNumber());
        mSubjectViewHolder.mIsCore.setText(mSubject.getIsCore());
        mSubjectViewHolder.mStartDate.setText(mSubject.getStartDate());

        return row;
    }

    /**
     * Created by Jake Hambessis 10/05/2016
     */
    private class SubjectViewHolder {

        private TextView mSubjectName;
        private TextView mSubjectNumber;
        private TextView mIsCore;
        private TextView mStartDate;

        /**
         * @param view viewholder constructor to initialise GUI elements
         */
        public SubjectViewHolder(View view){
            mSubjectName = (TextView) view.findViewById(R.id.list_activity_subject_name_text_view);
            mSubjectNumber = (TextView) view.findViewById(R.id.list_activity_subject_number_text_view);
            mIsCore = (TextView) view.findViewById(R.id.list_activity_subject_type_text_view);
            mStartDate = (TextView) view.findViewById(R.id.list_activity_subject_date_text_view);
        }
    }
}
