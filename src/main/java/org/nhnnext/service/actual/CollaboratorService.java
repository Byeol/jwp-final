package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.Label;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.domain.actual.User;
import org.nhnnext.repository.LabelRepository;
import org.nhnnext.repository.UserRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class CollaboratorService extends RepositoryEntityService<User, String> {

	private final UserRepository repository;

	@Inject
	public CollaboratorService(UserRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public User findOne(Repo collection, String key) {
		User user = repository.findByUsername(key);

		if (!collection.hasCollaborator(user)) {
			return null;
		}

		return user;
	}

	@Override
	public Collection<User> findAll(Repo collection) {
		return collection.getCollaborators();
	}

	@Override
	public void save(Repo collection, User entity) {

	}

	@Override
	public void saveAndFlush(Repo collection, User entity) {

	}

//	public Collection<Label> findAll(Repo repo) {
//		if (repo == null) {
//			throw new IllegalArgumentException();
//		}
//		return repo.getLabels().values();
//	}
//
//	public Label findOne(Repo repo, String name) {
//		if (repo == null) {
//			throw new IllegalArgumentException();
//		}
//		return repo.getLabel(name);
//	}
//
//	public void save(Repo repo, Label entity) {
//		repo.putLabel(entity);
//		save(entity);
//	}
//
//	public void saveAndFlush(Repo repo, Label entity) {
//		repo.putLabel(entity);
//		saveAndFlush(entity);
//	}
}
