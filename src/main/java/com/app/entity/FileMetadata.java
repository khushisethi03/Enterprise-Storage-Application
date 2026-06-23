package com.app.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "file_metadata")
public class FileMetadata {

	@Id
	private String id;

	private String originalName;

	private String storedName;

	private String bucketName;

	private Long fileSize;

	private String contentType;

	private String uploadedBy;

	private Instant uploadedAt;

	private String etag;

	private String checksum;

	private String status;

	public FileMetadata() {
	}

	public FileMetadata(String id, String originalName, String storedName, String bucketName,
			Long fileSize, String contentType, String uploadedBy, Instant uploadedAt,
			String etag, String checksum, String status) {
		super();
		this.id = id;
		this.originalName = originalName;
		this.storedName = storedName;
		this.bucketName = bucketName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.uploadedBy = uploadedBy;
		this.uploadedAt = uploadedAt;
		this.etag = etag;
		this.checksum = checksum;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public Instant getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(Instant uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}