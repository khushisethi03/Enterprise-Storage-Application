package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.entity.FileMetadata;

public interface FileMetadataRepository extends MongoRepository<FileMetadata, String> {

	List<FileMetadata> findByUploadedBy(String uploadedBy);

	List<FileMetadata> findByStatus(String status);

	List<FileMetadata> findByBucketName(String bucketName);

	List<FileMetadata> findByOriginalNameContainingIgnoreCase(String name);
	
	Optional<FileMetadata> findBystoredName(String storedName);
}