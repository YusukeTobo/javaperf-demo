package com.example.demo;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.Sleep;
import com.example.demo.helper.Divide;

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

	private static final long SLEEP_SEC = 5;

	@RequestMapping("sleep")
	public String doSleep() throws InterruptedException {
		Thread.sleep(SLEEP_SEC * 1000);
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
		var executors = Executors.newFixedThreadPool(10);
		var sleep = new Sleep();

		for (int i=0; i<10; ++i) {
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
