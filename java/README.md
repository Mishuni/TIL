# Java

자바는 singletone pattern 의 설계를 주로 함

**GOF의 디자인 패턴**

**HEAD First 디자인 패턴**

---

#### 상속

장점 : 클래스의 재사용이 가능, 클래스의 확장을 편하게 할 수  있음

단점 : 클래스간의 관계성이 생김 -> 의존성 (Tightly coupled) : 독립적인 클래스 사용이 어려워짐

#### Tread

* 프로그램의 enrty point

* 해당 프로그램의 사용되는 모든 Thread가 종료되는 시점에 프로그램이 종료가 됨

* Java에서는 Thread를 만들기 위한 class 존재 => Instance 생성 (java.lang.Thread)

* Thread 객체를 생성하는 2가지 방법

  1. 상속

  2. 인터페이스 (interface) 

     : class 간의 관계성을 낮춤 (loosely coupled)

     : 더 선호되는 방법 : 클래스의 종속도를 줄일 수 있어서

     : Runnable interface 사용

* jvm 에 요청만 하고 넘김 ( 결과를 기다리지 않음)
  non-blocking 방식

* 기본적인 방식에서 thread 들의 실행 순서를 개발자가 판단할 수 없다. 지정할 수 있는 부가적인 방법은 존재 ex) main에 있는 print와 thread.start()로 실행한 print중에 누가 먼저 실행될까? 

* 
  Thread 의 run() 이 호출
  언젠가는 run 이라는 method 의 실행이 끝
  후에 thread 의 상태가 dead 상태가 됨 
  그래서 처음 start 를 하고 나서 또 start 는 할 수가 없음
  이를 해결하기 위한 방법은 없음!!!!!
  IllegalStateException 
  유일하게 다시 실행시키는 방법은 Thread 를 다시 생성해서 실행하는 것
  



----

### Eclipse 단축키 모음

