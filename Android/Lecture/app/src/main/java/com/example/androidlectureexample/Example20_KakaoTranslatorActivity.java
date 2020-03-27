package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Example20_KakaoTranslatorActivity extends AppCompatActivity {
    private TextView englishTv;
    private EditText contentEt;
    private Button translateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example20_kakao_translator);

        englishTv = (TextView) findViewById(R.id.englishTv);
        contentEt = (EditText) findViewById(R.id.contentEt);
        translateBtn = (Button) findViewById(R.id.translateBtn);

        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(
                        getApplicationContext(),
                        Example20Sub_Service.class
                );
                in.putExtra("script",contentEt.getText().toString());
                startService(in);
            }
        });


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("KAKAO","new intent");
        // receive resultData from the Service
        ArrayList<String> scriptResult =
                (ArrayList<String>) intent.getExtras().get("SCRIPTRESULT");
        // set the extracted data to TextView
        StringBuilder sb = new StringBuilder();
        for(String tmp : scriptResult){
            sb.append(tmp+"\n");
        }
        englishTv.setText(sb.toString());

    }
}
