# Web Crawling with Python

## 1. requests

> 입력된  url의 소스코드를 str 타입으로 얻어올 수 있다.

```python
import requests

url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=836"
# 회차에 맞는 로또 번호 조회 json

# 브라우저에서 링크 검색하면 하는 동작과 같은 동작을 함
# str
response = requests.get(url)
print(response.text)
```



## 2. bs4 (BeautifulSoup)

> 가져온 소스코드를 입력된 형식에 맞게 보기 좋게 바꿔준다.

```python
import requests
from bs4 import BeautifulSoup as bs



url = "https://www.naver.com"
response = requests.get(url).text
# "html.parser" > 받아올 형식 (html 파일을 받아오고 있고, 이걸 이쁘게 바꿔줘라)
doc = bs(response, "html.parser")
# 앞에 . 은 class 를 가져온다는 것을 의미
# 앞에 # 은 id 를 가져온다는 것을 의미
result = doc.select_one(".ah_k")
print(result)
```



## 3. webbrowser

> web을 동작시키는 여러 명령어들을 지니고 있는 내장 패키지

```python

import requests 
from bs4 import BeautifulSoup as bs

import webbrowser

url = "https://www.naver.com"
response = requests.get(url).text

# "html.parser" > 받아올 형식 (html 파일을 받아오고 있고, 이걸 이쁘게 바꿔줘라)
doc = bs(response, "html.parser")
result = doc.select(".ah_k")
search_url = "https://search.naver.com/search.naver?query="

for i in range(5):
    webbrowser.open(search_url+result[i].text)
```

