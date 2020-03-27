package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Example19_KakaoBookActivity extends AppCompatActivity {
    ListView kakaoBookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example19_kakao_book);

        // get Widget Reference
        final EditText kakaoEt = (EditText)findViewById(R.id.kakaoEt);
        Button kakaoSearchBtn = (Button)findViewById(R.id.kakaoSearchBtn);
        kakaoBookList = (ListView)findViewById(R.id.kakaoBookList);

        // Event Processing for Button
        kakaoSearchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        getApplicationContext(),
                        Example19Sub_Service.class
                );
                i.putExtra("KEYWORD", kakaoEt.getText().toString());
                startService(i);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("KAKAO","new intent");
        // receive resultData from the Service
        ArrayList<String> booksresult =
                (ArrayList<String>) intent.getExtras().get("BOOKRESULT");
        // set the extracted data to TextView
        ArrayAdapter adapter
                = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                booksresult);
        kakaoBookList.setAdapter(adapter);

    }


}
