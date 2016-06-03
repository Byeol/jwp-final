package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.Comment;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.CommentRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Objects;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CommentService extends RepositoryEntityService<Comment,Long> {

	@Inject
	public CommentService(CommentRepository repository) {
		super(repository);
	}

	public Comment findOne(Repo repo, Long id) {
		Comment comment = findOne(id);

		if (!Objects.equals(comment.getIssue().getRepository(), repo)) {
			return null;
		}

		return comment;
	}

	// TODO
	public Collection<Comment> findAll(Repo collection) {
		return null;
	}

	public void save(Repo repo, Comment entity) {
		if (Objects.equals(entity.getIssue().getRepository(), repo)) {
			save(entity);
		}
	}

	public void saveAndFlush(Repo repo, Comment entity) {
		if (Objects.equals(entity.getIssue().getRepository(), repo)) {
			saveAndFlush(entity);
		}
	}
}
