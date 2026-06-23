package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BucketRequest;
import com.app.service.BucketService;

@RequestMapping("/api/buckets")
@RestController
public class BucketController {

	@Autowired
	private BucketService bucketService;

	@PostMapping
	public void createBucket(@RequestBody BucketRequest bucketRequest) {
		bucketService.createBucket(bucketRequest);
	}

	@GetMapping
	public List<String> listBuckets() {
		return bucketService.listBuckets();
	}
	
	
	@DeleteMapping("/{bucketName}")
	public String deleteBucket(@PathVariable("bucketName") String bucketName) {
		bucketService.deleteBucket(bucketName);
		
		return "Bucket delete Successfull";
	}
}
