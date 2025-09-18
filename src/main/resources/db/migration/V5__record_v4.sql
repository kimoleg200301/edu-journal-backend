ALTER TABLE subjects
DROP COLUMN was_present;

ALTER TABLE journals
    ADD COLUMN was_present TINYINT(1) NULL;