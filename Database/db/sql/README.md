# SQL

## 0. Eclipse 와 Oracle DB연동

1. java 프로젝트폴더 > new > file > db_info.properties (환경 설정 파일)

>  configuration file 이 옛날에는 .properties 파일 많이 쓰다가 .XML  로 넘어갔는데 복잡해서
>
> .JSON 이나 .properties 가 늘어나고 있는 추세

2. java 랑 특정 database 랑 connection 을 하는 환경 설정

   ```
   id = SCOTT
   pw = TIGER
   driver =
   url= jdbc:oracle:thin:@127.0.0.1:1521:xe
   ```

3. Oracle 용 jdbc driver

   C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib 에 ojdbc6.jar 복사

   C:\lib 에 붙여넣기

4. eclipse Data Source Explorer 창

   Database Connections > new > Oracle > driver name 에 SCOTT > next > New Driver Definition> Oracle Thin Driver, 버전 11 > JAR List > Add jar > 원래 거 지우고, 3.번에서 찾은 driver 추가 > ok

   SCOTT/TIGER 계정을 물린 connection 을 만들겠다. -> SCOTT/TIGER 계정이 생성 된 상태이고, connect 에 대한 권한이 그 계정에 부여된 상태에서 진행

5. service name : xe, Host : 127.0.0.1(SERVER IP 주소, 현재는 내 컴퓨터로 하니까 localhost), user name, password 설정 하고 save password 체크

   여기서는 (SCOTT/TIGER)

6. Test Connection 클릭 > 성공 메세지 뜨면 ok> connectin url 복사 

7. 복사한 url 을 db_infr.properties 파일에 url = 다음에 붙여넣기

8. 이제 sql 파일에서 connection profile 을 앞서 설정한 것에 맞춰 선택하면 완료 !

### 다른 방법

C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib

에서, ojdbc6.jar 파일(압축된 파일)복사

C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext 에다가 붙여넣기

Class.forName("oracle.jdbc.driver.OracleDriver"); 를

eclipse 에서 실행해 보는데 에러가 나타나면

그 붙여넣은 파일 지우고, 

C:\Program Files\Java\jre1.8.0_231\lib\ext 에다가 다시 붙여넣기

ext ? 장치 관련된 라이브러리가 저장된 곳

## 1. SQL 명령어

DDL : ROLLBACK 불가(되돌리기 불가)

DML : ROLLBACK 가능

### SQL 기본 구문

```mysql
SELECT column1, column2, ...
FROM table_name
[WHERE condition]
[GROUP BY]
 [HAVING]
[ORDER BY]
```