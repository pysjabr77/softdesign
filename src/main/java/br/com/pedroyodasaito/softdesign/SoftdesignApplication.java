package br.com.pedroyodasaito.softdesign;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class SoftdesignApplication {

	public static final String exchangeName = "softdesign_teste_exchange";

	public static final String filaName = "softdesign_teste";

	public static void main(String[] args) {
		SpringApplication.run(SoftdesignApplication.class, args);
	}

	@Bean
	Queue queue() {
		return new Queue(filaName, true);
	}
}
