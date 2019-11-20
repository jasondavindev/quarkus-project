package org.acme.models;

import java.util.Date;

public class Solution {
	private String sourcecode;
	private String filename;
	private long timestamp;
	private String problem;
	private String status;

	public Solution() {
		setTimestamp(new Date().getTime());
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
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
