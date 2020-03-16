# 수행평가 문제 3개

#################################################################
# 1. mpg data set 을 이용한 데이터 추출

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



#################################################################
# 2. 웹 스크랩핑
# 로튼토마토 사이트 2019 년 가장 인기 있었던 영화 100개의
# 제목, user rating, 장르 추출해서 데이터 프레임으로 만든 후 파일로 저장하기
install.packages("rvest") # scraping, crawling 할 때 쓸 수 있는 패키지
library(rvest)
library(stringr)

url <- 'https://www.rottentomatoes.com/top/bestofrt/?year=2019'
request_url <- url
page_html <- read_html(request_url)
# 2019년도 영화 랭킹 페이지 에서
# 세번째 테이블(랭킹 테이블)
tables <- html_table(page_html)[[3]]
tables
tables$`No. of Reviews`<-NULL
# 특정 영화 정보 페이지에서 장르 가져오기
info_url <- 'https://www.rottentomatoes.com'
html_table <- html_nodes(page_html,".table>tr>td>a")
att <- html_attrs(html_table)
att
genres <- list(mode="chracter",length=100)
for(i in 1:length(att)){
  title<-att[[i]][1]
  request_url <- str_c(info_url,title)
  page_html <- read_html(request_url)
  genre <- html_nodes(page_html," section.panel.panel-rt.panel-box.movie_info.media > div > div > ul > li:nth-child(2) > div.meta-value")
  genre <- html_text(genre)
  genre <- str_remove_all(genre,"\n")
  genre <- str_remove_all(genre," ")
  genres[i] <- genre[[1]]
}
head(genres)
tables$Genre <- as.character(genres)
tables$RatingTomatometer <- as.character(tables$RatingTomatometer)
tables$Title <- as.character(tables$Title)
head(tables)
write.csv(
  tables, file ="./data/rowtten_tomato_2019_rank.csv", row.names = FALSE 
)


#################################################################
# 3. 네이버 영화 댓글 사이트에서 특정 영화에 대한
#    댓글을 수집하여 wordcloud 생성
install.packages("rvest") # scraping, crawling 할 때 쓸 수 있는 패키지
library(rvest)
library(stringr)

# 제목에 맞는 무비 코드 추출
url <- "https://movie.naver.com/movie/bi/pop/movie_sel.nhn"
page_html <- read_html(url, encoding="cp949") 
nodes = html_nodes(page_html,".c_44 > li")
attrs<-html_attrs(nodes[[1]])
attrs <- attrs[3]
movie <- str_split(attrs,",'")[[1]]
title <- str_sub(movie[3],0,nchar(movie[3])-3)
movieCode <- str_sub(movie[2],0,nchar(movie[2])-1)
movieCode <- "89755"  # 레미제라블
movieCode <- "186613" # 작은아씨들
# 무비 페이지에서 댓글 내용 추출 (scraping)
movie_url <- 
  "https://movie.naver.com/movie/point/af/list.nhn?st=mcode&sword="
comment <- vector(mode = "character")
for(i in 1:100){
  request_url <- str_c(movie_url,movieCode,"&target=&page=",i)
  page_html <- read_html(request_url,encoding="cp949")
  nodes <- html_nodes(page_html,".list_netizen > tbody> tr >.title")
  text <- html_text(nodes)
  index <- str_locate(text,"별점")
  result <- str_replace_all(text,"\n","")
  result <- str_sub(result,index[[1]]+7,nchar(text)-31)
  result <- str_replace_all(result,"\t","")
  comment <-append(comment,unlist(result))
}

# 댓글 내용에서 형태소 분석 하기
library(KoNLP) 
library(dplyr)
useNIADic()
nouns <- extractNoun(comment, autoSpacing = T)
wordcount <- table(unlist(nouns))
df_word <- as.data.frame(wordcount,stringsAsFactors = FALSE)
head(df_word)
df_word <- rename(df_word, word=Var1, freq=Freq) %>% arrange(desc(freq))
df_word <- filter(df_word, nchar(word)>=2) %>% arrange(desc(freq))
head(df_word)
# 분석 내용으로 word cloud 만들기
library(RColorBrewer)
library(wordcloud)
pal <- brewer.pal(8,"Dark2")
set.seed(1234) 
df_word<-df_word[c(-1),]
wordcloud(words=df_word$word, #단어
          freq=df_word$freq, #빈도수
          min.freq = 2, # 최소 단어 빈도
          max.words = 300, # 표현 단어 수
          random.order = F, #고빈도 단어 중앙 배치
          random.color = F,
          rot.per=.01, # 회전 단어 비율
          scale=c(2,0.5),
          colors=pal)

install.packages('wordcloud2')
library('wordcloud2')
df_word %>% 
  filter(str_length(word)>=2) %>% 
  wordcloud2(fontFamily='Noto Sans CJK KR Bold',size=0.2,minSize = 0.3,shape ='star' )
?wordcloud2


