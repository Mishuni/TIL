# Nginx에 Flask(uwsgi) 연결하기



1. 설치

   - uWSGI

     ```sh
     $ sudo mv /usr/bin/uwsgi /usr/bin/uwsgi-old
     $ sudo apt-get update 
     $ sudo apt-get install -y uwsgi
     $ sudo apt-get install uwsgi-plugin-python3
     
     $ pip3 install uwsgi
     $ ln -s /usr/local/bin/uwsgi /usr/bin/uwsgi
     ```

   - nginx

     ```sh
     $ sudo apt-get update 
     $ sudo apt-get install -y nginx 
     ```

2. Flask app

   ./app.py

   ```python
   from flask import Flask
   
   app = Flask(__name__)
   
   @app.route("/")
   def index():
       return "Hello"
   
   @app.route("/bye")
   def bye():
       return "bye"
   
   application = app
   ```

   ./uwsgi.ini  [app.py 와 같은 디렉토리]

   ```ini
   [uwsgi]
   socket = /tmp/flask.sock
   chmod-socket = 666
   wsgi-file = app.py
   single-interpreter = true
   enable-threads = true
   master = true
   ```

   ```sh
   $ uwsgi --ini uwsgi.ini
            혹은
   $ sudo uwsgi -i uwsgi.ini --plugin python3
   ```

3. nginx  설정 파일

   /etc/nginx/site-available

   이 위치에서, flaskconfig 파일을 생성한다.

   ```sh
   cd /etc/nginx/site-available/
   sudo touch flaskconfig
   ```

   ```config
   # /etc/nginx/site-available/flaskconfig
   server {
       # 80 번 포트를 다른 프로그램이 쓰고 있다면 다른 번호 할당 가능
       listen 80;
       server_name localhost;
   
       location / {
           include uwsgi_params;
           uwsgi_pass unix:///tmp/flask.sock;
       }
   }
   ```

	활성화 되어 있는 기본 구성 삭제하고 사이트를 활성화 하기

   ```sh
   cd /etc/nginx/sites-enabled
   sudo rm defalut
   sudo ln -s ../sites-available/flaskconfig /
   ```

	nginx 다시 시작

   ```sh
   sudo service nginx restart
   ```

	http://localhost/    

	http://localhost/bye

   를 통해 확인

## Reference

[nginx와-함께-uwsgi-웹-서버를-사용하여-flask-응용-프로그램-배포](https://sodocumentation.net/ko/flask/topic/4637/nginx%EC%99%80-%ED%95%A8%EA%BB%98-uwsgi-%EC%9B%B9-%EC%84%9C%EB%B2%84%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-flask-%EC%9D%91%EC%9A%A9-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8-%EB%B0%B0%ED%8F%AC)