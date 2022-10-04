package com.example.clientTIC;

import com.example.clientTIC.UI.InitApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientTicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientTicApplication.class, args);
		InitApplication.main(args);
	}

}
