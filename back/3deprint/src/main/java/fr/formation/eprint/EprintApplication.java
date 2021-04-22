package fr.formation.eprint;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.eprint.services.ImageStorageService;
import fr.formation.eprint.utility.ApplicationPath;

@SpringBootApplication
public class EprintApplication {
	@Resource
	ImageStorageService imageStorageService;

	public static void main(String[] args) {

		SpringApplication.run(EprintApplication.class, args);

		/*
		 * Verification or creation of the user upload path at launching's application
		 */
		ApplicationPath myPath = new ApplicationPath();
		myPath.fetch();
	}

}
