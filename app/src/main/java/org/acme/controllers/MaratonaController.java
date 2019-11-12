package org.acme.controllers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.acme.models.Solution;
import org.acme.services.ExecutionService;
import org.acme.services.ValidationService;

public class MaratonaController {
	private Set<Solution> solutions = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	public Set<Solution> save(Solution solution) {
		ExecutionService.executeScript(solution);
		boolean isValid = ValidationService.runValidation(solution);
		solution.setStatus(isValid ? "SUCCESS" : "FAIL");
		solutions.add(solution);
		return solutions;
	}

	public List<Solution> filterByStatus(String status) {
		List<Solution> sol = solutions.stream()
				.filter(solution -> solution.getStatus().toLowerCase().equals(status.toLowerCase()))
				.collect(Collectors.toList());
		return sol;
	}

	public List<Solution> filterByProblem(String problem) {
		List<Solution> sol = solutions.stream()
				.filter(solution -> solution.getProblem().toLowerCase().equals(problem.toLowerCase()))
				.collect(Collectors.toList());
		return sol;
	}

	public List<Solution> filterByDatetime(long datetime) {
		List<Solution> sol = solutions.stream().filter(solution -> solution.getTimestamp() >= datetime)
				.collect(Collectors.toList());
		return sol;
	}

	public Set<Solution> getSolutions() {
		return solutions;
	}
}
