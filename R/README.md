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

(mysql5.6.47 버전, )

1. DB생성, 간단한 servlet을 이용하여 JSON 받아오기


2. Open API (영화진흥위원회 open api 사용)


도서검색 프로그램을 이용해서 JSON을 이용해보기

데이터베이스 생성 (Mysql 이용, standalone)

1. mysql 설치 폴더 압축 풀기
2. bin 폴더 안에 있는 mysqld명령어로 서버실행 (cmd로)
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

```
 
```

