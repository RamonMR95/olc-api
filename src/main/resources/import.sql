INSERT INTO addresses (city, country, province, street, zip) VALUES ("Manhattan", "USA", "New York", "123 main st", "66666");
INSERT INTO addresses (city, country, province, street, zip) VALUES ("Chicago", "USA", "Illinois", "456 main st", "77777");
 
INSERT INTO roles (name) VALUES ("Admin");
INSERT INTO roles (name) VALUES ("Teacher");
INSERT INTO roles (name) VALUES ("Student");
INSERT INTO roles (name) VALUES ("Anonymous");
 
 
INSERT INTO courses (course_name, schedule, year_start, year_end) VALUES("DAM", "morning", "1992/05/07", "1993/06/07");
INSERT INTO courses (course_name, schedule, year_start, year_end) VALUES("DAM", "afternoon", "1993/05/07", "1994/06/07");
INSERT INTO users (active, email, name, nick_name, password, sur_name, address_id, course_id, role_id) VALUES (TRUE, "antonio507murcia@gmail.com", "Antonio", "tiky", ":)", "Ruiz", 1, 1, 1);
INSERT INTO users (active, email, name, nick_name, password, sur_name, address_id, course_id, role_id) VALUES (TRUE, "ramonmr16@gmail.com", "Ramon", "ramonmr95", ":)", "Moñino", 2, 2, 1);
 
INSERT INTO subjects (subject_name) VALUES ("PSP");
INSERT INTO subjects (subject_name) VALUES ("SGE");
INSERT INTO subjects (subject_name) VALUES ("DI");
INSERT INTO subjects (subject_name) VALUES ("AAD");
 
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/07", 4,1,1);
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/07", 2,1,2);
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/07", 1,1,3);
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/02", 5,1,4);
 
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1993/05/07", 1,2,1);
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/07", 0,2, 2);
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/07", 0,2, 3);
INSERT INTO exams(DATE, mark, course_id, subject_id) VALUES ("1992/05/02", 0,2, 4);