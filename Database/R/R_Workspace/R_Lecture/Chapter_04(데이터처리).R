
# R에서 JSON 처리
# JSON 데이터를 어디서 가져올까?

# 1. DB생성, 간단한 servlet을 이용하여 JSON 받아오기

# R 에서 외부 Web application 호출한 후 결과를
# data frame 에 받아오기

# 결과로 얻은 json을 data farme 으로 바꾸기
# 외부 package 필요 : 많은 패키지 중  jsonlite사용
install.packages("jsonlite")
require(jsonlite) # JSON 처리
require(stringr)  # 문자열 처리
install.packages("curl")
require(curl) # 의존성 문제 처리
url <- "http://localhost:8080/bookSearch/search?keyword="

request_url <- str_c(url,scan(what=character()))
request_url

df = fromJSON(request_url)
View(df)

# 책 제목만 뽑아내기
for (i in 1:nrow(df)) {
  print(df$title[i])
}


# 2. Open API (영화진흥위원회 open api 사용)
# http://www.kobis.or.kr/kobisopenapi/homepg/main/main.do
# 위 사이트 들어가서 키 발급 받기
myKey <- "b32fad64739ee39bbbf10380f1489444"
url <- "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
url <- str_c(url,myKey)
url

search_day <- str_c("&&targetDt=",scan())
request_url <- str_c(url,search_day)
request_url

# json 안에 또 json 이 있는 형태
df = fromJSON(request_url)
View(df) # 가져온 df가 list로 되어 있음(복잡한 json을 1차원식으로)

df[["boxOfficeResult"]] # = df[[1]]
name = df$boxOfficeResult$dailyBoxOfficeList
name$movieNm

for (i in 1:nrow(name)) {
  print(name$movieNm[i])
}

