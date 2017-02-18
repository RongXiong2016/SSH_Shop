package com.ssh.shop.model;
//Model里需要有三个属性：文件、文件类型和文件名

import java.io.File;

public class FileImage {
	//文件
	private File file;
	//文件类型
	private String contentType;
	//文件名
	private String filename;
	
	public File getFile() {
		return file;
	}
	 
	public String getContentType() {
		return contentType;
	}
	 
	public String getFileName() {
		return filename;
	}
	 
	public void setUpload(File file) {
		this.file = file;
	}
	
	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

}
