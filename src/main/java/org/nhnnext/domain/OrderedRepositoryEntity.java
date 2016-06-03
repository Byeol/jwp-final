package org.nhnnext.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public abstract class OrderedRepositoryEntity<T> extends RepositoryEntity<T> {

	@NotNull
	@GeneratedValue
	private int number;
}
