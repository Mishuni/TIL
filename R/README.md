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

## Script

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
* heterogeneous (다른 종류) : 데이터 타입들이 섞여 있는 구조 
  1. list (중첩 자료 구조) : 1차원 선형구조, 순서 존재, 실제로 저장되는 구조는 map 구조, map 과 배열을 짬뽕시킨 배열 (list 안에는 vector, matix, numeric 다 들어올 수 있음)
  2. data frame : 2차원 테이블 구조 

**경로**: Linux 기반이라 역슬래시(|)가 아니라 (/)기반으로 경로를 나눔

**RData 파일**: R 전용 데이터 파일(.rda),  다른 파일들에 비해 R에서 일고 쓰는 속도가 빠르고 용량이 작음, R에서 분석 작업시에 RData파일을 이용하고, R을 사용하지 않는 사람과 파일을 주고받을 시에 CSV파일을 사용

참고사이트 : [R documentation](https://www.rdocumentation.org/)