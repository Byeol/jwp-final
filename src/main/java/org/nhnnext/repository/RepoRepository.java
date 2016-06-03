package org.nhnnext.repository;

import org.nhnnext.domain.actual.Repo;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(path = "repos")
public interface RepoRepository extends BaseRepository<Repo, Long> {

//	@PostAuthorize("hasPermission(returnObject, 'read')")
	Repo findOne(Long id);

	@PreAuthorize("hasPermission(#entity, 'write')")
	<S extends Repo> S save(@Param("entity") S entity);

	@PreAuthorize("hasPermission(#entity, 'create')")
	<S extends Repo> S saveAndFlush(@Param("entity") S entity);
}
