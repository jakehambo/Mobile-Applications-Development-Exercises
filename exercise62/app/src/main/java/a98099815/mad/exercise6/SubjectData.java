package a98099815.mad.exercise6;

/**
 * Created by Jake Hambessis on 16/05/2016.
 */
public class SubjectData {
    /**
     * Constructor
     */
    public SubjectData() {}

    /**
     * Abstract class
     */
    public static abstract class SubjectTableData {
        public static final String TABLE_NAME = "Subjects";
        public static final String KEY_ID = "_id";
        public static final String KEY_SUBJECT_NAME = "subjectname";
        public static final String KEY_SUBJECT_NUMBER = "subjectnumber";
        public static final String KEY_IS_CORE = "iscore";
        public static final String KEY_START_DATE = "startdate";
        public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS ";
        public static final int DATABASE_VERSION = 1;

    }
}
