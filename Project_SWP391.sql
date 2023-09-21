use master
drop database SWP391
create database SWP391
USE SWP391


CREATE TABLE Roles (
   roleId INT NOT NULL identity(1,1),
   roleName NVARCHAR (50)  UNIQUE   NOT NULL,
   PRIMARY KEY (roleId),
);
insert into Roles(roleName)values('User');
insert into Roles(roleName)values('Mentee');
insert into Roles(roleName)values('Mentor');
insert into Roles(roleName)values('Admin');


CREATE TABLE Users (
   userId   INT    NOT NULL identity(1,1),
   email NVARCHAR (50)  Unique   NOT NULL,
   username NVARCHAR (50)  Unique   NOT NULL,
   password NVARCHAR (50)     NOT NULL,
   userAuthorization bit  NOT NULL,
   PRIMARY KEY (userId),
);


CREATE TABLE UserDetail (
   userId   INT  Unique  NOT NULL,
   username NVARCHAR (50)     NOT NULL,
   phone NVARCHAR (50)     NOT NULL,
   fullname NVARCHAR (50)     NOT NULL,
   dob date NOT NULL,
   gender bit  NOT NULL,
   userAddress NVARCHAR (50)     NOT NULL,
   roleId INT default 4 NOT NULL,
   PRIMARY KEY (userId),
   FOREIGN KEY (userId) REFERENCES Users(userId),
   FOREIGN KEY (roleId) REFERENCES Roles(roleId),
);


CREATE TABLE Skills (
   skillId   INT    NOT NULL identity(1,1),
   skillName NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (skillId),
);


CREATE TABLE ProgrammingLanguage (
   languageId   INT    NOT NULL identity(1,1),
   languageName NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (languageId),
);

CREATE TABLE Mentor (
   mentorId   INT    NOT NULL identity(1,1),
   userId   INT    NOT NULL ,
   PRIMARY KEY (mentorId),
   FOREIGN KEY (userId) REFERENCES UserDetail(userId),
);


CREATE TABLE MentorCV (
   mentorId   INT  Unique  NOT NULL,
   avatarLink NVARCHAR (50)     NOT NULL,
   profession NVARCHAR (50)     NOT NULL,
   professionIntro NVARCHAR (50)     NOT NULL,
   serviceIntro NVARCHAR (50)     NOT NULL,
   achivementIntro NVARCHAR (50)     NOT NULL,
   rating   INT  default 0  NOT NULL,
   PRIMARY KEY (mentorId),
   FOREIGN KEY (mentorId) REFERENCES Mentor(mentorId),
);


CREATE TABLE MentorSkills (
   mentorId   INT    NOT NULL,
   skillId	  INT    NOT NULL,
   languageId   INT    NOT NULL ,
   PRIMARY KEY (mentorId),
   FOREIGN KEY (skillId) REFERENCES Skills(skillId),
   FOREIGN KEY (mentorId) REFERENCES MentorCV(mentorId),
   FOREIGN KEY (languageId) REFERENCES ProgrammingLanguage(languageId),
);


CREATE TABLE Statuses (
   statusId int NOT NULL identity(1,1),
   statusName NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (statusId),
);
insert into Statuses(statusName)values('Open');
insert into Statuses(statusName)values('Processing');
insert into Statuses(statusName)values('Cancel');
insert into Statuses(statusName)values('Closed');


CREATE TABLE Requests (
   userId   INT    NOT NULL ,
   requestId int NOT NULL identity(1,1),
   PRIMARY KEY (requestId),
   FOREIGN KEY (userId) REFERENCES UserDetail(userId),
);


CREATE TABLE RequestDetail(
   requestId int    NOT NULL,
   title NVARCHAR (50)     NOT NULL,
   requestContent NVARCHAR (50)     NOT NULL,
   createdDate date NOT NULL,
   deadline date NOT NULL,
   statusId int   default 1  NOT NULL,
   mentorId   INT default 0,
   rating int  NOT NULL,
   PRIMARY KEY (requestId),
   FOREIGN KEY (requestId) REFERENCES Requests(requestId),
   FOREIGN KEY (statusId) REFERENCES Statuses(statusId),
   FOREIGN KEY (mentorId) REFERENCES Mentor(mentorId),
);


CREATE TABLE requestSkillsChoices (
   requestId   INT    NOT NULL,
   skillId	  INT    NOT NULL,
   languageId   INT    NOT NULL ,
   PRIMARY KEY (requestId),
   FOREIGN KEY (skillId) REFERENCES Skills(skillId),
   FOREIGN KEY (requestId) REFERENCES RequestDetail(requestId),
   FOREIGN KEY (languageId) REFERENCES ProgrammingLanguage(languageId),
);
select *from Users,UserDetail where Users.userId=UserDetail.userId