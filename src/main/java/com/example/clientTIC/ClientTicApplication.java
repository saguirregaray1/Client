package com.example.clientTIC;

import com.example.clientTIC.spring.AppService;
import com.example.clientTIC.spring.ApplicationContextProvider;
import com.example.clientTIC.ui.InitApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientTicApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ClientTicApplication.class, args);
		AppService appService = ApplicationContextProvider.getApplicationContext().getBean(AppService.class);
	//	appService.insertCosas();  		//Comentar luego de correr por primera vez
		InitApplication.main(args);
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
