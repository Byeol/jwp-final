package org.nhnnext.repository;

import org.nhnnext.domain.actual.Issue;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface IssueRepository extends AuditableRepository<Issue, Long> {
}
