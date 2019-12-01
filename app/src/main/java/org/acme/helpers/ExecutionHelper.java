package org.acme.helpers;

import org.acme.services.ProblemsService;

public class ExecutionHelper {
	public static String getScriptFilename(long solutionTimestamp, String solutionFilename) {
		return String.format("%s/scripts/%d_%s", ProblemsService.getInstance().problemsPath, solutionTimestamp,
				solutionFilename);
	}

	public static String getOutputFilename(int caseNumber, long problemTimestamp) {
		return String.format("%s/outputs/%d_%d.txt", ProblemsService.getInstance().problemsPath, problemTimestamp,
				caseNumber);
	}

	public static String getInputFilename(int caseNumber, String problemName) {
		return String.format("%s/inputs/%s_%d.txt", ProblemsService.getInstance().problemsPath, problemName,
				caseNumber);
	}

	public static String getExpectedFilename(int caseNumber, String problemName) {
		return String.format("%s/expecteds/%s_%d.txt", ProblemsService.getInstance().problemsPath, problemName,
				caseNumber);
	}
}
