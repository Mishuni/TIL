package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
// ANR : Application Not Responding
public class Example08_ANRActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example08_anr);

        tv = (TextView)findViewById(R.id.sumTv);

        // 첫번째 버튼에 대한 reference 획득 & 이벤트 처리

        Button startBtn = (Button)findViewById(R.id.startBtn);
        Button secondBtn = (Button)findViewById(R.id.secondBtn);

        // 첫번째 버튼
        startBtn.setOnClickListener(new View.OnClickListener(){
            // 버튼을 누르면 상당히 오랜 시간 연산이 수행될 것
            // 백만번 연산 수행
            @Override
            public void onClick(View v) {
                long sum = 0;
                for (long i =0; i<1000000000L; ++i){
                    sum += i;
                }
                Log.i("Sum","result : "+sum);
            }

        });

        // 두번째 버튼
        secondBtn.setOnClickListener(new View.OnClickListener(){
            // Toast message 출력
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        Example08_ANRActivity.this,
                        "clicked!",
                        Toast.LENGTH_SHORT).show();


            }
        });

    }
}
