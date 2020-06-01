# Docker

> 컨테이너 기반 가상화 도구

* 컨테이너는 하드웨어를 소프트웨어로 재구현하는 가상화(= 가상머신)와는 달리 프로세스의 실행 환경을 격리

* 테이너가 실행되고 있는 호스트 입장에서 컨테이너는 단순히 프로세스에 불과합니다만, 사용자나 컨테이너 입장에서는 호스트와는 무관하게 동작하는 가상머신처럼 동작
* 도커는 가상머신과 같이 하드웨어를 가상화하는 것이 아니라, 리눅스 운영체제에서 지원하는 다양한 기능을 사용해 컨테이너(하나의 프로세스)를 실행하기 위한 별도의 환경(파일 시스템)을 준비하고, 리눅스 네임스페이스와 다양한 커널 기능을 조합해 프로세스를 특별하게 실행
* 예로, ios os 와 android os 에 맞는 application 을 따로 만들던 환경에서, 한가지 app 만 개발해도 두 os 에 모두 돌아갈 수 있도록 하는 것과 비슷한 개념

### Docker Image

> 이미지는 미리 구성된 환경을 저장해 놓은 파일들의 집합, 컨테이너는 이 파일들의 집합 위에서 실행된 결리된 프로세스

* 이미지는 불변이지만, 그 대신 도커에서는 이 이미지 위에 무언가를 더해서 새로운 이미지를 만들어내는 일이 가능하다. 이미지를 기반으로 만들어진 컨테이너는 변경 가능Mutable하기 때문이다.

* 도커는 계층화된 파일 시스템을 사용한다. 즉, 특정한 이미지로부터 생성된 컨테이너에 어떤 변경사항을 더하고(파일들을 변경하고), 이 변경된 상태를 새로운 이미지로 만들어내는 것이 가능하다.



## Tutorial of Docker

### install

```cmd
root@user:~#apt install curl
// docker 최신 버전 설치 (19.03.8)
$ curl -s https://get.docker.com | sudo sh
```

### basic of Docker

```cmd
// 현재 실행중인 컨테이너 목록 출력
$ sudo docker ps
// sudo 를 붙일 필요 없도록 사용자에도 권한 부여
$ sudo usermod -aG docker $USER
$ sudo su - $USER
// 죽은 컨테이너의 목록까지 출력
$ docker ps -a
// docker 에 받아진 image 목록 출력
$ docker images
```

### Implementation

```cmd
// docker 로 centOS image 받아오기
$ docker pull centos
Using default tag: latest
latest: Pulling from library/centos
8a29a15cefae: Pull complete
Digest: sha256:fe8d824220415eed5477b63addf40fb06c3b049404242b31982106ac204f6700
Status: Downloaded newer image for centos:latest
docker.io/library/centos:latest

// image 목록에 centos 가 추가된 것을 확인 할 수 있음
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
centos              latest              470671670cac        8 weeks ago         237MB
// centos run
$ docker run -it centos:latest bash
// docker contatiner 삭제
$ docker rm (container id | names)
// docker image 삭제
$ docker rmi (image)
```

### Docker life cycle

```cmd
$ docker run -it ubuntu:bionic bash
root@65d60d3dd306:/# apt update
root@65d60d3dd306:/# apt install -y git
root@65d60d3dd306:/# git --version
git version 2.17.1

$ docker diff 65d60d3dd306 | head
$ docker run -it --rm ubuntu:bionic bash
root@33f6039322df:/# git --version
bash: git: command not found
root@33f6039322df:/# exit

$ docker commit 33f6039322df ubuntu:git
sha256:12924460218feb38da74e9a64c95acd55d16297346b2698f47f396936636c93d
$ REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              git                 12924460218f        4 seconds ago       186MB
ubuntu              bionic              4e5021d210f6        8 hours ago         64.2MB

$ docker run -i -t ubuntu:git bash
root@2a00b9b2b7cc:/# git --version
git version 2.17.1
root@2a00b9b2b7cc:/# exit

// image, contatiner 삭제
$ docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                        PORTS               NAMES
2a00b9b2b7cc        ubuntu:git          "bash"              2 minutes ago       Exited (130) 3 seconds ago                        cranky_franklin
$ docker rm 2a00b9b2b7cc
2a00b9b2b7cc
$ docker rmi ubuntu:git
```

### Create Image using Dockerfile 

```cmd
$ docker build -t <image name>:<version> .
```

### Docker Hub

```cmd
// docker hub 사용을 위한 로그인
$ docker login
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username: <DOCKER_HUB_ID>
Password: <PASSWORD>
WARNING! Your password will be stored unencrypted in /home/vagrant/.docker/config.json.
Configure a credential helper to remove this warning. See
https://docs.docker.com/engine/reference/commandline/login/#credentials-store

Login Succeeded
```



## 마이크로 서비스 (Microservice)

> 소프트웨어 시스템을 개발하고 구성하는 방법 중 하나로 네트워크를 통해 통신하는 작고, 독립된 구성 요소들로 만들어진 것

* 좀 더 많은 RAM 과 CPU 를 가진 대형 머신을 이용하여 더 많은 요청을 처리하는 규모 키우기 (scale up) 에 치중된 모노리틱(monolithic) 소프트웨어 개발 방법과 달리, 여러 대의 머신을 생성하여 생성된 여러 대의 머신으로 부하를 분산시켜서 처리하는 방식인 규모 확장(scale out)에 맞추어 설계된 방법
* 마이크로서비스 아키텍쳐에서는 특정 서비스에서 필요한 자원들만 규모를 확장할 수 있고, 시스템의 병목에만 집중하면 된다.
* 컨테이너의 가볍고 빠르다는 특성이 마이크로서비스 아케텍쳐에서 매우 적합하다. VM에 비해 컨테이너는 경량이고 빠르게 배포할 수 있어서 마이크로서비스 아키텍처를 통해 최소한의 자원만 사용할 수 있고, 필요에 따른 변경도 재빨리 대응 할 수 있다. 단, 수백 개의 서비스들로 구성된 시스템에서 복잡도가 상대적으로 증가할 수 있다.
* 마이크로서비스에는 독립적인 프로세스들을 유기적으로 동작할 수 있게 하는 분산 네트워크 시스템이 필요하다. 네트워크 레벨에서 다양한 상황별로 처리해야 하므로 구현하기 어렵다.

## 참고

[도커(Docker) 입문편컨테이너 기초부터 서버 배포까지](https://www.44bits.io/ko/post/easy-deploy-with-docker)

-> 현재 , centos 받기 까지 함

제대로 배우는 도커: 도커와 컨테이너 에코시스템 활용 해법 [아드리안 모트]