package org.nhnnext.web.actual;

import org.nhnnext.domain.actual.Label;
import org.nhnnext.repository.RepoRepository;
import org.nhnnext.service.actual.IssueLabelService;
import org.nhnnext.web.IssueEntityController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@RepositoryRestController
@RequestMapping(IssueLabelController.BASE_MAPPING)
public class IssueLabelController extends IssueEntityController<Label, String> {

	protected static final String BASE_MAPPING = IssueEntityController.BASE_MAPPING + "/labels";

	@Inject
	public IssueLabelController(IssueLabelService service, RepoRepository repoRepository) {
		super(service, repoRepository);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getCollectionResource(@PathVariable Long repoId, @PathVariable Integer number) {
		return super.getCollectionResource(repoId, number);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> postCollectionResource(@PathVariable Long repoId, @PathVariable Integer number, @RequestBody List<String> keys) {
		return super.postCollectionResource(repoId, number, keys);
	}
}
