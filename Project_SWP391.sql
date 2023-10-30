use master
GO
drop database SWP391
GO
create database SWP391
GO
USE SWP391
GO
CREATE TABLE Roles (
   roleId INT NOT NULL identity(1,1),
   roleName NVARCHAR (50)  UNIQUE   NOT NULL,
   PRIMARY KEY (roleId),
);
insert into Roles(roleName)values('Admin');
insert into Roles(roleName)values('User');
insert into Roles(roleName)values('Mentee');
insert into Roles(roleName)values('Mentor');

CREATE TABLE Users (
   userId   INT    NOT NULL identity(1,1),
   email NVARCHAR (50)  Unique   NOT NULL,
   username NVARCHAR (50) Unique  NOT NULL,
   password NVARCHAR (50)     NOT NULL,
   userAuthorization bit  NOT NULL,
   PRIMARY KEY (userId),
);
CREATE TABLE UserStatus (
   userId   INT  NOT NULL,
   userStatus NVARCHAR(50) NOT NULL,
   PRIMARY KEY (userId),
   FOREIGN KEY (userId) REFERENCES Users(userId),
);
CREATE TABLE UserAvatar (
   userId   INT    NOT NULL ,
   avatarLink NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (userId),
   FOREIGN KEY (userId) REFERENCES Users(userId),
);

CREATE TABLE Gender (
   gender bit  NOT NULL,
   genderName NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (gender),
);
insert into Gender(gender,genderName) values(0,'Female')
insert into Gender(gender,genderName) values(1,'Male')

CREATE TABLE UserDetail (
   userId   INT  Unique  NOT NULL,
   username NVARCHAR (50)     NOT NULL,
   phone NVARCHAR (50)     NOT NULL,
   fullname NVARCHAR (50)     NOT NULL,
   dob date NOT NULL,
   gender bit  NOT NULL,
   userAddress NVARCHAR (50)     NOT NULL,
   roleId INT default 2 NOT NULL,
   PRIMARY KEY (userId),
   FOREIGN KEY (userId) REFERENCES Users(userId),
   FOREIGN KEY (roleId) REFERENCES Roles(roleId),
   FOREIGN KEY (gender) REFERENCES Gender(gender),
);

CREATE TABLE SkillStatus (
   skillStatusId   INT    NOT NULL identity(0,1),
   skillStatus NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (skillStatusId),
);
insert into SkillStatus(skillStatus) values('inactive')
insert into SkillStatus(skillStatus) values('active')
CREATE TABLE Skills (
   skillId   INT    NOT NULL identity(1,1),
   skillName NVARCHAR (50)     NOT NULL,
   skillStatusId INT NOT NULL,
   PRIMARY KEY (skillId),
   FOREIGN KEY (skillStatusId) REFERENCES SkillStatus(skillStatusId),
);

CREATE TABLE LanguageStatus (
   languageStatusId   INT    NOT NULL identity(0,1),
   languageStatus NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (languageStatusId),
);
insert into LanguageStatus(languageStatus) values('inactive')
insert into LanguageStatus(languageStatus) values('active')

CREATE TABLE ProgrammingLanguage (
   languageId   INT    NOT NULL identity(1,1),
   languageName NVARCHAR (50)     NOT NULL,
   languageStatusId INT NOT NULL,
   PRIMARY KEY (languageId),
   FOREIGN KEY (languageStatusId) REFERENCES LanguageStatus(languageStatusId),
);

CREATE TABLE Mentor (
   mentorId   INT    NOT NULL identity(1,1),
   userId   INT    NOT NULL ,
   PRIMARY KEY (mentorId),
   FOREIGN KEY (userId) REFERENCES UserDetail(userId),
);


CREATE TABLE MentorCV (
   mentorId   INT  Unique  NOT NULL,
   profession NVARCHAR (50)     NOT NULL,
   professionIntro NVARCHAR (50)     NOT NULL,
   serviceIntro NVARCHAR (50)     NOT NULL,
   achivementIntro NVARCHAR (50)     NOT NULL,
   PRIMARY KEY (mentorId),
   FOREIGN KEY (mentorId) REFERENCES Mentor(mentorId),
);


CREATE TABLE MentorSkills (
	id int primary key NOT NULL identity(1,1),
   mentorId   INT    NOT NULL,
   skillId	  INT    NOT NULL,
   FOREIGN KEY (skillId) REFERENCES Skills(skillId),
   FOREIGN KEY (mentorId) REFERENCES MentorCV(mentorId),
);

CREATE TABLE MentorProgramingLanguage (
	id int primary key NOT NULL identity(1,1),
   mentorId   INT    NOT NULL,
   languageId   INT    NOT NULL ,
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
   PRIMARY KEY (requestId),
   FOREIGN KEY (requestId) REFERENCES Requests(requestId),
   FOREIGN KEY (statusId) REFERENCES Statuses(statusId),
   FOREIGN KEY (mentorId) REFERENCES Mentor(mentorId),
);


CREATE TABLE requestSkillsChoices (
   id INT NOT NULL identity(1,1),
   requestId   INT    NOT NULL,
   skillId	  INT    NOT NULL,
   languageId   INT    NOT NULL ,
   PRIMARY KEY (id),
   FOREIGN KEY (skillId) REFERENCES Skills(skillId),
   FOREIGN KEY (requestId) REFERENCES RequestDetail(requestId),
   FOREIGN KEY (languageId) REFERENCES ProgrammingLanguage(languageId),
);

CREATE TABLE Comment (
   commentId   int NOT NULL identity(1,1),
   commentDetail NVARCHAR (200)     NOT NULL,
   createdDate Date NOT NULL,
   PRIMARY KEY (commentId),
);

CREATE TABLE Rating (
   ratingId   INT    NOT NULL identity(1,1),
   rating   INT    NOT NULL CHECK (rating >= 1 AND rating <= 5),
   commentId	  INT    NOT NULL,
   requestId   INT    NOT NULL,
   PRIMARY KEY (requestId),
   FOREIGN KEY (requestId) REFERENCES RequestDetail(requestId),
   FOREIGN KEY (commentId) REFERENCES Comment(commentId),
);
