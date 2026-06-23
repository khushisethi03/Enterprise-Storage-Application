package com.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CopyRequest;
import com.app.dto.MoveRequest;
import com.app.entity.FileMetadata;
import com.app.service.ObjectService;

@RestController
@RequestMapping("/api/objects")
public class ObjectController {

	private final ObjectService objectService;

	public ObjectController(ObjectService objectService) {
		super();
		this.objectService = objectService;
	}

	@PostMapping("/upload")
	public FileMetadata upload(@RequestParam MultipartFile file, @RequestParam String bucketName,
			@RequestParam String uploadedBy) {

		return objectService.upload(file, bucketName, uploadedBy);
	}

	@GetMapping
	public List<String> listObjects(@RequestParam String bucketName) {
		return objectService.listObjects(bucketName);
	}

	@DeleteMapping("/{metadataId}")
	public ResponseEntity<String> delete(@PathVariable("metadataId") String metadataId) {
		objectService.delete(metadataId);

		return ResponseEntity.ok("Deleted Successfully");
	}

	@PostMapping("/copy")
	public ResponseEntity<String> copy(@RequestBody CopyRequest request) {
		objectService.copy(request);

		return ResponseEntity.ok("Copied Successfully");
	}
	
	
	@PostMapping("/move")
    public ResponseEntity<String> move(@RequestBody MoveRequest request) {
        objectService.move(request);
        return ResponseEntity.ok("Moved Successfully");
    }
	
	
}
