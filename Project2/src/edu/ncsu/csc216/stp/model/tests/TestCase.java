package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * Concrete Class for creating, modifying, and storing test cases, which make up
 * System Test Plans
 * 
 * @author William Hazlehurst, Erik Svanes
 */
public class TestCase {
	/** ID of the TestCase as a String */
	private String testCaseId;
	/** Test Type of the TestCase as a String */
	private String testType;
	/** Description of the TestCase as a String */
	private String testDescription;
	/** Excepted Results field of the TestCase as a String */
	private String expectedResults;
	/** TestPlan of the TestCase as a TestPlan object */
	private TestPlan testPlan;
	/** Boolean for whether or not the TestCase is passing */
	private boolean isPassing;
	/** Log of the TestResults for a given TestCase */
	private ILog<TestResult> testResults;

	/**
	 * Constructor of a TestCase given four fields, the TestCase ID, Test Type, Test
	 * Description, and Expected Results, all as Strings.
	 * 
	 * @param testCaseId      ID of the TestCase as a String
	 * @param testType        Test Type of the TestCase as a String
	 * @param testDescription Description of the TestCase as a String
	 * @param expectedResults Expected Results of the TestCase as a String
	 */
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		testResults = new Log<TestResult>();
	}

	/**
	 * Getter method for the TestCaseId
	 * 
	 * @return the testCaseId
	 */
	public String getTestCaseId() {
		return testCaseId;
	}

	/**
	 * Setter method for the TestCaseId
	 * 
	 * @param testCaseId the testCaseId to set
	 */
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	/**
	 * Getter method for the testType
	 * 
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * SetterMethod for the TestType
	 * 
	 * @param testType the testType to set
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}

	/**
	 * Getter method for the TestDescription
	 * 
	 * @return the testDescription
	 */
	public String getTestDescription() {
		return testDescription;
	}

	/**
	 * Setter method for the TestCase Description
	 * 
	 * @param testDescription the testDescription to set
	 */
	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	/**
	 * Getter method for the Expected results
	 * 
	 * @return the expectedResults
	 */
	public String getExpectedResults() {
		return expectedResults;
	}

	/**
	 * Setter method for the expected results
	 * 
	 * @param expectedResults the expectedResults to set
	 */
	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}

	/**
	 * Method for adding TestResults to a TestCase given a boolean of whether or not
	 * it is passing and the TestCase's actual results.
	 * 
	 * @param passing       Boolean of whether or not the TestCase is passing
	 * @param actualResults Actual Results of the TestCase
	 */
	public void addTestResult(boolean passing, String actualResults) {
		TestResult newResult = new TestResult(passing, actualResults);
		testResults.add(newResult);
		isPassing = passing;
	}

	/**
	 * Method for getting the testCase's passing boolean
	 * 
	 * @return The boolean value of whether or not the TestCase is passing
	 */
	public boolean isTestCasePassing() {
		return testResults.get(testResults.size()).isPassing();
	}

	/**
	 * Getter method of the Status of a TestCase
	 * 
	 * @return The status of the TestCase as a String
	 */
	public String getStatus() {
		if (this.isPassing) {
			return TestResult.PASS;
		} else {
			return TestResult.FAIL;
		}
	}

	/**
	 * Getter method for the ActualResultsLog of the TestCase
	 * 
	 * @return ActualResultsLog of the TestCase as a String
	 */
	public String getActualResultsLog() {
		String out = new String();
		String passFail = new String();
		for (int i = 0; i < testResults.size(); i++) {
			if (testResults.get(i).isPassing()) {
				passFail = TestResult.PASS;
			} else if (!testResults.get(i).isPassing()) {
				passFail = TestResult.FAIL;
			}
			if (i != 0) {
				out += "\n";
			}
			out = out + "-" + passFail + ": " + testResults.get(i).getActualResults();
		}
		return out;
	}

	/**
	 * Setter method for the TestPlan of the TestCase
	 * 
	 * @param t The TestPlan object which the TestCase is being set to
	 */
	public void setTestPlan(TestPlan t) {
		this.testPlan = t;
	}

	/**
	 * Getter method for the TestPlan of the TestCase
	 * 
	 * @return The TestPlan of the TestCase
	 */
	public TestPlan getTestPlan() {
		return this.testPlan;
	}

	/**
	 * Method for getting the String of the TestCase, which consists of whether it
	 * is passing or not, followed by the actual results
	 * 
	 * @return String of the TestCase
	 */
	public String toString() {
		String out = new String();
		out = out + "# " + this.testCaseId + "," + this.testType + "\n";
		out = out + "* " + this.getTestDescription() + "\n* " + this.getExpectedResults() + "\n";
		out = out + this.getActualResultsLog();
		return out;
	}

}
