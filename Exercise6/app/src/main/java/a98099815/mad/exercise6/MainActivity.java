package a98099815.mad.exercise6;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
/**
 * blah
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

    }

}
