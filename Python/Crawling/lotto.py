# pip install requests
# crlt + /   -> 주석 처리
# pip install bs4   -> beautifulsoup4
# 응답한 내용을 보기 좋게, 사용하기 좋게 만들어 줌
# crlt + u + l   -> bash 에서 cls 와 같은 동작
# import requests
# json viewer 확장 프로그램 크롬에 하면 json 주소 내용 깔끔하게 보여 줌 
# response = requests.get("http://www.naver.com").text
# print(response)
import random
import json

import requests

url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=836"
# 회차에 맞는 로또 번호 조회 json

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

def pick():
    picked = sorted(random.sample(range(1,46),6))
    print(picked)
    matched = len(set(winner) & set(picked))
    # 같은 숫자의 갯수 저장
    if(matched == 6):
        print("1th")
    elif(matched == 5):
        print("2th")
    elif(matched == 4):
        print("4th")
    elif(matched == 3):
        print("3th")
    else:
        print("fail")

pick()