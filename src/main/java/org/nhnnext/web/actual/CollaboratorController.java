package org.nhnnext.web.actual;

import org.nhnnext.domain.actual.User;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.CollaboratorService;
import org.nhnnext.web.RepositoryFieldController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RepositoryRestController
@RequestMapping(CollaboratorController.BASE_MAPPING)
public class CollaboratorController extends RepositoryFieldController<User, String> {

	protected static final String BASE_MAPPING = RepositoryFieldController.BASE_MAPPING + "/collaborators";

	@Inject
	public CollaboratorController(CollaboratorService service, RepoRepository repoRepository) {
		super(service, repoRepository);
	}
}
