
drop table board;
drop table users;

-- ���̺� ����
create table users(
	id varchar2(20) constraint users_pk primary key,
	pw number(20)
);
-- ȸ�� ���
insert into users(id,pw)
values('a',11);
insert into users(id,pw)
values('b',22);
insert into users(id,pw)
values('c',33);
insert into users(id,pw)
values('d',11);
commit;

-- �α���
-- �α��� ����
select id from users
where id = 'a' and pw =11;
-- �α��� ����
select id from users
where id = 'a' and pw =22;

