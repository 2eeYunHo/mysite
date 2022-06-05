-- board
desc board;
desc user;

select *
	from user;
select *
	from board;
insert
	into board
	values(null, '게시판', '자유게시판', 31 ,now(), 1, 1, 1, 1);
delete
	from board;
select a.no, a.title, u.name, a.hit, a.reg_date, a.group_no ,a.order_no, a.depth, u.no
	from board a, user u
    where a.no = u.no
    group by u.no ;
select a.no, a.title, a.contents,u.name, a.hit, a.reg_date, a.group_no, a.order_no, a.depth, 
		   u.no, u.email, u.password, u.gender, u.join_date
	from board a, user u
    where a.no = u.no
    group by u.name
    order by reg_date desc, order_no asc;
    
select *
	from board;
select a.no, a.title, a.contents, a.hit, a.reg_date, a.group_no, a.order_no, a.depth, a.user_no, b.name
	from board a, user b
	where a.user_no = b.no
	order by group_no desc, order_no desc;
    

    select a.no, a.title,  b.name, a.contents, a.hit, a.reg_date, a.group_no, a.order_no, a.depth, a.user_no
				from board a, user b 
                where a.user_no = b.no 
				order by a.group_no desc, a.order_no asc, a.depth asc
				limit 0, 10;
                
                select * from guestbook;