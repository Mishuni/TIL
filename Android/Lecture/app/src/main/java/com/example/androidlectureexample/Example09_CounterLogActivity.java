package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
class MyRunnable implements Runnable {
    // activity 내부 자원을 사용하기 어려움
}*/

public class Example09_CounterLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example09_counter_log);

        Button startBtn2 = (Button)findViewById(R.id.startBtn2);
        Button secondBtn2 = (Button)findViewById(R.id.secondBtn2);

        startBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thread 만들어서 실행
                MyRunnable runnable = new MyRunnable();
                Thread t = new Thread(runnable);
                t.start();
            }
        });

        // Toast 띄우기
        secondBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Example09_CounterLogActivity.this,
                        "Click Click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    // inner class for thread
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            long sum = 0;
            for(long i =0; i<1000000000L; ++i ){
                sum += i;
            }
            Log.i("Sum","result: "+sum);
        }
    }
}
