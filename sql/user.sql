-- UserRepository
select *
	from user;
desc user;

insert 
	into user
	values(null, '관리자', 'admin@mysite.com', '1234', 'male', now());
    
-- login
select no, name
	from user
	where email = 'a' 
    and password = 'a';

-- findByno
select no, name, email, gender
	from user
	where name = 'qwe' 
    and password = 'qwe';
select *
	from guestbook

