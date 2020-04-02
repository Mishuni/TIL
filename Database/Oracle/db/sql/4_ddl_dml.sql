######################################################
Table 생성   DDL  
(auto commit) -> 취소가 안됨을 의미, 수행하면 동기화 되어 볼 수 있음

테이블은 실제로 데이터들이 저장되는 곳 이라고 생각하면 쉽게 이해 할 수 있으며, 
CREATE TABLE 명령어를 이용해서 테이블을 생성 할 수 있다. 
######################################################

데이터타입설명
	VARCHAR2 타입
	- 가변길이 문자형 데이터 타입 
	- 최대 길이 : 2000 바이트(반드시 길이 지정) 
	- 다른 타입에 비해 제한이 적다 
	- 일부만 입력시 뒷부분은 NULL 
	- 입력한 값의 뒷부분에 있는 BLANK도 같이 입력 
	- 전혀 값을 주지 않으면 NULL 상태 입력 
	- 지정된 길이보다 길면 입력시 에러 발생 
	- 컬럼 길이의 편차가 심한 경우, NULL 로 입력되는 경우가 많은 경우 VARCHAR2 사용 
	- 그렇지 않은 가변형은 VARCHAR
	- CHAR : 고정길이 문자형 데이터 타입

	NUMBER 타입
	- 숫자형 데이타 타입, 음수, ZERO, 양수 저장 
	- 전체 자리수는 38자리를 넘을 수 없다 
	- 소수점이 지정되지 않았을 때 소수점이 입력되거나, 지정된 소수점자리 이상 입력되면 반올림되어 저장 
	- 지정한 정수 자리수 이상 입력시 에러 발생 
	- 연산이 필요한 컬럼은 NUMBER타입으로 지정한다. 
	- NUMBER(p,s) 로 지정시 p는 s의 자리수를 포함한 길이므로 감안하여 P의 자리수를 결정 
	- NUMBER 타입은 항상 가변길이므로 충분하게 지정할 것 

	DATE 타입
	- 일자와 시간을 저장하는 날짜형 타입 
	- 일자나 시간의 연산이 빈번한 경우 사용 
	- 포함정보 : 세기, 년도, 월, 일, 시간, 분, 초 
	- NLS_DATE_FORMAT을 이용하여 국가별 특수성을 해결 
	- 특별히 시간을 지정하지 않으면 00:00:00로 입력 됨 
	- 특별히 일자를 지정하지 않았다면 현재월의 1일로 지정 됨 
	- SYSDATE는 현재일과 시간을 제공 

######################################################
-- DDL : auto commit
-- 테이블 삭제
drop table book;
-- 테이블 생성
create table book(
	bookno number(5),
	title varchar2(40),
	author varchar2(20),
	pubdate date
);

-- DML : auto commit 이 아님
-- insert, delete, update
insert into book (bookno,title,author,pubdate)
values(1,'java','홍길동',sysdate);

select * from book;
-- 이 쿼리를 수행한 곳에서는 추가가 되어서 나타나지만
-- 다른 client 는 확인 하지 못함
-- commit 을 날려야 함

commit;
-- commit이 날라가야 다른 client에서도 이를 확인할 수 있음(동기화)
-- commit 전에는 rollback(취소)가 가능
-- commit을 날리면 취소가 불가능
-- eclipse는 모든 명령문에  Auto commit으로 자동설정 되어 있음

insert into book (bookno,title,author,pubdate)
values(2,'sql',null, null);

rollback;
-- 바로 이전 commit 후의 명령들이 취소
-- commit 과 rollback 의 사이

insert into book (bookno,title,author,pubdate)
values(2,'sql',null, '2019/01/01');

insert into book (bookno,title,author,pubdate)
values(3,'spring','고길동', to_date('01/01/2019','dd/mm/yyyy'));

commit;

-- 나머지는 null로 들어감
insert into book (bookno,title) values(4,'html');
insert into book values(5,'python','jecy', sysdate);
commit;
-- 아래 쿼리로 인해 pk 가 없는 테이블이 됨
insert into book values(1,'java','홍길동',sysdate);

-- 모든 열 삭제
delete from book;
rollback;
-- 특정 열 삭제
delete from book where bookno=4;
delete from book where bookno=1;
rollback;

update book set title = '~~~~~' where bookno=3;
update book set title = 'hadoop' ,author ='kim' where bookno=1;
update book set title = 'html5' where title = '~~~~~';
-- 이 경우에 pk 값이 없기 때문에 원하는  제목을 수 많은 데이터에서 찾아야 하므로 비효율적(full scan)
-- pk 설정을 하면, index가 만들어지니까 search 속도, 성능이 매우 향상
-- 회원가입 때, 내 아이디 및 비번이 그 회사 db에 insert 됨
-- 그 때, id 중복 체크 -> id 가 PK가 될 수 있음

-- 테이블구조
desc book;

-- 수정은 alter (DDL: auto commit)
-- column 추가
alter table book add(price number(7));
update book set price = 0;
update book set price = 99.99; 
-- 값이 반올림 되어 100으로 저장되어 짐
update book set price = null;
commit;
-- column 수정
alter table book modify(price number(7,2));
-- number를 소숫점 2번째 자리 까지 저장
update book set price = 99.99; 
commit; -- 99,99 로 잘 저장되어 있는 것을 볼 수 있음
-- column 삭제
alter table book drop column price;

-- 테이블 이름 변경
rename book to book2;
rename book2 to book;

delete from book; 
-- rollback 가능
rollback;

-- 구조는 남아있고 테이블 속 데이터만 지우기
truncate table book; 
-- auto commit (복구 불가)

-- 테이블 자체를 지우기
drop table book;

-- clone
-- PK, FK가 있고 없음의 차이
create table emp2 as select * from emp; 
create table dept2 as select * from dept; 
select * from emp2;
select * from dept2;
-- 디벨로퍼의 모델 그림을 보면 fk pk 가 없음

insert into dept (deptno,dname,loc) values(10,'EDU','SEOUL');
-- 위 쿼리는 제약조건 때문에 에러 발생 (pk 중복)
insert into dept2 (deptno,dname,loc) values(10,'EDU','SEOUL');
-- 위 쿼리는 에러 없이 수행

-- 50은 원래도 없었으니까 둘다 수행 가능
insert into dept (deptno,dname,loc) values(50,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(50,'EDU','SEOUL');
commit;

insert into dept (deptno,dname,loc) values(null,'EDU','SEOUL');
-- 위 쿼리는 제약조건 때문에 에러 발생 (pk는  null 불가)
insert into dept2 (deptno,dname,loc) values(null,'EDU','SEOUL');
-- 위 쿼리는 에러 없이 수행

insert into emp(empno, ename, sal, deptno)
-- 위 쿼리는 에러 발생 (fk에 입력한 값(90)이 참조 테이블에 존재하지 않음으로)
values(999,'한예슬',2100,90);

insert into emp2(empno, ename, sal, deptno)
values(999,'한예슬',2100,90);
-- emp2는 fk가 없으므로 가능

insert into emp(empno, ename, sal, deptno)
values(999,'한예슬',2100,50);
commit;

-- emp에서 홍 사원의 근무부서와 근무지 출력
select ename,dname , loc
from emp, dept
where emp.deptno = dept.deptno
	and emp.ename ='한예슬';
-- 결과
-- ENAME DNAME LOC
-- 한예슬     EDU   SEOUL

select ename,dname , loc
from emp2, dept2
where emp2.deptno = dept2.deptno
	and emp2.ename ='한예슬';
	
select ename,dname , loc
from emp2, dept
where emp2.deptno = dept.deptno
	and emp2.ename ='한예슬';
-- 결과
-- no rows selected
-- 한예슬의 deptno 는 90이고, dept2와 dept에서 deptno=90은 존재 x
-- DB 설계의 중요성을 알 수 있음

###################################################

-- 제약 조건 설정 --
-- primary key 는 null 값 비허용
-- unique는 null 값 허용
drop table book;
-- 테이블 생성 (기본키 설정, 제약 조건 설정)
create table book(
	bookno number(5) primary key,
	title varchar2(40) unique,
	author varchar2(20),
	price number(7,2) check(price>=0),
	pubdate date default sysdate
);

-- DML : auto commit 이 아님
-- insert, delete, update
insert into book (bookno,title,author,pubdate)
values(1,'java','홍길동',sysdate);

select * from book;
-- 이 쿼리를 수행한 곳에서는 추가가 되어서 나타나지만
-- 다른 client 는 확인 하지 못함
-- commit 을 날려야 함

commit;
-- commit이 날라가야 다른 client에서도 이를 확인할 수 있음(동기화)
-- commit 전에는 rollback(취소)가 가능
-- commit을 날리면 취소가 불가능
-- eclipse는 모든 명령문에  Auto commit으로 자동설정 되어 있음

insert into book (bookno,title,author,pubdate)
values(2,'sql',null, null);

rollback;
-- 바로 이전 commit 후의 명령들이 취소
-- commit 과 rollback 의 사이

insert into book (bookno,title,author,price)
values(a,'java','홍길둥',-900);

insert into book (bookno,title,author,pubdate)
values(3,'spring','고길동', to_date('01/01/2019','dd/mm/yyyy'));

commit;
###################################################
-- 제약 조건 추가해보기 
drop table book;
-- 테이블 생성 (기본키 설정, 제약 조건 설정)
create table book(
	bookno number(5) ,
	title varchar2(40) unique,
	author varchar2(20),
	price number(7,2) check(price>=0),
	pubdate date default sysdate
);
-- 제약조건 추가
alter table book 
add CONSTRAINT book_pk primary key(bookno);
-- 제약조건 삭제
alter table book drop CONSTRAINT book_pk;

insert into book (bookno,title,author,price)
values(1,'sql','홍길둥',-900);
insert into book (bookno,title,author,price)
values(1,'sql','홍길둥',2900);

###################################################
drop table emp2;
drop table dept2;

create table emp2 as select * from emp; 
create table dept2 as select * from dept; 

-- 제약조건 추가
alter table emp2 
add CONSTRAINT emp2_pk primary key(empno);
alter table dept2 
add CONSTRAINT dept2_pk primary key(deptno);

alter table emp2
add CONSTRAINT emp2_fk_deptno 
foreign key(deptno) references dept2;

alter table emp2
add CONSTRAINT emp2_fk_mgr
foreign key(mgr) references emp2;
-- 위 쿼리는 self join

-- 제약조건 삭제
alter table dept2 drop CONSTRAINT dept2_pk;


###################################################
scott.sql 분석

CREATE TABLE DEPT
       (DEPTNO NUMBER(2) CONSTRAINT PK_DEPT PRIMARY KEY,
	DNAME VARCHAR2(14) ,
	LOC VARCHAR2(13) ) ;
	
CREATE TABLE EMP
       (EMPNO NUMBER(4) CONSTRAINT PK_EMP PRIMARY KEY,
	ENAME VARCHAR2(10),
	JOB VARCHAR2(9),
	MGR NUMBER(4),
	HIREDATE DATE,
	SAL NUMBER(7,2),
	COMM NUMBER(7,2),
	DEPTNO NUMBER(2) CONSTRAINT FK_DEPTNO REFERENCES DEPT);










