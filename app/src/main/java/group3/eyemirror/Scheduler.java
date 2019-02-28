package group3.eyemirror;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class Scheduler extends AppCompatActivity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText event;
    private EditText desc;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        event = (EditText) findViewById(R.id.eventText);
        desc = (EditText) findViewById(R.id.descriptionText);
        submit = (Button) findViewById(R.id.submit);
    }

    public void submitInfo(View v){
        String eventText = event.getText().toString();
        String descText = desc.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        System.out.println("Date: " + month + ":"+day+":"+year + " Time: " + hour + ":" + minute);
        startActivity(new Intent(Scheduler.this, MainActivity.class));
    }
}
