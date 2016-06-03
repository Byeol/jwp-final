package org.nhnnext.web;

import org.nhnnext.domain.RepositoryEntity;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.data.rest.core.mapping.ResourceType;
import org.springframework.data.rest.core.mapping.SupportedHttpMethods;
import org.springframework.data.rest.webmvc.RootResourceInformation;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.net.URI;
import java.util.Collection;

//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public abstract class RepositoryEntityController<T extends RepositoryEntity, K extends Serializable> extends EntityController<T, K> {

	protected static final String BASE_MAPPING = "/repos/{repoId}";

	private final RepositoryEntityService<T, K> service;
	private final RepoRepository repoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	public RepositoryEntityController(RepositoryEntityService<T, K> service, RepoRepository repoRepository) {
		super(service, repoRepository);
		this.service = service;
		this.repoRepository = repoRepository;
	}

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

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postCollectionResource(@PathVariable Long repoId, @RequestBody T entity) {
		Repo repo = repoRepository.findOne(repoId);

		service.saveAndFlush(repo, entity);
		repoRepository.saveAndFlush(repo);
		entityManager.refresh(entity);

		URI location = getLinkBuilder(entity).toUri();
		return ResponseEntity.created(location).body(entity);
	}

	private ControllerLinkBuilder getLinkBuilder(T entity) {
		return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getCollectionResource(entity.getRepository().getId()));
	}

}
