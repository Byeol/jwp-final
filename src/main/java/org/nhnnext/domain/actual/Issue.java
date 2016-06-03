package org.nhnnext.domain.actual;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.domain.OrderedRepositoryEntity;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.acls.domain.BasePermission;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Issue extends OrderedRepositoryEntity<Issue> {

	public Issue(Long id) {
		setId(id);
	}

	@NotEmpty
	private String title;

	private String body;

	@ManyToOne
	@JsonDeserialize(using = UserDeserializer.class)
	private User assignee;

	private static class UserDeserializer extends JsonDeserializer<User> {

		@Override
		public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			User user = new User();
			user.setUsername(p.getValueAsString());

			return user;
		}
	}

	@NotNull
	private State state = State.open;

	@ManyToOne
	@RestResource(exported = true)
	@JsonProperty
	@JsonDeserialize(using = MilestoneDeserializer.class)
	private Milestone milestone;

	private static class MilestoneDeserializer extends JsonDeserializer<Milestone> {

		@Override
		public Milestone deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			Milestone milestone = new Milestone();
			milestone.setNumber(p.getValueAsInt());
			return milestone;
		}
	}

	@ManyToMany //(mappedBy = "issues")
	@RestResource(exported = true)
	@JsonProperty
	@JsonDeserialize(using = LabelsDeserializer.class)
	private Set<Label> labels = new HashSet<>();

	private static class LabelsDeserializer extends JsonDeserializer<Set<Label>> {

		@Override
		public Set<Label> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			Label label = new Label();
			label.setName(p.getValueAsString());

			Set<Label> labels = new HashSet<>();
			labels.add(label);
			return labels;
		}
	}

	public void addLabel(Label label) {
		this.labels.add(label);
	}

	public void addLabels(Collection<Label> labels) {
		this.labels.addAll(labels);
	}

	public void removeLabel(Label label) {
		this.labels.remove(label);
	}

	public boolean hasLabel(Label label) {
		return this.labels.contains(label);
	}

	public void updateLabels(Collection<Label> labels) {
		this.labels.clear();
		this.labels.addAll(labels);
	}

	@Override
	public boolean hasPermission(User user, Object permission) {
		return BasePermission.CREATE.equals(getPermission(permission)) || super.hasPermission(user, permission);
	}

	@Override
	public void update(Issue entity) {
		update(entity.getTitle(), this::setTitle);
		update(entity.getBody(), this::setBody);
		update(entity.getAssignee(), this::setAssignee);
		update(entity.getState(), this::setState);
		update(entity.getMilestone(), this::setMilestone);
		update(entity.getLabels(), this::setLabels);
	}

	public enum State {
		closed, open
	}
}
