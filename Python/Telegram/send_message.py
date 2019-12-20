# token 숨기기
import requests
from decouple import config

token = config("TELEGRAM_BOT_TOKEN")
app_url = f"https://api.telegram.org/bot{token}"
chat_id = config("CHAT_ID")
update_url = app_url + "/getUpdates"
print(chat_id)
response = requests.get(update_url).json()
#print(response)

chat_id = response["result"][0]["message"]['chat']['id']
print(chat_id)

text = "lunch time"
message_url = app_url + f"/sendMessage?chat_id={chat_id}&text={text}"

# 메세지를 보내는 요청
requests.get(message_url)