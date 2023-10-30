insert into Users(email,username,password,userAuthorization)values('happyprogramming551@gmail.com','Admin','552B2EBE774BB5AAA0AD2021DA259D22',1)
insert into Users(email,username,password,userAuthorization)values('bachnd.2003@gmail.com','bach','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',1)
insert into Users(email,username,password,userAuthorization)values('bachndhe170904@fpt.edu.vn','mra','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',0)
insert into Users(email,username,password,userAuthorization)values('dungld2003@gmail.com','dung','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',1)
insert into Users(email,username,password,userAuthorization)values('bachnd20003@gmail.com','b','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',1)
insert into Users(email,username,password,userAuthorization)values('abc@gmail.com','c','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',1)
insert into Users(email,username,password,userAuthorization)values('abc123@gmail.com','d','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',1)
insert into Users(email,username,password,userAuthorization)values('abc123123@gmail.com','e','D18E7B9AB96C45E9DC5FFD53B7FFD1F7',1)

insert into UserStatus(userId,userStatus) values(1,'active')
insert into UserStatus(userId,userStatus) values(2,'active')
insert into UserStatus(userId,userStatus) values(3,'active')
insert into UserStatus(userId,userStatus) values(4,'active')
insert into UserStatus(userId,userStatus) values(5,'active')
insert into UserStatus(userId,userStatus) values(6,'active')
insert into UserStatus(userId,userStatus) values(7,'active')
insert into UserStatus(userId,userStatus) values(8,'active')

insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(1,'Admin','0912345678','Admin','2003-03-18',1,'Hanoi',1)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(2,'bach','0912345678','Nguyen Dinh Bach','2003-03-18',1,'Hanoi',3)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(3,'mra','0912345678','Nguyen Van A','2003-03-18',1,'Hanoi',2)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(4,'dung','0912345678','Luong Dinh Dung','2003-06-20',1,'Hanoi',3)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(5,'b','0912345678','Nguyen Van B','2003-06-20',1,'Hanoi',3)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(6,'c','0912345678','Nguyen Van C','2003-06-20',1,'Hanoi',3)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(7,'d','0912345678','Nguyen Van D','2003-06-20',1,'Hanoi',3)
insert into UserDetail(userId,username,phone,fullname,dob,gender,userAddress,roleId)values(8,'e','0912345678','Nguyen Van E','2003-06-20',1,'Hanoi',3)

INSERT INTO Skills (skillName, skillStatusId) VALUES
('Software Development', 1),
('Web Development', 1),
('Data Analysis', 1),
('Automation Scripting', 1),
('Game Development', 1),
('Machine Learning and Data Science', 1),
('Embedded Systems Programming', 1),
('Artificial Intelligence (AI)', 1),
('Mobile App Development', 1),
('IoT Development', 1),
('Database Management', 1),
('Scripting for System Administration', 1),
('Network Programming', 1),
('Game Scripting', 1),
('Robotics Programming', 1),
('Cybersecurity', 1),
('DevOps', 1),
('Cloud Computing', 0),
('Scripting for Graphics and Design', 0),
('GIS (Geographic Information Systems)', 0);

INSERT INTO ProgrammingLanguage (languageName, languageStatusId) VALUES
('Python', 1),
('Java', 1),
('JavaScript', 1),
('C++', 1),
('C#', 1),
('Ruby', 1),
('Swift', 1),
('Kotlin', 1),
('PHP', 1),
('Go (Golang)', 1),
('Rust', 1),
('TypeScript', 1),
('Perl', 1),
('Dart', 1),
('Scala', 1),
('R', 1),
('Objective-C', 1),
('Lua', 0),
('MATLAB', 0),
('Haskell', 0);