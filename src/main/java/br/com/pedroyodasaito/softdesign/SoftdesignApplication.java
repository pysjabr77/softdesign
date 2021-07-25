package br.com.pedroyodasaito.softdesign;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SoftdesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftdesignApplication.class, args);
	}

}
