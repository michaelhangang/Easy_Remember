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
import java.util.Locale;

public class Report extends AppCompatActivity {
    ArrayList<Record> records;
    private TableLayout tableLayout;
    SimpleDateFormat curFormater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
         records =(ArrayList<Record>)getIntent().getExtras().getSerializable("records");
        curFormater = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.getDefault());

        //table layout
        tableLayout = findViewById(R.id.table);
        addHeader();

        addRows();
    }


    public void onClose(View view) {
        finish();
    }

    public void addHeader(){
        TableRow tr =new TableRow(this);
        tr.setLayoutParams(getLayoutParams());
        tr.addView(getTextView(0, "Date"));
        tr.addView(getTextView(0, "Book"));
        tr.addView(getTextView(0, "Words"));

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

    private TextView getTextView(int id, String title) {
        TextView tv = new TextView(this);
        tv.setId(id);

        tv.setText(title);
        tv.setPadding(40, 40, 40, 40);
        tv.setLayoutParams(getLayoutParams());
       // tv.setOnClickListener((View.OnClickListener) this);
        return tv;
    }

    public void addRows(){
        // Collections.reverse(trainScheduleList);

        for(Record record: records){
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParams());

            tr.addView(getTextView(0, curFormater.format(record.data)));
            tr.addView(getTextView(0, record.book));
            tr.addView(getTextView(0, Integer.toString(record.words)));


            tableLayout.addView(tr, getTblLayoutParams());
        }


    }
}
