
--���?
-- ��� �ϳ��� ���� ���̺��̶� ���� �ϸ� �ȴ�. 
-- ��� ���� �����Ͱ� ���� �Ǵ� ���� �ƴ����� �並 ���� �����͸� ���� �Ҽ� �ִ�. 
-- ��� ������ Query�� ���� ���� �� �ִ� ����� ������ Query�� ���� �� �ְ� �Ѵ�. 
-- �� ���� ��� ���� ���̺� ���� �����͸� �˻� �� �� �ִ�. 
-- Ư�� �� ���ؿ� ���� ����� ���� �ٸ� �����͸� �׼����� �� �ֵ��� �Ѵ�. 

-- �� �μ����� �ִ� �޿��� �޴� ����� ��� 
select * from emp
where (deptno,sal) in (select deptno,max(sal) from emp
			group by deptno)
order by deptno;

-- �� ������ view�� ����� (�б� ����)
create or replace view max_dept
as
select * from emp
where (deptno,sal) in (select deptno,max(sal) from emp
			group by deptno)
order by deptno;

select * from max_dept;
select * from max_dept where deptno = 10;

create or replace view page_emp
as
select *
from (
	select rownum row#,ename,job,sal 
	from (select ename,job,sal from emp order by sal desc)
);

select * from page_emp;

select * from page_emp
where row# between 6 and 10;


############################################
--sequence
############################################
--��������?
-- ����(UNIQUE)�� ���� �������ִ� ����Ŭ ��ü�̴�. 
-- �������� �����ϸ� �⺻Ű�� ���� ���������� �����ϴ� �÷��� �ڵ������� ���� �� �� �ִ�. 
-- ���� PRIMARY KEY ���� �����ϱ� ���� ��� �Ѵ�. 
-- �޸𸮿� Cache�Ǿ��� �� ���������� �׼��� ȿ���� ���� �Ѵ�. 
-- �������� ���̺���� ���������� ����ǰ� �����ȴ�.
-- ����Ŭ������ �ִ� ��ü, mySQL���� ����

select * from emp2;
select * from dept2;
select * from book;
-- �Ʒ��� ���� ������ ���� (����Ű�� �ֱ� ������)
delete from dept2;
delete from emp2;
-- sequence ����
create sequence dept_seq;
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
select * from dept2;
select dept_seq.currval from dual;
-- nextval�� ��ȸ�� �ص� ȣ��ÿ� ������ 1�� ����
select dept_seq.nextval from dual;
select dept_seq.nextval from dual;
-- sequence ����
drop sequence dept_seq;

-- 10���� �����ϰ� 10�������� ����
create sequence dept_seq start with 10 increment by 10;
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
insert into dept2 (deptno,dname,loc) values(dept_seq.nextval,'EDU','SEOUL');
rollback;
-- subquery �� sequence �����
select * from dept2;
select nvl(max(deptno),0)+10 from dept2;

insert into dept2 (deptno,dname,loc) 
values((select nvl(max(deptno),0)+10 from dept2),'EDU','SEOUL');

