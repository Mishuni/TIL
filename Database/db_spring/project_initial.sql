-- 계정 생성 
create user MULTI identified by 1234
default TABLESPACE mc;
-- 권한 부여
grant connect,resource,dba to MULTI;

DROP TABLE USERS;
DROP TABLE CLASS;
DROP TABLE SUGBOARD;
DROP TABLE REVIEWBOARD;
DROP TABLE SUGBOARD;


CREATE TABLE CLASS (
	CLASSNO NUMBER(5) PRIMARY KEY,
	CLASSNAME VARCHAR2(500),
	STARTDAY DATE,
	ENDDAY DATE,
	STUDENTCNT NUMBER(5)
);
INSERT INTO CLASS VALUES(0,'admin',sysdate,sysdate,1);
INSERT INTO CLASS VALUES(1,'bigdata IOT B',sysdate,sysdate,23);


CREATE TABLE USERS (
	ID VARCHAR2(50) PRIMARY KEY,
	PW VARCHAR2(50),
	ROLE VARCHAR2(10),
	NAME VARCHAR2(100),
	CLASSNO NUMBER(5) REFERENCES CLASS (CLASSNO),
	POINT NUMBER(5),
	TICKETS NUMBER(5),
	TOTAL NUMBER(10)
);
INSERT INTO USERS VALUES('admin','1234','admin','관리자',0, 10000, 10000,0);
INSERT INTO USERS VALUES('test','1234','user','유미선',1, 50, 0, null);


CREATE TABLE SUGBOARD (
	SEQ NUMBER(5) PRIMARY KEY,
	TITLE VARCHAR2(100),
	CONTENTS VARCHAR2(4000),
	WRITER VARCHAR2(50) REFERENCES USERS(ID),
	REGDATE DATE,
	VIEWCOUNT NUMBER(5)
);

INSERT INTO SUGBOARD VALUES(1,'게시판 공지사항입니다.','이런 양식을 지켜 주세요!','admin',sysdate, 0);
INSERT INTO SUGBOARD VALUES(2,'식단 A 건의사항','고기 좀 많이 주세요','test',sysdate, 0);

CREATE TABLE REVIEWBOARD (
	SEQ NUMBER(5) PRIMARY KEY,
	TITLE VARCHAR2(100),
	CONTENTS VARCHAR2(4000),
	WRITER VARCHAR2(50) REFERENCES USERS(ID),
	REGDATE DATE,
	VIEWCOUNT NUMBER(5)
);

INSERT INTO REVIEWBOARD VALUES(1,'게시판 공지사항입니다.','이런 양식을 지켜 주세요!','admin',sysdate, 0);
INSERT INTO REVIEWBOARD VALUES(2,'식단 A 후기','고기가 맛있어요!','test',sysdate, 0);

CREATE TABLE MCREVIEWBOARD (
	SEQ NUMBER(5) PRIMARY KEY,
	TITLE VARCHAR2(100),
	CONTENTS VARCHAR2(4000),
	WRITER VARCHAR2(50) REFERENCES USERS(ID),
	REGDATE DATE,
	VIEWCOUNT NUMBER(5)
);

INSERT INTO MCREVIEWBOARD VALUES(1,'게시판 공지사항입니다.','이런 양식을 지켜 주세요!','admin',sysdate, 0);
INSERT INTO MCREVIEWBOARD VALUES(2,'빅데이터를 활용한 IOT B반 후기','좋아요','test',sysdate, 0);
