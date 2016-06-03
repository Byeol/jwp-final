package org.nhnnext.web;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.AuditableEntity;
import org.nhnnext.domain.actual.Issue;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.IssueEntityService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public abstract class IssueEntityController<T extends AuditableEntity, K extends Serializable>  {

	protected static final String BASE_MAPPING = "/repos/{repoId}/issues/{number}";

	private final IssueEntityService<T, K> service;
	private final RepoRepository repoRepository;

	public ResponseEntity<?> getCollectionResource(@PathVariable Long repoId, @PathVariable Integer number) {
		Issue issue = getIssue(repoId, number);
		Collection<T> entities = service.findAll(issue);

		return ResponseEntity.ok(entities);
	}

	public ResponseEntity<?> postCollectionResource(@PathVariable Long repoId, @PathVariable Integer number, @RequestBody T entity) {
		Issue issue = getIssue(repoId, number);

		service.saveAndFlush(issue, entity);

		return ResponseEntity.ok(entity);
	}

	public ResponseEntity<?> postCollectionResource(@PathVariable Long repoId, @PathVariable Integer number, @RequestBody Collection<K> keys) {
		Issue issue = getIssue(repoId, number);

		List<T> entities = new ArrayList<>();
		keys.forEach(key -> entities.add(service.findOne(issue, key)));

		service.save(issue, entities);

		return ResponseEntity.ok(entities);
	}

	private Issue getIssue(Long repoId, Integer number) {
		try {
			Repo repo = Optional.of(repoRepository.findOne(repoId)).get();
			return Optional.of(repo.getIssue(number)).get();
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException();
		}
	}
}
