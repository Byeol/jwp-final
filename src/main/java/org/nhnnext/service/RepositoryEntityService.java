package org.nhnnext.service;

import org.nhnnext.domain.actual.Repo;
import org.nhnnext.repository.BaseRepository;

import java.io.Serializable;

public abstract class RepositoryEntityService<T, K extends Serializable> extends AbstractCollectionEntityService<T, Repo, K> {
	public RepositoryEntityService(BaseRepository<T, Long> repository) {
		super(repository);
	}
}
