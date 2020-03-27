package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

                // 12. bundle 에서 key 값을 이용해서 데이터 추출
                Bundle bundle = msg.getData();
                String[] bookList = bundle.getStringArray("bookList");
                //Log.i("Book",bookList[0]);
                // 13. ListView 사용은 Adapter 가 필요 (Spinner 사용과 비슷)
                // ListView 에 데이터를 설정하기 위해 Adapter 를 사용
                // Adapter 에 데이터를 설정해서 ListView 에 붙이기
                // 배열을 가지고 만드는 어댑터 : ArrayAdapter
                ArrayAdapter adapter =
                        new ArrayAdapter(
                                getApplicationContext(), // context
                                android.R.layout.simple_list_item_1, // pattern
                                bookList // data
                        );

                searchList.setAdapter(adapter);
                searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in = new Intent();


                        ComponentName cname =
                                new ComponentName(
                                        "com.example.androidlectureexample",
                                        "com.example.androidlectureexample.Example12_01_BookDetailActivity"
                                );

                        in.setComponent(cname);
                        in.putExtra("keyword", searchList.getItemAtPosition(position).toString());
                        startActivity(in);

                    }
                });

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
        }); // End of btn Click

    }


}

// create Thread
// receive json data from web application
// and change the json data to String Array
// and finally send the Array data to Activity (handler)
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
        String url = "http://70.12.60.109:8080/bookSearch/searchTitle?keyword="+keyword;
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

            // 7. 보안 설정 (AndroidManifest.xml file)
            // You must set the security setting for using some function in Android App
            // From on Android 9(pie), the base protocol of web is changed to HTTPS from HTTP
            // So, if you use http protocol, Must set two option tag in AndroidManifest.xml

            // 8. create a stream
            // Open a data stream to a Server using Connection object (Java Stream)
            // Through the stream, we can read the data from a server
            // from basic stream(InputStreamReader) to more efficient stream(BufferedReader)
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
                    // 데이터를 읽어들일 수 있는 통로
            );
            // 9. Make a String from the data received
            String readLine = "";
            StringBuffer responseText = new StringBuffer();
            while((readLine=br.readLine())!=null){
                responseText.append(readLine); // JSON 형식의 데이터
            }

            br.close(); // resource close
            Log.i("Book","Content: "+responseText.toString());
            //["Head First Java: 뇌 회로를 자극하는 자바 학습법(개정판)", ... ]
            // How to make a data structure(ex.List, Array) using the String

            // 일반적으로 서버쪽 웹 프로그래밍은 XML 이나 JSON 형태의 데이터를 제공
            // JSON parsing library 활용하여 JSON handling
            // 가장 대표적인 JSON 처리 라이브러리 중 하나인 JACKSON library 를 이용
            // JSON library 설치 => gradle (gradle script) -> build.gradle(Module:app)
            // 10. 얻어온 json 문자열 데이터를 java의 string array 로 변환
            ObjectMapper mapper = new ObjectMapper();
            String[] resultArr = mapper.readValue(responseText.toString(),String[].class);

            // 11. 최종 결과 데이터 Activity 에 전달
            // 데이터를 Activity 에게 전달하기
            // 11-1. Bundle 에 전달 데이터 붙이기
            Bundle bundle = new Bundle();
            bundle.putStringArray("bookList",resultArr);
            // 11-2. message 만들어서 번들을 message 에 부착하기
            Message msg = new Message();
            // 11-3. handler 를 이용해서 message 를 Activity 에게 전달
            msg.setData(bundle);
            handler.sendMessage(msg);

        }catch(Exception e){
            Log.i("Book",e.toString());
        }

    }

} // End of Thread
