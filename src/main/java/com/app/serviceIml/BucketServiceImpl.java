package com.app.serviceIml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BucketRequest;
import com.app.exception.BucketNotFoundException;
import com.app.service.BucketService;
import com.app.validator.BucketValidator;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;

@Service
public class BucketServiceImpl implements BucketService {

	@Autowired
	private S3Client s3Client;

	@Override
	public void createBucket(BucketRequest bucketRequest) {
		String bucketName = bucketRequest.getBucketName();
		BucketValidator.validate(bucketName);

		CreateBucketRequest creationRequest = CreateBucketRequest.builder().bucket(bucketName).build();

		s3Client.createBucket(creationRequest);
	}

	@Override
	public List<String> listBuckets() {

		List<Bucket> buckets = s3Client.listBuckets().buckets();

		List<String> bucketNames = new ArrayList<>();

		for (Bucket bucket : buckets)
			bucketNames.add(bucket.name());

		return bucketNames;
	}

	@Override
	public boolean bucketExists(String bucketName) {

		try {
			HeadBucketRequest request = HeadBucketRequest.builder().bucket(bucketName).build();
			s3Client.headBucket(request);

			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}
	
	
	@Override
	public void deleteBucket(String bucketName) {
		BucketValidator.validate(bucketName);
		
		if(!bucketExists(bucketName))
			throw new BucketNotFoundException(bucketName);
		
		
		DeleteBucketRequest request = DeleteBucketRequest.builder().bucket(bucketName).build();
		
		s3Client.deleteBucket(request);
	}
	
	
	
}
