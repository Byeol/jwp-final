package org.nhnnext.repository;

import org.nhnnext.domain.actual.User;

public interface UserRepository extends BaseRepository<User, Long> {

	User findByUsername(String username);
}
