package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
    private StudentRepository studentRepository;

    // Create or Update student
    public List<Student> saveStudent(List<Student> student) {
    	//Student student=new Student();
    	//student=studen;
        return studentRepository.saveAll(student);
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by roll number
    public Optional<Student> getStudentByRollNo(Long rollNo) {
        return studentRepository.findById(rollNo);
    }
    public Student updateStudent(Student updatedStudent) {
        return studentRepository.save(updatedStudent);  // Save the updated student
    }

    // Delete student
    public void deleteStudent(Long rollNo) {
        studentRepository.deleteById(rollNo);
        // student=new Student();
        //student.name;
    }
 
}



