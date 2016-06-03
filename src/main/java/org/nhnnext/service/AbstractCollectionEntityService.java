package org.nhnnext.service;

import org.nhnnext.repository.BaseRepository;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractCollectionEntityService<E, T, K extends Serializable> extends AbstractEntityService<E, Long> implements CollectionEntityService<E, T, K> {
	public AbstractCollectionEntityService(BaseRepository<E, Long> repository) {
		super(repository);
	}

	public void save(T collection, Collection<E> entities) {
		entities.forEach(entity -> save(collection, entity));
	}

	public void saveAndFlush(T collection, Collection<E> entities) {
		entities.forEach(entity -> saveAndFlush(collection, entity));
	}
}
