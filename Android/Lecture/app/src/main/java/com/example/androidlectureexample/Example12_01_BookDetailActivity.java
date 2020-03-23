package com.example.androidlectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Example12_01_BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example12_01_book_detail);

        final ImageView iv = (ImageView)findViewById(R.id.imageView);
        final TextView titleView = (TextView)findViewById(R.id.title);
        final TextView authorView = (TextView)findViewById(R.id.author);
        final TextView priceView = (TextView)findViewById(R.id.price);
        final TextView isbnView = (TextView)findViewById(R.id.isbn);

        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);

                Bundle bundle = msg.getData();
                String[] book = bundle.getStringArray("book");

                URL  url = null;
                try {
                    Log.i("BookDetail",book[0]);
                    url = new URL(book[0]);
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                    iv.setImageBitmap(bitmap);

                } catch (MalformedURLException e) {
                    Log.i("BookDetail",e.toString());
                } catch (IOException e) {
                    Log.i("BookDetail",e.toString());
                } catch (Exception e){
                    Log.i("BookDetail",e.toString());
                }


            }

        };

        Intent i = getIntent();
        String title = (String) i.getExtras().get("keyword");
        titleView.setText(title);

        // Button Event Processing
        // Thread 생성
        // with handler, text
        BookRunnable runnable = new BookRunnable(handler,title);
        Thread th = new Thread(runnable);
        th.start();

    } // End of OnCreate

}

class BookRunnable implements Runnable{
    private Handler handler;
    private String keyword;

    BookRunnable(){};
    BookRunnable(Handler handler, String keyword){
        this.handler = handler;
        this.keyword = keyword;
    }

    @Override
    public void run() {
        String url = "http://70.12.60.109:8080/bookSearch/search?keyword="+keyword;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            Log.i("BookDetail","response code :"+responseCode);
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
            Log.i("BookDetail","Content: "+responseText.toString());

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(responseText.
                    substring(1,responseText.length()).toString(), Map.class);
            //Log.i("BookDetail","Content3: "+ map.size());

            Bundle bundle = new Bundle();
            String[] abc = {map.get("img"),map.get("author"),map.get("price"),map.get("isbn")};
            bundle.putStringArray("book",abc);

            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

        }catch (Exception e){
            Log.i("BookDetail",e.toString());
        }

    }
}
