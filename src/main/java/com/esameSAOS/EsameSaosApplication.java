package com.esameSAOS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.esameSAOS","com.esameSAOS.config","com.esameSAOS.controller"})
@EnableJpaRepositories("com.esameSAOS.repository")
public class EsameSaosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsameSaosApplication.class, args);
	}

}
