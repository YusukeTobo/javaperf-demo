package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("")
	public String index() {
		return "running.";
	}

	private static final int STRENGTH = 17;

	@RequestMapping("userLoad")
	public int userModeLoad() {
		return BCrypt.hashpw("foobar", BCrypt.gensalt(STRENGTH)).length();
	}
}
