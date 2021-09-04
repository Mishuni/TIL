## Cloud Native 환경

개발자 -> 로컬 실행 (apache tomcat) 

DB(oracle) -> 

개발 코드 WAS 에 의해 실행되고 데이터는 DB에 저장된고



각 개발자가 자신의 tomcat 으로 실행, DB는 1개 같이 사용

개발자가 여려명이 되면 코드 형상 관리 -> git 등

 

상용 운용 -> 배포 스크립트, 톰캣 매니저



최초 상용 배포

톰캣 1대, DB 1대 구입

SCP 를 통해 Clean 배포 (stop -> delivery -> start)

DNS (12st.com) => Tomcat

=> 무중단 배포가 불가능

=> 톰갯을 1대 더 구입 (Load Balancer)

HA(high availity) 구성

=> 지속적으로 구동되는 시스템

Load Balancer(LB) Out -> tomcat stop -> delivery -> tomcat start -> LB in

서버 3대 추가 구입

LB 설정 변겅, 배포 방식 변경(Jenkins, Ansible, Chef, etc..)







모놀리틱 아키텍쳐

-> 개발이 단순, 배포 단순, scale-out 단순, but DB 성능으로 인한 한계

무겁다 - IDE가 못 받쳐줌

어플리케이션 시작이 오래 걸린다, 기술 스택 바꾸기가 어렵다, 높은 결합도

