package org.nhnnext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nhnnext.domain.actual.Repo;
import org.nhnnext.domain.actual.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(exclude = "repository") // TO CHECK
@ToString(exclude = "repository") // TO CHECK
@MappedSuperclass
public abstract class RepositoryEntity<T> extends AuditableEntity<T> {

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "repository_id")
	@JsonIgnore
	private Repo repository;

	@Override
	public boolean hasPermission(User user, Object permission) {
		return getRepository().hasCollaborator(user) || getRepository().isCreator(user) ||
				super.hasPermission(user, permission);
	}
}
