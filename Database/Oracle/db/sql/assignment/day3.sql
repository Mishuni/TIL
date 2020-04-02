drop table board;
drop table users;
create table users(
	id varchar2 (30) constraint users_pk primary key,
	password varchar2 (30),
	name varchar2 (20) unique,
	role varchar2(10)
);
create table board(
	seq number(10) constraint board_pk primary key,
	title varchar2 (30),
	content varchar2 (50),
	regdate date default sysdate,
	cnt number(10) default 0 check(cnt>=0),
	id varchar2 (30) constraint board_fk_id references users 
	on delete cascade
);

-- ȸ�����
insert into users(id,password,name,role)
values('a','m','aa','admin');
insert into users(id,password,name,role)
values('b','m','bb','user');
insert into users(id,password,name,role)
values('c','m','cc','user');
insert into users(id,password,name,role)
values('d','m','dd','user');
insert into users(id,password,name,role)
values('e','m','ee','user');
insert into users(id,password,name,role)
values('f','m','ff','user');
insert into users(id,password,name,role)
values('g','m','gg','user');
insert into users(id,password,name,role)
values('h','m','hh','user');
insert into users(id,password,name,role)
values('i','m','ii','user');
insert into users(id,password,name,role)
values('j','m',null,'user');
insert into users(id,password,name,role)
values('k','m','kk','user');
insert into users(id,password,name,role)
values('l','m','ll','user');
insert into users(id,password,name,role)
values('m','m','mm','user');

-- �Խ��� �۵��
drop sequence board_seq;
create sequence board_seq;
insert 
into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'99','gg',sysdate, 1,'a');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'22','aa','2019/01/01', 1,'b');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'33','cc','2019/08/02', 3,'c');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'22','dd','2019/07/03', 4,'d');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'11','ee','2019/04/01', 10,'a');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'55','ff','2019/05/04', 3,'e');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'11','ii','2019/03/05', 4,'g');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'22','dd','2019/01/06', 1,'f');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'33','cc','2019/01/07', 1,'f');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'333','cc','2019/01/07', 67,'h');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'665','i like computer','2019/02/07', 67,'l');
insert into board(seq,title,content,regdate,cnt,id)
values(board_seq.nextval,'775','I want to go there','2019/02/15', 30,'j');

commit;

-- ȸ�� ���� ���� & �ۼ���
update users set name = 'empty' where name is null;
update users set password = 'z' where id = 'l';
update users set password = 'z' where id = 'k';
update users set password = 'z' where id = 'i';
update board set title = '34262' where title = '55' and id ='e';
commit;

select * from board;
select * from users;

-- login
-- �α��� ����
select name from users
where id='a' and password = 'm';
-- �α��� ����
select name from users
where id='a' and password = 'b';

-- �����Ͱ˻�
select * from board
order by cnt;
-- ��ü ��ϱ� ��
select count(*) from board;
-- ����ں� ��ϱۼ�
select id, count(*) from board group by id;
-- ���� �Խñ� ��
select subStr(regdate,4,2) as month,count(*) 
from board
group by subStr(regdate,4,2);
-- ����ں� �Խñ� �˻�
select * from board
where id = 'a';
-- �������� �Խñ� �˻�
select * from board
where title LIKE '%1%';

-- �Խ��� �� ����
delete from board where title='99' and id='a' ;
select * from board
where id = 'a';

-- ȸ�� ����
delete from users where id = 'b' ;
select * from board
select * from users
where id = 'b';

-- �Խñ� id�� �Խ��� name �˻�
select u.id,u.name, b.title 
from users u, board b
where u.id = b.id;
