# 여러 줄 주석 설정은: ctrl + shift + c 
# 스크립트 실행은 (interpreter 형태): ctrl + enter

# R package
## pakage 설치
remove.packages("ggplot2")
install.packages("ggplot2")
install.packages("dplyr")
install.packages("readxl")
## pakage 로딩
library(ggplot2)
library(dplyr)
require(readxl)
# path 출력 (base pakage에 들어 있는 함수)
# 안에 경로 문자열 넣어주면 lib 설치 경로 바꿀 수 있음
.libPaths() # 출력되는 것 중 1번 경로에 라이브러리가 설치됨

A <- 100 ; B <- 300; C=400; D<- 500 ->E
a <-c(1:4)
500 -> R
x <- c("z","c")
qplot(x)
qplot(data=mpg,x=drv, y=hwy)
qplot(data=mpg,x=drv, y=hwy, geom="boxplot", colour = drv)

english <- c(90,80,70,30)
math <- c(50,60,100,20)
df_midterm <- data.frame(english, math,
                         class=c(1,1,2,3))
df_midterm
mean(df_midterm$english)

# 여러개의 값을 출력하려면 cat 함수 사용
print(A)
# 값을 지정한 file 에 출력 (추가 아니고 덮어쓰는 것)
cat("\n변수값들:",A,B,english,file="C:/Users/student/TIL/R/R_Workspace/R_Lecture/test.txt")
# file 안에 내용을 추가하기
cat("\n변수값들:",A,B,english,file="C:/Users/student/TIL/R/R_Workspace/R_Lecture/test.txt",append="TRUE")

# CSV 안에 테이블 저장하기
write.csv(df_midterm, file="C:/Users/student/TIL/R/R_Workspace/R_Lecture/test.csv")

# 정수값도 사실은 실수 이기 때문에 나누기도 실수값
result <- 100/3
# R studio 에서 숫자 7digits 나오도록 기본 세팅 되어 있음
result # 33.33333
options(digits=5)
result # 33.333
sprintf("%.8f",result) # 33.33333333
result = 100 %/% 3 # 몫 구하는 연산
result = 100 %% 3 # modulo 연산(나머지)
save(df_midterm,file = "df_midterm.rda")
rm(df_midterm)
load("df_midterm.rda")

# 논리 연산자
# &,  && => 의미가 같음 (AND)
# |, || => 의미가 같음 (OR)
# but, 벡터인지 스칼라(원소 하나짜리 벡터)인지에 따라 동작이 다름
english-90 & math # vector-wise 연산 = 결과가 벡터
english-90 | math 

english-90 && math # 결과가 스칼라, 맨 앞 원소끼리만 
english-90 || math-math
english-90 || math-100
c(TRUE,FALSE) && c(FALSE,FALSE,TRUE)

#기본함수
abs(-3)
factorial(4)

exam <- read.csv("csv_exam.csv")
head(exam,10)
View(exam)
dim(exam)
str(exam)
summary(exam)
mpg <- as.data.frame(ggplot2::mpg)
tail(mpg)
?mpg
summary(mpg)

# R 기본 내장 함수
mode(a) # 데이터 타입을 알려줌
is.numeric(a) # 데이터가 수치형인지 
is.double(B) # 데이터가 실수인지
is.integer(10L) # 데이터가 정수인지
is.integer(10) 

# vector 는 데이터 타입이 모두 같아야함
# 위 같은 경우에 logical 이 우선순위가 더 낮으므로
# FALSE 는 0으로 바뀜
myvar = c(19,20,30,40,FALSE) 
# 밑은 다 문자열로 바뀜
myvar = c(3+2i,"a",123,TRUE)
myvar = "100"
as.numeric(myvar) #as계열 이용한 형변환(casting)

df_raw <- data.frame(var1=c(1,2,1),
                     var2=c(2,3,2))
df_new <- df_raw
df_new <- rename(df_new,v2=var2) #var2 를 v2 로 수정
df_new$var_sum <- df_new$var1 + df_new$v2
df_new
mpg$total <- (mpg$cty + mpg$hwy)/2
head(mpg)
summary(mpg$total)
hist(mpg$total)
mpg$test <- ifelse(mpg$total >=20 , "pass", "fail")
table(mpg$test)
mpg$grade <- ifelse(mpg$total>=30,"A",
                    ifelse(mpg$total>=20,"B","C"))
table(mpg$grade)
qplot(mpg$grade)

mds <- as.data.frame(ggplot2::midwest)
head(mds)
mds <- rename(mds, total=poptotal)
mds <- rename(mds, asian=popasian)
mds$per_asian <- ((mds$asian/mds$total)*100)
asian_dst_mean = mean(mds$per_asian)
mds$asian_lot <- ifelse(mds$per_asian>=asian_dst_mean,"large",
                        "small")
qplot(mds$asian_lot)

## 자료구조
# vector : index 시작은 1 
myvar = c(100) # scala
# numeric 데이터만 사용할 수 있는 단조증가,감소 vector생성
myvar = 2:10 # 둘 다 포함한 범위에서 생성
myvar = 8.4:2
myvar = seq(1, 10, by = 2)
myvar = seq(from=10, to=3, by=-3)
myvar = rep(1:3,times=3)
myvar = rep(1:3, each=3)
length(myvar)
myvar[1]
myvar[length(myvar)]

result <- myvar[3:7] # slicing
result <- myvar[c(1,4,7)] # fancy indexing
myvar[-1] # 1번 요소 빼고 나머지
myvar[-(3:5)] # 3,4,5 요소 빼고 나머지

### vector 데이터에 이름 붙이기
myvar <- seq(10,50,by=10)
names(myvar) = c("a","b","c","d")
names(myvar) # 각 요소의 이름 출력
myvar
myvar["a"]

# vector 연산
myvar1 = 1:3
myvar2 = 4:6
result = myvar1 - myvar2
result = myvar1 * myvar2
myvar3 = 4:10
# 크기가 다르면?
# 작은 벡터를 큰 벡터에 맞춤
# 작은 벡터를 순환시켜서 생성 [recycle rule]
result = myvar1 * myvar3

