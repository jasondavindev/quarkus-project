package org.acme.models;

public class Problem {
	private String name;
	private int casesTest;
	
	public Problem(String name, int casesTest) {
		setName(name);
		setCasesTest(casesTest);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCasesTest() {
		return casesTest;
	}

	public void setCasesTest(int casesTest) {
		this.casesTest = casesTest;
	}
}
