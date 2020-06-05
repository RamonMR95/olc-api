INSERT INTO addresses (city, country, province, street, zip) VALUES ("Manhattan", "USA", "New York", "123 main st", "66666");
INSERT INTO addresses (city, country, province, street, zip) VALUES ("Chicago", "USA", "Illinois", "456 main st", "77777");
 
INSERT INTO roles (name) VALUES ("Admin");
INSERT INTO roles (name) VALUES ("Teacher");
INSERT INTO roles (name) VALUES ("Student");
INSERT INTO roles (name) VALUES ("Anonymous");

INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAM", null, "morning", "1992/09/07", "1993/06/08");
INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAM", null, "afternoon", "1993/09/07", "1994/06/08");
INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAW", null, "morning", "1992/09/07", "1993/06/08");
INSERT INTO courses (course_name, photo, schedule, year_start, year_end) VALUES("DAW", null, "afternoon", "1993/09/07", "1994/06/08");


INSERT INTO subjects (subject_name) VALUES ("PSP");
INSERT INTO subjects (subject_name) VALUES ("SGE");
INSERT INTO subjects (subject_name) VALUES ("DI");
INSERT INTO subjects (subject_name) VALUES ("AAD");


INSERT INTO users (about, active, birth_date, email, name, password, photo, sur_name, address_id, course_id, mentor_id, role_id) VALUES ("", TRUE, "1995/03/15", "antonio507murcia@gmail.com", "Antonio", "$2a$10$VhrKZNJnTau5YdY2lyieMOLJLsEOQ1hun0j6PvgTuFY9T2vA.us7K", "", "Ruiz", 1, 1, 1, 1);
INSERT INTO users (about, active, birth_date, email, name, password, photo, sur_name, address_id, course_id, mentor_id, role_id) VALUES ("", TRUE, "1970/05/07", "ramonmr16@gmail.com", "Ramon", "$2a$10$VhrKZNJnTau5YdY2lyieMOLJLsEOQ1hun0j6PvgTuFY9T2vA.us7K", "", "Moñino", 2, 2, 2, 1);
 

INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/01/07", "Examen 1", 1, 1);
INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/04/07", "Examen 2", 1, 2);
INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/02/07", "Examen 3", 1, 3);
INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/03/02", "Examen 4", 1, 4);

INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/06/07", "Examen 5", 2, 1);
INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/05/07", "Examen 6", 2, 2);
INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/04/07", "Examen 7", 2, 3);
INSERT INTO exams(date_register, name, course_id, subject_id) VALUES ("1992/03/07", "Examen 8", 2, 4);

INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 10, 1, 1);
INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 5, 2, 1);
INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 7, 3, 1);
INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 9, 4, 1);

INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 9, 8, 2);
INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 5, 5, 2);
INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 4, 6, 2);
INSERT INTO user_exams(date_success, mark, exam_id, user_id) VALUES ("1992/05/07", 7, 7, 2);

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

INSERT INTO topics (description, name, subject_id) VALUES ("Description theme one", "Theme 1 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme two", "Theme 2 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme three", "Theme 3 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme four", "Theme 4 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme five", "Theme 5 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme six", "Theme 6 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme seven", "Theme 7 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme eight", "Theme 8 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme nine", "Theme 9 - Name Theme" , 1);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme ten", "Theme 10 - Name Theme" , 1);

INSERT INTO topics (description, name, subject_id) VALUES ("Description theme one", "Theme 1 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme two", "Theme 2 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme three", "Theme 3 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme four", "Theme 4 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme five", "Theme 5 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme six", "Theme 6 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme seven", "Theme 7 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme eight", "Theme 8 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme nine", "Theme 9 - Name Theme" , 2);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme ten", "Theme 10 - Name Theme" , 2);

INSERT INTO topics (description, name, subject_id) VALUES ("Description theme one", "Theme 1 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme two", "Theme 2 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme three", "Theme 3 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme four", "Theme 4 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme five", "Theme 5 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme six", "Theme 6 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme seven", "Theme 7 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme eight", "Theme 8 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme nine", "Theme 9 - Name Theme" , 3);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme ten", "Theme 10 - Name Theme" , 3);

INSERT INTO topics (description, name, subject_id) VALUES ("Description theme one", "Theme 1 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme two", "Theme 2 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme three", "Theme 3 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme four", "Theme 4 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme five", "Theme 5 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme six", "Theme 6 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme seven", "Theme 7 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme eight", "Theme 8 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme nine", "Theme 9 - Name Theme" , 4);
INSERT INTO topics (description, name, subject_id) VALUES ("Description theme ten", "Theme 10 - Name Theme" , 4);

INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 1);

INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 2);

INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 3);

INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);
INSERT INTO topic_content (subtitle, url, topic_id) VALUES ("Name for content theme", "https://www.fdi.ucm.es/profesor/jpavon/web/41-J2EE.pdf", 4);

INSERT INTO news (content, created_at, title, writer) VALUES ("Content1", "1992/05/07", "Title1", "Aenesidemus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content2", "1992/05/07", "Title2", "Anaxilaus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content3", "1992/05/07", "Title3", "Antoninus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content4", "1992/05/07", "Title4", "Anniceris");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content5", "1992/05/07", "Title5", "Aristippus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content6", "1992/05/07","Title6", "Aenesidemus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content7", "1992/05/07", "Title7", "Anaxilaus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content8", "1992/05/07","Title8", "Antoninus");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content9", "1992/05/07", "Title9", "Anniceris");
INSERT INTO news (content, created_at, title, writer) VALUES ("Content10", "1992/05/07","Title10", "Aristippus");





