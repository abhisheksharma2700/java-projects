package com.busbookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.busbookingsystem")
public class BusBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusBookingSystemApplication.class, args);
	}

}
