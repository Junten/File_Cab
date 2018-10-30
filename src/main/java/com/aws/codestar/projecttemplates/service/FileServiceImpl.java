package com.aws.codestar.projecttemplates.service;

import com.aws.codestar.projecttemplates.model.File;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.repository.FileRepository;
import com.aws.codestar.projecttemplates.service.FileService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;

@Service
public class FileServiceImpl implements FileService {
    
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    
    @Autowired
    private FileRepository fileRepository;
    
    @Autowired
    private AmazonS3 s3client;
    
    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;
    
    
    @Override
    public void uploadFile(String fileName, MultipartFile file) {
    	int maxSize = 10485760;
    	if (file.getSize() > maxSize) {
			throw new ValidationException("File Size larger than 10MB");
		}
    	
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
    		
            s3client.putObject(
            		new PutObjectRequest(awsS3Bucket, fileName, file.getInputStream(), metadata)
            			.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(IOException ioe) {
            logger.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
            throw ace;
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
//        	
//        	List<File> fileList = fileRepository.findAll();
//        	for(File file: fileList) {
//        		if (file.getFileName() == fileName) {
//        			fileRepository.deleteById((long)file.getId());
//        		}
//        	}
//        	fileRepository.deleteById((long)13);
            s3client.deleteObject(new DeleteObjectRequest(awsS3Bucket, fileName));
        } catch(AmazonServiceException ase) {
            logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (SdkClientException sce) {
            logger.info("Caught an SdkClientException: ");
            logger.info("Error Message: " + sce.getMessage());
            throw sce;
        }
    }

    @Override
    public ByteArrayOutputStream downloadFile(String fileName) {
        try {
            S3Object s3object = s3client.getObject(new GetObjectRequest(awsS3Bucket, fileName));
            
            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, len);
            }
            
            return baos;
        } catch (IOException ioe) {
            logger.error("IOException: " + ioe.getMessage());
        } catch (AmazonServiceException ase) {
            logger.info("sCaught an AmazonServiceException from GET requests, rejected reasons:");
            logger.info("Error Message:    " + ase.getMessage());
            logger.info("HTTP Status Code: " + ase.getStatusCode());
            logger.info("AWS Error Code:   " + ase.getErrorCode());
            logger.info("Error Type:       " + ase.getErrorType());
            logger.info("Request ID:       " + ase.getRequestId());
            throw ase;
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());
            throw ace;
        }
        return null;
    }
    
    @Override
    public void saveFile(String description, String fileName, User user, String createDate, String updateDate) {
    	
    	File file = new File();
    	String path = "daenrptbvbfth.cloudfront.net/" + fileName;
        file.setFileName(fileName);
        file.setDescription(file.getDescription());
        file.setPath(path);
        file.setUser(user);
        file.setCreateDate(createDate);
        file.setUpdateDate(updateDate);
        fileRepository.save(file);
    }
    
    public List<File> listFiles() {
		
  		return fileRepository.findAll();
  	}
    
}
