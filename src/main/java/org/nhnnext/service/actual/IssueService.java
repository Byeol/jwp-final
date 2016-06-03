package org.nhnnext.service.actual;

import org.nhnnext.domain.OrderedRepositoryEntity;
import org.nhnnext.domain.actual.Issue;
import org.nhnnext.domain.actual.Label;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.domain.actual.User;
import org.nhnnext.repository.IssueRepository;
import org.nhnnext.repository.UserRepository;
import org.nhnnext.service.RepositoryEntityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class IssueService extends RepositoryEntityService<Issue, Integer> {

	private final LabelService labelService;
	private final MilestoneService milestoneService;
	private final UserRepository userRepository;
	private final IssueRepository repository;

	@Inject
	public IssueService(IssueRepository repository, UserRepository userRepository, LabelService labelService, MilestoneService milestoneService) {
		super(repository);
		this.repository = repository;
		this.userRepository = userRepository;
		this.labelService = labelService;
		this.milestoneService = milestoneService;
	}

	public Collection<Issue> findAll(Repo collection) {
		if (collection == null) {
			throw new IllegalArgumentException();
		}
		return collection.getIssues();
	}

	public Issue findOne(Repo collection, Integer number) {
		if (collection == null) {
			throw new IllegalArgumentException();
		}
		return collection.getIssue(number);
	}

	public void save(Repo collection, Issue entity) {
		collection.putIssue(entity);
		save(entity);
	}

	public void saveAndFlush(Repo collection, Issue entity) {
		collection.putIssue(entity);
		saveAndFlush(entity);
	}

	protected void beforeSave(Issue entity) {
		Repo repo = entity.getRepository();

		update(entity.getAssignee(), entity::setAssignee, userRepository);
		update(entity.getMilestone(), entity::setMilestone, repo, milestoneService);

		Set<Label> labels = new HashSet<>();
		entity.getLabels().forEach(label -> update(label, labels::add, repo, labelService));
		entity.updateLabels(labels);
	}

	private <T> void update(T value, Consumer<? super T> consumer) {
		Optional.ofNullable(value).ifPresent(consumer);
	}

	private <T extends User> void update(T value, Consumer<User> consumer, UserRepository repository) {
		try {
			update(value, entity -> consumer.accept(Optional.of(repository.findByUsername(entity.getUsername())).get()));
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	private <T extends OrderedRepositoryEntity> void update(T value, Consumer<? super T> consumer, Repo repo, RepositoryEntityService<T, Integer> service) {
		try {
			update(value, entity -> consumer.accept(Optional.of(service.findOne(repo, entity.getNumber())).get()));
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}

	private <T extends Label> void update(T value, Consumer<? super T> consumer, Repo repo, RepositoryEntityService<T, String> service) {
		try {
			update(value, entity -> consumer.accept(Optional.of(service.findOne(repo, entity.getName())).get()));
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
	}
}
