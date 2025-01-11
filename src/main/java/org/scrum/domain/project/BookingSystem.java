package org.scrum.domain.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import BD.Data_Base;

@SpringBootApplication
@EntityScan(basePackages = "org.scrum.domain.project")
@EnableJpaRepositories(basePackages = "org.scrum.domain.project.repository")
public class BookingSystem {

	public static void main(String[] args) {
		SpringApplication.run(BookingSystem.class, args);
		System.out.println("Aplicatia Sistem Bilete a pornit cu succes!");
	}
}