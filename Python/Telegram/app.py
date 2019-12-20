from flask import Flask, render_template, request
from decouple import config
from bs4 import BeautifulSoup as bs
import requests
import random
import datetime

app = Flask(__name__)

token = config('TELEGRAM_BOT_TOKEN')
chat_id = config('CHAT_ID')

app_url = f"https://api.telegram.org/bot{token}"

# root
@app.route('/')
def hello():
    return "안녕하세요!"

@app.route('/write')
def write():
    # html file return
    return render_template("write.html")

@app.route('/send')
def send():
    message = request.args.get("message")
    message_url = app_url+f"/sendMessage?chat_id={chat_id}&text={message}"
    # 메세지를 받아서 telegram에게 메세지 보내는 요청
    requests.get(message_url)
    return f"\"{message}\"메세지 전송 완료"

# webhook 
@app.route(f'/{token}',methods = ['POST'])
def telegram():
    # return body, status_code
    # webhook 으로 부터 데이터를 넘겨받는 것
    response = request.get_json()
    
    # 실습 1 : 사용자의 아이디와 텍스트 가져오기
    user_id = response['message']['chat']['id']
    text = response['message']['text']
    print(user_id)
    print(text)

    # 앵무새
    # 실습 2 : 텔레그렘 메시지 보내기
    if(text=="lotto" or text=="로또" or text =="로또 추천해줘"):
        case = sorted(random.sample(range(1,46),6))
        text = f"오늘의 행운의 숫자는 {case}입니다."
    #if 만약 / 로또 입력이되면 text > 6개 숫자를 추천

    elif(text=="dollor"):
        url = "https://finance.naver.com/marketindex/?tabSel=exchange#tab_section"
        response = requests.get(url).text
        soup = bs(response, "html.parser")

        kospi = soup.select_one(".value")

        print(f"현재 원/달러 환율은 {kospi.text}입니다.")
        text = f"현재 원/달러 환율은 {kospi.text}입니다."

    # 점심 추천
    elif(text=="lunch"):
        menus = ["자장면","짬뽕", "오므라이스", "라면", "볶음밥", "샌드위치","닭볶음탕","김치찌개","순대국"]
        tmp = random.randint(0,len(menus)-1)
        text = menus[tmp]

    elif(text=="안녕"):
        text = "안녕하세요, 저는 Shu에요."
    
    elif(text=="바보"):
        text = "왜 나한테 바보래 나도 운다."

    message_url = app_url+f"/sendMessage?chat_id={chat_id}&text={text}"
    requests.get(message_url)

    return f"\"{text}\"메세지 전송 완료",200  # 200 반환해서 telegram이 동작 완료를 수신받을 수 있도록
# 로컬 서버에서는 그냥 집에서 놀고 있는 거라 TELEGRAM 과의 통신 불가능


# debug
if(__name__=="__main__"):
    app.run(debug=True)