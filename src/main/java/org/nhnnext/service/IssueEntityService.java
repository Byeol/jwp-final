package org.nhnnext.service;

import org.nhnnext.domain.actual.Issue;
import org.nhnnext.repository.BaseRepository;

import java.io.Serializable;

public abstract class IssueEntityService<T, K extends Serializable> extends AbstractCollectionEntityService<T, Issue, K> {
	public IssueEntityService(BaseRepository<T, Long> repository) {
		super(repository);
	}
}
