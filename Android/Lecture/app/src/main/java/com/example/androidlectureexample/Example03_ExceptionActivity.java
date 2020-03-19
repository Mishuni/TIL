package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Example03_ExceptionActivity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example03_exception);

        iv = (ImageView) findViewById(R.id.iv);
        Button changeImgBtn = (Button)findViewById(R.id.imgChangeBtn);
        changeImgBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                iv.setImageResource(R.drawable.disney_night);
            }
        });
    }
}
