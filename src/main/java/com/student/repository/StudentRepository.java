package com.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query(value = "SELECT * FROM student ORDER BY marks DESC LIMIT 1 OFFSET 2", nativeQuery = true)
    Optional<Student> findSecondHighestMarksStudent();
	
}


