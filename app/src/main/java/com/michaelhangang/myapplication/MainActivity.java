package com.michaelhangang.myapplication;

import android.icu.text.SimpleDateFormat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    RadioGroup radionGroup;
    RadioButton radioButton;
    Spinner numWord;
    Date date;
    SimpleDateFormat curFormater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numWord = findViewById(R.id.numWords);
        radionGroup =findViewById(R.id.books);
        curFormater = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.getDefault());

    }


    public void startPlan(View view) {
        //get date
        date = new Date();
        String today = curFormater.format(date);
        //get book name
        int radioId = radionGroup.getCheckedRadioButtonId();
        radioButton= findViewById(radioId);
        String book = (String) radioButton.getText();
        //get numWords
        String num = numWord.getSelectedItem().toString();


        Toast.makeText(this, today, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "You selected "+book+"\nToday learn "+num+" words", Toast.LENGTH_SHORT).show();


    }
}// end Main



