package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Example05_SwipeActivity extends AppCompatActivity {
    double x1,x2;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example05_swipe);

        LinearLayout ll = (LinearLayout) findViewById(R.id.myLinearLayout);
        ll.setOnTouchListener(new View.OnTouchListener(){
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String msg = "";

                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    x1 = event.getX(); // 눌러져을 때 x좌표

                }
                else if(event.getAction()==MotionEvent.ACTION_UP){
                    x2 = event.getX(); // 눌렀다가 땔 때 x좌표
                    if(x1<x2){
                        // 오른쪽으로 스와이프
                        msg = "Right";
                    }
                    else if(x1>x2){
                        msg = "Left";
                    }
                    else{
                        msg = "Same";
                    }

                    Toast.makeText(Example05_SwipeActivity.this,msg, Toast.LENGTH_SHORT
                    ).show();
                    // innerclass 에서 상위에 있는
                    // Activity 개체를 불러오기
                }

                return true;
            }
        });

    }
}
