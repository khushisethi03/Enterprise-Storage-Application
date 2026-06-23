package com.app.dto;

public class BucketRequest {

	private String bucketName;

	public BucketRequest() {
	}

	public BucketRequest(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

}
