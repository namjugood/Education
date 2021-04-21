create table product(
	code number(5) primary key,
	name varchar2(100),
	price number(8),
	pictureurl varchar2(50),
	description varchar2(1000)
);

create sequence product_seq start with 1 increment by 1;

insert into product
values(product_seq.nextVal, '개념을 콕콕 잡아주는 데이터베이스', 
27000, 'db.jpg', '데이터베이스에관한 모든것을 쉽고 재미있게 정리한 교재...');
insert into product
values(product_seq.nextVal, 'JQuery and JQuery mobile : 이해하기 쉽게 풀어쓴', 
25000, 'jquery.jpg', '소스 하나로 데스크탑과 모바일까지 HTML5와 함께 사용...');

select * from product;