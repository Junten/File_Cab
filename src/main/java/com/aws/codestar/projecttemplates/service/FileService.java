package com.aws.codestar.projecttemplates.service;

import com.aws.codestar.projecttemplates.model.File;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aws.codestar.projecttemplates.model.User;

public interface FileService {
	public void uploadFile(String keyName, MultipartFile file);

    public void deleteFile(String fileName);
    
    public ByteArrayOutputStream downloadFile(String fileName);
    
    public void saveFile(String description, String fileName, User user, String createDate, String updateDate);
    
    public List<File> listFiles();
   
}
