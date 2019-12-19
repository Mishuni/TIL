import requests
import time
from bs4 import BeautifulSoup as bs
url = "https://finance.naver.com/marketindex/?tabSel=exchange#tab_section"
response = requests.get(url).text
soup = bs(response, "html.parser")

kospi = soup.select_one(".value")

print(f"현재 원/달러 환율은 {kospi.text}입니다.")

exchange1 = soup.select_one("#exchangeList > li.on > a.head.usd > div > .change")
exchange2 = soup.select_one("#exchangeList > li.on > a.head.usd > div > .blind")
print(f"원/달러 환율은 전일 대비{exchange1.text}원 {exchange2.text} 했습니다.")

# file 저장
with open("text.txt", 'w', encoding='utf-8') as f:
    f.write(f"현재 원/달러 환율은 {kospi.text}입니다.\n")
    f.write(f"원/달러 환율은 전일 대비{exchange1.text}원 {exchange2.text} 했습니다.\n")
i = 10
while(i>0):

    time.sleep(300)
    with open("text.txt", 'a', encoding='utf-8') as f:
            
        response = requests.get(url).text
        soup = bs(response, "html.parser")

        kospi = soup.select_one(".value")

        print(f"현재 원/달러 환율은 {kospi.text}입니다.")

        exchange1 = soup.select_one("#exchangeList > li.on > a.head.usd > div > .change")
        exchange2 = soup.select_one("#exchangeList > li.on > a.head.usd > div > .blind")
        print(f"원/달러 환율은 전일 대비{exchange1.text}원 {exchange2.text} 했습니다.")

        f.write(f"현재 원/달러 환율은 {kospi.text}입니다.\n")
        f.write(f"원/달러 환율은 전일 대비{exchange1.text}원 {exchange2.text} 했습니다.\n")
    i = i - 1

print("complete")
