package org.nhnnext.service.actual;

import org.nhnnext.domain.actual.Issue;
import org.nhnnext.domain.actual.Label;
import org.nhnnext.repository.IssueRepository;
import org.nhnnext.repository.LabelRepository;
import org.nhnnext.service.IssueEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class IssueLabelService extends IssueEntityService<Label,String> {

	private final IssueRepository issueRepository;
	private final LabelService service;

	@Inject
	public IssueLabelService(LabelRepository repository, LabelService service, IssueRepository issueRepository) {
		super(repository);
		this.service = service;
		this.issueRepository = issueRepository;
	}

	public Label findOne(Issue collection, String key) {
		return service.findOne(collection.getRepository(), key);
	}

	public Collection<Label> findAll(Issue collection) {
		return collection.getLabels();
	}

	public void save(Issue collection, Label entity) {
		collection.addLabel(entity);
		issueRepository.save(collection);
	}

	public void save(Issue collection, Collection<Label> entities) {
		collection.addLabels(entities);
		issueRepository.save(collection);
	}

	public void saveAndFlush(Issue collection, Label entity) {
		collection.addLabel(entity);
		issueRepository.saveAndFlush(collection);
	}

	public void saveAndFlush(Issue collection, Collection<Label> entities) {
		collection.addLabels(entities);
		issueRepository.save(collection);
	}
}
