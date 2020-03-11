Web

-----

* [Web Github 주소](https://github.com/Mishuni/Web_study)

위 url로 요청을 하면, Json 형태로 데이터가 전달될 것

round-trip 방식 : jsp (was 가 동작 후, 페이지 동쨰로 보내주는 것) -> 프로그램이 쉽다는 장점 : 모든 작업이 server 단에서 일어남 , client 사이드가 복잡하지 않음,  개발이 오히려 쉬움(빨리 만들 수 있음), 서버와 클라이언트가 주고 받는 용량이 많은 수 밖에없음,(html,이미지.. 다 주고 받으니까) -> 이게 옛날에는 문제가 되지 않았지만, 현재는 매우 중요(데이터 크기가 곧 비용 및 속도) 

다른 방식 : jsp 안쓰고,  client 따로 만들기 (Front-end web application, server 는 순수 로직만) -> front-end(html,css,javascript) : server -> 사이에는 데이터만 왔다 갔다 하는 거 ! ( csv, xml, javascript ) -> 이런 방식은 SPA(single page application)

javascript : jquery (client side) > library와 framework 차이? 프로그래밍 규칙이 없나 있나, library 를 이용한 구현은 유지보수 문제가 존재(절차가 명시되어 있지 않으니까) -> 그래서 jquery 로 client side 를 다 해버리면 문제가 있음 ,  유지보수 비용 발생> 

이래서 나온 프레임웍(frontend size) : angular.js, react.js

spring 과는 완전 별개, 둘은 data 만 주고 받는 관계다. 문제는? 서버와 클라이언트 둘 다 고용해야함

웹 프로그램을 어떤 방식으로 사용하느냐에 따라 방식을 선택할 수 있음 (어떤게 더 좋고 나쁜게 아님)