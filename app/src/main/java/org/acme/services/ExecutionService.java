package org.acme.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExecutionService {
	public static List<String> executeScript(char problem) {
		try {
			String cmd[] = { "/bin/sh", "-c",
					"cat /home/jason/Documentos/test.txt | python3 /home/jason/Documentos/script.py" };
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();

			List<String> output = readOutput(p);

			p.destroy();
			return output;
		} catch (IOException | InterruptedException e) {
			System.out.println(e);
		}

		return null;
	}

	private static List<String> readOutput(Process proc) {
		List<String> out = null;

		try {
			BufferedReader output = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			out = new ArrayList<String>();

			String line;

			while ((line = output.readLine()) != null) {
				out.add(line);
			}
		} catch (IOException e) {
			System.out.println("Error while reading line");
		}

		return out;
	}
}
