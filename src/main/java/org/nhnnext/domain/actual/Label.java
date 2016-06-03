package org.nhnnext.domain.actual;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.domain.RepositoryEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Label extends RepositoryEntity<Label> {

	public Label(Long id) {
		setId(id);
	}

	@NotEmpty
	@Column(unique = true)
	private String name;

	@NotEmpty
	private String color;

//	@ManyToMany
//	@JoinTable(name = "ISSUE_LABELS")
//	private final Set<Issue> issues = new HashSet<>();

	@Override
	public void update(Label entity) {
		update(entity.getName(), this::setName);
		update(entity.getColor(), this::setColor);
	}
}
