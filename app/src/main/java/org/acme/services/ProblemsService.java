package org.acme.services;

import java.util.ArrayList;
import java.util.List;

import org.acme.models.Problem;

// TODO: make a singleton service
public class ProblemsService {
	private static List<Problem> problems;
	public static final String problemsPath = "../problems";

	public static List<Problem> getProblems() {
		if (problems != null) {
			return problems;
		}

		problems = new ArrayList<>();
		problems.add(new Problem("a", 2));
		problems.add(new Problem("b", 1));

		return problems;
	}

	public static Problem findProblem(String name) {
		for (Problem problem : getProblems()) {
			if (problem.getName().equals(name)) {
				return problem;
			}
		}

		return null;
	}
}
