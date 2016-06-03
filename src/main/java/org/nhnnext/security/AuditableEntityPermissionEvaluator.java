package org.nhnnext.security;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.AuditableEntity;
import org.nhnnext.domain.MyUserDetails;
import org.nhnnext.domain.actual.User;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.Authentication;

import javax.inject.Inject;
import java.io.Serializable;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class AuditableEntityPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (targetDomainObject == null) {
			return true;
		}

		if (!(targetDomainObject instanceof AuditableEntity)) {
			return false;
		}

		if (!(authentication.getPrincipal() instanceof MyUserDetails)) {
			return false;
		}

		AuditableEntity auditableEntity = (AuditableEntity) targetDomainObject;
		User user = ((MyUserDetails) authentication.getPrincipal()).getUser();

		return auditableEntity.hasPermission(user, getPermission(permission));
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		return false;
	}

	private Permission getPermission(Object permission) {
		if (permission.equals("read")) {
			return BasePermission.READ;
		} else if (permission.equals("write")) {
			return BasePermission.WRITE;
		} else if (permission.equals("create")) {
			return BasePermission.CREATE;
		} else if (permission.equals("delete")) {
			return BasePermission.DELETE;
		}

		return null;
	}
}
