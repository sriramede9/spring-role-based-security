package com.sri.sec;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/v1/students")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentManagementController {

	private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "James Bond"),
			new Student(2, "Maria Jones"), new Student(3, "Anna Smith"));

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudents() {
		return STUDENTS;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void addStudent(@RequestBody Student student) {

		System.out.println("Adding student " + student);
	}

	@PutMapping("/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {

		System.out.println("Updating student");
	}

	@DeleteMapping("/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudentById(@PathVariable("studentId") Integer studentId) {
		System.out.println("deleting student with id ");
	}

}
