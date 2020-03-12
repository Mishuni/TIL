# 데이터 수집(web scraping, web crawlling)

# 웹사이트 상에서 내가 원하는 위치의 대한 정보를
# 자동으로 추출해서 수집하는 기능 => Web Scraping

# 자동화 봇인 web crawler가 정해진 규칙에 따라서
# 복수개의 웹페이지를 browsing하는행위 => Web Crawling

# web scraping => 1) css,jquery(selector)를 이용하는 방식
#                 2) XPATH 를 이용하는 방식

# selector 이용 방식
# javascript로 selector 를 알아보기

# 네이버 영화 댓글 페이지를 이용해서 scraping 하기
# 필요한 pakage 
install.packages("rvest") # scraping, crawling 할 때 쓸 수 있는 패키지
library(rvest)
library(stringr)
#scraping할 page의 url
url <- "https://movie.naver.com/movie/point/af/list.nhn?&page="
request_url <- str_c(url,1)
request_url

# 가지고 오는 url의 페이지 encoding을 알아야 함
# 이 페이지 같은 경운 cp949를 사용
page_html <- read_html(request_url, encoding="cp949") 
page_html
# 영화 제목
## selector로 원하는 부분 찾기
nodes = html_nodes(page_html,"td.title > a.movie")
nodes
# elements가 가지고 있는 tag사이의 글자만 가져오기
movie_title <- html_text(nodes)
movie_title

# 평점
nodes = html_nodes(page_html,".list_netizen_score > em")
nodes
scores <- html_text(nodes)
scores
# 내용
nodes = html_nodes(page_html,"td.title")
nodes
test <- html_text(nodes)
test <- str_remove_all(test,"\n")
test <- str_remove_all(test,"\t")

test <- xml_attrs(xml_child(nodes[[1]], 4))[["href"]]
test

for(i in 1:length(nodes)){
  test <- xml_attrs(xml_child(nodes[[i]], 4))[["href"]]
  s <- str_locate_all(test,",")
  list <- s[[1]]
  con <- str_sub(test,list[2],list[3])
  con <- str_sub(con,4,str_length(con)-2)
  print(movie_title[i])
  print(scores[i])
  print(con)
  print("----------------------------")
}


## XPATH 이용하기
url <- "https://movie.naver.com/movie/point/af/list.nhn?&page="
request_url <- str_c(url,1)
request_url
# read html 로 해당 페이지의 html 내용을 끌어오기
page_html<-read_html(request_url,encoding="cp949")
page_html
# 영화 제목 추출 -> chrome에서 그 부분 xpath 복사할 수 있음
# 같은 태그 다 추출하려면 loop 를 돌려야 한다는 단점이 있음
# title_xpath = '//*[@id="old_content"]/table/tbody/tr[1]/td[2]/a[1]'
movie_title = vector(mode="character", length = 10) # 빈 벡터 (영화 제목들 저장 벡터)
i = 1
for(i in 1:10){
  myPath <- str_c('//*[@id="old_content"]/table/tbody/tr[',i,']/td[2]/a[1]/text()')
  nodes <- html_nodes(page_html, xpath = myPath)
  movie_title[i] = html_text(nodes)
}
movie_title
nodes


## 수행평가
# 로튼토마토 사이트 2019 년 가장 인기 있었던 영화 100개의
# 제목, user rating, 장르 추출해서 데이터 프레임으로 만든 후 파일로 저장하기
install.packages("rvest") # scraping, crawling 할 때 쓸 수 있는 패키지
library(rvest)
library(stringr)

url <- 'https://www.rottentomatoes.com/top/bestofrt/?year=2019'
request_url <- url
page_html <- read_html(request_url)
tables <- html_table(page_html)[[3]]
tables
tables$`No. of Reviews`<-NULL
# 영화 정보에서 장르 가져오기
info_url <- 'https://www.rottentomatoes.com'
html_table <- html_nodes(page_html,".table>tr>td>a")
att <- html_attrs(html_table)
att
genres <- list(mode="chracter",length=100)

for(i in 1:length(att)){
  title<-att[[i]][1]
  request_url <- str_c(info_url,title)
  page_html <- read_html(request_url)
  genre <- html_nodes(page_html," .meta-value>a")
  genre <- html_nodes(page_html," section.panel.panel-rt.panel-box.movie_info.media > div > div > ul > li:nth-child(2) > div.meta-value")
  genre <- html_text(genre)
  genre <- str_remove_all(genre,"\n")
  genre <- str_remove_all(genre," ")
  genres[i] <- genre[[1]]
}
genre
genres
tables$Genre <- as.character(genres)
tables$RatingTomatometer <- as.character(tables$RatingTomatometer)
tables$Title <- as.character(tables$Title)
tables
write.csv(
  tables, file ="C:/Users/student/TIL/R/R_Workspace/R_Lecture/data/rowtten_tomato_2019_rank.csv", row.names = FALSE 
)
