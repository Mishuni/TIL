
drop table board;
drop table users;

-- 테이블 생성
create table users(
	id varchar2(20) constraint users_pk primary key,
	pw number(20)
);
-- 회원 등록
insert into users(id,pw)
values('a',11);
insert into users(id,pw)
values('b',22);
insert into users(id,pw)
values('c',33);
insert into users(id,pw)
values('d',11);
commit;

-- 로그인
-- 로그인 성공
select id from users
where id = 'a' and pw =11;
-- 로그인 실패
select id from users
where id = 'a' and pw =22;

