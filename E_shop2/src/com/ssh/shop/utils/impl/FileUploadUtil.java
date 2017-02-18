package com.ssh.shop.utils.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssh.shop.model.FileImage;
import com.ssh.shop.utils.FileUpload;
@Component("fileUpload")
public class FileUploadUtil implements FileUpload{
	@Value("#{prop.bankImagePath}")
	private String bankImagePath;
	
	public String[] getBankImage(){
		String[] list = new File(bankImagePath).list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				System.out.println("dir:"+dir+","+"name:"+name);
				return name.endsWith(".gif");
			}
		});
		return list;
	}
	
	private String filePath;
	
	@Value("#{prop.filePath}")
	 //@Value表示去beans.xml文件中找id="prop"的bean，它是通过注解的方式读取properties配置文件的，然后去相应的配置文件中读取key=filePath的值 
	public void setFilePath(String filePath) {
		System.out.println(filePath);
		this.filePath = filePath;
	}

	//1.通过文件名获得扩展名
	private String getFileExt(String filename){
		return FilenameUtils.getExtension(filename);
	}
	
	//2.生成UUID随机数，作为新的文件名
	private String newFileName(String fileName){
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString()+"."+ext;
	}
	
	//3.实现文件上传,返回新的文件名
	
	@Override
	public String uploadFile(FileImage fileImage) {
		//获取唯一的新文件名
		String pic = newFileName(fileImage.getFileName());
		
		try {
			//第一个参数是上传的文件，第二个参数是将文件拷贝到新路径下  
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;
		} catch (IOException e) {
			 throw new RuntimeException(e);  
        } finally {  
            fileImage.getFile().delete();  
        }  
	}

}
