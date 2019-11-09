package org.acme.models;

public class Solution {
	private String sourcecode;
	private String filename;
	private int timestamp;
	private char problem;
	private String status;

	public Solution() {
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public char getProblem() {
		return problem;
	}

	public void setProblem(char problem) {
		this.problem = problem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSourcecode() {
		return sourcecode;
	}

	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
