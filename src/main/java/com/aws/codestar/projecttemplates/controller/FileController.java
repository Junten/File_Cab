package com.aws.codestar.projecttemplates.controller;

import com.aws.codestar.projecttemplates.model.File;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.service.FileService;
import com.aws.codestar.projecttemplates.service.UserDetailsServiceImpl;
import com.aws.codestar.projecttemplates.service.UserService;

import java.io.ByteArrayOutputStream;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@RestController
public class FileController {
	
	@Autowired
	FileService fileService;
	
    @DeleteMapping("/file/delete/{fileName}")
    public String uploadMultipartFile(@PathVariable String fileName) {
    	try {
    		fileService.deleteFile(fileName);	
    	}catch(Exception e) {
    		return "Cannot Delete File -> Keyname = " + fileName;
    	}
    	return "Delete Successfully -> Keyname = " + fileName;
    }   
    
    @GetMapping("/file/{fileName}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
		ByteArrayOutputStream downloadInputStream = fileService.downloadFile(fileName);
	
		return ResponseEntity.ok()
					.contentType(contentType(fileName))
					.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileName + "\"")
					.body(downloadInputStream.toByteArray());	
	}
	
	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length-1];
		switch(type) {
			case "txt": return MediaType.TEXT_PLAIN;
			case "png": return MediaType.IMAGE_PNG;
			case "jpg": return MediaType.IMAGE_JPEG;
			default: return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
	
	@PostMapping("/file/upload")
    public String uploadMultipartFile(
    		@RequestParam("description") String description,
    		HttpServletRequest request, 
    		RedirectAttributes redirectAttributes,
    		@RequestParam("uploadFile") MultipartFile file,
    		Model model) {
		
//		User uTemp = (User)request.getSession().getAttribute("currentSessionUser");
		User uTemp = new User();
		uTemp.setId((long)1);
		uTemp.setActive(1);
		uTemp.setUsername("sai1210");
		LocalDateTime time= LocalDateTime.now();
		String createDate= time.toString();
		String fileName = file.getOriginalFilename();
		String updateDate = createDate;
		fileService.saveFile(description, fileName, uTemp, createDate, updateDate);
    	fileService.uploadFile(fileName, file);
		return "File Upload Successfully";
    }  
}
