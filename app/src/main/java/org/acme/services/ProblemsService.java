package org.acme.services;

import java.util.ArrayList;
import java.util.List;

import org.acme.models.Problem;

public class ProblemsService {
	private static ProblemsService instance;

	private List<Problem> problems;
	public final String problemsPath = "./problems";

	private ProblemsService() {
	}

	public static ProblemsService getInstance() {
		if (instance != null) {
			return instance;
		}

		instance = new ProblemsService();
		return instance;
	}

	public List<Problem> getProblems() {
		if (problems != null) {
			return problems;
		}

		// It's necessary add problem here to find files
		problems = new ArrayList<>();
		problems.add(new Problem("a", 2));
		problems.add(new Problem("b", 1));

		return problems;
	}

	public Problem findProblem(String name) {
		for (Problem problem : getProblems()) {
			if (problem.getName().equals(name)) {
				return problem;
			}
		}

		return null;
	}
}
