package com.spring.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> students;

	@PostConstruct
	public void loadData() {

		students = new ArrayList<>();

		students.add(new Student("sia", "kj"));
		students.add(new Student("jack", "fam"));
		students.add(new Student("murad", "aziz"));
	}

	// define endpoint for /students - return list of students
	@GetMapping("/students")
	public List<Student> getStudents() {

		return students;
	}

	// define endpoint for "/student/{student_id}" - return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {

		// error handling
		// check studentId against list size
		if ((studentId >= students.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		return students.get(studentId);
	}



}
