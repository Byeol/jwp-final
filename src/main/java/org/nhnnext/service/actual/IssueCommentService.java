package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.Comment;
import org.nhnnext.domain.actual.Issue;
import org.nhnnext.repository.CommentRepository;
import org.nhnnext.service.IssueEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class IssueCommentService extends IssueEntityService<Comment,Long> {

	private final CommentRepository repository;
	private final CommentService service;

	@Inject
	public IssueCommentService(CommentRepository repository, CommentService service) {
		super(repository);
		this.repository = repository;
		this.service = service;
	}

	public Comment findOne(Issue collection, Long key) {
		return service.findOne(collection.getRepository(), key);
	}

	public Collection<Comment> findAll(Issue collection) {
		return repository.findByIssue(collection);
	}

	public void save(Issue collection, Comment entity) {
		entity.setIssue(collection);
		repository.save(entity);
	}

	public void save(Issue collection, Collection<Comment> entities) {
		entities.forEach(entity -> save(collection, entity));
	}

	public void saveAndFlush(Issue collection, Comment entity) {
		entity.setIssue(collection);
		repository.saveAndFlush(entity);
	}

	public void saveAndFlush(Issue collection, Collection<Comment> entities) {
		entities.forEach(entity -> save(collection, entities));
	}
}
