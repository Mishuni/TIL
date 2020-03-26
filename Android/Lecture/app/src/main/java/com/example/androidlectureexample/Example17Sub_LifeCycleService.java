package com.example.androidlectureexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Example17Sub_LifeCycleService extends Service {

    private Thread myThread;
    /*= new Thread(new Runnable() {
        @Override
        public void run() {
            // thread 가 시작되면 1초동안 sleep 후에
            // Log 이용해서 숫자 출력
            for(int i=1; i<=10; ++i){
                try {
                    Log.i("ServiceExam",Integer.toString(i));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });*/

    public Example17Sub_LifeCycleService() {

    }
    // 현재는 사용하지 않음
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Service 객체가 생성될 때, 호출
        Log.i("ServiceExam","onCreate() 호출");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 실제 서비스 동작을 수행하는 부분
        // onCreate 다음에 바로 이어서 호출
        // 1초간격으로 1부터 시작해서 10까지 숫자를 Log 로 출력 with Thread
        Log.i("ServiceExam","onStartCommand() 호출");

        /*myThread.start();*/
        // Thread 의 run() 이 호출
        // 언젠가는 run 이라는 method 의 실행이 끝
        // 후에 thread 의 상태가 dead 상태가 됨
        // 그래서 처음 start 를 하고 나서 또 start 는 할 수가 없음
        // 이를 해결하기 위한 방법은 없음!!!!!
        // IllegalStateException
        // 유일하게 다시 실행시키는 방법은 Thread 를 다시 생성해서 실행하는 것
        myThread= new Thread(new Runnable() {
            @Override
            public void run() {
                // thread 가 시작되면 1초동안 sleep 후에
                // Log 이용해서 숫자 출력
                for(int i=1; i<=50; ++i){
                    try {
                        Log.i("ServiceExam",Integer.toString(i));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                        // interrupt 가 걸리면 에러 나니까
                        // 그 때 break 로 벗어나기!
                    }
                }
            }
        });
        myThread.start();

       /* for(int i=1; i<=10; ++i){
            try {
                Log.i("ServiceExam",Integer.toString(i));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // 현재 클래스가 담긴 StopService 가 호출되면 onDestroy()가 호출
        Log.i("ServiceExam","onDestroy() 호출");
        // 현재 Service 에 의해서 동작하고 있는 모든 Thread 종료
        if(myThread!=null && myThread.isAlive()){
            // Thread 를 중지시키기
            // myThread.stop(); -> 예전 방법, deprecated 됨
            myThread.interrupt();
            // thread 한테 상황되면 멈추라고 표시
            // 어떤 상황? sleep 을 하려고 할 때 !
            // 만약 그 때, interrupt 가 걸려있으면 Exception 발생
            // 그래서 try-catch 가 Exception 을 인식!
        }
        super.onDestroy();
    }
}
