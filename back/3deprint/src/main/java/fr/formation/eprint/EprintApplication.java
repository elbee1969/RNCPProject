package fr.formation.eprint;

import java.io.File;
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
		
		  String path = System.getProperty("user.dir");

		  // directory from where the program was launched
		  System.out.println(path);
	       File directory=new File(path+"/uploads");
	       if(directory.exists()){
	           System.out.println("A folder with name 'uploads' is already exist in the path "+path);
	       }else{
	    	   directory.mkdir();
	       }
	       
	}
	
	
}
