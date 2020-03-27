package com.example.androidlectureexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Example20Sub_Service extends Service {
    public Example20Sub_Service() {
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
        String script = intent.getExtras().getString("script");

        // Thread 로 카카오 API 통해 데이터 받아오기
        TranslateRunnable runnable = new TranslateRunnable(script,getApplicationContext());
        Thread thread = new Thread(runnable);
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

class TranslateRunnable implements Runnable {
    private String script;
    private Context context;

    TranslateRunnable() {};
    TranslateRunnable(String script){
        this.script = script;
    }
    TranslateRunnable(String script, Context context){
        this.script = script;
        this.context = context;
    }

    @Override
    public void run() {
        String url = "https://kapi.kakao.com/v1/translation/translate?src_lang=en&target_lang=kr&query="+this.script;
        try{
            URL request = new URL(url);
            HttpURLConnection con = (HttpURLConnection) request.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization","KakaoAK cffb65b1aa52796d94b04c246bbdd848");
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
            br.close(); // 통로 닫기

            // translated_text 의 값 빼오기
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map
                    = mapper.readValue(
                    sb.toString(),
                    new TypeReference<Map<String, Object>>() {
                    } // 어떤 데이터 타입을 만들지 지정
            );
            Object jsonObject = map.get("translated_text");
            String jsonString = mapper.writeValueAsString(jsonObject);
            jsonString = jsonString.substring(1,jsonString.length()-1);
            Log.i("Translate",jsonString);

            // 빼온 array String 형태의 값을
            // java 의 진짜 array 형태로 자료 빼오기
            ArrayList<String> translatedScript =
                    mapper.readValue(
                            jsonString,
                            new TypeReference<ArrayList<String>>() {}
                    );
           ArrayList<String> resultData = new ArrayList<>();
            for(String comment : translatedScript){
                Log.i("Translate",comment);
                resultData.add(comment);
            }
            Intent i = new Intent(
                    context,
                    Example20_KakaoTranslatorActivity.class
            );
            //Log.i("KAKAO",getApplicationContext().toString());
            i.putExtra("SCRIPTRESULT",resultData);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }
        catch (Exception e){

        }
    }
}