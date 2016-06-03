package org.nhnnext.domain;

import org.nhnnext.domain.actual.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.acls.domain.BasePermission;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AuditableEntity<T> extends AbstractUpdatable<T> {

	public boolean isCreator(User user) {
		return Objects.equals(getCreatedBy(), user);
	}

	protected Object getPermission(Object permission) {
		if (BasePermission.WRITE.equals(permission) && isNew()) {
			return BasePermission.CREATE;
		}

		return permission;
	}

	public boolean hasPermission(User user, Object permission) {
		return BasePermission.READ.equals(permission) || isCreator(user);
	}
}
