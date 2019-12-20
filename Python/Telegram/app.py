from flask import Flask, render_template, request
from decouple import config
import requests

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
    user_id = response['message']['from']['id']
    text = response['message']['text']
    print(user_id)
    print(text)

    # 앵무새
    # 실습 2 : 텔레그렘 메시지 보내기
    message_url = app_url+f"/sendMessage?chat_id={chat_id}&text={text}"
    requests.get(message_url)


    return f"\"{text}\"메세지 전송 완료",200  # 200 반환해서 telegram이 동작 완료를 수신받을 수 있도록
# 로컬 서버에서는 그냥 집에서 놀고 있는 거라 TELEGRAM 과의 통신 불가능



# debug
if(__name__=="__main__"):
    app.run(debug=True)