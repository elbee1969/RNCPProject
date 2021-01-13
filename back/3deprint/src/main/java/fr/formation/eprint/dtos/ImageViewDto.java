package fr.formation.eprint.dtos;

public interface ImageViewDto {

	Long getId();
	
	String getName();

    String getType();

    String getUrl();
    
    String getStatus();
    
    int getQuantity();

    Long getCustomUserId();

}
