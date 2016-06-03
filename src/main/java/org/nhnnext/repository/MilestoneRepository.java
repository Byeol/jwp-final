package org.nhnnext.repository;

import org.nhnnext.domain.actual.Milestone;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface MilestoneRepository extends AuditableRepository<Milestone, Long> {
}
