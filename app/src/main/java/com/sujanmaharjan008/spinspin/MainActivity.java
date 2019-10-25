package com.sujanmaharjan008.spinspin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Spinner spin, spinP;
    private AutoCompleteTextView actvN;
    private TextView pickDate, pickTime;
    private String playersR[] = {"Noone","Ramzes","Solo"};
    private String playersN[] = {"Kappa","Ram Bahadur"};
    private String countries[] = {"Nepal","Russia"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin = findViewById(R.id.spin);
        spinP = findViewById(R.id.spinP);
        actvN  =findViewById(R.id.actvN);
        pickDate = findViewById(R.id.pickDate);
        pickTime = findViewById(R.id.pickTime);

        ArrayAdapter<String> NepalAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                playersN
        );

        actvN.setAdapter(NepalAdapter);

        actvN.setThreshold(1);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,
                countries
        );

        spin.setAdapter(arrayAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spin.getSelectedItem().toString().equals("Russia")){
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            playersR
                    );
                    spinP.setAdapter(arrayAdapter);
                }
                else {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            playersN
                    );
                    spinP.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });

        pickTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                loadTimePicker();
            }
        });
    }
    private void loadDatePicker(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, year, month, day
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "MM/DD/YYYY : " + month +"/"+dayOfMonth+"/"+year;
        pickDate.setText(date);
    }

    private void loadTimePicker(){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                pickTime.setText("Time is : " + hourOfDay +":" +minute);
            }
        },hour, minute, true);
        timePickerDialog.show();
    }
}
