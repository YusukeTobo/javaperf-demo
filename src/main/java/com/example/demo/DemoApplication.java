package com.example.demo;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.Sleep;
import com.example.demo.helper.Divide;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class DemoApplication {
	private int bcryptStrength = 17;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public DemoApplication() {
		try {
			this.bcryptStrength = Integer.parseInt(System.getenv("BCRYPT_STRENGTH"));
		} catch (NumberFormatException ex) {
			// do nothing.
		}
	}

	@RequestMapping("")
	public String index() {
		return "running.";
	}

	@RequestMapping("userLoad")
	public int userModeLoad() {
		return BCrypt.hashpw("foobar", BCrypt.gensalt(this.bcryptStrength)).length();
	}

	@RequestMapping("sleep")
	public String doSleep(@RequestParam String seconds) throws InterruptedException {
		long sleepSec = 5;
		try {
			sleepSec = Long.parseLong(seconds);
		} catch (NumberFormatException ex) {
			// do nothing.
		}
		Thread.sleep(sleepSec * 1000);
		return "done.";
	}

	private static final String SLEEP_ENDPOINT = "http://localhost:8080/sleep";
	@RequestMapping("testHttp")
	public int testHttpLegacyClient() {
		int status = 0;
		try {
			HttpURLConnection httpcon = (HttpURLConnection) new URI(SLEEP_ENDPOINT).toURL().openConnection();
			httpcon.setReadTimeout(10*1000);
			status = httpcon.getResponseCode();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	@RequestMapping("blocking")
	public String blcoking() {
		var executors = Executors.newFixedThreadPool(3);
		var sleep = new Sleep();

		for (int i=0; i<3; ++i) {
			executors.submit(sleep::doSleep);
		}

		executors.close();

		return "Blocked.";
	}

	@RequestMapping("crash")
	public void crash() {
		new Divide().doDivide(10);
	}
}
