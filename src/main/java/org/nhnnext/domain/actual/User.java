package org.nhnnext.domain.actual;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.nhnnext.domain.GeneratedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = "username")
@ToString(of = "username")
@Entity
public class User extends GeneratedEntity {

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String avatarUrl;
}
