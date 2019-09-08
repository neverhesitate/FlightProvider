package com.flightprovider.flightproviderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.flightprovider"})
public class FlightProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightProviderApplication.class, args);
	}
}
