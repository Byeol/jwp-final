package org.nhnnext.web.actual;

import org.nhnnext.domain.MyUserDetails;
import org.nhnnext.domain.actual.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getAuthenticatedUser(@AuthenticationPrincipal MyUserDetails userDetails) {
		return userDetails.getUser();
	}
}
