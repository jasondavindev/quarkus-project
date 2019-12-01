package org.acme.services;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import org.acme.helpers.ExecutionHelper;
import org.acme.models.Problem;
import org.acme.models.Solution;
import org.acme.utils.FileUtil;
import org.acme.utils.Sourcecode;

@ApplicationScoped
public class ExecutionService {

	public void executeScript(Solution solution) {
		Problem problem = ProblemsService.getInstance().findProblem(solution.getProblem());

		if (problem == null) {
			return;
		}

		String scriptFilename = ExecutionHelper.getScriptFilename(solution.getTimestamp(), solution.getFilename());
		String sourcecode = Sourcecode.decode(solution.getSourcecode());
		FileUtil.writeFile(scriptFilename, sourcecode);

		for (int i = 1; i <= problem.getCasesTest(); i++) {

			String inputFilename = ExecutionHelper.getInputFilename(i, problem.getName());
			String outputFilename = ExecutionHelper.getOutputFilename(i, solution.getTimestamp());
			executeCase(i, scriptFilename, inputFilename, outputFilename);
		}
	}

	private void executeCase(int caseNumber, String scriptFilename, String inputFilename, String outputFilename) {
		String commandString = String.format("python3 %s < %s > %s", scriptFilename, inputFilename, outputFilename);

		try {
			String cmd[] = { "/bin/sh", "-c", commandString };
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			p.destroy();
		} catch (IOException | InterruptedException e) {
			// TODO: handle exception
		}
	}
}
