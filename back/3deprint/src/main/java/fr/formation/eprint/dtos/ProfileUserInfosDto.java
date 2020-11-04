package fr.formation.eprint.dtos;

public interface ProfileUserInfosDto {

	 Long getId();

	    String getUsername();

	    String getEmail();

	    String getFirstname();

	    String getLastname();
	    
	    int getNum();
	    
	    String getStreet();
	    
	    String getTown();
	    
	    String getCountry();

		CustomUserInfoDto orElseThrow(Object object);
}
