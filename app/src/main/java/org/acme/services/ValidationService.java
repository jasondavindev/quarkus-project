package org.acme.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.acme.models.Solution;

public class ValidationService {
	public static boolean runValidation(Solution solution) {
		List<String> scriptExecutionOutput = (ArrayList<String>) ExecutionService.executeScript(solution.getProblem());
		List<String> expectedOutput = (ArrayList<String>) readSolutionFile(solution.getProblem());

		if (scriptExecutionOutput == null || expectedOutput == null) {
			return false;
		}

		return expectedOutput.equals(scriptExecutionOutput);
	}

	private static List<String> readSolutionFile(char problem) {
		List<String> output = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader("/home/jason/Documentos/expected.txt"));
			output = new ArrayList<String>();

			String line;

			while ((line = file.readLine()) != null) {
				output.add(line);
			}

			file.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

		return output;
	}
}
