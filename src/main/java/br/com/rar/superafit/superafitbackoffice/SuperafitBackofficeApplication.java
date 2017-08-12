package br.com.rar.superafit.superafitbackoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuperafitBackofficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperafitBackofficeApplication.class, args);
	}
}
