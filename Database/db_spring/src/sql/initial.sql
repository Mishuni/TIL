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
commit;
SELECT * FROM BOARD

select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') from dual
