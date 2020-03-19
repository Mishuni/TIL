package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;

public class Main2Activity extends AppCompatActivity {
    HashSet<String> list;
    int i, count;
    TextView tv, tv2;
    static final String[] name={"이효진","정혜진","심재영","김하균","이진호","최시영","김현호",
    "송영재","조규창","최혜근","김택광","구기영","김효식","이윤호","서보인","최환","박진한","최영신",
    "권혁락"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.text);
        final Button lecture1 = findViewById(R.id.click);
        list = new HashSet<String>();
        i = 1;
        count = 6;
        lecture1.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        int random=0;
                        if(lecture1.getText().equals("다시시작")){
                            lecture1.setText("뽑기");
                            return;
                        }
                        if(i>count){
                            tv.setText("End");
                            tv2.setText(" ");
                            lecture1.setText("다시시작");
                            list.clear();
                            i=1;
                            return;
                        }
                        while(list.size()!=i&&i<=name.length){
                            random = (int)(Math.random()* name.length);
                            list.add(name[random]);
                            Log.i("MYTEST", String.valueOf(random));
                        }
                        tv.setText(name[random]);
                        tv2.setText(String.valueOf(i)+"번째 담청자\n 축하합니다!");
                        ++i;


                    }

                });
    }
}
