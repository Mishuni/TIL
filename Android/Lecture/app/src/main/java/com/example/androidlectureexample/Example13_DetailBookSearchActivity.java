package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class Example13_DetailBookSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example13_detail_book_search);

        // 사용하는 widget의 reference를 획득
        // 프로그램으로 widget을 제어하기 위해서 reference가 필요!!
        Button detailSearchBtn = (Button)findViewById(R.id.detailSearchBtn);
        final EditText detailSearchTitle =
                (EditText)findViewById(R.id.detailSearchTitle);
        final ListView detailSearchList =
                (ListView)findViewById(R.id.detailSearchList);

        // Web application과 연동해야 하는 Android App
        // Network 기능을 이용해서 Web Application에서 데이터를 받아와야 해요!
        // Network 연결은 Activity에서 작업하면 안되고 별도의 Thread를 만들어서
        // 처리해야 해요!
        // Activity와 Thread가 데이터를 주고받기위해서 Handler가 필요!!
        // Thread가 handler를 통해서 Activity에게 데이터를 전달하게되요!!(sendMessage)
        // Activity가 데이터를 받으면 Handler안에 handlerMessage가 호출되요!
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();

                final BookVO[] bookList =
                        (BookVO[])bundle.getSerializable("BOOKLIST");

                // ArrayAdapter를 만들어서 ListView에 책 제목만 출력해보아요!
                // 책 제목에 대한 String[]이 필요해요..
                // BookVO[]로부터 String[]를 뽑아내야 해요!
                // 책의 개수만큼 String 배열을 만들어요!
                String[] titles = new String[bookList.length];
                int i = 0;
                // ArrayList안의 VO들을 반복하면서
                // 제목만 뽑아서 String[]에 저장
                for(BookVO vo : bookList) {
                    titles[i++] = vo.getTitle();
                }
                ArrayAdapter adapter =
                        new ArrayAdapter(getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                titles);
                detailSearchList.setAdapter(adapter);


                detailSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in = new Intent(getApplicationContext(),
                                Example13_01_BookDetailActivity.class);
                        BookVO vo = bookList[position];
                        in.putExtra("bookVo",vo);

                        //in.putExtra("keyword", detailSearchList.getItemAtPosition(position).toString());
                        startActivity(in);

                    }
                });

            }
        };

        // Button을 클릭하면 Thread를 생성해서 일을 시켜요!!(Event처리)
        detailSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thread를 생성
                MyBookInfoRunnable runnable =
                        new MyBookInfoRunnable(handler,
                                detailSearchTitle.getText().toString());
                Thread t = new Thread(runnable);
                t.start(); // Thread가 시작!!
            }
        });




    }
}

class MyBookInfoRunnable implements Runnable {

    private Handler handler;
    private String keyword;

    // Constructor Injection
    MyBookInfoRunnable(Handler handler,String keyword) {
        this.handler = handler;
        this.keyword = keyword;
    }
    @Override
    public void run() {
        // Thread가 시작되면 수행하는 작업을 여기에 작성!!
        // Web application을 호출해서 JSON결과를 가져와서 Activity에게 전달
        String url = "http://70.12.60.109:8080/bookSearch/bookinfo?keyword="+keyword;

        try {
            // 3. URL 객체를 생성해야 해요!! ( Java의 URL class를 이용해서 )
            URL obj = new URL(url);
            // 4. URL 객체를 이용해서 접속을 시도해요!!
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            // 5. Web Application의 호출방식을 설정해요!! (GET, POST)
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            Log.i("BookSearch","서버로부터 전달된 code : " + responseCode);

            // 연결성공 후 데이터를 받아오기 위한 통로 생성.
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            // 서버가 보내주는 데이터를 읽어서 하나의 문자열로 만들어요!
            String readLine = "";
            StringBuffer responseTxt = new StringBuffer();
            while((readLine = br.readLine()) != null) {
                responseTxt.append(readLine);
            }

            br.close();   // 통로사용이 끝났으니 해당 resource자원을 해제.

            // 서버로부터 얻어온 문자열을 Log로 출력해보아요!
            Log.i("BookSearch","얻어온 내용은 : " + responseTxt.toString());
            // ["Head First Java: ...","뇌를 자극하는..."]
            // 가져온 데이터(문자열)를 자료구조화 시켜서 안드로이드 앱에 적용하는
            // 방법을 생각해 보아요!!


            // 일반적으로 서버쪽 웹 프로그램은 XML이나 혹은 JSON으로 결과 데이터를
            // 제공해줘요!!
            // 우리가 받은 데이터도 JSON형식이예요!!
            // 받은 JSON을 Java의 자료구조로 변경해야 해요!!
            // JSON parsing library를 가져다가 좀 편하고 쉽게 JSON을 handling
            // 가장 대표적인 JSON처리 library중 하나인 JACKSON library를 이용

            // JSON library가 있어야 사용할 수 있으니 라이브러리를 설치해야 해요!

            // JACKSON library 사용
            ObjectMapper mapper = new ObjectMapper();
            BookVO[] resultArr =
                    mapper.readValue(responseTxt.toString(),BookVO[].class);

            //String[] resultArr = mapper.readValue(responseTxt.toString(),String[].class);

            // 얻어온 JSON문자열 데이터를 Java의 String array로 변환했어요!

            // 최종 결과 데이터를 Activity에게 전달해야 해요!!(UI Thread에게 전달해서
            // 화면제어를 해야 해요!!)

            // 데이터를 Activity에게 전달하기 위해서
            // 1. Bundle에 전달할 데이터를 붙여요!!
            Bundle bundle = new Bundle();
            bundle.putSerializable("BOOKLIST",resultArr);
            //bundle.putStringArray("BOOKLIST",resultArr);
            // 2. Message를 만들어서 Bundle을 Message에 부착
            Message msg = new Message();
            msg.setData(bundle);
            // 3. Handler를 이용해서 Message를 Activity에게 전달.
            handler.sendMessage(msg);

        } catch(Exception e) {
            Log.i("BookSearch",e.toString());
        }

    }
}
@SuppressWarnings("serial")
class BookVO implements Serializable {

    private String img;
    private String title;
    private String author;
    private int price;
    private String isbn;
    private String date;
    private int page;
    private String translator;
    private String supplement;
    private String publisher;
    private String imgbase64;

    public BookVO() {
    }

    public BookVO(String img, String title, String author, int price, String isbn) {
        super();
        this.img = img;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }


    public BookVO(String img, String title, String author, int price, String isbn,
                  String date, int page, String translator, String supplement, String publisher,
                  String imagbase) {
        super();
        this.img = img;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.date = date;
        this.page = page;
        this.translator = translator;
        this.supplement = supplement;
        this.publisher = publisher;
        this.imgbase64 = imagbase;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImgbase64() {
        return imgbase64;
    }

    public void setImgbase64(String imgbase64) {
        this.imgbase64 = imgbase64;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


}
