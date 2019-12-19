# pip install requests
# crlt + /   -> 주석 처리
# pip install bs4   -> beautifulsoup4
# 응답한 내용을 보기 좋게, 사용하기 좋게 만들어 줌
# crlt + u + l   -> bash 에서 cls 와 같은 동작
# import requests

# response = requests.get("http://www.naver.com").text
# print(response)
import random
import json

import requests
from bs4 import BeautifulSoup as bs


numbers = random.sample(range(1,46),6)
url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=836"
print(numbers)

# 브라우저에서 링크 검색하면 하는 동작과 같은 동작을 함
# str
response = requests.get(url)
print(response.text)

# dictionary
lotto = json.loads(response.text)
print(lotto)

winner = []

for i in range(1,7):
    winner.append(lotto.get(f"drwtNo{i}"))

print(winner)