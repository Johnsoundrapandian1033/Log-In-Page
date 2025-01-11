drop database loginpage;
create database loginpage;
use loginpage;
create table details(
	d_no int auto_increment primary key,
    dName varchar(30),
    dGender varchar(10),
    dob varchar(30)
);
create table Users(
  userNo int auto_increment primary key,
  userId varchar(30) not null,
  passwords varchar(15) not null,
  dNo int,
  foreign key(dNo) references details(d_no)
  );
  describe Users;
  insert into details (dName ,dGender, dob) values ("John","male","28-Apr-2002");
  insert into Users (userId,passwords,dNo) values("john1033@gmail.com","John1045@",1);
  select details.dName,details.dGender,details.dob,Users.userId from Users join details on dNo=d_no ;
  select * from Users;
  update Users 
  SET passwords = 'John1033@'
  where userId = "john1033@gmail.com";