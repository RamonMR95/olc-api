package com.ramonmr95.tiky.olc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ramonmr95.tiky.olc.entities.Role;
import com.ramonmr95.tiky.olc.entities.User;

@Repository
public interface IUserDao extends CrudRepository<User, Long> {

	@Query(nativeQuery = true, value = "SELECT ue.mark, sb.subject_name FROM user_exams ue " + 
			"INNER JOIN exams x ON x.id = ue.exam_id " + 
			"INNER JOIN subjects sb ON sb.id = x.subject_id " + 
			"INNER JOIN courses co ON co.id = x.course_id " + 
			"WHERE ue.user_id = ?1 AND year_start = ?2")
	public List<Object[]> findMarkAndSubjectsByStudentIdAndYearStart(Long id, String year);

	@Query(value = "SELECT u FROM User u WHERE course_id = ?1")
	public List<User> findAllUsersPerCourse(Long course_id);
	
	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
	
	@Query(value = "SELECT r FROM Role r INNER JOIN User u ON r.id = u.role WHERE u.id = ?1")
	public Role findRoleByUserId(Long id);

	@Query(nativeQuery = true, value = "SELECT u.* FROM users u WHERE mentor_id = ?1")
	public User findMentorByCourseId(Long course_id);

	@Modifying
	@Query(value = "UPDATE User u SET u.active = ?2 WHERE u.id = ?1")
	public void updateStateUser(int user_id, boolean active);
	
	@Query(nativeQuery = true, value = "SELECT * FROM users u WHERE u.mentor_id = ?1")
	public User findMentorById(Long id);
	
	@Query("SELECT u FROM User u WHERE u.course.id = ?1 AND u.role.name = 'Student' OR u.role.name = 'Teacher'")
	public List<User> findUsersGivenMentorId(Long id);
	
}
