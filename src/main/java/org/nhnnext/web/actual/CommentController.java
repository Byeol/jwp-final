package org.nhnnext.web.actual;

import org.nhnnext.domain.actual.Comment;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.CommentService;
import org.nhnnext.web.EntityController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RepositoryRestController
@RequestMapping(CommentController.BASE_MAPPING)
public class CommentController extends EntityController<Comment, Long> {

	protected static final String BASE_MAPPING = "/repos/{repoId}/issues/comments";

	@Inject
	public CommentController(CommentService service, RepoRepository repoRepository) {
		super(service, repoRepository);
	}
}
