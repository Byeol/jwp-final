package org.nhnnext.domain.actual;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.domain.OrderedRepositoryEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class Milestone extends OrderedRepositoryEntity<Milestone> {

	public Milestone(Long id) {
		setId(id);
	}

	@NotEmpty
	private String title;

	@NotNull
	private State state = State.open;

	private String description;

	private Timestamp dueOn;

	@Override
	public void update(Milestone entity) {
		update(entity.getTitle(), this::setTitle);
		update(entity.getState(), this::setState);
		update(entity.getDescription(), this::setDescription);
		update(entity.getDueOn(), this::setDueOn);
	}

	public enum State {
		closed, open
	}
}
