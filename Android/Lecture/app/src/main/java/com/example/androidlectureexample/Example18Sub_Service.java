package com.example.androidlectureexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

// When the Service Object create,
// Constructor -> onCreate() -> onStartCommand() ->
// When the Service Object has been already created
public class Example18Sub_Service extends Service {
    public Example18Sub_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Overriding Three Methods
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Logic process
        // The Intent from Activity is transmitted through parameter of the method
        String data = intent.getExtras().getString("DATA");
        // Using the data, Do the basic Logic process
        // If the process is long, The Activity would be blocked for that time
        // In the case, Usually use Thread But not now ^^
        String resultData = data + " is arrived.";
        // transmit the resultData to Activity,
        // and the Activity prints the data
        Intent resultIntent = new Intent(
                getApplicationContext(),
                Example18_ServiceDataTransferActivity.class
                );
        // 결과값을 intent 에 부착
        resultIntent.putExtra("RESULT",resultData);

        // Service 에서 Activity 호출
        // 화면이 없는 Service 에서 화면을 가지고 있는 Activity 호출
        // Task 라는게 필요!
        // Activity 를 새로 생성하는게 아니라
        // 메모리에 존재하는 이전 Activity 를 찾아서 실행 => 플래그 2개 추가
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(resultIntent);

        return super.onStartCommand(intent, flags, startId);
        // Activity thread 도 가능 하지만
        // 로직 처리를 주로 service 에 맡긴다.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
