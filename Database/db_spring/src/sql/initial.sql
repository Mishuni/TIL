drop table BOARD;
CREATE TABLE BOARD (
	SEQ NUMBER(5) PRIMARY KEY,
	TITLE VARCHAR2(100),
	CONTENTS VARCHAR2(4000),
	WRITER VARCHAR2(20),
	TIME DATE,
	PASSWORD NUMBER(5),
	VIEWCOUNT NUMBER(10)
);
insert into book (bookno,title,author,pubdate)
values(1,'java','홍길동',sysdate);
INSERT INTO BOARD VALUES(1,'1번 게시물','공지사항입니다~!!!!!!!','USER1',sysdate, 11111, 0);
INSERT INTO BOARD VALUES(2,'2번 게시물','공지사항아닙니다~','USER2',sysdate, 22222, 0);
INSERT INTO BOARD VALUES(3,'3번 게시물','공지사항일까?','USER1',sysdate, 33333, 0);
INSERT INTO BOARD VALUES(5,'5번 게시물','공지사항일까?','USER1',sysdate, 33333, 0);
commit;
SELECT * FROM BOARD

select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') from dual

select count(*) from BOARD
select max(seq)+1 from BOARD
INSERT INTO BOARD VALUES((select max(seq)+1 from BOARD),'7번 게시물','공지사항이다.','USER1',sysdate, 33333, 0);

update BOARD set TITLE = '3번 게시글이다.' ,CONTENTS='음하하하하', WRITER ='USER3',TIME=sysdate where SEQ=3;


-- SCOTT/TIGER 계정 생성 
create user MULTI identified by 1234
default TABLESPACE mc;
-- 권한 부여
grant connect,resource,dba to MULTI;



select employee_id , first_name, last_name from employees;
select * from employees;
desc employees;

insert into employees(employee_id,first_name,last_name,email,job_id,hire_date)
		values((select nvl(max(employee_id),0)+1 from employees),
		'miseon','yu','alt@dk.com', 'IT_PROG', sysdate)