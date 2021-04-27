package fr.formation.eprint.utility;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPath {
	public ApplicationPath() {
	}

	public void fetch() {

		/**
		 * DECLARATION OF DEFAULT FILESYSTEM
		 */
		FileSystem fs = FileSystems.getDefault();

		/**
		 * declaration of the root directory
		 * C:\\Users\\utilisateur\\Documents\\GitHub\\RNCPProject\\front\\Front3DePrint\\src\\assets
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
