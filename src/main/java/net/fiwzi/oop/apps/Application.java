package net.fiwzi.oop.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/")
	public String test() {
		for (int i = 0; i < 100000; i++)
			for (int j = 0; j < 100000; j++) {
				for (int k = 0; k < 100000; k++) {
					System.out.println(i);
				}
			}
		return "Hello World";
	}

}
