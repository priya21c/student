package com.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.service.StudentService;

@Controller
@RestController
public class StudentController {
	@Autowired
    private StudentService studentService;
	

    // Create or Update a student
    @PostMapping("/create")
    public List<Student> createOrUpdateStudent(@RequestBody List<Student> student) {
        return studentService.saveStudent(student);
    }
    @GetMapping("/gets")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by roll number
    @GetMapping("/{rollNo}")
    public Optional<Student> getStudentByRollNo(@PathVariable Long rollNo) {
        return studentService.getStudentByRollNo(rollNo);
    }
    @PutMapping("/{rollNo}")
    public ResponseEntity<Student> updateStudentByRollNo(@PathVariable Long rollNo, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = studentService.getStudentByRollNo(rollNo);

        if (existingStudent.isPresent()) {
            // If the student exists, update their information
            updatedStudent.setRollNo(rollNo); // Ensure the rollNo remains the same
            Student savedStudent = studentService.updateStudent(updatedStudent);
            return ResponseEntity.ok(savedStudent); // Return the updated student with 200 OK status
        } else {
            // If no student is found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // Delete student by roll number
    @DeleteMapping("/{rollNo}")
    public void deleteStudent(@PathVariable Long rollNo) {
        studentService.deleteStudent(rollNo);
    }
    @GetMapping("/second-highest")
    public ResponseEntity<?> getSecondHighestMarksStudent() {
        Optional<Student> student = studentService.getSecondHighestMarksStudent();

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No student found with second highest marks.");
        }
    }

}
