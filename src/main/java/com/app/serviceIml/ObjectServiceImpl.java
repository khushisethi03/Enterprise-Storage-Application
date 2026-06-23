package com.app.serviceIml;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CopyRequest;
import com.app.dto.MoveRequest;
import com.app.entity.FileMetadata;
import com.app.exception.FileUploadException;
import com.app.exception.MetadataNotFoundException;
import com.app.repository.FileMetadataRepository;
import com.app.service.ObjectService;
import com.app.util.ChecksumUtil;
import com.app.util.FileStatus;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

@Service
public class ObjectServiceImpl implements ObjectService{

	private final S3Client s3Client;
    private final FileMetadataRepository repository;

    public ObjectServiceImpl(S3Client s3Client, FileMetadataRepository repository) {
        this.s3Client = s3Client;
        this.repository = repository;
    }
	
	@Override
	public FileMetadata upload(MultipartFile file, String bucketName, String uploadBy) {
		
		try {
			String originalName = file.getOriginalFilename();
	        String extension = "";
	        int index = originalName.lastIndexOf(".");

	        if (index != -1) {
	            extension = originalName.substring(index);
	        }
	        
	        
	        String storedName = UUID.randomUUID() + extension;
	        String checksum = ChecksumUtil.sha256(file.getInputStream());
	        
	        
	        PutObjectRequest request = PutObjectRequest.builder()
	        									.bucket(bucketName)
	        									.key(storedName)
	        									.contentType(file.getContentType())
	        									.build();
	        
	        PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
	        
	        FileMetadata metaData = new FileMetadata();
	        metaData.setOriginalName(originalName);
	        metaData.setStoredName(storedName);
	        metaData.setBucketName(bucketName);
	        metaData.setFileSize(file.getSize());
	        metaData.setContentType(file.getContentType());
	        metaData.setUploadedBy(uploadBy);
	        metaData.setUploadedAt(Instant.now());
	        metaData.setChecksum(checksum);
	        metaData.setEtag(response.eTag());
	        metaData.setStatus(FileStatus.ACTIVE);
	        
	        
	        return repository.save(metaData);
		}
		catch(Exception e) {
			throw new FileUploadException(e.getMessage());
		}
	
	}
	
	@Override
	public List<String> listObjects(String bucketName){

		ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();
		
		ListObjectsV2Response response = s3Client.listObjectsV2(request);
		
		List<S3Object> s3objects = response.contents();
		
		List<String> objects = new ArrayList<>();
		
		for(S3Object x : response.contents()) {
			objects.add(x.key());
		}

		return objects;
	}
	
	@Override
	public void delete(String metadataId) {
		
		FileMetadata metaData = repository.findById(metadataId).orElseThrow(() -> new MetadataNotFoundException(metadataId) );
		
		DeleteObjectRequest request = DeleteObjectRequest.builder()
											.bucket(metaData.getBucketName())
											.key(metaData.getStoredName())
											.build();
		
		s3Client.deleteObject(request);
		
		repository.delete(metaData);
	}
	
	
	@Override
    public void copy(CopyRequest request) {

        CopyObjectRequest copyRequest = CopyObjectRequest.builder().sourceBucket(request.getSourceBucket()).sourceKey(request.getObjectKey()).destinationBucket(request.getTargetBucket()).destinationKey(request.getObjectKey()).build();

        s3Client.copyObject(copyRequest);
    }
	
	
	@Override
    public void move(MoveRequest request) {

        CopyRequest copyRequest = new CopyRequest();

        copyRequest.setSourceBucket(request.getSourceBucket());

        copyRequest.setTargetBucket(request.getTargetBucket());

        copyRequest.setObjectKey(request.getObjectKey());

        copy(copyRequest);

        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
        										.bucket(request.getSourceBucket())
        										.key(request.getObjectKey())
        										.build();

        s3Client.deleteObject(deleteRequest);

        
        FileMetadata metaData = repository.findBystoredName(request.getObjectKey())
        							.orElseThrow(() -> new MetadataNotFoundException(request.getObjectKey()));

        metaData.setBucketName(request.getTargetBucket());
        
        repository.save(metaData);
	
	}
	
}
