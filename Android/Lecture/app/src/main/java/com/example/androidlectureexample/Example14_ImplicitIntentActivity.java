package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Example14_ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example14_implicit_intent);

        Button implicitIntentBtn = (Button) findViewById(R.id.implicitIntentBtn);
        implicitIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Implicit","Button Click");
                // 버튼을 클릭했을 때, 새로운 Activity 를 실행 (묵시적 Intent 이용)
                // Implicit Intent (묵시적 인텐트)
                Intent i = new Intent();
                i.setAction("MY_ACTION");
                i.addCategory("INTENT_TEST");
                i.putExtra("SEND DATA","HELLO");
                startActivity(i);
            }
        });

        // 전화걸기 기능을 사용하려면, AndroidManifest.xml 파일에 권한 설정 필요
        // 안드로이드 6.0 (마쉬멜로우) 이상에서는 위 설정 외에도
        // 사용자에게 권한을 따로 요청해야한다.
        // 즉, 권한자체가 크게 2가지로 분류되어서 관리
        // 1. 일반 권한(normal permission)
        // 2. 위험 권한(dangerous permission)
        // : 개인정보에 해당 되는 것
        //   (위치,전화걸기,카메라,마이크,문자,일정,주소록,내장 센서 등)
        // 특정 앱을 사용할 때, 앱이 사용자에게 권한을 요구하고
        // 사용자가 권한을 허용하면 그 기능을 이용할 수 있음
        // 설정 > app > 앱 목록에서 권한 설정 수정 가능
        Button callBtn = (Button) findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 사용하는 안드로이드 버전이 6.0(마쉬멜로우=M) 이상인지 체크
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    // 사용자의 Android version 이 6이상인 경우
                    // 사용자 권한 중 전화걸기 기능이 설정되어 있는지 확인
                    int permissionResult =
                            ActivityCompat.checkSelfPermission(
                                    getApplicationContext(),
                                    Manifest.permission.CALL_PHONE
                                    );
                    // 권한에 따른 동작 수행

                    if(permissionResult == PackageManager.PERMISSION_DENIED){
                        // 권한이 허용되지 않은 경우
                        if(shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                            //  전에 권한을 거부한 적이 있는 경우
                            AlertDialog.Builder dialog =
                                    new AlertDialog.Builder(Example14_ImplicitIntentActivity.this);
                            Log.i("Implicit",getApplicationContext().toString());
                            dialog.setTitle("권한요청에 관한 Dialog");
                            dialog.setMessage("전화걸기 기능이 필요합니다. 수락할까요?");
                            dialog.setPositiveButton(
                                    "yes",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(
                                                    new String[]{Manifest.permission.CALL_PHONE},
                                                    1000);
                                        }
                                    });
                            dialog.setNegativeButton(
                                    "no",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // pass
                                        }
                                    }
                            );
                            dialog.show();

                        }
                        else{
                            // 권한을 거부한 적이 없는 경우 (처음 들어온 경우)
                            requestPermissions(
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    1000);
                            // requestCode ? 저 request permission 에 대한 용도구나
                        }
                    }
                    else{
                        // 이미 권한을 허용한 경우
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_CALL); // 전화걸기
                        i.setData(Uri.parse("tel:01065584898"));
                        startActivity(i);
                    }
                }
                else{
                    // 사용자의 Android version 이 6미만인 경우
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_CALL); // 전화걸기
                    i.setData(Uri.parse("tel:01065584898"));
                    startActivity(i);
                }
            }
        });

    } // end of onCreate

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // grandResult : per 에 들어간 여러가지 권한에 대한 결과를 담는 배열
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000){
            // 위에서 수행한 권한 요청에 대한 응답인지를 확인
            // 권한 요청 응답 갯수가 1개 이상이고
            // 지금 상황에서 전화걸기 기능 1개만 물어봤으니까, 첫번째 배열에
            // 그 결과가 담겨 도착
            if(grantResults.length>0 &&
                    grantResults[0]==PackageManager.PERMISSION_GRANTED ){
                // 허용을 눌렀으면
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL); // 전화걸기
                i.setData(Uri.parse("tel:01065584898"));
                startActivity(i);
            }
        }
    }

}
