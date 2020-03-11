# R

## 환경 설정(Windows)

### 1) R Tool

https://cran.r-project.org/ > [Download R for Windows](https://cran.r-project.org/bin/windows/) > base 클릭 > 설치 후 실행 후 defalut 형태로 설치 **컴퓨터 로그인 id 가 한글로 되어 있으면 문제가 발생 할 수 있음**

### 2) 개발 IDE

[R Studio](https://rstudio.com/) > product > R Studio > [R Studio Desktop](https://rstudio.com/products/rstudio/download/) > RStudio Desktop free 버전 다운로드 > default 형태로 설치

### 3) Project

File > new Project > New Project > 프로젝트 이름, 워크스페이스 위치 설정

### 4) Encoding

Tools > global Options (모든 프로젝트에 해당) > Code > Soft-wrap R source files (줄바꿈 처리) > Saving > encoding쪽에 change > UTF-8 로 바꿔서 저장

## Basic 01

**변수** : Weak Type 형식, Type 을 따로 선언해줄 필요 없음 ( Java 는 Strong Type 형식), 변수 이름은 보통 CamelCase 형태로 지음 ( ex firstVal)

**데이터 타입**: 총 4가지

* numeric (수치형) : 정수/실수 (기본은 실수인데, 숫자 뒤에 L을 붙이면 정수형 ex 10L)
* character (문자형) : 모든 글자는 모두 character('a','bb',"a","bb") 
* logical (논리형) : TRUE(=T), FALSE(=F)
* complex (복소수형) : 4-3i

**특수 데이터 타입 (Data Object)**:

* NULL : 빈 객체, 가리키는 객체가 없음을 뜻함

* NA (Not Available) : 유효하지 않은 값, 일반적으로 Missing Value (결측치)를 표현할 때 사용

 > 결측치? sensor 가 데이터를 측정할 때, 중간중간 값이 없거나 쓰레기 값 같은 것을 가져올 수 있음 (값을 가져와야 하는데 못 가져온 경우) 
 >
 > -> 이 경우, 나중에 결측치를 지우던가, 다른 방식으로 데이터를 채우던가 하는 방향으로 해결

* NAN (Not A Number) : 숫자 인데 숫자가 아닌 것? -> 수치 값이지만 숫자로 표현이 안되는 것

  ex) sqrt(-9) #NAN

* Inf (Infinite) : 양의 무한대

  ex) 3 / 0 #Inf

**데이터 타입의 우선 순위**: 기본적으로 사용하는 자료구조는 vector 이므로 우선 순위가 있음 

​	***character > complex > numeric > logical***

**자료구조**: 

* homogeneous (같은 종류) : 같은 종류의 데이터 타입들만 있는 구조  
  1. vector : 1차원 선형구조, 순서 존재
  
  2. matrix : 2차원 구조, 순서 존재
  
  3. array : 3차원 이상의 같은 데이터 타입으로만 이루어진 구조
  
  4. factor : 범주형 데이터 구조, 값과 level(범위)를 둘 다 정의를 해야함
  
     level에 순서 개념이 있으면 : 순서형 (대, 중, 소)
  
     level에 순서 개념이 없으면 : 명목형 (비, 맑음, 흐림)
* heterogeneous (다른 종류) : 데이터 타입들이 섞여 있는 구조 
  1. list (중첩 자료 구조) : 1차원 선형구조, 순서 존재, 실제로 저장되는 구조는 map 구조, map 과 배열을 짬뽕시킨 배열 (list 안에는 vector, matix, numeric 다 들어올 수 있음)
  2. data frame : 2차원 테이블 구조 

**경로**: Linux 기반이라 역슬래시(|)가 아니라 (/)기반으로 경로를 나눔

**RData 파일**: R 전용 데이터 파일(.rda),  다른 파일들에 비해 R에서 일고 쓰는 속도가 빠르고 용량이 작음, R에서 분석 작업시에 RData파일을 이용하고, R을 사용하지 않는 사람과 파일을 주고받을 시에 CSV파일을 사용

참고사이트 : [R documentation](https://www.rdocumentation.org/)



## Basic 02

**EDA (탐색적 데이터 분석)** : 주어진 데이터 안에서 알고자 하는 데이터를 추출, 데이터에 숨겨진 특정한 사실을 유추하는 작업 

**파일 입출력** : 자주 나는 인코딩 에러 해결법

``` R
# 파일에 있는 데이터 읽어 오기
df <- read.table("./data/t.txt",sep=",",
                header=TRUE) 
# 경고 메세지 : txt 파일에서 맨 밑 공백 주면 없어짐
df
df <- read.table("./data/t.txt",sep=",",
                 header=TRUE) 
# 멀티바이트 에러 -> 한글 읽어들일 때 발생
# R은 기본적으로 데이터를 읽어올 때, system encoding
# cp949 인코딩으로 읽음 (파일은 UTF-8)
df <- read.table("./data/t.txt",
                 sep=",",
                 header=TRUE,
                 fileEncoding = "UTF-8")
df
```

**데이터 교환, 전달 시에 사용하는 데이터 표준형식**

1. CSV(comma seperated value) 방식
    csv 파일을 이용해서 사용 -> 데이터를 컴마로 구분
    예) 홍길동, 20, 서울
 장점 - 용량이 작다, 대용량 전달에 유리
        부가적 데이터가 별로 없음(real data + ',')
 단점 - 데이터의 구조화가 힘듦
        해당 데이터의 계층 구조 표현 어려움
        받는 쪽에서 이를 위한 parser를 만들어야함
        따라서, 유지보수 항상 필요(데이터 열 추가하면)

 2. XML (extened mark language) 방식
    예) <name>hong</name><age>20</age>
 장점 - csv의 단점을 어느정도 보완
 단점 - redandent data(부가적 데이터)가 너무 큼
        따라서 파일의 크기 증가
        과거는 괜찮지만, packet이 돈인 모바일 환경 
        -> 전달 횟수 줄여야함

 3. JSON(JavaScript Object Notation) 방식
    예) {name:"홍길동",age:20,address:"서울"}
 장점 - xml에 비해 부가적 데이터가 적어서
        요새 많이 쓰임, xml 은 과거에 많이 쓰임

## R에서 JSON 처리 실습

----

(개발 환경 : mysql5.6.47 버전, Tomcat 7 버전, )

1. DB생성, 간단한 servlet을 이용하여 JSON 받아오기


2. Open API (영화진흥위원회 open api 사용)


도서검색 프로그램을 이용해서 JSON을 이용해보기

데이터베이스 생성 (Mysql 이용, standalone)

1. mysql 설치 폴더 압축 풀기

2. bin 폴더 안에 있는 mysqld명령어로 서버실행 (cmd로)

      (만약 서버 종료하려면, mysqladmin -u root shutdown 입력)

3. 새로운 cmd 창에서 bin폴더 가고, mysql -u root 로 실행
      root 권한으로 mysql 진입한다는 의미

4. 새로운 사용자 생성

```sql
> create user rdata identified by "rdata";

# 로컬호스트 계정 만들기
> create user rdata@localhost identified by "rdata";
```

5. Table이 들어갈 저장 공간(database) 만들고, 권한 부여

```sql
# library라는 이름을 갖는 데이터베이스
> create database library;
# rdata유저한테, library안에 있는 모든 object에 모든 권한 부여
> grant all privileges on library.* to rdata;
> grant all privileges on library.* to rdata@localhost;
> flush privileges;
> exit;
```

6. 제공된 script를 이용해 db 구축

   mysql의 bin 폴더에 script파일 복사 붙여넣기 하고 밑 명령어 실행으로 테이블 생성

   mysql -u rdata -p library < _BookTableDump.spl
   
7. Ecilpse 실행, preferences > General > Workspace > encoding을 utf-8로 바꾸기

   preferences > web > 다 utf-8 로

8. Tomcat 7 설치하고 ecilpse 연동

9. project import 하기, import > Genaral > Existing Projects into Workspace > select archive file > 압축 파일 (bookSearchProject.zip) 선택

10. project 와 eclipse 환경 맞추기

    Project > properties > java build path > JRE path 가 틀려서 (unbounded) error 발생 > library > jre library 더블 클릭 > Alternate JRE 선택

11. META-INF> context.xml > username = "rdata", password = "rdata"

12. web program 을 호출해보기

    (context root 확인 : properties > web project setting 에 있음)

    http://localhost:8080/bookSearch/search?keyword=java

    위 url로 요청을 하면, Json 형태로 데이터가 전달될 것

    url query에 한글이 있으면 encoding문제로 검색이 안될 것

    -> WAS로 인한 문제

    -> 해결법 : eclipse Server 프로젝트 > server.xml > 65번째 라인을 밑에 코드로 수정

    ```xml
 <Connector URIEncoding="UTF-8" connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
    ```

    round-trip 방식 : jsp (was 가 동작 후, 페이지 동쨰로 보내주는 것) -> 프로그램이 쉽다는 장점 : 모든 작업이 server 단에서 일어남 , client 사이드가 복잡하지 않음,  개발이 오히려 쉬움(빨리 만들 수 있음), 서버와 클라이언트가 주고 받는 용량이 많은 수 밖에없음,(html,이미지.. 다 주고 받으니까) -> 이게 옛날에는 문제가 되지 않았지만, 현재는 매우 중요(데이터 크기가 곧 비용 및 속도) 
    
    다른 방식 : jsp 안쓰고,  client 따로 만들기 (Front-end web application, server 는 순수 로직만) -> front-end(html,css,javascript) : server -> 사이에는 데이터만 왔다 갔다 하는 거 ! ( csv, xml, javascript ) -> 이런 방식은 SPA(single page application)
    
    javascript : jquery (client side) > library와 framework 차이? 프로그래밍 규칙이 없나 있나, library 를 이용한 구현은 유지보수 문제가 존재(절차가 명시되어 있지 않으니까) -> 그래서 jquery 로 client side 를 다 해버리면 문제가 있음 ,  유지보수 비용 발생> 
    
    이래서 나온 프레임웍(frontend size) : angular.js, react.js
    
    spring 과는 완전 별개, 둘은 data 만 주고 받는 관계다. 문제는? 서버와 클라이언트 둘 다 고용해야함
    
    웹 프로그램을 어떤 방식으로 사용하느냐에 따라 방식을 선택할 수 있음 (어떤게 더 좋고 나쁜게 아님)
    
    ```
    단, jquery는 library 형태로 코드가 구현되기 때문에,
    framework 기반(패턴 기반)의 개발을 할 수 없음
    따라서, 유지보수에 문제가 발생
    ex) 구글의 angular, 혹은 react 등과 같은 framework 를 이용해서 프론트엔드 개발
    백엔드를 어느 정도 정리하고 (웬만한 웹 시스템을 개발할수있어)
    정도가 되고 나서 프론트엔드를 공부하면 더욱 좋을 것임
    ```
    
13. JSON : 데이터 전달 표준

    => key와 value 가 쌍으로 표현

```json
[
    {
        "name":"김길동"
    },
    {
        "name":"홍길동"
    }
] 
```

http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=b32fad64739ee39bbbf10380f1489444&targetDt=20200310

myKey = b32fad64739ee39bbbf10380f1489444

**웹 스크랩핑**  : 원하는 부분 데이터를 가지고 오는거

vs **크롤링** : 스크랩핑을 여러 페이지를 반복적으로 browsing 하는 행위 (데이터 수집을 하는 게 아니라)



웹서버가 크롤러가 너무 심하면 막음 요즘 (서버 부하를 막기 위해) -> 크롤랑 데이터 수집? 매일 업데이트 되는 데이터를 일정량 가져와서 축적 시키는 경우 이용될 것

jquery selector , xpass 를 알아야 web scraping 이 가능 

웹사이트 상에서 내가 원하는 위치의 대한 정보를
자동으로 추출해서 수집하는 기능 => Web Scraping
자동화 봇인 web crawler가 정해진 규칙에 따라서
복수개의 웹페이지를 browsing하는행위 => Web Crawling

web scraping => 1) css,jquery(selector)를 이용하는 방식
                			  2) XPATH 를 이용하는 방식

Eclipse 는 Backend program을 작성에 최적화!

Front-End는 webStorm 을 이용해서 알아보기 

[WebStorm다운로드](https://www.jetbrains.com/ko-kr/webstorm/) : 30일 무료판 (재다운 받으면 다시 백업)

![install_webstorm](..\image\install_webstorm.PNG)