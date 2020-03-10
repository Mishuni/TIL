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
