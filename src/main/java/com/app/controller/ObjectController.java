package com.app.controller;

import java.io.InputStream;
import java.util.List;

import com.app.dto.RenameRequest;
import com.app.exception.MetadataNotFoundException;
import com.app.repository.FileMetadataRepository;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
	private final FileMetadataRepository repository;

	public ObjectController(ObjectService objectService, FileMetadataRepository repository) {
		super();
		this.objectService = objectService;
		this.repository = repository;
	}

	@PostMapping("/upload")
	public FileMetadata upload(@RequestParam MultipartFile file, @RequestParam String bucketName,
			@RequestParam String uploadedBy) {

		return objectService.upload(file, bucketName, uploadedBy);
	}
	@GetMapping("/download/{id}")
	public ResponseEntity<InputStreamResource> download(@PathVariable String id) {

		FileMetadata metadata = repository.findById(id).orElseThrow(() -> new MetadataNotFoundException(id));

		InputStream stream = objectService.download(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + metadata.getOriginalName() + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(stream));
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

	@PostMapping("/rename")
	public ResponseEntity<String> rename(@Valid @RequestBody RenameRequest request) {
		objectService.rename(request);
		return ResponseEntity.ok("Renamed Successfully");
	}
}
