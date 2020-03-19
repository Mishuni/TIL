package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Example07_DataFromActivity extends AppCompatActivity {
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example07_data_from);

        // create data for Spinner
        // we make some data as String in this example
        final ArrayList<String> list = new ArrayList<String>();
        list.add("grape");
        list.add("strawberry");
        list.add("banana");
        list.add("apple");

        // get Spinner reference
        Spinner spinner = (Spinner)findViewById(R.id.mySpinner);
        // create an Adapter (there are various kinds of Adapter)
        // among many kind of Adapter, we use ArrayAdapter
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                                        android.R.layout.simple_spinner_dropdown_item,
                                        list);
        // attach the adapter to spinner
        spinner.setAdapter(adapter);

        // spinner's event processing
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position : the order of the item selected
                result = list.get(position);
                Log.i("SELECTED",result+" selected!");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("resultValue",result);
                setResult(7000,returnIntent);
                Example07_DataFromActivity.this.finish();
            }

        });
    }
}
