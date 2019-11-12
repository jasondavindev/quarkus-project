package org.acme.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static List<String> fileContentToList(String path) {
		List<String> output = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
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

	public static boolean writeFile(String filename, String data) {
		BufferedWriter output = null;

		try {
			File file = new File(filename);
			output = new BufferedWriter(new FileWriter(file));
			output.write(data);
			output.close();
			return true;
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return false;
	}
}
