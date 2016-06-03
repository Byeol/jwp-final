package org.nhnnext.repository;

import org.nhnnext.domain.actual.Comment;
import org.nhnnext.domain.actual.Issue;

import java.util.List;

public interface CommentRepository extends AuditableRepository<Comment, Long> {

	List<Comment> findByIssue(Issue issue);
}
