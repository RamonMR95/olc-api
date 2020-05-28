INSERT INTO addresses (city, country, province, street, zip) VALUES ("Manhattan", "USA", "New York", "123 main st", "66666");
INSERT INTO addresses (city, country, province, street, zip) VALUES ("Chicago", "USA", "Illinois", "456 main st", "77777");
 
INSERT INTO roles (name) VALUES ("Admin");
INSERT INTO roles (name) VALUES ("Teacher");
INSERT INTO roles (name) VALUES ("Student");
INSERT INTO roles (name) VALUES ("Anonymous");

INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAM", null, "morning", "1992/05/07", "1993/06/07");
INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAM", null, "afternoon", "1993/05/07", "1994/06/07");
INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAW", null, "morning", "1992/05/07", "1993/06/07");
INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAW", null, "afternoon", "1993/05/07", "1994/06/07");

INSERT INTO subjects (subject_name) VALUES ("PSP");
INSERT INTO subjects (subject_name) VALUES ("SGE");
INSERT INTO subjects (subject_name) VALUES ("DI");
INSERT INTO subjects (subject_name) VALUES ("AAD");


INSERT INTO users (about, active, birth_date, email, name, password, photo, sur_name, address_id, course_id, mentor_id, role_id) VALUES ("", TRUE, "1995/03/15", "antonio507murcia@gmail.com", "Antonio", ":)", "", "Ruiz", 1, 1, 1, 1);
INSERT INTO users (about, active, birth_date, email, name, password, photo, sur_name, address_id, course_id, mentor_id, role_id) VALUES ("", TRUE, "1970/05/07", "ramonmr16@gmail.com", "Ramon", ":)", "", "Moñino", 2, 2, 2, 1);
 

INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/07", 1, 1, 1, 1);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/07", 2, 1, 1, 1);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/07", 3, 1, 2, 1);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/07", 4, 1, 3, 1);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/02", 1, 1, 4, 1);
 
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1993/05/07", 2, 2, 1, 2);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/07", 7, 2, 2, 2);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/07", 8, 2, 3, 2);
INSERT INTO exams(DATE, mark, course_id, subject_id, user_id) VALUES ("1992/05/02", 9, 2, 4, 2);
 

 
