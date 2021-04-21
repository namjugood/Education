-- * 오라클 - 뷰(View)
--	  - 물리적인 테이블에 근거한 논리적인 가상 테이블
--	  - 가상이란 단어는 실질적으로 데이터를 저장하고 있지 않기 때문에 붙인 것이고,
--		테이블이란 단어는 실질적으로 데이터를 저장하고 있지 않더라도 사용계정자는 마치
--		테이블을 사용하는 것과 동일하게 뷰를 사용할 수 있기 때문에 붙인 것
--	  - 뷰는 기본테이블에서 파생된 객체로서 기본테이블에 대한 하나의 쿼리문임
--	  - 실제 테이블에 저장된 데이터를 뷰를 통해서 볼 수 있도록 함
--	  - 사용자에게 주어진 뷰를 통해서 기본 테이블을 제한적으로 사용하게 됨
--	  - 뷰는 이미 존재하고 있는 테이블에 제한적으로 접근하도록 한다
--	  - 뷰를 생성하기 위해서는 실질적으로 데이터를 저장하고 있는 물리적인 테이블이
--		존재해야하는데 이 테이블은 기본테이블이라고 한다

-- 기존에 사용했던 select 문의 결과를 필요할 때마다 사용하기 위해 view에 select가 저장됩니다.
-- 1차 필터로 걸러낸 내용을 저장하는 것과 같은 맥락

-- 뷰 생성 방법
-- create or replace view 뷰이름
-- as select ...
create or replace view result_inner_join
as
select a.empno, a.ename, a.job, a.hiredate, a.deptno, b.dname, b.loc
from emp a, dept b where a.deptno = b.deptno;

select*from result_inner_join;
select empno, ename, job from result_inner_join;
select empno, ename, job from result_inner_join where job='MANAGER';

--통상 복잡한 select 문의 결과가 필요하거나, join의 결과가 필요할 때 view를 생성하여 저장하고
--필요 시 그 이름으로 이용합니다.

--앞선 예제의 join 명령을 뷰로 만들어 저장하고 조회해 보세요