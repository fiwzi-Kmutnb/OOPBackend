package net.fiwzi.oop.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {
		"net.fiwzi.oop"
})
public class Application  {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
