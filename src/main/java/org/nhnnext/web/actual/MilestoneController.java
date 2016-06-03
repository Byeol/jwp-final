package org.nhnnext.web.actual;

import org.nhnnext.domain.actual.Milestone;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.MilestoneService;
import org.nhnnext.web.RepositoryEntityController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@RepositoryRestController
@RequestMapping(MilestoneController.BASE_MAPPING)
public class MilestoneController extends RepositoryEntityController<Milestone, Integer> {

	protected static final String BASE_MAPPING = RepositoryEntityController.BASE_MAPPING + "/milestones";

	@Inject
	public MilestoneController(MilestoneService service, RepoRepository repoRepository) {
		super(service, repoRepository);
	}
}
