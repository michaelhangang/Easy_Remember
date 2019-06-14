package com.michaelhangang.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Report extends AppCompatActivity {
    ArrayList<Record> records;
    private TableLayout tableLayout;
    SimpleDateFormat curFormater;
    private int cet4=0, cet6=0, ielts=0;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        records = (ArrayList<Record>) getIntent().getExtras().getSerializable(getString(R.string.records));
        curFormater = new SimpleDateFormat(getString(R.string.pattern), Locale.getDefault());
        textView = findViewById(R.id.textView);
        //table layout
        tableLayout = findViewById(R.id.table);
        addHeader();

        addRows();
        onShowResult();
    }

    public void onClose(View view) {
        finish();
    }

    public void addHeader(){
        TableRow tr =new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, getString(R.string.header1),Color.BLACK, Typeface.BOLD, R.drawable.cell_shape));
        tr.addView(getTextView(0, getString(R.string.header2),Color.BLACK, Typeface.BOLD, R.drawable.cell_shape));
        tr.addView(getTextView(0, getString(R.string.header3),Color.BLACK, Typeface.BOLD, R.drawable.cell_shape));

        tableLayout.addView(tr, getTblLayoutParams());

    }
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        params.weight = 1;
        return params;
    }
    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }

    private TextView getTextView(int id, String title,int color, int typeface,int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setTextColor(color);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundResource(bgColor);
        tv.setText(title);
        tv.setPadding(40, 40, 40, 40);
        tv.setLayoutParams(getLayoutParams());
        return tv;
    }

    public void addRows(){
        // Collections.reverse(trainScheduleList);

        for(Record record: records){
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());

            tr.addView(getTextView(0, curFormater.format(record.data),Color.BLACK, Typeface.BOLD, R.drawable.cell_shape ));
            tr.addView(getTextView(0, record.book,Color.BLACK, Typeface.BOLD, R.drawable.cell_shape));
            tr.addView(getTextView(0, Integer.toString(record.words),Color.BLACK, Typeface.BOLD, R.drawable.cell_shape));

            if(record.book.equals(getString(R.string.cet4))){
                cet4 +=record.words;
            }
           else if(record.book.equals(getString(R.string.cet6))){
                cet6 +=record.words;
            }
           else if(record.book.equals(getString(R.string.ielts))){
                ielts +=record.words;
            }
            tableLayout.addView(tr, getTblLayoutParams());
        }


    }

    public void onShowResult(){

        String result =getString(R.string.result1)+cet4+getString(R.string.result2)+cet6+getString(R.string.result3)+ielts
                +getString(R.string.total)+(cet4+cet6+ielts);
        textView.setText(result);
    }
}
