package fr.formation.eprint;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EprintApplication {

	public static void main(String[] args) {
		SpringApplication.run(EprintApplication.class, args);
		
		  String dir = System.getProperty("user.dir");

		  // directory from where the program was launched
		  System.out.println(dir);
		  String path = System.getProperty("user.dir");
	       File directory=new File(path+"/imagesusers");
	       if(directory.exists()){
	           System.out.println("A folder with name 'imagesusers' is already exist in the path "+path);
	       }else{
	    	   directory.mkdir();
	       }
	}

}
