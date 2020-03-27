package com.example.androidlectureexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Example19Sub_Service extends Service {
    public Example19Sub_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // extract keyword
        String keyword = intent.getExtras().getString("KEYWORD");
        // process the task that calls the Open API through Network connection
        // using Thread
        KakaoBookSearchRunnable runnable = new KakaoBookSearchRunnable(keyword);
        Thread t = new Thread(runnable);
        t.start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // create Inner class
    class KakaoBookSearchRunnable implements Runnable {
        private String keyword;
        KakaoBookSearchRunnable(){};
        KakaoBookSearchRunnable(String keyword) {
            this.keyword = keyword;
        }
        @Override
        public void run() {
            // network 접속을 통해 OPEN API 호출
            // kakao develpoer 사이트에서 open api 의 호출방식 알아내기
            String url = "https://dapi.kakao.com/v3/search/book?target=title";
            url += "&query="+keyword;

            // Network Connection
            // Must do Exception Handling
            try{
                // 1. For Http Access, create URL object
                URL obj  = new URL(url);
                // 2. Open the connection of URL
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                // 3. do some Setting for connection
                //    GET or POST method, Authentication process
                con.setRequestMethod("GET"); // GET METHOD
                con.setRequestProperty("Authorization","KakaoAK cffb65b1aa52796d94b04c246bbdd848");
                // 4. Read JSON data using Stream
                //    get the basic Stream
                //    and transform the stream to another way
                // 사용하기 편하고 효율이 좋은 BufferedReader 로 변경하는 과정
                BufferedReader br =new BufferedReader(
                        new InputStreamReader(
                                con.getInputStream()
                        )
                );
                String line;
                StringBuffer sb = new StringBuffer(); // 문자열 붙이려고
                // 반복적으로 서버가 보내 준 데이터 읽기
                while( (line = br.readLine()) != null){
                    //Log.i("KAKAO","line: "+line);
                    sb.append(line);
                }
                //Log.i("KAKAO",sb.toString());
                // 데이터가 JSON으로 정상적으로 왔음을 확인
                br.close(); // 통로 닫기

                // documents : [{book1},{book2},{book3}....]
                // documents 라는 key 값에 대한 value 를 가지고 오기
                // JACKSON library 를 이용하자!
                ObjectMapper mapper = new ObjectMapper();
                // JSON 구조를 읽어서 Map 구조로 저장
                Map<String, Object> map
                        = mapper.readValue(
                        sb.toString(),
                        new TypeReference<Map<String, Object>> () {
                        } // 어떤 데이터 타입을 만들지 지정
                );
                Object jsonObject = map.get("documents");
                // jsonObject : [{book1},{book2},{book3}....]
                String jsonString = mapper.writeValueAsString(jsonObject);
                //Log.i("KAKAO",jsonString);
                // ArrayList 안에 객체가 들어 있는 형태로 만들기
                // 그 객체는? VO 객체가 되겠지
                ArrayList<KakaoBookVO> searchBooks =
                        mapper.readValue(
                                jsonString,
                                new TypeReference<ArrayList<KakaoBookVO>>() {}
                        );
                ArrayList<String> resultData = new ArrayList<>();
                for(KakaoBookVO vo : searchBooks){
                    Log.i("KAKAO",vo.getTitle());
                    resultData.add(vo.getTitle());
                }

                // Send resultData to Activity
                Intent i = new Intent(
                        getApplicationContext(),
                        Example19_KakaoBookActivity.class
                        );
                //Log.i("KAKAO",getApplicationContext().toString());
                i.putExtra("BOOKRESULT",resultData);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }catch(Exception e){
                Log.i("KAKAO",e.toString());
            }

        }
    }
}

// create VO class
class KakaoBookVO {

    private ArrayList<String> authors;
    private String contents;
    private String datetime;
    private String isbn;
    private String price;
    private String publisher;
    private String sale_price;
    private String status;
    private String thumbnail;
    private String title;
    private ArrayList<String> translators;
    private String url;

    KakaoBookVO() {};

    public KakaoBookVO(ArrayList<String> authors, String contents, String datetime,
                       String isbn, String price, String publisher, String sale_price,
                       String status, String thumbnail, String title,
                       ArrayList<String> translators, String url) {
        this.authors = authors;
        this.contents = contents;
        this.datetime = datetime;
        this.isbn = isbn;
        this.price = price;
        this.publisher = publisher;
        this.sale_price = sale_price;
        this.status = status;
        this.thumbnail = thumbnail;
        this.title = title;
        this.translators = translators;
        this.url = url;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTranslators() {
        return translators;
    }

    public void setTranslators(ArrayList<String> translators) {
        this.translators = translators;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}