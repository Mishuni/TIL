# Docker compose 를 이용하여 Nginx,Flask,uwsgi 개발 환경 만들기



* Flask 웹서버는 개발 용도로 만들어졌기 때문에 실제 프로덕션 환경에서 사용하기에는 보안 및 기능 면에서 매우 부족하다.
* 개발과 프로덕션 환경의 차이를 줄이기 위하여 uWSGI 와 nginx 같은 웹서버 뒤에서 동작하는 애플리케이션 서버를 사용해야 한다.
* 기본 Flask 웹서버를 사용하는 대신 uWSGI를 이용하면 다양한 설정을 통하여 컨테이너를 유연하게 사용할 수 있다.



## Reference

https://basketdeveloper.tistory.com/66

https://github.com/kjcaway/nginx_flask_test

제대로 배우는 도커: 도커와 컨테이너 에코시스템 활용 해법 [아드리안 모트]