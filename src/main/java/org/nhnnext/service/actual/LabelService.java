package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.Label;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.LabelRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LabelService extends RepositoryEntityService<Label, String> {

	private final LabelRepository repository;

	@Inject
	public LabelService(LabelRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public Collection<Label> findAll(Repo repo) {
		if (repo == null) {
			throw new IllegalArgumentException();
		}
		return repo.getLabels().values();
	}

	public Label findOne(Repo repo, String name) {
		if (repo == null) {
			throw new IllegalArgumentException();
		}
		return repo.getLabel(name);
	}

	public void save(Repo repo, Label entity) {
		repo.putLabel(entity);
		save(entity);
	}

	public void saveAndFlush(Repo repo, Label entity) {
		repo.putLabel(entity);
		saveAndFlush(entity);
	}
}
