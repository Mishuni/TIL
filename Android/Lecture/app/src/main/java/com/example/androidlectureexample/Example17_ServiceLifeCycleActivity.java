package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Example17_ServiceLifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example17_service_life_cycle);

        Button startBtn = (Button) findViewById(R.id.startServiceBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Service Component start !!
                Log.i("ServiceExam","start");
                Intent i = new Intent(
                        getApplicationContext(),
                        Example17Sub_LifeCycleService.class
                        );

                i.putExtra("MSG","HELLO");
                // Start Service
                // 만약 서비스 객체가 메모리에 없으면 생성하고 수행
                // onCreate() -> onStartCommand()
                // 만약 서비스 객체가 이미 존재하고 있으면
                // onStartCommand()
                startService(i);
            }
        });

        Button stopBtn = (Button) findViewById(R.id.stopServiceBtn);

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ServiceExam","stop");
                Intent i = new Intent(
                        getApplicationContext(),
                        Example17Sub_LifeCycleService.class
                );
                stopService(i);
            }
        });
    }
}
