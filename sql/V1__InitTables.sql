create table students (
	student_id int primary key auto_increment,
	firstname varchar(100) not null,
	lastname varchar(100) not null,
	birth_date date not null,
	gender varchar(100) not null,
	IIN varchar(12),
	living_adress varchar(150),
	edu_group_id int
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
	list_of_subjects_id int primary key auto_increment,
	edu_group_id int not null,
	subject_id int not null,
	foreign key(edu_group_id) references edu_groups(edu_group_id)
);
create table journals (
	journal_id int primary key auto_increment,
	subject_id int,
	student_id int not null,
	mark int check(mark between 1 and 100),
	foreign key (student_id) references students(student_id),
	foreign key(subject_id) references subjects(subject_id)
);

select * from students;
select * from edu_groups;
select * from subjects;
select * from list_of_subjects;
select * from journals;

drop table students;
drop table edu_groups;
drop table subjects;
drop table list_of_subjects;
drop table journals;