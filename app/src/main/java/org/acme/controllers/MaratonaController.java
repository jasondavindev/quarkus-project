package org.acme.controllers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.models.Solution;
import org.acme.services.ExecutionService;
import org.acme.services.ValidationService;

@ApplicationScoped
public class MaratonaController {
	@Inject
	private ExecutionService executionService;
	@Inject
	private ValidationService validationService;
	private Set<Solution> solutions = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	public Set<Solution> save(Solution solution) {
		executionService.executeScript(solution);
		boolean isValid = validationService.runValidation(solution);
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
