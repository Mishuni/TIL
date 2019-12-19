# Python으로 웹 서버 만들기



## Flask

> Flask 를 이용하여 web server를 python을 통하여 구축할 수 있다.

```python
#파일명 : app.py
from flask import Flask

# flask 이름이 app으로 설정된다.
app = Flask(__name__)

@app.route("/")
def hello():
    return "안녕!"

if __name == "__main__":
    app.run(debug=True)
```



## Flask로 변수 값 받기

```python
from flask import Flask

@app.route("/greeting/<name>")
def greeting(name):
    return f"안녕하세요 {name}님!"

@app.route("/movies")
def movies():
    movies = ["겨울왕국2","클라우스","어바웃타임","나홀로집에","러브 액츄얼리"]
    print("dd","ee","ff","gg")
    return render_template("movie.html", movies = movies, text = "영화 목록")
```



