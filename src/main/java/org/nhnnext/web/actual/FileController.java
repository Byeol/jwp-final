package org.nhnnext.web.actual;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.actual.File;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
@RestController
@RequestMapping(FileController.BASE_MAPPING)
public class FileController {

	protected static final String BASE_MAPPING = "/repos/{repoId}/files";

	private final FileService service;
	private final RepoRepository repoRepository;

	@RequestMapping(value = "/{key}/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getItemResource(@PathVariable Long repoId, @PathVariable Long key) {
		File domainObject = getObject(repoId, key);

		if (domainObject == null) {
			throw new ResourceNotFoundException();
		}

		Resource resource = new ByteArrayResource(domainObject.getContent());
		return ResponseEntity.ok(resource);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postCollectionResource(@PathVariable Long repoId, @RequestParam MultipartFile file) {
		Repo repo = repoRepository.findOne(repoId);

		File entity = toFile(file);
		service.saveAndFlush(repo, entity);

		return ResponseEntity.ok(entity);
	}

	private File toFile(MultipartFile file) {
		File entity = new File();

		try {
			entity.setName(file.getName());
			entity.setContentType(file.getContentType());
			entity.setOriginalFilename(file.getOriginalFilename());
			entity.setSize(file.getSize());
			entity.setContent(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

	private File getObject(Long repoId, Long key) {
		Repo repo = repoRepository.findOne(repoId);

		if (repo == null) {
			throw new ResourceNotFoundException();
		}

		return service.findOne(repo, key);
	}
}
