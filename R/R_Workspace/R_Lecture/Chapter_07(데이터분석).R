# 웹 데이터 분석?
# 사용자가 언제 어떤 걸 사고, 팔고 (log data)
# 이는 의사결정에 사용되는 데이터들 ex)마케팅
# 추천 시스템 

# Graph
# 2차원 그래프
# ggplot2 pakage 를 이용
library(ggplot2)
# 1) 배경설정(x축,y축 설정)
# 2) 그래프 모형 설정 및 추가 (선,막대,점 등등)
# 3) 축 범위, 색과 같은 설정 추가
df <- as.data.frame(mpg) # table형식 데이터를 data frame 으로 변경
head(df)

## 1. 산점도(scatter plot)
# 2차원 평면상에 점으로 표현한 그래프
# 배기량과 고속도로 연비의 관계 분석 산정도
# -> 보통 배기량이 높을 수록 연비가 떨어짐

# x,y 축 설정
ggplot(data=df, 
       aes(x=displ,y=hwy))
# 그래프 형태 설정
ggplot(data=df, 
       aes(x=displ,y=hwy))+geom_point() # 산점도 그리는 함수
# 설정 넣기
ggplot(data=df, 
       aes(x=displ,y=hwy))+geom_point()+
      xlim(2,6)+ylim(20,35)

## 2. 막대그래프
# 일반적으로 집단간의 차이를 볼 때 사용
# 구동 방식별 연비 차이 (전륜구동, 후륜구동, 4륜구동)
# 일반적으로 전륜 연비 > 후륜 > 4륜
result <- df %>% group_by(drv) %>% summarise(avg=mean(hwy))
# 막대 그래프를 이용해서 집단간의 차이를 비교하기
ggplot(data=result, aes(x=drv,y=avg))+
      geom_col() # 막대그래프 그리는 함수

## 3. box 그래프
# 구동방식에 따른 고속도로 연비를 boxplot 으로 그리고
# 어떤 의미를 내포하고 있는지 알아보기
ggplot(data=df, aes(x=drv,y=hwy))+geom_boxplot()
# 최소-1사분위-2사분위-3사분위-최댓값
# 그래프에서 점 값의 의미? 이상치(극단치)
# 전륜구동차의 연비가 매우 다양한 범위를 지니는 것을 알 수 있음(표준 편차가 큼)

## 문자열처리(워드클라우드)
# 한글 형태소 분석 (Morphology Analysis)
# 형태소 : 뜻을 가진 가장 작은 단위

# 영화댓글사이트에서 특정 영화에 대한 댓글을 모아서 형태소 분석 후,
# 워드클라우드 작성

# KoNLP pakage : 한글 자연어 분석 패키지
# 한글은 프로그램적으로 자연어 분석이 매우 어려움
install.packages("rJava")
install.packages("memoies")
install.packages("KoNLP") # error날 것 
# -> 2020년 들어와서 repository에서 삭제 됨(update 중일 듯)
# 따라서, 폴더를 들여와서 사용하자
# 복사된 2개의 폴더를 library폴더에 복사
.libPaths()[1] # 여기에 이동시키자
library(KoNLP) # 
library(dplyr)
install.packages("Sejong")
install.packages("hash")
install.packages("tau")
install.packages("RSQLite")
install.packages("devtools")
# 사용할 수 있는 한글 사전은 3가지 종류
# 시스템, 세종, NIADIC
useNIADic()
txt <- readLines("C:/Users/student/TIL/R/R_Workspace/R_Lecture/data/hiphop.txt"
                 ,encoding = "UTF-8")
head(txt)
install.packages("stringr")
library(stringr)
library(dplyr)
# \n 같은 부가적 문자 지우기
txt<-str_replace_all(txt,"\\W"," ")
head(txt)
extractNoun("아버지가 방에 들어가신다")
# 여러 줄의 데이터를 분석
# 결과는 vector가 담긴 리스트형식으로 나타남
nouns <- extractNoun(txt)
nouns
# 리스트로 되어 있는 nouns를 빈도 테이블로 변환
# unlist : 리스트를 풀어서 vector로 싹 풀어 담는 것
# table : 빈도 수 테이블 생성
?unlist
unlist(nouns)
wordcount <- table(unlist(nouns))
wordcount
df_word <- as.data.frame(wordcount,stringsAsFactors = FALSE)
head(df_word)
df_word <- rename(df_word,word=Var1,freq=Freq)
# 두 글자 이상의 명사만 추출
# nchar > 몇글자인지 결과 출력
df_word <- filter(df_word,nchar(word)>=2)
top_20 <- df_word %>% arrange(desc(freq)) %>% head(20)

# 워드 클라우드 만들기
# : 단어의 빈도에 따라 글자의 크기와 색깔이 다르게 표현
install.packages("wordcloud")
library(RColorBrewer)
library(wordcloud)
# 단어 색상 목록 만들기
# Dark2 색상 목록에서 8개 색상 코드 목록 추출
pal <- brewer.pal(8,"Dark2")
# 난수 고정
# wordcloud()는 함수를 실행할 때마다 난수를 이용해 다른 모양 생성
# 항상 동일한 모양의 클라우드를 생성하도록 set.seed 로 난수 고정
set.seed(1234) 
wordcloud(words=df_word$word, #단어
          freq=df_word$freq, #빈도수
          min.freq = 2, # 최소 단어 빈도
          max.words = 200, # 표현 단어 수
          random.order = F, #고빈도 단어 중앙 배치
          rot.per=.1, # 회전 단어 비율
          scale=c(4,0.3),
          colors=pal)

df_word %>% filter(df_word$freq>=2) %>% arrange(desc(freq))

## 국정원 트윗
twitter <- read.csv("./data/twitter.csv",
                    header =T,
                    stringsAsFactors = F,
                    fileEncoding = "UTF-8")
twitter
twitter <- rename(twitter,
                  no = 번호,
                  id = 계정이름,
                  date = 작성일,
                  tw = 내용)
twitter$tw <- str_replace_all(twitter$tw, "\\W"," ")
head(twitter$tw)

# 트윗에서 명사 추출
nouns <- extractNoun(twitter$tw)
nouns
wordcount <- table(unlist(nouns))
df_word <- as.data.frame(wordcount, stringsAsFactors = F)
df_word <- rename(df_word, word=Var1, freq=Freq)
head(df_word)
# 두 글자 이상 단어만 추출
df_word <- filter(df_word,nchar(word)>=2)
top20 <- df_word %>% arrange(desc(freq)) %>% head(20)
top20
# 단어 빈도 막대 그래프
library(ggplot2)
# 빈도 순서 변수 생성
order <- arrange(top20, freq)$word

ggplot(data=top20, aes(x=word, y=freq))+
   ylim(0,2500)+
   geom_col()+
   coord_flip()+
   scale_x_discrete(limit=order)+ # 빈도순 막대 정렬
   geom_text(aes(label=freq),hjust=-0.3) # 빈도 표시
pal <- brewer.pal(8,"Dark2")
pal <- brewer.pal(9,"Blues")[5:9]
set.seed(1234)

wordcloud(words=df_word$word,
          freq = df_word$freq,
          min.freq = 10,
          max.words = 200,
          random.order = F,
          rot.per = .1,
          scale = c(6,0.2),
          colors=pal)

# 단계 구분도 (Choropleth Map) : 
# 지역별 통계치를 색깔의 차이로 표현한 지도
install.packages("ggiraphExtra")
library(ggiraphExtra)
# 미국 주(state)별 강력 범죄율 정보
str(USArrests)
head(USArrests)























