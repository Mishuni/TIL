## 12. simple book search program

with [Web application for book](..\..\R\Data_Workspace)

http://70.12.60.109:8080/bookSearch/searchTitle?keyword=java

> 보안 설정을 따로 하지 않으면 밑에 에러가 뜸
>
> java.io.IOException: Cleartext HTTP traffic to localhost not permitted
>
> url 은 localhost 로 하면 안된다 
>
> - 모바일 기기는 현재 server 와 독립되는 다른 기기 이므로  localhost 가 달라짐

* AndroidManifest.xml 에 밑에 태그 추가

```xml
    <!--인터넷 접속 보안 허용-->
    <uses-permission android:name="android.permission.INTERNET" />
    <!--버전 9 부턴, HTTPS 를 기본 프로토콜로 사용하기 때문에 아래 태그도 필요-->
    
    <application
        android:usesCleartextTraffic="true"
```

