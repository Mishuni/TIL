## 데이터 조작 ##
# sql 처럼 빅데이터 내에서 필요한 정보 추출

# Basic
# ggplot2 package 안에 mpg data set
library(ggplot2)

# mpg가 data frame 형식은 아님
# 그래서 data frame 으로 바꾸기
df = as.data.frame(mpg)
head(df)
class(df) # 자료구조 보고 싶을 때
ls(df)
dim(df)
                
library(dplyr)
library(xlsx)
excel_data = read.xlsx(file.choose(),sheetIndex = 1,encoding = "UTF-8")
excel_data

# dplyr 은 data frame 을 제어하는데 특화된 함수 제공
# chaining 을 지원하기 때문에 퍈하게 data frame 제어 가능 (%>%)

# 1. rename()  : column 명을 변경
rename(excel_data, 
       Y17_AMT = AMT17)
names(excel_data)
# 2. filter() 
df <- filter(excel_data,
             SEX=="F",
             AREA %in% c("서울","경기"),
             )
df
# 3. arrange() : 데이터를 정렬해서 뽑는 (order by)
df <- arrange(df,AGE) # desc는 내림차순 안하면 오름차순
df
df <- arrange(df,desc(AMT17),AGE)
df

## 성별이 남성인 사람들만 찾아서 나이순(내림차순) 
## 정렬해서 출력 using chaining
df <- filter(excel_data,
             SEX=="M") %>% arrange(desc(AGE)) 
df

# 4. select(data frame, 컬럼명, 컬럼명 .. )
df <- filter(excel_data,
             SEX=="M") %>% arrange(desc(AGE)) %>% select(ID,SEX,AREA)
df

df <- filter(excel_data,
             SEX=="M") %>% arrange(desc(AGE)) %>% select(-SEX,-AREA)
df

# 5. mutate(data frame, colname = 수식, colname = 수식, ..) 
#   : column을 생성하는 함수
# AMT17 >= 100000 사람 vip로 설정
df = filter(excel_data, AREA=="서울") %>% 
            mutate(VIP = AMT17 > 500000)
df

# 6. summarise(data frame, colname = 함수, colname = 함수, ..) : 집계 함수
df = summarise(excel_data, SUM17AMT = sum(AMT17), cnt=n())
df

# 7. group_by(data frame, 범주형 column) :  
df = group_by(excel_data, SEX ) %>% summarize(SUM17AMT=sum(AMT17), cnt=n())
df

# 8. bind_rows(df1, df2) : 2개의 데이터 프레임을 붙이기
# column 명이 같아야 같은 줄로 묶안다.
df1 <- data.frame(x=c(1,2,3))
df1
df2 <- data.frame(x=c(4,5,6),y=c(4,4,5))
df2
bind_rows(df1,df2)
bind_cols(df1,df2)


# 연습 문제
######################################################
# mpg 이용 (EDA 탐색적 데이터 분석)
# ggplot2 package의 mpg data 활용

install.packages("ggplot2")
library(ggplot2)

mpg = as.data.frame(mpg)   # mpg data frame

# 주요컬럼
# manufacturer : 제조회사
# displ : 배기량
# cyl : 실린더 개수
# drv : 구동 방식
# hwy : 고속도로 연비
# class : 자동차 종류
# model : 자동차 모델명
# year : 생산연도
# trans : 변속기 종류
# cty : 도시 연비
# fl : 연료 종류

# 1. 자동차 배기량에 따라 고속도로 연비가 다른지 알아보려 한다. 
# displ(배기량)이 4 이하인 자동차와 4 초과인 자동차 중 
# 어떤 자동차의 hwy(고속도로 연비)가 평균적으로 더 높은지 확인하세요.
df = group_by(mpg,displ<=4 ) %>% summarise( average = mean(hwy) )
df 
df <- mutate(mpg, DISPL_DIFF = ifelse(displ <= 4, "LOW","HIGH")) %>%
  group_by(DISPL_DIFF) %>% summarise(AVG_HWY = mean(hwy))

# 2. 자동차 제조 회사에 따라 도시 연비가 다른지 알아보려고 한다. 
# "audi"와 "toyota" 중 어느 manufacturer(제조회사)의 cty(도시 연비)가 
# 평균적으로 더 높은지 확인하세요.
df = filter(mpg, manufacturer %in% c("audi","toyota")) %>% group_by(manufacturer) %>% summarise(AVG_CTY=mean(cty))
df

# 3. "chevrolet", "ford", "honda" 자동차의 고속도로 연비 평균을 알아보려고 한다. 
# 이 회사들의 데이터를 추출한 후 hwy(고속도로 연비) 전체 평균을 구하세요.
df = filter(mpg, manufacturer %in% c("chevrolet","ford","honda")) %>% summarise(AVG_HWY=mean(hwy))
df

# 4. "audi"에서 생산한 자동차 중에 어떤 자동차 모델의 hwy(고속도로 연비)가 
# 높은지 알아보려고 한다. "audi"에서 생산한 자동차 중 hwy가 1~5위에 해당하는 
# 자동차의 데이터를 출력하세요.
df = filter(mpg, manufacturer == "audi") %>% arrange(desc(hwy))
head(df,5)

# 5. mpg 데이터는 연비를 나타내는 변수가 2개입니다. 
# 두 변수를 각각 활용하는 대신 하나의 통합 연비 변수를 만들어 사용하려 합니다. 
# 평균 연비 변수는 두 연비(고속도로와 도시)의 평균을 이용합니다. 
# 회사별로 "suv" 자동차의 평균 연비를 구한후 내림차순으로 정렬한 후 1~5위까지 데이터를 출력하세요.
df = filter(mpg, class=="suv") %>% mutate( avgy = (cty+hwy)/2)  %>% arrange(desc(avgy))
head(df,5)

# 6. mpg 데이터의 class는 "suv", "compact" 등 자동차의 특징에 따라 
# 일곱 종류로 분류한 변수입니다. 어떤 차종의 도시 연비가 높은지 비교하려 합니다. 
# class별 cty 평균을 구하고 cty 평균이 높은 순으로 정렬해 출력하세요.
df <- group_by(mpg,class) %>% summarise(avg_cty = mean(cty)) %>% arrange(desc(avg_cty))
df

# 7. 어떤 회사 자동차의 hwy(고속도로 연비)가 가장 높은지 알아보려 합니다. 
# hwy(고속도로 연비) 평균이 가장 높은 회사 세 곳을 출력하세요.
df <- group_by(mpg,manufacturer) %>% summarise(avg_hwy = mean(hwy)) %>% arrange(desc(avg_hwy))
head(df , 3)

# 8. 어떤 회사에서 "compact" 차종을 가장 많이 생산하는지 알아보려고 합니다. 
# 각 회사별 "compact" 차종 수를 내림차순으로 정렬해 출력하세요.
df <- filter(mpg, class=="compact") %>% group_by(manufacturer) %>% summarise(cnt=n())
df


## 데이터 정제 ##

# raw 데이터를 분석이 가능한 형태로 가공

## 1. 결측치 NA를 해결 
### 1-1) 가장 간단한 법 - 지우기
# is.na() 라는 함수 이용
df <- data.frame(   id=c(1,2,NA,4,NA,6),
                    score=c(20,30,NA,50,70,NA))
df
is.na(df)
is.na(df$id)

# NA가 들어가 있는 데이터가 전체 데이터 크기에 비해 ( EX)10000개 중 1개면?)
# 상대적으로 아주 작을때는 삭제하는게 좋을 수 있다.
library(dplyr) # 데이터 프레임 제어
# id 가 NA인 row는 삭제
result <- df %>% filter(!is.na(df$id))
result <- df %>% filter(!is.na(df$id),
                        !is.na(df$score))
# 더 간단하게 모든 NA를 다 찾아서 제거하기
result <- na.omit(df) # 한개라도 NA가 있으면 행 제거


### 1-2) NA값을 다른 값으로 대체하는 방법
df <- data.frame(   id=c(1,2,NA,4,NA,6),
                    score=c(20,30,NA,50,70,NA))
# 점수 평균
mean(df$score) #NA
# NA 무시하고 계산 하기
value<-mean(df$score, na.rm = TRUE) # 42.5
# score의 NA값을 평균으로 대체
df$score <- ifelse(is.na(df$score),value,df$score)
df


## 2. 이상치를 해결
# 2-1) 이상치는 값이 없는 건 아닌데, 사용할 수 없는 값이 포함된 경우
df <- data.frame(   id=c(1,2,NA,4,NA,6),
                    score=c(20,30,NA,50,70,NA),
                    gender=c('f','m','f','m','f','%'),
                    stringsAsFactors = FALSE)
df # gender 에서 '%' 는 이상치

# 이상치를 NA로 변환 후, NA 처리
df$gender <- ifelse(df$gender %in% c('f','m'),df$gender,NA)
df
df$gender <- ifelse(is.na(df$gender),'f',df$gender)
df
# 2-2) 극단치의 경우
# 한계점을 넘는 값이 들어온 경우
# 이런 극단치는 데이터 분석시에 결과에 영향이 클 수 있음
# ex) 성인 몸무게 5g, 5000000kg
# 극단치에 대한 논리적인 판단 근거 및 기준이 있어야함
#   1 논리적으로 이상적인 범위 설정하기
#   2 통계적인 방법으로 범위 설정하기
































