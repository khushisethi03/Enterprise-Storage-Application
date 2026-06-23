package com.app.service;

import java.util.List;

import com.app.dto.BucketRequest;

public interface BucketService {

	void createBucket(BucketRequest bucketRequest);
	
	boolean bucketExists(String bucketName);
	
	List<String> listBuckets();
	
	void deleteBucket(String bucketName);
}
