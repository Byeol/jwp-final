package org.nhnnext.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.io.Serializable;

@NoRepositoryBean
public interface AuditableRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

//	@PostAuthorize("hasPermission(returnObject, 'read')")
	T findOne(ID id);

	@PreAuthorize("hasPermission(#entity, 'write')")
	<S extends T> S save(@Param("entity") S entity);

	@PreAuthorize("hasPermission(#entity, 'write')")
	<S extends T> S saveAndFlush(@Param("entity") S entity);

	@PreAuthorize("hasPermission(#entity, 'delete')")
	void delete(@Param("entity") T entity);
}
