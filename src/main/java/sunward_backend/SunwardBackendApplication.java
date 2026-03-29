package sunward_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // BaseTimeEntity 자동화 설정
@SpringBootApplication
public class SunwardBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunwardBackendApplication.class, args);
	}

}
