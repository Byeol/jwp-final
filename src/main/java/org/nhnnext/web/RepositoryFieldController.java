package org.nhnnext.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.rest.core.mapping.ResourceType;
import org.springframework.data.rest.core.mapping.SupportedHttpMethods;
import org.springframework.data.rest.webmvc.RootResourceInformation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collection;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public abstract class RepositoryFieldController<T extends AbstractPersistable<Long>, K extends Serializable> {

	protected static final String BASE_MAPPING = "/repos/{repoId}";

	private final RepositoryEntityService<T, K> service;
	private final RepoRepository repoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<?> optionsForCollectionResource(RootResourceInformation information) {
		HttpHeaders headers = new HttpHeaders();
		SupportedHttpMethods supportedMethods = information.getSupportedMethods();

		headers.setAllow(supportedMethods.getMethodsFor(ResourceType.COLLECTION));

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getCollectionResource(@PathVariable Long repoId) {
		Repo repo = repoRepository.findOne(repoId);

		Collection<T> entities = service.findAll(repo);

		return ResponseEntity.ok(entities);
	}
}
