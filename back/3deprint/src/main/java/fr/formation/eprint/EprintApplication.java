package fr.formation.eprint;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import fr.formation.eprint.services.ImageStorageService;

@SpringBootApplication
public class EprintApplication {
	@Resource
	ImageStorageService imageStorageService;

	public static void main(String[] args) {

		SpringApplication.run(EprintApplication.class, args);
		/**
		 * DECLARATION OF DEFAULT FILESYSTEM
		 */
		FileSystem fs = FileSystems.getDefault();

		/**
		 * declaration of the root directory
		 */
		Path path = fs.getPath("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets");

		/**
		 * create directory first time
		 */
		System.out.println(path);
		File directory = new File(path + "/uploads");
		if (directory.exists()) {
			System.out.println("A folder with name 'uploads' is already exist in the path " + path);
		} else {
			directory.mkdir();
		}

	}

}
