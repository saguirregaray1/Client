package com.example.clientTIC;

import com.example.clientTIC.ui.InitApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientTicApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ClientTicApplication.class, args);
		InitApplication.main(args);
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
