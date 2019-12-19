# selenium -> 크롤링으로 많이 사용하기도 함
# 웹 크롤링
# 네이버 증권 사이트 불러오기
import requests 
from bs4 import BeautifulSoup as bs

url = "https://finance.naver.com/sise/"
response = requests.get(url).text
soup = bs(response, "html.parser")

# . -> class
# # -> id
kospi = soup.select_one("#KOSPI_now")
print(kospi.text)


rate = soup.select_one("")