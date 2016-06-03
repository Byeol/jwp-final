package org.nhnnext.service;

import java.io.Serializable;
import java.util.Collection;

public interface CollectionEntityService<E, T, K extends Serializable> extends EntityService<E, Long> {

	E findOne(T collection, K key);
	Collection<E> findAll(T collection);
	void save(T collection, E entity);
	void save(T collection, Collection<E> entities);
	void saveAndFlush(T collection, E entity);
	void saveAndFlush(T collection, Collection<E> entities);
}
