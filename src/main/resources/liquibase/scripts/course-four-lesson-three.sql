-- liquibase formatted sql

-- changeset akolodin:1
CREATE INDEX students_name_index ON student (name);

-- changeset akolodin:2
ALTER TABLE faculty
    ADD COLUMN name_faculty TEXT;

-- changeset akolodin:3
CREATE INDEX faculty_name_coor ON faculty (color, name_faculty);

-- changeset akolodin:4
DROP INDEX faculty_name_coor;

-- changeset akolodin:5
ALTER TABLE faculty
DROP
COLUMN name_faculty;

-- changeset akolodin:6
CREATE INDEX faculty_color_name ON faculty (color, name);



