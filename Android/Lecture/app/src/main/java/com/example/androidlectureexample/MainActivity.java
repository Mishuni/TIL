package com.example.androidlectureexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lecture1 = (Button)findViewById(R.id.btn1);
        Button lecture2 = (Button)findViewById(R.id.btn2);
        Button lecture3 = (Button)findViewById(R.id.btn3);
        Button lecture4 = (Button)findViewById(R.id.btn4);
        Button lecture5 = (Button)findViewById(R.id.btn5);
        Button lecture6 = (Button)findViewById(R.id.btn6);
        Button lecture7 = (Button)findViewById(R.id.btn7);
        Button lecture8 = (Button)findViewById(R.id.btn8);
        Button lecture9 = (Button)findViewById(R.id.btn9);

        Button lecture12 = (Button)findViewById(R.id.btn12);
        Button lecture14 = (Button)findViewById(R.id.btn14);
        Button lecture15 = (Button)findViewById(R.id.btn15);
        Button lecture16 = (Button)findViewById(R.id.btn16);
        Button lecture17 = (Button)findViewById(R.id.btn17);

        Button bonus = (Button)findViewById(R.id.btn_bonus);

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

        lecture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example02_widgetActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });

        lecture3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example03_ExceptionActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });


        lecture4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example04_TouchEventActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });

        lecture5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example05_SwipeActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });

        lecture6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alert 창 (dialog) 으로 문자열 입력 받기
                // 입력받은 문자열 다음 activity 로 전달

                // 사용자가 문자열을 입력할 수 있는 widget
                final EditText editText = new EditText(MainActivity.this);
                // AlertDialog 생성
                AlertDialog.Builder builder
                        = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Activity data transmission");
                builder.setMessage("Put your message to send");
                builder.setView(editText);
                builder.setPositiveButton("Send",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Send 버튼을 눌렀을 때, 수행되는 이벤트 처리 작업
                                Intent i = new Intent();
                                ComponentName cname = new ComponentName(
                                        "com.example.androidlectureexample",
                                        "com.example.androidlectureexample.Example06_SendMsgActivity"
                                        );
                                i.setComponent(cname);
                                // 데이터를 전달하면서 Activity 를 실행
                                // 데이터 전달? intent 에 붙여 전달 = 중간자 역할
                                i.putExtra("sendMsg",editText.getText().toString());
                                // toString()이유? 안하면 char[] 느낌의 interface 형태로 보내므로

                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Cancel 버튼을 눌렀을 때, 수행되는 이벤트 처리 작업
                                // 특별한 처리를 안 함
                            }
                        }
                );
                builder.show();

            }
        });


        lecture7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example07_DataFromActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                // receive data from the new Activity
                // when the new Activity finishes, receive some data from it
                startActivityForResult(in, 3000);
                // requestCode
            }
        });


        lecture8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example08_ANRActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });

        lecture9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example09_CounterLogActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });


        Button _10_CounterLogProgressBtn = (Button)findViewById(R.id.btn10);
        _10_CounterLogProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example10_CounterLogProgressActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button _11_CounterLogHandlerBtn = (Button)findViewById(R.id.btn11);
        _11_CounterLogHandlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example11_CounterLogHandlerActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        lecture12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example12_SimpleBookSearchActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });

        Button _13_BookSearchDetailBtn = (Button)findViewById(R.id._13_BookSearchDetailBtn);
        _13_BookSearchDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidlectureexample",
                        "com.example.androidlectureexample.Example13_DetailBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        lecture14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적) Intent
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example14_ImplicitIntentActivity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
            }
        });

        lecture15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전화걸기 Activity 로 호출하려면 2가지 중 1가지를 이용
                // 1. 클래스명을 알면 호출할 수 있음 (Explicit intent)
                // 2. Implicit Intent 를 이용해서 알려져있는 Action 통해서 전화
                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                // 전화가 실제로 걸리는 건 아님
                // 카테고리, action 값만 알면
                // 내가 원하는 activity 를 찾을 수 있음
                i.setData(Uri.parse("tel:01065584898"));
                startActivity(i);
            }
        });

        lecture16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 특정 URL 을 이용 Browser 실행
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.naver.com"));
                startActivity(i);
            }
        });

        // Service !!
        // App 이 실행되었다고 해서 항상 Activity 가 보이는 건 아님
        // 가장 대표적으로 카톡, 멜론 ...
        // 카톡 전면에 없는데 메세지를 받으면 알림이 울림
        // 멜론 Activity 가 안 보이는데 음악은 계속 들림
        // 따라서 Service 는 화면이 없는 Activity 라고 생각하면 됨
        // Activity 는 onCreate() -> onStart() -> onResume() -> onPause() -> onStop()
        // Service 는 onCreate() -> onStartCommand() -> onDestroy()
        // 눈에 보이지 않기 때문에, Background 에서 Logic 처리 하는데 많이 씀
        lecture17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Service 를 실행해보기
                Intent in = new Intent();
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Example17_ServiceLifeCycleActivity"
                        );
                in.setComponent(cname);
                startActivity(in);
            }
        });


        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Explicit (명시적)
                Intent in = new Intent();
                // cname has the info about the class(=Activity) we would use
                ComponentName cname =
                        new ComponentName(
                                "com.example.androidlectureexample",
                                "com.example.androidlectureexample.Main2Activity"
                        );
                // intent hold component name we will exacute
                in.setComponent(cname);
                // start Activity using the info about the intent instance
                startActivity(in);
                // Implicit Intent 는 다양한 class 로 activity 를 열 수 있는 옵션 제공
                // 예로, 핸드폰에서 어떤 파일을 열 때,
                // pdf 로 열까 ppt 로 열까, 카카오로 보낼까
            }
        });

    } // end of onCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3000 && resultCode==7000){
            String msg = (String) data.getExtras().get("resultValue");
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    } // end of onActivityResult
}
