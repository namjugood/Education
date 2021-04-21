select * from emp;
select*from dept;


--[1] 이름 scott이 근무하는 부서명, 부서의 지역명 출력 (서브쿼리 사용)
-- select 두 번 사용
select deptno from emp where ename = 'SCOTT';	--20
select dname, loc from dept where deptno=20;

-- 서브쿼리 사용
-- select dname, loc from dept where deptno=( );
-- 괄호 안에 select deptno from emp where ename = 'SCOTT' 명령을 삽입
select dname, loc from dept where deptno=(select deptno from emp where ename='SCOTT');

-- 위 둘의 공통 단점 : 결과에 SCOTT이라는 이름이 출력되지 않습니다.
-- 서브쿼리의 단점 : 서브쿼리의 결과가 2건 이상이면 뜻하지 않은 오류를 초래합니다.
-- 서브쿼리의 단점 해결을 위해 '='대신 in을 씁니다
select dname, loc from dept where deptno in(select deptno from emp where ename='SCOTT');
-- where deptno in(10, 20, ...) 와 같은 형식이므로 in 안의 데이터만큼 결과가 출력됩니다.

--[2] join : 두 개 이상의 테이블에 나뉘어져 있는 데이터를 한 번의 sql문으로 원하는 결과를 얻음
-- 위에서 언급한 단점들이 한 번에 해결디는 방법

-- cross jon : 두 개 이상의 테이블이 조인될 때 where 절에 의해 공통되는 컬럼에 의한 결합이 발생하지 않는 경우며,
--	가장 최악의 결과를 얻는 조인방식
-- A테이블의 1레코드와 B테이블의 모든 레코드의 연결
-- A테이블의 2레코드와 B테이블의 모든 레코드의 연결
-- A테이블의 3레코드와 B테이블의 모든 레코드의 연결
-- ...
-- A테이블의 레코드가 8개, B테이블의 레코드가 7개라면, 총 크로스조인의 결과 레코드 수는 8*7=56
-- A테이블의 필드가 8개, B테이블의 필드가 3개라면, 총 크로스조인의 결과 필드 수는 8+3=11
select*from emp, dept;

-- update emp set deptno=40 where ename like 'S%';

-- equi join : 조인 대상이 되는 두 테이블에서 공통적으로 존재하는 컬럼의 값이 일치하는 행을 연결하여 결과를 생성
-- 시작은 cross join -> 같은 값을 갖는 필드를 조건으로 걸어 필터링합니다.
select*from emp, dept; -- 시작
select*from emp, dept where emp.deptno=dept.deptno; -- 두 테이블의 부서번호가 같은 레코드만 필터링
select*from emp, dept where emp.deptno=dept.deptno and ename='SCOTT'; -- 이름 SCOTT만 필터링
select ename, dname, loc from emp, dept where emp.deptno=dept.deptno and ename='SCOTT'; -- 필드 일부를 필터링

-- 출력 내용에 부서번호 추가
select ename, DEPTNO, dname, loc from emp, dept
where emp.deptno=dept.deptno and ename='SCOTT';		-- 에러
select ename, EMP.DEPTNO, dname, loc from emp, dept
where emp.deptno=dept.deptno and ename='SCOTT';		-- 정상실행

-- 필드 명 앞에 테이블 명을 기술하여 필드의 소속을 명확히 밝힐 수 있다.
select emp.ename, dept.dname, dept.loc, emp.deptno from emp, dept
where emp.deptno = dept.deptno and emp.ename='SCOTT';

select A.ename, B.dname, B.loc, A.deptno from EMP A, DEPT B
where A.deptno = B.deptno and A.ename='SCOTT';
-- 테이블 명에 별칭을 부여한 후 컬럼 앞에 소속테이블을 지정, 별칭 부여시에는 반드시 별칭을 기술해야 합니다.

-- equi 조인 연습
-- in_out, booklist 테이블을 이용하여 대여일자, 도서코드, 도서제목, 출판년도 equi 조인으로
-- 출력하세요. 필드명은 위에있는 한글 이름으로 별칭으로 표시하고, 테이블 이름도 a, b로 지정하여
-- 필드명에 적용하세요
select a.out_date as 대여일자, a.booknum as 도서코드,
		  b.subject as 도서제목, b.makeyear as 출판년도
from in_out a, booklist b
where a.booknum=b.booknum;

-- in_out, person 테이블을 이용하여 대여일자, 회원이름, 전화번호, 사은포인트(bpoint)를 equi join으로
-- 출력하세요. 필드명은 위에 있는 한글 이름으로 별칭을 표시하고, 테이블 이름도 a, b로 지정하여 필드명에 적용하세요
select a.out_date as 대여일자, b.personname as 회원이름, 
		  b.phone as 전화번호, b.bpoint as 사은포인트
from in_out a, person b
where a.personnum=b.personnum;

-- 세 걔의 테이블(in_out, person, booklist)을 equi join으로 조합
-- 표시할 필드별칭 : 대여일자, 도서제목, 회원이름, 대여가격, 할인금액, 매출액
-- 테이블 별칭 : in_out - a, person - b, booklist - c
select * from booklist;
select * from in_out;
select * from person;
select a.out_date as 대여일자, c.subject as 도서제목, b.personname as 회원이름,
		  c.outprice as 대여가격, a.discount as 할인금액, c.outprice-a.discount as 매출액
from in_out a, person b, booklist c
where a.booknum=c.booknum and a.personnum=b.personnum;

-- select*from in_out, person, booklist;

-- non-equi join
-- 동일 컬럼이 없이 다른 조건을 사용하여 join
-- join 조건이 특정 범위 내에 있는지를 조사하기 위해 조건절에 join 조건을 = 연산자 이외의 비교
-- 연산자를 이용
select*from emp;
select*from salgrade;
-- 급여가 LOSAL보다 크고, HISAL보다 작은 조건에 맞는 레코드를 찾아 GRADE를 출력합니다.
select a.ename, a.sal, b.grade from emp a, salgrade b
where a.sal>=b.losal and a.sal<=b.hisal;

select a.ename, a.sal, b.grade from emp a, salgrade b
where a.sal between b.losal and b.hisal;

--outer join
--조인 조건에 만족하지 못해서 해당결과를 출력 시에 누락이 되는 문제점이 발생할 때
--해당 레코드를 출력하는 join
select a.booknum, a.subject, b.out_date from booklist a, in_out b
where a.booknum=b.booknum(+);

--연습문제
--outer join으로 emp 테이블의 인원에 대한 부서명을 출력하되 부서명이 없는 필드는
--null값으로 표시하세요
alter table emp drop constraint fk_deptno;
update emp set deptno = 50 where job='CLERK';
update emp set deptno = 60 where job='ANALYST';

select*from emp;
select*from dept;

select a.ename as 이름, b.dname as 부서명 from emp a, dept b
where a.deptno=b.deptno(+);

select * from emp a, dept b
where a.deptno=b.deptno(+);

--[3] ANSI join
-- (1) Ansi Cross join
select*from emp cross join dept		-- 일반 크로스 조인과 같은 효과
-- (2) Ansio inner join
-- 기존 equi 조인의 표현방식
select ename, dname from emp a, dept b where a.deptno = b.deptno
-- Ansi inner join의 표현 방식
select ename, dname from emp inner join dept on emp.deptno = dept.deptno;
select ename, dname from emp inner join dept on emp.deptno = dept.deptno
where ename='SCOTT';
select ename, dname from emp inner join dept using (deptno);
-- 두 테이블의 조인 기준이 되는 필드명이 똑같을 때만 사용가능
-- (3) Ansi Outer join
-- 기존 Outer Join 표현방식
select*from emp, dept where emp.deptno = dept.deptno(+);
select*from emp, dept where emp.deptno(+) = dept.deptno;
-- Ansi Outer Join 표현방식
select*from emp left outer join dept on emp.deptno =dept.deptno;
select*from emp right outer join dept on emp.deptno =dept.deptno;
-- 기준이 되는 필드명 중 A 테이블의 필드에는 있으나 B테이블 필드에는 해당값이 없는 경우에 대한 표현여부 결정

