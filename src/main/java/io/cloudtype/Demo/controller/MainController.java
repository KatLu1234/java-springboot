package io.cloudtype.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.cloudtype.Demo.template.Template;

@Controller
@RequestMapping
public class MainController {
	
	@GetMapping
	public String testData() {
		System.out.println("TestOut");
		System.out.println((new Template().simpleText("helloworld!")) == null);
		return "testOut";
	}
	
	@PostMapping
	public String getTestData(@RequestBody String requested) {
		
		System.out.println("testOut");
		
		Template template = new Template();
		template.simpleText("Hello world!");
		
		return template.toString();
	}

}
