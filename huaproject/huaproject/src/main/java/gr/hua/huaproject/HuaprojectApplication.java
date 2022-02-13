package gr.hua.huaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class HuaprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HuaprojectApplication.class, args);
	}

	@GetMapping
	public String homePage(){
		return "global-home";
	}




}
