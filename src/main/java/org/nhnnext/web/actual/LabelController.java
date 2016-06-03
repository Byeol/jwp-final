package org.nhnnext.web.actual;

import org.nhnnext.domain.actual.Label;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.LabelService;
import org.nhnnext.web.RepositoryEntityController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RepositoryRestController
@RequestMapping(LabelController.BASE_MAPPING)
public class LabelController extends RepositoryEntityController<Label, String> {

	protected static final String BASE_MAPPING = RepositoryEntityController.BASE_MAPPING + "/labels";

	@Inject
	public LabelController(LabelService service, RepoRepository repoRepository) {
		super(service, repoRepository);
	}
}
