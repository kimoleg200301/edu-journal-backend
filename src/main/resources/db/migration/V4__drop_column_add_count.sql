ALTER TABLE session_results
DROP COLUMN was_present;

ALTER TABLE subjects
    ADD COLUMN was_present TINYINT(1) NULL COMMENT '1 – присутствовал, 0 – отсутствовал (Н)';
