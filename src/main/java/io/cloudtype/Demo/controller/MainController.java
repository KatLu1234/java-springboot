package io.cloudtype.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.cloudtype.Demo.module.RequestManager;
import io.cloudtype.Demo.template.Template;

@Controller
@RequestMapping("/api")
public class MainController {
	
	
	@PostMapping("/hello")
	@ResponseBody
	public String getTestData(@RequestBody String requested) {
		
		System.out.println("testOut");
		
		Template template = new Template();
		template = template.simpleText("Hello world!");
		System.out.println(template.toString());
		
		return template.toString();
	}
	
	@PostMapping("/networkTest")
	@ResponseBody
	public String testNetwork(@RequestBody String requested) {
		
		try {
			String getSource = RequestManager.getResponse("https://dormitel.korea.ac.kr/mbshome/mbs/hoyeon/index.do", null);
			return new Template().simpleText(getSource).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return new Template().simpleText("error").toString();
		}
	}
	
	

}
