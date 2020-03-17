# Android

## 기본 개념

#### : Google이 주도하는 휴대 기기용 (스마트폰, 태블릿) 플랫폼

안드로이드 안에서는 **OS(Linux Kernel)** 가 존재
                       +
**미들 웨어 (Middleware)** : 여러가지 중간 library 및 frameword (다른 데이터베이스 같은 것들을 관리해주는 system)
                       +
기본으로 내장된 **핵심 애플리케이션** (전화, 갤러리, 카메라,browser, message)

이런 것들이 포함된 모바일 디바이스들을 위한 소프트웨어 스택

##### 특징

* OpenSource : 무료!
* 앱간의 자유로운 연동
* Runtime 존재 (jre) => ART Runtime 탑재 : pc와 물리적 성능이 같지 않은 모바일 환경, 이와 관련된 성능 이슈가 많이 개선된 runtime 이므로 성능이 좋아졌다.

##### 버전

> 2007 년도 초기 SDK 발표
> 1.5 -> 2.0. -> 2.2 -> 3.0 -> ... -> 8 (오레오) -> 9(파이) -> 10 (Q) -> 11 (2020.02 현재)



---



1. Android를 구성하고 있는 주요 Component
   * Activity

     : User Interface(UI)를 담당하는 Component 

     : 사용자와의 interaction

     : Android App의 화면 1개

     : 주로 사용자의 EVENT 처리를 담당 (ex. 클릭)

     : 부하가 많이(load가 많은) 걸리는 작업처리 (db처리, 네트워크)는 주로 담당하지 않는다, 사용자에게 속도감 있게 출력을 전달해야 하기 때문

   * Service

     : 내부 로직 처리를 담당하는 Component

     : DB 연결, 네트워크 연결을 주로 담당

     : 일반적으로 Background에서 로직처리를 수행한다.

   * Broadcast Receiver

     : 안드로이드 시스템에서 내부적으로 발생되는 signal(신호)를 받아서 적절한 처리를 하는 Compnent 

     ex. 배터리 없으면 안드로이드가 배터리 계속 check 하다가 20%이하로 떨어지면 관련 신호를 brodcast -> 이 신호를 잡아주는 것

   * Content Provider & Resolver

     :  App 간의 데이터 공유

     : 데이터를 공유하려면, 한 앱이 데이터를 제공하고 다른 앱이 그 데이터를 가져다가 사용하는 공유하는 방식

     : 모든 Android App은 sandbox model을 이용 -> 하나의 앱(각 앱이 하나의 box)이 관리하는 데이터는 그 앱만 사용할 수 있음, 하나의 앱의 데이터를 다른 데이터와 공유하지를 못하는 폐쇄적인 구조 그래서 필요한 Component

     ex. 주소록 앱에 담긴 전화번호들을 통화 앱에서 가져오는 것

     

2. Android Framework 의 동작 원리 (Lifecycle)

   

> **Check!**) library vs Framework
>
> library 는 특정 기능을  모아놓은 것을 가져다가 쉽고 편하게 구현할 수가 있음, 전체 프로그래밍 구조나 로직은 개발자 걔개인이 스스로 정하기 때문에, 유지 보수가 어려움
>
> Framework 는 그 안에 담긴 rule이나 설계들을 이해해야 사용할 수 있음, 대신 공통된 구조 및 로직들이 정해져 있어 유지보수를 하는데 있어 편함

## 개발환경

> 자바언어와 코틀린 언어로 개발할 수 있음
> **java** : 10여년 된 여러 기존 자료들이 많이 있어 개발 시에 안정성이 부여됨
> **kotlin** : java 의 resource 관련된 문제에 의해 개발된 언어

* JDK
* Android Studio IDE : 주로 쓰는 안드로이드 통합 개발 환경
* AVD(Android Virtual Device) : 가상의 디바이스를 이용하여 앱을 실행해볼 때, 사용하는 것
* 실제 Android phone : 실제로 앱을 실행해볼 때 필요

설치

1. [안드로이드 스튜디오 홈페이지](https://developer.android.com/studio) 에서 다운로드
2. 

