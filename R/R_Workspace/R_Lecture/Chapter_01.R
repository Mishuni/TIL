# 여러 줄 주석 설정은
# ctrl + shift + c 

# 스크립트 실행은 (interpreter 형태)
# crtl + enter

# Weak Type
library(ggplot2)
library(readxl)
A <- 100 ; B <- 300; C=400; D<- 500 ->E
a <-c(1:4)
500 -> R
x <- c("a","a","b","c")
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
dim(mpg)
str(mpg)
?mpg
summary(mpg)

# R 기본 내장 함수
mode(a) # 데이터 타입을 알려줌
is.numeric(a) # 데이터가 수치형인지 
is.double(B) # 데이터가 실수인지
is.integer(10L) # 데이터가 정수인지
is.integer(10) 
