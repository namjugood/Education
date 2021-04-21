-- Function 내장함수

--[1] 샘플 테이블인 dual 테이블
select * from tabs; -- 현재 사용자가 소유한 테이블 목록 표시
select * from dual; -- 라이브러리 함수의 결과를 보기 위한 키워드 사용 dual : 임시 데이터 출력 시 사용합니다.
-- 임시 데이터란 특정 테이블을 대상으로 하는 select문이 아니면서 계산의 결과가 출력되어야 하는 곳에 쓰는 키워드입니다.

--[2] 임시데이터 출력
select sal*12+nvl(comm,0) as "연봉" from emp;	--emp 테이블 대상
select 1234 * 1234 from dual;		-- 특정 테이블이 대상이 아닌 연산결과 조회

-- *** 문자열 처리 관련 함수 ***
--[3] lower() : 모든 문자를 소문자로 변환
select lower('Hong Kil Dong') as "소문자" from dual;
--[4] upper() : 모든 문자를 대문자로 변환
select upper('Hong Kil Dong') as "대문자" from dual;
--[5] initcap() : 첫 자만 대문자로 변환
select initcap('Hong Kil Dong') as "첫 글자만 대문자" from dual;

--[6] concat() : 문자열 연결
-- ()안에 필드명이 들어갈 경우 필드명이 합쳐짐
select concat('이젠 IT','아카데미') from dual;
--[7] length() : 문자열의 길이
select length('이젠 아이티 아카데미') as len1, length('The ezen IT') as len2 from dual;
--[8] substr() : 문자열 추출(데이터, 인덱스(1), 카운트)
select substr('홍길동 만세', 2, 4) from dual;
-- substr의 경우 자바의 substring처럼 시작 번째부터 끝번째+1이 아니라
-- 시작 번째부터 글자수를 나타냅니다. 위의 예시의 경우 2번째 글자부터 네 글자 표시

--[9] instr() : 문자열 시작 위치
select instr('홍길동 만세 동그라미', '동') from dual;
--[10] lpad(), rpad() : 자리채우기
select lpad('Oracle', 20, '#') from dual;		-- #################Oracle
select rpad('Oracle', 20, '*') from dual;		-- Oracle***********************
--[11] trim() : 컬럼이나 대상 문자열에서 특정 문자가 첫 번째 글자이거나 마지막 글자면 잘라내고 남은 문자열만 반환
select trim('a' from 'aaaOracleaaaaaaa') as result from dual;	--Oracle
select trim(' ' from '     Oracle        ') as result from dual;	-- Oracle


-- *** 수식 처리 관련 함수 ***
--[12] round() : 반올림(음수 : 소수점 이상 자리)
select round(12.3456, 0) from dual;
-- 12.3456 : 반올림하려는 대상 숫자
-- 3 : 반올림하여 표시하고자 하는 마지막 자리수
-- 3 : 소수점 넷째 자리에서 반올림하여 셋째 자리까지 남김
-- 2 : 소수점 셋째 자리에서 반올림하여 둘째 자리까지 남김
-- 1 : 소수점 둘째 자리에서 반올림하여 첫째 자리까지 남김
-- 0 : 소수점 첫째 자리에서 반올림하여 소수점 자리수 없앰
-- -1 : 1의 자리에서 반올림하여 10의 자리까지 남김
-- -2 : 10의 자리에서 반올림하여 100의 자리까지 남김
-- -3 : 100이 자리에서 반올림하여 1000의 자리까지 남김

select round(1728.9382, 3) from dual;		--1728.938
select round(1728.9382, 2) from dual;		--1728.94
select round(1728.9382, 1) from dual;		--1728.9
select round(1728.9382, 0) from dual;		--1729
select round(1728.9382, -1) from dual;		--1730
select round(1728.9382, -2) from dual;		--1700
select round(1728.9382, -3) from dual;		--2000

--[13] abs() : 절대값
select abs(-10) from dual;	--10
--[14] floor() : 소수점 아래 절사 - 반올림 없음
select fllor(12.94567) from dual;	-- 12
-- 소수점 아래 올림
select ceil(12.94567) from dual;	-- 13
--[15] trunc() : 특정 자리 자르기 - 반올림 없음, 3은 남기고 싶은 소수점 아래 자리수
select trunc(12.34567, 3) from dual;	--12.345
--[16] mod() : 나머지		-- 8을 5로 나눈 나머지
select mod(8, 5) from dual;		--3


--*** 날짜 처리 관련 함수 ***
--[17] sysdate : 날짜
select sysdate from dual;	-- 오늘 날짜와 현재 시각 2020-12-17 17:26:30:52.0
--[18] months_between() : 개월 수 구하기
select months_between('2021-12-31', '2020-07-10') from dual;	-- 17.677419... 개월 수
--[19] add_months() : 개월 수 더하기
select add_months(sysdate, 200) from dual;	-- 2037-08-17 17:29:29.0
--[20] next_day() : 다가올 요일에 해당하는 날짜 - 오늘 날짜(sysdate)에서 가장 가까운 일요일
select next_day(sysdate, '일요일') from dual;	-- 2020-12-20 17:30:46.0
--[21] last_day() : 해당 달의 마지막 일 수
select last_day(sysdate) from dual;	-- 2020-12-31 17:31:27.0
select last_day('2020-12-15') from dual;	--  2020-12-31 00:00:00.0
--[22] to_char() : 문자열로 반환
select to_char(sysdate, 'yyyy-mm-dd') from dual;	--  2020-12-17
--[23] to_date() : 날짜형(date)으로 변환
select to_date('2019/12/31', 'yyyy/mm/dd') from dual;	--  2019-12-31 00:00:00.0


-- *** 그 외 활용가능한 함수들 ***
--[24] nvl() : null인 데이터를 다른 데이터로 변경
select comm/100 as comm_pct from emp;
-- comm 필드값에 따라서 일부 레코드는 계산결과가 null이 나올 수 있음
select nvl(comm, 1)/100 as comm_pct from emp;
-- 해당 필드 값이 null이면 1로 바꿔서 계산에 참여

--[25] decode() : switch문과 같은 기능
select ename, deptno, decode(deptno,
						10, 'ACCOUNT',
						20, 'RESEARCH',
						30, 'SALES',
						40, 'OPERATIONS',
						50, 'SH_CHECK',
						60, 'IT_PROG',
						70, 'PR_REP',
						80, 'SA_REP',
						90, 'AD_PRES',
						100, 'FI_ACCOUNT',
						110, 'AC_ACCOUNT'	) as "부서 이름" from emp;
-- deptno 값이 무엇이냐에 따라 부서이름이 결정되어 결과 표시;
-- 부서번호(deptno) 필드와 부서명(dname) 필드가 정의되어 있는 테이블이 있다면 두 개의 테이블을
-- join하여 표시하면 되지만, 위의 경우 해당 테이블이 없을 때 사용하는 방법입니다.
						
--[26] case() : if ~ else if ~ 와 비슷한 구조
select ename, deptno,
		  case when deptno = 10 then'ACCOUNT'
		  		 when deptno = 20 then'RESEARCH'
		  		 when deptno = 30 then'SALES'
		  		 when deptno = 40 then'OPERATIONS'
		  		 when deptno = 50 then'SH_CHECK'
		  		 when deptno = 60 then'IT_PROG'
		  		 when deptno = 70 then'PR_REP'
		  		 when deptno = 80 then'SA_REP'
		  		 when deptno = 90 then'AD_PRES'
		  		 when deptno = 100 then'FI_ACCOUNT'
		  		 when deptno = 110 then'AC_ACCOUNT'
	  	  end as "부서명" 
from emp;