package org.nhnnext.repository;

import org.nhnnext.domain.actual.Comment;
import org.nhnnext.domain.actual.Issue;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CommentRepository extends AuditableRepository<Comment, Long> {

	List<Comment> findByIssue(Issue issue);
}
