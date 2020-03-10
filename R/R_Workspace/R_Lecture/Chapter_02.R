# matrix
var1 <- matrix(c(1:5)) # 5 x 1 dimension
var1
var1 <- matrix(c(1:10),nrow = 2) # 열 기준 순서로 채워짐
var1
# 갯수 비율 못 맞추면, 순환해서 나머지 채움(recycle rule)
var1 <- matrix(var1, nrow = 3)
var1
# 행 기준 순서로 채우기
var1 <- matrix(var1, nrow=2, byrow = TRUE)
var1
var2 <- 1:5
# 행 방향으로 합치기
mat1 <- rbind(var1,var2)
mat1
# 열 방향을 합치기
var3 <- 1:3
mat1 <- cbind(mat1,var3)
mat1
var1[1,4]
var1[,4]
var1[2,]
length(var1)
nrow(var1)
ncol(var1)

# Array
var1 <- array(c(1:24),dim = c(3,2,4)) # 3 차원 행,열,면
var1

# R factor
# factor 는 범주형, vector 를 사용해서 만듦
var1 = c("A","B","AB","O","A","B")
var1_factor <- factor(var1)
var1_factor
levels(var1_factor)
# 범주에서 빠진 AB는 NA 로 바뀜
# ordered 를 참으로 하면 순서가 있는 데이터
var1_factor <- factor(var1,
                      levels=c("A","B","O"),
                      ordered = TRUE)
var1_factor

# list
# 각 index 값이 map 형태로 저장
var_scalar = 100
var_vector = c(10,20,30)
var_matrix = matrix(c(1:6), nrow=3)
# key 지정 없으면, 숫자로 매겨짐
# list는 json 처리할 때 필요
# 외부 api 를 통해 json 데이터 들여올 때, list형식
myList = list(var_scalar, var_vector, var_matrix)
myList
myList[[3]][1,2]
myList <- list(name = c("hong","kim"),
               age = c(20,30),
              gender = c("f","m") )
myList$name
myList["name"] # list 
myList[[1]]
myList[['age']]

# data frame : R에서 가장 많이 쓰이는 중요한 자료구조
df <- data.frame(NO=c(1,2,3), NAME=c("HONG","LEE","KANG"),AGE=c(20,40,50))
df$NAME # factor 로 나옴 -> 문자열이 데이터 프레임에 들어갈 때 기본으로 factor로 잡혀 들어감
df <- data.frame(NO=c(1,2,3), NAME=c("HONG","LEE","KANG"),AGE=c(20,40,50),stringsAsFactors = FALSE)
# stringsAsFactors = FALSE 을 주면 NAME이 벡터로 잘 들어감
df$NAME
df <- data.frame(x=c(1:5),
                 y=seq(2,10,2),
                 z=c('a','b','c','d','e'),
                 stringsAsFactors = FALSE)
df
# x 값이 3 이상인 행만 추출
subset(df,x>=3)
subset(df,x==2)
subset(df,z=="c")
subset(df,x>=3&y<=8)
subset(df,z>='b'&y<=8)

# !! Practice !! 
# P1) vector 생성
x <- c(4,5,7,8,10,3)
x
# P2) 
x1 = c(3,5,7,9)
x2 = c(3,3,3)
x3=x1+x2
x3 # recycle rule
x1*x2
# P3) data-frame, subset 이용
Age <- c(22,25,20,18)
Name <- c("H","C","P","K")
Gender <- c("F","M","F","M")
df <- data.frame(Age,Name,Gender,stringsAsFactors = FALSE)
subset(df,Gender=="M")

# P4) boolean indexing
x <- c(2,4,6,8)
y <- c(TRUE,FALSE,TRUE,F)
x[y] #boolean indexing
y[c(1,3)] # fancy indexing
x[1:3] #slicing
sum(x[y])

# P5)
x <- c(1,2,NA,4)
(x+2)[(!is.na(x)) & x>2] -> k # 5,6
# is .na ? NA 이냐 아니냐 
k
is.na(x)
sum(is.na(x))
