package org.nhnnext.domain.actual;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.nhnnext.domain.IssueEntity;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "body")
@Entity
public class Comment extends IssueEntity<Comment> {

	public Comment(Long id) {
		setId(id);
	}

	@NotEmpty
	private String body;

	@Override
	public void update(Comment entity) {
		update(entity.getBody(), this::setBody);
	}
}
