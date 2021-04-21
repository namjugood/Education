create table board(
	num varchar2(5) primary key,
	pass varchar2(30),
	userid varchar2(30),
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount number(4) default 0,
	writedate date default sysdate
);

alter table board modify num number(30);

create sequence board_seq start with 1 increment by 1;
select*from member;

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'somi', 'abcd@naver.com', '1234', '첫 방문', '반갑습니다');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '게시판 개설', '축하드립니다');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'sang12', 'bnbn@naver.com', '1234', '돼지골마을', '돼지 삼겹살이 맛있습니다');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'namju', 'namjugood@naver.com', '1234', '2020년 겨울', '날씨가 춥고 한파가 기승을 부립니다');

select*from board;

insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'somi', 'abcd@naver.com', '1234', '맛집 탐방 후기', '맛집이라고 하기엔 그저 그랬습니다');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'addd@naver.com', '1234', '사회적 거리두기 2.5단계', '모두 거리두기에 동참해 주세요');

select*from board;
delete from board where num=103;
select*from board where num>=98 and num<=104;

select rownum as rn, board.*from board; -- 열의 번호를 따로 매김


--1. 리스트 숫자 배열 정리방법
select*from board order by num desc;
--2. 
select rownum as rn, t.* from (select * from board order by num desc) t;
--3.
select*from(select rownum as rn, t.*from (select*from board order by num desc) t)
where rn>=1 and rn<=10;


create table reply(
	num number(7) primary key,
	boardnum number(5),
	userid varchar2(20),
	writedate date default sysdate,
	content varchar2(1000)
);

create sequence reply_seq start with 1 increment by 1;
select * from reply;

alter table board add replycnt number(3);
