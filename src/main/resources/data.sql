insert into course(id, name, created_date, last_updated_date,is_deleted) 
values(10001, 'Spring Master Course',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10002, 'SpringBoot Master Course',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10003, 'Spring MVC Master Course',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10004, 'Dummy1',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10005, 'Dummy2',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10006, 'Dummy3',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10007, 'Dummy4',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10008, 'Dummy5',sysdate(),sysdate(),false);

insert into course(id, name,created_date, last_updated_date,is_deleted) 
values(10009, 'Dummy6',sysdate(),sysdate(),false);


insert into passport(id,number) values(30000,'E123456');
insert into passport(id,number) values(30001,'1234E56');
insert into passport(id,number) values(30002,'K123081');


insert into student(id,name,passport_id) values(20000,'John',30000);
insert into student(id,name,passport_id) values(20001,'James',30001);
insert into student(id,name,passport_id) values(20002,'David',30002);


insert into review(id,rating,description,course_id) values(40000,'5','Great Course',10001);
insert into review(id,rating,description,course_id) values(40001,'3','Awsome Course',10001);
insert into review(id,rating,description,course_id) values(40002,'4','Wonderful Course',10002);

insert into student_course(student_id, course_id) values(20001,10001);
insert into student_course(student_id, course_id) values(20002,10001);
insert into student_course(student_id, course_id) values(20002,10001);
insert into student_course(student_id, course_id) values(20001,10003);