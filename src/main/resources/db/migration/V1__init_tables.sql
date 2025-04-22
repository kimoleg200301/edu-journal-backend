create table edu_groups
(
    edu_group_id int primary key auto_increment,
    name         varchar(150) not null,
    created      date         not null default (date(current_timestamp))
); /* the main one from tables list_of_subjects, subjects and journals */
create table students
(
    student_id    int primary key auto_increment,
    firstname     varchar(100) not null,
    lastname      varchar(100) not null,
    birth_date    date         not null,
    gender        varchar(100) not null,
    IIN           varchar(12),
    living_adress varchar(150),
    edu_group_id  int,
    foreign key (edu_group_id) references edu_groups (edu_group_id) on delete set null
);

create table subjects
(
    subject_id   int primary key auto_increment,
    name         varchar(150) not null,
    subject_code varchar(100) not null,
    credits      int          not null
); /* <-- journals */
create table list_of_subjects
(
    list_of_subject_id int primary key auto_increment,
    edu_group_id       int not null,
    subject_id         int not null,
    foreign key (edu_group_id) references edu_groups (edu_group_id) on delete cascade
);
create table journals
(
    journal_id         int primary key auto_increment,
    list_of_subject_id int  not null,
    student_id         int  not null,
    mark               int check (mark between 1 and 100),
    date_for           date not null,
    foreign key (list_of_subject_id) references list_of_subjects (list_of_subject_id),
    unique index idx_student_subject (list_of_subject_id, student_id, date_for)
);

# select * from students;
# select * from edu_groups;
# select * from subjects;
# select * from list_of_subjects;
# select * from journals; /* лучше закинуть туда ид list_of_subjects и удалить subject_id и student_id */
#
# select s.subject_id,
# 	s.name,
# 	s.subject_code,
# 	s.credits from subjects s
# inner join list_of_subjects los on s.subject_id = los.subject_id where los.edu_group_id = 1; /* запрос на добавленных предметов */
#
# select s.subject_id,
# 	s.name,
# 	s.subject_code,
# 	s.credits from subjects s
# left join list_of_subjects los on s.subject_id = los.subject_id and los.edu_group_id = 1 where los.subject_id is null; /* запрос на не добавленных предметов */
#
# select s.firstname,
# 	s.lastname,
# 	j.mark,
# 	los.subject_id,
# 	j.date_for from students s
# left join journals j on s.student_id = j.student_id
# inner join list_of_subjects los on j.list_of_subject_id = los.list_of_subject_id
# where los.list_of_subject_id = (select list_of_subject_id from list_of_subjects where edu_group_id = 1 and subject_id = 1) and j.date_for between CONCAT("2025-01", '-01') and LAST_DAY(CONCAT("2025-01", '-01')); -- 1. Вывод студ. по выбранно группе. 2. по предмету. 3. по дате.
#
# SELECT
#   s.student_id,
#   s.firstname,
#   s.lastname,
#   g.name AS group_name,
#   subj.name AS subject_name,
#   j.mark,
#   j.date_for
# FROM students s
# INNER JOIN edu_groups g ON s.edu_group_id = g.edu_group_id
# INNER JOIN list_of_subjects ls ON g.edu_group_id = ls.edu_group_id
# INNER JOIN subjects subj ON ls.subject_id = subj.subject_id
# LEFT JOIN journals j ON ls.list_of_subject_id = j.list_of_subject_id
#   AND s.student_id = j.student_id
#   AND j.date_for BETWEEN CONCAT('2025-02', '-01') AND LAST_DAY(CONCAT('2025-02', '-01'))
# WHERE g.edu_group_id = 1  -- ID группы
#   AND subj.subject_id = 1  -- ID предмета
# ORDER BY j.date_for DESC;
#
#
# DELETE FROM journals WHERE list_of_subject_id = (select list_of_subject_id from list_of_subjects where edu_group_id = 1 AND subject_id = 1) and student_id = 18;
#
# drop table students;
# drop table edu_groups;
# drop table subjects;
# drop table list_of_subjects;
# drop table journals;




