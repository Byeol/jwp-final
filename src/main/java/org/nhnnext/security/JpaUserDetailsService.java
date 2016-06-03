package org.nhnnext.security;

import lombok.RequiredArgsConstructor;
import org.nhnnext.domain.MyUserDetails;
import org.nhnnext.domain.actual.User;
import org.nhnnext.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.text.MessageFormat;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Component
public class JpaUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(MessageFormat.format("Username {0} not found", username));
		}

		return new MyUserDetails(user);
	}
}
