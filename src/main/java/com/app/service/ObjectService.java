package com.app.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CopyRequest;
import com.app.dto.MoveRequest;
import com.app.dto.RenameRequest;
import com.app.entity.FileMetadata;

public interface ObjectService {
	
	FileMetadata upload(MultipartFile file, String bucketName, String uploadBy);
	
//	InputStream download(String metadataId);
	
	void delete(String metadataId);
	
	List<String> listObjects(String bucketName);
	
	void copy(CopyRequest copyRequest);
	
	void move(MoveRequest moveRequest);
	
//	void rename(RenameRequest renameRequest);
}
