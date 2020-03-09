# Hadoop

> hadoop : 컴퓨터 여러 개 연결 해서 빅 데이터 처리
>
> HDFS : Hadoop Distributed File System

하둡 클러스터링 : Multi node 구성 = 빅데이터 분산 처리(저장) 시스템

# 환경 설정

1. 1개 컴퓨터(vm) : single node 운영 
2. 4개 컴퓨터(vm 4개) : multi clustering node 운영

## 새로운 vm / linux 설치

1. create new vm 클릭 > 이름 master > C:\hadoop\master 에 위치 > single file 선택

   2g/1/ 20GB/ NAT/ ISO

2. edit vm setting > memory 2GB 로 늘림 > cd/dvd에서 use iso image file 클릭 > centOS 이미지 파일 찾아서 선택 > ok > play > 기다리면 os 설치 자동

3. 언어 > 키보드 (영어:미국 추가) > 소프트웨어 선택 > 개발 및 창조를 위한 워크스테이션 > 설치 대상 > 디스크 그림 2번 클릭 > 파티션 설정 체크 > 표준 파티션으로 바꾸고 + 버튼 클릭, 마운트 지점 swap , 2G 입력, 또 + 버튼 클릭 , / , 확인 버튼 > 네트워크 호스트 > 끔을 켬으로 바꾸고 > ip 가 잡히게 될 것 (난 안되는데 이거 나중에 터미널로 설정 가능) > root 암호 password 설정 > 사용자 생성 hadoop/hadoop 으로 생성 > 설정 완료 > kdump 설정 해제 (이거 하면 log 가 생겨서 용량 차지) > 재부팅

4. root 로 접속 시스템 도구 . 소프트웨어 > 그래도 계속 클릭 > 상단에 소프트웨어 메뉴 누르고 > 최신 패키지만 끔 , 전용 패키지만 끔 , 스프트웨어 공급원 하지 않기

5. yum 업데이트 방지

   ```cmd
   [root@localhost ~]# gedit /etc/sysconfig/network-scripts/ifcfg-eno16777736
   
   // 설정파일 내용에서 수정
   BOOTPROTO=none
   ONBOOT=yes
   // 설정파일 내용에서 추가
   IPADDR=192.168.111.110
   NETMASK=255.255.255.0
   GATEWAY=192.168.111.2
   DNS1=192.168.111.2
   
   [root@localhost ~]# systemctl restart network
   // ifconfig 명령으로 eno16777736 inet 바뀐 거 확인
   
   [root@localhost ~]# gedit /etc/yum.repos.d/CentOS-Base.repo 
   
   // 중간 부분 주석 처리 or 삭제
   #released updates 
   #[updates]
   		.
   		.
   		.
   #gpgcheck=1
   #gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
   
   
   [root@localhost ~]# gedit /etc/yum.repos.d/CentOS-Sources.repo 
   
   // 중간 부분 주석 처리 or 삭제
   #released updates 
   #[updates-source]
   		.
   		.
   		.
   #enabled=0
   #gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
   
   
   // 백업 파일 만들어놓기
   [root@localhost ~]# mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.bak
   
   [root@localhost ~]# cd /etc/yum.repos.d/
   [root@localhost ~]# wget http://download.hanbit.co.kr/centos/7/CentOS-Base.repo
   [root@localhost ~]# rm -f *.repo~
   [root@localhost yum.repos.d]# yum clean allLoaded plugins: fastestmirror, langpacks
   Cleaning repos: base extras
   Cleaning up everything
   Cleaning up list of fastest mirrors
   
   ```

6. selinux 방지 설정

   ```cmd
   [root@localhost ~]# gedit /etc/sysconfig/selinux
   
   // 내용 수정
   SELINUX=disabled
   ```

## JDK 설치(root)

```cmd
// openjdk 삭제
[root@localhost ~]# rpm -qa | grep jdk
java-1.7.0-openjdk-headless-1.7.0.51-2.4.5.5.el7.x86_64
java-1.7.0-openjdk-1.7.0.51-2.4.5.5.el7.x86_64
[root@localhost ~]# yum remove java
	(생략)
Complete!
[root@localhost local]# mv /root/다운로드/jdk-8u241-linux-x64.tar.gz .
[root@localhost local]# tar xvfz jdk-8u241-linux-x64.tar.gz
// 심볼릭 링크 생성
[root@localhost local]# ln -s jdk1.8.0_241 jdk1.8
[root@localhost local]# gedit /etc/profile

// 설정 파일 내용추가
export JAVA_HOME=/usr/local/jdk1.8
export PATH=$JAVA_HOME/bin:$PATH

[root@localhost local]# source /etc/profile

```

## 방화벽 해제

```cmd
// 방화벽 상태 active 인지 확인
[root@localhost ~]# systemctl status firewalld

[root@localhost ~]# systemctl stop firewalld
// 다음번에도 영구적으로 자동으로 실시하지 않도록
[root@localhost ~]# systemctl disable firewalld
[root@localhost ~]# systemctl restart network
```

## Hadoop 설치

```cmd
// hadoop 계정으로 로그인
// software 설정 똑같이 하기~
// hadoop 파일 다운로드 폴더에 붙여넣기
[hadoop@localhost 다운로드]$ ls
hadoop-1.2.1.tar.gz

// root 계정
[root@localhost ~]# hostname
localhost.localdomain
// 4대가 다 localhost 를 쓰면 분별이 어려우므로 이름 바꿔주기
// master, slave1, slave2, slave3
[root@localhost ~]# hostnamectl set-hostname master
[root@localhost ~]# hostname
master

```

* C:\hadoop 폴더에서 master 폴더 3개 더 복사하기 (총 4개)

  ![image-20200224131411381](C:..\image\구조)

* vm ware 에서 open vm 해서 slave 들 열어서 vm settings 에서 options 에서 이름 바꾸고, 목록에 보이게 하기

* vm setting 에서 network adapter 에서 밑에 Advanced... 클릭 MAC address > Generate > vm 시작하고 알림창 나오면 I moved It 선택

  ```cmd
  [root@master ~]# gedit /etc/sysconfig/network-scripts/ifcfg-eno16777736
  
  // mac, ip addr 수정
  HWADDR="00:50:56:3C:DC:55"
  IPADDR=192.168.111.130
  
  [root@master ~]# hostname
  master
  [root@master ~]# hostnamectl set-hostname slave1
  [root@master ~]# systemctl restart network
  // 재부팅
  ```

  slave1 Mac addr : 00:50:56:3C:DC:55

  ​			 ip addr : 192.168.111.130

  slave2 Mac addr : 00:50:56:27:07:06

  ​			 ip addr :  192.168.111.140

  slave3 Mac addr : 00:50:56:21:CF:C4

  ​			ip addr : 192.168.111.150

  ```cmd
  [root@master ~]# gedit /etc/hosts
  
  // 내용 다 지우고 입력
  192.168.111.110 master
  192.168.111.130 slave1
  192.168.111.140 slave2
  192.168.111.150 slave3
  
  // master 에서 slave 들로 ping 날려보기
  [root@master ~]# ping slave3
  
  // 원격 접속 하기
  [root@master ~]# ssh slave1
  [root@slave1 ~]# who
  root     :0           2020-02-24 13:44 (:0)
  root     pts/0        2020-02-24 13:52 (:0)
  root     pts/1        2020-02-24 13:58 (master)
  // 원격 접속 끝내기
  [root@slave1 ~]# exit
  ```

  * 원격 접속시, 서로 암호 물어보지 않게 하기 : master 의 hadoop계정에서 ssh key 만들기 

  ```cmd
  [hadoop@master ~]$ ssh-keygen -t rsa 
  Generating public/private rsa key pair.
  Enter file in which to save the key (/home/hadoop/.ssh/id_rsa):  
  Created directory '/home/hadoop/.ssh'.
  Enter passphrase (empty for no passphrase): 
  Enter same passphrase again: 
  Your identification has been saved in /home/hadoop/.ssh/id_rsa.
  Your public key has been saved in /home/hadoop/.ssh/id_rsa.pub.
  The key fingerprint is:
  a7:f0:f6:f3:7e:12:73:05:9f:79:0c:27:28:da:4c:85 hadoop@master
  The key's randomart image is:
  +--[ RSA 2048]----+
  |           o..   |
  |          E . + .|
  |         = .   Bo|
  |        . o    o=|
  |      . S .    ..|
  |       o o  o .  |
  |        +    +   |
  |       . .. . .  |
  |          .+oo   |
  +-----------------+
  
  // 열쇠 slave들한테 복사해주기
  // 비밀번호는 hadoop
  [hadoop@master ~]$ ssh-copy-id -i /home/hadoop/.ssh/id_rsa hadoop@slave1
  [hadoop@master ~]$ ssh-copy-id -i /home/hadoop/.ssh/id_rsa hadoop@slave2
  [hadoop@master ~]$ ssh-copy-id -i /home/hadoop/.ssh/id_rsa hadoop@slave3
  ```

  ```cmd
  // 압축 풀기 (4개 다)
  [hadoop@master ~]$ mv 다운로드/hadoop-1.2.1.tar.gz .
  [hadoop@master ~]$ tar xvfz hadoop-1.2.1.tar.gz 
  
  [hadoop@master ~]$ cd hadoop-1.2.1/
  [hadoop@master hadoop-1.2.1]$ cd conf
  
  [hadoop@master conf]$ gedit hadoop-env.sh
  // 내용 주석 해제 후 수정
  xport JAVA_HOME=/usr/local/jdk1.8
  
  [hadoop@master conf]$ gedit masters
  // 내용 다 지우고 입력
  // 
  slave1
  
  [hadoop@master conf]$ gedit slaves
  // 내용 다 지우고 입력
  slave1
  slave2
  slave3
  
  [hadoop@master conf]$ mkdir /home/hadoop/hadoop data
  
  [hadoop@master ~]$ cd hadoop-1.2.1/
  [hadoop@master hadoop-1.2.1]$ cd conf
  [hadoop@master conf]$ gedit core site.xml
  [hadoop@master conf]$ gedit hdfs-site.xml
  [hadoop@master conf]$ gedit mapred-site.xml
  
  gedit hadoop-1.2.1/conf/core-site.xml
  
  	<configuration>
    	  <property>
      	    <name>fs.default.name</name>
              <value>hdfs://master:9000</value>
            </property>
            <property>
              <name>hadoop.tmp.dir</name>
              <value>/home/hadoop/hadoop-data/</value>
            </property>
  </configuration>
  
  
  
  gedit hadoop-1.2.1/conf/hdfs-site.xml
  
  	<configuration>
    	  <property>
      	    <name>dfs.replication</name>
       	    <value>3</value>
    	  </property>
    	  <property>
      	    <name>dfs.http.address</name>
      	    <value>master:50070</value>
    	  </property>
    	  <property>
      	    <name>dfs.secondary.http.address</name>
      	    <value>slave1:50090</value>
    	  </property>
  </configuration>
  
  
  
  
  gedit hadoop-1.2.1/conf/mapred-site.xml
   	<configuration>
    	  <property>
     	    <name>mapred.job.tracker</name>
     	    <value>master:9001</value>
    	  </property>
  </configuration>
  
  
  // 원격 장소로 파일 복사
  [hadoop@master conf]$ scp hadoop env.sh hadoop@slave1:/home/hadoop/hadoop_1.2.1/conf
  [hadoop@master conf]$ scp *-site.xml hadoop@slave1:/home/hadoop/hadoop-1.2.1/conf
  
  // /etc/profile 은 전체 계정에 영향
  // .bash_profile 은 그 계정만 영향
  [hadoop@master conf]$ gedit /home/hadoop/.bash_profile
  // 내용 추가
  export PATH
  export HADOOP_HOME=/home/hadoop/hadoop-1.2.1
  export HADOOP_HOME_WARN_SURPRESS="TRUE"
  PATH=$HADOOP_HOME/bin:$PATH
  [hadoop@master conf]$ source /home/hadoop/.bash_profile
  
  // hadoop 저장소 초기화
  [hadoop@master conf]$ hadoop namenode -format
  20/02/24 15:36:40 INFO common.Storage: Storage directory /home/hadoop/hadoop-data/dfs/name has been successfully formatted.
  
  // hadoop 시작
  [hadoop@master conf]$ start-all.sh
  Warning: $HADOOP_HOME is deprecated.
  
  starting namenode, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-namenode-master
  \
  .out
  slave1: starting datanode, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-datanode-slave1.out
  slave3: starting datanode, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-datanode-slave3.out
  slave2: starting datanode, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-datanode-slave2.out
  slave1: starting secondarynamenode, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-secondarynamenode-slave1.out
  starting jobtracker, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-jobtracker-master.out
  slave1: starting tasktracker, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-tasktracker-slave1.out
  slave2: starting tasktracker, lgging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-tasktracker-slave2.out
  slave3: starting tasktracker, logging to /home/hadoop/hadoop-1.2.1/libexec/../logs/hadoop-hadoop-tasktracker-slave3.out
  
  // 자바로 실행되고 있는 프로세스 확인
  [hadoop@master conf]$ jps
  45538 JobTracker
  45364 NameNode
  45672 Jps
  
  // slave1
  [hadoop@slave1 ~]$ jps
  45720 Jps
  45369 DataNode
  45577 TaskTracker
  45455 SecondaryNameNode
  
  // slave2, slave3
  [hadoop@slave2 ~]$ jps
  45344 DataNode
  45612 Jps
  45469 TaskTracker
  
  == 하둡 시작 ==
  -- master에서 --
  hadoop namenode -format
  start-all.sh
  
  == 하둡 종료 ==
  -- master 에서 --
  stop-all.sh
  
  ```

  

moon9342@gmail.com