package com.michaelhangang.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class Setting extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListern;
    private Calendar calendar;
    private   int year , month,day, hour,min;
    private   SimpleDateFormat curFormater;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        curFormater = new SimpleDateFormat(getString(R.string.pattern), Locale.getDefault());
        textView =findViewById(R.id.showDate);
        calendar = Calendar.getInstance();
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                month= m ;
                year = y;
                day =d;
            }
        };
        mTimeSetListern = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                hour =h;
                min =m;
            }
        };

    }

    public void onClose(View view) {
        finish();
    }

    public void onSetDate(View view) {
        Calendar cal = Calendar.getInstance();
        int calYear = cal.get(cal.YEAR);
        int calMonth = cal.get(cal.MONTH);
        int calDay = cal.get(cal.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                mDateSetListener,
                calYear, calMonth, calDay);
        dialog.show();

    }


    public void onSetTime(View view) {
        Calendar cal = Calendar.getInstance();
        int calHour = cal.get(Calendar.HOUR);
        int calMin = cal.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog =new TimePickerDialog(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,mTimeSetListern,calHour,calMin,true);

        timePickerDialog.show();


    }
    public void onConfirm (View view){
        calendar.set(year,month,day,hour,min);

        textView.setText(curFormater.format(calendar.getTime()));
    }

}
