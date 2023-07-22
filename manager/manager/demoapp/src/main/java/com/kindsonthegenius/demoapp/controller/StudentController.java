package com.kindsonthegenius.demoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kindsonthegenius.demoapp.entity.Student;
import com.kindsonthegenius.demoapp.service.StudentService;



@Controller

public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// handler method to handle list students and return mode and view
	@GetMapping("/student")
	public String listStudents(Model model) {
		model.addAttribute("student", studentService.getAllStudents());
		return "student";
	}

	@GetMapping("/student/new")
	public String createStudentForm(Model model) {
		// create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}
	
	@PostMapping("/student")
	public String saveStudent(@ModelAttribute("student") Student student) {
		// create student object to hold student form data
		studentService.saveStudent(student);
		return "redirect:/student";
	}
	
	@GetMapping("/student/edit/{id}")
	public String editStudentForm(@PathVariable Long id,Model model) {
		// create student object to hold student form data 
		model.addAttribute("student", studentService.getStudentById(id));
		
		return "edit_student";
	}
	
	
	@PostMapping("/student/{id}")
	public String updateStudent(@PathVariable Long id,@ModelAttribute("student") Student student,Model model) {
		// create student object to hold student form data 
		//get student from data from by id 
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
	
		// save updated student object 
		studentService.updateStudent(existingStudent);
		return "redirect:/student";
	}
	
	@GetMapping("/student/{id}")
	public String deleteStudent(@PathVariable Long id) {
		// create student object to hold student form data 
		studentService.deleteStudentById(id);
		
		return "redirect:/student";
	}
}