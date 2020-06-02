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

	@Query(nativeQuery = true, value = "SELECT e.mark, sb.subject_name " + "FROM exams e "
			+ "INNER JOIN courses co ON co.id = e.course_id " + "INNER JOIN users us ON us.course_id = co.id "
			+ "INNER JOIN subjects sb ON sb.id = e.subject_id " + "WHERE us.id = ?1 AND co.year_start = ?2")
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
		
}
