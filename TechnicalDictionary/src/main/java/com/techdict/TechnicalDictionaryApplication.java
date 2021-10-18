package com.techdict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.techdict.app.controller.AdminController;
import com.techdict.app.controller.SecurityController;
import com.techdict.app.dao.UserRepository;

@EnableJpaRepositories("com.techdict.app.dao")
@EnableAutoConfiguration
@SpringBootApplication

@ComponentScan(basePackageClasses = AdminController.class)
@ComponentScan(basePackageClasses = SecurityController.class)
@EnableTransactionManagement
public class TechnicalDictionaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalDictionaryApplication.class, args);
	}

}
