package org.acme.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.helpers.ExecutionHelper;
import org.acme.models.Problem;
import org.acme.models.Solution;
import org.acme.utils.FileUtil;

@ApplicationScoped
public class ValidationService {

	public boolean runValidation(Solution solution) {
		Problem problem = ProblemsService.getInstance().findProblem(solution.getProblem());

		if (problem == null) {
			return false;
		}

		for (int i = 1; i <= problem.getCasesTest(); i++) {
			if (!runValidationCase(i, problem.getName(), solution.getTimestamp())) {
				return false;
			}
		}

		return true;
	}

	private boolean runValidationCase(int caseNumber, String problemName, long solutionTimestamp) {
		List<String> expected = FileUtil.fileContentToList(ExecutionHelper.getExpectedFilename(caseNumber, problemName));
		List<String> output = FileUtil.fileContentToList(ExecutionHelper.getOutputFilename(caseNumber, solutionTimestamp));

		if (expected == null || output == null) {
			return false;
		}

		if (!expected.equals(output)) {
			return false;
		}
		
		return true;
	}
}
