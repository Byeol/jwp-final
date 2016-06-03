package org.nhnnext.service;

import lombok.RequiredArgsConstructor;
import org.nhnnext.repository.BaseRepository;

import java.io.Serializable;

@RequiredArgsConstructor
public abstract class AbstractEntityService<T, ID extends Serializable> implements EntityService<T, ID> {
	private final BaseRepository<T, ID> repository;

	public T findOne(ID id) {
		return repository.findOne(id);
	}

	public void save(T entity) {
		beforeSave(entity);
		repository.save(entity);
	}

	public void saveAndFlush(T entity) {
		beforeSave(entity);
		repository.saveAndFlush(entity);
	}

	public void delete(T entity) {
		repository.delete(entity);
	}

	protected void beforeSave(T entity) {};
}
