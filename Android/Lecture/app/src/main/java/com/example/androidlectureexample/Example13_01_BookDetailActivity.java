package com.example.androidlectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class Example13_01_BookDetailActivity extends AppCompatActivity {
    private BookVO bookVO;
    private Bitmap bm;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example13_01_book_detail);

        final ImageView iv = (ImageView)findViewById(R.id.imageView);
        final TextView titleView = (TextView)findViewById(R.id.title);
        final TextView authorView = (TextView)findViewById(R.id.author);
        final TextView priceView = (TextView)findViewById(R.id.price);
        final TextView isbnView = (TextView)findViewById(R.id.isbn);

        Intent i = getIntent();
        //String title = (String) i.getExtras().get("keyword");
        final BookVO bookVO = (BookVO) i.getSerializableExtra("bookVo");
        Log.i("BOOK",bookVO.getTitle());
        Log.i("BOOK",bookVO.getImg());
        titleView.setText(bookVO.getTitle());
        authorView.setText(bookVO.getAuthor());
        priceView.setText(bookVO.getPrice()+"원");
        isbnView.setText("No."+bookVO.getIsbn());


                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {    // 오래 거릴 작업을 구현한다
                        // TODO Auto-generated method stub
                        try{
                            URL url = new URL(bookVO.getImg());
                            InputStream is = url.openStream();
                            bm = BitmapFactory.decodeStream(is);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {  // 화면에 그려줄 작업
                                    iv.setImageBitmap(bm);
                                }
                            });
                            iv.setImageBitmap(bm); //비트맵 객체로 보여주기
                        } catch(Exception e){
                            Log.i("BOOK",e.toString());
                        }

                    }
                });

                t.start();


    }
}
