package org.nhnnext.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.nhnnext.domain.actual.Issue;
import org.nhnnext.domain.actual.User;
import org.springframework.security.acls.domain.BasePermission;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public abstract class IssueEntity<T> extends AuditableEntity<T> {

	@NotNull
	@ManyToOne
	@JsonIgnore
	private Issue issue;

	@Override
	public boolean hasPermission(User user, Object permission) {
		if (!getIssue().hasPermission(user, BasePermission.READ)) {
			return false;
		}

		return BasePermission.CREATE.equals(getPermission(permission)) || super.hasPermission(user, permission);
	}
}
