package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Example06_SendMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example06_send_msg);
        // xml 에 정의되어 있는 component reference 획득
        TextView tv = (TextView) findViewById(R.id.msgTv);
        Button closeBtn = (Button) findViewById(R.id.closeBtn);

        // Activity 에 전달된 intent 획득
        Intent i = getIntent();
        // getExtras? 붙어 있는 모든 데이터들 다 받아 오기
        // key 값을 이용해서 특정 값 가져오기
        String msg = (String) i.getExtras().get("sendMsg");
        tv.setText(msg);

        closeBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Example06_SendMsgActivity.this.finish();
            }
        });

    }
}
