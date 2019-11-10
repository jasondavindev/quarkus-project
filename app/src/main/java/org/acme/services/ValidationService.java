package org.acme.services;

import java.util.List;

import org.acme.models.Problem;
import org.acme.models.Solution;
import org.acme.utils.FileUtil;

public class ValidationService {
	public static boolean runValidation(Solution solution) {
		Problem problem = ProblemsService.findProblem(solution.getProblem());

		if (problem == null) {
			return false;
		}

		for (int i = 1; i <= problem.getCasesTest(); i++) {
			List<String> expected = FileUtil.fileContentToList(
					String.format("%s/expecteds/%s_%d.txt", ProblemsService.problemsPath, problem.getName(), i));

			List<String> output = FileUtil.fileContentToList(
					String.format("%s/outputs/%d_%d.txt", ProblemsService.problemsPath, solution.getTimestamp(), i));

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
