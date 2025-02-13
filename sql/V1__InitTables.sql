create table students (
	student_id int primary key auto_increment,
	firstname varchar(100) not null,
	lastname varchar(100) not null,
	birth_date date not null,
	gender varchar(100) not null,
	IIN varchar(12),
	living_adress varchar(150),
	edu_group_id int,
	foreign key(edu_group_id) references edu_groups(edu_group_id) on delete set null
);
create table edu_groups (
	edu_group_id int primary key auto_increment,
	name varchar(150) not null,
	created date not null default current_date
); /* the main one from tables list_of_subjects, subjects and journals */
create table subjects (
	subject_id int primary key auto_increment,
	name varchar(150) not null,
	subject_code varchar(100) not null,
	credits int not null
); /* <-- journals */
create table list_of_subjects (
	list_of_subject_id int primary key auto_increment,
	edu_group_id int not null,
	subject_id int not null,
	foreign key(edu_group_id) references edu_groups(edu_group_id) on delete cascade
);
create table journals (
	journal_id int primary key auto_increment,
	list_of_subject_id int not null,
	student_id int not null,
	mark int check(mark between 1 and 100),
	date_for date not null,
	foreign key (list_of_subject_id) references list_of_subjects(list_of_subject_id),
	unique index idx_student_subject (list_of_subject_id, student_id)
);

select * from students;
select * from edu_groups;
select * from subjects; 
select * from list_of_subjects;
select * from journals; /* лучше закинуть туда ид list_of_subjects и удалить subject_id и student_id */

select s.subject_id,
	s.name,
	s.subject_code,
	s.credits from subjects s
inner join list_of_subjects los on s.subject_id = los.subject_id where los.edu_group_id = 1; /* запрос на добавленных предметов */

select s.subject_id,
	s.name,
	s.subject_code,
	s.credits from subjects s
left join list_of_subjects los on s.subject_id = los.subject_id and los.edu_group_id = 1 where los.subject_id is null; /* запрос на не добавленных предметов */

UPDATE students SET edu_group_id = NULL WHERE student_id = (SELECT student_id FROM students WHERE )

DELETE FROM journals WHERE list_of_subject_id = (select list_of_subject_id from list_of_subjects where edu_group_id = 1 AND subject_id = 3) and student_id = 18;
           
drop table students;
drop table edu_groups;
drop table subjects;
drop table list_of_subjects;
drop table journals;