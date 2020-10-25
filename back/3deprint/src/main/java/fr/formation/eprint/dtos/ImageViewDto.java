package fr.formation.eprint.dtos;

public interface ImageViewDto {

	Long getId();
	
	String getName();

    String getType();

    byte[] getData();

    Long getCustomUserId();

}
