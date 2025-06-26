package com.prototype.erpsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ErpAttendanceSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpAttendanceSyncApplication.class, args);
	}

}
