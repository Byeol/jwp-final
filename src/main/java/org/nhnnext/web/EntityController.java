package org.nhnnext.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.AuditableEntity;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.io.Serializable;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public abstract class EntityController<T extends AuditableEntity, K extends Serializable> {

	private final RepositoryEntityService<T, K> service;
	private final RepoRepository repoRepository;

	@RequestMapping(value = "/{key}", method = RequestMethod.GET)
	public ResponseEntity<?> getItemResource(@PathVariable Long repoId, @PathVariable K key) {
		T domainObject = getObject(repoId, key);

		if (domainObject == null) {
			throw new ResourceNotFoundException();
		}

		return ResponseEntity.ok(domainObject);
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.PATCH)
	public ResponseEntity<?> patchItemResource(@PathVariable Long repoId, @PathVariable K key, @RequestBody T newEntity) {
		T domainObject = getObject(repoId, key);

		if (domainObject == null) {
			throw new ResourceNotFoundException();
		}

		domainObject.update(newEntity);
		service.save(domainObject);

		return ResponseEntity.ok(domainObject);
	}

	@RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteItemResource(@PathVariable Long repoId, @PathVariable K key) {
		T domainObject = getObject(repoId, key);

		if (domainObject == null) {
			throw new ResourceNotFoundException();
		}

		service.delete(domainObject);

		return ResponseEntity.noContent().build();
	}

	private T getObject(Long repoId, K key) {
		Repo repo = repoRepository.findOne(repoId);

		if (repo == null) {
			throw new ResourceNotFoundException();
		}

		return service.findOne(repo, key);
	}
}
