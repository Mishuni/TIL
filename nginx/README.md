# nginx

> 경량 HTTP 웹 서버 : 기능이 많으면서도 매우 효율적이고 가벼움

* 비동기 소켓을 사용하기 때문에 받은 요청 수만큼 자식 프로세스를 생성하지 않아 코어당 한 프로세스만으로도 수천 개의 연결을 처리하는 데 충분하므로 cpu 부하와 메모리 사용량이 적다.
* 



```nginx
http{
    server{
        listen 80;
        server_name example.com;
        # 이 서버로 들어오는 모든 http 요청을 밑 파일에 기록
        access_log /var/log/nginx/example.com.log;
        location ^~/admin/{
            index index.php;
        }
    }
}
```

