'''
1. requests > naver.com
2. response > bs4
3. webbrowser

'''

import requests 
from bs4 import BeautifulSoup as bs

import webbrowser

url = "https://www.naver.com"
response = requests.get(url).text

# "html.parser" > 받아올 형식 (html 파일을 받아오고 있고, 이걸 이쁘게 바꿔줘라)
doc = bs(response, "html.parser")
# 앞에 . 은 class 를 가져온다는 것을 의미
# 앞에 # 은 id 를 가져온다는 것을 의미
result = doc.select_one(".ah_k")
print(result)



result = doc.select_one("#whale_promotion_download")
print(result)
print()

result = doc.select(".ah_k")
print(result[0])

search_url = "https://search.naver.com/search.naver?query="

for i in range(5):
    webbrowser.open(search_url+result[i].text)
    
