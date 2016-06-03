package org.nhnnext.domain.actual;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nhnnext.domain.RepositoryEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class File extends RepositoryEntity<File> {
	@NotNull
	private String name;

	@NotNull
	@Lob
	@JsonIgnore
	private byte[] content;

	@NotNull
	private String contentType;

	@NotNull
	private String originalFilename;

	@NotNull
	private long size;

	@Override
	public void update(File entity) {

	}
}
