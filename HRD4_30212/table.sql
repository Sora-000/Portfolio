create table mem_tbl_book(
	custno number(6) not null,
	custname varchar2(20),
	joindate date,
	grade char(1),
	address varchar2(60),
	primary key(custno)
);

select max(custno)+1 max from mem_tbl_book;
select * from mem_tbl_book;
insert into mem_tbl_book values(10001,'박정희','2019-02-15','A','경기 분당구 서현동');
insert into mem_tbl_book values(10002,'최선한','2019-03-15','B','경기 군포시 산본동');
insert into mem_tbl_book values(10003,'김순애','2019-04-17','A','경기 군포시 산본동');
insert into mem_tbl_book values(10004,'최정현','2019-12-19','B','경기 분당구 정자동');
insert into mem_tbl_book values(10005,'김영림','2020-02-15','A','경기 분당구 정자동');
insert into mem_tbl_book values(10006,'박세영','2020-02-15','C','경기 용인시 용인동');

insert into mem_tbl_book values(10007,'박정희','2019-02-15','A','경기 분당구 서현동');
insert into mem_tbl_book values(10008,'최선한','2019-03-15','B','경기 군포시 산본동');
insert into mem_tbl_book values(10009,'김순애','2019-04-17','A','경기 군포시 산본동');
insert into mem_tbl_book values(10010,'최정현','2019-12-19','B','경기 분당구 정자동');
insert into mem_tbl_book values(10011,'김영림','2020-02-15','A','경기 분당구 정자동');
insert into mem_tbl_book values(10012,'박세영','2020-02-15','C','경기 용인시 용인동');
insert into mem_tbl_book values(10013,'최정현','2019-12-19','B','경기 분당구 정자동');
insert into mem_tbl_book values(10014,'김영림','2020-02-15','A','경기 분당구 정자동');
insert into mem_tbl_book values(10015,'박세영','2020-02-15','C','경기 용인시 용인동');
insert into mem_tbl_book values(10016,'박세영','2020-02-15','C','경기 용인시 용인동');
insert into mem_tbl_book values(10017,'최정현','2019-12-19','B','경기 분당구 정자동');
insert into mem_tbl_book values(10018,'김영림','2020-02-15','A','경기 분당구 정자동');
insert into mem_tbl_book values(10019,'박세영','2020-02-15','C','경기 용인시 용인동');


create table rent_tbl_book(
	custno number(6) not null,
	bookno number(4) not null,
	rentdate date,
	returndate date,
	primary key(custno, bookno)
);


insert into rent_tbl_book values(10001,1234,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10001,1122,'2019-02-15','2019-02-16');
insert into rent_tbl_book values(10002,1234,'2019-03-15','2019-02-15');
insert into rent_tbl_book values(10003,1234,'2019-04-17','2019-02-15');
insert into rent_tbl_book values(10004,1122,'2019-12-19','2019-02-15');
insert into rent_tbl_book values(10005,1122,'2020-02-15','2019-02-15');
insert into rent_tbl_book values(10005,1113,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10005,1114,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10006,1113,'2020-02-15','2019-02-15');

insert into rent_tbl_book values(10007,1234,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10008,1122,'2019-02-15','2019-02-16');
insert into rent_tbl_book values(10009,1234,'2019-03-15','2019-02-15');
insert into rent_tbl_book values(10010,1234,'2019-04-17','2019-02-15');
insert into rent_tbl_book values(10011,1122,'2019-12-19','2019-02-15');
insert into rent_tbl_book values(10011,1122,'2020-02-15','2019-02-15');
insert into rent_tbl_book values(10012,1234,'2019-04-17','2019-02-15');
insert into rent_tbl_book values(10013,1122,'2019-12-19','2019-02-15');
insert into rent_tbl_book values(10014,1122,'2020-02-15','2019-02-15');
insert into rent_tbl_book values(10015,1122,'2020-02-15','2019-02-15');
insert into rent_tbl_book values(10016,1122,'2019-12-19','2019-02-15');
insert into rent_tbl_book values(10017,1122,'2020-02-15','2019-02-15');
insert into rent_tbl_book values(10018,1122,'2020-02-15','2019-02-15');


select custno, custname, joindate, decode(grade,'A','VIP','B','일반','C','직원') grade, address
from mem_tbl_book;

select m.custno,custname,count(*) cnt
from mem_tbl_book m, rent_tbl_book r
where m.custno = r.custno
group by (m.custno,custname)
order by cnt desc, m.custno asc;

select bookno, count(*) cnt
from rent_tbl_book
group by bookno
order by cnt desc, bookno asc;

insert into mem_tbl_book values(?,?,?,?,?);

select * from mem_tbl_book where custno = ?;








