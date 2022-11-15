package edu.ncsu.csc216.stp.model.tests;

/**
 * Concrete Class for handling the result of a test case's execution
 * 
 * @author William Hazlehurst, Erik Svanes
 */
public class TestResult {
	/** Static String for the passing state */
	public static final String PASS = "PASS";
	/** Static String for the failing state */
	public static final String FAIL = "FAIL";
	/** Boolean of whether or not the TestResult was successful */
	private boolean passing;
	/** String of the actual results of the TestResult */
	private String actualResults;

	/**
	 * Constructor for making a new TestResult object, given a boolean of whether or
	 * not it is passing, as well as the actual results as a String.
	 * 
	 * @param passing       Boolean of whether or not the TestResult was successful
	 * @param actualResults String of the actual results of the TestResult
	 */
	public TestResult(boolean passing, String actualResults) {
		if (actualResults == null || actualResults.isEmpty()) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		setActualResults(actualResults);
		setPassing(passing);
	}

	/**
	 * Getter method for the actual results of the TestResult
	 * 
	 * @return the actualResults
	 */
	public String getActualResults() {
		return actualResults;
	}

	/**
	 * Setter method for the actual results of the TestResult
	 * 
	 * @param actualResults the actualResults to set
	 */
	public void setActualResults(String actualResults) {
		this.actualResults = actualResults;
	}

	/**
	 * Getter method for the passing boolean of the TestResults
	 * 
	 * @return the passing
	 */
	public boolean isPassing() {
		return passing;
	}

	/**
	 * Setter method for the passing boolean of the TestResults
	 * 
	 * @param passing the passing to set
	 */
	public void setPassing(boolean passing) {
		this.passing = passing;
	}

	/**
	 * Method to return a String with information about the Test Result. String
	 * returns as "PASS: expected results", if the test result is passing, and
	 * "FAIL: expected results" if it is failing.
	 * 
	 * @return String The String which is representing the TestResult
	 */
	public String toString() {
		String passFail = new String();
		if (isPassing()) {
			passFail = PASS;
		} else {
			passFail = FAIL;
		}
		return passFail + ": " + getActualResults();
	}

}
