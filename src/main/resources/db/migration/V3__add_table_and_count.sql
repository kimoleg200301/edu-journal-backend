CREATE TABLE session_results
(
    session_id  INT,
    student_id  INT,
    date        DATE,
    was_present TINYINT(1) NULL, -- 1 – присутствовал, 0 – отсутствовал (Н)
    PRIMARY KEY (session_id, student_id)
);
