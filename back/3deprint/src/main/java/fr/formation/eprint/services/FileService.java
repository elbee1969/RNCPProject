package fr.formation.eprint.services;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	@Resource
	ImageStorageService imageStorageService;

	@Value("${3deprint.myAppPath}")
	public String myAppPath;

	public void uploadFile(MultipartFile file) {

		/**
		 * DECLARATION OF DEFAULT FILESYSTEM
		 */
		FileSystem fs = FileSystems.getDefault();

		/**
		 * declaration of the root directory
		 */
		// Path path = fs.getPath("H:\\RNCPProject\\front\\Front3DePrint\\src\\assets");
		Path path = fs.getPath(myAppPath);

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
