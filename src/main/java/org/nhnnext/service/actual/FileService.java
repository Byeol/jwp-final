package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.File;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.FileRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Objects;

@Service
public class FileService extends RepositoryEntityService<File, Long> {

	private final FileRepository repository;

	@Inject
	public FileService(FileRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public File findOne(Repo collection, Long key) {
		File entity = findOne(key);

		if (collection == null || entity == null ||
				!Objects.equals(entity.getRepository(), collection)) {
			return null;
		}

		return entity;
	}

	public Collection<File> findAll(Repo collection) {
		return null;
	}

	public void save(Repo collection, File entity) {
		entity.setRepository(collection);
		repository.save(entity);
	}

	public void saveAndFlush(Repo collection, File entity) {
		entity.setRepository(collection);
		repository.saveAndFlush(entity);
	}
}
