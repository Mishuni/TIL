######################################################
Table ����   DDL  
(auto commit) -> ��Ұ� �ȵ��� �ǹ�, �����ϸ� ����ȭ �Ǿ� �� �� ����

���̺��� ������ �����͵��� ����Ǵ� �� �̶�� �����ϸ� ���� ���� �� �� ������, 
CREATE TABLE ��ɾ �̿��ؼ� ���̺��� ���� �� �� �ִ�. 
######################################################

������Ÿ�Լ���
	VARCHAR2 Ÿ��
	- �������� ������ ������ Ÿ�� 
	- �ִ� ���� : 2000 ����Ʈ(�ݵ�� ���� ����) 
	- �ٸ� Ÿ�Կ� ���� ������ ���� 
	- �Ϻθ� �Է½� �޺κ��� NULL 
	- �Է��� ���� �޺κп� �ִ� BLANK�� ���� �Է� 
	- ���� ���� ���� ������ NULL ���� �Է� 
	- ������ ���̺��� ��� �Է½� ���� �߻� 
	- �÷� ������ ������ ���� ���, NULL �� �ԷµǴ� ��찡 ���� ��� VARCHAR2 ��� 
	- �׷��� ���� �������� VARCHAR
	- CHAR : �������� ������ ������ Ÿ��

	NUMBER Ÿ��
	- ������ ����Ÿ Ÿ��, ����, ZERO, ��� ���� 
	- ��ü �ڸ����� 38�ڸ��� ���� �� ���� 
	- �Ҽ����� �������� �ʾ��� �� �Ҽ����� �Էµǰų�, ������ �Ҽ����ڸ� �̻� �ԷµǸ� �ݿø��Ǿ� ���� 
	- ������ ���� �ڸ��� �̻� �Է½� ���� �߻� 
	- ������ �ʿ��� �÷��� NUMBERŸ������ �����Ѵ�. 
	- NUMBER(p,s) �� ������ p�� s�� �ڸ����� ������ ���̹Ƿ� �����Ͽ� P�� �ڸ����� ���� 
	- NUMBER Ÿ���� �׻� �������̹Ƿ� ����ϰ� ������ �� 

	DATE Ÿ��
	- ���ڿ� �ð��� �����ϴ� ��¥�� Ÿ�� 
	- ���ڳ� �ð��� ������ ����� ��� ��� 
	- �������� : ����, �⵵, ��, ��, �ð�, ��, �� 
	- NLS_DATE_FORMAT�� �̿��Ͽ� ������ Ư������ �ذ� 
	- Ư���� �ð��� �������� ������ 00:00:00�� �Է� �� 
	- Ư���� ���ڸ� �������� �ʾҴٸ� ������� 1�Ϸ� ���� �� 
	- SYSDATE�� �����ϰ� �ð��� ���� 

######################################################
-- DDL : auto commit
-- ���̺� ����
drop table book;
-- ���̺� ����
create table book(
	bookno number(5),
	title varchar2(40),
	author varchar2(20),
	pubdate date
);

-- DML : auto commit �� �ƴ�
-- insert, delete, update
insert into book (bookno,title,author,pubdate)
values(1,'java','ȫ�浿',sysdate);

select * from book;
-- �� ������ ������ �������� �߰��� �Ǿ ��Ÿ������
-- �ٸ� client �� Ȯ�� ���� ����
-- commit �� ������ ��

commit;
-- commit�� ���󰡾� �ٸ� client������ �̸� Ȯ���� �� ����(����ȭ)
-- commit ������ rollback(���)�� ����
-- commit�� ������ ��Ұ� �Ұ���
-- eclipse�� ��� ��ɹ���  Auto commit���� �ڵ����� �Ǿ� ����

insert into book (bookno,title,author,pubdate)
values(2,'sql',null, null);

rollback;
-- �ٷ� ���� commit ���� ��ɵ��� ���
-- commit �� rollback �� ����

insert into book (bookno,title,author,pubdate)
values(2,'sql',null, '2019/01/01');

insert into book (bookno,title,author,pubdate)
values(3,'spring','��浿', to_date('01/01/2019','dd/mm/yyyy'));

commit;

-- �������� null�� ��
insert into book (bookno,title) values(4,'html');
insert into book values(5,'python','jecy', sysdate);
commit;
-- �Ʒ� ������ ���� pk �� ���� ���̺��� ��
insert into book values(1,'java','ȫ�浿',sysdate);

-- ��� �� ����
delete from book;
rollback;
-- Ư�� �� ����
delete from book where bookno=4;
delete from book where bookno=1;
rollback;

update book set title = '~~~~~' where bookno=3;
update book set title = 'hadoop' ,author ='kim' where bookno=1;
update book set title = 'html5' where title = '~~~~~';
-- �� ��쿡 pk ���� ���� ������ ���ϴ�  ������ �� ���� �����Ϳ��� ã�ƾ� �ϹǷ� ��ȿ����(full scan)
-- pk ������ �ϸ�, index�� ��������ϱ� search �ӵ�, ������ �ſ� ���
-- ȸ������ ��, �� ���̵� �� ����� �� ȸ�� db�� insert ��
-- �� ��, id �ߺ� üũ -> id �� PK�� �� �� ����

-- ���̺���
desc book;

-- ������ alter (DDL: auto commit)
-- column �߰�
alter table book add(price number(7));
update book set price = 0;
update book set price = 99.99; 
-- ���� �ݿø� �Ǿ� 100���� ����Ǿ� ��
update book set price = null;
commit;
-- column ����
alter table book modify(price number(7,2));
-- number�� �Ҽ��� 2��° �ڸ� ���� ����
update book set price = 99.99; 
commit; -- 99,99 �� �� ����Ǿ� �ִ� ���� �� �� ����
-- column ����
alter table book drop column price;

-- ���̺� �̸� ����
rename book to book2;
rename book2 to book;

delete from book; 
-- rollback ����
rollback;

-- ������ �����ְ� ���̺� �� �����͸� �����
truncate table book; 
-- auto commit (���� �Ұ�)

-- ���̺� ��ü�� �����
drop table book;

-- clone
-- PK, FK�� �ְ� ������ ����
create table emp2 as select * from emp; 
create table dept2 as select * from dept; 
select * from emp2;
select * from dept2;
-- �𺧷����� �� �׸��� ���� fk pk �� ����

insert into dept (deptno,dname,loc) values(10,'EDU','SEOUL');
-- �� ������ �������� ������ ���� �߻� (pk �ߺ�)
insert into dept2 (deptno,dname,loc) values(10,'EDU','SEOUL');
-- �� ������ ���� ���� ����

-- 50�� ������ �������ϱ� �Ѵ� ���� ����
insert into dept (deptno,dname,loc) values(50,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(50,'EDU','SEOUL');
commit;

insert into dept (deptno,dname,loc) values(null,'EDU','SEOUL');
-- �� ������ �������� ������ ���� �߻� (pk��  null �Ұ�)
insert into dept2 (deptno,dname,loc) values(null,'EDU','SEOUL');
-- �� ������ ���� ���� ����

insert into emp(empno, ename, sal, deptno)
-- �� ������ ���� �߻� (fk�� �Է��� ��(90)�� ���� ���̺� �������� ��������)
values(999,'�ѿ���',2100,90);

insert into emp2(empno, ename, sal, deptno)
values(999,'�ѿ���',2100,90);
-- emp2�� fk�� �����Ƿ� ����

insert into emp(empno, ename, sal, deptno)
values(999,'�ѿ���',2100,50);
commit;

-- emp���� ȫ ����� �ٹ��μ��� �ٹ��� ���
select ename,dname , loc
from emp, dept
where emp.deptno = dept.deptno
	and emp.ename ='�ѿ���';
-- ���
-- ENAME DNAME LOC
-- �ѿ���     EDU   SEOUL

select ename,dname , loc
from emp2, dept2
where emp2.deptno = dept2.deptno
	and emp2.ename ='�ѿ���';
	
select ename,dname , loc
from emp2, dept
where emp2.deptno = dept.deptno
	and emp2.ename ='�ѿ���';
-- ���
-- no rows selected
-- �ѿ����� deptno �� 90�̰�, dept2�� dept���� deptno=90�� ���� x
-- DB ������ �߿伺�� �� �� ����

###################################################

-- ���� ���� ���� --
-- primary key �� null �� �����
-- unique�� null �� ���
drop table book;
-- ���̺� ���� (�⺻Ű ����, ���� ���� ����)
create table book(
	bookno number(5) primary key,
	title varchar2(40) unique,
	author varchar2(20),
	price number(7,2) check(price>=0),
	pubdate date default sysdate
);

-- DML : auto commit �� �ƴ�
-- insert, delete, update
insert into book (bookno,title,author,pubdate)
values(1,'java','ȫ�浿',sysdate);

select * from book;
-- �� ������ ������ �������� �߰��� �Ǿ ��Ÿ������
-- �ٸ� client �� Ȯ�� ���� ����
-- commit �� ������ ��

commit;
-- commit�� ���󰡾� �ٸ� client������ �̸� Ȯ���� �� ����(����ȭ)
-- commit ������ rollback(���)�� ����
-- commit�� ������ ��Ұ� �Ұ���
-- eclipse�� ��� ��ɹ���  Auto commit���� �ڵ����� �Ǿ� ����

insert into book (bookno,title,author,pubdate)
values(2,'sql',null, null);

rollback;
-- �ٷ� ���� commit ���� ��ɵ��� ���
-- commit �� rollback �� ����

insert into book (bookno,title,author,price)
values(a,'java','ȫ���',-900);

insert into book (bookno,title,author,pubdate)
values(3,'spring','��浿', to_date('01/01/2019','dd/mm/yyyy'));

commit;
###################################################
-- ���� ���� �߰��غ��� 
drop table book;
-- ���̺� ���� (�⺻Ű ����, ���� ���� ����)
create table book(
	bookno number(5) ,
	title varchar2(40) unique,
	author varchar2(20),
	price number(7,2) check(price>=0),
	pubdate date default sysdate
);
-- �������� �߰�
alter table book 
add CONSTRAINT book_pk primary key(bookno);
-- �������� ����
alter table book drop CONSTRAINT book_pk;

insert into book (bookno,title,author,price)
values(1,'sql','ȫ���',-900);
insert into book (bookno,title,author,price)
values(1,'sql','ȫ���',2900);

###################################################
drop table emp2;
drop table dept2;

create table emp2 as select * from emp; 
create table dept2 as select * from dept; 

-- �������� �߰�
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
-- �� ������ self join

-- �������� ����
alter table dept2 drop CONSTRAINT dept2_pk;


###################################################
scott.sql �м�

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










