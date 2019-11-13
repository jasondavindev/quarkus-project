package org.acme.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.models.Problem;
import org.acme.models.Solution;
import org.acme.utils.FileUtil;

@ApplicationScoped
public class ValidationService {
	@Inject
	private ProblemsService problemsService;
	
	public boolean runValidation(Solution solution) {
		Problem problem = problemsService.findProblem(solution.getProblem());

		if (problem == null) {
			return false;
		}

		for (int i = 1; i <= problem.getCasesTest(); i++) {
			List<String> expected = FileUtil.fileContentToList(
					String.format("%s/expecteds/%s_%d.txt", problemsService.problemsPath, problem.getName(), i));

			List<String> output = FileUtil.fileContentToList(
					String.format("%s/outputs/%d_%d.txt", problemsService.problemsPath, solution.getTimestamp(), i));

			if (expected == null || output == null) {
				return false;
			}

			if (!expected.equals(output)) {
				return false;
			}
		}

		return true;
	}
}
