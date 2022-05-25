-- guestbook

desc guestbook;

select no, name, password, message, reg_date
	from guestbook
    order by reg_date;
