package com.aws.codestar.projecttemplates.model;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "files")
public class File {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "file_name")
    private String fileName;
    

    @Column(name = "path")
    private String path;
    
    @Column(name = "create_date")
    private String createDate;

    
    @Column(name = "update_date")
    private String updateDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }
     
    public String getPath() {
    	return path;
    }
    
    public String getCreateDate() {
    	return createDate;
    }
    
    public String getUpdateDate() {
    	return updateDate;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPath (String path) {
    	this.path = path;	
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setCreateDate(String createDate) {
    	this.createDate = createDate;
    }
    
    public void setUpdateDate(String updateDate) {
    	this.updateDate = updateDate;
    }
}