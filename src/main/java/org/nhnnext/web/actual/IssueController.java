package org.nhnnext.web.actual;

import org.nhnnext.domain.actual.Issue;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.IssueService;
import org.nhnnext.web.RepositoryEntityController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RepositoryRestController
@RequestMapping(IssueController.BASE_MAPPING)
public class IssueController extends RepositoryEntityController<Issue, Integer> {

	protected static final String BASE_MAPPING = RepositoryEntityController.BASE_MAPPING + "/issues";

	@Inject
	public IssueController(IssueService service, RepoRepository repoRepository) {
		super(service, repoRepository);
	}
}
