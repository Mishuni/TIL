# Linux

## 환경설정 (window)

[VMware Workstation 15 Player Douwnload](https://www.vmware.com/kr/products/workstation-player/workstation-player-evaluation.html)

> 무료 버전 , 책에서는 pro 버전이 나와서 좀 다를 수 있음

### 가상 머신 만들기

> 이를 통해, 내 컴퓨터에서 여러개의 컴퓨터를 가상으로 만들 수 있다.

1. c:// 밑에 hadoop 이라는 (가상 머신 만들 폴더) 폴더 생성

2. vmware 에서 create vm

3. i will install the os later 체크 하고 next

4. linux -> centOS 7 64 bit -> 이름 변경 -> 아까 만든 폴더로 경로 변경 후 그 경로 안에 이 vm 이름으로 폴더 지정

   C:\hadoop\vm1

5. HD 용량 사이즈 : 가상으로 잡을만한 크기 (추천은 20GB) , 밑 체크는 기본으로 multiple files로 선택

6. finish 선택

7. 그 edit vm setting 에서 memory 를 2GB로 바꾸면 조금이나마 속도 향상시킬 수 있음

8. HD Remove 해서 기존 거 버리기 후에 Add 눌러서 새로운 HD 추가, type은 SCSI (스카시 하드디스크, 서버용) -> 그나마 속도가 좀 빠름

9. Maximun disk size 를 80 GB로 수정 -> next -> finish

10. Network Adapter : NAT 로 잡혀있는 거 확인, 밑에 Advanced 클릭, mac address 를 generate 할 수 있음 여기서(일단 안하고, 확인만)

    **NAT** : (VMNET8) VM 컴퓨터 + 호스트 컴퓨터들이 이 안에서 네트워킹이 가능하게 하는 것 (192.168.111.xxx) 끼리 통신이 가능하도록 위에서 설정함 -> vm 가상의 ip

1. vm1 선택후 play vm 버튼 클릭해서 실행

12. 이제 OS 를 설치해야한다.

    ```
    >ipconfig
    
    Windows IP 구성
    이더넷 어댑터 이더넷 5:
    
    이더넷 어댑터 VMware Network Adapter VMnet1:
    
    이더넷 어댑터 VMware Network Adapter VMnet8:
    
    
    ```

    책에, 그림 1-59 를 이렇게 설정하였다. vmnetcfg 를 vmware 설치 경로에 복사, 붙여넣기 한다.

    vmnetcfg 에서, change setting 선택

13. ```
    C:\Users\student>ipconfig
    
    Windows IP 구성
    
    이더넷 어댑터 이더넷 5:
    
    
    이더넷 어댑터 VMware Network Adapter VMnet1:
    
    
    이더넷 어댑터 VMware Network Adapter VMnet8:
    
       연결별 DNS 접미사. . . . :
       링크-로컬 IPv6 주소 . . . . : fe80::b9c1:d36e:a300:e9b2%29
       IPv4 주소 . . . . . . . . . : 192.168.111.1
       서브넷 마스크 . . . . . . . : 255.255.255.0
       기본 게이트웨이 . . . . . . :
       
       // VMnet 8  ip 주소가 바뀐 것 확인
       // 내 컴퓨터들 끼리 ( 가상 머신, host )들이
       // 서로 통신을 할 그룹을 설정
    ```

    (192.168.111.xxx)(0~255) 끼리 통신이 가능하도록 위에서 설정함 -> vm 가상의 ip

14. [centOS 설치](http://archive.kernel.org/centos-vault/7.0.1406/isos/x86_64/) > CentOS-7.0-1406-x86_64-DVD.iso (책이 7버전이라 이거 설치)

15. 만들어놓은 vm1 으로 가서 edit vm setting > CD/DVD (IDE) > Use ISO image file > 위에서 설치한 iso 찾기 

16. play 하고 install CentOS  선택하고 엔터 선택

17. 언어 선택 후, 계속 진행

18. 키보드 >  영어 (미국) 추가, 영어를 한국어보다 위로 올라가게 해서 default 로 설정 > 완료

19. 소프트웨어 선택 > 개발 및 창조를 위한 워크스테이션 ( 서버에 관련된 기능까지 추가 ) > 완료

20. 네트워크 및 호스트 이름 > 오른 쪽 끔 을 선택해서 켬으로 바꾸기 > 아이피주소 안뜨고 연결 중. .. 

21. 설치대상 > hd 두번 클릭 > 밑에 파티션을 설정합니다 체크

22. 표준 파티션 > + > swap , 2G >마운트 지점 추가 >  + > / ,  > 마운트지점 추가 > swap 메모리와 / 메모리를 합치면 총 hd 메모리 ( c , d 드라이브를 나눈 느낌)

23. 설치 실행 > Root 암호는 password 로 설정

24. 사용자 생성 > centos, centos 입력 > 완료

25. 라이센스 정보 > 동의 > 설정 완료

26. kdump (error  log 남기는가) > 활성화 해제 (메모리, 속도 먹으니까)

27. 이제 시작 화면, 목록에 없습니까? 클릭 > root, password 입력

28. 언어 목록에서 한국어 두개면 밑에 한국어 지우고 다음

29. 업그레이드 자동 처리 지우기 (책)

30. firefox 웹 브라우저 업그레이드 필요

    ```
    [root@localhost yum.repos.d]# ifconfig
    eno16777736: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
            inet 192.168.111.100  netmask 255.255.255.0  
    ```

    -> ip 는 시간마다 달라질 수 있음 -> 고정 등록 필요 (나중에 hadoop 시 네트워크 연결에 수고스러움이 생길 수 있으니까)

    ```
    [root@localhost yum.repos.d]# cd /etc/sysconfig/network-scripts/
    [root@localhost network-scripts]# gedit ifcfg-eno16777736
    
    [root@localhost network-scripts]# cat /etc/redhat-release
    CentOS Linux release 7.0.1406 (Core) 
    ```

    -> dhcp 방식을 none 으로 바꿈 (책 117p)

    > dhcp? 네트워크 안에 컴퓨터에 자동으로 네임 서버 주소, IP주소, 게이트웨이 주소를 할당해주는 것, 해당 클라이언트에게 일정 기간 임대를 하는 동적 주소 할당 프로토콜

    IPADDR=192.168.111.100  // ip 주소
    NETMASK=255.255.255.0 // 이것도 그룹
    GATEWAY=192.168.111.2 // 출력이 나가는 곳
    DNS1=192.168.111.2 // 도메인 관련

### Linux 명령어

**/root** : root 계정 관련 저장 디렉토리

**/home/사용자계정이름/** : 해당 계정 관련 저장 디렉토리

**/etc/xxxx** : 설정파일 저장 디렉토리

**/usr/...** : 모든 계정 사용 디렉토리

**shutdown -P now**ㅊㅁ : 컴퓨터 종료 (= halt -p, init 0, poweroff)

**shutdown -P +10** : 10분 후에 컴퓨터 종료

**shutdown -r now** : 컴퓨터 리부팅 (=reboot, init 6) 

**gedit** : 파일 내용 수정 가능

**window + space** : 터미널에서 한/영 전환

**man** : 각 명령어의 도움말을 자세히 보여주는 명령어

**cat filename1 filename2 > filename3**  : filename3 의 내용 삭제하고 filename1 filename2 를 합쳐서 저장

**cat filename1 filename2 >> filename3**  : filename3 의 내용 유지하고 filename1 filename2 를 합쳐서 저장

file 파일이름.확장자 : 그 파일에 대한 정보 출력

**ls -l** : 했을 때 나오는 , -rw-r--r--. 의 뜻은? 

처음 rw- (root 계정, 읽기 쓰기 가능) , 다음 r-- (root 계정과 같은 그룹 사용자, 읽기만 가능), r-- (다른 사용자 그룹, 읽기만 가능), rwx (읽기, 쓰기, 실행 권한)

r : read, w : write, x: exectue (실행 권한)

**chmod xxx filename** : 파일의 허가권을 변경하는 명령어 ( root 혹은 해당 파일 소유자만 실행 가능 ) -> 첫번째 숫자는 root, 두번째 숫자는 root계정 같은 그룹, 세번째 숫자는 다른 사용자

**whoami** : 현재 접속 계정 이름 출력

**su - username** : username으로 사용자 계정 변경

**exit** : 현재 계정을 나가고, root 계정으로 돌아감

**gedit /etc/passwd** : 사용자 목록

**gedit /etc/shadow** : 사용자의 인코딩된 암호 목록

**gedit /etc/group** : 그룹 목록

사용자 생성 / 그룹 변경 -> root 계정만 가능

**useradd newusername**: newusername 이라는 이름의 계정 생성

**usermod -g groupname newusername** : newusername 이름의 계정의 그룹을 groupname 으로 변경 

**passwd newusername** : newusername 계정의 암호 변경

**userdel -r newusername** :  newusrename 계정 삭제 및 홈 디렉터리까지 삭제 (디렉토리가 남아있으면, 나중에 똑같은 이름의 계정 만들때 오류가 생기니까 디렉토리까지 정리해주면 좋음)

**groupdel newusername** :  newusrename 을 추가하면서 생긴 그룹을 삭제

**yum** : 다운로드 + 설치 (강력한 설치도구)

yum -y install system-config-users

**system-config-users** : 사용자 계정 관련 gui 제공

**echo** : 출력

**echo 'content' >> filename** : filename 에 'content' 를 추가 ( 부등호 1개 이면 내용 지우고 입력)

**/root/filename.확장자** : 'x' (실행 권한) 이 있는 파일인 경우에 이렇게 파일 디렉토리랑, 파일명 입력해주면 파일 안에 있는 명령어가 실행

**chown** : 파일 소유자 변경 (root 만 실행 가능)

**chgrp** : 파일 소유 그룹 변경

권한이 없는 디렉토리 안에 소유 권한이 있는 파일이 있는 경우 -> 권한있는 사용자 계정에 접속해서 그 파일 다른 디렉토리로 옮기기

**ls -il** : 맨 앞에 inode의 값이 출력 (inode는 파일이 생성될 때 파일의 정보들이 저장되는 파일의 식별자 같은 것)

**ln 원본파일 링크이름** : 하드 링크 파일

**rpm** : 설치 프로그램 조회, 미설치 프로그램 정보 조회

파일 위치 찾기 

**which** : 환경변수 등록 경로상 파일명 

**whereis** 

**find**: 조건 없이 찾는 것 ex) find / -name CentOS*

**rpm -qa**  : rpm파일명(rpm으로 설치한)

rmp 파일들 -> 패키지

 cd /run/media/root/CentOS\ 7\ x86_64/Packages/ 에 rpm 파일들 존재

rpm -Uvh mc-4.8.7-8.el7.x86_64.rpm

rpm 을 통해 mc 파일 설치하고 mc 명령어 실행하면 파일 관리자 화면 출력

rpm -e mc : 설치한 rpm 파일 제거



< 설정 >

**/etc/sysconf/network-scripts/ifcfg-enoxxxx**

: 네트워크, 맥어드레스, ip, g/w, dns 저장 파일

**systemctl restart network** : 설정 사항 바뀐 후에 변경된 내용을 시스템에 적용시키기 위해 네트워크 재시작

**/etc/resolve.conf **: dns 수정 추가 (네크워크 재시작 필요 없음 ), dns ip 수정을 해주면 네트워크가 불가능 해진다. 그 이유는 이미 설정된 ip 주소이기 때문이다.

**/etc/sysconfig/hostname** : 도메인 수정

**/etc/sysconfig/network** :네트워크 기본 정보, 파일 별 네트워크 사용여부 저장

**/etc/sysconfig/network-scripts/ifcfg-eno16777736** : centOS 환경, 네트워크이름, mac addr, ip addr, g/w, dns 설정 및 정보 저장된 파일 이 환경 파일 수정 후에 적용을 위해서는 systemctl restart network를 수행시켜주어야 한다.

**/etc/profile** : (부팅 - root 접속 ) 시에 실행되는 파일, 설정 변경 후에 적용 위해 source /etc/profile 을 실행해야 적용된다.

**grep** : 파일에서 틍정 문자열 검색

ex ) ps -ef | grep bash

**sort < smple.txt** : sample 파일 내용을 줄마다 sort 함

**gedit /etc/passwd &** : gedit 가 열려 있어도, CLI 동작도 가능

**jobs** : 그 터미널과 관련된 background process 를 볼 수 있음

```
[root@localhost ~]# gedit /etc/passwd &
[1] 2855
[root@localhost ~]# kill 2855
[1]+  종료됨               gedit /etc/passwd
```

**nslookup www/daum.net** :

vm ware -> vm ware (ex window) : gateway 를 통해 나감

window -> www.daum.net  의 과정



## 환경설정 (linux 환경에서)

> firefox 72 + jdk 1.8 + tomcat 9

### tomcat 설치

firefox 업그레이드 & tomcat 설치

```cmd
다운로드# tar xvf firefox-72.0.2.tar.bz2
[root@localhost 다운로드]# mv firefox /usr/local
[root@localhost 다운로드]# ls -l /usr/local
// firefox 와 jdk 가 있는 걸 볼 수 있음

[root@localhost 다운로드]# ln -s /usr/local/firefox/firefox /usr/local/bin
// firefox 파일을 bin 폴더에 링크 만들기
// 다른 사용자가 원본 파일을 접근하지 못하게 하기 위해

실행
/root/다운로드/firefox/firefox
[root@localhost ~]# firefox -version
Mozilla Firefox 72.0.2

[root@localhost ~]# mv /root/다운로드/tomcat9/ /usr/local
[root@localhost ~]# ls -l /usr/local
[root@localhost ~]# /usr/local/tomcat9/bin/startup.sh
Using CATALINA_BASE:   /usr/local/tomcat9
Using CATALINA_HOME:   /usr/local/tomcat9
Using CATALINA_TMPDIR: /usr/local/tomcat9/temp
Using JRE_HOME:        /usr/local/jdk1.8
Using CLASSPATH:       /usr/local/tomcat9/bin/bootstrap.jar:/usr/local/tomcat9/bin/tomcat-juli.jar
Tomcat started.
```

리눅스에서 톰캣을 실행하고, 윈도우에서 ip 로 접속하고자 했으나 안된다 -> 이는 방화벽 때문에 접근하지 못하는 것

/usr/local/tomcat9/bin/ 시작 - 종료 명령어

/usr/local/tomcat9/conf 설정파일.xml

/usr/local/tomcat9/lib/*.jar [+ ojdbc6.jar]

/usr/local/tomcat9/webapps/웹프로젝트폴더

1. tomcat start 

2. linux(localhost) - access 

3. oracle : db server(1521) + web server(8080) 동시 w자동 설치 -> web server port 와 tomcat server 의 port 가 8080으로 충돌하므로, tomcat server 의 port 를 변경할 것 : /usr/local/tomcat9/conf/server.xml 파일에 있는 내용을 수정하면 가능 

4. host os access (windows) -> 접근 불가 -> linux 의 방화벽이 막혀있기 때문 그래서 외부 접속이 불가능 -> 방화벽 기본 설정 해제는 불필요한 것 까지 열어서 보안이 위험하므로 4번에서 port 를 9090으로 변경하고, 저 9090 port(tomcat server 를 열어둔) 번호만 방화벽을 해제

   firewall-config : gui 로 방화벽 설정

   firewall-cmd : cli 로 방화벽 설정

   http://192.168.111.100:9090/

   http://localhost:9090/

   둘 다 접근 가능

   ```cmd
   # systemctl status firewalld
   // active 상태 확인
   # firewall-config
   // gui 설정 영구적 으로 바꾸고, 포트에 9090, tcp 추가
   // 설정 후에 옵션 > firewalld 다시 불러오기로 적용
   ```

### linux jdk 설치

> 리눅스 설치 시에 openjdb 1.7 버전이 내장
>
> 내장된 jdk 삭제 후, 새로운 버전 설치
>
> 후에 환경변수 path 등록

```cmd
# tar xfvz jdk-8u241-linux-x64.tar.gz

# mv jdk1.8.0_241 /usr/local/jdk1.8

[root@localhost etc]# cd /usr/local/jdk1.8/
[root@localhost jdk1.8]# cd bin
[root@localhost bin]# gedit /etc/profile

export JAVA_HOME=/usr/local/jdk1.8
export PATH=$PATH:$JAVA_HOME/bin
export JAVA_OPTS="-Dfile.encoding=UTF-8"
export CLASSPATH="."

[root@localhost bin]# source /etc/profile
```

### Oracle 설치 (책 571page)

oracle 사이트 > oracle database 11g standard edition > oracle database express edition 11g linux 64

#### 프로세스

데몬 서비스 : 항상 실행중인 서비스

소켓 : 필요 실행 서비스

### 서버 운영

window -> linux 옮겨가기 : deployee

* window

1. dynamic web project

   webcontetnt 폴더 속 jsp 파일

   linux는 구조가 다를 수 있다.

   -> eclipse 로 web dynamic project 만들어서 webcontent 폴더에 jsp 파일 만들고 tomcat9 구조 맞추어서 압축 project>export> war file 로 export

   destination 설정 > 밑에 두개 check (옵션)

   이제 설정한 경로에 war 파일 생성

   이 war 파일을 리눅스의 다운로드 폴더에 복사, 붙여넣기

   후에 linux 터미널 환경에서 파일 usr/local/tomcat9/webapps/ 로 위치 변경

   ```cmd
   // tomcat 이 실행중일 때, 옮겨줘야 자동으로 압축이 풀림
   # mv /root/다운로드/vm.war /usr/local/tomcat9/webapps/
   ```

   http://192.168.111.100:9090/vm/test.jsp 으로 접속하면 jsp 페이지가 보이는 것을 확인할 수 있음 : deployee 완성

   

2. spring mvc project

   spring mvc : war 파일 추출한 후, 후에 linux 터미널 환경에서 파일 usr/local/tomcat9/webapps/ 로 위치 변경

   -> 압축파일을 푼 폴더 이름이 context

   spirng 프로젝트 폴더를 war 로 export 하면 http://localhost:9090/spring/ 로 접속하면 hello world 페이지가 나타남

   ```cmd
   [root@localhost 다운로드]# unzip oracle*
   [root@localhost 다운로드]# cd Disk1/
   [root@localhost Disk1]# ls
   oracle-xe-11.2.0-1.0.x86_64.rpm  response  upgrade
   // 설치 정보 확인
   [root@localhost Disk1]# rpm -qip oracle-xe-11.2.0-1.0.x86_64.rpm
   
   ```

   하드디스크 파티션 설정 (vm 설정시)  : / (파일 저장) , swap (임시 메모리) 2GB

   => 기본적으로 swap 을 2GB 하는게 좋지만,  Oracle은 많이 차지하게 되므로 swap 공간을 늘려서(설치할 때만, 설치 시에 임시 공간이 많이 필요하니까) 

   ```cmd
   [root@localhost Disk1]# swapon -s
   Filename				Type		Size	UsedPriority
   /dev/sda1                              	partition	2047996	0	-1
   [root@localhost Disk1]# mkswap /swapfile
   [root@localhost Disk1]# swapon -s
   [root@localhost Disk1]# sudo swapon /swapfile
   [root@localhost Disk1]# yum -y localinstall oracle-xe-11.2.0-1.0.x86_64.rpm 
   [root@localhost Disk1]# rpm -qa | grep oracle
   oracle-xe-11.2.0-1.0.x86_64
   [root@localhost Disk1]# service oracle -xe configure
   Specify the HTTP port that will be used for Oracle Application Express [8080]:
   
   Specify a port that will be used for the database listener [1521]:
   // system 계정 비밀번호 설정
   initial configuration:
   
   // 데이터 베이스 시작
   [root@localhost Disk1]# /etc/init.d/oracle-xe start
   // systemctl 으로 사용 가능
   Starting oracle-xe (via systemctl):
   // u01/app/oracle/product/11.2.0/xe/bin 경로
   [root@localhost bin]# cat oracle_env.sh
   export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
   export ORACLE_SID=XE
   export NLS_LANG=`$ORACLE_HOME/bin/nls_lang.sh`
   export PATH=$ORACLE_HOME/bin:$PATH
   
   // 위에 실행했던 명령문 밑 파일에 맨 밑에  추가해줘서 리부팅 할 때마다 환경변수가 설정되도록 하기
   [root@localhost bin]# gedit /etc/bashrc
   
   // window 와의 연결이 가능하게 하기 위해서 오라클 포트 방화벽 해제
   [root@localhost bin]# firewall-config
   // 들어가서 옵션 영구적 으로 바꾸고 포트 8080, 1521 번호 추가
   
   ```

   후에 웹 브라우저 오라클 접속해서 로그인

   ```sql
   sql > conn hr/ hr
   
   sql> conn system/1234
   
   sql> alter user hr identified by hr account unlock;
   
   sql> conn hr/hr
   
   sql> create user scott identified by TIGER;
   
   sql> grant connect,resouce to scott;
   
   sql> conn scott/TIGER
   ```

   vmnet8 로 윈도우와 리눅스가 통신, 그곳에 있는 ipv4가 리눅스에서 접근할 수 있는 윈도우 ip

   /u01/app/oracle/product/11.2.0/xe/network/admin./tnsnames.ora 파일에서 window 관련 정보 추가

   win =
     (DESCRIPTION =
       (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.111.1)(PORT = 1521))
       (CONNECT_DATA =
         (SERVER = DEDICATED)
         (SERVICE_NAME = XE)
       )
     )

   설정 후 , 오라클 재시작

   ```cmd
   [root@localhost admin]# /etc/init.d/oracle-xe stop
   [root@localhost admin]# /etc/init.d/oracle-xe start
   
   // 윈도우 오라클에 있는 scott 계정 접속
   sql > SQL> conn scott/TIGER@win
   ```

   위 방법은, windows 에서 oracle 서비스 , linux 에서 tomcat -> tomcat이 windows oracle 에 접속

   windows 개발 -> linux 운영

# Hadoop

> hadoop : 컴퓨터 여러 개 연결 해서 빅 데이터 처리

java 기반으로 제공

