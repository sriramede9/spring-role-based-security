package com.sri.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Templatecontroller {

	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("courses")
	public String getCourses() {
		return "courses";
	}
}
