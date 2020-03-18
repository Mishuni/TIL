package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lecture1 = findViewById(R.id.btn1);

        lecture1.setOnClickListener(
                new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // find other Activity and execute it
                // there are 2 ways for finding it

                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                "com.example.androidlectureexample.Example01_LayoutActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
                //setContentView(R.layout.activity_example01_layout);
                // 2. implicit (묵시적)
            }

        });

    }
}
