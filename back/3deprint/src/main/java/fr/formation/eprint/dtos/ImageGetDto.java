package fr.formation.eprint.dtos;

import fr.formation.eprint.entities.CustomUser;

public class ImageGetDto {
    private String name;
    private String url;
    private String type;
    private long size;
    private Long customUserId;

    public ImageGetDto(String name, String url, String type, long size, Long customUserId) {
	this.name = name;
	this.url = url;
	this.type = type;
	this.size = size;
	this.customUserId = customUserId;
    }

    public ImageGetDto(byte[] bytes, String contentType, String originalFilename, CustomUser user) {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public long getSize() {
	return size;
    }

    public void setSize(long size) {
	this.size = size;
    }

    public Long getCustomUserId() {
	return customUserId;
    }

    public void setCustomUserId(Long customUserId) {
	this.customUserId = customUserId;
    }

}