package com.aws.codestar.projecttemplates.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config 
{
    @Value("${aws.access.key.id}")
    private String awsKeyId;

    @Value("${aws.access.key.secret}")
    private String awsKeySecret;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;
    

    @Bean(name = "awsKeyId")
    public String getAWSKeyId() {
        return awsKeyId;
    }

    @Bean(name = "awsKeySecret")
    public String getAWSKeySecret() {
        return awsKeySecret;
    }

    @Bean(name = "awsRegion")
    public Region getAWSPollyRegion() {
        return Region.getRegion(Regions.fromName(awsRegion));
    }

    @Bean(name = "awsCredentialsProvider")
    public AWSCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.awsKeyId, this.awsKeySecret);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
    
    @Bean(name = "awsS3Bucket")
    public String getAWSSBucket() {
        return awsS3Bucket;
    }
    
    @Bean
    public AmazonS3 s3client() {
    	AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
    			.withRegion(Regions.fromName(awsRegion))
    			.withCredentials(this.getAWSCredentials())
    			.build();
		return s3Client;
    }

    
}