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
values(1,'java','ȫ�浿',sysdate);
INSERT INTO BOARD VALUES(1,'1�� �Խù�','���������Դϴ�~!!!!!!!','USER1',sysdate, 11111, 0);
INSERT INTO BOARD VALUES(2,'2�� �Խù�','�������׾ƴմϴ�~','USER2',sysdate, 22222, 0);
INSERT INTO BOARD VALUES(3,'3�� �Խù�','���������ϱ�?','USER1',sysdate, 33333, 0);
INSERT INTO BOARD VALUES(5,'5�� �Խù�','���������ϱ�?','USER1',sysdate, 33333, 0);
commit;
SELECT * FROM BOARD

select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') from dual

select count(*) from BOARD
select max(seq)+1 from BOARD
INSERT INTO BOARD VALUES((select max(seq)+1 from BOARD),'7�� �Խù�','���������̴�.','USER1',sysdate, 33333, 0);

update BOARD set TITLE = '3�� �Խñ��̴�.' ,CONTENTS='����������', WRITER ='USER3',TIME=sysdate where SEQ=3;


-- SCOTT/TIGER ���� ���� 
create user MULTI identified by 1234
default TABLESPACE mc;
-- ���� �ο�
grant connect,resource,dba to MULTI;



select employee_id , first_name, last_name from employees;
select * from employees;
desc employees;

insert into employees(employee_id,first_name,last_name,email,job_id,hire_date)
		values((select nvl(max(employee_id),0)+1 from employees),
		'miseon','yu','alt@dk.com', 'IT_PROG', sysdate)