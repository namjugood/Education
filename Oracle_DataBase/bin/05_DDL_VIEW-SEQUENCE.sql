-- * 오라클 - 뷰(view)
-- - 물리적인 테이블에 근거한 논리적인 가상 테이블
-- - 가상이란 단어는 실질적으로 데이터를 저장하고 있지 않기 때문에 붙인 것이고,
--   테이블이란 단어는 실질적으로 데이터를 저장하고 있지 않더라도 사용계정자는
--	  마치 테이블을 사용하는 것과 동일하게 뷰를 사용할 수 있기 때문에 붙인 것입니다.
-- - 뷰는 기본테이블에서 파생된 또는 가공된 객체로서 기본테이블에 대한 하나의 쿼리(질의, 명령)문입니다.
-- - 실제 테이블에 저장된 데이터를 뷰를 통해서 볼 수 있도록 합니다.
-- - 사용자에게 주어진 뷰를 통해서 기본 테이블을 제한적으로 사용할 수 있습니다.
-- - 뷰는 이미 존재하고 있는 테이블에 제한적으로 접근하도록 합니다.
-- - 뷰를 생성하기 위해서는 실질적으로 데이터를 저장하고 있는 물리적인 테이블이
--   존재해야 하는데 이 테이블은 기본테이블이라고 합니다
--   두 개 이상의 테이블 또는 한 개의 테이블이나 또 다른 뷰를 참조하는 객체입니다.
--   저장된 테이블이라기보다 공식 또는 select 문을 갖고 있다가 명령대로 불러와 그때 그때 구성하는 형식입니다.
--   원본의 데이터 변화가 실시간으로 반영됩니다.

-- 생성방법
-- Create or Replace View 뷰이름 as (select 구문)
-- select 명령과 Join 명령 학습 후 다시 상세하게 공부할 예정입니다

-- * 오라클 - 시퀀스(sequence)
--		: 테이블 내의 유일한 숫자를 자동으로 생성하는 자동 번호 발생기
--		: 테이블 생성 후 시퀀스(일련번호)를 따로 만들어야 한다
-- 생성방법
-- create sequence 시퀀스이름 start with 시작숫자 increment by 증가량
-- 연속적으로 중복된 값이 없도록 하는데에 목적이 있음

-- [1] 샘플 테이블 생성
create table memos(
	num	number(4) constraint memos_num_pk primary key,
	name		varchar2(20) constraint memos_name_nn not null,
	postDate	Date default sysdate
);

select * from memos;

-- [2] 해당 테이블의 시퀀스 생성
create sequence memos_seq start with 1 increment by 1;
-- memos_seq : 시퀀스 이름
-- start with 1 : 시작 숫자
-- increment by 1 : 증감량

-- [3] 데이터 입력 : 일련번호 포함
insert into memos(num, name) values(memos_seq.nextVal, '홍길동');
insert into memos(num, name) values(memos_seq.nextVal, '이순신');
insert into memos(num, name) values(memos_seq.nextVal, '강감찬');
select*from memos;
insert into memos(num, name) values(memos_seq.nextVal, '유관순');
insert into memos(num, name) values(memos_seq.nextVal, '장영실');
select*from memos;

-- [4] 현재 시퀀스가 어디까지 증가되어져 있는지 확인
select memos_seq.nextVal, memos_seq.currval from dual;
select memos_seq.currval from dual;
-- 시퀀스는 .nextVal 메서드가 실행되는 순간 숫자가 증가합니다. 그래서 추후 웹프로젝트에서 실습할 때
-- 다른 요인에 의한 실행실패에도 .nextVal가 실행된 상태라면 숫자는 증가된 상태로 종료합니다.
-- 위의 예제에서도 select 구문 안에서 memos_seq.nextVal 가 실행될 때마다 숫자는 증가하게 됩니다.

-- [5] 시퀀스 수정 : 최대 증가값을 특정숫자(14)까지로 제한
alter sequence memos_seq maxvalue 14;

-- [6] 시퀀스 삭제
drop sequence memos_seq;

-- [7] 시퀀스 재생성 : 14 다음 숫자부터 시작하게 하여 기존레코드와 중복되지 않게 합니다
create sequence memos_seq start with 15 increment by 1;

