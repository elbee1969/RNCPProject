package fr.formation.eprint.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageDto {

    @NotBlank
    @Max(255)
    private String name;

    @NotBlank
    @Max(255)
    private String type;

    @NotNull
    private byte[] data;

    private Long customUserId;

    public ImageDto() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ImageDto(@NotBlank @Max(255) String name, @NotBlank @Max(255) String type, @NotNull byte[] data,
	    Long customUserId) {
	super();
	this.name = name;
	this.type = type;
	this.data = data;
	this.customUserId = customUserId;
    }

    public String getName() {
	MultipartFile file = null;
	name = StringUtils.cleanPath(file.getOriginalFilename());
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public byte[] getData() {
	return data;
    }

    public void setData(byte[] data) {
	this.data = data;
    }

    public Long getCustomUserId() {
	return customUserId;
    }

    public void setCustomUserId(Long customUserId) {
	this.customUserId = customUserId;
    }

}
