package org.nhnnext.repository;

import org.nhnnext.domain.actual.Label;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "labels", path = "labels")
public interface LabelRepository extends AuditableRepository<Label, Long> {
}
