package com.michaelhangang.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    RadioGroup radionGroup;
    RadioButton radioButton;
    Spinner numWord;
    Date date;
    SimpleDateFormat curFormater;
    ArrayList<Record> records;
    Gson gson;
    TextView lastRecord;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get spinner
        numWord = findViewById(R.id.numWords);
        //get radio group
        radionGroup =findViewById(R.id.books);
        checkBox =findViewById(R.id.clearall);
        lastRecord =findViewById(R.id.lastRecord);
        //format data
        curFormater = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.getDefault());
        //create gson object
        gson = new Gson();
        //create a file
        File file = getBaseContext().getFileStreamPath(getString(R.string.file));
        //check if file exist
         if( file.exists()){
             String  temp = readFromFile();
             if(temp.isEmpty()) {
                 records = new ArrayList<Record>();
             }

             else{
                 Type type = new TypeToken<ArrayList<Record>>(){}.getType();
                 //convert json string to container
                 records = gson.fromJson(temp,type);
                 }
         }

          else
             records = new ArrayList<Record>();

    }

    @Override
    protected void onResume() {
        super.onResume();
        lastRecord.setText("");
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
        String item =numWord.getSelectedItem().toString();
        int  num =Integer.parseInt(item);

        Record record =new Record(date,book,num);
        records.add(record);
        save();
        Toast.makeText(this, today, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "You selected "+book+"\nToday learn "+num+" words", Toast.LENGTH_SHORT).show();


    }


    public void onReport(View view) {
        Intent intent = new Intent(this, Report.class);
        intent.putExtra("records",records);
        startActivity(intent);

    }
    public void save() {

        String jsonString = gson.toJson(records);
        saveToFile(jsonString);
    }

    public void load(View view){
        if(records.size()==0)
            lastRecord.setText("");
        if(records.size()>0) {
            Record   lastRec = records.get((records.size() - 1));
            //get details
            String dateOfRec = curFormater.format(lastRec.data);
            String bookOfRec = lastRec.book;
            String wordsOfRec = Integer.toString(lastRec.words);
            lastRecord.setText("Date: "+dateOfRec + "\n\nBook: " + bookOfRec + "\n\nWords: " + wordsOfRec);
        }
    }

    public boolean compareDate(Date date){


        Calendar ccc= Calendar.getInstance();
        ccc.setTime(date);
        ccc.add(Calendar.MONDAY,1);
        Date dat = ccc.getTime();
       return   date.before(dat);
//        if(i)
//            Toast.makeText(this, "is true", Toast.LENGTH_SHORT).show();
    }


    public void saveToFile(String jsonString) {
        try
        {
            // to save to file "test.txt" in data/data/packagename/File
            FileOutputStream ofile = openFileOutput(getString(R.string.file),MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(ofile);
            osw.write(jsonString);
            osw.flush();
            osw.close();

        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    // Read the name from file
    public String readFromFile() {
        // read the file from the data/data/packagename
        String str="";

        try
        {
            // reading from data/data/packagename/File
            FileInputStream fin = openFileInput(getString(R.string.file));
            InputStreamReader isr = new InputStreamReader(fin);
            char [] inputBuffer = new char[100];
            int charRead;
            while((charRead = isr.read(inputBuffer)) > 0)
            {
                String readString =String.copyValueOf(inputBuffer,0,charRead);
                str += readString;
            }
          //  return str;
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return str;
    }

    public void onDelete(View view) {
        if (checkBox.isChecked())
            records.clear();
        if(records.size()>0) {
           records.remove(records.size() - 1);
        }
        save();
    }



}// end Main



