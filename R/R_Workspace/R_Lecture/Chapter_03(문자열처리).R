# 문자열 처리는 꽤나 비번하게 발생하는 작업
# 기본적인 R의 Base 시스템으로 문자열을 처리하는 건
# 그다지 좋은 방식은 아니다!!!!!!!!!
# 문자열 처리에 대한 대표적인 pakage가 있다.
# 그것은 stringr
install.packages("stringr")
library(stringr)
myStr = "HONG123LEE4454YOU11HONG홍1004"
str_length(myStr) # 기본 내장 함수
# 한글 2byte 이지만-> 한글 한글자는 길이 1
str_locate(myStr,"EE") # list 안에 matrix 로 결과 나옴
# 부분 문자열
str_sub(myStr,3,5)
# 모두 대문자, 모두 소문자
str_to_upper(myStr)
str_to_lower(myStr)

str_replace(myStr,"HONG","YU")

# 문자열 결합!
str_c(myStr,"LEEPARK1221")
myStr = "HONG123,LEE4454,YOU11,HONG홍1004"
str_split(myStr,",")

# Regular Expression
myStr = "HONG123,LEE4454,YOU11,HONG홍1004"
# stringr 에 정규식을 이용해서 내가 원하는 형태의 문자열을 추출하는 함수 존재
# 정규식은 문자열 처리를 간다한게 해주는 좋은 방안
# 익숙해지면 굳~!!
str_extract_all(myStr, "[A-z]{3,5}[1-9]{3}")
str_extract_all(myStr, "[^A-z]{3}")

## 데이터의 입출력
# from 키보드 -< scan( ) or edit( )
myNum = scan() # 숫자만 받을 수 있음
myNum
myStr = scan(what=character()) #문자 받기
myStr
# data frame 에 직접 데이터 입력
df <- data.frame()
my_df <- edit(df)
my_df
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
# 파일 탐색기를 이용해서 파일 선택
df <- read.table(file.choose(),
                 sep=",",
                 header=TRUE,
                 fileEncoding = "UTF-8")
# 파일에 이상 데이터 있을 때
# 데이터가 없는 경우 "-"로 표시되어 있는 경우
df <- read.table("./data/t.txt",
                 sep=",",
                 header=TRUE,
                 fileEncoding = "UTF-8",
                 na.strings = "-")
df
## 데이터 교환, 전달 시에 사용하는 데이터 표준형식 ##
# 1. CSV(comma seperated value) 방식
#    csv 파일을 이용해서 사용 -> 데이터를 컴마로 구분
#    예) 홍길동, 20, 서울
# 장점 - 용량이 작다, 대용량 전달에 유리
#        부가적 데이터가 별로 없음(real data + ',')
# 단점 - 데이터의 구조화가 힘듦
#        해당 데이터의 계층 구조 표현 어려움
#        받는 쪽에서 이를 위한 parser를 만들어야함
#        따라서, 유지보수 항상 필요(데이터 열 추가하면)

# 2. XML (extened mark language) 방식
#    예) <name>hong</name><age>20</age>
# 장점 - csv의 단점을 어느정도 보완
# 단점 - redandent data(부가적 데이터)가 너무 큼
#        따라서 파일의 크기 증가
#        과거는 괜찮지만, packet이 돈인 모바일 환경 
#        -> 전달 횟수 줄여야함

# 3. JSON(JavaScript Object Notation) 방식
#    예) {name:"홍길동",age:20,address:"서울"}
# 장점 - xml에 비해 부가적 데이터가 적어서
#        요새 많이 쓰임, xml 은 과거에 많이 쓰임

# read.table()과 다른 점? 
# header=TRUE, sep=","가 defalut
df = read.csv(file.choose(),
              fileEncoding = "UTF-8")
df

# excel 파일로 데이터 제공하는 경우
install.packages("xlsx")
library(xlsx)
df = read.xlsx("data/student.xlsx",
               sheetIndex = 1,
               encoding = "UTF-8")
df
class(df) #자료구조가 뭔지 알려주는 함수

# file에 데이터 프레임 저장
write.table(df,
            file="data/result.csv",
            row.names = FALSE, # index 출력하지 말기
            quote=FALSE # " 이거 잡지 말기
            )

# R에서 JSON 처리
# JSON 데이터를 어디서 가져올까?
# 1. DB생성, 간단한 servlet을 이용하여 JSON 받아오기
# 2. Open API (영화진흥위원회 open api 사용)







