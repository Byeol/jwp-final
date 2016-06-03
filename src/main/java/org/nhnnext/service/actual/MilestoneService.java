package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.Milestone;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.MilestoneRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MilestoneService extends RepositoryEntityService<Milestone, Integer> {

	private final MilestoneRepository repository;

	@Inject
	public MilestoneService(MilestoneRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public Collection<Milestone> findAll(Repo repo) {
		if (repo == null) {
			throw new IllegalArgumentException();
		}
		return repo.getMilestones();
	}

	public Milestone findOne(Repo repo, Integer number) {
		if (repo == null) {
			throw new IllegalArgumentException();
		}
		return repo.getMilestone(number);
	}

	public void save(Repo repo, Milestone entity) {
		repo.putMilestone(entity);
		save(entity);
	}

	public void saveAndFlush(Repo repo, Milestone entity) {
		repo.putMilestone(entity);
		saveAndFlush(entity);
	}
}
