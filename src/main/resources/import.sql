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


INSERT INTO users (about, active, birth_date, email, name, password, photo, sur_name, address_id, course_id, mentor_id, role_id) VALUES ("", TRUE, "1995/03/15", "antonio507murcia@gmail.com", "Antonio", "$2a$10$VhrKZNJnTau5YdY2lyieMOLJLsEOQ1hun0j6PvgTuFY9T2vA.us7K", "", "Ruiz", 1, 1, 1, 1);
INSERT INTO users (about, active, birth_date, email, name, password, photo, sur_name, address_id, course_id, mentor_id, role_id) VALUES ("", TRUE, "1970/05/07", "ramonmr16@gmail.com", "Ramon", "$2a$10$VhrKZNJnTau5YdY2lyieMOLJLsEOQ1hun0j6PvgTuFY9T2vA.us7K", "", "Moñino", 2, 2, 2, 1);
 

INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 1", 1, 1);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 2", 1, 1);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 3", 1, 2);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 4", 1, 3);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/02", "Examen 5", 1, 4);

INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 6", 2, 1);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 7", 2, 1);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 8", 2, 2);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 9", 2, 3);
INSERT INTO exams(DATE, name, course_id, subject_id) VALUES ("1992/05/02", "Examen 10", 2, 4);

INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 10, 1, 1);
INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 5, 2, 1);
INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 7, 3, 1);
INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 9, 4, 1);

INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 6, 5, 2);
INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 8, 6, 1);
INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 10, 7, 1);
INSERT INTO user_exams(date, mark, exam_id, user_id) VALUES ("1992/05/07", 9, 8, 1);

INSERT INTO questions(name, exam_id) VALUES ("What is correct syntax for main method of a java class?", 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("A) public static int main(String[] args)", FALSE, 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("B) public int main(String[] args)", FALSE, 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("C) public static void main(String[] args)", TRUE, 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("D) None of the above.", FALSE, 1);

INSERT INTO questions(name, exam_id) VALUES ("What is the size of double variable?", 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("A) 8 bit", FALSE, 2);
INSERT INTO answers(answer, correct, question_id) VALUES ("B) 16 bit", FALSE, 2);
INSERT INTO answers(answer, correct, question_id) VALUES ("C) 32 bit", FALSE, 2);
INSERT INTO answers(answer, correct, question_id) VALUES ("D) 64 bit", TRUE, 2);

INSERT INTO questions(name, exam_id) VALUES ("What is the default value of byte variable?", 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("A) 0", TRUE, 3);
INSERT INTO answers(answer, correct, question_id) VALUES ("B) 0.0", FALSE, 3);
INSERT INTO answers(answer, correct, question_id) VALUES ("C) null", FALSE, 3);
INSERT INTO answers(answer, correct, question_id) VALUES ("D) not defined", FALSE, 3);

INSERT INTO questions(name, exam_id) VALUES ("What is class variable?", 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("A) class variables are static variables within a class but outside any method.", TRUE, 4);
INSERT INTO answers(answer, correct, question_id) VALUES ("B) class variables are variables defined inside methods, constructors or blocks.", FALSE, 4);
INSERT INTO answers(answer, correct, question_id) VALUES ("C) class variables are variables within a class but outside any method.", FALSE, 4);
INSERT INTO answers(answer, correct, question_id) VALUES ("D) None of the above.", FALSE, 4);

INSERT INTO questions(name, exam_id) VALUES ("What invokes a thread's run() method??", 1);
INSERT INTO answers(answer, correct, question_id) VALUES ("A) JVM invokes the thread's run() method when the thread is initially executed.", TRUE, 5);
INSERT INTO answers(answer, correct, question_id) VALUES ("B) Main application running the thread.", FALSE, 5);
INSERT INTO answers(answer, correct, question_id) VALUES ("C) start() method of the thread class.", FALSE, 5);
INSERT INTO answers(answer, correct, question_id) VALUES ("D) None of the above.", FALSE, 5);

 
