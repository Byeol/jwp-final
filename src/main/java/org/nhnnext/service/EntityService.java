package org.nhnnext.service;

import java.io.Serializable;

public interface EntityService<T, ID extends Serializable> {

	T findOne(ID id);
	void save(T entity);
	void saveAndFlush(T entity);
	void delete(T entity);
}
