package org.nhnnext.domain.actual;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.domain.AuditableEntity;
import org.springframework.security.acls.domain.BasePermission;

import javax.persistence.*;
import java.util.*;

@Data
@EqualsAndHashCode(exclude = "issues", callSuper = true)
@ToString(exclude = "issues")
@Entity
public class Repo extends AuditableEntity<Repo> {

	@NotEmpty
	private String name;

	private String description;

	@ManyToMany
	private final Set<User> collaborators = new HashSet<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL)
	@OrderColumn(name = "number")
	private final List<Issue> issues = new ArrayList<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL)
	@OrderColumn(name = "number")
	private final List<Milestone> milestones = new ArrayList<>();

	@OneToMany(mappedBy = "repository", cascade = CascadeType.ALL)
	@MapKey(name = "name")
	private final Map<String, Label> labels = new HashMap<>();

	@Override
	public boolean hasPermission(User user, Object permission) {
		return BasePermission.CREATE.equals(getPermission(permission)) || hasCollaborator(user) || super.hasPermission(user, permission);
	}

	@Override
	public void update(Repo entity) {
		setName(entity.getName());
		setDescription(entity.getDescription());
	}

	public Collection<User> getCollaborators() {
		Collection<User> collaborators = new HashSet<>();
		collaborators.addAll(this.collaborators);
		collaborators.add(this.getCreatedBy());
		return Collections.unmodifiableCollection(collaborators);
	}

	public boolean hasCollaborator(User user) {
		return getCollaborators().contains(user);
	}

	public void putCollaborator(User user) {
		collaborators.add(user);
	}

	public void deleteCollaborator(User user) {
		collaborators.remove(user);
	}

	public Issue getIssue(Integer number) {
		try {
			return issues.get(number);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void putIssue(Issue issue) {
		issue.setRepository(this);
		if (!hasIssue(issue)) {
			issues.add(issue);
		}
	}

	public boolean hasIssue(Issue issue) {
		return issues.contains(issue);
	}

	public Milestone getMilestone(Integer number) {
		try {
			return milestones.get(number);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void putMilestone(Milestone milestone) {
		milestone.setRepository(this);
		milestones.add(milestone);
	}

	public Label getLabel(String name) {
		return labels.get(name);
	}

	public void putLabel(Label label) {
		Optional.of(label).ifPresent(entity -> {
			entity.setRepository(this);
			labels.put(entity.getName(), entity);
		});
	}
}
