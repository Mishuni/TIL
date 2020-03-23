package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.net.HttpURLConnection;
import java.net.URL;

public class Example12_SimpleBookSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example12_simple_book_search);

        // get the reference for a Search Button
        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        // get the reference for the title for search
        final EditText searchTitle = (EditText) findViewById(R.id.searchTitle);
        // get the reference of ListView for printing out the result
        final ListView searchList = (ListView) findViewById(R.id.searchList);

        // 외부 프로그램과 연결하는 network 는 activity 에서 처리하면 ANR 걸림
        // thread 로 빼서 해결 (DB 처리, Network 처리 등등)
        // Network connection ( Web Application call )
        // => UI Thread can not handle this for a moment (Long time task)
        // => Thread must handle it instance of Activity.
        // => For data transmission, We use Handler.

        // Creating Handler Object for network
        // it receives message and processes it
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            // final 은 지역함수로 남지 않음
            // Thread 에 의해 추후에 sendMessage 가 호출되는데
            // sendMessage 에 의해서 전달된 데이터를 처리하기 위해서
            // handleMessage 를 overriding 하면서 instance 를 생성
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                // process data
                // Thread 가 보내준 데이터로 ListView 를 채우는 코드
                // data 를 주고 받을 수 있는 도구



            }

        };  // End of Handler

        // Button Event Processing
        searchBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                // Thread 생성
                // with handler, text
                BookSearchRunnable runnable
                        = new BookSearchRunnable(handler, searchTitle.getText().toString());
                Thread th = new Thread(runnable);
                th.start();


            }
        });


    }
}

// create Thread
class BookSearchRunnable implements Runnable {

    private Handler handler;
    private String keyword;

    // 모든 class 는 기본 생성자를 일반적으로 가지고 있다.
    // class 의 확장성 때문이다.
    // 다른 생성자가 있는데, 기본 생성자를 작성하지 않으면
    // 나중에 문제가 생길 수 있음

    BookSearchRunnable(){ }

    BookSearchRunnable(Handler handler, String keyword) {

        this.handler = handler;
        this.keyword = keyword;

    }

    @Override
    public void run() {

        // Web Application call
        // 결과를 받아와서 예쁘게 만든 후 handler 를 통해서
        // activity 에게 전달

        // 1. url
        String url = "http://localhost:8080/bookSearch/searchTitle?keyword="+keyword;
        // 2. Must write Exception process if use Java Network
        try{

            // 3. create URL Object ( JAVA URL class )
            URL obj = new URL(url);
            // 4. try to connect using URL instance
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            // 5. set the way of call of web application (GET OR POST)
            con.setRequestMethod("GET"); // use GET
            // 6. receive the responseCode
            // that is the value of status of connection via HTTP protocol
            // 200 : 접속 성공 , 404 : Not Found , 500 : internal server error
            // 403 : forbidden (접속 차단)
            int responseCode = con.getResponseCode();
            Log.i("Book","response code :"+responseCode);
            // java.io.IOException: Cleartext HTTP traffic to localhost not permitted
            // 앱이 외부에 연결될 때, 보안적 이유로 연결이 되지 않는다.
            // 7. 보안 설정
            // You must set the security setting for using some function in Android App



        }catch(Exception e){
            Log.i("Book",e.toString());
        }

    }

}
