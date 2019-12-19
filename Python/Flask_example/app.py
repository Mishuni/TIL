
from flask import Flask, render_template
import datetime
import requests
from bs4 import BeautifulSoup as bs
import random

# 지금부터 flask의 이름이 app 이다.
app = Flask(__name__)

# url을 관리해주는 친구 > @app.route("/")
@app.route("/")
def hello():
    return "안녕!"

@app.route("/dday")
def dday():
    today = datetime.datetime.now()
    final = datetime.datetime(2020, 6, 9)
    result = final - today
    print(result)
    return f"{result.days}일 남았습니다."

@app.route("/value")
def value():
    url = "https://finance.naver.com/marketindex/?tabSel=exchange#tab_section"
    response = requests.get(url).text
    soup = bs(response, "html.parser")
    kospi = soup.select_one(".value")
        
    exchange1 = soup.select_one("#exchangeList > li.on > a.head.usd > div > .change")
    exchange2 = soup.select_one("#exchangeList > li.on > a.head.usd > div > .blind")

    return f"현재 원/달러 환율은 {kospi.text}입니다.\n 원/달러 환율은 전일 대비{exchange1.text}원 {exchange2.text} 했습니다.\n"

# is it christmas Example
# "/christmas"
@app.route("/christmas")
def chritsmas():
    today = datetime.datetime.now().date()
    print(today.strftime("%m월 %d일"))
    result = ( today.month == 12 ) and ( today.day == 25 )
    if (result) : return "크리스마스 입니다." 
    else : return f"<h1>{(datetime.datetime(today.year,12,25).date()-today).days}일 남았습니다</h1>"


@app.route("/soccer")
def soccer():
    url = "https://search.naver.com/search.naver?sm=tab_sug.top&where=nexearch&query=2019%EB%8F%99%EC%95%84%EC%8B%9C%EC%95%88%EC%BB%B5&oquery=%EB%B6%80%EC%82%B0%EC%95%84%EC%8B%9C%EC%95%84%EB%93%9C%EC%A3%BC%EA%B2%BD%EA%B8%B0%EC%9E%A5&tqi=UQVbSlp0J1sssCmdrENssssssx0-144037&acq=%EB%8F%99%EC%95%84%EC%8B%9C%EC%95%88&acr=1&qdt=0"
    response = requests.get(url).text
    soup = bs(response, "html.parser")
    play = soup.select("#main_pack > div.content_search.section > div > div.contents03_sub > div > div.tb_box > div > table > tbody > tr.first_row.tc > td:nth-child(2) > p > a")
    return f"{play[0].text}"

@app.route("/movies")
def movies():
    movies = ["겨울왕국2","클라우스","어바웃타임","나홀로집에","러브 액츄얼리"]
    return render_template("movie.html", movies = movies)

@app.route("/greeting/<name>")
def greeting(name):
    return f"안녕하세요 {name}님!"

@app.route("/cube/<int:num>")
def cube(num):
    result = num ** 3
    return str(result) 


# 식사 메뉴 추천
# 1. random
# 2. DR :  @app.route("/lunch/<inr:num>")
#    - list : ["자장면", "짬뽕", "오므라이스", "라면", "볶음밥", "샌드위치"]
#    - <int : num> 숫자 만큼 뽑기
# 3. print(선택된 메뉴들)

@app.route("/lunch/<int:num>")
def lunch(num):
    menus = ["자장면","짬뽕", "오므라이스", "라면", "볶음밥", "샌드위치","닭볶음탕"]
    #choice = random.sample(range(0,len(menus)),num)
    lunch_menus = []
    for i in range(num):
        tmp = random.randint(0,len(menus)-1)
        print(menus[tmp])
        lunch_menus.append(menus[tmp])
    return render_template("lunch.html",lunch_menus = lunch_menus)

# name == main 코드는 가장 바닥에 위치하는 것을 권장
if __name__ == "__main__":
    app.run(debug=True)
