package org.nhnnext.domain;

import org.nhnnext.domain.actual.User;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractUpdatable<T> extends AbstractAuditable<User, Long> implements Updatable<T> {

	protected static <T> void update(T value, Consumer<? super T> consumer) {
		Optional.ofNullable(value).ifPresent(consumer);
	}
}
