package org.acme.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.models.Problem;

@ApplicationScoped
public class ProblemsService {
	private List<Problem> problems;
	public final String problemsPath = "./problems";

	public List<Problem> getProblems() {
		if (problems != null) {
			return problems;
		}

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
